import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @author Lingmao
 * @creat 2021-10-24  21:03
 */
//https://www.liaoxuefeng.com/wiki/1252599548343744/1303871087444002

/*
从 Java 8 开始，java.time 包提供了新的日期和时间 API，主要涉及的类型有：

本地日期和时间：LocalDateTime，LocalDate，LocalTime；
带时区的日期和时间：ZonedDateTime；
时刻：Instant；
时区：ZoneId，ZoneOffset；
时间间隔：Duration。
以及一套新的用于取代 SimpleDateFormat 的格式化类型 DateTimeFormatter。

和旧的 API 相比，新 API 严格区分了时刻、本地日期、本地时间和带时区的日期时间，并且，对日期和时间进行运算更加方便。

此外，新 API 修正了旧 API 不合理的常量设计：

Month 的范围用 1~12 表示 1 月到 12 月；
Week 的范围用 1~7 表示周一到周日。
最后，新 API 的类型几乎全部是不变类型（和 String 类似），可以放心使用不必担心被修改。

LocalDateTime
我们首先来看最常用的 LocalDateTime，它表示一个本地日期和时间：
 */

//public class A3LocalDateTime {
//    public static void main(String[] args) {
//        LocalDate d = LocalDate.now();
//        LocalTime t = LocalTime.now();
//        LocalDateTime dt = LocalDateTime.now();
//
//        System.out.println(d);
//        System.out.println(t);
//        System.out.println(dt);
//    }
//}

/*
本地日期和时间通过 now () 获取到的总是以当前默认时区返回的，和旧 API 不同，LocalDateTime、LocalDate 和 LocalTime 默认严格按照 ISO 8601 规定的日期和时间格式进行打印。

上述代码其实有一个小问题，在获取 3 个类型的时候，由于执行一行代码总会消耗一点时间，因此，3 个类型的日期和时间很可能对不上（时间的毫秒数基本上不同）。为了保证获取到同一时刻的日期和时间，可以改写如下：

LocalDateTime dt = LocalDateTime.now(); // 当前日期和时间
LocalDate d = dt.toLocalDate(); // 转换到当前日期
LocalTime t = dt.toLocalTime(); // 转换到当前时间
反过来，通过指定的日期和时间创建 LocalDateTime 可以通过 of() 方法：

// 指定日期和时间:
LocalDate d2 = LocalDate.of(2019, 11, 30); // 2019-11-30, 注意11=11月
LocalTime t2 = LocalTime.of(15, 16, 17); // 15:16:17
LocalDateTime dt2 = LocalDateTime.of(2019, 11, 30, 15, 16, 17);
LocalDateTime dt3 = LocalDateTime.of(d2, t2);
因为严格按照 ISO 8601 的格式，因此，将字符串转换为 LocalDateTime 就可以传入标准格式：

LocalDateTime dt = LocalDateTime.parse("2019-11-19T15:16:17");
LocalDate d = LocalDate.parse("2019-11-19");
LocalTime t = LocalTime.parse("15:16:17");
注意 ISO 8601 规定的日期和时间分隔符是 T。标准格式如下：

日期：yyyy-MM-dd
时间：HH:mm:ss
带毫秒的时间：HH:mm:ss.SSS
日期和时间：yyyy-MM-dd'T'HH:mm:ss
带毫秒的日期和时间：yyyy-MM-dd'T'HH:mm:ss.SSS
DateTimeFormatter
如果要自定义输出的格式，或者要把一个非 ISO 8601 格式的字符串解析成 LocalDateTime，可以使用新的 DateTimeFormatter：
 */

//public class A3LocalDateTime {
//    public static void main(String[] args) {
//        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        System.out.println(dtf.format(LocalDateTime.now()));
//
//        LocalDateTime dt2=LocalDateTime.parse("2019/11/30 15:16:17",dtf);
//        System.out.println(dt2);
//    }
//}

/*
注意到月份加减会自动调整日期，例如从 2019-10-31 减去 1 个月得到的结果是 2019-09-30，因为 9 月没有 31 日。

对日期和时间进行调整则使用 withXxx() 方法，例如：withHour(15) 会把 10:11:12 变为 15:11:12：

调整年：withYear ()
调整月：withMonth ()
调整日：withDayOfMonth ()
调整时：withHour ()
调整分：withMinute ()
调整秒：withSecond ()
示例代码如下：
 */

