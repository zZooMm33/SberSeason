package storage;

/**
 * Класс для хранения данных в узле
 */
public class Tree {

    /**
     * id узла
     */
    private int nodeId;

    /**
     * id вышестоящего узла
     */
    private int parentId;

    /**
     * Название узла
     */
    private String nodeName;

    /**
     * Значение в узле
     */
    private String nodeValue;

    /**
     * Получить id узла
     * @return id узла
     */
    public int getNodeId() {
        return nodeId;
    }

    /**
     * Установить id узла
     * @param nodeId id узла
     */
    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * Получить id вышестоящего узла
     * @return id вышестоящего узла
     */
    public int getParentId() {
        return parentId;
    }

    /**
     * Установить id вышестоящего узла
     * @param parentId id вышестоящего узла
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    /**
     * Получить название узла
     * @return Название узла
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * Установить название узла
     * @param nodeName Название узла
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    /**
     * Получить значение в узле
     * @return Значение в узле
     */
    public String getNodeValue() {
        return nodeValue;
    }

    /**
     * Установить значение в узле
     * @param nodeValue Значение в узле
     */
    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    /**
     * Переопредление toString
     * @return вернет строку содержащую все данные в узле
     */
    @Override
    public String toString() {
        return "Tree{" +
                "nodeId=" + nodeId +
                ", parentId=" + parentId +
                ", nodeName='" + nodeName + '\'' +
                ", nodeValue='" + nodeValue + '\'' +
                '}';
    }

    /**
     * Билдер для Tree
     */
    public static final class TreeBuilder {
        private int nodeId;
        private int parentId;
        private String nodeName;
        private String nodeValue;

        private TreeBuilder() {
        }

        public static TreeBuilder aTree() {
            return new TreeBuilder();
        }

        public TreeBuilder setNodeId(int nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public TreeBuilder setParentId(int parentId) {
            this.parentId = parentId;
            return this;
        }

        public TreeBuilder setNodeName(String nodeName) {
            this.nodeName = nodeName;
            return this;
        }

        public TreeBuilder setNodeValue(String nodeValue) {
            this.nodeValue = nodeValue;
            return this;
        }

        public Tree build() {
            Tree tree = new Tree();
            tree.setNodeId(nodeId);
            tree.setParentId(parentId);
            tree.setNodeName(nodeName);
            tree.setNodeValue(nodeValue);
            return tree;
        }
    }
}
