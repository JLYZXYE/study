package reflect.agent.jdk;

public class JdkTest {

    interface Person {
        void eat();

        int sleep();
    }

    static class Man implements Person {

        @Override
        public void eat() {
            System.out.println("eat food");
        }

        @Override
        public int sleep() {
            System.out.println("sleep ten hours");
            return 10;
        }
    }

    public static void main(String[] args) {

        $Proxy $proxy = new $Proxy((proxy, method, args1) -> {
            System.out.println("before...");
            return method.invoke(new Man(), args1);
        });

        $proxy.eat();
        $proxy.sleep();
    }
}
