package lesson7;

import kotlin.NotImplementedError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    //Трудоемкость = O(N * M)
    //Ресурсоемкость = O(N * M)
    //N - длина первой строки
    //M - длина второй строки
    public static String longestCommonSubSequence(String first, String second) {
        int lengthFirst = first.length() + 1;
        int lengthSecond = second.length() + 1;
        int[][] matrix = new int[lengthFirst][lengthSecond];
        //заполняем матрицу
        for (int x = 1; x < lengthFirst; x++) {
            for (int y = 1; y < lengthSecond; y++) {
                char char1 = first.charAt(x - 1);
                char char2 = second.charAt(y - 1);
                if (char1 != char2) {
                    matrix[x][y] = Math.max(matrix[x - 1][y], matrix[x][y - 1]);
                } else {
                    matrix[x][y] = matrix[x - 1][y - 1] + 1;
                }
            }
        }
        lengthFirst--;
        lengthSecond--;
        StringBuilder answer = new StringBuilder();
        while (lengthFirst != 0 && lengthSecond != 0) {
            char char1 = first.charAt(lengthFirst - 1);
            char char2 = second.charAt(lengthSecond - 1);
            if (char1 == char2) {
                answer.insert(0, char1);
                lengthFirst--;
                lengthSecond--;
                continue;
            }
            if (matrix[lengthFirst - 1][lengthSecond] >= matrix[lengthFirst][lengthSecond - 1]) {
                lengthFirst--;
            } else {
                lengthSecond--;
            }
        }
        return answer.toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    //Трудоемкость = O(N^2)
    //Ресурсоемкость = O(N)
    //N - длина списка
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        int size = list.size();
        if (size <= 1) return list;
        int[] previous = new int[size];
        int[] value = new int[size]; //будет хранить длины подпоследовательностей
        //поиск максимальной длины
        for (int i = 0; i < size; i++) {
            value[i] = 1;
            previous[i] = -1;
            for (int j = 0; j < i; j++) {
                boolean logic1 = list.get(j) < list.get(i);
                boolean logic2 = value[j] + 1 > value[i];
                if (logic1 && logic2) {
                    value[i] = value[j] + 1;
                    previous[i] = j;
                }
            }
        }

        int index = 0;
        int length = 0;
        for (int i = 0; i < size; i++) {
            if (value[i] > length) {
                index = i;
                length = value[i];
            }
        }
        //восстановление ответа
        List<Integer> result = new ArrayList<>();
        while (index != -1) {
            result.add(0, list.get(index));
            index = previous[index];
        }
        return result;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
