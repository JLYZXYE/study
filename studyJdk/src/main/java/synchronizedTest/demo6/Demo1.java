package synchronizedTest.demo6;

import org.openjdk.jol.info.ClassLayout;

public class Demo1 {
    public static void main(String[] args) {

        LockObj lockObj = new LockObj();
//        System.out.println(lockObj.hashCode());
//        System.out.println(Integer.toHexString(lockObj.hashCode()));
        System.out.println(ClassLayout.parseInstance(lockObj).toPrintable());
    }
}
