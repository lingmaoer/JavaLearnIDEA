import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * @author Lingmao
 * @creat 2021-10-13  19:27
 */
//https://www.liaoxuefeng.com/wiki/1252599548343744/1265102026065728

/*
Java 的注解本身对代码逻辑没有任何影响。根据 @Retention 的配置：

SOURCE 类型的注解在编译期就被丢掉了；
CLASS 类型的注解仅保存在 class 文件中，它们不会被加载进 JVM；
RUNTIME 类型的注解会被加载进 JVM，并且在运行期可以被程序读取。
如何使用注解完全由工具决定。SOURCE 类型的注解主要由编译器使用，因此我们一般只使用，不编写。CLASS 类型的注解主要由底层工具库使用，涉及到 class 的加载，一般我们很少用到。只有 RUNTIME 类型的注解不但要使用，还经常需要编写。

因此，我们只讨论如何读取 RUNTIME 类型的注解。

因为注解定义后也是一种 class，所有的注解都继承自 java.lang.annotation.Annotation，因此，读取注解，需要使用反射 API。

Java 提供的使用反射 API 读取 Annotation 的方法包括：

判断某个注解是否存在于 Class、Field、Method 或 Constructor：

Class.isAnnotationPresent(Class)
Field.isAnnotationPresent(Class)
Method.isAnnotationPresent(Class)
Constructor.isAnnotationPresent(Class)
例如：

// 判断@Report是否存在于Person类:
Person.class.isAnnotationPresent(Report.class);
使用反射 API 读取 Annotation：

Class.getAnnotation(Class)
Field.getAnnotation(Class)
Method.getAnnotation(Class)
Constructor.getAnnotation(Class)
例如：

// 获取Person定义的@Report注解:
Report report = Person.class.getAnnotation(Report.class);
int type = report.type();
String level = report.level();
使用反射 API 读取 Annotation 有两种方法。方法一是先判断 Annotation 是否存在，如果存在，就直接读取：

Class cls = Person.class;
if (cls.isAnnotationPresent(Report.class)) {
    Report report = cls.getAnnotation(Report.class);
    ...
}
第二种方法是直接读取 Annotation，如果 Annotation 不存在，将返回 null：

Class cls = Person.class;
Report report = cls.getAnnotation(Report.class);
if (report != null) {
   ...
}
读取方法、字段和构造方法的 Annotation 和 Class 类似。但要读取方法参数的 Annotation 就比较麻烦一点，因为方法参数本身可以看成一个数组，而每个参数又可以定义多个注解，所以，一次获取方法参数的所有注解就必须用一个二维数组来表示。例如，对于以下方法定义的注解：

public void hello(@NotNull @Range(max=5) String name, @NotNull String prefix) {
}
要读取方法参数的注解，我们先用反射获取 Method 实例，然后读取方法参数的所有注解：

// 获取Method实例:
Method m = ...
// 获取所有参数的Annotation:
Annotation[][] annos = m.getParameterAnnotations();
// 第一个参数（索引为0）的所有Annotation:
Annotation[] annosOfName = annos[0];
for (Annotation anno : annosOfName) {
    if (anno instanceof Range) { // @Range注解
        Range r = (Range) anno;
    }
    if (anno instanceof NotNull) { // @NotNull注解
        NotNull n = (NotNull) anno;
    }
}
使用注解
注解如何使用，完全由程序自己决定。例如，JUnit 是一个测试框架，它会自动运行所有标记为 @Test 的方法。

我们来看一个 @Range 注解，我们希望用它来定义一个 String 字段的规则：字段长度满足 @Range 的参数定义：

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Range {
    int min() default 0;
    int max() default 255;
}
在某个 JavaBean 中，我们可以使用该注解：

public class Person {
    @Range(min=1, max=20)
    public String name;

    @Range(max=10)
    public String city;
}
但是，定义了注解，本身对程序逻辑没有任何影响。我们必须自己编写代码来使用注解。这里，我们编写一个 Person 实例的检查方法，它可以检查 Person 实例的 String 字段长度是否满足 @Range 的定义：

void check(Person person) throws IllegalArgumentException, ReflectiveOperationException {
    // 遍历所有Field:
    for (Field field : person.getClass().getFields()) {
        // 获取Field定义的@Range:
        Range range = field.getAnnotation(Range.class);
        // 如果@Range存在:
        if (range != null) {
            // 获取Field的值:
            Object value = field.get(person);
            // 如果值是String:
            if (value instanceof String) {
                String s = (String) value;
                // 判断值是否满足@Range的min/max:
                if (s.length() < range.min() || s.length() > range.max()) {
                    throw new IllegalArgumentException("Invalid field: " + field.getName());
                }
            }
        }
    }
}
这样一来，我们通过 @Range 注解，配合 check() 方法，就可以完成 Person 实例的检查。注意检查逻辑完全是我们自己编写的，JVM 不会自动给注解添加任何额外的逻辑。
*/

//练习
//使用 @Range 注解来检查 Java Bean 的字段。如果字段类型是 String，就检查 String 的长度，如果字段是 int，就检查 int 的范围。



/*
小结
可以在运行期通过反射读取 RUNTIME 类型的注解，注意千万不要漏写 @Retention(RetentionPolicy.RUNTIME)，否则运行期无法读取到该注解。

可以通过程序处理注解来实现相应的功能：

对 JavaBean 的属性值按规则进行检查；
JUnit 会自动运行 @Test 标记的测试方法。
 */
public class A3DealAnnotations {
    public static void main(String[] args) throws Exception {
        Person p1 = new Person("Bob", "Beijing", 20);
        Person p2 = new Person("", "Shanghai", 20);
        Person p3 = new Person("Alice", "Shanghai", 199);
        for (Person p : new Person[] { p1, p2, p3 }) {
            try {
                check(p);
                System.out.println("Person " + p + " checked ok.");
            } catch (IllegalArgumentException e) {
                System.out.println("Person " + p + " checked failed: " + e);
            }
        }
    }

    static void check(Person person) throws IllegalArgumentException, ReflectiveOperationException {
        for (Field field : person.getClass().getFields()) {
            Range range = field.getAnnotation(Range.class);
            if (range != null) {
                Object value = field.get(person);
                // TODO:
                if(value instanceof String s){
                    if(s.length()<range.min()||s.length()>range.max()){
                        throw new IllegalArgumentException("Invalid:"+field.getName());
                    }
                }
                else if(value instanceof Integer n){
                    if (n< range.min()||n> range.max()){
                        throw new IllegalArgumentException("Invalid:"+ field.getName());
                    }

                }

            }
        }
    }
}

class Person {

    @Range(min = 1, max = 20)
    public String name;

    @Range(max = 10)
    public String city;

    @Range(min = 1, max = 100)
    public int age;

    public Person(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("{Person: name=%s, city=%s, age=%d}", name, city, age);
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Range {

    int min() default 0;

    int max() default 255;

}

