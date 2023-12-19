import domain.*;
import org.hibernate.validator.internal.engine.DefaultClockProvider;
import org.hibernate.validator.internal.engine.DefaultParameterNameProvider;
import org.hibernate.validator.internal.engine.ValidatorContextImpl;
import org.hibernate.validator.internal.engine.ValidatorFactoryImpl;
import org.junit.Test;
import service.PersonService;
import utils.ValidatorUtil;
import valueExtractor.ResultValueExtractor;

import javax.validation.ConstraintViolation;
import javax.validation.ParameterNameProvider;
import javax.validation.Validator;
import javax.validation.ValidatorContext;
import javax.validation.constraints.NotNull;
import javax.validation.metadata.*;
import java.util.*;

public class TestValidator {

    @Test
    public void test1() {
        Person person = new Person();
        person.setAge(-1);
        Set<ConstraintViolation<Person>> result = ValidatorUtil.obtainValidator().validate(person);
        ValidatorUtil.printViolations(result);
    }

    @Test
    public void testInterface() throws NoSuchMethodException {
        // save.arg0 不能为null: null
        // new PersonService().save(null);
        new PersonService().save(new Person());
    }

    @Test
    public void testScriptAssert() {
        User user = new User();
        user.setName("YourBatman");

        Set<ConstraintViolation<User>> result = ValidatorUtil.obtainValidator().validate(user);
        ValidatorUtil.printViolations(result);
    }

    /**
     * 校验属性上的「所有」约束，注意只是属性上的
     */
    @Test
    public void testtestScriptAssert2() {
        User user = new User();
        user.setFullName("YourBatman");

        Set<ConstraintViolation<User>> result = ValidatorUtil.obtainValidator().validateProperty(user, "fullName");
        ValidatorUtil.printViolations(result);
    }

    @Test
    public void testValidateValue() {
        Set<ConstraintViolation<User>> result = ValidatorUtil.obtainValidator().validateValue(User.class, "fullName", "A哥");
        ValidatorUtil.printViolations(result);
    }

    @Test
    public void testBeanDescriptor() {
        BeanDescriptor beanDescriptor = ValidatorUtil.obtainValidator().getConstraintsForClass(User.class);
        System.out.println("此类是否需要校验：" + beanDescriptor.isBeanConstrained());

        // 获取属性、方法、构造器的约束
        Set<PropertyDescriptor> constrainedProperties = beanDescriptor.getConstrainedProperties();
        Set<MethodDescriptor> constrainedMethods = beanDescriptor.getConstrainedMethods(MethodType.GETTER);
        Set<ConstructorDescriptor> constrainedConstructors = beanDescriptor.getConstrainedConstructors();
        System.out.println("需要校验的属性：" + constrainedProperties);
        System.out.println("需要校验的方法：" + constrainedMethods);
        System.out.println("需要校验的构造器：" + constrainedConstructors);

        PropertyDescriptor fullNameDesc = beanDescriptor.getConstraintsForProperty("fullName");
        System.out.println(fullNameDesc);
        System.out.println("fullName属性的约束注解个数：" + fullNameDesc.getConstraintDescriptors().size());
    }


    @Test
    public void testValidatorContext() {
        ValidatorFactoryImpl validatorFactory = (ValidatorFactoryImpl) ValidatorUtil.obtainValidatorFactory();
        // 使用默认的Context上下文，并且初始化一个Validator实例
        // 必须传入一个校验器工厂实例哦
        ValidatorContext validatorContext = new ValidatorContextImpl(validatorFactory)
                .parameterNameProvider(new DefaultParameterNameProvider())
                .clockProvider(DefaultClockProvider.INSTANCE);

        // 通过该上下文，生成校验器实例（注意：调用多次，生成实例是多个哟）
        System.out.println(validatorContext.getValidator());
    }

    @Test
    public void testParameterNameProvider() {
        ParameterNameProvider parameterNameProvider = new DefaultParameterNameProvider();

        // 拿到Person的无参构造和有参构造（@NoArgsConstructor和@AllArgsConstructor）
        Arrays.stream(Person.class.getConstructors()).forEach(c -> System.out.println(parameterNameProvider.getParameterNames(c)));
    }


    /**
     * 若一个Bean遵循「Java Bean规范」，那么也可以使用属性约束来代替字段约束
     * 当把约束标注在Property属性上时，将采用属性访问策略来获取要验证的值。说白了：会调用你的Method来获取待校验的值
     * 约束放在get方法上「优于」放在set方法上，这样只读属性（没有get方法）依然可以执行约束逻辑
     * 不要在「属性和字段」上都标注注解，否则会重复执行约束逻辑（有多少个注解就执行多少次）
     * 不要既在属性的get方法上又在set方法上标注约束注解
     *
     * 字段和属性的区别
     * 字段具有存储功能：字段是类的一个成员，值在内存中真实存在；而属性它不具有存储功能，属于Java Bean规范抽象出来的一个叫法
     * 字段一般用于类「内部」（一般是private），而属性可供外部访问（get/set一般是public）
     * 这指的是一般情况下的规律
     * 字段的本质是Field，属性的本质是Method
     * 属性并「不依赖于」字段而存在，只是他们一般都成双成对出现
     * 如getClass()你可认为它有名为class的属性，但是它并没有名为class的字段
     */
    @Test
    public void testProperty() {
        Room bean = new Room();
        bean.finished = false;
        ValidatorUtil.printViolations(ValidatorUtil.obtainValidator().validate(bean));
    }

    /**
     * 也就是说并没有对rooms立面的元素进行验证。这里有一个误区：Bean Validator是基于「Java Bean」进行验证的，而此处你的rooms仅仅只是一个容器类型的变量而已，因此不会验证。
     */
    @Test
    public void testListContainer() {
        List<@NotNull Room> rooms = new ArrayList<>();
        rooms.add(null);
        rooms.add(new Room());

        Room room = new Room();
        room.name = "YourBatman";
        rooms.add(room);

        ValidatorUtil.printViolations(ValidatorUtil.obtainValidator().validate(rooms));
    }

    @Test
    public void testListContainer2() {
        List<@NotNull Room> beans = new ArrayList<>();
        beans.add(null);
        beans.add(new Room());

        Room room = new Room();
        room.name = "YourBatman";
        beans.add(room);

        // 必须基于Java Bean，验证才会生效
        Rooms rooms = new Rooms(beans);
        ValidatorUtil.printViolations(ValidatorUtil.obtainValidator().validate(rooms));
    }

    @Test
    public void testResultValueExtractor(){
        Room room = new Room();
        room.name = "YourBatman";
        Result<Room> result = new Result<>();
        result.setData(room);

        // 注册自定义的值提取器
        Validator validator = ValidatorUtil.obtainValidatorFactory()
                .usingContext()
                .addValueExtractor(new ResultValueExtractor())
                .getValidator();
        ValidatorUtil.printViolations(validator.validate(result));
    }

    @Test
    public void testScriptAssert3(){
        RoomNum room = new RoomNum();
        ValidatorUtil.printViolations(ValidatorUtil.obtainValidator().validate(room));
    }

    @Test
    public void testConstraintValidator(){
        RoomNum room = new RoomNum();
        room.setStudentNames(Collections.singletonList("YourBatman"));

        ValidatorUtil.printViolations(ValidatorUtil.obtainValidator().validate(room));
    }





}
