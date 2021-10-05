import java.util.Scanner;

/**
 * @author Lingmao
 * @creat 2021-10-04  22:38
 */

/*
输出
在前面的代码中，我们总是使用 System.out.println() 来向屏幕输出一些内容。

println 是 print line 的缩写，表示输出并换行。因此，如果输出后不想换行，可以用 print()：
 */
//public class A1InOut {
//    public static void main(String[] args) {
//        System.out.print("A,");
//        System.out.print("B,");
//        System.out.print("C.");
//        System.out.println();
//        System.out.println("END");
//    }
//}

//格式化输出
//Java 还提供了格式化输出的功能。为什么要格式化输出？
// 因为计算机表示的数据不一定适合人来阅读：
//public class A1InOut {
//    public static void main(String[] args) {
//        double d = 12900000;
//        System.out.println(d); // 1.29E7
//    }
//}

//如果要把数据显示成我们期望的格式，就需要使用格式化输出的功能。
// 格式化输出使用 System.out.printf()，通过使用占位符 %?，
// printf() 可以把后面的参数格式化成指定格式：
//public class A1InOut {
//    public static void main(String[] args) {
//        double d = 3.1415926;
//        System.out.printf("%.2f\n", d); // 显示两位小数3.14
//        System.out.printf("%.4f\n", d); // 显示4位小数3.1416
//    }
//}

/*
Java 的格式化功能提供了多种占位符，可以把各种数据类型 “格式化” 成指定的字符串：

占位符	说明
%d	格式化输出整数
%x	格式化输出十六进制整数
%f	格式化输出浮点数
%e	格式化输出科学计数法表示的浮点数
%s	格式化字符串
注意，由于 % 表示占位符，因此，连续两个 %% 表示一个 % 字符本身。\
占位符本身还可以有更详细的格式化参数。下面的例子把一个整数格式化成十六进制，并用 0 补足 8 位：
 */

//public class A1InOut {
//    public static void main(String[] args) {
//        int n = 12345000;
//        System.out.printf("n=%d, hex=%08x", n, n); // 注意，两个%占位符必须传入两个数
//    }
//}


//输入
//和输出相比，Java 的输入就要复杂得多。
//
//我们先看一个从控制台读取一个字符串和一个整数的例子：
import java.util.Scanner;

//public class A1InOut {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Input your name:");
//        String name=scanner.nextLine();
//        System.out.print("Input your age:");
//        int age=scanner.nextInt();
//        System.out.printf("Hi, %s, you are %d\n",name,age);
//    }
//}

/*
首先，我们通过 import 语句导入 java.util.Scanner，
import 是导入某个类的语句，必须放到 Java 源代码的开头，
后面我们在 Java 的 package 中会详细讲解如何使用 import。

然后，创建 Scanner 对象并传入 System.in。System.out 代表标准输出流，
而 System.in 代表标准输入流。直接使用 System.in 读取用户输入虽然是可以的，
但需要更复杂的代码，而通过 Scanner 就可以简化后续的代码。

有了 Scanner 对象后，要读取用户输入的字符串，使用 scanner.nextLine()，
要读取用户输入的整数，使用 scanner.nextInt()。
Scanner 会自动转换数据类型，因此不必手动转换。


要测试输入，我们不能在线运行它，因为输入必须从命令行读取，因此，需要走编译、执行的流程：

$ javac Main.java
这个程序编译时如果有警告，可以暂时忽略它，在后面学习 IO 的时候再详细解释。编译成功后，执行：

$ java Main
Input your name: Bob
Input your age: 12
Hi, Bob, you are 12
根据提示分别输入一个字符串和整数后，我们得到了格式化的输出。

练习
请帮小明同学设计一个程序，输入上次考试成绩（int）和本次考试成绩（int），
然后输出成绩提高的百分比，保留两位小数位（例如，21.75%）。
 */
public class A1InOut {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input your last score:");
        int lastScore=scanner.nextInt();
        System.out.print("Input your this score:");
        int thisScore=scanner.nextInt();
        System.out.printf("%.2f%%\n",(thisScore-lastScore)*1.0/lastScore*100);
    }
}

/*
小结
Java 提供的输出包括：System.out.println() / print() / printf()，其中 printf() 可以格式化输出；

Java 提供 Scanner 对象来方便输入，读取对应的类型可以使用：scanner.nextLine() / nextInt() / nextDouble() / ...
*/