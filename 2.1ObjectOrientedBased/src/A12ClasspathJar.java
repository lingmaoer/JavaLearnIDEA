/**
 * @author Lingmao
 * @creat 2021-10-07  23:14
 */


//来自  https://www.liaoxuefeng.com/wiki/1252599548343744/1260466914339296

/*
在 Java 中，我们经常听到 classpath 这个东西。网上有很多关于 “如何设置 classpath” 的文章，但大部分设置都不靠谱。

到底什么是 classpath？

classpath 是 JVM 用到的一个环境变量，它用来指示 JVM 如何搜索 class。

因为 Java 是编译型语言，源码文件是.java，而编译后的.class 文件才是真正可以被 JVM 执行的字节码。因此，JVM 需要知道，如果要加载一个 abc.xyz.Hello 的类，应该去哪搜索对应的 Hello.class 文件。

所以，classpath 就是一组目录的集合，它设置的搜索路径与操作系统相关。例如，在 Windows 系统上，用 ; 分隔，带空格的目录用 "" 括起来，可能长这样：

C:\work\project1\bin;C:\shared;"D:\My Documents\project1\bin"
在 Linux 系统上，用: 分隔，可能长这样：

/usr/shared:/usr/local/bin:/home/liaoxuefeng/bin
现在我们假设 classpath 是.;C:\work\project1\bin;C:\shared，当 JVM 在加载 abc.xyz.Hello 这个类时，会依次查找：

<当前目录>\abc\xyz\Hello.class

C:\work\project1\bin\abc\xyz\Hello.class

C:\shared\abc\xyz\Hello.class

注意到. 代表当前目录。如果 JVM 在某个路径下找到了对应的 class 文件，就不再往后继续搜索。如果所有路径下都没有找到，就报错。

classpath 的设定方法有两种：

在系统环境变量中设置 classpath 环境变量，不推荐；

在启动 JVM 时设置 classpath 变量，推荐。

我们强烈不推荐在系统环境变量中设置 classpath，那样会污染整个系统环境。在启动 JVM 时设置 classpath 才是推荐的做法。实际上就是给 java 命令传入 -classpath 或 -cp 参数：

java -classpath .;C:\work\project1\bin;C:\shared abc.xyz.Hello
或者使用 -cp 的简写：

java -cp .;C:\work\project1\bin;C:\shared abc.xyz.Hello
没有设置系统环境变量，也没有传入 -cp 参数，那么 JVM 默认的 classpath 为.，即当前目录：

java abc.xyz.Hello
上述命令告诉 JVM 只在当前目录搜索 Hello.class。

在 IDE 中运行 Java 程序，IDE 自动传入的 -cp 参数是当前工程的 bin 目录和引入的 jar 包。

通常，我们在自己编写的 class 中，会引用 Java 核心库的 class，例如，String、ArrayList 等。这些 class 应该上哪去找？

有很多 “如何设置 classpath” 的文章会告诉你把 JVM 自带的 rt.jar 放入 classpath，但事实上，根本不需要告诉 JVM 如何去 Java 核心库查找 class，JVM 怎么可能笨到连自己的核心库在哪都不知道？

 不要把任何 Java 核心库添加到 classpath 中！JVM 根本不依赖 classpath 加载核心库！
更好的做法是，不要设置 classpath！默认的当前目录. 对于绝大多数情况都够用了。

假设我们有一个编译后的 Hello.class，它的包名是 com.example，当前目录是 C:\work，那么，目录结构必须如下：

C:\work
└─ com
   └─ example
      └─ Hello.class
运行这个 Hello.class 必须在当前目录下使用如下命令：

C:\work> java -cp . com.example.Hello
JVM 根据 classpath 设置的. 在当前目录下查找 com.example.Hello，即实际搜索文件必须位于 com/example/Hello.class。如果指定的.class 文件不存在，或者目录结构和包名对不上，均会报错。

jar 包
如果有很多.class 文件，散落在各层目录中，肯定不便于管理。如果能把目录打一个包，变成一个文件，就方便多了。

jar 包就是用来干这个事的，它可以把 package 组织的目录层级，以及各个目录下的所有文件（包括.class 文件和其他文件）都打成一个 jar 文件，这样一来，无论是备份，还是发给客户，就简单多了。

jar 包实际上就是一个 zip 格式的压缩文件，而 jar 包相当于目录。如果我们要执行一个 jar 包的 class，就可以把 jar 包放到 classpath 中：

java -cp ./hello.jar abc.xyz.Hello
这样 JVM 会自动在 hello.jar 文件里去搜索某个类。

那么问题来了：如何创建 jar 包？

因为 jar 包就是 zip 包，所以，直接在资源管理器中，找到正确的目录，点击右键，在弹出的快捷菜单中选择 “发送到”，“压缩 (zipped) 文件夹”，就制作了一个 zip 文件。然后，把后缀从.zip 改为.jar，一个 jar 包就创建成功。

假设编译输出的目录结构是这样：

package_sample
└─ bin
   ├─ hong
   │  └─ Person.class
   │  ming
   │  └─ Person.class
   └─ mr
      └─ jun
         └─ Arrays.class
这里需要特别注意的是，jar 包里的第一层目录，不能是 bin，而应该是 hong、ming、mr。如果在 Windows 的资源管理器中看，应该长这样：

hello.zip.ok

如果长这样：

hello.zip.invalid

说明打包打得有问题，JVM 仍然无法从 jar 包中查找正确的 class，原因是 hong.Person 必须按 hong/Person.class 存放，而不是 bin/hong/Person.class。

jar 包还可以包含一个特殊的 /META-INF/MANIFEST.MF 文件，MANIFEST.MF 是纯文本，可以指定 Main-Class 和其它信息。JVM 会自动读取这个 MANIFEST.MF 文件，如果存在 Main-Class，我们就不必在命令行指定启动的类名，而是用更方便的命令：

java -jar hello.jar
jar 包还可以包含其它 jar 包，这个时候，就需要在 MANIFEST.MF 文件里配置 classpath 了。

在大型项目中，不可能手动编写 MANIFEST.MF 文件，再手动创建 zip 包。Java 社区提供了大量的开源构建工具，例如 Maven，可以非常方便地创建 jar 包。






小结
JVM 通过环境变量 classpath 决定搜索 class 的路径和顺序；

不推荐设置系统环境变量 classpath，始终建议通过 -cp 命令传入；

jar 包相当于目录，可以包含很多.class 文件，方便下载和使用；

MANIFEST.MF 文件可以提供 jar 包的信息，如 Main-Class，这样可以直接运行 jar 包。
 */
