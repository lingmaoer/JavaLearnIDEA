import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author Lingmao
 * @creat 2021-10-24  15:31
 */
//https://www.liaoxuefeng.com/wiki/1252599548343744/1298366845681698

/*
序列化是指把一个 Java 对象变成二进制内容，本质上就是一个 byte[] 数组。

为什么要把 Java 对象序列化呢？因为序列化后可以把 byte[] 保存到文件中，或者把 byte[] 通过网络传输到远程，这样，就相当于把 Java 对象存储到文件或者通过网络传输出去了。

有序列化，就有反序列化，即把一个二进制内容（也就是 byte[] 数组）变回 Java 对象。有了反序列化，保存到文件中的 byte[] 数组又可以 “变回” Java 对象，或者从网络上读取 byte[] 并把它 “变回” Java 对象。

我们来看看如何把一个 Java 对象序列化。

一个 Java 对象要能序列化，必须实现一个特殊的 java.io.Serializable 接口，它的定义如下：

public interface Serializable {
}
Serializable 接口没有定义任何方法，它是一个空接口。我们把这样的空接口称为 “标记接口”（Marker Interface），实现了标记接口的类仅仅是给自身贴了个 “标记”，并没有增加任何方法。

序列化
把一个 Java 对象变为 byte[] 数组，需要使用 ObjectOutputStream。它负责把一个 Java 对象写入一个字节流：
 */

//public class A7Serialize {
//    public static void main(String[] args) throws IOException {
//        ByteArrayOutputStream buffer=new ByteArrayOutputStream();
//        try(ObjectOutputStream output=new ObjectOutputStream(buffer)){
//            //写入int
//            output.writeInt(123456);
//            //写入String
//            output.writeUTF("Hello World");
//            //写入object
//            output.writeObject(Double.valueOf(123.456));
//        }
//        System.out.println(Arrays.toString(buffer.toByteArray()));
//    }
//}
/*
反序列化
和 ObjectOutputStream 相反，ObjectInputStream 负责从一个字节流读取 Java 对象：

try (ObjectInputStream input = new ObjectInputStream(...)) {
    int n = input.readInt();
    String s = input.readUTF();
    Double d = (Double) input.readObject();
}
除了能读取基本类型和 String 类型外，调用 readObject() 可以直接返回一个 Object 对象。要把它变成一个特定类型，必须强制转型。

readObject() 可能抛出的异常有：

ClassNotFoundException：没有找到对应的 Class；
InvalidClassException：Class 不匹配。
对于 ClassNotFoundException，这种情况常见于一台电脑上的 Java 程序把一个 Java 对象，例如，Person 对象序列化以后，通过网络传给另一台电脑上的另一个 Java 程序，但是这台电脑的 Java 程序并没有定义 Person 类，所以无法反序列化。

对于 InvalidClassException，这种情况常见于序列化的 Person 对象定义了一个 int 类型的 age 字段，但是反序列化时，Person 类定义的 age 字段被改成了 long 类型，所以导致 class 不兼容。

为了避免这种 class 定义变动导致的不兼容，Java 的序列化允许 class 定义一个特殊的 serialVersionUID 静态变量，用于标识 Java 类的序列化 “版本”，通常可以由 IDE 自动生成。如果增加或修改了字段，可以改变 serialVersionUID 的值，这样就能自动阻止不匹配的 class 版本：

public class Person implements Serializable {
    private static final long serialVersionUID = 2709425275741743919L;
}
要特别注意反序列化的几个重要特点：

反序列化时，由 JVM 直接构造出 Java 对象，不调用构造方法，构造方法内部的代码，在反序列化时根本不可能执行。

安全性
因为 Java 的序列化机制可以导致一个实例能直接从 byte[] 数组创建，而不经过构造方法，因此，它存在一定的安全隐患。一个精心构造的 byte[] 数组被反序列化后可以执行特定的 Java 代码，从而导致严重的安全漏洞。

实际上，Java 本身提供的基于对象的序列化和反序列化机制既存在安全性问题，也存在兼容性问题。更好的序列化方法是通过 JSON 这样的通用数据结构来实现，只输出基本类型（包括 String）的内容，而不存储任何与代码相关的信息。
*/
public class A7Serialize {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream buffer=new ByteArrayOutputStream();
        try(ObjectOutputStream output=new ObjectOutputStream(buffer)){
            //写入int
            output.writeInt(123456);
            //写入String
            output.writeUTF("Hello World");
            //写入object
            output.writeObject(Double.valueOf(123.456));
        }
        System.out.println(Arrays.toString(buffer.toByteArray()));
        ByteArrayInputStream buff=new ByteArrayInputStream(buffer.toByteArray());
        try (ObjectInputStream input = new ObjectInputStream(buff)) {
            int n = input.readInt();
            String s = input.readUTF();
            Double d = null;
            try {
                d = (Double) input.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(n);
            System.out.println(s);
            System.out.println(d);
        }
    }
}
/*
小结
可序列化的 Java 对象必须实现 java.io.Serializable 接口，类似 Serializable 这样的空接口被称为 “标记接口”（Marker Interface）；

反序列化时不调用构造方法，可设置 serialVersionUID 作为版本号（非必需）；

Java 的序列化机制仅适用于 Java，如果需要与其它语言交换数据，必须使用通用的序列化方法，例如 JSON。
 */