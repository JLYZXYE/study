package function;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        Map<Integer,ObjectFactory> map = new HashMap<>();
        /**
         * 存入lambda表达式接口
         */
        map.put(1,(()->print()));


        /**
         * 执行print方法
         * spring第三极缓存时使用
         */
        map.get(1).getObject();
    }

    static Object print(){
        System.out.println("print");
        return null;
    }
}
