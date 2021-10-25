import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Lingmao
 * @creat 2021-10-25  18:03
 */
//https://www.liaoxuefeng.com/wiki/1252599548343744/1303791989162017
/*
LocalDateTime 总是表示本地日期和时间，要表示一个带时区的日期和时间，我们就需要 ZonedDateTime。

可以简单地把 ZonedDateTime 理解成 LocalDateTime 加 ZoneId。ZoneId 是 java.time 引入的新的时区类，注意和旧的 java.util.TimeZone 区别。

要创建一个 ZonedDateTime 对象，有以下几种方法，一种是通过 now() 方法返回当前时间：
 */

//public class A4ZonedDateTime {
//    public static void main(String[] args) {
//        ZonedDateTime zbj = ZonedDateTime.now();//默认时区
//        ZonedDateTime zny = ZonedDateTime.now(ZoneId.of("America/New_York"));
//        System.out.println(zbj);
//        System.out.println(zny);
//    }
//}

/*
以这种方式创建的 ZonedDateTime，它的日期和时间与 LocalDateTime 相同，但附加的时区不同，因此是两个不同的时刻：

2019-09-15T15:16:17+08:00[Asia/Shanghai]
2019-09-15T15:16:17-04:00[America/New_York]
 */

//public class A4ZonedDateTime {
//    public static void main(String[] args) {
//        // 以中国时区获取当前时间
//        ZonedDateTime zbj=ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
//        // 转换为纽约时间
//        ZonedDateTime zny=zbj.withZoneSameInstant(ZoneId.of("America/New_York"));
//        System.out.println(zbj);
//        System.out.println(zny);
//    }
//}

/*
要特别注意，时区转换的时候，由于夏令时的存在，不同的日期转换的结果很可能是不同的。这是北京时间 9 月 15 日的转换结果：

2019-09-15T21:05:50.187697+08:00[Asia/Shanghai]
2019-09-15T09:05:50.187697-04:00[America/New_York]
这是北京时间 11 月 15 日的转换结果：

2019-11-15T21:05:50.187697+08:00[Asia/Shanghai]
2019-11-15T08:05:50.187697-05:00[America/New_York]
两次转换后的纽约时间有 1 小时的夏令时时差。

 涉及到时区时，千万不要自己计算时差，否则难以正确处理夏令时。
有了 ZonedDateTime，将其转换为本地时间就非常简单：

ZonedDateTime zdt = ...
LocalDateTime ldt = zdt.toLocalDateTime();
转换为 LocalDateTime 时，直接丢弃了时区信息。
 */

//练习
//某航线从北京飞到纽约需要 13 小时 20 分钟，请根据北京起飞日期和时间计算到达纽约的当地日期和时间。

public class A4ZonedDateTime {
    public static void main(String[] args) {
        LocalDateTime departureAtBeijing = LocalDateTime.of(2019, 9, 15, 13, 0, 0);
        int hours = 13;
        int minutes = 20;
        LocalDateTime arrivalAtNewYork = calculateArrivalAtNY(departureAtBeijing, hours, minutes);
        System.out.println(departureAtBeijing + " -> " + arrivalAtNewYork);
        // test:
        if (!LocalDateTime.of(2019, 10, 15, 14, 20, 0)
                .equals(calculateArrivalAtNY(LocalDateTime.of(2019, 10, 15, 13, 0, 0), 13, 20))) {
            System.err.println("测试失败!");
        } else if (!LocalDateTime.of(2019, 11, 15, 13, 20, 0)
                .equals(calculateArrivalAtNY(LocalDateTime.of(2019, 11, 15, 13, 0, 0), 13, 20))) {
            System.err.println("测试失败!");
        }
    }

    static LocalDateTime calculateArrivalAtNY(LocalDateTime bj, int h, int m) {
        ZonedDateTime zdt=bj.atZone(ZoneId.systemDefault());
        ZonedDateTime zny=zdt.withZoneSameInstant(ZoneId.of("America/New_York"));

        ZonedDateTime  z= zny.plusHours(h).plusMinutes(m);
        return z.toLocalDateTime();
    }
}

/*
小结
ZonedDateTime 是带时区的日期和时间，可用于时区转换；

ZonedDateTime 和 LocalDateTime 可以相互转换。
 */

