package utils;

import org.springframework.core.ResolvableType;

import java.util.HashMap;
import java.util.List;

public class GenericClazz2 {

    private HashMap<String, List<Integer>> param;

    public static void main(String[] args) throws NoSuchFieldException {

        ResolvableType param = ResolvableType.forField(GenericClazz2.class.getDeclaredField("param"));
        System.out.println("从 HashMap<String, List<Integer>> 中获取 String:" + param.getGeneric(0).resolve());
        System.out.println("从 HashMap<String, List<Integer>> 中获取 List<Integer> :" + param.getGeneric(1));
        System.out.println(
            "从 HashMap<String, List<Integer>> 中获取 List :" + param.getGeneric(1).resolve());
        System.out.println("从 HashMap<String, List<Integer>> 中获取 Integer:" + param.getGeneric(1,0));
        System.out.println("从 HashMap<String, List<Integer>> 中获取父类型:" +param.getSuperType());
    }
}
