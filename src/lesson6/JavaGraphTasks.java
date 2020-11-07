package lesson6;

import kotlin.NotImplementedError;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import lesson6.Graph.Vertex;
import lesson6.Graph.Edge;

@SuppressWarnings("unused")
public class JavaGraphTasks {
    /**
     * Эйлеров цикл.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
     * Если в графе нет Эйлеровых циклов, вернуть пустой список.
     * Соседние дуги в списке-результате должны быть инцидентны друг другу,
     * а первая дуга в списке инцидентна последней.
     * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
     * Веса дуг никак не учитываются.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
     *
     * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
     * связного графа ровно по одному разу
     */
    public static List<Graph.Edge> findEulerLoop(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Минимальное остовное дерево.
     * Средняя
     *
     * Дан связный граф (получатель). Найти по нему минимальное остовное дерево.
     * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
     * вернуть любое из них. Веса дуг не учитывать.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ:
     *
     *      G    H
     *      |    |
     * A -- B -- C -- D
     * |    |    |
     * E    F    I
     * |
     * J ------------ K
     */
    public static Graph minimumSpanningTree(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Максимальное независимое множество вершин в графе без циклов.
     * Сложная
     *
     * Дан граф без циклов (получатель), например
     *
     *      G -- H -- J
     *      |
     * A -- B -- D
     * |         |
     * C -- F    I
     * |
     * E
     *
     * Найти в нём самое большое независимое множество вершин и вернуть его.
     * Никакая пара вершин в независимом множестве не должна быть связана ребром.
     *
     * Если самых больших множеств несколько, приоритет имеет то из них,
     * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
     *
     * В данном случае ответ (A, E, F, D, G, J)
     *
     * Если на входе граф с циклами, бросить IllegalArgumentException
     *
     * Эта задача может быть зачтена за пятый и шестой урок одновременно
     */
    //Ресурсоемкость O(V)
    //Трудоемкость O(V + E)
    //V - число вершин графа
    //E - число граней графа
    public static Set<Vertex> largestIndependentVertexSet(Graph graph) {
       if (!isCycled(graph)) {
           Set<Vertex> independent = graph.getVertices();
           if (independent.isEmpty()) return new HashSet<>();

           Set<Vertex> neighbors;
           //смотрим каждую вершину графа
           for (Vertex vertex : graph.getVertices()) {
               //если вершина является еще независимой
               if (independent.contains(vertex)) {
                   neighbors = graph.getNeighbors(vertex);
                   //убираем всех соседей этой вершины из независимых
                   for (Vertex element : neighbors) {
                       independent.remove(element);
                   }
               }
               //поиск максимального наибольшего множества независимых вершин
               Set<Vertex> initialIndependent = graph.getVertices();
               //если найденный набор независимых вершин является максимальным
               if (independent.size() < graph.getVertices().size() - independent.size()) {
                   //убираем независимые вершины из первоначального множества
                   for (Vertex element : independent) {
                       initialIndependent.remove(element);
                   }
                   return initialIndependent;
               }
           }
           return independent;
       }
       return new HashSet<>();
    }

    private static boolean isCycled(Graph graph) {
        Set<Vertex> starts = new HashSet<>();
        Set<Vertex> ends = new HashSet<>();
        Set<Edge> edges = graph.getEdges();

        //для каждой ребра заполняем массив
        for (Edge edge : edges) {
            Vertex start = edge.getBegin();
            Vertex end = edge.getEnd();

            //если есть общие вершины в множествах - зациклен
            boolean logic1 = starts.contains(end) && ends.contains(start);
            boolean logic2 = starts.contains(start) && ends.contains(end);
            if (logic1 || logic2) return true;

            //конец ребра есть уже в начале, добавляем начало в оба множества
            if (starts.contains(end)) {
                starts.add(start);
                ends.add(start);
            }
            //начало ребра есть уже в конце, добавляем конец в оба множества
            if (ends.contains(start)) {
                starts.add(end);
                ends.add(end);
            }
            //если ребро еще не проверили
            if (!starts.contains(start) && !ends.contains(end)) {
                starts.add(start);
                ends.add(end);
            }
        }
        return false;
    }

    /**
     * Наидлиннейший простой путь.
     * Сложная
     *
     * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
     * Простым считается путь, вершины в котором не повторяются.
     * Если таких путей несколько, вернуть любой из них.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ: A, E, J, K, D, C, H, G, B, F, I
     */
    //Ресурсоемкость O(V!)
    //Трудоемкость O(V!)
    //V - число вершин графа
    public static Path longestSimplePath(Graph graph) {
        Set<Vertex> allVertices = graph.getVertices(); //все вершины
        if (allVertices.isEmpty()) return new Path(); //если вершин нет
        Path answer = new Path(allVertices.iterator().next()); //самый длинный маршрут

        PriorityQueue<Path> queue = new PriorityQueue<>();
        for (Vertex element : allVertices) { //добавляем все вершины в очередь
            queue.add(new Path(element));
        }

        int maxLen = 0;
        do {
            Path path = queue.poll(); //из начала очереди
            assert path != null;

            if (path.getLength() >= maxLen) { //если нашли путь длиннее, записываем в ответ
                answer = path;
                maxLen = path.getLength();
            }

            List<Vertex> vertices = path.getVertices();
            Vertex temp = vertices.get(vertices.size() - 1);
            Set<Vertex> neighbors = graph.getNeighbors(temp); //получаем соседей

            for (Vertex element : neighbors) { //проходимся по соседям и добавляем новые пути
                if (!path.contains(element)) queue.add(new Path(path, graph, element)); //previous, graph, next
            }

        } while (!queue.isEmpty());
        return answer;
    }


    /**
     * Балда
     * Сложная
     *
     * Задача хоть и не использует граф напрямую, но решение базируется на тех же алгоритмах -
     * поэтому задача присутствует в этом разделе
     *
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     *
     * И Т Ы Н
     * К Р А Н
     * А К В А
     *
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     *
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     *
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     *
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) {
        throw new NotImplementedError();
    }
}
