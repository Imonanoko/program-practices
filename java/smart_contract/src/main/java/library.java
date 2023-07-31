import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//"C:\\Users\\Six square\\Desktop\\�{���y���m��\\java\\smart_contract\\src\\main\\java\\contract.txt"
public class library {
    protected static List<List<String>> file_analyses (String Path) throws FileNotFoundException {
        List<List<String>> lines = new ArrayList<>();
        File doc = new File(Path);
        Scanner obj = new Scanner(doc);
        //�H�欰���Ū�����
        while (obj.hasNextLine()) {
            //�s�C�@�檺�C�ӵ�
            List<String> word_List = new ArrayList<>();
            //�N�C�@�檺�C�Ӧr�Ȧs�A�B�z���a��word_List
            StringBuilder word = new StringBuilder();
            String str = obj.nextLine();
            //�J����ѲŸ����ܫ᭱�N���z��
            out:
            //�̧�Ū�C��character
            for (int i = 0; i < str.length(); i++) {
                char chr = str.charAt(i);
                switch (chr) {
                    //�p�G�O�Ů�N��e���o�Ǧr�O�@�ժ��A�ҥH�N�e�@�զۦs��List�̭�
                    case ' ' -> {
                        //�S���F�褣�[�J�A���M�ܶäS���O����
                        if (!word.toString().equals("")) {
                            word_List.add(word.toString());
                            word.setLength(0);
                        }
                    }
                    //�p�G�O;�B(�B)���r���|�Q�Ϥ��}�ӡA��Y;�B(�B{�O�Q�W�ߥ[�iword�̭���
                    case ';', '(', ')', '{', '}' -> {
                        word_List.add(word.toString());
                        word.setLength(0);
                        word_List.add(Character.toString(chr));
                    }
                    //�]��=�B<�B>�i��᭱���@��=�B<�B>���r���ҥH�ݭn�ˬd�᭱�O�_���o�Ǧr���A�S���N�[�iword�̭��B���N���Ӧr���@�_�[�i�hword
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
                    //�D�W�z�r��N�|����e�����r���O�@�ժ�
                    default -> {
                        word.append(chr);
                    }
                }
            }
            lines.add(new ArrayList<>(word_List));
        }
        return lines;
    }
    //��X��O�C�@�C��@�Ӱ��D[�ĴX�檺if or require,�ƻ��ܼ�,�i��X�{����ȳy�����~];
    protected static void logic_test(List<List<String>> lines){

    }
}
