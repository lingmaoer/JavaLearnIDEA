import java.util.Scanner;

/**
 * @author Lingmao
 * @creat 2021-10-04  23:22
 */

//来自 https://www.liaoxuefeng.com/wiki/1252599548343744/1259541030848864

/*
除了 if 语句外，还有一种条件判断，
是根据某个表达式的结果，分别去执行不同的分支。

例如，在游戏中，让用户选择选项：

单人模式
多人模式
退出游戏
这时，switch 语句就派上用场了。

switch 语句根据 switch (表达式) 计算的结果，
跳转到匹配的 case 结果，然后继续执行后续语句，
直到遇到 break 结束执行。
*/
//public class A3SwitchStatement {
//    public static void main(String[] args) {
//        int option = 3;
//        switch (option){
//            case 1:
//                System.out.println("Slected 1");
//                break;
//            case 2:
//                System.out.println("Selected 2");
//                break;
//            case 3:
//                System.out.println("Selected 3");
//                break;
//        }
//    }
//}

//修改 option 的值分别为 1、2、3，观察执行结果。
//
//如果 option 的值没有匹配到任何 case，
// 例如 option = 99，那么，switch 语句不会执行任何语句。
// 这时，可以给 switch 语句加一个 default，
// 没有匹配到任何 case 时，执行 default：

//public class A3SwitchStatement {
//    public static void main(String[] args) {
//        int option = 99;
//        switch (option) {
//            case 1:
//                System.out.println("Selected 1");
//                break;
//            case 2:
//                System.out.println("Selected 2");
//                break;
//            case 3:
//                System.out.println("Selected 3");
//                break;
//            default:
//                System.out.println("Not selected");
//                break;
//        }
//    }
//}

/*
如果把 switch 语句翻译成 if 语句，
那么上述的代码相当于：

if (option == 1) {
    System.out.println("Selected 1");
} else if (option == 2) {
    System.out.println("Selected 2");
} else if (option == 3) {
    System.out.println("Selected 3");
} else {
    System.out.println("Not selected");
}

对于多个 == 判断的情况，使用 switch 结构更加清晰。

同时注意，上述 “翻译” 只有在 switch 语句中
对每个 case 正确编写了 break 语句才能对应得上。

使用 switch 时，注意 case 语句并没有花括号 {}，
而且，case 语句具有 “穿透性”，漏写 break 将导致意想不到的结果：
*/
//public class A3SwitchStatement {
//    public static void main(String[] args) {
//        int option = 2;
//        switch (option) {
//            case 1:
//                System.out.println("Selected 1");
//            case 2:
//                System.out.println("Selected 2");
//            case 3:
//                System.out.println("Selected 3");
//            default:
//                System.out.println("Not selected");
//        }
//    }
//}

//public class A3SwitchStatement {
//    public static void main(String[] args) {
//        int option = 2;
//        switch (option) {
//            case 1:
//                System.out.println("Selected 1");
//            case 2:
//                System.out.println("Selected 2");
//            case 3:
//                System.out.println("Selected 3");
//            default:
//                System.out.println("Not selected");
//        }
//    }
//}

//当 option = 2 时，将依次输出 "Selected 2"、
// "Selected 3"、"Not selected"，原因是从匹配到
// case 2 开始，后续语句将全部执行，直到遇到 break 语句。
// 因此，任何时候都不要忘记写 break。
//
//如果有几个 case 语句执行的是同一组语句块，可以这么写：

//public class A3SwitchStatement {
//    public static void main(String[] args) {
//        int option = 2;
//        switch (option) {
//            case 1:
//                System.out.println("Selected 1");
//                break;
//            case 2:
//            case 3:
//                System.out.println("Selected 2, 3");
//                break;
//            default:
//                System.out.println("Not selected");
//                break;
//        }
//    }
//}

//public class A3SwitchStatement {
//    public static void main(String[] args) {
//        int option = 2;
//        switch (option) {
//            case 1:
//                System.out.println("Selected 1");
//                break;
//            case 2:
//            case 3:
//                System.out.println("Selected 2, 3");
//                break;
//            default:
//                System.out.println("Not selected");
//                break;
//        }
//    }
//}

//使用 switch 语句时，只要保证有 break，
// case 的顺序不影响程序逻辑：

//public class A3SwitchStatement {
//    public static void main(String[] args) {
//        int option = 2;
//        switch (option) {
//            case 1:
//                System.out.println("Selected 1");
//                break;
//            case 2:
//            case 3:
//                System.out.println("Selected 2, 3");
//                break;
//            default:
//                System.out.println("Not selected");
//                break;
//        }
//    }
//}

/*
switch (option) {
case 3:
    ...
    break;
case 2:
    ...
    break;
case 1:
    ...
    break;
}
 */

//但是仍然建议按照自然顺序排列，便于阅读。
//
//switch 语句还可以匹配字符串。字符串匹配时，
// 是比较 “内容相等”。例如：

