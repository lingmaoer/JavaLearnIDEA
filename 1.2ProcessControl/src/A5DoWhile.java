/**
 * @author Lingmao
 * @creat 2021-10-05  10:07
 */

//来自 https://www.liaoxuefeng.com/wiki/1252599548343744/1259541649945472
/*
在 Java 中，while 循环是先判断循环条件，再执行循环。
而另一种 do while 循环则是先执行循环，再判断条件，
条件满足时继续循环，条件不满足时退出。它的用法是：

do {
    执行循环语句
} while (条件表达式);
可见，do while 循环会至少循环一次。

我们把对 1 到 100 的求和用 do while 循环改写一下：
 */

//public class A5DoWhile {
//    public static void main(String[] args) {
//        int sum = 0;
//        int n = 1;
//        do {
//            sum = sum + n;
//            n ++;
//        } while (n <= 100);
//        System.out.println(sum);
//    }
//}


//使用 do while 循环时，同样要注意循环条件的判断。



//    练习
//使用 do while 循环计算从 m 到 n 的和。
public class A5DoWhile {
    public static void main(String[] args) {
        int sum = 0;
        int m = 20;
        int n = 100;
        // 使用do while计算M+...+N:
        do {
            sum+=m;
            m++;
        } while (m<=n);
        System.out.println(sum);
    }
}

/*
小结
do while 循环先执行循环，再判断条件；

do while 循环会至少执行一次。
*/
