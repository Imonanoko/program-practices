import java.io.*;

public class test {
    public static void main(String [] args) throws IOException {
        String path = "C:\\Users\\Six square\\Desktop\\�{���y���m��\\java\\smart_contract\\src\\main\\java\\contract.txt";
        File doc = new File(path);
        FileReader fileReader = new FileReader(doc);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

// ���w???UTF-8
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
    }
}
