/**
 * @author Lingmao
 * @creat 2021-10-04  22:11
 */

//public class A5BoolOperate {
//	@SuppressWarnings("unused")
//	public static void main(String[] args)	{
//		boolean isGreater = 5 > 3; // true
//		System.out.println(isGreater);
//		int age = 12;
//		boolean isZero = age == 0; // false
//		System.out.println(isZero);
//		boolean isNonZero = !isZero; // true
//		System.out.println(isNonZero);
//		boolean isAdult = age >= 18; // false
//		System.out.println(isAdult);
//		boolean isTeenager = age >6 && age <18; // true
//		System.out.println(isTeenager);
//	}
//}
/*
关系运算符的优先级从高到低依次是：

!
>，>=，<，<=
==，!=
&&
||


短路运算

布尔运算的一个重要特点是短路运算。
如果一个布尔运算的表达式能提前确定结果，
则后续的计算不再执行，直接返回结果。

因为 false && x 的结果总是 false，
无论 x 是 true 还是 false，因此，
与运算在确定第一个值为 false 后，
不再继续计算，而是直接返回 false。

对于 || 运算，只要能确定第一个值为 true，
后续计算也不再进行，而是直接返回 true
*/

//public class A5BoolOperate {
//    public static void main(String[] args) {
//        boolean b = 5 < 3;
//        boolean result = b && (5 / 0 > 0);
//        System.out.println(result);
//    }
//}

/*
 *三元运算符
Java 还提供一个三元运算符 b ? x : y，
它根据第一个布尔表达式的结果，
分别返回后续两个表达式之一的计算结果。

三元运算 b ? x : y 会首先计算 b，如果 b 为 true，
则只计算 x，否则，只计算 y。此外，x 和 y 的类型必须相同，
因为返回值不是 boolean，而是 x 和 y 之一。
 */
//public class A5BoolOperate {
//    public static void main(String[] args) {
//        int n = -100;
//        int x = n >= 0 ? n : -n;
//        System.out.println(x);
//    }
//}


// 布尔运算
//判断指定年龄是否是小学生（6～12 岁）：
public class A5BoolOperate {
    public static void main(String[] args) {
        int age = 7;
        // primary student的定义: 6~12岁
        boolean isPrimaryStudent = age>6&&age<12;
        System.out.println(isPrimaryStudent ? "Yes" : "No");

    }
}
