# JsonSet

修改json中指定key所对应的value。

背景
---
案例：有这样一个程序，它的配置文件以json格式存放，现在用一个填有默认值的json串做模板，运行时通过获取程序参数或读取环境变量等方式动态修改模板，产生真正可用的json配置。

例如：

```json
{
  "store": {
    "bicycle": {
      "color": "red",
      "price": 19.95
    }
  },
  "expensive": 10
}
```

传入的参数为：

```
$.store.bicycle.color=bule
```

产生如下结果：

```json
{
  "store": {
    "bicycle": {
      "color": "blue",
      "price": 19.95
    }
  },
  "expensive": 10
}
```

使用
---

以背景中的例子书写代码：

```
String[] paths = {"store", "bicycle", "color"};
String value = "blue";
String json = "{\"store\":{\"bicycle\":{\"color\":\"red\",\"price\":19.95}},\"expensive\":10}";

String result = JsonSet.setJson(paths, value, json);
```

其中：

- path: 要修改值的key的路径
- value: 要修改的值
- json: 要修改的json模板
- result: 最终json结果

当前版本value支持的类型：
- 基本数据类型及其封装类
- 引用类型(需要引入Gson)

更多使用示例，见 [JsonSetTest.java](src/test/java/JsonSetTest.java)

待支持的功能
---

- 支持json数组的增删改
- 支持更多的查询语法