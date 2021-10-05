/**
 * @author Lingmao
 * @creat 2021-10-04  22:10
 */
//
//public class A3IntOperate {
//	public static void main(String[] args){
//        int i = (100 + 200) * (99 - 88); // 3300
//        int n = 7 * (5 + (i - 9)); // 23072
//        System.out.println(i);
//        System.out.println(n);
//
//        int x = 12345 / 67; // 184(整除)
//        System.out.println("x = "+x);
//
//        int y=12345%67;//求余数
//
//        int x1 = 2147483640;
//        int y1 = 15;
//        int sum = x1 + y1;
//        //越界后,值变负  用long可以解决
//        System.out.println(sum); // -2147483641
//
//        n += 100; // 3409, 相当于 n = n + 100;
//        n -= 100; // 3309, 相当于 n = n - 100;
//
//        //自增  自减
//        int n1 = 3300;
//        n1++; // 3301, 相当于 n = n + 1;
//        n1--; // 3300, 相当于 n = n - 1;
//        int y2 = 100 + (++n1); // 不要这么写
//        System.out.println(y2);
//	}
//}

//左移
//public class A3IntOperate {
//	public static void main(String[] args){
//		int n = 7;       // 00000000 00000000 00000000 00000111 = 7
//		int a = n << 1;  // 00000000 00000000 00000000 00001110 = 14
//		int b = n << 2;  // 00000000 00000000 00000000 00011100 = 28
//		int c = n << 28; // 01110000 00000000 00000000 00000000 = 1879048192
//		int d = n << 29; // 11100000 00000000 00000000 00000000 = -536870912
//	}
//}

//正数有符号右移
//public class A3IntOperate {
//	public static void main(String[] args){
//		int n = 7;       // 00000000 00000000 00000000 00000111 = 7
//		int a = n >> 1;  // 00000000 00000000 00000000 00000011 = 3
//		int b = n >> 2;  // 00000000 00000000 00000000 00000001 = 1
//		int c = n >> 3;  // 00000000 00000000 00000000 00000000 = 0
//	}
//}

//负数有符号右移
//public class A3IntOperate {
//	public static void main(String[] args){
//		int n = -536870912;
//		int a = n >> 1;  // 11110000 00000000 00000000 00000000 = -268435456
//		int b = n >> 2;  // 11111000 00000000 00000000 00000000 = -134217728
//		int c = n >> 28; // 11111111 11111111 11111111 11111110 = -2
//		int d = n >> 29; // 11111111 11111111 11111111 11111111 = -1
//	}
//}


//无符号右移
//public class A3IntOperate {
//	public static void main(String[] args){
//		int n = -536870912;
//		int a = n >>> 1;  // 01110000 00000000 00000000 00000000 = 1879048192
//		int b = n >>> 2;  // 00111000 00000000 00000000 00000000 = 939524096
//		int c = n >>> 29; // 00000000 00000000 00000000 00000111 = 7
//		int d = n >>> 31; // 00000000 00000000 00000000 00000001 = 1
//	}
//}

//与或非运算
/*
与运算的规则是，必须两个数同时为 1，结果才为 1：
n = 0 & 0; // 0
n = 0 & 1; // 0
n = 1 & 0; // 0
n = 1 & 1; // 1

或运算的规则是，只要任意一个为 1，结果就为 1：

n = 0 | 0; // 0
n = 0 | 1; // 1
n = 1 | 0; // 1
n = 1 | 1; // 1
非运算的规则是，0 和 1 互换：

n = ~0; // 1
n = ~1; // 0
异或运算的规则是，如果两个数不同，结果为 1，否则为 0：

n = 0 ^ 0; // 0
n = 0 ^ 1; // 1
n = 1 ^ 0; // 1
n = 1 ^ 1; // 0
*/

// 位运算
//public class A3IntOperate {
//    public static void main(String[] args) {
//        int i = 167776589; // 00001010 00000000 00010001 01001101
//        int n = 167776512; // 00001010 00000000 00010001 00000000
//        System.out.println(i & n); // 167776512
//    }
//}

/*
运算优先级
在 Java 的计算表达式中，运算优先级从高到低依次是：

()
! ~ ++ --
* / %
+ -
<< >> >>>
&
|
+= -= *= /=

*/


// 类型自动提升与强制转型
//public class A3IntOperate {
//    public static void main(String[] args) {
//        short s = 1234;
//        int i = 123456;
//        int x = s + i; // s自动转型为int
//        //short y = s + i; // 编译错误!
//
//        //强制转换
//        long u=(long)x;
//    }
//}

//强制转型
//超出范围的强制转型会得到错误的结果，
//原因是转型时，int 的两个高位字节直接被扔掉，
//仅保留了低位的两个字节：
//public class A3IntOperate {
//    public static void main(String[] args) {
//        int i1 = 1234567;
//        short s1 = (short) i1; // -10617
//        System.out.println(s1);
//        int i2 = 12345678;
//        short s2 = (short) i2; // 24910
//        System.out.println(s2);
//    }
//}

//练习
//计算前 N 个自然数的和
//计算前N个自然数的和
public class A3IntOperate {
    public static void main(String[] args) {
        int n = 100;
        // TODO: sum = 1 + 2 + ... + n
        int sum = (1+n)*n/2;

        System.out.println(sum);
        System.out.println(sum == 5050 ? "测试通过" : "测试失败");
    }
}


