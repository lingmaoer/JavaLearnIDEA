/**
 * @author Lingmao
 * @creat 2021-10-04  22:11
 */
//因为浮点数常常无法精确表示，因此，浮点数运算会产生误差
//public class A4FloatOperate {
//	public static void main(String[] args) {
//        double x = 1.0 / 10;
//        double y = 1 - 9.0 / 10;
//        // 观察x和y是否相等:
//        System.out.println(x);
//        System.out.println(y);
//        System.out.println(x==y?"相等":"不相等");
//
//     // 比较x和y是否相等，先计算其差的绝对值:
//        double r = Math.abs(x - y);
//        // 再判断绝对值是否足够小:
//        if (r < 0.00001) {
//            // 可以认为相等
//        	System.out.println("相等");
//        } else {
//            // 不相等
//        	System.out.println("不相等");
//        }
//	}
//}


//如果参与运算的两个数其中一个是整型，
//那么整型可以自动提升到浮点型
//public class A4FloatOperate {
//	public static void main(String[] args) {
//		int n=5;
//		double d=1.2+24.0/n;//6.0
//		System.out.println(d);
//	}
//}

//
//public class A4FloatOperate {
//	public static void main(String[] args) {
//		int n=5;
//		double d=1.2+24.0/n;//6.0
//		System.out.println(d);
//
//		//在一个复杂的四则运算中，两个整数的运算不会出现自动提升的情况
//		d = 1.2 + 24 / 5; // 5.2
//
//	}
//}

/*
整数运算在除数为 0 时会报错，而浮点数运算在除数为 0 时，不会报错，但会返回几个特殊值：

NaN 表示 Not a Number
Infinity 表示无穷大
-Infinity 表示负无穷大
例如：

double d1 = 0.0 / 0; // NaN
double d2 = 1.0 / 0; // Infinity
double d3 = -1.0 / 0; // -Infinity
*/

/*
可以将浮点数强制转型为整数。
在转型时，浮点数的小数部分会被丢掉。
如果转型后超过了整型能表示的最大范围，
将返回整型的最大值。例如：

int n1 = (int) 12.3; // 12
int n2 = (int) 12.7; // 12
int n2 = (int) -12.7; // -12
int n3 = (int) (12.7 + 0.5); // 13
int n4 = (int) 1.2e20; // 2147483647
*/


//练习
//根据一元二次方程ax^2+bx+c=0的求根公式：
//一元二次方程
public class A4FloatOperate {
    public static void main(String[] args) {
        double a = 1.0;
        double b = 3.0;
        double c = -4.0;
        // 求平方根可用 Math.sqrt():
        // System.out.println(Math.sqrt(2)); ==> 1.414
        // TODO:
        double r1 = 0;
        double r2 = 0;
        r1=(-b+Math.sqrt(b*b-4*a*c))/(2*a);
        r2=(-b-Math.sqrt(b*b-4*a*c))/(2*a);

        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r1 == 1 && r2 == -4 ? "测试通过" : "测试失败");
    }
}
