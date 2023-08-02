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
                    case ';', '(', ')', '{', '}', '.', '\'', ',' -> {
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
                    case '+', '-', '%' -> {
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

    //輸出格是每一列表一個問題[第幾行的if or require,甚麼變數,可能出現什麼值造成錯誤];
    //輸入:
    //lines:檔案分析後的資料
    protected static List<List<List<String>>> variable_analyses(List<List<String>> lines) {
        List<List<List<String>>> var = new ArrayList<>();
        //存取全域變數的名稱
        List<List<String>> contract_global_var = new ArrayList<>();
        //不同合約有不同的全域變數[合約名稱,變數名稱]
        List<String> global_var = new ArrayList<>();
        //存取區域變，每個row是[函數名稱,裡面的變數名稱]
        List<List<String>> function_local_var = new ArrayList<>();
        //function_local_var 的row暫存
        List<String> local_var = new ArrayList<>();
        //判斷大括號的暫存器[contract,function,if_elif,for,while]
        List<Integer> flag = new ArrayList<>();
        String word;
        // i+1為行數
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).size(); j++) {
                word = lines.get(i).get(j);
                switch (word) {
                    case "contract" ->{
                        flag.add(0);
                        //前面有合約就加入到contract_global_var
                        if(global_var.size()!=0){
                            contract_global_var.add(new ArrayList<>(global_var));
                            global_var.clear();
                        }
                        //加入名稱
                        j++;
                        global_var.add(lines.get(i).get(j));
                    }
                    case "function" ->{
                        flag.add(0);
                        if(local_var.size()!=0){
                            function_local_var.add(new ArrayList<>(local_var));
                            local_var.clear();
                        }
                        j++;
                        local_var.add(lines.get(i).get(j));
                    }
                    case  "if" -> {
                        flag.add(0);
                    }
                    case "else" -> {
                        flag.add(0);
                        if (lines.get(i).get(j + 1).equals("if"))
                            j++;
                    }
                    case "{" -> {
                        int index = flag.size()-1;
                        //旗標+1
                        flag.set(index, flag.get(index) + 1);
                    }
                    case "}" -> {
                        int index = flag.size()-1;
                        //旗標-1
                        flag.set(index,flag.get(index) - 1);
                        if(flag.get(index)==0){
                            flag.remove(index);
                        }
                    }
                    case "uint","address","uint256" ->{
                        j++;
                        word = lines.get(i).get(j);
                        switch (word){
                            //待新增，可能遇到宣告型別是陣列之類的
                            case ")","("->{

                            }
                            default -> {
                                //等於一代表全域變數
                                if(flag.size()==1){

                                    switch (word){
                                        case "public","private" ->{
                                            j++;
                                            global_var.add(lines.get(i).get(j));
                                        }
                                        default -> {
                                            global_var.add(lines.get(i).get(j));
                                        }
                                    }
                                }
                                else{
                                    local_var.add(word);
                                }
                            }
                        }

                    }
                }
//                switch (word){
//                    case "if" ->{
//                        System.out.println("if at:"+(i+1)+":"+j);
//                    }
//                    case "require" ->{
//                        System.out.println("require at:"+(i+1)+":"+j);
//                    }
//                }
            }
        }
        contract_global_var.add(new ArrayList<>(global_var));
        function_local_var.add(new ArrayList<>(local_var));
        var.add(new ArrayList<>(contract_global_var));
        var.add(new ArrayList<>(function_local_var));
        return var;
    }
}
