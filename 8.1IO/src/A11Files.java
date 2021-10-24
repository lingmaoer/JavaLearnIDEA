/**
 * @author Lingmao
 * @creat 2021-10-24  16:27
 */
//https://www.liaoxuefeng.com/wiki/1252599548343744/1375100746072098
/*
从 Java 7 开始，提供了 Files 和 Paths 这两个工具类，能极大地方便我们读写文件。

虽然 Files 和 Paths 是 java.nio 包里面的类，但他俩封装了很多读写文件的简单方法，例如，我们要把一个文件的全部内容读取为一个 byte[]，可以这么写：

byte[] data = Files.readAllBytes(Paths.get("/path/to/file.txt"));
如果是文本文件，可以把一个文件的全部内容读取为 String：

// 默认使用UTF-8编码读取:
String content1 = Files.readString(Paths.get("/path/to/file.txt"));
// 可指定编码:
String content2 = Files.readString(Paths.get("/path/to/file.txt"), StandardCharsets.ISO_8859_1);
// 按行读取并返回每行内容:
List<String> lines = Files.readAllLines(Paths.get("/path/to/file.txt"));
写入文件也非常方便：

// 写入二进制文件:
byte[] data = ...
Files.write(Paths.get("/path/to/file.txt"), data);
// 写入文本并指定编码:
Files.writeString(Paths.get("/path/to/file.txt"), "文本内容...", StandardCharsets.ISO_8859_1);
// 按行写入文本:
List<String> lines = ...
Files.write(Paths.get("/path/to/file.txt"), lines);
此外，Files 工具类还有 copy()、delete()、exists()、move() 等快捷方法操作文件和目录。

最后需要特别注意的是，Files 提供的读写方法，受内存限制，只能读写小文件，例如配置文件等，不可一次读入几个 G 的大文件。读写大型文件仍然要使用文件流，每次只读写一部分文件内容。

小结
对于简单的小文件读写操作，可以使用 Files 工具类简化代码。
 */

public class A11Files {
    public static void main(String[] args) {
        System.out.println("使用Files");
    }
}
