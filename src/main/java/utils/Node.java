package utils;

import storage.Tree;

import java.io.FileWriter;
import java.io.IOException;
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

    public static void printNode(Node node){
        System.out.println(node.getData().toString());

        if (!node.getChildList().isEmpty()){
            for (Node child:
                    node.getChildList()) {
                printNode(child);
            }
        }
    }

    public static void writeToFile(Node node, String path){
        try(FileWriter writer = new FileWriter(path, false))
        {
            writeNode(node, writer);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static void writeNode(Node node, FileWriter writer) throws IOException {
        writer.write(node.getData().toString() + "\n");

        if (!node.getChildList().isEmpty()){
            for (Node child:
                    node.getChildList()) {
                writeNode(child, writer);
            }
        }
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
