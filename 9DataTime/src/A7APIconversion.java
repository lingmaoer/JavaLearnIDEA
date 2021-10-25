import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * @author Lingmao
 * @creat 2021-10-25  21:08
 */

//https://www.liaoxuefeng.com/wiki/1252599548343744/1303978948165666
/*
由于 Java 提供了新旧两套日期和时间的 API，除非涉及到遗留代码，否则我们应该坚持使用新的 API。

如果需要与遗留代码打交道，如何在新旧 API 之间互相转换呢？

旧 API 转新 API
如果要把旧式的 Date 或 Calendar 转换为新 API 对象，可以通过 toInstant() 方法转换为 Instant 对象，再继续转换为 ZonedDateTime：

// Date -> Instant:
Instant ins1 = new Date().toInstant();

// Calendar -> Instant -> ZonedDateTime:
Calendar calendar = Calendar.getInstance();
Instant ins2 = calendar.toInstant();
ZonedDateTime zdt = ins2.atZone(calendar.getTimeZone().toZoneId());
从上面的代码还可以看到，旧的 TimeZone 提供了一个 toZoneId()，可以把自己变成新的 ZoneId。

新 API 转旧 API
如果要把新的 ZonedDateTime 转换为旧的 API 对象，只能借助 long 型时间戳做一个 “中转”：

// ZonedDateTime -> long:
ZonedDateTime zdt = ZonedDateTime.now();
long ts = zdt.toEpochSecond() * 1000;

// long -> Date:
Date date = new Date(ts);

// long -> Calendar:
Calendar calendar = Calendar.getInstance();
calendar.clear();
calendar.setTimeZone(TimeZone.getTimeZone(zdt.getZone().getId()));
calendar.setTimeInMillis(zdt.toEpochSecond() * 1000);
从上面的代码还可以看到，新的 ZoneId 转换为旧的 TimeZone，需要借助 ZoneId.getId() 返回的 String 完成。

在数据库中存储日期和时间
除了旧式的 java.util.Date，我们还可以找到另一个 java.sql.Date，它继承自 java.util.Date，但会自动忽略所有时间相关信息。这个奇葩的设计原因要追溯到数据库的日期与时间类型。

在数据库中，也存在几种日期和时间类型：

DATETIME：表示日期和时间；
DATE：仅表示日期；
TIME：仅表示时间；
TIMESTAMP：和 DATETIME 类似，但是数据库会在创建或者更新记录的时候同时修改 TIMESTAMP。
在使用 Java 程序操作数据库时，我们需要把数据库类型与 Java 类型映射起来。下表是数据库类型与 Java 新旧 API 的映射关系：

数据库	对应 Java 类（旧）	对应 Java 类（新）
DATETIME	java.util.Date	LocalDateTime
DATE	java.sql.Date	LocalDate
TIME	java.sql.Time	LocalTime
TIMESTAMP	java.sql.Timestamp	LocalDateTime
实际上，在数据库中，我们需要存储的最常用的是时刻（Instant），因为有了时刻信息，就可以根据用户自己选择的时区，显示出正确的本地时间。所以，最好的方法是直接用长整数 long 表示，在数据库中存储为 BIGINT 类型。

通过存储一个 long 型时间戳，我们可以编写一个 timestampToString() 的方法，非常简单地为不同用户以不同的偏好来显示不同的本地时间：
 */

public class A7APIconversion {
    public static void main(String[] args) {
        long ts=1574208900000L;
        System.out.println(timestampToString(ts,Locale.CANADA,"Asia/Shanghai"));
        System.out.println(timestampToString(ts,Locale.US,"America/New_York"));
    }

    static String timestampToString(long epochMilli, Locale lo,String zoneId){
        Instant ins=Instant.ofEpochMilli(epochMilli);
        DateTimeFormatter f=DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,FormatStyle.SHORT);
        return f.withLocale(lo).format(ZonedDateTime.ofInstant(ins, ZoneId.of(zoneId)));
    }
}

/*
对上述方法进行调用，结果如下：

2019年11月20日 上午8:15
Nov 19, 2019, 7:15 PM
*/

/*
小结
处理日期和时间时，尽量使用新的 java.time 包；

在数据库中存储时间戳时，尽量使用 long 型时间戳，它具有省空间，效率高，不依赖数据库的优点。
 */