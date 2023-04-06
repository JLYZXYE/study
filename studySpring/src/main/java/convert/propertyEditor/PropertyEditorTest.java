package convert.propertyEditor;

import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.PropertyEditorRegistrySupport;
import org.springframework.beans.propertyeditors.CharsetEditor;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.UUIDEditor;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceEditor;
import propertyEditorRegistrar.Person;

import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;
import java.util.UUID;


public class PropertyEditorTest {
    public static void main(String[] args) {
        test6();
    }

    public static void test1() {
        PropertyEditor editor = new CustomBooleanEditor(true);
        // 这些都是true，不区分大小写
        editor.setAsText("trUe");
        System.out.println(editor.getAsText());
        editor.setAsText("on");
        System.out.println(editor.getAsText());
        editor.setAsText("yes");
        System.out.println(editor.getAsText());
        editor.setAsText("1");
        System.out.println(editor.getAsText());

        // 这些都是false（注意：null并不会输出为false，而是输出空串）
        editor.setAsText(null);
        System.out.println(editor.getAsText());
        editor.setAsText("fAlse");
        System.out.println(editor.getAsText());
        editor.setAsText("off");
        System.out.println(editor.getAsText());
        editor.setAsText("no");
        System.out.println(editor.getAsText());
        editor.setAsText("0");
        System.out.println(editor.getAsText());

        // 报错
        editor.setAsText("2");
        System.out.println(editor.getAsText());
    }

    public static void test2() {
        // 虽然都行，但建议你规范书写：UTF-8
        PropertyEditor editor = new CharsetEditor();
        editor.setAsText("UtF-8");
        System.out.println(editor.getAsText()); // UTF-8
        editor.setAsText("utF8");
        System.out.println(editor.getAsText()); // UTF-8
    }

    /**
     * 时间/日期转换器很不好用，构造时就必须指定一个SimpleDateFormat格式化器。在实际应用中，Spring并没有使用到它，而是用后面会说到的替代方案
     */
    public static void test3() {
        PropertyEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true);
        editor.setAsText("2020-11-30 09:10:10");
        System.out.println(editor.getAsText()); // 2020-11-30 09:10:10

        // null输出空串
        editor.setAsText(null);
        System.out.println(editor.getAsText());

        // 报错
        editor.setAsText("2020-11-30");
        System.out.println(editor.getAsText());
    }

    public static void test4() {
        // 支持标准URL如file:C:/myfile.txt，也支持classpath:myfile.txt
        // 同时还支持占位符形式
        PropertyEditor editor = new ResourceEditor(new DefaultResourceLoader(), new StandardEnvironment(), true);

        // file:形式本处略
        // editor.setAsText("file:...");
        // System.out.println(editor.getAsText());

        // classpath形式（注意：若文件不存在不会报错，而是输出null）
        editor.setAsText("classpath:test.xml");
        System.out.println(editor.getAsText()); // 输出带盘符的全路径

        System.setProperty("myFile.name", "test.xml");
        editor.setAsText("classpath:${myFile.name}");
        System.out.println(editor.getAsText()); // 结果同上
    }

    public static void test5() {
        PropertyEditorRegistry propertyEditorRegistry = new PropertyEditorRegistrySupport();
        propertyEditorRegistry.registerCustomEditor(Animal.class, new AnimalPropertyEditor());

        // 付类型、子类型均可匹配上对应的编辑器
        PropertyEditor customEditor1 = propertyEditorRegistry.findCustomEditor(Cat.class, null);
        PropertyEditor customEditor2 = propertyEditorRegistry.findCustomEditor(Animal.class, null);
        System.out.println(customEditor1 == customEditor2);
        System.out.println(customEditor1.getClass().getSimpleName());
    }

    public static void test6() {
        PropertyEditorRegistry propertyEditorRegistry = new PropertyEditorRegistrySupport();
        // 通用的
        propertyEditorRegistry.registerCustomEditor(UUID.class, new UUIDEditor());
        // 专用的
        propertyEditorRegistry.registerCustomEditor(Person.class, "cat.uuid", new PersonCatUUIDEditor());


        String uuidStr = "1-2-3-4-5";
        String personCatUuidStr = "1-2-3-4-5_YourBatman";

        PropertyEditor customEditor = propertyEditorRegistry.findCustomEditor(UUID.class, null);
        // customEditor.setAsText(personCatUuidStr); // 抛异常：java.lang.NumberFormatException: For input string: "5_YourBatman"
        customEditor.setAsText(uuidStr);
        System.out.println(customEditor.getAsText());

        customEditor = propertyEditorRegistry.findCustomEditor(Person.class, "cat.uuid");
        customEditor.setAsText(personCatUuidStr);
        System.out.println(customEditor.getAsText());
    }
}
