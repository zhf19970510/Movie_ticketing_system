/**
 * Created on 2019/10/27 0027.
 */
public class StringFormatTest {
    public static void main(String[] args) {
        String s = String.format("%-10d%-30s", 10, "中华人民共和国");
        System.out.println(s);
    }
}
