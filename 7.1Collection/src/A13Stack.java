import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lingmao
 * @creat 2021-10-20  19:36
 */
//
/*
    栈（Stack）是一种后进先出（LIFO：Last In First Out）的数据结构。

什么是 LIFO 呢？我们先回顾一下 Queue 的特点 FIFO：

          ────────────────────────
  (\(\      (\(\    (\(\    (\(\      (\(\
 (='.') ─> (='.')  (='.')  (='.') ─> (='.')
O(_")")   O(_")") O(_")") O(_")")   O(_")")
          ────────────────────────
所谓 FIFO，是最先进队列的元素一定最早出队列，而 LIFO 是最后进 Stack 的元素一定最早出 Stack。如何做到这一点呢？只需要把队列的一端封死：

           ───────────────────────────────┐
  (\(\       (\(\    (\(\    (\(\    (\(\ │
 (='.') <─> (='.')  (='.')  (='.')  (='.')│
O(_")")    O(_")") O(_")") O(_")") O(_")")│
           ───────────────────────────────┘
因此，Stack 是这样一种数据结构：只能不断地往 Stack 中压入（push）元素，最后进去的必须最早弹出（pop）来：

donuts-stack

Stack 只有入栈和出栈的操作：

把元素压栈：push(E)；
把栈顶的元素 “弹出”：pop()；
取栈顶元素但不弹出：peek()。
在 Java 中，我们用 Deque 可以实现 Stack 的功能：

把元素压栈：push(E)/addFirst(E)；
把栈顶的元素 “弹出”：pop()/removeFirst()；
取栈顶元素但不弹出：peek()/peekFirst()。
为什么 Java 的集合类没有单独的 Stack 接口呢？因为有个遗留类名字就叫 Stack，出于兼容性考虑，所以没办法创建 Stack 接口，只能用 Deque 接口来 “模拟” 一个 Stack 了。

当我们把 Deque 作为 Stack 使用时，注意只调用 push()/pop()/peek() 方法，不要调用 addFirst()/removeFirst()/peekFirst() 方法，这样代码更加清晰。

Stack 的作用
Stack 在计算机中使用非常广泛，JVM 在处理 Java 方法调用的时候就会通过栈这种数据结构维护方法调用的层次。例如：

static void main(String[] args) {
    foo(123);
}

static String foo(x) {
    return "F-" + bar(x + 1);
}

static int bar(int x) {
    return x << 2;
}
JVM 会创建方法调用栈，每调用一个方法时，先将参数压栈，然后执行对应的方法；当方法返回时，返回值压栈，调用方法通过出栈操作获得方法返回值。

因为方法调用栈有容量限制，嵌套调用过多会造成栈溢出，即引发 StackOverflowError：
 */

//public class A13Stack {
//    public static void main(String[] args) {
//        increase(1);
//    }
//
//    static int increase(int x) {
//        return increase(x) + 1;
//    }
//}

/*
我们再来看一个 Stack 的用途：对整数进行进制的转换就可以利用栈。

例如，我们要把一个 int 整数 12500 转换为十六进制表示的字符串，如何实现这个功能？

首先我们准备一个空栈：

│   │
│   │
│   │
│   │
│   │
│   │
│   │
│   │
└───┘
然后计算 12500÷16=781…4，余数是 4，把余数 4 压栈：

│   │
│   │
│   │
│   │
│   │
│   │
│   │
│ 4 │
└───┘
然后计算 781÷16=48…13，余数是 13，13 的十六进制用字母 D 表示，把余数 D 压栈：

│   │
│   │
│   │
│   │
│   │
│ D │
│   │
│ 4 │
└───┘
然后计算 48÷16=3…0，余数是 0，把余数 0 压栈：

│   │
│   │
│   │
│ 0 │
│   │
│ D │
│   │
│ 4 │
└───┘
最后计算 3÷16=0…3，余数是 3，把余数 3 压栈：

│   │
│ 3 │
│   │
│ 0 │
│   │
│ D │
│   │
│ 4 │
└───┘
当商是 0 的时候，计算结束，我们把栈的所有元素依次弹出，组成字符串 30D4，这就是十进制整数 12500 的十六进制表示的字符串。

计算中缀表达式
在编写程序的时候，我们使用的带括号的数学表达式实际上是中缀表达式，即运算符在中间，例如：1 + 2 * (9 - 5)。

但是计算机执行表达式的时候，它并不能直接计算中缀表达式，而是通过编译器把中缀表达式转换为后缀表达式，例如：1 2 9 5 - * +。

这个编译过程就会用到栈。我们先跳过编译这一步（涉及运算优先级，代码比较复杂），看看如何通过栈计算后缀表达式。

计算后缀表达式不考虑优先级，直接从左到右依次计算，因此计算起来简单。首先准备一个空的栈：

│   │
│   │
│   │
│   │
│   │
│   │
│   │
│   │
└───┘
然后我们依次扫描后缀表达式 1 2 9 5 - * +，遇到数字 1，就直接扔到栈里：

│   │
│   │
│   │
│   │
│   │
│   │
│   │
│ 1 │
└───┘
紧接着，遇到数字 2，9，5，也扔到栈里：

│   │
│ 5 │
│   │
│ 9 │
│   │
│ 2 │
│   │
│ 1 │
└───┘
接下来遇到减号时，弹出栈顶的两个元素，并计算 9-5=4，把结果 4 压栈：

│   │
│   │
│   │
│ 4 │
│   │
│ 2 │
│   │
│ 1 │
└───┘
接下来遇到 * 号时，弹出栈顶的两个元素，并计算 2*4=8，把结果 8 压栈：

│   │
│   │
│   │
│   │
│   │
│ 8 │
│   │
│ 1 │
└───┘
接下来遇到 + 号时，弹出栈顶的两个元素，并计算 1+8=9，把结果 9 压栈：

│   │
│   │
│   │
│   │
│   │
│   │
│   │
│ 9 │
└───┘
扫描结束后，没有更多的计算了，弹出栈的唯一一个元素，得到计算结果 9。
 */

