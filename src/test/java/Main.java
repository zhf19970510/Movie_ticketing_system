import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 2019/10/29 0029.
 */
public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student);
        Date date = new Date(1527769494340L);
        String result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println(result1);
    }
}
