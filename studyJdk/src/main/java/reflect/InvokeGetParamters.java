package reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * JDK1.8反射可以获取方法参数名。
 * 有了这个特性之后，以后咱们编译的时候加上启动参数，使用Mybatis再也不需要那烦人的@Param注解了
 *
 * 要求：JDK版本必须是1.8及以上；编译时候必须有编译选项：javac -parameters打开，默认是关闭的
 *
 * idea设置保留参数名：
 * 在 preferences-》Java Compiler->设置模块字节码版本1.8，Javac Options中的 Additional command line parameters: -parameters
 */
public class InvokeGetParamters {
    public static void main(String[] args) {
        List<String> paramterNames = getParameterNameJava8(InvokeGetParamters.class, "fun1");
        paramterNames.forEach((x) -> System.out.println(x));
    }

    public static void fun1(String aaa, Integer bbb) {

    }

    public static List<String> getParameterNameJava8(Class clazz, String methodName) {
        List<String> paramterList = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                //直接通过method就能拿到所有的参数
                Parameter[] params = method.getParameters();
                for (Parameter parameter : params) {
                    paramterList.add(parameter.getName());
                }

            }
        }

        return paramterList;
    }

}
