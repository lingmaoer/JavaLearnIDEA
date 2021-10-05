/**
 * @author Lingmao
 * @creat 2021-10-04  22:09
 */

//public class A2Variable {
//	public static void main(String[] args) {
//		int x = 100;//定义一个int变量x,并赋予初始值100;
//		System.out.println(x);//打印变量
//		x=200;//重新赋值
//		System.out.println(x);
//	}
//}

//public class A2Variable {
//	public static void main(String[] args) {
//		int n = 100;//定义一个int变量x,并赋予初始值100;
//		System.out.println("n = " + n);//打印变量
//
//		n=200;//重新赋值
//		System.out.println("n = " + n);
//
//		int x=n;
//		System.out.println("x = "+x);
//
//		x=x+100;
//		System.out.println("x = "+x);
//		System.out.println("n = " + n);
//	}
//}

/*
整数类型：
	byte	8位(1字节)
	short	16位(2字节)
	int		32位(4字节)
	long	64位(8字节)

浮点数类型：
	float	16位(2字节)
	double	64位(8字节)

字符类型：
	char	16位(2字节)

布尔类型：
	boolean	16位(2字节)
*/

//public class A2Variable {
//    public static void main(String[] args) {
//        int i = 2147483647;
//        int i2 = -2147483648;
//        int i3 = 2_000_000_000; // 加下划线更容易识别
//        int i4 = 0xff0000; // 十六进制表示的16711680
//        int i5 = 0b1000000000; // 二进制表示的512
//        long l = 9000000000000000000L; // long型的结尾需要加L
//    }
//}

public class A2Variable {
    public static void main(String[] args) {
        char a = 'A';
        char zh = '中';
        System.out.println(a);
        System.out.println(zh);

        String s = "hello";//创建一个字符串变量

        final double PI = 3.14; // PI是一个常量
        double r = 5.0;
        double area = PI * r * r;
        //PI = 300; // compile error!
    }
}


