/**
 * @author Lingmao
 * @creat 2021-10-06  17:40
 */

//来自 https://www.liaoxuefeng.com/wiki/1252599548343744/1260464690677856

/*
在一个 class 中定义的字段，我们称之为实例字段。实例字段的特点是，每个实例都有独立的字段，各个实例的同名字段互不影响。

还有一种字段，是用 static 修饰的字段，称为静态字段：static field。

实例字段在每个实例中都有自己的一个独立 “空间”，但是静态字段只有一个共享 “空间”，所有实例都会共享该字段。举个例子：

class Person {
    public String name;
    public int age;
    // 定义静态字段number:
    public static int number;
}
我们来看看下面的代码：
 */
//public class A8Static {
//    public static void main(String[] args) {
//        Person8 ming = new Person8("Xiao Ming", 12);
//        Person8 hong = new Person8("Xiao Hong", 15);
//        ming.number = 88;
//        System.out.println(hong.number);
//        hong.number = 99;
//        System.out.println(ming.number);
//    }
//}
//
//class Person8 {
//    public String name;
//    public int age;
//
//    public static int number;
//
//    public Person8(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//}

/*
对于静态字段，无论修改哪个实例的静态字段，效果都是一样的：所有实例的静态字段都被修改了，原因是静态字段并不属于实例：

        ┌──────────────────┐
ming ──>│Person instance   │
        ├──────────────────┤
        │name = "Xiao Ming"│
        │age = 12          │
        │number ───────────┼──┐    ┌─────────────┐
        └──────────────────┘  │    │Person class │
                              │    ├─────────────┤
                              ├───>│number = 99  │
        ┌──────────────────┐  │    └─────────────┘
hong ──>│Person instance   │  │
        ├──────────────────┤  │
        │name = "Xiao Hong"│  │
        │age = 15          │  │
        │number ───────────┼──┘
        └──────────────────┘
虽然实例可以访问静态字段，但是它们指向的其实都是 Person class 的静态字段。所以，所有实例共享一个静态字段。

因此，不推荐用实例变量.静态字段去访问静态字段，因为在 Java 程序中，实例对象并没有静态字段。在代码中，实例对象能访问静态字段只是因为编译器可以根据实例类型自动转换为类名.静态字段来访问静态对象。

推荐用类名来访问静态字段。可以把静态字段理解为描述 class 本身的字段（非实例字段）。对于上面的代码，更好的写法是：

Person.number = 99;
System.out.println(Person.number);
静态方法
有静态字段，就有静态方法。用 static 修饰的方法称为静态方法。

调用实例方法必须通过一个实例变量，而调用静态方法则不需要实例变量，通过类名就可以调用。静态方法类似其它编程语言的函数。例如：
     */

//public class A8Static {
//    public static void main(String[] args) {
//        Person8.setNumber(99);
//        System.out.println(Person8.number);
//    }
//}
//
//class Person8 {
//    public static int number;
//
//    public static void setNumber(int value) {
//        number = value;
//    }
//}

/*
因为静态方法属于 class 而不属于实例，因此，静态方法内部，无法访问 this 变量，也无法访问实例字段，它只能访问静态字段。

通过实例变量也可以调用静态方法，但这只是编译器自动帮我们把实例改写成类名而已。

通常情况下，通过实例变量访问静态字段和静态方法，会得到一个编译警告。

静态方法经常用于工具类。例如：

Arrays.sort()

Math.random()

静态方法也经常用于辅助方法。注意到 Java 程序的入口 main() 也是静态方法。

接口的静态字段
因为 interface 是一个纯抽象类，所以它不能定义实例字段。但是，interface 是可以有静态字段的，并且静态字段必须为 final 类型：

public interface Person {
    public static final int MALE = 1;
    public static final int FEMALE = 2;
}
实际上，因为 interface 的字段只能是 public static final 类型，所以我们可以把这些修饰符都去掉，上述代码可以简写为：

public interface Person {
    // 编译器会自动加上public statc final:
    int MALE = 1;
    int FEMALE = 2;
}
编译器会自动把该字段变为 public static final 类型。
 */
public class A8Static {

    public static void main(String[] args) {
        // TODO: 给Person类增加一个静态字段count和静态方法getCount，统计实例的个数
        Person8 p1 = new Person8("小明");
        System.out.println(Person8.getCount()); // 1
        Person8 p2 = new Person8("小红");
        System.out.println(Person8.getCount()); // 2
        Person8 p3 = new Person8("小军");
        System.out.println(Person8.getCount()); // 3
    }
}

class Person8 {
    // TODO
    static int count=0;
    String name;
    public Person8(String name) {
        count++;
        this.name = name;
    }

    public static int getCount(){
        return count;
    }
}
