/**
 * @author Lingmao
 * @creat 2021-10-09  20:44
 */

//来自  https://www.liaoxuefeng.com/wiki/1252599548343744/1337645544243233

/*
在所有的 RuntimeException 异常中，Java 程序员最熟悉的恐怕就是 NullPointerException 了。

NullPointerException 即空指针异常，俗称 NPE。如果一个对象为 null，调用其方法或访问其字段就会产生 NullPointerException，这个异常通常是由 JVM 抛出的，例如：
 */

//public class A5NullPointerException {
//    public static void main(String[] args) {
//        String s = null;
//        System.out.println(s.toLowerCase());
//    }
//}

/*
指针这个概念实际上源自 C 语言，Java 语言中并无指针。我们定义的变量实际上是引用，Null Pointer 更确切地说是 Null Reference，不过两者区别不大。

处理 NullPointerException
如果遇到 NullPointerException，我们应该如何处理？首先，必须明确，NullPointerException 是一种代码逻辑错误，遇到 NullPointerException，遵循原则是早暴露，早修复，严禁使用 catch 来隐藏这种编码错误：

// 错误示例: 捕获NullPointerException
try {
    transferMoney(from, to, amount);
} catch (NullPointerException e) {
}
好的编码习惯可以极大地降低 NullPointerException 的产生，例如：

成员变量在定义时初始化：

public class Person {
    private String name = "";
}
使用空字符串 "" 而不是默认的 null 可避免很多 NullPointerException，编写业务逻辑时，用空字符串 "" 表示未填写比 null 安全得多。

返回空字符串 ""、空数组而不是 null：

public String[] readLinesFromFile(String file) {
    if (getFileSize(file) == 0) {
        // 返回空数组而不是null:
        return new String[0];
    }
    ...
}
这样可以使得调用方无需检查结果是否为 null。

如果调用方一定要根据 null 判断，比如返回 null 表示文件不存在，那么考虑返回 Optional<T>：

public Optional<String> readFromFile(String file) {
    if (!fileExist(file)) {
        return Optional.empty();
    }
    ...
}
这样调用方必须通过 Optional.isPresent() 判断是否有结果。

定位 NullPointerException
如果产生了 NullPointerException，例如，调用 a.b.c.x() 时产生了 NullPointerException，原因可能是：

a 是 null；
a.b 是 null；
a.b.c 是 null；
确定到底是哪个对象是 null 以前只能打印这样的日志：

System.out.println(a);
System.out.println(a.b);
System.out.println(a.b.c);
从 Java 14 开始，如果产生了 NullPointerException，JVM 可以给出详细的信息告诉我们 null 对象到底是谁。我们来看例子：
 */

public class A5NullPointerException {
    public static void main(String[] args) {
        Person p = new Person();
        System.out.println(p.address.city.toLowerCase());
    }
}

class Person {
    String[] name = new String[2];
    Address address = new Address();
}

class Address {
    String city;
    String street;
    String zipcode;
}

/*
可以在 NullPointerException 的详细信息中看到类似... because "<local1>.address.city" is null，意思是 city 字段为 null，这样我们就能快速定位问题所在。

这种增强的 NullPointerException 详细信息是 Java 14 新增的功能，但默认是关闭的，我们可以给 JVM 添加一个 -XX:+ShowCodeDetailsInExceptionMessages 参数启用它：

java -XX:+ShowCodeDetailsInExceptionMessages Main.java
小结
NullPointerException 是 Java 代码常见的逻辑错误，应当早暴露，早修复；

可以启用 Java 14 的增强异常信息来查看 NullPointerException 的详细错误信息。
 */

