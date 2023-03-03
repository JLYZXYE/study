package synchronizedTest.demo9lockfat;

public class Demo01 {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 100; i++) {
            sb.append("aa");
        }
        System.out.println(sb.toString());
    }
}