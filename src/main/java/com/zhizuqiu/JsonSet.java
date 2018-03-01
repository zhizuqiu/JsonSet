package com.zhizuqiu;

import com.zhizuqiu.internal.Utils;

import javax.script.*;

public class JsonSet {

    public static String setJson(String[] paths, Object value, String json) throws ScriptException, NoSuchMethodException {
        Utils.notNull(paths, "paths can not be %s", "null");
        Utils.notNull(value, "value can not be %s", "null");
        Utils.notNull(json, "json can not be %s", "null");
        if (Utils.isNormalDataType(value)) {
            return setJsonWithObj1(paths, value, json);
        } else if (value instanceof Character) {
            return setJsonWithObj1(paths, "" + value, json);
        } else {
            return setJsonWithObj2(paths, value, json);
        }
    }

    private static String setJsonWithObj1(String[] paths, Object value, String json) throws ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        if (!(engine instanceof Invocable)) {
            throw new ScriptException("Invoking methods is not supported.");
        }
        String jsCodeStart = "function test(paths, value, json) {" +
                "var jsonObj = JSON.parse(json);" +
                "jsonObj";
        StringBuilder jsCode = new StringBuilder();
        for (int i = 0; i < paths.length; i++) {
            jsCode.append(new StringBuffer("[paths[" + i + "]]"));
        }
        String jsCodeEnd = "= value; return JSON.stringify(jsonObj);}";
        engine.eval(jsCodeStart + jsCode.toString() + jsCodeEnd);
        Invocable invocable = (Invocable) engine;
        return (String) invocable.invokeFunction("test", paths, value, json);
    }

    private static String setJsonWithObj2(String[] paths, Object value, String json) throws ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        Bindings bindings = engine.createBindings();
        bindings.put("value", value);
        bindings.put("paths", paths);
        bindings.put("json", json);
        String jsCodeStart = "var jsonObj = JSON.parse(json);" +
                "var param = JSON.parse(new com.google.gson.Gson().toJson(value));" +
                "jsonObj";
        StringBuilder jsCode = new StringBuilder();
        for (int i = 0; i < paths.length; i++) {
            jsCode.append(new StringBuffer("[paths[" + i + "]]"));
        }
        String jsCodeEnd = "= param; var r = JSON.stringify(jsonObj);";
        engine.eval(jsCodeStart + jsCode.toString() + jsCodeEnd, bindings);
        return bindings.get("r").toString();
    }

}
