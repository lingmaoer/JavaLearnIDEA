/**
 * @author Lingmao
 * @creat 2021-10-06  11:36
 */

//来自  https://www.liaoxuefeng.com/wiki/1252599548343744/1260454548196032

/*
在前面的章节中，我们已经定义了 Person 类：

class Person {
    private String name;
    private int age;

    public String getName() {...}
    public void setName(String name) {...}
    public int getAge() {...}
    public void setAge(int age) {...}
}
现在，假设需要定义一个 Student 类，字段如下：

class Student {
    private String name;
    private int age;
    private int score;

    public String getName() {...}
    public void setName(String name) {...}
    public int getAge() {...}
    public void setAge(int age) {...}
    public int getScore() { … }
    public void setScore(int score) { … }
}
仔细观察，发现 Student 类包含了 Person 类已有的字段和方法，只是多出了一个 score 字段和相应的 getScore()、setScore() 方法。

能不能在 Student 中不要写重复的代码？

这个时候，继承就派上用场了。

继承是面向对象编程中非常强大的一种机制，它首先可以复用代码。当我们让 Student 从 Person 继承时，Student 就获得了 Person 的所有功能，我们只需要为 Student 编写新增的功能。

Java 使用 extends 关键字来实现继承：

class Person {
    private String name;
    private int age;

    public String getName() {...}
    public void setName(String name) {...}
    public int getAge() {...}
    public void setAge(int age) {...}
}

class Student extends Person {
    // 不要重复name和age字段/方法,
    // 只需要定义新增score字段/方法:
    private int score;

    public int getScore() { … }
    public void setScore(int score) { … }
}
可见，通过继承，Student 只需要编写额外的功能，不再需要重复代码。

 注意：子类自动获得了父类的所有字段，严禁定义与父类重名的字段！
在 OOP 的术语中，我们把 Person 称为超类（super class），父类（parent class），基类（base class），把 Student 称为子类（subclass），扩展类（extended class）。

继承树
注意到我们在定义 Person 的时候，没有写 extends。在 Java 中，没有明确写 extends 的类，编译器会自动加上 extends Object。所以，任何类，除了 Object，都会继承自某个类。下图是 Person、Student 的继承树：

┌───────────┐
│  Object   │
└───────────┘
      ▲
      │
┌───────────┐
│  Person   │
└───────────┘
      ▲
      │
┌───────────┐
│  Student  │
└───────────┘
Java 只允许一个 class 继承自一个类，因此，一个类有且仅有一个父类。只有 Object 特殊，它没有父类。

类似的，如果我们定义一个继承自 Person 的 Teacher，它们的继承树关系如下：

       ┌───────────┐
       │  Object   │
       └───────────┘
             ▲
             │
       ┌───────────┐
       │  Person   │
       └───────────┘
          ▲     ▲
          │     │
          │     │
┌───────────┐ ┌───────────┐
│  Student  │ │  Teacher  │
└───────────┘ └───────────┘
protected
继承有个特点，就是子类无法访问父类的 private 字段或者 private 方法。例如，Student 类就无法访问 Person 类的 name 和 age 字段：

class Person {
    private String name;
    private int age;
}

class Student extends Person {
    public String hello() {
        return "Hello, " + name; // 编译错误：无法访问name字段
    }
}
这使得继承的作用被削弱了。为了让子类可以访问父类的字段，我们需要把 private 改为 protected。用 protected 修饰的字段可以被子类访问：

class Person {
    protected String name;
    protected int age;
}

class Student extends Person {
    public String hello() {
        return "Hello, " + name; // OK!
    }
}
因此，protected 关键字可以把字段和方法的访问权限控制在继承树内部，一个 protected 字段和方法可以被其子类，以及子类的子类所访问，后面我们还会详细讲解。

super
super 关键字表示父类（超类）。子类引用父类的字段时，可以用 super.fieldName。例如：

class Student extends Person {
    public String hello() {
        return "Hello, " + super.name;
    }
}
实际上，这里使用 super.name，或者 this.name，或者 name，效果都是一样的。编译器会自动定位到父类的 name 字段。

但是，在某些时候，就必须使用 super。我们来看一个例子：
*/
//public class A4Inherit {
//    public static void main(String[] args) {
//        Student s = new Student("Xiao Ming", 12, 89);
//    }
//}
//
//class Person3 {
//    protected String name;
//    protected int age;
//
//    public Person3(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//}
//
//class Student extends Person3 {
//    protected int score;
//
//    public Student(String name, int age, int score) {
//        //super(name,age);
//        this.score = score;
//    }
//}


