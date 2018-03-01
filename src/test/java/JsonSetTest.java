import com.zhizuqiu.JsonSet;
import com.zhizuqiu.internal.User;
import org.junit.Test;

import javax.script.ScriptException;

import static org.junit.Assert.assertEquals;

public class JsonSetTest {

    @Test
    public void setJsonTest() {

        String[] intPaths = {"store", "bicycle", "price"};
        int intValue = 123;
        String intJson = "{\"store\":{\"bicycle\":{\"color\":\"red\",\"price\":123}},\"expensive\":10}";

        String[] strPaths = {"store", "bicycle", "color"};
        String strValue = "blue";
        String strJson = "{\"store\":{\"bicycle\":{\"color\":\"blue\",\"price\":19.95}},\"expensive\":10}";

        String[] doublePaths = {"store", "bicycle", "price"};
        Double doubleValue = 21.88;
        String doubleJson = "{\"store\":{\"bicycle\":{\"color\":\"red\",\"price\":21.88}},\"expensive\":10}";


        String json = "{\"store\":{\"bicycle\":{\"color\":\"red\",\"price\":19.95}},\"expensive\":10}";
        System.out.println(json);

        try {
            assertEquals(JsonSet.setJson(intPaths, intValue, json), intJson);
            assertEquals(JsonSet.setJson(strPaths, strValue, json), strJson);
            assertEquals(JsonSet.setJson(doublePaths, doubleValue, json), doubleJson);

            short shortValue = 10;
            System.out.println(JsonSet.setJson(intPaths, shortValue, json));

            long longValue = 100000;
            System.out.println(JsonSet.setJson(intPaths, longValue, json));

            byte byteValue = 125;
            System.out.println(JsonSet.setJson(intPaths, byteValue, json));

            float floatValue = 2.21f;
            System.out.println(JsonSet.setJson(intPaths, floatValue, json));

            System.out.println(JsonSet.setJson(intPaths, true, json));

            char charValue = 'c';
            System.out.println(JsonSet.setJson(intPaths, charValue, json));

            System.out.println(JsonSet.setJson(intPaths, new User("name", 23), json));

        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }


}