//public class A3LocalDateTime {
//    public static void main(String[] args) {
//        LocalDateTime dt=LocalDateTime.of(2019,10,26,20,30,59);
//        System.out.println(dt);
//
//        LocalDateTime dt2=dt.withDayOfMonth(31);
//        System.out.println(dt2);
//
//        LocalDateTime dt3=dt2.withMonth(9);
//        System.out.println(dt3);
//    }
//}

/*
同样注意到调整月份时，会相应地调整日期，即把 2019-10-31 的月份调整为 9 时，日期也自动变为 30。

实际上，LocalDateTime 还有一个通用的 with() 方法允许我们做更复杂的运算。例如：
 */

//public class A3LocalDateTime {
//    public static void main(String[] args) {
//        //本月第一天0:00时刻:
//        LocalDateTime firstDay=LocalDate.now().withDayOfMonth(1).atStartOfDay();
//        System.out.println(firstDay);
//        //本月最后一天:
//        LocalDate lastDay=LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
//        System.out.println(lastDay);
//        //下月第一天:
//        LocalDate nextMonthFirstDay=LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
//        System.out.println(nextMonthFirstDay);
//        //本月第一个周一
//        LocalDate firstWeek=LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
//        System.out.println(firstWeek);
//    }
//}

/*
对于计算某个月第 1 个周日这样的问题，新的 API 可以轻松完成。

要判断两个 LocalDateTime 的先后，可以使用 isBefore()、isAfter() 方法，对于 LocalDate 和 LocalTime 类似：
 */

//public class A3LocalDateTime {
//    public static void main(String[] args) {
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime target = LocalDateTime.of(2019, 11, 19, 8, 15, 0);
//        System.out.println(now.isBefore(target));
//        System.out.println(LocalDate.now().isBefore(LocalDate.of(2019, 11, 19)));
//        System.out.println(LocalTime.now().isAfter(LocalTime.parse("08:15:00")));
//    }
//}

/*
注意到 LocalDateTime 无法与时间戳进行转换，因为 LocalDateTime 没有时区，无法确定某一时刻。后面我们要介绍的 ZonedDateTime 相当于 LocalDateTime 加时区的组合，它具有时区，可以与 long 表示的时间戳进行转换。

Duration 和 Period
Duration 表示两个时刻之间的时间间隔。另一个类似的 Period 表示两个日期之间的天数：
 */

public class A3LocalDateTime {
    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.of(2019, 11, 19, 8, 15, 0);
        LocalDateTime end = LocalDateTime.of(2020, 1, 9, 19, 25, 30);
        Duration d = Duration.between(start, end);
        System.out.println(d); // PT1235H10M30S

        Period p = LocalDate.of(2019, 11, 19).until(LocalDate.of(2020, 1, 9));
        System.out.println(p); // P1M21D
    }
}
/*
注意到两个 LocalDateTime 之间的差值使用 Duration 表示，类似 PT1235H10M30S，表示 1235 小时 10 分钟 30 秒。而两个 LocalDate 之间的差值用 Period 表示，类似 P1M21D，表示 1 个月 21 天。

Duration 和 Period 的表示方法也符合 ISO 8601 的格式，它以 P...T... 的形式表示，P...T 之间表示日期间隔，T 后面表示时间间隔。如果是 PT... 的格式表示仅有时间间隔。利用 ofXxx() 或者 parse() 方法也可以直接创建 Duration：

Duration d1 = Duration.ofHours(10); // 10 hours
Duration d2 = Duration.parse("P1DT2H3M"); // 1 day, 2 hours, 3 minutes
有的童鞋可能发现 Java 8 引入的 java.timeAPI。怎么和一个开源的 Joda Time 很像？难道 JDK 也开始抄袭开源了？其实正是因为开源的 Joda Time 设计很好，应用广泛，所以 JDK 团队邀请 Joda Time 的作者 Stephen Colebourne 共同设计了 java.timeAPI。

小结
Java 8 引入了新的日期和时间 API，它们是不变类，默认按 ISO 8601 标准格式化和解析；

使用 LocalDateTime 可以非常方便地对日期和时间进行加减，或者调整日期和时间，它总是返回新对象；

使用 isBefore() 和 isAfter() 可以判断日期和时间的先后；

使用 Duration 和 Period 可以表示两个日期和时间的 “区间间隔”。
 */
