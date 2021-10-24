/**
 * @author Lingmao
 * @creat 2021-10-24  15:23
 */
//https://www.liaoxuefeng.com/wiki/1252599548343744/1298366384308257

/*
很多 Java 程序启动的时候，都需要读取配置文件。例如，从一个.properties 文件中读取配置：

String conf = "C:\\conf\\default.properties";
try (InputStream input = new FileInputStream(conf)) {
    // TODO:
}
这段代码要正常执行，必须在 C 盘创建 conf 目录，然后在目录里创建 default.properties 文件。但是，在 Linux 系统上，路径和 Windows 的又不一样。

因此，从磁盘的固定目录读取配置文件，不是一个好的办法。

有没有路径无关的读取文件的方式呢？

我们知道，Java 存放.class 的目录或 jar 包也可以包含任意其他类型的文件，例如：

配置文件，例如.properties；
图片文件，例如.jpg；
文本文件，例如.txt，.csv；
……
从 classpath 读取文件就可以避免不同环境下文件路径不一致的问题：如果我们把 default.properties 文件放到 classpath 中，就不用关心它的实际存放路径。

在 classpath 中的资源文件，路径总是以／开头，我们先获取当前的 Class 对象，然后调用 getResourceAsStream() 就可以直接从 classpath 读取任意的资源文件：

try (InputStream input = getClass().getResourceAsStream("/default.properties")) {
    // TODO:
}
调用 getResourceAsStream() 需要特别注意的一点是，如果资源文件不存在，它将返回 null。因此，我们需要检查返回的 InputStream 是否为 null，如果为 null，表示资源文件在 classpath 中没有找到：

try (InputStream input = getClass().getResourceAsStream("/default.properties")) {
    if (input != null) {
        // TODO:
    }
}
如果我们把默认的配置放到 jar 包中，再从外部文件系统读取一个可选的配置文件，就可以做到既有默认的配置文件，又可以让用户自己修改配置：

Properties props = new Properties();
props.load(inputStreamFromClassPath("/default.properties"));
props.load(inputStreamFromFile("./conf.properties"));
这样读取配置文件，应用程序启动就更加灵活。

小结
把资源存储在 classpath 中可以避免文件路径依赖；

Class 对象的 getResourceAsStream() 可以从 classpath 中读取指定资源；

根据 classpath 读取资源时，需要检查返回的 InputStream 是否为 null。
 */

public class A6ReadclasspathSource {
    public static void main(String[] args) {
        System.out.println("读取 classpath 资源");
    }
}
