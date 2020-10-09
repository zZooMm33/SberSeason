package storage.tree;

public class Tree {
    private int nodeId;
    private int parentId;
    private String nodeName;
    private String nodeValue;

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "nodeId=" + nodeId +
                ", parentId=" + parentId +
                ", nodeName='" + nodeName + '\'' +
                ", nodeValue='" + nodeValue + '\'' +
                '}';
    }

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

        public TreeBuilder withNodeId(int nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public TreeBuilder withParentId(int parentId) {
            this.parentId = parentId;
            return this;
        }

        public TreeBuilder withNodeName(String nodeName) {
            this.nodeName = nodeName;
            return this;
        }

        public TreeBuilder withNodeValue(String nodeValue) {
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
