package utils;

import storage.tree.Tree;
import utils.tree.Node;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ParseFile {
    private static int id;

    public static Node parse(String path){
        id = 1;
        Node node = Node.NodeBuilder.aNode().build();
        node.setChildList(new LinkedList<Node>());
        try(FileReader reader = new FileReader(path))
        {
            detour(node, reader, 0);
            printNode(node);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        return node;
    }

    private static boolean detour(Node node, FileReader reader, int parentId){
        Node nodeChild = Node.NodeBuilder.aNode().build();
        String word = "";
        nodeChild.setChildList(new LinkedList<Node>());
        int c;
        try {
            while((c=reader.read())!=-1){
                char as = (char)c;
                if ((char)c == ' ' || (char)c == '\n'){
                    // начало списка
                    if (word.equals("{")){
                        // новые листы для каждого элемента и {
                        while (detour(nodeChild, reader, node.getData().getNodeId())){
                            node.getChildList().add(nodeChild);

                            nodeChild = Node.NodeBuilder.aNode().build();
                            nodeChild.setChildList(new LinkedList<Node>());
                        }

                        return true;
                    }
                    // конец списка
                    else if (word.equals("}")){
                        return false;
                    }
                    // имя узла
                    else if (word.length() != 0 && word.indexOf('\"') == -1 && word.indexOf('=') == -1) {
                        //Проверка на корректность
                        node.setData(Tree.TreeBuilder.aTree()
                                .withParentId(parentId)
                                .withNodeId(id)
                                .withNodeName(word)
                                .build());
                        id++;
                    }
                    // значение
                    else if (word.length() != 0 && word.indexOf('\"') != -1){
                        //Проверка на корректность
                        word = word.replaceAll("\"", "");
                        node.getData().setNodeValue(word);
                        return true;
                    }

                    word = "";
                }
                else{
                    word += (char)c;
                }
            }

            // В последний узел не добавили значение
            if (nodeChild.getData() != null){
                node.getChildList().add(nodeChild);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        return false;
    }

    private static void printNode(Node node){

        System.out.println(node.getData().toString());

        if (!node.getChildList().isEmpty()){
            for (Node child:
                    node.getChildList()) {
                printNode(child);
            }
        }

    }
}