//练习
//请利用 Stack 把一个给定的整数转换为十六进制：

//public class A13Stack {
//    public static void main(String[] args) {
//        String hex = toHex(12500);
//        if (hex.equalsIgnoreCase("30D4")) {
//            System.out.println("测试通过");
//        } else {
//            System.out.println("测试失败");
//        }
//    }

//    static String toHex(int n) {
//        if(n>0) {
//            switch (n%16){
//                case 0:return toHex(n/16)+"0";
//                case 1:return toHex(n/16)+"1";
//                case 2:return toHex(n/16)+"2";
//                case 3:return toHex(n/16)+"3";
//                case 4:return toHex(n/16)+"4";
//                case 5:return toHex(n/16)+"5";
//                case 6:return toHex(n/16)+"6";
//                case 7:return toHex(n/16)+"7";
//                case 8:return toHex(n/16)+"8";
//                case 9:return toHex(n/16)+"9";
//                case 10:return toHex(n/16)+"A";
//                case 11:return toHex(n/16)+"B";
//                case 12:return toHex(n/16)+"C";
//                case 13:return toHex(n/16)+"D";
//                case 14:return toHex(n/16)+"E";
//                case 15:return toHex(n/16)+"F";
//                default:return "";
//            }
//        }
//        return "";
//    }
//}

//进阶练习：
//
//请利用 Stack 把字符串中缀表达式编译为后缀表达式，然后再利用栈执行后缀表达式获得计算结果：

/**
 * @author: 默苍璃
 * @date: 2021-09-15 16:14
 * <p>
 * 请利用 Stack 把字符串中缀表达式编译为后缀表达式，然后再利用栈执行后缀表达式获得计算结果
 */
