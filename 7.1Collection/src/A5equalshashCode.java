import java.util.HashMap;
import java.util.Map;

/**
 * @author Lingmao
 * @creat 2021-10-20  9:55
 */
//
/*
我们知道 Map 是一种键 - 值（key-value）映射表，可以通过 key 快速查找对应的 value。

以 HashMap 为例，观察下面的代码：

Map<String, Person> map = new HashMap<>();
map.put("a", new Person("Xiao Ming"));
map.put("b", new Person("Xiao Hong"));
map.put("c", new Person("Xiao Jun"));

map.get("a"); // Person("Xiao Ming")
map.get("x"); // null
HashMap 之所以能根据 key 直接拿到 value，原因是它内部通过空间换时间的方法，用一个大数组存储所有 value，并根据 key 直接计算出 value 应该存储在哪个索引：

  ┌───┐
0 │   │
  ├───┤
1 │ ●─┼───> Person("Xiao Ming")
  ├───┤
2 │   │
  ├───┤
3 │   │
  ├───┤
4 │   │
  ├───┤
5 │ ●─┼───> Person("Xiao Hong")
  ├───┤
6 │ ●─┼───> Person("Xiao Jun")
  ├───┤
7 │   │
  └───┘
如果 key 的值为 "a"，计算得到的索引总是 1，因此返回 value 为 Person("Xiao Ming")，如果 key 的值为 "b"，计算得到的索引总是 5，因此返回 value 为 Person("Xiao Hong")，这样，就不必遍历整个数组，即可直接读取 key 对应的 value。

当我们使用 key 存取 value 的时候，就会引出一个问题：

我们放入 Map 的 key 是字符串 "a"，但是，当我们获取 Map 的 value 时，传入的变量不一定就是放入的那个 key 对象。

换句话讲，两个 key 应该是内容相同，但不一定是同一个对象。测试代码如下：
 */

//public class A5equalshashCode {
//    public static void main(String[] args) {
//        String key1 = "a";
//        Map<String, Integer> map = new HashMap<>();
//        map.put(key1, 123);
//
//        String key2 = new String("a");
//        map.get(key2); // 123
//
//        System.out.println(key1 == key2); // false
//        System.out.println(key1.equals(key2)); // true
//    }
//}
