import java.util.Arrays;

/**
 * @author Lingmao
 * @creat 2021-10-05  17:40
 */

//来自 https://www.liaoxuefeng.com/wiki/1252599548343744/1259544232593792

//二维数组
//二维数组就是数组的数组。定义一个二维数组如下：
//public class A3MultidimensionalArray {
//    public static void main(String[] args) {
//        int[][] ns={
//                {1,2,3,4},
//                {5,6,7,8},
//                {9,10,11,12}
//        };
//        System.out.println("ns.length = " + ns.length);
//    }
//}


//因为 ns 包含 3 个数组，因此，ns.length 为 3。实际上 ns 在内存中的结构如下：
//
//                    ┌───┬───┬───┬───┐
//         ┌───┐  ┌──>│ 1 │ 2 │ 3 │ 4 │
//ns ─────>│░░░│──┘   └───┴───┴───┴───┘
//         ├───┤      ┌───┬───┬───┬───┐
//         │░░░│─────>│ 5 │ 6 │ 7 │ 8 │
//         ├───┤      └───┴───┴───┴───┘
//         │░░░│──┐   ┌───┬───┬───┬───┐
//         └───┘  └──>│ 9 │10 │11 │12 │
//                    └───┴───┴───┴───┘
//如果我们定义一个普通数组 arr0，然后把 ns[0] 赋值给它：

//public class A3MultidimensionalArray {
//    public static void main(String[] args) {
//        int[][] ns = {
//                { 1, 2, 3, 4 },
//                { 5, 6, 7, 8 },
//                { 9, 10, 11, 12 }
//        };
//        int[] arr0 = ns[0];
//        System.out.println(arr0.length); // 4
//    }
//}

/*
实际上 arr0 就获取了 ns 数组的第 0 个元素。因为 ns 数组的每个元素也是一个数组，因此，arr0 指向的数组就是 { 1, 2, 3, 4 }。在内存中，结构如下：

            arr0 ─────┐
                      ▼
                    ┌───┬───┬───┬───┐
         ┌───┐  ┌──>│ 1 │ 2 │ 3 │ 4 │
ns ─────>│░░░│──┘   └───┴───┴───┴───┘
         ├───┤      ┌───┬───┬───┬───┐
         │░░░│─────>│ 5 │ 6 │ 7 │ 8 │
         ├───┤      └───┴───┴───┴───┘
         │░░░│──┐   ┌───┬───┬───┬───┐
         └───┘  └──>│ 9 │10 │11 │12 │
                    └───┴───┴───┴───┘
访问二维数组的某个元素需要使用 array[row][col]，例如：

System.out.println(ns[1][2]); // 7
二维数组的每个数组元素的长度并不要求相同，例如，可以这么定义 ns 数组：

int[][] ns = {
    { 1, 2, 3, 4 },
    { 5, 6 },
    { 7, 8, 9 }
};
这个二维数组在内存中的结构如下：

                    ┌───┬───┬───┬───┐
         ┌───┐  ┌──>│ 1 │ 2 │ 3 │ 4 │
ns ─────>│░░░│──┘   └───┴───┴───┴───┘
         ├───┤      ┌───┬───┐
         │░░░│─────>│ 5 │ 6 │
         ├───┤      └───┴───┘
         │░░░│──┐   ┌───┬───┬───┐
         └───┘  └──>│ 7 │ 8 │ 9 │
                    └───┴───┴───┘
要打印一个二维数组，可以使用两层嵌套的 for 循环：

for (int[] arr : ns) {
    for (int n : arr) {
        System.out.print(n);
        System.out.print(', ');
    }
    System.out.println();
}
或者使用 Java 标准库的 Arrays.deepToString()：
 */

//public class A3MultidimensionalArray {
//    public static void main(String[] args) {
//        int[][] ns = {
//                { 1, 2, 3, 4 },
//                { 5, 6, 7, 8 },
//                { 9, 10, 11, 12 }
//        };
//        System.out.println(Arrays.deepToString(ns));
//    }
//}

/*
****三维数组
三维数组就是二维数组的数组。可以这么定义一个三维数组：

int[][][] ns = {
    {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    },
    {
        {10, 11},
        {12, 13}
    },
    {
        {14, 15, 16},
        {17, 18}
    }
};
它在内存中的结构如下：

                              ┌───┬───┬───┐
                   ┌───┐  ┌──>│ 1 │ 2 │ 3 │
               ┌──>│░░░│──┘   └───┴───┴───┘
               │   ├───┤      ┌───┬───┬───┐
               │   │░░░│─────>│ 4 │ 5 │ 6 │
               │   ├───┤      └───┴───┴───┘
               │   │░░░│──┐   ┌───┬───┬───┐
        ┌───┐  │   └───┘  └──>│ 7 │ 8 │ 9 │
ns ────>│░░░│──┘              └───┴───┴───┘
        ├───┤      ┌───┐      ┌───┬───┐
        │░░░│─────>│░░░│─────>│10 │11 │
        ├───┤      ├───┤      └───┴───┘
        │░░░│──┐   │░░░│──┐   ┌───┬───┐
        └───┘  │   └───┘  └──>│12 │13 │
               │              └───┴───┘
               │   ┌───┐      ┌───┬───┬───┐
               └──>│░░░│─────>│14 │15 │16 │
                   ├───┤      └───┴───┴───┘
                   │░░░│──┐   ┌───┬───┐
                   └───┘  └──>│17 │18 │
                              └───┴───┘
如果我们要访问三维数组的某个元素，例如，ns[2][0][1]，只需要顺着定位找到对应的最终元素 15 即可。

理论上，我们可以定义任意的 N 维数组。但在实际应用中，除了二维数组在某些时候还能用得上，更高维度的数组很少使用。
 */

//练习
//使用二维数组可以表示一组学生的各科成绩，请计算所有学生的平均分：
public class A3MultidimensionalArray {
    public static void main(String[] args) {
        // 用二维数组表示的学生成绩:
        int[][] scores = {
                { 82, 90, 91 },
                { 68, 72, 64 },
                { 95, 91, 89 },
                { 67, 52, 60 },
                { 79, 81, 85 },
        };
        // TODO:
        double average = 0;
        double sum=0;
        int count=0;
        for(int[] arr:scores){
            count+=arr.length;
            for(int n:arr){
                sum+=n;
            }
        }
        average=sum/count;
        System.out.println(average);

        if (Math.abs(average - 77.733333) < 0.000001) {
            System.out.println("测试成功");
        } else {
            System.out.println("测试失败");
        }
    }
}