//运行上面的代码，会得到一个编译错误，大意是在 Student 的构造方法中，无法调用 Person 的构造方法。
//
//这是因为在 Java 中，任何 class 的构造方法，第一行语句必须是调用父类的构造方法。如果没有明确地调用父类的构造方法，编译器会帮我们自动加一句 super();，所以，Student 类的构造方法实际上是这样：
//
//class Student extends Person {
//    protected int score;
//
//    public Student(String name, int age, int score) {
//        super(); // 自动调用父类的构造方法
//        this.score = score;
//    }
//}
//但是，Person 类并没有无参数的构造方法，因此，编译失败。
//
//解决方法是调用 Person 类存在的某个构造方法。例如：
//
//class Student extends Person {
//    protected int score;
//
//    public Student(String name, int age, int score) {
//        super(name, age); // 调用父类的构造方法Person(String, int)
//        this.score = score;
//    }
//}
//这样就可以正常编译了！
//
//因此我们得出结论：如果父类没有默认的构造方法，子类就必须显式调用 super() 并给出参数以便让编译器定位到父类的一个合适的构造方法。
//
//这里还顺带引出了另一个问题：即子类不会继承任何父类的构造方法。子类默认的构造方法是编译器自动生成的，不是继承的。
//
//阻止继承
//正常情况下，只要某个 class 没有 final 修饰符，那么任何类都可以从该 class 继承。
//
//从 Java 15 开始，允许使用 sealed 修饰 class，并通过 permits 明确写出能够从该 class 继承的子类名称。
//
//例如，定义一个 Shape 类：
//
//public sealed class Shape permits Rect, Circle, Triangle {
//    ...
//}
//上述 Shape 类就是一个 sealed 类，它只允许指定的 3 个类继承它。如果写：
//
//public final class Rect extends Shape {...}
//是没问题的，因为 Rect 出现在 Shape 的 permits 列表中。但是，如果定义一个 Ellipse 就会报错：
//
//public final class Ellipse extends Shape {...}
//// Compile error: class is not allowed to extend sealed class: Shape
//原因是 Ellipse 并未出现在 Shape 的 permits 列表中。这种 sealed 类主要用于一些框架，防止继承被滥用。
//
//sealed 类在 Java 15 中目前是预览状态，要启用它，必须使用参数 --enable-preview 和 --source 15。
//
//向上转型
//如果一个引用变量的类型是 Student，那么它可以指向一个 Student 类型的实例：
//
//Student s = new Student();
//如果一个引用类型的变量是 Person，那么它可以指向一个 Person 类型的实例：
//
//Person p = new Person();
//现在问题来了：如果 Student 是从 Person 继承下来的，那么，一个引用类型为 Person 的变量，能否指向 Student 类型的实例？
//
//Person p = new Student(); // ???
//测试一下就可以发现，这种指向是允许的！
//
//这是因为 Student 继承自 Person，因此，它拥有 Person 的全部功能。Person 类型的变量，如果指向 Student 类型的实例，对它进行操作，是没有问题的！
//
//这种把一个子类类型安全地变为父类类型的赋值，被称为向上转型（upcasting）。
//
//向上转型实际上是把一个子类型安全地变为更加抽象的父类型：
//
//Student s = new Student();
//Person p = s; // upcasting, ok
//Object o1 = p; // upcasting, ok
//Object o2 = s; // upcasting, ok
//注意到继承树是 Student > Person > Object，所以，可以把 Student 类型转型为 Person，或者更高层次的 Object。
//
//向下转型
//和向上转型相反，如果把一个父类类型强制转型为子类类型，就是向下转型（downcasting）。例如：
//
//Person p1 = new Student(); // upcasting, ok
//Person p2 = new Person();
//Student s1 = (Student) p1; // ok
//Student s2 = (Student) p2; // runtime error! ClassCastException!
//如果测试上面的代码，可以发现：
//
//Person 类型 p1 实际指向 Student 实例，Person 类型变量 p2 实际指向 Person 实例。在向下转型的时候，把 p1 转型为 Student 会成功，因为 p1 确实指向 Student 实例，把 p2 转型为 Student 会失败，因为 p2 的实际类型是 Person，不能把父类变为子类，因为子类功能比父类多，多的功能无法凭空变出来。
//
//因此，向下转型很可能会失败。失败的时候，Java 虚拟机会报 ClassCastException。
//
//为了避免向下转型出错，Java 提供了 instanceof 操作符，可以先判断一个实例究竟是不是某种类型：
//
//Person p = new Person();
//System.out.println(p instanceof Person); // true
//System.out.println(p instanceof Student); // false
//
//Student s = new Student();
//System.out.println(s instanceof Person); // true
//System.out.println(s instanceof Student); // true
//
//Student n = null;
//System.out.println(n instanceof Student); // false
//instanceof 实际上判断一个变量所指向的实例是否是指定类型，或者这个类型的子类。如果一个引用变量为 null，那么对任何 instanceof 的判断都为 false。
//
//利用 instanceof，在向下转型前可以先判断：
//
//Person p = new Student();
//if (p instanceof Student) {
//    // 只有判断成功才会向下转型:
//    Student s = (Student) p; // 一定会成功
//}
//从 Java 14 开始，判断 instanceof 后，可以直接转型为指定变量，避免再次强制转型。例如，对于以下代码：
//
//Object obj = "hello";
//if (obj instanceof String) {
//    String s = (String) obj;
//    System.out.println(s.toUpperCase());
//}
//可以改写如下：
//
//// instanceof variable:
//public class A4Inherit {
//    public static void main(String[] args) {
//        Object obj = "hello";
//        if (obj instanceof String s) {
//            // 可以直接使用变量s:
//            System.out.println(s.toUpperCase());
//        }
//    }
//}

