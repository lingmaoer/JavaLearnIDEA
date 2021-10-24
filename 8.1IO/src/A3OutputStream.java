import java.io.*;

/**
 * @author Lingmao
 * @creat 2021-10-22  11:36
 */
//https://www.liaoxuefeng.com/wiki/1252599548343744/1298069169635361

/*
和 InputStream 相反，OutputStream 是 Java 标准库提供的最基本的输出流。

和 InputStream 类似，OutputStream 也是抽象类，它是所有输出流的超类。这个抽象类定义的一个最重要的方法就是 void write(int b)，签名如下：

public abstract void write(int b) throws IOException;
这个方法会写入一个字节到输出流。要注意的是，虽然传入的是 int 参数，但只会写入一个字节，即只写入 int 最低 8 位表示字节的部分（相当于 b & 0xff）。

和 InputStream 类似，OutputStream 也提供了 close() 方法关闭输出流，以便释放系统资源。要特别注意：OutputStream 还提供了一个 flush() 方法，它的目的是将缓冲区的内容真正输出到目的地。

为什么要有 flush()？因为向磁盘、网络写入数据的时候，出于效率的考虑，操作系统并不是输出一个字节就立刻写入到文件或者发送到网络，而是把输出的字节先放到内存的一个缓冲区里（本质上就是一个 byte[] 数组），等到缓冲区写满了，再一次性写入文件或者网络。对于很多 IO 设备来说，一次写一个字节和一次写 1000 个字节，花费的时间几乎是完全一样的，所以 OutputStream 有个 flush() 方法，能强制把缓冲区内容输出。

通常情况下，我们不需要调用这个 flush() 方法，因为缓冲区写满了 OutputStream 会自动调用它，并且，在调用 close() 方法关闭 OutputStream 之前，也会自动调用 flush() 方法。

但是，在某些情况下，我们必须手动调用 flush() 方法。举个栗子：

小明正在开发一款在线聊天软件，当用户输入一句话后，就通过 OutputStream 的 write() 方法写入网络流。小明测试的时候发现，发送方输入后，接收方根本收不到任何信息，怎么肥四？

原因就在于写入网络流是先写入内存缓冲区，等缓冲区满了才会一次性发送到网络。如果缓冲区大小是 4K，则发送方要敲几千个字符后，操作系统才会把缓冲区的内容发送出去，这个时候，接收方会一次性收到大量消息。

解决办法就是每输入一句话后，立刻调用 flush()，不管当前缓冲区是否已满，强迫操作系统把缓冲区的内容立刻发送出去。

实际上，InputStream 也有缓冲区。例如，从 FileInputStream 读取一个字节时，操作系统往往会一次性读取若干字节到缓冲区，并维护一个指针指向未读的缓冲区。然后，每次我们调用 int read() 读取下一个字节时，可以直接返回缓冲区的下一个字节，避免每次读一个字节都导致 IO 操作。当缓冲区全部读完后继续调用 read()，则会触发操作系统的下一次读取并再次填满缓冲区。

FileOutputStream
我们以 FileOutputStream 为例，演示如何将若干个字节写入文件流：

public void writeFile() throws IOException {
    OutputStream output = new FileOutputStream("out/readme.txt");
    output.write(72); // H
    output.write(101); // e
    output.write(108); // l
    output.write(108); // l
    output.write(111); // o
    output.close();
}
每次写入一个字节非常麻烦，更常见的方法是一次性写入若干字节。这时，可以用 OutputStream 提供的重载方法 void write(byte[]) 来实现：

public void writeFile() throws IOException {
    OutputStream output = new FileOutputStream("out/readme.txt");
    output.write("Hello".getBytes("UTF-8")); // Hello
    output.close();
}
和 InputStream 一样，上述代码没有考虑到在发生异常的情况下如何正确地关闭资源。写入过程也会经常发生 IO 错误，例如，磁盘已满，无权限写入等等。我们需要用 try(resource) 来保证 OutputStream 在无论是否发生 IO 错误的时候都能够正确地关闭：

public void writeFile() throws IOException {
    try (OutputStream output = new FileOutputStream("out/readme.txt")) {
        output.write("Hello".getBytes("UTF-8")); // Hello
    } // 编译器在此自动为我们写入finally并调用close()
}
阻塞
和 InputStream 一样，OutputStream 的 write() 方法也是阻塞的。

OutputStream 实现类
用 FileOutputStream 可以从文件获取输出流，这是 OutputStream 常用的一个实现类。此外，ByteArrayOutputStream 可以在内存中模拟一个 OutputStream：
 */

//public class A3OutputStream {
//    public static void main(String[] args) throws IOException {
//        byte[] data;
//        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
//            output.write("Hello ".getBytes("UTF-8"));
//            output.write("world!".getBytes("UTF-8"));
//            data = output.toByteArray();
//        }
//        System.out.println(new String(data, "UTF-8"));
//    }
//}

/*
ByteArrayOutputStream 实际上是把一个 byte[] 数组在内存中变成一个 OutputStream，虽然实际应用不多，但测试的时候，可以用它来构造一个 OutputStream。
同时操作多个 AutoCloseable 资源时，在 try(resource) { ... } 语句中可以同时写出多个资源，用 ; 隔开。例如，同时读写两个文件：
// 读取input.txt，写入output.txt:
try (InputStream input = new FileInputStream("input.txt");
     OutputStream output = new FileOutputStream("output.txt"))
{
    input.transferTo(output); // transferTo的作用是?
}
 */
//练习
//请利用 InputStream 和 OutputStream，编写一个复制文件的程序，它可以带参数运行：
//

public class A3OutputStream {
    public static void main(String[] args) throws IOException {
        String ReadFilePath="E:\\github\\JAVA\\JavaLearnIDEA\\8.1IO\\src\\A3Source";
        String WriteFilePath="E:\\github\\JAVA\\JavaLearnIDEA\\8.1IO\\src\\A3Copy.txt";
        int readfileLimit = 1024;
        copy(ReadFilePath, WriteFilePath, readfileLimit);
    }

    static void copy(String ReadFilePath, String WriteFilePath, int readfileLimit) throws IOException {
        // 友情提示：测试时请使用无关紧要的文件
        // TODO:
        int n = 0;
        // 写入对象
        OutputStream outputStream = null;
        byte[] buffer = new byte[readfileLimit];
        try(InputStream inputStream = new FileInputStream(ReadFilePath)){
            outputStream = new FileOutputStream(WriteFilePath);
            while (true){
                n = inputStream.read();
                if(n == -1){
                    break;
                }
                outputStream.write(n);
            }
        }catch (IOException e){
            System.out.println ("复制失败！");
            e.printStackTrace();
        }finally {
            if(outputStream != null) {
                outputStream.close();
            }
        }
    }
}

/*
小结
Java 标准库的 java.io.OutputStream 定义了所有输出流的超类：

FileOutputStream 实现了文件流输出；

ByteArrayOutputStream 在内存中模拟一个字节流输出。

某些情况下需要手动调用 OutputStream 的 flush() 方法来强制输出缓冲区。

总是使用 try(resource) 来保证 OutputStream 正确关闭。
 */