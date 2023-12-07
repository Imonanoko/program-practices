import object.contract;

import java.io.*;
import java.util.List;


public class main {
    public static void main(String[] args) throws IOException {
//        for(int i=1;i<33;i++){
//            System.out.print("\"bytes"+i+"\", ");
//        }
        String path = "C:\\Users\\Six square\\Desktop\\µ{¦¡»y¨¥½m²ß\\java\\smart_contract\\src\\main\\java\\test.txt";
        List<List<String>> line = library.file_analyses(path);
        for(List<String>x:line){
            System.out.println(x);
        }
        library.creat_fuzzy_testing_file(line);


//        System.out.println(line.size());
//        for(List<String> str : line){
//            System.out.println(str);
//        }


//        library.variable_analyses(line);
//        List<contract> x = library.variable_analyses(line);
//        for(contract c:x){
//            c.display();
//            System.out.println("--------------------------------------------------------------------------------------------------------------");
//        }

    }
}
