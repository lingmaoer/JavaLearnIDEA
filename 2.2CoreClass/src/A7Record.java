/**
 * @author Lingmao
 * @creat 2021-10-08  19:03
 */

//来自 https://www.liaoxuefeng.com/wiki/1252599548343744/1331429187256353

/*
使用 String、Integer 等类型的时候，这些类型都是不变类，一个不变类具有以下特点：

定义 class 时使用 final，无法派生子类；
每个字段使用 final，保证创建实例后无法修改任何字段。
假设我们希望定义一个 Point 类，有 x、y 两个变量，同时它是一个不变类，可以这么写：

public final class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }
}
为了保证不变类的比较，还需要正确覆写 equals() 和 hashCode() 方法，这样才能在集合类中正常使用。后续我们会详细讲解正确覆写 equals() 和 hashCode()，这里演示 Point 不变类的写法目的是，这些代码写起来都非常简单，但是很繁琐。

record
从 Java 14 开始，引入了新的 Record 类。我们定义 Record 类时，使用关键字 record。把上述 Point 类改写为 Record 类，代码如下：
 */
public class A7Record {
    public static void main(String[] args) {
        Point p = new Point(123, 456);
        System.out.println(p.x());
        System.out.println(p.y());
        System.out.println(p);
    }
}

record Point(int x, int y) {}

/*
仔细观察 Point 的定义：

public record Point(int x, int y) {}
把上述定义改写为 class，相当于以下代码：

public final class Point extends Record {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public String toString() {
        return String.format("Point[x=%s, y=%s]", x, y);
    }

    public boolean equals(Object o) {
        ...
    }
    public int hashCode() {
        ...
    }
}
除了用 final 修饰 class 以及每个字段外，编译器还自动为我们创建了构造方法，和字段名同名的方法，以及覆写 toString()、equals() 和 hashCode() 方法。

换句话说，使用 record 关键字，可以一行写出一个不变类。

和 enum 类似，我们自己不能直接从 Record 派生，只能通过 record 关键字由编译器实现继承。

构造方法
编译器默认按照 record 声明的变量顺序自动创建一个构造方法，并在方法内给字段赋值。那么问题来了，如果我们要检查参数，应该怎么办？

假设 Point 类的 x、y 不允许负数，我们就得给 Point 的构造方法加上检查逻辑：

public record Point(int x, int y) {
    public Point {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException();
        }
    }
}
注意到方法 public Point {...} 被称为 Compact Constructor，它的目的是让我们编写检查逻辑，编译器最终生成的构造方法如下：

public final class Point extends Record {
    public Point(int x, int y) {
        // 这是我们编写的Compact Constructor:
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException();
        }
        // 这是编译器继续生成的赋值代码:
        this.x = x;
        this.y = y;
    }
    ...
}
作为 record 的 Point 仍然可以添加静态方法。一种常用的静态方法是 of() 方法，用来创建 Point：

public record Point(int x, int y) {
    public static Point of() {
        return new Point(0, 0);
    }
    public static Point of(int x, int y) {
        return new Point(x, y);
    }
}
这样我们可以写出更简洁的代码：

var z = Point.of();
var p = Point.of(123, 456);
小结
从 Java 14 开始，提供新的 record 关键字，可以非常方便地定义 Data Class：

使用 record 定义的是不变类；

可以编写 Compact Constructor 对参数进行验证；

可以定义静态方法。
 */