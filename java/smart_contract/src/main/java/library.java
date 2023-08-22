import object.contract;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


//"C:\\Users\\Six square\\Desktop\\程式語言練習\\java\\smart_contract\\src\\main\\java\\contract.txt"
public class library {
    protected static List<List<String>> file_analyses(String Path) throws FileNotFoundException {
        List<List<String>> lines = new ArrayList<>();
        File doc = new File(Path);
        Scanner obj = new Scanner(doc);
        //以行為單位讀取資料
        while (obj.hasNextLine()) {
            //存每一行的每個詞
            List<String> word_List = new ArrayList<>();
            //將每一行的每個字暫存，處理玩家到word_List
            StringBuilder word = new StringBuilder();
            String str = obj.nextLine();
            //遇到註解符號的話後面就不理它
            out:
            //依序讀每個character
            for (int i = 0; i < str.length(); i++) {
                char chr = str.charAt(i);
                switch (chr) {
                    //如果是空格代表前面這些字是一組的，所以將前一組自存到List裡面
                    case ' ' -> {
                        //沒有東西不加入，不然很亂又佔記憶體
                        if (!word.toString().equals("")) {
                            word_List.add(word.toString());
                            word.setLength(0);
                        }
                    }
                    //將字串單獨放入一個格子裡，因為如果遇到特殊字元會被切割
                    case '\"' -> {
                        if (!word.toString().equals("")) {
                            word_List.add(word.toString());
                            word.setLength(0);
                        }
                        word_List.add(Character.toString(chr));
                        i++;
                        char tmp = str.charAt(i);
                        while (tmp != '\"') {
                            word.append(tmp);
                            i++;
                            tmp = str.charAt(i);
                        }
                        if (!word.toString().equals("")) {
                            word_List.add(word.toString());
                            word.setLength(0);
                        }
                        word_List.add(Character.toString(chr));
                        i++;
                    }
                    //如果是;、(、)等字元會被區分開來，亦即;、(、{是被獨立加進word裡面的
                    case ';', '(', ')', '{', '}', '.', '\'', ',', '~', '[', ']', '?' -> {
                        if (!word.toString().equals("")) {
                            word_List.add(word.toString());
                            word.setLength(0);
                        }
                        word_List.add(Character.toString(chr));
                    }
                    //因為=、<、>可能後面接一樣=、<、>的字元所以需要檢查後面是否有這些字元，沒有就加進word裡面、有就把兩個字元一起加進去word
                    case '=', '<', '>', '|', '&', '!', '*' -> {
                        if (!word.toString().equals("")) {
                            word_List.add(word.toString());
                            word.setLength(0);
                        }
                        char tmp = str.charAt(i + 1);
                        switch (tmp) {
                            case '=', '<', '>', '|', '&', '!', '*' -> {
                                word.append(chr);
                                word.append(tmp);
                                word_List.add(word.toString());
                                word.setLength(0);
                                i++;
                            }
                            default -> word_List.add(Character.toString(chr));
                        }

                    }
                    //遇到運算式需要將符號分開並檢查後面是否有符號
                    case '+', '-', '%', '^' -> {
                        if (!word.toString().equals("")) {
                            word_List.add(word.toString());
                            word.setLength(0);
                        }
                        if (str.charAt(i + 1) == '=') {
                            word.append(chr);
                            word.append(str.charAt(i + 1));
                            word_List.add(word.toString());
                            word.setLength(0);
                            i++;
                        } else {
                            word_List.add(Character.toString(chr));
                        }
                    }
                    case '/' -> {
                        if (str.charAt(i + 1) == '/') {
                            break out;
                        }
                        if (!word.toString().equals("")) {
                            word_List.add(word.toString());
                            word.setLength(0);
                        }
                        if (str.charAt(i + 1) == '=') {
                            word.append(chr);
                            word.append(str.charAt(i + 1));
                            word_List.add(word.toString());
                            word.setLength(0);
                            i++;
                        } else {
                            word_List.add(Character.toString(chr));
                        }
                    }
                    //非上述字串就會當成跟前面的字元是一組的
                    default -> word.append(chr);
                }
            }
            if (!word.toString().equals("")) {
                word_List.add(word.toString());
            }
            lines.add(new ArrayList<>(word_List));
        }
        return lines;
    }

