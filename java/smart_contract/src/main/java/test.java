public class test {
    public static void main(String [] args){
        StringBuilder x = new StringBuilder("aaaa");
        char y = '(';
        x.setLength(0);
        x.append(Character.toString(y));
        System.out.println(x);
    }
}
