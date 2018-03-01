import com.zhizuqiu.internal.User;
import com.zhizuqiu.internal.Utils;
import org.junit.Test;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class InternalTest {
    /*
    @Test
    public void test() {

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        try {
            Bindings bindings = engine.createBindings();
            Map<String, String> params = new HashMap<>();
            params.put("name", "rain");
            params.put("age", "28");

            bindings.put("paramsStr", new Gson().toJson(params));
            engine.eval("var params=eval('('+paramsStr+')')", bindings);
            engine.eval("var result=params.name;", bindings);
            System.out.println(bindings.get("result"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */

    @Test
    public void UserTest1() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        try {
            Bindings bindings = engine.createBindings();
            engine.eval("var result= new com.zhizuqiu.internal.User(\"name\",\"23\");" +
                    "var r = result.name;");
            System.out.println(bindings.get("r"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void UserTest2() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        try {
            Bindings bindings = engine.createBindings();
            bindings.put("user", new User("name", 23));
            engine.eval("var result= user;" +
                    "var r = result.name;", bindings);
            System.out.println(bindings.get("r"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void objectToMapTest() {
        System.out.println(Utils.objectToMap(new User("name", 23)));
    }

}
