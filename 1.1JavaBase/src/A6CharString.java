/**
 * @author Lingmao
 * @creat 2021-10-04  22:12
 */
/*
 * 字符类型
字符类型 char 是基本数据类型，它是 character 的缩写。一个 char 保存一个 Unicode 字符：

char c1 = 'A';
char c2 = '中';

因为 Java 在内存中总是使用 Unicode 表示字符，
所以，一个英文字符和一个中文字符都用一个 char 类型表示，
它们都占用两个字节。要显示一个字符的 Unicode 编码，
只需将 char 类型直接赋值给 int 类型即可：
int n1 = 'A'; // 字母“A”的Unicodde编码是65
int n2 = '中'; // 汉字“中”的Unicode编码是20013

还可以直接用转义字符 (只有一个\,写一个会报错)\\u + Unicode 编码来表示一个字符：

// 注意是十六进制:
char c3 = '\u0041'; // 'A'，因为十六进制0041 = 十进制65
char c4 = '\u4e2d'; // '中'，因为十六进制4e2d = 十进制20013

字符串类型
和 char 类型不同，字符串类型 String 是引用类型，
我们用双引号 "..." 表示字符串。一个字符串可以存储 0 个到任意个字符：

String s = ""; // 空字符串，包含0个字符
String s1 = "A"; // 包含一个字符
String s2 = "ABC"; // 包含3个字符
String s3 = "中文 ABC"; // 包含6个字符，其中有一个空格

因为字符串使用双引号 "..." 表示开始和结束，
那如果字符串本身恰好包含一个 " 字符怎么表示？
例如，"abc"xyz"，编译器就无法判断中间的引号究竟是字符串的一
部分还是表示字符串结束。这个时候，我们需要借助转义字符 \：

String s = "abc\"xyz"; // 包含7个字符: a, b, c, ", x, y, z

因为 \ 是转义字符，所以，两个 \\ 表示一个 \ 字符：

String s = "abc\\xyz"; // 包含7个字符: a, b, c, \, x, y, z

***常见的转义字符包括：

\" 表示字符 "
\' 表示字符'
\\ 表示字符 \
\n 表示换行符
\r 表示回车符
\t 表示 Tab
\\u#### 表示一个 Unicode 编码的字符(只有一个\,写一个会报错)

例如：

String s = "ABC\n\u4e2d\u6587"; // 包含6个字符: A, B, C, 换行符, 中, 文
 */

/*
 * 字符串连接
Java 的编译器对字符串做了特殊照顾，
可以使用 + 连接任意字符串和其他数据类型，
这样极大地方便了字符串的处理。例如：
 */
// 字符串连接
//public class A6CharString {
//    public static void main(String[] args) {
//        String s1 = "Hello";
//        String s2 = "world";
//        String s = s1 + " " + s2 + "!";
//        System.out.println(s);
//    }
//}

//如果用 + 连接字符串和其他数据类型，会将其他数据类型先自动转型为字符串，再连接：

//字符串连接
//public class A6CharString {
//    public static void main(String[] args) {
//        int age = 25;
//        String s = "age is " + age;
//        System.out.println(s);
//    }
//}

/*
  多行字符串
如果我们要表示多行字符串，使用 + 号连接会非常不方便：

String s = "first line \n"
         + "second line \n"
         + "end";
从 Java 13 开始，字符串可以用 """...""" 表示多行字符串（Text Blocks）了。举个例子：
*/

// 多行字符串
//public class A6CharString {
//    public static void main(String[] args) {
//        String s = """
//                SELECT * FROM
//                  users
//                WHERE id > 100
//                ORDER BY name DESC
//                """;
//     System.out.println(s);
//    }
//}

/*
上述多行字符串实际上是 5 行，在最后一个 DESC 后面还有一个 \n。
如果我们不想在字符串末尾加一个 \n，就需要这么写：

String s = """
           SELECT * FROM
             users
           WHERE id > 100
           ORDER BY name DESC""";


还需要注意到，多行字符串前面共同的空格会被去掉，即：

String s = """
...........SELECT * FROM
...........  users
...........WHERE id > 100
...........ORDER BY name DESC
...........""";
用. 标注的空格都会被去掉。


如果多行字符串的排版不规则，那么，去掉的空格就会变成这样：

String s = """
.........  SELECT * FROM
.........    users
.........WHERE id > 100
.........  ORDER BY name DESC
.........  """;

即总是以最短的行首空格为基准。
*/

///不可变特性
//Java 的字符串除了是一个引用类型外，
//还有个重要特点，就是字符串不可变。考察以下代码：

// 字符串不可变
//public class A6CharString {
//    public static void main(String[] args) {
//        String s = "hello";
//        System.out.println(s); // 显示 hello
//        s = "world";
//        System.out.println(s); // 显示 world
//    }
//}

/*
 * 观察执行结果，难道字符串 s 变了吗？其实变的不是字符串，而是变量 s 的 “指向”。

执行 String s = "hello"; 时，JVM 虚拟机先创建字符串 "hello"，然后，把字符串变量 s 指向它：

          s
          │
          ▼
┌───┬───────────┬───┐
│   │  "hello"  │   │
└───┴───────────┴───┘
紧接着，执行 s = "world"; 时，JVM 虚拟机先创建字符串 "world"，然后，把字符串变量 s 指向它：

      s ──────────────────┐
                          │
                          ▼
┌───┬───────────┬───┬───────────┬───┐
│   │  "hello"  │   │  "world"  │   │
└───┴───────────┴───┴───────────┴───┘
原来的字符串 "hello" 还在，只是我们无法通过变量 s 访问它而已。因此，字符串的不可变是指字符串内容不可变。

理解了引用类型的 “指向” 后，试解释下面的代码输出：
*/


// 字符串不可变
//public class A6CharString {
//    public static void main(String[] args) {
//        String s = "hello";
//        String t = s;
//        s = "world";
//        System.out.println(t); // t是"hello"还是"world"?
//    }
//}
/*
 * 空值 null
引用类型的变量可以指向一个空值 null，它表示不存在，即该变量不指向任何对象。例如：

String s1 = null; // s1是null
String s2; // 没有赋初值值，s2也是null
String s3 = s1; // s3也是null
String s4 = ""; // s4指向空字符串，不是null
注意要区分空值 null 和空字符串 ""，空字符串是一个有效的字符串对象，它不等于 null。

*/


//练习
//请将一组 int 值视为字符的 Unicode 编码，然后将它们拼成一个字符串：

public class A6CharString {
    public static void main(String[] args) {
        // 请将下面一组int值视为字符的Unicode码，把它们拼成一个字符串：
        int a = 72;
        int b = 105;
        int c = 65281;
        // FIXME:
        String s = ""+(char)a + (char)b + (char)c;
        System.out.println(s);

    }
}
