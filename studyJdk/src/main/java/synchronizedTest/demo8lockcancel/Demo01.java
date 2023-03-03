package synchronizedTest.demo8lockcancel;

import org.openjdk.jol.info.ClassLayout;

public class Demo01 {
    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(contactString("aa", "bb", "cc")).toPrintable());
    }

    public static String contactString(String s1, String s2, String s3) {
        return new StringBuffer().append(s1).append(s2).append(s3).toString();
    }
}