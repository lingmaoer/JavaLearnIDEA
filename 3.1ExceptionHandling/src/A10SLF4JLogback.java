import com.sun.tools.javac.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * @author Lingmao
 * @creat 2021-10-09  22:30
 */

//来自  https://www.liaoxuefeng.com/wiki/1252599548343744/1264739155914176
/*
前面介绍了 Commons Logging 和 Log4j 这一对好基友，它们一个负责充当日志 API，一个负责实现日志底层，搭配使用非常便于开发。
有的童鞋可能还听说过 SLF4J 和 Logback。这两个东东看上去也像日志，它们又是啥？
其实 SLF4J 类似于 Commons Logging，也是一个日志接口，而 Logback 类似于 Log4j，是一个日志的实现。
因为对 Commons Logging 的接口不满意，有人就搞了 SLF4J。因为对 Log4j 的性能不满意，有人就搞了 Logback。
我们先来看看 SLF4J 对 Commons Logging 的接口有何改进。在 Commons Logging 中，我们要打印日志，有时候得这么写：

int score = 99;
p.setScore(score);
log.info("Set score " + score + " for Person " + p.getName() + " ok.");
拼字符串是一个非常麻烦的事情，所以 SLF4J 的日志接口改进成这样了：

int score = 99;
p.setScore(score);
logger.info("Set score {} for Person {} ok.", score, p.getName());
我们靠猜也能猜出来，SLF4J 的日志接口传入的是一个带占位符的字符串，用后面的变量自动替换占位符，所以看起来更加自然。

如何使用 SLF4J？它的接口实际上和 Commons Logging 几乎一模一样：

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Main {
    final Logger logger = LoggerFactory.getLogger(getClass());
}
对比一下 Commons Logging 和 SLF4J 的接口：

Commons Logging	SLF4J
org.apache.commons.logging.Log	org.slf4j.Logger
org.apache.commons.logging.LogFactory	org.slf4j.LoggerFactory
不同之处就是 Log 变成了 Logger，LogFactory 变成了 LoggerFactory。
使用 SLF4J 和 Logback 和前面讲到的使用 Commons Logging 加 Log4j 是类似的，先分别下载 SLF4J(http://www.slf4j.org/download.html) 和 Logback(http://logback.qos.ch/download.html)，然后把以下 jar 包放到 classpath 下：
slf4j-api-1.7.x.jar
logback-classic-1.2.x.jar
logback-core-1.2.x.jar
然后使用 SLF4J 的 Logger 和 LoggerFactory 即可。和 Log4j 类似，我们仍然需要一个 Logback 的配置文件，把 logback.xml 放到 classpath 下，配置如下：

<?xml version="1.0" encoding="UTF-8"?>
<configuration>

	<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
		<encoder>
			<pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
		</encoder>
	</appender>

	<appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
		<encoder>
			<pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
			<charset>utf-8</charset>
		</encoder>
		<file>log/output.log</file>
		<rollingPolicy class="ch.qos.logback.core.rolling.FixedWindowRollingPolicy">
			<fileNamePattern>log/output.log.%i</fileNamePattern>
		</rollingPolicy>
		<triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
			<MaxFileSize>1MB</MaxFileSize>
		</triggeringPolicy>
	</appender>

	<root level="INFO">
		<appender-ref ref="CONSOLE" />
		<appender-ref ref="FILE" />
	</root>
</configuration>

运行即可获得类似如下的输出：
13:15:25.328 [main] INFO  com.itranswarp.learnjava.Main - Start process...
从目前的趋势来看，越来越多的开源项目从 Commons Logging 加 Log4j 转向了 SLF4J 加 Logback。
*/

public class A10SLF4JLogback {
    static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("Start process {}...", Main.class.getName());
        try {
            "".getBytes("invalidCharsetName");
        } catch (UnsupportedEncodingException e) {
            // TODO: 使用logger.error(String, Throwable)打印异常
        }
        logger.info("Process end.");
    }
}

/*
小结
SLF4J 和 Logback 可以取代 Commons Logging 和 Log4j；

始终使用 SLF4J 的接口写入日志，使用 Logback 只需要配置，不需要修改代码。
 */