    private static String find_visibility(List<String> line) {
        for (String word : line) {
            switch (word) {
                case "public" -> {
                    return "public";
                }
                case "private" -> {
                    return "private";
                }
                case "internal" -> {
                    return "internal";
                }
                case "external" -> {
                    return "external";
                }
            }
        }
        return "public";
    }

    private static boolean is_array(List<String> line) {
        return line.contains("[") && line.contains("]");
    }

    private static String find_array_size(List<String> line) {
        for (int i = 0; i < line.size(); i++) {
            String word = line.get(i);
            if (Objects.equals(word, "[")) {
                i++;
                if (!Objects.equals(line.get(i), "]")) {
                    return line.get(i);
                }
            }
        }

        return "INF";
    }

    private static String find_array_name(List<String> line) {
        List<String> sub_list;
        if (line.contains("=")) {
            sub_list = line.subList(1, line.indexOf("="));
        } else {
            sub_list = line.subList(1, line.size());
        }
        sub_list.remove(";");
        String size = find_array_size(line);
        sub_list.remove("[");
        sub_list.remove("]");
        sub_list.remove(size);
        sub_list.remove("memory");
//        if(sub_list.contains("memory"))
        if (line.contains("public") || line.contains("private") || line.contains("internal") || line.contains("external")) {
            return sub_list.get(1);
        }
        return sub_list.get(0);

    }

