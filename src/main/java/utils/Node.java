package utils;

import storage.Tree;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Класс для хранения дерева
 */
public class Node {

    /**
     * Данные в узле
     */
    private Tree data;

    /**
     * Потомки
     */
    private List<Node> childList;

    /**
     * Получить данные в узле
     * @return Сущность Tree, которая содержит данные в узле
     */
    public Tree getData() {
        return data;
    }

    /**
     * Установить данные в узле
     * @param data данные
     */
    public void setData(Tree data) {
        this.data = data;
    }

    /**
     * Получить список потомков
     * @return Потомки
     */
    public List<Node> getChildList() {
        return childList;
    }

    /**
     * Установить список потомков
     * @param childList Список потомков
     */
    public void setChildList(List<Node> childList) {
        this.childList = childList;
    }

    /**
     * Выводит дерево в консоль
     * @param node Дерево
     */
    public static void printNode(Node node) {
        if (node != null){
            System.out.println(node.getData().toString());

            if (!node.getChildList().isEmpty()) {
                node.childList.forEach(child -> printNode(child));
            }
        }
    }

    /**
     * Записывает дерево в файл
     * @param node Дерево
     * @param path Путь к файлу
     */
    public static void writeToFile(Node node, String path) {
        if (node != null && path !=null){
            try (FileWriter writer = new FileWriter(path, false)) {
                writeNode(node, writer);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            System.out.printf("Результат парсинга был успешно занесен в файл %s.\n",
                    PropReader.getVal(PropReader.PATH_WRITE_FILE));
        }
    }

    /**
     * Рекурсивный обход дерево с записью в файл
     * @param node Дерево
     * @param writer FileWriter для файла в который буду записываться данные
     * @throws IOException
     */
    private static void writeNode(Node node, FileWriter writer) throws IOException {
        writer.write(node.getData().toString() + "\n");

        if (!node.getChildList().isEmpty()) {

            node.childList.forEach(child -> {
                try {
                    writeNode(child, writer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }
    }

    /**
     * Билдер для Node
     */
    public static final class NodeBuilder {
        private Tree data;
        private List<Node> childList;

        private NodeBuilder() {
        }

        public static NodeBuilder aNode() {
            return new NodeBuilder();
        }

        public NodeBuilder setData(Tree data) {
            this.data = data;
            return this;
        }

        public NodeBuilder setChildList(List<Node> childList) {
            this.childList = childList;
            return this;
        }

        public Node build() {
            Node node = new Node();
            node.setData(data);
            node.setChildList(childList);
            return node;
        }
    }
}
