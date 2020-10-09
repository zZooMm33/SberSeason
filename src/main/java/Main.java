import utils.ParseFile;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String path = "";
        Scanner in = null;

        try{
            in = new Scanner(System.in);
            System.out.print("Введите путь к файлу: ");
            //path = in.nextLine();
            path = "/Users/zzoomm/Desktop/test.txt";
            ParseFile.parse(path);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            if (in != null){
                in.close();
            }
        }
    }
}
