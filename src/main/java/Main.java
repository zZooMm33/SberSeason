import utils.Node;
import utils.ParseFile;
import utils.PropReader;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Node node = null;
        node = ParseFile.parse(PropReader.getVal(PropReader.PATH_READ_FILE));

        if (node != null){
            Node.writeToFile(node, PropReader.getVal(PropReader.PATH_WRITE_FILE));
        }
    }
}
