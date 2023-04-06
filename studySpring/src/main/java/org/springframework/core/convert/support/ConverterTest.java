package org.springframework.core.convert.support;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.support.ObjectToObject.Customer;
import org.springframework.core.convert.support.ObjectToObject.Person;

import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Stream;

public class ConverterTest {

    public static void main(String[] args) {
        test8();
    }

    public void test() {
        System.out.println("----------------StringToBooleanConverter---------------");
        Converter<String, Boolean> converter = new StringToBooleanConverter();

        // trueValues.add("true");
        // trueValues.add("on");
        // trueValues.add("yes");
        // trueValues.add("1");
        System.out.println(converter.convert("true"));
        System.out.println(converter.convert("1"));

        // falseValues.add("false");
        // falseValues.add("off");
        // falseValues.add("no");
        // falseValues.add("0");
        System.out.println(converter.convert("FalSe"));
        System.out.println(converter.convert("off"));
        // 注意：空串返回的是null
        System.out.println(converter.convert(""));


        System.out.println("----------------StringToCharsetConverter---------------");
        Converter<String, Charset> converter2 = new StringToCharsetConverter();
        // 中间横杠非必须，但强烈建议写上   不区分大小写
        System.out.println(converter2.convert("uTf-8"));
        System.out.println(converter2.convert("utF8"));
    }

    public void test2() {
        System.out.println("----------------StringToNumberConverterFactory---------------");
        ConverterFactory<String, Number> converterFactory = new StringToNumberConverterFactory();
        // 注意：这里不能写基本数据类型。如int.class将抛错
        System.out.println(converterFactory.getConverter(Integer.class).convert("1").getClass());
        System.out.println(converterFactory.getConverter(Double.class).convert("1.1").getClass());
        System.out.println(converterFactory.getConverter(Byte.class).convert("0x11").getClass());
    }

    public static void test3() {
        System.out.println("----------------CollectionToCollectionConverter---------------");
        ConditionalGenericConverter conditionalGenericConverter = new CollectionToCollectionConverter(new DefaultConversionService());
        // 将Collection转为Collection（注意：没有指定泛型类型哦）
        System.out.println(conditionalGenericConverter.getConvertibleTypes());

        List<String> sourceList = Arrays.asList("1", "2", "2", "3", "4");
        TypeDescriptor sourceTypeDesp = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(String.class));
        TypeDescriptor targetTypeDesp = TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(Integer.class));

        System.out.println(conditionalGenericConverter.matches(sourceTypeDesp, targetTypeDesp));
        Object convert = conditionalGenericConverter.convert(sourceList, sourceTypeDesp, targetTypeDesp);
        System.out.println(convert.getClass());
        System.out.println(convert);
    }

    public static void test4() {
        System.out.println("----------------StreamConverter---------------");
        ConditionalGenericConverter converter = new StreamConverter(new DefaultConversionService());

        TypeDescriptor sourceTypeDesp = TypeDescriptor.valueOf(Set.class);
        TypeDescriptor targetTypeDesp = TypeDescriptor.valueOf(Stream.class);
        boolean matches = converter.matches(sourceTypeDesp, targetTypeDesp);
        System.out.println("是否能够转换：" + matches);

        // 执行转换
        Object convert = converter.convert(Collections.singleton(1), sourceTypeDesp, targetTypeDesp);
        System.out.println(convert);
        System.out.println(Stream.class.isAssignableFrom(convert.getClass()));
    }

    public static void test5() {
        System.out.println("----------------StreamConverter使用场景---------------");
        ConversionService conversionService = new DefaultConversionService();
        Stream<Integer> result = conversionService.convert(Collections.singleton(1), Stream.class);

        // 消费
        result.forEach(System.out::println);
        // result.forEach(System.out::println); //stream has already been operated upon or closed
    }

    public static void test6() {
        System.out.println("----------------ObjectToObjectConverter---------------");
        ConditionalGenericConverter converter = new ObjectToObjectConverter();

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setAddress("Peking");

        Object convert = converter.convert(customer, TypeDescriptor.forObject(customer), TypeDescriptor.valueOf(Person.class));
        System.out.println(convert);

        // ConversionService方式（实际使用方式）
        ConversionService conversionService = new DefaultConversionService();
        Person person = conversionService.convert(customer, Person.class);
        System.out.println(person);
    }

    public static void test7() {
        System.out.println("----------------IdToEntityConverter---------------");
        ConditionalGenericConverter converter = new IdToEntityConverter(new DefaultConversionService());

        TypeDescriptor sourceTypeDesp = TypeDescriptor.valueOf(String.class);
        TypeDescriptor targetTypeDesp = TypeDescriptor.valueOf(org.springframework.core.convert.support.IdToEntity.Person.class);
        boolean matches = converter.matches(sourceTypeDesp, targetTypeDesp);
        System.out.println("是否能够转换：" + matches);

        // 执行转换
        Object convert = converter.convert("1", sourceTypeDesp, targetTypeDesp);
        System.out.println(convert);
    }

    public static void test8() {
        System.out.println("----------------ObjectToOptionalConverter---------------");
        ConversionService conversionService = new DefaultConversionService();
        Optional<Integer> result = conversionService.convert(Arrays.asList(2), Optional.class);

        System.out.println(result);
    }
}
