/**
 * @author Lingmao
 * @creat 2021-10-06  11:22
 */

//来自 https://www.liaoxuefeng.com/wiki/1252599548343744/1260454256258656

/*
在一个类中，我们可以定义多个方法。如果有一系列方法，它们的功能都是类似的，只有参数有所不同，那么，可以把这一组方法名做成同名方法。例如，在 Hello 类中，定义多个 hello() 方法：

class Hello {
    public void hello() {
        System.out.println("Hello, world!");
    }

    public void hello(String name) {
        System.out.println("Hello, " + name + "!");
    }

    public void hello(String name, int age) {
        if (age < 18) {
            System.out.println("Hi, " + name + "!");
        } else {
            System.out.println("Hello, " + name + "!");
        }
    }
}
这种方法名相同，但各自的参数不同，称为方法重载（Overload）。

注意：方法重载的返回值类型通常都是相同的。

方法重载的目的是，功能类似的方法使用同一名字，更容易记住，因此，调用起来更简单。

举个例子，String 类提供了多个重载方法 indexOf()，可以查找子串：

    int indexOf(int ch)：根据字符的 Unicode 码查找；

    int indexOf(String str)：根据字符串查找；

    int indexOf(int ch, int fromIndex)：根据字符查找，但指定起始位置；

    int indexOf(String str, int fromIndex) 根据字符串查找，但指定起始位置。
 */
//public class A3Overload {
//    public static void main(String[] args) {
//        String s = "Test string";
//        int n1 = s.indexOf('t');
//        int n2 = s.indexOf("st");
//        int n3 = s.indexOf("st", 4);
//        System.out.println(n1);
//        System.out.println(n2);
//        System.out.println(n3);
//    }
//}


public class A3Overload {
    public static void main(String[] args) {
        Person2 ming = new Person2();
        Person2 hong = new Person2();
        ming.setName("Xiao Ming");
        // TODO: 给Person增加重载方法setName(String, String):
        hong.setName("Xiao", "Hong");
        System.out.println(ming.getName());
        System.out.println(hong.getName());
    }
}
class Person2 {
    private String name;

    public void setName(String surname,String name){
        this.name=surname+" "+name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/*
小结
方法重载是指多个方法的方法名相同，但各自的参数不同；

重载方法应该完成类似的功能，参考 String 的 indexOf()；

重载方法返回值类型应该相同。
 */