//public class A3SwitchStatement {
//    public static void main(String[] args) {
//        String fruit = "apple";
//        switch (fruit) {
//            case "apple":
//                System.out.println("Selected apple");
//                break;
//            case "pear":
//                System.out.println("Selected pear");
//                break;
//            case "mango":
//                System.out.println("Selected mango");
//                break;
//            default:
//                System.out.println("No fruit selected");
//                break;
//        }
//    }
//}

/*
switch 语句还可以使用枚举类型，枚举类型我们在后面讲解。

编译检查
使用 IDE 时，可以自动检查是否漏写了 break 语句和 default 语句，方法是打开 IDE 的编译检查。

在 Eclipse 中，选择 Preferences - Java - Compiler - Errors/Warnings - Potential programming problems，将以下检查标记为 Warning：

'switch' is missing 'default' case
'switch' case fall-through
在 Idea 中，选择 Preferences - Editor - Inspections - Java - Control flow issues，将以下检查标记为 Warning：

Fallthrough in 'switch' statement
'switch' statement without 'default' branch
当 switch 语句存在问题时，即可在 IDE 中获得警告提示。

不要忘记break
不要忘记default

switch 表达式
使用 switch 时，如果遗漏了 break，就会造成严重的逻辑错误，
而且不易在源代码中发现错误。从 Java 12 开始，
switch 语句升级为更简洁的表达式语法，使用类似模式匹配
（Pattern Matching）的方法，保证只有一种路径会被执行，
并且不需要 break 语句：
 */

//public class A3SwitchStatement {
//    public static void main(String[] args) {
//        String fruit = "apple";
//        switch (fruit) {
//            case "apple" -> System.out.println("Selected apple");
//            case "pear" -> System.out.println("Selected pear");
//            case "mango" -> {
//                System.out.println("Selected mango");
//                System.out.println("Good choice!");
//            }
//            default -> System.out.println("No fruit selected");
//        }
//    }
//}

//注意新语法使用 ->，如果有多条语句，需要用 {} 括起来。
// 不要写 break 语句，因为新语法只会执行匹配的语句，
// 没有穿透效应。
//
//很多时候，我们还可能用 switch 语句给某个变量赋值。例如：

/*
int opt;
switch (fruit) {
case "apple":
    opt = 1;
    break;
case "pear":
case "mango":
    opt = 2;
    break;
default:
    opt = 0;
    break;
}
*/
//使用新的 switch 语法，不但不需要 break，
// 还可以直接返回值。把上面的代码改写如下：

//public class A3SwitchStatement {
//    public static void main(String[] args) {
//        String fruit = "apple";
//        int opt = switch (fruit) {
//            case "apple" -> 1;
//            case "pear", "mango" -> 2;
//            default -> 0;
//        }; // 注意赋值语句要以;结束
//        System.out.println("opt = " + opt);
//    }
//}

//这样可以获得更简洁的代码。
//
//yield
//大多数时候，在 switch 表达式内部，我们会返回简单的值。
//
//但是，如果需要复杂的语句，我们也可以写很多语句，
// 放到 {...} 里，然后，用 yield 返回一个值作为 switch 语句的返回值：

//public class A3SwitchStatement {
//    public static void main(String[] args) {
//        String fruit = "orange";
//        int opt = switch (fruit) {
//            case "apple" -> 1;
//            case "pear", "mango" -> 2;
//            default -> {
//                int code = fruit.hashCode();
//                yield code; // switch语句返回值
//            }
//        };
//        System.out.println("opt = " + opt);
//    }
//}

//练习
//使用 switch 实现一个简单的石头、剪子、布游戏。

public class A3SwitchStatement {
    public static void main(String[] args) {
        var in=new Scanner(System.in);
        while(true) {
            System.out.println("please choice:");
            System.out.println(" 1: Rock");
            System.out.println(" 2: Scissors");
            System.out.println(" 3: Paper");
// 用户输入:
            int choice = in.nextInt();
// 计算机随机数 1, 2, 3:
            int random = 1 + (int) Math.random() * 3;
            System.out.println("You select "+select(choice));
            System.out.println("PC select "+select(random));
            if(choice==random) {
                System.out.println("Draw");
            }else if(choice<random) {
                System.out.println("PC Lose");
            }else if(choice>random) {
                System.out.println("You Win");
            }
            System.out.println("game go on？");
            System.out.println(" 1: Yes");
            System.out.println(" 2: No");
            choice = in.nextInt();
            if(choice==2) {
                System.out.println("Game exit");
                break;
            }else {
                System.out.println("Game go on");
            }
        }
        in.close();
    }
    public static String select(int n) {
        String a="";
        switch (n) {
            case 1 -> a = "Rock";
            case 2 -> a = "Scissors";
            case 3 -> a = "Paper";
            default -> a = "No Select";
        }
        return a;
    }
}

/*
小结
switch 语句可以做多重选择，然后执行匹配的 case 语句后续代码；

switch 的计算结果必须是整型、字符串或枚举类型；

注意千万不要漏写 break，建议打开 fall-through 警告；

总是写上 default，建议打开 missing default 警告；

从 Java 14 开始，switch 语句正式升级为表达式，
不再需要 break，并且允许使用 yield 返回值。
 */