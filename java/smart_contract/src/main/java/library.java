import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

//"C:\\Users\\Six square\\Desktop\\�{���y���m��\\java\\smart_contract\\src\\main\\java\\contract.txt"
public class library {
    protected static List<List<String>> file_analyses(String Path) throws FileNotFoundException {
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
                    //�N�r���W��J�@�Ӯ�l�̡A�]���p�G�J��S��r���|�Q����
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
                    //�p�G�O;�B(�B)���r���|�Q�Ϥ��}�ӡA��Y;�B(�B{�O�Q�W�ߥ[�iword�̭���
                    case ';', '(', ')', '{', '}', '.', '\'', ',' -> {
                        if (!word.toString().equals("")) {
                            word_List.add(word.toString());
                            word.setLength(0);
                        }
                        word_List.add(Character.toString(chr));
                    }
                    //�]��=�B<�B>�i��᭱���@��=�B<�B>���r���ҥH�ݭn�ˬd�᭱�O�_���o�Ǧr���A�S���N�[�iword�̭��B���N���Ӧr���@�_�[�i�hword
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
                    //�J��B�⦡�ݭn�N�Ÿ����}���ˬd�᭱�O�_���Ÿ�
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
                    //�D�W�z�r��N�|����e�����r���O�@�ժ�
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

    //��X��O�C�@�C��@�Ӱ��D[�ĴX�檺if or require,�ƻ��ܼ�,�i��X�{����ȳy�����~];
    //��J:
    //lines:�ɮפ��R�᪺���
    protected static List<List<List<String>>> variable_analyses(List<List<String>> lines) {
        List<List<List<String>>> var = new ArrayList<>();
        //�s�������ܼƪ��W��
        List<List<String>> contract_global_var = new ArrayList<>();
        //���P�X�������P�������ܼ�[�X���W��,�ܼƦW��]
        List<String> global_var = new ArrayList<>();
        //�s���ϰ��ܡA�C��row�O[��ƦW��,�̭����ܼƦW��]
        List<List<String>> function_local_var = new ArrayList<>();
        //function_local_var ��row�Ȧs
        List<String> local_var = new ArrayList<>();
        //�P�_�j�A�����Ȧs��[contract,function,if_elif,for,while]
        List<Integer> flag = new ArrayList<>();
        String word;
        // i+1�����
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).size(); j++) {
                word = lines.get(i).get(j);
                switch (word) {
                    case "contract" ->{
                        flag.add(0);
                        //�e�����X���N�[�J��contract_global_var
                        if(global_var.size()!=0){
                            contract_global_var.add(new ArrayList<>(global_var));
                            global_var.clear();
                        }
                        //�[�J�W��
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
                        //�X��+1
                        flag.set(index, flag.get(index) + 1);
                    }
                    case "}" -> {
                        int index = flag.size()-1;
                        //�X��-1
                        flag.set(index,flag.get(index) - 1);
                        if(flag.get(index)==0){
                            flag.remove(index);
                        }
                    }
                    case "uint","address","uint256" ->{
                        j++;
                        word = lines.get(i).get(j);
                        switch (word){
                            //�ݷs�W�A�i��J��ŧi���O�O�}�C������
                            case ")","("->{

                            }
                            default -> {
                                //����@�N������ܼ�
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