    private static List<List<String>> find_parameter(List<String> line) {
        List<List<String>> find_parameter = new ArrayList<>();
        List<List<String>> parameter_set = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        for (int i = line.indexOf("(") + 1; i < line.indexOf(")"); i++) {
            if (Objects.equals(line.get(i), ",")) {
                parameter_set.add(new ArrayList<>(tmp));
                tmp.clear();
            } else {
                tmp.add(line.get(i));
            }
        }
        parameter_set.add(new ArrayList<>(tmp));
        tmp.clear();

        for (List<String> P : parameter_set) {
            if(P.isEmpty()){
                return find_parameter;
            }
            else if (is_array(P)) {
                tmp.add("true");
                tmp.add(find_array_name(P));
                tmp.add(P.get(0));


            } else {
                tmp.add("false");
                tmp.add(P.get(1));
                tmp.add(P.get(0));
            }
            find_parameter.add(new ArrayList<>(tmp));
            tmp.clear();
        }
        return find_parameter;
    }
    private static List<String> find_parents(List<String> line){
        if(!line.contains("is")){
            return new ArrayList<>();
        }
        List<String> parents = new ArrayList<>();
        for(int i= line.indexOf("is")+1;i< line.size();i++){
            if(!Objects.equals(line.get(i), ",") && !Objects.equals(line.get(i), "{")){
                parents.add(line.get(i));
            }
        }
        return parents;
    }
    //lines:檔案分析後的資料
    protected static List<contract> variable_analyses(List<List<String>> lines) {
        //包含所有合約的變數、函數
        List<contract> contract_list = new ArrayList<>();
        //暫存每個區塊的變數[name,type]
        List<List<String>> variables = new ArrayList<>();
        //暫存每個區塊的陣列變數[name,size,type]
        List<List<String>> array_variables = new ArrayList<>();
        boolean[] function_or_struct = {false, false};
        //判斷大括號的暫存器[contract,function,if_elif,for,while]
        List<Boolean> flag = new ArrayList<>();
        String word;
        //暫存index用
        int index;
        // i+1為行數
        for (List<String> line : lines) {
            loop_out:
            for (int j = 0; j < line.size(); j++) {
                word = line.get(j);
                switch (word) {
                    case "contract" -> {
                        flag.add(true);
                        contract_list.add(new contract(line.get(j + 1)));
                        index = contract_list.size()-1;
                        contract_list.get(index).add_inheritance(find_parents(line));
                        break loop_out;
                    }
                    case "function" -> {
                        flag.add(true);
                        variables = new ArrayList<>();
                        array_variables = new ArrayList<>();
                        index = contract_list.size() - 1;
                        List<List<String>> parameter = find_parameter(line);
                        contract_list.get(index).add_function(line.get(++j), find_visibility(line), parameter);
                        function_or_struct[0] = true;
                        break loop_out;
                    }
                    case "struct" -> {
                        flag.add(true);
                        variables = new ArrayList<>();
                        array_variables = new ArrayList<>();
                        index = contract_list.size() - 1;
                        contract_list.get(index).add_struct(line.get(++j));
                        function_or_struct[1] = true;
                        break loop_out;
                    }
                    case "if", "for", "while", "do", "else","constructor" -> {
                        flag.add(true);
                        break loop_out;
                    }
//                    case "else" -> {
//                        flag.add(true);
//                        if (line.get(j + 1).equals("if"))
//                            j++;
//                    }
                    case "}" -> {
                        int flag_index = flag.size() - 1;
                        if (flag.size() == 2) {
                            //function
                            if (function_or_struct[0]) {
                                index = contract_list.size() - 1;
                                contract_list.get(index).add_function_local_variable(variables);
                                contract_list.get(index).add_function_array_variable(array_variables);
                            }
                            //struct
                            if (function_or_struct[1]) {
                                index = contract_list.size() - 1;
                                contract_list.get(index).add_struct_variable(variables);
                                contract_list.get(index).add_struct_array_variable(array_variables);
                            } else {
                                System.out.println("error");
                            }
                            //將讀到的variable_list存入
                            function_or_struct[0] = false;
                            function_or_struct[1] = false;
                        }
                        flag.remove(flag_index);
                    }
                    case "int", "int8", "int16", "int24", "int32", "int40", "int48", "int56", "int64", "int72", "int80", "int88", "int96", "int104", "int112", "int120", "int128", "int136", "int144", "int152", "int160", "int168", "int176", "int184", "int192", "int200", "int208", "int216", "int224", "int232", "int240", "int248", "int256",
                            "uint", "uint8", "uint16", "uint24", "uint32", "uint40", "uint48", "uint56", "uint64", "uint72", "uint80", "uint88", "uint96", "uint104", "uint112", "uint120", "uint128", "uint136", "uint144", "uint152", "uint160", "uint168", "uint176", "uint184", "uint192", "uint200", "uint208", "uint216", "uint224", "uint232", "uint240", "uint248", "uint256",
                            "bytes1", "bytes2", "bytes3", "bytes4", "bytes5", "bytes6", "bytes7", "bytes8", "bytes9", "bytes10", "bytes11", "bytes12", "bytes13", "bytes14", "bytes15", "bytes16", "bytes17", "bytes18", "bytes19", "bytes20", "bytes21", "bytes22", "bytes23", "bytes24", "bytes25", "bytes26", "bytes27", "bytes28", "bytes29", "bytes30", "bytes31", "bytes32",
                            "address", "string", "bytes" -> {
                        if (j == 0) {

                            //區域變數
                            if (function_or_struct[0] || function_or_struct[1]) {
                                if (is_array(line)) {
                                    String size = find_array_size(line);
                                    assert size != null;
                                    array_variables.add(new ArrayList<>(List.of(find_array_name(line), size, word)));
                                } else {
                                    variables.add(new ArrayList<>(List.of(line.get(j + 1), word)));
                                }
                            }
                            //全域變數
                            else {
                                String visibility = find_visibility(line);
                                //是array的話
                                if (is_array(line)) {
                                    String size = find_array_size(line);
                                    index = contract_list.size() - 1;
                                    contract_list.get(index).add_array(new ArrayList<>(List.of(find_array_name(line), size, visibility, word)));
                                }
                                //不是array的話
                                else {
                                    String name;
                                    if (line.contains(visibility)) {
                                        name = line.get(2);
                                    } else {
                                        name = line.get(1);
                                    }
                                    index = contract_list.size() - 1;
                                    contract_list.get(index).add_global_variable(new ArrayList<>(List.of(name, word, visibility)));
                                }
                            }
                            break loop_out;
                        }
                    }
                }
            }
        }
        return contract_list;
    }
//    protected static void minimum_visibility(List<List<String>> lines , List<contract> contracts){
//
//    }
}