/*
这种使用 instanceof 的写法更加简洁。

区分继承和组合
在使用继承时，我们要注意逻辑一致性。

考察下面的 Book 类：

class Book {
    protected String name;
    public String getName() {...}
    public void setName(String name) {...}
}
这个 Book 类也有 name 字段，那么，我们能不能让 Student 继承自 Book 呢？

class Student extends Book {
    protected int score;
}
显然，从逻辑上讲，这是不合理的，Student 不应该从 Book 继承，而应该从 Person 继承。

究其原因，是因为 Student 是 Person 的一种，它们是 is 关系，而 Student 并不是 Book。实际上 Student 和 Book 的关系是 has 关系。

具有 has 关系不应该使用继承，而是使用组合，即 Student 可以持有一个 Book 实例：

class Student extends Person {
    protected Book book;
    protected int score;
}
因此，继承是 is 关系，组合是 has 关系。
 */


//    练习
//            定义 PrimaryStudent，从 Student 继承，并新增一个 grade 字段：

public class A4Inherit {
    public static void main(String[] args) {
        Person3 p = new Person3("小明", 12);
        Student s = new Student("小红", 20, 99);
        // TODO: 定义PrimaryStudent，从Student继承，新增grade字段:
        Student ps = new PrimaryStudent("小军", 9, 100, 5);
        System.out.println(ps.getScore());
    }
}

class Person3 {
    protected String name;
    protected int age;

    public Person3(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}

class Student extends Person3 {
    protected int score;

    public Student(String name, int age, int score) {
        super(name, age);
        this.score = score;
    }

    public int getScore() { return score; }
}

class PrimaryStudent extends Student{
    // TODO:定义PrimaryStudent，从Student继承，新增grade字段:
    private int grade;
    public PrimaryStudent (String name, int age, int score,int grade){
        super(name,age,score);
        this.grade=grade;
    }
}

/*
小结
继承是面向对象编程的一种强大的代码复用方式；

Java 只允许单继承，所有类最终的根类是 Object；

protected 允许子类访问父类的字段和方法；

子类的构造方法可以通过 super() 调用父类的构造方法；

可以安全地向上转型为更抽象的类型；

可以强制向下转型，最好借助 instanceof 判断；

子类和父类的关系是 is，has 关系不能用继承。
 */
