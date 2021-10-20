import java.time.DayOfWeek;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author Lingmao
 * @creat 2021-10-20  10:05
 */
//https://www.liaoxuefeng.com/wiki/1252599548343744/1265117217944672
/*
因为 HashMap 是一种通过对 key 计算 hashCode()，通过空间换时间的方式，直接定位到 value 所在的内部数组的索引，因此，查找效率非常高。

如果作为 key 的对象是 enum 类型，那么，还可以使用 Java 集合库提供的一种 EnumMap，它在内部以一个非常紧凑的数组存储 value，并且根据 enum 类型的 key 直接定位到内部数组的索引，并不需要计算 hashCode()，不但效率最高，而且没有额外的空间浪费。

我们以 DayOfWeek 这个枚举类型为例，为它做一个 “翻译” 功能：
 */

public class A6UseEnumMap {
    public static void main(String[] args) {
        Map<DayOfWeek, String> map = new EnumMap<>(DayOfWeek.class);
        map.put(DayOfWeek.MONDAY, "星期一");
        map.put(DayOfWeek.TUESDAY, "星期二");
        map.put(DayOfWeek.WEDNESDAY, "星期三");
        map.put(DayOfWeek.THURSDAY, "星期四");
        map.put(DayOfWeek.FRIDAY, "星期五");
        map.put(DayOfWeek.SATURDAY, "星期六");
        map.put(DayOfWeek.SUNDAY, "星期日");
        System.out.println(map);
        System.out.println(map.get(DayOfWeek.MONDAY));
    }
}

//使用 EnumMap 的时候，我们总是用 Map 接口来引用它，因此，实际上把 HashMap 和 EnumMap 互换，在客户端看来没有任何区别。

/*
如果 Map 的 key 是 enum 类型，推荐使用 EnumMap，既保证速度，也不浪费空间。

使用 EnumMap 的时候，根据面向抽象编程的原则，应持有 Map 接口。
 */
