package convert.formatter;

import java.text.*;
import java.util.Date;

public class JDKFormatterTest {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Date curr = new Date();

        // 格式化日期 + 时间
        System.out.println(DateFormat.getInstance().getClass() + "-->" + DateFormat.getInstance().format(curr));
        System.out.println(DateFormat.getDateTimeInstance().getClass() + "-->" + DateFormat.getDateTimeInstance().format(curr));

        // 格式化日期
        System.out.println(DateFormat.getDateInstance().getClass() + "-->" + DateFormat.getDateInstance().format(curr));

        // 格式化时间
        System.out.println(DateFormat.getTimeInstance().getClass() + "-->" + DateFormat.getTimeInstance().format(curr));
    }

    public void test2() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // yyyy-MM-dd HH:mm:ss
        System.out.println(dateFormat.format(new Date()));
    }

    public void test41() {
        double myNum = 1220.0455;

        System.out.println(NumberFormat.getInstance().getClass() + "-->" + NumberFormat.getInstance().format(myNum));
        System.out.println(NumberFormat.getCurrencyInstance().getClass() + "-->" + NumberFormat.getCurrencyInstance().format(myNum));
        System.out.println(NumberFormat.getIntegerInstance().getClass() + "-->" + NumberFormat.getIntegerInstance().format(myNum));
        System.out.println(NumberFormat.getNumberInstance().getClass() + "-->" + NumberFormat.getNumberInstance().format(myNum));
        System.out.println(NumberFormat.getPercentInstance().getClass() + "-->" + NumberFormat.getPercentInstance().format(myNum));
    }

    public void test4() {
        double myNum = 1220.0455;

        System.out.println("===============0的使用===============");
        System.out.println("只保留整数部分：" + new DecimalFormat("0").format(myNum));
        System.out.println("保留3位小数：" + new DecimalFormat("0.000").format(myNum));
        System.out.println("整数部分、小数部分都5位。不够的都用0补位(整数高位部，小数低位补)：" + new DecimalFormat("00000.00000").format(myNum));

        System.out.println("===============#的使用===============");
        System.out.println("只保留整数部分：" + new DecimalFormat("#").format(myNum));
        System.out.println("保留2为小数并以百分比输出：" + new DecimalFormat("#.##%").format(myNum));

        // 非标准数字（不建议这么用）
        System.out.println("===============非标准数字的使用===============");
        System.out.println(new DecimalFormat("666").format(myNum));
        System.out.println(new DecimalFormat(".6666").format(myNum));
    }

    public void test5() {
        double myNum = 1220.0455;

        System.out.println(new DecimalFormat("0E0").format(myNum));
        System.out.println(new DecimalFormat("0E00").format(myNum));
        System.out.println(new DecimalFormat("00000E00000").format(myNum));
        System.out.println(new DecimalFormat("#E0").format(myNum));
        System.out.println(new DecimalFormat("#E00").format(myNum));
        System.out.println(new DecimalFormat("#####E00000").format(myNum));
    }

    public void test6() {
        double myNum = 1220.0455;

        System.out.println(new DecimalFormat(",###").format(myNum));
        System.out.println(new DecimalFormat(",##").format(myNum));
        System.out.println(new DecimalFormat(",##").format(123456789));

        // 分隔符,左边是无效的
        System.out.println(new DecimalFormat("###,##").format(myNum));
    }

    public void test42() {
        double myNum = 1220.0455;

        System.out.println("百分位表示：" + new DecimalFormat("#.##%").format(myNum));
        System.out.println("千分位表示：" + new DecimalFormat("#.##\u2030").format(myNum));
    }

    public void test7() {
        double myNum = 1220.0455;

        System.out.println(new DecimalFormat(",000.00¤").format(myNum));
        System.out.println(new DecimalFormat(",000.¤00").format(myNum));
        System.out.println(new DecimalFormat("¤,000.00").format(myNum));
        System.out.println(new DecimalFormat("¤,000.¤00").format(myNum));
        // 世界货币表达形式
        System.out.println(new DecimalFormat(",000.00¤¤").format(myNum));
    }

    public void test8() {
        double[] limits = {1, 2, 3, 4, 5, 6, 7};
        String[] formats = {"周一", "周二", "周三", "周四", "周五", "周六", "周天"};
        NumberFormat numberFormat = new ChoiceFormat(limits, formats);

        System.out.println(numberFormat.format(1));
        System.out.println(numberFormat.format(4.3));
        System.out.println(numberFormat.format(5.8));
        System.out.println(numberFormat.format(9.1));
        System.out.println(numberFormat.format(11));
    }

    public void test9() {
        String sourceStrPattern = "Hello {0},my name is {1}";
        Object[] args = new Object[]{"girl", "YourBatman"};

        String formatedStr = MessageFormat.format(sourceStrPattern, args);
        System.out.println(formatedStr);
    }

    public void test10() {
        MessageFormat messageFormat = new MessageFormat("Hello, my name is {0}. I’am {1,number,#.##} years old. Today is {2,date,yyyy-MM-dd HH:mm:ss}");
        // 亦可通过编程式 显示指定某个位置要使用的格式化器
        // messageFormat.setFormatByArgumentIndex(1, new DecimalFormat("#.###"));

        System.out.println(messageFormat.format(new Object[]{"YourBatman", 24.123456, new Date()}));
    }

    public void test11() {
        System.out.println(MessageFormat.format("{1} - {1}", new Object[]{1})); // {1} - {1}
        System.out.println(MessageFormat.format("{0} - {1}", new Object[]{1})); // 输出：1 - {1}
        System.out.println(MessageFormat.format("{0} - {1}", new Object[]{1, 2, 3})); // 输出：1 - 2

        System.out.println("---------------------------------");

        System.out.println(MessageFormat.format("'{0} - {1}", new Object[]{1, 2})); // 输出：{0} - {1}
        System.out.println(MessageFormat.format("''{0} - {1}", new Object[]{1, 2})); // 输出：'1 - 2
        System.out.println(MessageFormat.format("'{0}' - {1}", new Object[]{1, 2})); // {0} - 2
        // 若你数据库值两边都需要''包起来，请你这么写
        System.out.println(MessageFormat.format("''{0}'' - {1}", new Object[]{1, 2})); // '1' - 2

        System.out.println("---------------------------------");
        System.out.println(MessageFormat.format("0} - {1}", new Object[]{1, 2})); // 0} - 2
        System.out.println(MessageFormat.format("{0 - {1}", new Object[]{1, 2})); // java.lang.IllegalArgumentException: Unmatched braces in the pattern.
    }
}
