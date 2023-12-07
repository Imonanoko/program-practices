import object.contract;

import java.io.*;
import java.util.*;


//"C:\\Users\\Six square\\Desktop\\�{���y���m��\\java\\smart_contract\\src\\main\\java\\contract.txt"
public class library {
    protected static List<List<String>> file_analyses(String Path) throws IOException {
        List<List<String>> lines = new ArrayList<>();
        File doc = new File(Path);
        FileReader fileReader = new FileReader(doc);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //�h����ѺX��
        boolean MLCF = false;

        //�H�欰���Ū�����
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            //�s�C�@�檺�C�ӵ�
            List<String> word_List = new ArrayList<>();
            //�N�C�@�檺�C�Ӧr�Ȧs�A�B�z���[��word_List
            StringBuilder word = new StringBuilder();
            //�J����ѲŸ����ܫ᭱�N���z��
            out:
            //�̧�Ū�C��character
            for (int i = 0; i < str.length(); i++) {
                char chr = str.charAt(i);
                switch (chr) {
                    //�p�G�O�Ů�N��e���o�Ǧr�O�@�ժ��A�ҥH�N�e�@�զۦs��List�̭�
                    case ' ' -> {
                        //�S���F�褣�[�J�A���M�ܶäS���O����
                        if (!word.toString().isEmpty()) {
                            word_List.add(word.toString());
                            word.setLength(0);
                        }
                    }
                    //�N�r���W��J�@�Ӯ�l�̡A�]���p�G�J��S��r���|�Q����
                    case '\"' -> {
                        //�N���e���r���Jword_list
                        if (!word.toString().isEmpty()) {
                            word_List.add(word.toString());
                            word.setLength(0);
                        }
                        word.append(chr);
                        i++;
                        char tmp = str.charAt(i);
                        while (tmp != '\"') {
                            word.append(tmp);
                            i++;
                            tmp = str.charAt(i);
                        }
                        word.append(chr);
                        if (!word.toString().isEmpty()) {
                            word_List.add(word.toString());
                            word.setLength(0);
                        }
                    }
                    //�p�G�O;�B(�B)���r���|�Q�Ϥ��}�ӡA��Y;�B(�B{�O�Q�W�ߥ[�iword�̭���
                    case ';', '(', ')', '{', '}', '.', '\'', ',', '~', '[', ']', '?' -> {
                        if (!word.toString().isEmpty()) {
                            word_List.add(word.toString());
                            word.setLength(0);
                        }
                        word_List.add(Character.toString(chr));
                    }
                    //�]��=�B<�B>�i��᭱���@��=�B<�B>���r���ҥH�ݭn�ˬd�᭱�O�_���o�Ǧr���A�S���N�[�iword�̭��B���N���Ӧr���@�_�[�i�hword
                    case '=', '<', '>', '|', '&', '!', '*' -> {
//                        System.out.println(chr);
                        //�N�e�����r�x�s�A�M��]����
                        if (!word.toString().isEmpty()) {
                            word_List.add(word.toString());
                            word.setLength(0);
                        }
//                        System.out.println("i:"+i+" str: "+str+" chr: "+chr);
                        if (i + 1 >= str.length()) {
                            if (!MLCF) {
                                word.append(chr);
                                word_List.add(word.toString());
                                word.setLength(0);
                            }
                            break;
                        }
                        char tmp = str.charAt(i + 1);
                        switch (tmp) {
                            case '=' ->{
                                if(chr=='*'){
                                    if(!word_List.isEmpty()){
                                        String var = String.join("",word_List);
                                        word_List.add("=");
                                        word_List.add(var);
                                        word_List.add(Character.toString(chr));
                                        i++;
                                    }
                                }
                                else {
                                    word.append(chr);
                                    word.append(tmp);
                                    word_List.add(word.toString());
                                    word.setLength(0);
                                    i++;
                                }
                            }
                            case '|', '&', '!', '*' -> {
                                word.append(chr);
                                word.append(tmp);
                                word_List.add(word.toString());
                                word.setLength(0);
                                i++;
                            }
                            case '>', '<' -> {
                                word.append(chr);
                                word.append(tmp);
                                if (i + 2 >= str.length() && !MLCF) {
                                    word_List.add(word.toString());
                                    word.setLength(0);
                                    i++;
                                } else if (str.charAt(i + 2) == '=') {
                                    word.append(str.charAt(i + 2));
                                    word_List.add(word.toString());
                                    word.setLength(0);
                                    i += 2;
                                } else {
                                    word_List.add(word.toString());
                                    word.setLength(0);
                                    i++;
                                }
                            }
                            case '/' -> {
                                MLCF = false;
                                word.setLength(0);
                                break out;
                            }
                            default -> {
                                if (!MLCF) {
                                    word_List.add(Character.toString(chr));
                                }
                            }
                        }

                    }
                    //�J��B�⦡�ݭn�N�Ÿ����}���ˬd�᭱�O�_���Ÿ�
                    case '+', '-', '%', '^' -> {
                        if (!word.toString().isEmpty()) {
                            word_List.add(word.toString());
                            word.setLength(0);
                        }
                        if (str.charAt(i + 1) == '-'||str.charAt(i + 1) == '+') {
                            word.append(chr);
                            word.append(str.charAt(i + 1));
                            word_List.add(word.toString());
                            word.setLength(0);
                            i++;
                        } else if (str.charAt(i + 1) == '=') {
                            if(!word_List.isEmpty()){
                                String var = String.join("",word_List);
                                word_List.add("=");
                                word_List.add(var);
                                word_List.add(Character.toString(chr));
                                i++;
                            }
                        } else {
                            word_List.add(Character.toString(chr));
                        }
                    }
                    case '/' -> {
                        if (!word.toString().isEmpty()) {
                            word_List.add(word.toString());
                            word.setLength(0);
                        }
                        switch (str.charAt(i + 1)) {
                            case '/' -> {
                                break out;
                            }
                            case '=' -> {
                                if(!word_List.isEmpty()){
                                    String var = String.join("",word_List);
                                    word_List.add("=");
                                    word_List.add(var);
                                    word_List.add(Character.toString(chr));
                                    i++;
                                }
                            }
                            case '*' -> {
                                MLCF = true;
                            }
                            default -> {
                                word_List.add(Character.toString(chr));
                            }
                        }
                    }
                    //�D�W�z�r��N�|����e�����r���O�@�ժ�
                    default -> word.append(chr);
                }
                if (MLCF && chr != ' ') {
                    word.setLength(0);
                    break;
                }
            }
            if (!word.toString().isEmpty()) {
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
        //[�ϧ_���}�Cname,type]
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
            if (P.isEmpty()) {
                return find_parameter;
            } else if (is_array(P)) {
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

    private static List<String> find_parents(List<String> line) {
        if (!line.contains("is")) {
            return new ArrayList<>();
        }
        List<String> parents = new ArrayList<>();
        for (int i = line.indexOf("is") + 1; i < line.size(); i++) {
            if (!Objects.equals(line.get(i), ",") && !Objects.equals(line.get(i), "{")) {
                parents.add(line.get(i));
            }
        }
        return parents;
    }

    //lines:�ɮפ��R�᪺���
    protected static List<contract> variable_analyses(List<List<String>> lines) {
        //�]�t�Ҧ��X�����ܼơB���
        List<contract> contract_list = new ArrayList<>();
        //�Ȧs�C�Ӱ϶����ܼ�[name,type]
        List<List<String>> variables = new ArrayList<>();
        //�Ȧs�C�Ӱ϶����}�C�ܼ�[name,size,type]
        List<List<String>> array_variables = new ArrayList<>();
        boolean[] function_or_struct = {false, false};
        //�P�_�j�A�����Ȧs��[contract,function,if_elif,for,while]
        List<Boolean> flag = new ArrayList<>();
        String word;
        //�Ȧsindex��
        int index;
        // i+1�����
        for (List<String> line : lines) {
            loop_out:
            for (int j = 0; j < line.size(); j++) {
                word = line.get(j);
                switch (word) {
                    case "contract" -> {
                        flag.add(true);
//                        System.out.println(word);
                        contract_list.add(new contract(line.get(j + 1)));
                        index = contract_list.size() - 1;
                        contract_list.get(index).add_inheritance(find_parents(line));
                        break loop_out;
                    }
                    case "library" -> {
                        flag.add(true);
//                        System.out.println(word);
                        contract_list.add(new contract(line.get(j + 1) + "_library"));
                        index = contract_list.size() - 1;
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
                    case "if", "for", "while", "do", "else", "constructor" -> {
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
                            index = contract_list.size() - 1;
                            contract contract = contract_list.get(index);
                            //function
                            if (function_or_struct[0]) {
                                contract.add_function_local_variable(variables);
                                contract.add_function_array_variable(array_variables);
                            }
                            //struct
                            else if (function_or_struct[1]) {
                                contract.add_struct_variable(variables);
                                contract.add_struct_array_variable(array_variables);
                            } else {
                                contract.NeedInit();
                            }
                            //�NŪ�쪺variable_list�s�J
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

                            //�ϰ��ܼ�
                            if (function_or_struct[0] || function_or_struct[1]) {
                                if (is_array(line)) {
                                    String size = find_array_size(line);
                                    assert size != null;
                                    array_variables.add(new ArrayList<>(List.of(find_array_name(line), size, word)));
                                } else {
                                    variables.add(new ArrayList<>(List.of(line.get(j + 1), word)));
                                }
                            }
                            //�����ܼ�
                            else {
                                String visibility = find_visibility(line);
                                //�Oarray����
                                if (is_array(line)) {
                                    String size = find_array_size(line);
                                    index = contract_list.size() - 1;
                                    contract_list.get(index).add_array(new ArrayList<>(List.of(find_array_name(line), size, visibility, word)));
                                }
                                //���Oarray����
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

    //�B�z�C�@�檺�r�åB�d�ݻݤ��ݭn�[�Wassert
    private static String insert_string(List<String> line) {
        StringBuilder str = new StringBuilder();
        StringBuilder logic_assert = new StringBuilder();
        //���A����index
        List<Integer> left_parenthesis = new ArrayList<>();
        //�k�A����index
        List<Integer> right_parenthesis = new ArrayList<>();
        //+��index
        List<Integer> addition = new ArrayList<>();
        //-��index
        List<Integer> sub = new ArrayList<>();
        //*��index
        List<Integer> product = new ArrayList<>();
        //����index
        List<Integer> divide = new ArrayList<>();
//        List<Integer[]> parenthesis_position = new ArrayList<>();
        for (int i = 0; i < line.size(); i++) {
            String word = line.get(i);
            switch (word) {
                case "+" -> {
                    str.append(" ").append(word).append(" ");
                    addition.add(i);
                }
                case "-" -> {
                    str.append(" ").append(word).append(" ");
                    sub.add(i);
                }
                case "*" -> {
                    str.append(" ").append(word).append(" ");
                    product.add(i);
                }
                case "/" -> {
                    str.append(" ").append(word).append(" ");
                    divide.add(i);
                }
                case "(" -> {
                    left_parenthesis.add(i);
                    if ((i + 1) < line.size() && !Objects.equals(line.get(i + 1), ")") && !Objects.equals(line.get(i + 1), "(") && !Objects.equals(line.get(i + 1), "+") && !Objects.equals(line.get(i + 1), "-") && !Objects.equals(line.get(i + 1), "*") && !Objects.equals(line.get(i + 1), "/")) {
                        str.append(word).append(line.get(i + 1));
                        i++;
                    } else
                        str.append(word);
                }
                case ")" -> {
                    right_parenthesis.add(i);
                    if ((i + 1) < line.size() && !Objects.equals(line.get(i + 1), ")") && !Objects.equals(line.get(i + 1), "(") && !Objects.equals(line.get(i + 1), "+") && !Objects.equals(line.get(i + 1), "-") && !Objects.equals(line.get(i + 1), "*") && !Objects.equals(line.get(i + 1), "/")) {
                        str.append(word).append(line.get(i + 1));
                        i++;
                    } else
                        str.append(word);
                }
                case "[", "]", ".", ";", ":","++","--" -> {
                    if ((i + 1) < line.size() && !Objects.equals(line.get(i + 1), ")") && !Objects.equals(line.get(i + 1), "(") && !Objects.equals(line.get(i + 1), "+") && !Objects.equals(line.get(i + 1), "-") && !Objects.equals(line.get(i + 1), "*") && !Objects.equals(line.get(i + 1), "/")) {
                        str.append(word).append(line.get(i + 1));
                        i++;
                    } else
                        str.append(word);
                }
                case "assert" -> {
                    str.append("require");
                }
                default -> {
                    str.append(" ").append(word);
                }
            }
        }
//        if (line.contains("assert") || line.contains("require")||line.contains("for")||line.contains("if")) {
//            return str.toString();
//        }
        if(!line.contains("=")&&!line.contains("return")){
            return str.toString();
        }
        //�����A��
        while (!left_parenthesis.isEmpty()) {
            int indexhead = left_parenthesis.get(left_parenthesis.size() - 1);
            left_parenthesis.remove(Integer.valueOf(indexhead));
            int indextail = 0;
            for (int i = 0; i < right_parenthesis.size(); i++) {
                if (right_parenthesis.get(i) > indexhead) {
                    indextail = right_parenthesis.get(i);
                    right_parenthesis.remove(i);
                    break;
                }
            }
            if (indextail != 0) {
                //�B�z�A�����B��O*�����p
                for (int i = 0; i < product.size(); i++) {
                    int index = product.get(i);
                    if (index > indexhead && index < indextail) {
                        product.remove(i);
                        StringBuilder multiplicand = new StringBuilder();
                        StringBuilder multiplier = new StringBuilder();
                        for (int j = indexhead + 1; j < index; j++) {
                            multiplicand.append(line.get(j));
                            if (Objects.equals(line.get(j), ",")) {
                                multiplicand.setLength(0);
                                ;
                            }
                        }
                        for (int j = index + 1; j < indextail; j++) {
                            if (Objects.equals(line.get(j), ",")) {
                                break;
                            }
                            multiplier.append(line.get(j));
                        }
                        logic_assert.append("assert(").append(multiplicand).append("*").append(multiplier).append("/").append(multiplicand).append("==").append(multiplier).append(");\n");
                        break;
                    }
                }
                //�B�z�A�����B��O/�����p
                for (int i = 0; i < divide.size(); i++) {
                    int index = divide.get(i);
                    if (index > indexhead && index < indextail) {
                        divide.remove(i);
                        StringBuilder dividend = new StringBuilder();
                        StringBuilder divisor = new StringBuilder();
                        for (int j = indexhead + 1; j < index; j++) {
                            dividend.append(line.get(j));
                            if (Objects.equals(line.get(j), ",")) {
                                dividend.setLength(0);
                            }
                        }
                        for (int j = index + 1; j < indextail; j++) {
                            if (Objects.equals(line.get(j), ",")) {
                                break;
                            }
                            divisor.append(line.get(j));
                        }
                        logic_assert.append("assert(").append(divisor).append(">0").append(");\n");
                        logic_assert.append("assert(").append(dividend).append("==").append(divisor).append("*(").append(dividend).append("/").append(divisor).append(") +").append(dividend).append("%").append(divisor).append(");\n");
                        break;
                    }
                }
                //�B�z�A�����B��O+�����p
                for (int i = 0; i < addition.size(); i++) {
                    int index = addition.get(i);
                    if (index > indexhead && index < indextail) {
                        addition.remove(i);
                        StringBuilder addend = new StringBuilder();
                        StringBuilder augend = new StringBuilder();
                        for (int j = indexhead + 1; j < index; j++) {
                            addend.append(line.get(j));
                            if (Objects.equals(line.get(j), ",")) {
                                addend.setLength(0);
                            }
                        }
                        for (int j = index + 1; j < indextail; j++) {
                            if (Objects.equals(line.get(j), ",")) {
                                break;
                            }
                            augend.append(line.get(j));
                        }
                        logic_assert.append("assert(").append(addend).append("+").append(augend).append(">=").append(addend).append(");\n");
                        break;
                    }
                }
                //�B�z�A�����B��O-�����p
                for (int i = 0; i < sub.size(); i++) {
                    int index = sub.get(i);
                    if (index > indexhead && index < indextail) {
                        sub.remove(i);
                        StringBuilder minuend = new StringBuilder();
                        StringBuilder subtrahend = new StringBuilder();
                        for (int j = indexhead + 1; j < index; j++) {
                            minuend.append(line.get(j));
                            if (Objects.equals(line.get(j), ",")) {
                                minuend.setLength(0);
                            }
                        }
                        for (int j = index + 1; j < indextail; j++) {
                            if (Objects.equals(line.get(j), ",")) {
                                break;
                            }
                            subtrahend.append(line.get(j));
                        }
                        logic_assert.append("assert(").append(minuend).append(" >= ").append(subtrahend).append(");\n");
                        break;
                    }
                }

            }

        }

        //�����V�M�ƦC
        List<Integer> product_and_divide_index = new ArrayList<>();
        product_and_divide_index.addAll(product);
        product_and_divide_index.addAll(divide);
        Collections.sort(product_and_divide_index);
        //�[��V�M�ƦC
        List<Integer> add_and_sub_index = new ArrayList<>();
        add_and_sub_index.addAll(addition);
        add_and_sub_index.addAll(sub);
        Collections.sort(add_and_sub_index);
        List<Integer> operator = new ArrayList<>();
        operator.addAll(add_and_sub_index);
        operator.addAll(product_and_divide_index);
        Collections.sort(operator);
        //������
        if (!product_and_divide_index.isEmpty()) {
            for (int i : product_and_divide_index) {
                int left_edge = line.indexOf("=");
                if (left_edge == -1 && line.contains("return")) {
                    left_edge = line.indexOf("return");
                } else if (left_edge == -1 && !line.contains("return")) {
                    break;
                }
                int right_edge = line.size() - 1;
                for (int j = add_and_sub_index.size() - 1; j >= 0; j--) {
                    if (add_and_sub_index.get(j) < i) {
                        left_edge = add_and_sub_index.get(j);
                        break;
                    }
                }
                int next_operator = operator.indexOf(i);
                if (next_operator + 1 < operator.size() && next_operator != -1) {
                    right_edge = operator.get(next_operator + 1);
                }
                StringBuilder left = new StringBuilder();
                StringBuilder right = new StringBuilder();
                for (int j = left_edge + 1; j < i; j++) {
                    left.append(line.get(j));
                }
                for (int j = i + 1; j < right_edge; j++) {
                    right.append(line.get(j));
                }
                if (product.contains(i)) {
                    logic_assert.append("assert(").append(left).append("*").append(right).append("/").append(left).append("==").append(right).append(");\n");
                } else {
                    logic_assert.append("assert(").append(right).append(">0").append(");\n");
                    logic_assert.append("assert(").append(left).append("==").append(right).append("*(").append(left).append("/").append(right).append(") +").append(left).append("%").append(right).append(");\n");
                }
            }
        }
        //��[��
        if (!add_and_sub_index.isEmpty()) {
            for (int i = 0; i < add_and_sub_index.size(); i++) {
                int left_edge = line.indexOf("=");
                if (left_edge == -1 && line.contains("return")) {
                    left_edge = line.indexOf("return");
                } else if (left_edge == -1 && !line.contains("return")) {
                    break;
                }
                int right_edge = line.size() - 1;
                int next_operator = add_and_sub_index.get(i);
                if (i + 1 < add_and_sub_index.size() && next_operator != -1) {
                    right_edge = add_and_sub_index.get(i + 1);
                }
                StringBuilder left = new StringBuilder();
                StringBuilder right = new StringBuilder();
                int index = add_and_sub_index.get(i);
                for (int j = left_edge + 1; j < index; j++) {
                    left.append(line.get(j));
                }
                for (int j = index + 1; j < right_edge; j++) {
                    right.append(line.get(j));
                }
                if (addition.contains(index)) {
                    logic_assert.append("assert(").append(left).append("+").append(right).append(">=").append(left).append(");\n");
                } else {
                    logic_assert.append("assert(").append(left).append(" >= ").append(right).append(");\n");
                }
            }
        }
        if (!logic_assert.isEmpty()) {
            str.insert(0, logic_assert);
        }
        return str.toString();
    }
    protected static void creat_fuzzy_testing_file(List<List<String>> lines,String path) throws IOException {
        //�O�o���ɦW��sol
        // String path = "./testing_file.sol";
        try (FileWriter fw = new FileWriter(path);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (List<String> line : lines) {
                String write_string = insert_string(line);
                if (!write_string.isEmpty()) {
                    bw.write(write_string);
                    bw.newLine();
                }
            }
            System.out.println("File write completed");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while writing to the file");
        }
    }
    protected static void creat_fuzzy_testing_file(List<List<String>> lines) throws IOException {
        //�O�o���ɦW��sol
        String path = "C:\\Users\\Six square\\Desktop\\�{���y���m��\\java\\smart_contract\\src\\main\\java\\testing_file.txt";
        try (FileWriter fw = new FileWriter(path);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (List<String> line : lines) {
                String write_string = insert_string(line);
                if (!write_string.isEmpty()) {
                    bw.write(write_string);
                    bw.newLine();
                }
            }
            System.out.println("File write completed");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while writing to the file");
        }
    }
//    protected static void minimum_visibility(List<List<String>> lines , List<contract> contracts){
//
//    }
}
