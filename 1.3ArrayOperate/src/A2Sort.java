import java.util.Arrays;

/**
 * @author Lingmao
 * @creat 2021-10-05  17:32
 */

//来自 https://www.liaoxuefeng.com/wiki/1252599548343744/1259543088592672

//对数组进行排序是程序中非常基本的需求。常用的排序算法有冒泡排序、插入排序和快速排序等。
//
// 我们来看一下如何使用冒泡排序算法对一个整型数组从小到大进行排序：
//public class A2Sort {
//    public static void main(String[] args) {
//        int[] ns = { 28, 12, 89, 73, 65, 18, 96, 50, 8, 36 };
//        // 排序前:
//        System.out.println(Arrays.toString(ns));
//        for (int i = 0; i < ns.length - 1; i++) {
//            for (int j = 0; j < ns.length - i - 1; j++) {
//                if (ns[j] > ns[j+1]) {
//                    // 交换ns[j]和ns[j+1]:
//                    int tmp = ns[j];
//                    ns[j] = ns[j+1];
//                    ns[j+1] = tmp;
//                }
//            }
//        }
//        // 排序后:
//        System.out.println(Arrays.toString(ns));
//    }
//}

/*
冒泡排序的特点是，每一轮循环后，最大的一个数被交换到末尾，因此，下一轮循环就可以 “刨除” 最后的数，每一轮循环都比上一轮循环的结束位置靠前一位。

另外，注意到交换两个变量的值必须借助一个临时变量。像这么写是错误的：

int x = 1;
int y = 2;

x = y; // x现在是2
y = x; // y现在还是2
正确的写法是：

int x = 1;
int y = 2;

int t = x; // 把x的值保存在临时变量t中, t现在是1
x = y; // x现在是2
y = t; // y现在是t的值1
实际上，Java 的标准库已经内置了排序功能，我们只需要调用 JDK 提供的 Arrays.sort() 就可以排序：
 */
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

//public class A2Sort {
//    public static void main(String[] args) {
//        int[] ns = { 28, 12, 89, 73, 65, 18, 96, 50, 8, 36 };
//        Arrays.sort(ns);
//        System.out.println(Arrays.toString(ns));
//    }
//}

/*
必须注意，对数组排序实际上修改了数组本身。例如，排序前的数组是：

int[] ns = { 9, 3, 6, 5 };
在内存中，这个整型数组表示如下：

      ┌───┬───┬───┬───┐
ns───>│ 9 │ 3 │ 6 │ 5 │
      └───┴───┴───┴───┘
当我们调用 Arrays.sort(ns); 后，这个整型数组在内存中变为：

      ┌───┬───┬───┬───┐
ns───>│ 3 │ 5 │ 6 │ 9 │
      └───┴───┴───┴───┘
即变量 ns 指向的数组内容已经被改变了。

如果对一个字符串数组进行排序，例如：

String[] ns = { "banana", "apple", "pear" };
排序前，这个数组在内存中表示如下：

                   ┌──────────────────────────────────┐
               ┌───┼──────────────────────┐           │
               │   │                      ▼           ▼
         ┌───┬─┴─┬─┴─┬───┬────────┬───┬───────┬───┬──────┬───┐
ns ─────>│░░░│░░░│░░░│   │"banana"│   │"apple"│   │"pear"│   │
         └─┬─┴───┴───┴───┴────────┴───┴───────┴───┴──────┴───┘
           │                 ▲
           └─────────────────┘
调用 Arrays.sort(ns); 排序后，这个数组在内存中表示如下：

                   ┌──────────────────────────────────┐
               ┌───┼──────────┐                       │
               │   │          ▼                       ▼
         ┌───┬─┴─┬─┴─┬───┬────────┬───┬───────┬───┬──────┬───┐
ns ─────>│░░░│░░░│░░░│   │"banana"│   │"apple"│   │"pear"│   │
         └─┬─┴───┴───┴───┴────────┴───┴───────┴───┴──────┴───┘
           │                              ▲
           └──────────────────────────────┘
原来的 3 个字符串在内存中均没有任何变化，但是 ns 数组的每个元素指向变化了。
 */

//练习
//请思考如何实现对数组进行降序排序：
public class A2Sort {
    public static void main(String[] args) {
        int[] ns = { 28, 12, 89, 73, 65, 18, 96, 50, 8, 36 };
        // 排序前:
        System.out.println(Arrays.toString(ns));
        for (int i = 0; i < ns.length; i++) {
            for (int j=0;j< ns.length-i-1;j++){
                if(ns[j]<ns[j+1])
                {
                    int temp=ns[j];
                    ns[j]=ns[j+1];
                    ns[j+1]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(ns));
        if (Arrays.toString(ns).equals("[96, 89, 73, 65, 50, 36, 28, 18, 12, 8]")) {
            System.out.println("测试成功");
        } else {
            System.out.println("测试失败");
        }
    }
}

