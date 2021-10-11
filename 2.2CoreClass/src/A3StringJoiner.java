import java.util.StringJoiner;

/**
 * @author Lingmao
 * @creat 2021-10-08  11:20
 */

//来自  https://www.liaoxuefeng.com/wiki/1252599548343744/1271993169413952

//    要高效拼接字符串，应该使用 StringBuilder。
//
//很多时候，我们拼接的字符串像这样：


//public class A3StringJoiner {
//    public static void main(String[] args) {
//        String[] names = {"Bob", "Alice", "Grace"};
//        var sb = new StringBuilder();
//        sb.append("Hello ");
//        for (String name : names) {
//            sb.append(name).append(", ");
//        }
//        // 注意去掉最后的", ":
//        sb.delete(sb.length() - 2, sb.length());
//        sb.append("!");
//        System.out.println(sb.toString());
//    }
//}

//类似用分隔符拼接数组的需求很常见，所以 Java 标准库还提供了一个 StringJoiner 来干这个事：
//public class A3StringJoiner {
//    public static void main(String[] args) {
//        String[] names = {"Bob", "Alice", "Grace"};
//        var sj = new StringJoiner(", ");
//        for (String name : names) {
//            sj.add(name);
//        }
//        System.out.println(sj.toString());
//    }
//}

//慢着！用 StringJoiner 的结果少了前面的 "Hello " 和结尾的 "!"！遇到这种情况，需要给 StringJoiner 指定 “开头” 和 “结尾”：
//public class A3StringJoiner {
//    public static void main(String[] args) {
//        String[] names = {"Bob", "Alice", "Grace"};
//        var sj = new StringJoiner(", ", "Hello ", "!");
//        for (String name : names) {
//            sj.add(name);
//        }
//        System.out.println(sj.toString());
//    }
//}

//String.join()
//String 还提供了一个静态方法 join()，这个方法在内部使用了 StringJoiner 来拼接字符串，在不需要指定 “开头” 和 “结尾” 的时候，用 String.join() 更方便：
//
//String[] names = {"Bob", "Alice", "Grace"};
//var s = String.join(", ", names);


//练习
//请使用 StringJoiner 构造一个 SELECT 语句：

public class A3StringJoiner {
    public static void main(String[] args) {
        String[] fields = { "name", "position", "salary" };
        String table = "employee";
        String select = buildSelectSql(table, fields);
        System.out.println(select);
        System.out.println("SELECT name, position, salary FROM employee".equals(select) ? "测试成功" : "测试失败");
    }
    static String buildSelectSql(String table, String[] fields) {
        // TODO:
        var connect = new StringJoiner(", ","SELECT ", String.format(" FROM %s", table));
        for(String str:fields){
            connect.add(str);
        }
        return connect.toString();
    }

}