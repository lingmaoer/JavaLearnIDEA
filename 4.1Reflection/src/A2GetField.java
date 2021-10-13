import java.lang.reflect.Field;

/**
 * @author Lingmao
 * @creat 2021-10-11  22:19
 */
//来自  https://www.liaoxuefeng.com/wiki/1252599548343744/1264803033837024
/*
对任意的一个 Object 实例，只要我们获取了它的 Class，就可以获取它的一切信息。

我们先看看如何通过 Class 实例获取字段信息。Class 类提供了以下几个方法来获取字段：

Field getField (name)：根据字段名获取某个 public 的 field（包括父类）
Field getDeclaredField (name)：根据字段名获取当前类的某个 field（不包括父类）
Field [] getFields ()：获取所有 public 的 field（包括父类）
Field [] getDeclaredFields ()：获取当前类的所有 field（不包括父类）
我们来看一下示例代码：
 */
//public class A2GetField {
//    public static void main(String[] args) throws Exception {
//        Class stdClass = Student.class;
//        // 获取public字段"score":
//        System.out.println(stdClass.getField("score"));
//        // 获取继承的public字段"name":
//        System.out.println(stdClass.getField("name"));
//        // 获取private字段"grade":
//        System.out.println(stdClass.getDeclaredField("grade"));
//    }
//}
//
//class Student extends Person {
//    public int score;
//    private int grade;
//}
//
//class Person {
//    public String name;
//}

/*
上述代码首先获取 Student 的 Class 实例，然后，分别获取 public 字段、继承的 public 字段以及 private 字段，打印出的 Field 类似：

public int Student.score
public java.lang.String Person.name
private int Student.grade
一个 Field 对象包含了一个字段的所有信息：

getName()：返回字段名称，例如，"name"；
getType()：返回字段类型，也是一个 Class 实例，例如，String.class；
getModifiers()：返回字段的修饰符，它是一个 int，不同的 bit 表示不同的含义。
以 String 类的 value 字段为例，它的定义是：

public final class String {
    private final byte[] value;
}
我们用反射获取该字段的信息，代码如下：

Field f = String.class.getDeclaredField("value");
f.getName(); // "value"
f.getType(); // class [B 表示byte[]类型
int m = f.getModifiers();
Modifier.isFinal(m); // true
Modifier.isPublic(m); // false
Modifier.isProtected(m); // false
Modifier.isPrivate(m); // true
Modifier.isStatic(m); // false
获取字段值
利用反射拿到字段的一个 Field 实例只是第一步，我们还可以拿到一个实例对应的该字段的值。

例如，对于一个 Person 实例，我们可以先拿到 name 字段对应的 Field，再获取这个实例的 name 字段的值：
 */

//public class A2GetField {
//
//    public static void main(String[] args) throws Exception {
//        Object p = new Person("Xiao Ming");
//        Class c = p.getClass();
//        Field f = c.getDeclaredField("name");
//        Object value = f.get(p);
//        System.out.println(value); // "Xiao Ming"
//    }
//}
//
//class Person {
//    private String name;
//
//    public Person(String name) {
//        this.name = name;
//    }
//}

/*
上述代码先获取 Class 实例，再获取 Field 实例，然后，用 Field.get(Object) 获取指定实例的指定字段的值。

运行代码，如果不出意外，会得到一个 IllegalAccessException，这是因为 name 被定义为一个 private 字段，正常情况下，Main 类无法访问 Person 类的 private 字段。要修复错误，可以将 private 改为 public，或者，在调用 Object value = f.get(p); 前，先写一句：

f.setAccessible(true);
调用 Field.setAccessible(true) 的意思是，别管这个字段是不是 public，一律允许访问。

可以试着加上上述语句，再运行代码，就可以打印出 private 字段的值。

有童鞋会问：如果使用反射可以获取 private 字段的值，那么类的封装还有什么意义？

答案是正常情况下，我们总是通过 p.name 来访问 Person 的 name 字段，编译器会根据 public、protected 和 private 决定是否允许访问字段，这样就达到了数据封装的目的。

而反射是一种非常规的用法，使用反射，首先代码非常繁琐，其次，它更多地是给工具或者底层框架来使用，目的是在不知道目标实例任何信息的情况下，获取特定字段的值。

此外，setAccessible(true) 可能会失败。如果 JVM 运行期存在 SecurityManager，那么它会根据规则进行检查，有可能阻止 setAccessible(true)。例如，某个 SecurityManager 可能不允许对 java 和 javax 开头的 package 的类调用 setAccessible(true)，这样可以保证 JVM 核心库的安全。

设置字段值
通过 Field 实例既然可以获取到指定实例的字段值，自然也可以设置字段的值。

设置字段值是通过 Field.set(Object, Object) 实现的，其中第一个 Object 参数是指定的实例，第二个 Object 参数是待修改的值。示例代码如下：
 */
//public class A2GetField {
//    public static void main(String[] args) throws Exception {
//        Person p = new Person("Xiao Ming");
//        System.out.println(p.getName()); // "Xiao Ming"
//        Class c = p.getClass();
//        Field f = c.getDeclaredField("name");
//        f.setAccessible(true);
//        f.set(p, "Xiao Hong");
//        System.out.println(p.getName()); // "Xiao Hong"
//    }
//}
//
//class Person {
//    private String name;
//
//    public Person(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//}

//运行上述代码，打印的 name 字段从 Xiao Ming 变成了 Xiao Hong，说明通过反射可以直接修改字段的值。
//
//同样的，修改非 public 字段，需要首先调用 setAccessible(true)。

//练习
//利用反射给字段赋值：

public class A2GetField {
    public static void main(String[] args) {
        String name = "Xiao Ming";
        int age = 20;
        Person p = new Person();
        // TODO: 利用反射给name和age字段赋值:
        Class c = p.getClass();
        try {
            Field fieldName = c.getDeclaredField("name");
            Field fieldAge = c.getDeclaredField("age");
            fieldName.setAccessible(true);
            fieldName.set(p, "Xiao Hong");
            fieldAge.setAccessible(true);
            fieldAge.set(p,38);
        }catch(NoSuchFieldException|IllegalAccessException e){
            e.printStackTrace();
        }
        System.out.println(p.getName()); // "Xiao Ming"
        System.out.println(p.getAge()); // 20
    }
}
class Person {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

/*
小结
Java 的反射 API 提供的 Field 类封装了字段的所有信息：

通过 Class 实例的方法可以获取 Field 实例：getField()，getFields()，getDeclaredField()，getDeclaredFields()；

通过 Field 实例可以获取字段信息：getName()，getType()，getModifiers()；

通过 Field 实例可以读取或设置某个对象的字段，如果存在访问限制，要首先调用 setAccessible(true) 来访问非 public 字段。

通过反射读写字段是一种非常规方法，它会破坏对象的封装。
 */