//public class A13Stack {
//    public static void main(String[] args) {
//        String exp = "1 + 2 * (9 - 5)";
//        String change = change(exp);
//        int result = execute(change);
//        System.out.println(exp + " = " + result + " " + (result == 1 + 2 * (9 - 5) ? "✓" : "✗"));
//    }
//
//    /**
//     * 中缀表达式转换成后缀表达式 工具类
//     *
//     * @param exp
//     * @return
//     */
//    static String change(String exp) {
//// TODO: 1 2 9 5 - * +
//// 判断是否为数字 正则
//        Pattern pattern = Pattern.compile("[0-9]");
//// 判断是否为运算符 正则
//        Pattern pattern1 = Pattern.compile("[-+*/]");
//// 返回结果
//        StringBuilder result = new StringBuilder();
//// 储存中间结果的栈（结果栈）
//        Stack<String> s1 = new Stack<>();
//// 运算符栈
//        Stack<String> s2 = new Stack<>();
//// 字符串变 char []
//        char[] chars = exp.toCharArray();
//        for (char aChar : chars) {
//            String s = String.valueOf(aChar);
//            Matcher matcher = pattern.matcher(s);
//            Matcher matcher1 = pattern1.matcher(s);
//// 遇到操作数时，将其压 s1 (运算符栈)
//            if (matcher.matches()) {
//                s1.push(s);
//            }
//// 遇到运算符
//            if (matcher1.matches()) {
//                while (true) {
//// 如果 s2（运算符栈）为空，或栈顶运算符为左括号 “(”，则直接将此运算符入栈；
//                    if (s2.size() == 0 || s2.peek().equals("(")) {
//                        s2.push(s);
//                        break;
//                    }
//// 否则，若优先级比栈顶运算符的高，也将运算符压入 s2（运算符栈）；
//                    if ((s.equals("*") || s.equals("/")) && (s2.peek().equals("+") || s2.peek().equals("-"))) {
//                        s2.push(s);
//                        break;
//// 否则，将 s2 栈顶的运算符弹出并压入到 s1 中，再次转到与 s2 中新的栈顶运算符相比较；
//                    } else {
//                        s1.push(s2.pop());
//                    }
//                }
//            }
//// 遇到括号时
//// 如果是左括号 "("，则直接压入 s2（运算符栈）
//            if (s.equals("(")) {
//                s2.push(s);
//            }
//// 如果是右括号 ")"，则依次弹出 s2 栈顶的运算符，并压入 s1，直到遇到左括号为止，此时将这一对括号丢弃
//            if (s.equals(")")) {
//                for (int i = 0; i < s2.size(); i++) {
//                    String pop = s2.pop();
//                    if (!pop.equals("(")) {
//                        s1.push(pop);
//                    }
//                }
//            }
//        }
//        int size = s2.size();
//        for (int i = 0; i < size; i++) {
//            String pop = s2.pop();
//            s1.push(pop);
//        }
//        for (String s : s1) {
//            result.append(s);
//        }
//        return result.toString();
//    }
//    /**
//     * 中缀表达式 输出结果
//     *
//     * @return
//     */
//    public static int execute(String exp) {
//// TODO:
//// 判断是否为数字 正则
//        Pattern pattern = Pattern.compile("[0-9]");
//// 判断是否为运算符 正则
//        Pattern pattern1 = Pattern.compile("[-+*/]");
//// 储存中间结果的栈（结果栈）
//        Stack<String> s1 = new Stack<>();
//// 字符串变 char []
//        char[] chars = exp.toCharArray();
//        for (char aChar : chars) {
//            String s = String.valueOf(aChar);
//            Matcher matcher = pattern.matcher(s);
//            Matcher matcher1 = pattern1.matcher(s);
//// 遇到操作数时，直接扔到栈
//            if (matcher.matches()) {
//                s1.push(s);
//            }
//// 遇到运算符 弹出栈顶的两个元素，并计算 把结果压栈
//            if (matcher1.matches()) {
//                int i = Integer.parseInt(s1.pop());
//                int j = Integer.parseInt(s1.pop());
//                switch (s) {
//                    case "+":
//                        s1.push(Integer.toString(j + i));
//                        break;
//                    case "-":
//                        s1.push(Integer.toString(j - i));
//                        break;
//                    case "*":
//                        s1.push(Integer.toString(j * i));
//                        break;
//                    case "/":
//                        s1.push(Integer.toString(j / i));
//                        break;
//                    default:
//                }
//            }
//        }
//        return Integer.parseInt(s1.pop());
//    }
//}


//进阶练习 2：
//请把带变量的中缀表达式编译为后缀表达式，执行后缀表达式时，传入变量的值并获得计算结果：

/**
 * @author: 默苍璃
 * @date: 2021-09-15 16:14
 * <p>
 * 请利用 Stack 把字符串中缀表达式编译为后缀表达式，然后再利用栈执行后缀表达式获得计算结果
 */
