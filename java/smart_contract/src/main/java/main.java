import object.contract;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class main {
    public static void main(String[] args) throws FileNotFoundException {
//        for(int i=1;i<33;i++){
//            System.out.print("\"bytes"+i+"\", ");
//        }
        String path = "C:\\Users\\Six square\\Desktop\\µ{¦¡»y¨¥½m²ß\\java\\smart_contract\\src\\main\\java\\contract.txt";
        List<List<String>> line = library.file_analyses(path);
//        System.out.println(line.size());
//        for(List<String> str : line){
//            System.out.println(str);
//        }
        List<contract> x = library.variable_analyses(line);
        for(contract c:x){
            c.display();
            System.out.println("--------------------------------------------------------------------------------------------------------------");
        }

    }
}
