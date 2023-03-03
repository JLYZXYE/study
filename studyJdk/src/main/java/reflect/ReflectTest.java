package reflect;

import java.lang.reflect.*;

public class ReflectTest {

    public String a;
    protected String b;
    private String c;

    public String filedName;

    public String getFiledName() {
        return filedName;
    }



    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        Class<?> aClass = contextClassLoader.loadClass("reflect.ReflectTest");
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor((Class<?>[]) null);
        ReflectTest test = (ReflectTest)declaredConstructor.newInstance();
        System.out.println("构造："+test);

        Field[] declaredFields = ReflectTest.class.getDeclaredFields();
        Method[] methods = ReflectTest.class.getMethods();
        for (Field declaredField : declaredFields) {
            System.out.println("modifiers:"+ Modifier.toString(declaredField.getModifiers()));
            System.out.println("fieldName:"+declaredField.getName());
        }

        for (Method method : methods) {
            System.out.println("method:"+method.getName());
        }
    }
}
