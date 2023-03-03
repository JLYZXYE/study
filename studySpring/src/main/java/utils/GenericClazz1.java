package utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class GenericClazz1 {

    private HashMap<String, List<Integer>> param;

    public static void main(String[] args) throws NoSuchFieldException {
        Field param = GenericClazz1.class.getDeclaredField("param");
        Type genericType = param.getGenericType();
        ParameterizedType type = (ParameterizedType) genericType;
        Type[] typeArguments = type.getActualTypeArguments();
        System.out.println("从 HashMap<String, List<Integer>> 中获取 String:" + typeArguments[0]);
        System.out.println("从 HashMap<String, List<Integer>> 中获取 List<Integer> :" + typeArguments[1]);
        System.out.println(
                "从 HashMap<String, List<Integer>> 中获取 List :" + ((ParameterizedType) typeArguments[1]).getRawType());
        System.out.println("从 HashMap<String, List<Integer>> 中获取 Integer:" + ((ParameterizedType) typeArguments[1])
                .getActualTypeArguments()[0]);
        System.out.println("从 HashMap<String, List<Integer>> 中获取父类型:"+param.getType().getGenericSuperclass());
    }


}
