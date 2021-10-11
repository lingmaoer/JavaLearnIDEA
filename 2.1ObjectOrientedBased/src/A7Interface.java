/**
 * @author Lingmao
 * @creat 2021-10-06  16:16
 */

//来自  https://www.liaoxuefeng.com/wiki/1252599548343744/1260456790454816

/*
在抽象类中，抽象方法本质上是定义接口规范：即规定高层类的接口，从而保证所有子类都有相同的接口实现，这样，多态就能发挥出威力。

如果一个抽象类没有字段，所有方法全部都是抽象方法：

abstract class Person {
    public abstract void run();
    public abstract String getName();
}
就可以把该抽象类改写为接口：interface。

在 Java 中，使用 interface 可以声明一个接口：

interface Person {
    void run();
    String getName();
}
所谓 interface，就是比抽象类还要抽象的纯抽象接口，因为它连字段都不能有。因为接口定义的所有方法默认都是 public abstract 的，所以这两个修饰符不需要写出来（写不写效果都一样）。

当一个具体的 class 去实现一个 interface 时，需要使用 implements 关键字。举个例子：

class Student implements Person {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(this.name + " run");
    }

    @Override
    public String getName() {
        return this.name;
    }
}
我们知道，在 Java 中，一个类只能继承自另一个类，不能从多个类继承。但是，一个类可以实现多个 interface，例如：

class Student implements Person, Hello { // 实现了两个interface
    ...
}
术语
注意区分术语：

Java 的接口特指 interface 的定义，表示一个接口类型和一组方法签名，而编程接口泛指接口规范，如方法签名，数据格式，网络协议等。

抽象类和接口的对比如下：

abstract class	interface
继承	只能 extends 一个 class	可以 implements 多个 interface
字段	可以定义实例字段	不能定义实例字段
抽象方法	可以定义抽象方法	可以定义抽象方法
非抽象方法	可以定义非抽象方法	可以定义 default 方法
接口继承
一个 interface 可以继承自另一个 interface。interface 继承自 interface 使用 extends，它相当于扩展了接口的方法。例如：

interface Hello {
    void hello();
}

interface Person extends Hello {
    void run();
    String getName();
}
此时，Person 接口继承自 Hello 接口，因此，Person 接口现在实际上有 3 个抽象方法签名，其中一个来自继承的 Hello 接口。

继承关系
合理设计 interface 和 abstract class 的继承关系，可以充分复用代码。一般来说，公共逻辑适合放在 abstract class 中，具体逻辑放到各个子类，而接口层次代表抽象程度。可以参考 Java 的集合类定义的一组接口、抽象类以及具体子类的继承关系：

┌───────────────┐
│   Iterable    │
└───────────────┘
        ▲                ┌───────────────────┐
        │                │      Object       │
┌───────────────┐        └───────────────────┘
│  Collection   │                  ▲
└───────────────┘                  │
        ▲     ▲          ┌───────────────────┐
        │     └──────────│AbstractCollection │
┌───────────────┐        └───────────────────┘
│     List      │                  ▲
└───────────────┘                  │
              ▲          ┌───────────────────┐
              └──────────│   AbstractList    │
                         └───────────────────┘
                                ▲     ▲
                                │     │
                                │     │
                     ┌────────────┐ ┌────────────┐
                     │ ArrayList  │ │ LinkedList │
                     └────────────┘ └────────────┘
在使用的时候，实例化的对象永远只能是某个具体的子类，但总是通过接口去引用它，因为接口比抽象类更抽象：

List list = new ArrayList(); // 用List接口引用具体子类的实例
Collection coll = list; // 向上转型为Collection接口
Iterable it = coll; // 向上转型为Iterable接口
default 方法
在接口中，可以定义 default 方法。例如，把 Person 接口的 run() 方法改为 default 方法：
 */
//public class A7Interface {
//    public static void main(String[] args) {
//        Person7 p = new Student7("Xiao Ming");
//        p.run();
//    }
//}
//
//interface Person7 {
//    String getName();
//    default void run() {
//        System.out.println(getName() + " run");
//    }
//}
//
//class Student7 implements Person7 {
//    private String name;
//
//    public Student7(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//}

//练习
//用接口给一个有工资收入和稿费收入的小伙伴算税。



//小结
//Java 的接口（interface）定义了纯抽象规范，一个类可以实现多个接口；
//
//接口也是数据类型，适用于向上转型和向下转型；
//
//接口的所有方法都是抽象方法，接口不能定义实例字段；
//
//接口可以定义 default 方法（JDK>=1.8）。
public class A7Interface {
    public static void main(String[] args) {
        // TODO: 用接口给一个有工资收入和稿费收入的小伙伴算税:
        Income7[] incomes = new Income7[] {new Baseincome7(3000), new SalaryIncome7(7500), new RoyaltyIncome7(12000) };
        double total = 0;
        // TODO:
        for(Income7 in:incomes){
            total+=in.getTax();
        }
        System.out.println(total);
    }
}
/**
 * 定义接口Income
 */
interface Income7 {
    // TODO
    double getTax();
}

class Baseincome7 implements Income7{
    protected double income;
    public Baseincome7 (double income){
        this.income=income;
    }

    @Override
    public double getTax(){
        if(income<=5000){
            return 0;
        }
        else {
            return (income - 5000) * 0.2;
        }
    }
}
/**
 * 稿费收入税率是20%
 */
class RoyaltyIncome7 extends Baseincome7{
    // TODO
    public RoyaltyIncome7 (double income){
        super(income);
    }

    @Override
    public double getTax(){
        return income*0.2;
    }

}
class SalaryIncome7 extends Baseincome7{
    // TODO
    public SalaryIncome7 (double income){
        super(income);
    }

    @Override
    public double getTax() {
        if (income <= 5000) {
            return 0;
        } else {
            return (income - 5000) * 0.2;
        }
    }
}
