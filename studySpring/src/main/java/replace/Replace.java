package replace;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class Replace implements MethodReplacer {
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("Replace");
        return obj;
    }
}
