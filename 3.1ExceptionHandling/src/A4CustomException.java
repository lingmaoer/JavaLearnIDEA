/**
 * @author Lingmao
 * @creat 2021-10-09  20:30
 */

//来自  https://www.liaoxuefeng.com/wiki/1252599548343744/1264737765214592

/*
Java 标准库定义的常用异常包括：

Exception
│
├─ RuntimeException
│  │
│  ├─ NullPointerException
│  │
│  ├─ IndexOutOfBoundsException
│  │
│  ├─ SecurityException
│  │
│  └─ IllegalArgumentException
│     │
│     └─ NumberFormatException
│
├─ IOException
│  │
│  ├─ UnsupportedCharsetException
│  │
│  ├─ FileNotFoundException
│  │
│  └─ SocketException
│
├─ ParseException
│
├─ GeneralSecurityException
│
├─ SQLException
│
└─ TimeoutException
当我们在代码中需要抛出异常时，尽量使用 JDK 已定义的异常类型。例如，参数检查不合法，应该抛出 IllegalArgumentException：

static void process1(int age) {
    if (age <= 0) {
        throw new IllegalArgumentException();
    }
}
在一个大型项目中，可以自定义新的异常类型，但是，保持一个合理的异常继承体系是非常重要的。

一个常见的做法是自定义一个 BaseException 作为 “根异常”，然后，派生出各种业务类型的异常。

BaseException 需要从一个适合的 Exception 派生，通常建议从 RuntimeException 派生：

public class BaseException extends RuntimeException {
}
其他业务类型的异常就可以从 BaseException 派生：

public class UserNotFoundException extends BaseException {
}

public class LoginFailedException extends BaseException {
}

...
自定义的 BaseException 应该提供多个构造方法：

public class BaseException extends RuntimeException {
    public BaseException() {
        super();
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }
}
上述构造方法实际上都是原样照抄 RuntimeException。这样，抛出异常的时候，就可以选择合适的构造方法。通过 IDE 可以根据父类快速生成子类的构造方法。
*/

//练习
//从 BaseException 派生自定义异常

public class A4CustomException {
    public static void main(String[] args) {
        String token = login("admin", "pass");
        System.out.println("Token: " + token);
    }

    static String login(String username, String password) {
        if (username.equals("admin")) {
            if (password.equals("password")) {
                return "xxxxxx";
            } else {
                // 抛出LoginFailedException:
                throw new LoginFailedException("Bad username or password.");
            }
        } else {
            // 抛出UserNotFoundException:
            throw new UserNotFoundException("User not found.");
        }
    }
}

class BaseException extends RuntimeException {
    public BaseException() {
        super();
    }
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
    public BaseException(String message) {
        super(message);
    }
    public BaseException(Throwable cause) {
        super(cause);
    }
}

class LoginFailedException extends BaseException {
    public LoginFailedException() {
        super();
    }
    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }
    public LoginFailedException(String message) {
        super(message);
    }
    public LoginFailedException(Throwable cause) {
        super(cause);
    }
}

class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super();
    }
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
/*
小结
抛出异常时，尽量复用 JDK 已定义的异常类型；

自定义异常体系时，推荐从 RuntimeException 派生 “根异常”，再派生出业务异常；

自定义异常时，应该提供多种构造方法。
 */