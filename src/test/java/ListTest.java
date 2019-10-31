import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/10/28 0028.
 */
public class ListTest {
    public static void main(String[] args) {
        List<String> cityList = new ArrayList<>();
        cityList.add(null);
        cityList.add(null);
        System.out.println(cityList);
        System.out.println(cityList.size());

    }
}
