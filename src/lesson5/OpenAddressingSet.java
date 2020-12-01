package lesson5;

import org.jetbrains.annotations.NotNull;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

public class OpenAddressingSet<T> extends AbstractSet<T> {

    private enum Status {Removed}

    private final int bits;

    private final int capacity;

    private final Object[] storage;

    private int size = 0;

    private int startingIndex(Object element) {
        return element.hashCode() & (0x7FFFFFFF >> (31 - bits));
    }

    public OpenAddressingSet(int bits) {
        if (bits < 2 || bits > 31) {
            throw new IllegalArgumentException();
        }
        this.bits = bits;
        capacity = 1 << bits;
        storage = new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Проверка, входит ли данный элемент в таблицу
     */
    @Override
    public boolean contains(Object o) {
        int index = startingIndex(o);
        Object current = storage[index];
        while (current != null) {
            if (current.equals(o)) {
                return true;
            }
            index = (index + 1) % capacity;
            current = storage[index];
        }
        return false;
    }

    /**
     * Добавление элемента в таблицу.
     *
     * Не делает ничего и возвращает false, если такой же элемент уже есть в таблице.
     * В противном случае вставляет элемент в таблицу и возвращает true.
     *
     * Бросает исключение (IllegalStateException) в случае переполнения таблицы.
     * Обычно Set не предполагает ограничения на размер и подобных контрактов,
     * но в данном случае это было введено для упрощения кода.
     */
    @Override
    public boolean add(T t) {
        int startingIndex = startingIndex(t);
        int index = startingIndex;
        Object current = storage[index];
        while (current != null && current != Status.Removed) {
            if (current.equals(t)) {
                return false;
            }
            index = (index + 1) % capacity;
            if (index == startingIndex) {
                throw new IllegalStateException("Table is full");
            }
            current = storage[index];
        }
        storage[index] = t;
        size++;
        return true;
    }

    /**
     * Удаление элемента из таблицы
     *
     * Если элемент есть в таблица, функция удаляет его из дерева и возвращает true.
     * В ином случае функция оставляет множество нетронутым и возвращает false.
     * Высота дерева не должна увеличиться в результате удаления.
     *
     * Спецификация: {@link Set#remove(Object)} (Ctrl+Click по remove)
     *
     * Средняя
     */
    //Трудоемкость = O(capacity)
    //Ресурсоемкость = O(1)
    @Override
    public boolean remove(Object o) {
        int startingIndex = startingIndex(o);
        int i = startingIndex;
        Object current = storage[i];

        while (current != null) {
            if (current.equals(o)) {  //удаляем элемент, уменьшаем размер
                storage[i] = Status.Removed;
                size--;
                return true;
            }
            i = (i + 1) % capacity;
            if (i == startingIndex) return false;
            //следующий элемент - current
            current = storage[i];
        }
        return false;
    }

    /**
     * Создание итератора для обхода таблицы
     *
     * Не забываем, что итератор должен поддерживать функции next(), hasNext(),
     * и опционально функцию remove()
     *
     * Спецификация: {@link Iterator} (Ctrl+Click по Iterator)
     *
     * Средняя (сложная, если поддержан и remove тоже)
     */
    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new OpenAddressingSetIterator();
    }

    public class OpenAddressingSetIterator implements Iterator <T> {

        Object current = null;
        private int i = 0;
        private int previous = -1;

        private OpenAddressingSetIterator() {
            findNextIndex();
        }
        private void findNextIndex() {
            while (i < capacity && (storage[i] == null || storage[i] == Status.Removed)) {
                i++;
            }
        }

        //Трудоемкость = O(capacity)
        //Ресурсоемкость = O(1)
        @Override
        public T next() {
            if (!hasNext()) throw new IllegalStateException();
            current = storage[i];
            previous = i;
            i++;
            findNextIndex();
            return (T) current;
        }

        //Трудоемкость = O(1)
        //Ресурсоемкость = O(1)
        @Override
        public void remove() {
            if (current == null || previous == -1) throw new IllegalStateException();
            storage[previous] = Status.Removed;
            size--;
        }

        //Трудоемкость = O(1)
        //Ресурсоемкость = O(1)
        @Override
        public boolean hasNext() {
            return i < capacity;
        }
    }
}
