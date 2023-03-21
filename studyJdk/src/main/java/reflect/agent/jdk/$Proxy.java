package reflect.agent.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

public class $Proxy extends Proxy implements JdkTest.Person {

    public $Proxy(InvocationHandler h) {
        super(h);
    }

    @Override
    public void eat() {
        try {
            h.invoke(this, eat, null);
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public int sleep() {
        try {
            return (int) h.invoke(this, sleep, null);
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    static Method eat;
    static Method sleep;

    static {
        try {
            eat = JdkTest.Person.class.getDeclaredMethod("eat", null);
            sleep = JdkTest.Person.class.getDeclaredMethod("sleep", null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }
}