public class A13Stack {
    public static void main(String[] args) {
        String exp = "x + 2 * (y - 5)";
        Map<String, Integer> env = Map.of("x", 1, "y", 9);
        String change = change(exp);
        int result = execute(change, env);
        System.out.println(exp + " = " + result + " " + (result == 1 + 2 * (9 - 5) ? "✓" : "✗"));
    }
    /**
     * 中缀表达式转换成后缀表达式 工具类
     *
     * @param exp
     * @return
     */
    static String change(String exp) {
// TODO: 1 2 9 5 - * +
// 判断是否为数字 正则
        Pattern pattern = Pattern.compile("[0-9xy]");
// 判断是否为运算符 正则
        Pattern pattern1 = Pattern.compile("[-+*/]");
// 返回结果
        StringBuilder result = new StringBuilder();
// 储存中间结果的栈（结果栈）
        Stack<String> s1 = new Stack<>();
// 运算符栈
        Stack<String> s2 = new Stack<>();
// 字符串变 char []
        char[] chars = exp.toCharArray();
        for (char aChar : chars) {
            String s = String.valueOf(aChar);
            Matcher matcher = pattern.matcher(s);
            Matcher matcher1 = pattern1.matcher(s);
// 遇到操作数时，将其压 s1 (运算符栈)
            if (matcher.matches()) {
                s1.push(s);
            }
// 遇到运算符
            if (matcher1.matches()) {
                while (true) {
// 如果 s2（运算符栈）为空，或栈顶运算符为左括号 “(”，则直接将此运算符入栈；
                    if (s2.size() == 0 || s2.peek().equals("(")) {
                        s2.push(s);
                        break;
                    }
// 否则，若优先级比栈顶运算符的高，也将运算符压入 s2（运算符栈）；
                    if ((s.equals("*") || s.equals("/")) && (s2.peek().equals("+") || s2.peek().equals("-"))) {
                        s2.push(s);
                        break;
// 否则，将 s2 栈顶的运算符弹出并压入到 s1 中，再次转到与 s2 中新的栈顶运算符相比较；
                    } else {
                        s1.push(s2.pop());
                    }
                }
            }
// 遇到括号时
// 如果是左括号 "("，则直接压入 s2（运算符栈）
            if (s.equals("(")) {
                s2.push(s);
            }
// 如果是右括号 ")"，则依次弹出 s2 栈顶的运算符，并压入 s1，直到遇到左括号为止，此时将这一对括号丢弃
            if (s.equals(")")) {
                for (int i = 0; i < s2.size(); i++) {
                    String pop = s2.pop();
                    if (!pop.equals("(")) {
                        s1.push(pop);
                    }
                }
            }
        }
        int size = s2.size();
        for (int i = 0; i < size; i++) {
            String pop = s2.pop();
            s1.push(pop);
        }
        for (String s : s1) {
            result.append(s);
        }
        return result.toString();
    }
    /**
     * 中缀表达式 输出结果
     *
     * @return
     */
    public static int execute(String exp, Map<String, Integer> env) {
// TODO:
// 判断是否为数字 正则
        Pattern pattern = Pattern.compile("[0-9xy]");
// 判断是否为运算符 正则
        Pattern pattern1 = Pattern.compile("[-+*/]");
// 储存中间结果的栈（结果栈）
        Stack<String> s1 = new Stack<>();
// 获取变量 x y
        int x = 0;
        int y = 0;
        for (Map.Entry<String, Integer> entry : env.entrySet()) {
            String key = entry.getKey();
            if (key.equals("x")) {
                x = entry.getValue();
            }
            if (key.equals("y")) {
                y = entry.getValue();
            }
        }
// 字符串变 char []
        char[] chars = exp.toCharArray();
        for (char aChar : chars) {
            String s = String.valueOf(aChar);
            Matcher matcher = pattern.matcher(s);
            Matcher matcher1 = pattern1.matcher(s);
// 遇到操作数时，直接扔到栈
            if (matcher.matches()) {
                s1.push(s);
            }
// 遇到运算符 弹出栈顶的两个元素，并计算 把结果压栈
            if (matcher1.matches()) {
                String x1 = s1.pop();
                String y1 = s1.pop();
                int i = 0;
                int j = 0;
                if (!x1.equals("x") && !x1.equals("y")) {
                    i = Integer.parseInt(x1);
                }
                if (!y1.equals("x") && !y1.equals("y")) {
                    j = Integer.parseInt(y1);
                }
                if (x1.equals("x")) {
                    i = x;
                }
                if (x1.equals("y")) {
                    i = y;
                }
                if (y1.equals("x")) {
                    j = x;
                }
                if (y1.equals("y")) {
                    j = y;
                }
                switch (s) {
                    case "+":
                        s1.push(Integer.toString(j + i));
                        break;
                    case "-":
                        s1.push(Integer.toString(j - i));
                        break;
                    case "*":
                        s1.push(Integer.toString(j * i));
                        break;
                    case "/":
                        s1.push(Integer.toString(j / i));
                        break;
                    default:
                }
            }
        }
        return Integer.parseInt(s1.pop());
    }
}

/*
小结
栈（Stack）是一种后进先出（LIFO）的数据结构，操作栈的元素的方法有：

把元素压栈：push(E)；
把栈顶的元素 “弹出”：pop(E)；
取栈顶元素但不弹出：peek(E)。
在 Java 中，我们用 Deque 可以实现 Stack 的功能，注意只调用 push()/pop()/peek() 方法，避免调用 Deque 的其他方法。

最后，不要使用遗留类 Stack。
 */