package utils.tree;

import storage.tree.Tree;

import java.util.LinkedList;
import java.util.List;

public class Node {

    private Tree data; //некоторые данные в узле
    private List<Node> childList; //потомоки

    public Tree getData() {
        return data;
    }

    public void setData(Tree data) {
        this.data = data;
    }

    public List<Node> getChildList() {
        return childList;
    }

    public void setChildList(List<Node> childList) {
        this.childList = childList;
    }

    public static final class NodeBuilder {
        private Tree data; //некоторые данные в узле
        private List<Node> childList; //потомоки

        private NodeBuilder() {
        }

        public static NodeBuilder aNode() {
            return new NodeBuilder();
        }

        public NodeBuilder withData(Tree data) {
            this.data = data;
            return this;
        }

        public NodeBuilder withChildList(List<Node> childList) {
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
