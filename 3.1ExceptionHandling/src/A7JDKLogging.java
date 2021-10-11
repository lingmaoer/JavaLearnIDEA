import com.sun.tools.javac.Main;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

/**
 * @author Lingmao
 * @creat 2021-10-09  21:04
 */

//来自  https://www.liaoxuefeng.com/wiki/1252599548343744/1264738568571776

/*
在编写程序的过程中，发现程序运行结果与预期不符，怎么办？当然是用 System.out.println() 打印出执行过程中的某些变量，观察每一步的结果与代码逻辑是否符合，然后有针对性地修改代码。

代码改好了怎么办？当然是删除没有用的 System.out.println() 语句了。

如果改代码又改出问题怎么办？再加上 System.out.println()。

反复这么搞几次，很快大家就发现使用 System.out.println() 非常麻烦。

怎么办？

解决方法是使用日志。

那什么是日志？日志就是 Logging，它的目的是为了取代 System.out.println()。

输出日志，而不是用 System.out.println()，有以下几个好处：

可以设置输出样式，避免自己每次都写 "ERROR: " + var；
可以设置输出级别，禁止某些级别输出。例如，只输出错误日志；
可以被重定向到文件，这样可以在程序运行结束后查看日志；
可以按包名控制日志级别，只输出某些包打的日志；
可以……
总之就是好处很多啦。

那如何使用日志？

因为 Java 标准库内置了日志包 java.util.logging，我们可以直接用。先看一个简单的例子：
 */

//public class A7JDKLogging {
//    public static class Hello {
//        public static void main(String[] args) {
//            Logger logger = Logger.getGlobal();
//            logger.info("start process...");
//            logger.warning("memory is running out...");
//            logger.fine("ignored.");
//            logger.severe("process will be terminated...");
//        }
//    }
//}

/*
运行上述代码，得到类似如下的输出：

Mar 02, 2019 6:32:13 PM Hello main
INFO: start process...
Mar 02, 2019 6:32:13 PM Hello main
WARNING: memory is running out...
Mar 02, 2019 6:32:13 PM Hello main
SEVERE: process will be terminated...
对比可见，使用日志最大的好处是，它自动打印了时间、调用类、调用方法等很多有用的信息。

再仔细观察发现，4 条日志，只打印了 3 条，logger.fine() 没有打印。这是因为，日志的输出可以设定级别。JDK 的 Logging 定义了 7 个日志级别，从严重到普通：

SEVERE
WARNING
INFO
CONFIG
FINE
FINER
FINEST
因为默认级别是 INFO，因此，INFO 级别以下的日志，不会被打印出来。使用日志级别的好处在于，调整级别，就可以屏蔽掉很多调试相关的日志输出。

使用 Java 标准库内置的 Logging 有以下局限：

Logging 系统在 JVM 启动时读取配置文件并完成初始化，一旦开始运行 main() 方法，就无法修改配置；

配置不太方便，需要在 JVM 启动时传递参数 -Djava.util.logging.config.file=<config-file-name>。

因此，Java 标准库内置的 Logging 使用并不是非常广泛。更方便的日志系统我们稍后介绍。
 */


//练习
//使用 logger.severe () 打印异常：

public class A7JDKLogging {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class.getName());
        logger.info("Start process...");
        try {
            "".getBytes("invalidCharsetName");
        } catch (UnsupportedEncodingException e) {
            // TODO: 使用logger.severe()打印异常
            logger.severe(e.toString());
        }
        logger.info("Process end.");
    }
}

/*
小结
日志是为了替代 System.out.println()，可以定义格式，重定向到文件等；

日志可以存档，便于追踪问题；

日志记录可以按级别分类，便于打开或关闭某些级别；

可以根据配置文件调整日志，无需修改代码；

Java 标准库提供了 java.util.logging 来实现日志功能。
 */

