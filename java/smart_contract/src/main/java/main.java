import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class main {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\Six square\\Desktop\\µ{¦¡»y¨¥½m²ß\\java\\smart_contract\\src\\main\\java\\contract.txt";
        List<List<String>> line = library.file_analyses(path);
        for (List<String> str : line) {
            System.out.println(str);
        }

    }
}
