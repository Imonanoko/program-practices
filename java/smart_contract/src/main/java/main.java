import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class main {
    public static void main(String[] args) throws FileNotFoundException {
        String a = "sixsquare";
        String b = "aaa";
        List<List<String>> x = new ArrayList<>();
        x.add(List.of(a,b));
        System.out.println(x);
//        String path = "C:\\Users\\Six square\\Desktop\\µ{¦¡»y¨¥½m²ß\\java\\smart_contract\\src\\main\\java\\contract.txt";
//        List<List<String>> line = library.file_analyses(path);
////        System.out.println(line.size());
//        for(List<String> str : line){
//            System.out.println(str);
//        }
//
//
//        System.out.println("------------------------------");
//        List<List<List<String>>> outcome = library.variable_analyses(line);
//        List<List<String>> contract_global_var = new ArrayList<>(outcome.get(0));
//        List<List<String>> function_local_var = new ArrayList<>(outcome.get(1));
//        for (List<String> global_var : contract_global_var) {
//            System.out.println(global_var);
//        }
//        for (List<String> local_var : function_local_var) {
//            System.out.println(local_var);
//        }

    }
}
