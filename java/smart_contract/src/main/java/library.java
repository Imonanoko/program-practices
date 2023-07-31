import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//"C:\\Users\\Six square\\Desktop\\程式語言練習\\java\\smart_contract\\src\\main\\java\\contract.txt"
public class library {
    protected static List<List<String>> file_analyses (String Path) throws FileNotFoundException {
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
                    //如果是;、(、)等字元會被區分開來，亦即;、(、{是被獨立加進word裡面的
                    case ';', '(', ')', '{', '}' -> {
                        word_List.add(word.toString());
                        word.setLength(0);
                        word_List.add(Character.toString(chr));
                    }
                    //因為=、<、>可能後面接一樣=、<、>的字元所以需要檢查後面是否有這些字元，沒有就加進word裡面、有就把兩個字元一起加進去word
                    case '=', '<', '>' -> {
                        if (!word.toString().equals("")) {
                            word_List.add(word.toString());
                            word.setLength(0);
                        }
                        char tmp = str.charAt(i + 1);
                        if (tmp == '=' || tmp == '>' || tmp == '<') {
                            word.append(chr);
                            word.append(tmp);
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
                    }
                    //非上述字串就會當成跟前面的字元是一組的
                    default -> {
                        word.append(chr);
                    }
                }
            }
            lines.add(new ArrayList<>(word_List));
        }
        return lines;
    }
    //輸出格是每一列表一個問題[第幾行的if or require,甚麼變數,可能出現什麼值造成錯誤];
    protected static void logic_test(List<List<String>> lines){

    }
}
