package utils;

import storage.Tree;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseFile {
    private static int id;
    private static final String REGEX_HOSTNAME = "[a-zA-Z_]+[a-zA-Z0-9_]*";
    private static final String REGEX_VALUE = ".*[\"^]+.*";
    private static Pattern pattern;
    private static Matcher matcher;

    public static Node parse(String path){
        id = 1;
        Node node = Node.NodeBuilder.aNode().build();
        node.setChildList(new LinkedList<Node>());

        try(FileReader reader = new FileReader(path))
        {
            detour(node, reader, 0);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

        return node;
    }

    private static boolean detour(Node node, FileReader reader, int parentId) throws Exception {
        String word = "";
        Node nodeChild = Node.NodeBuilder.aNode().withChildList(new LinkedList<Node>()).build();
        int c;
        try {
            while((c=reader.read())!=-1){
                if ((char)c == ' ' || (char)c == '\n'){
                    // начало списка
                    if (word.equals("{")){
                        while (detour(nodeChild, reader, node.getData().getNodeId())){
                            node.getChildList().add(nodeChild);
                            nodeChild = Node.NodeBuilder.aNode().withChildList(new LinkedList<Node>()).build();
                        }

                        return true;
                    }
                    // конец списка
                    else if (word.equals("}")){
                        return false;
                    }
                    // имя узла
                    else if (word.length() != 0 && word.indexOf('\"') == -1 && word.indexOf('=') == -1) {
                        //Проверка имени узла на корректность
                        pattern = Pattern.compile(REGEX_HOSTNAME);
                        matcher = pattern.matcher(word);

                        if (matcher.matches()){

                            node.setData(Tree.TreeBuilder.aTree()
                                    .withParentId(parentId)
                                    .withNodeId(id)
                                    .withNodeName(word)
                                    .build());
                            id++;

                        }
                        else{
                            throw new Exception("Неверный формат данны");
                        }
                    }
                    // значение
                    else if (word.length() != 0 && word.indexOf('\"') != -1){
                        word = word.substring(1, word.length() -1);

                        pattern = Pattern.compile(REGEX_VALUE);
                        matcher = pattern.matcher(word);
                        if(!matcher.matches()){
                            node.getData().setNodeValue(word);
                        }
                        else {
                            throw new Exception("Неверный формат данны");
                        }
                        return true;
                    }

                    word = "";
                }
                else{
                    word += (char)c;
                }
            }

            if (nodeChild.getData() != null){
                node.getChildList().add(nodeChild);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        return false;
    }
}
