package lifeOrFull.full;

import lifeOrFull.User;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * 该模式下，配置类会被CGLIB增强(生成代理对象)，放进IoC容器内的是代理
 * 该模式下，对于内部类是没有限制的：可以是Full模式或者Lite模式
 * 该模式下，配置类内部可以通过方法调用来处理依赖，并且能够保证是同一个实例，都指向IoC内的那个单例
 * 该模式下，@Bean方法不能被private/final等进行修饰（很简单，因为方法需要被复写嘛，所以不能私有和final。defualt/protected/public都可以哦），否则启动报错（其实IDEA编译器在编译器就提示可以提示你了）：
 * PS:如果是在公司的业务功能/服务上做开发，使用Full模式
 * 如果你是个容器开发者，或者你在开发中间件、通用组件等，那么使用Lite模式是一种更被推荐的方式，它对Cloud Native更为友好
 */
public class Application {

    public static void main(String[] args) throws IllegalAccessException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 配置类情况
        AppConfig appConfig = context.getBean(AppConfig.class);

        System.out.println(appConfig.getClass());
        System.out.println(appConfig.getClass().getSuperclass() == AppConfig.class);
        System.out.println(AopUtils.isCglibProxy(appConfig));

        /**
         * CGLIB字节码提升时，会自动给代理类新增一个名为$$beanFactory的字段/属性，在运行期间给其赋值。所以通过反射可以从代理实例里拿到这个属性值，并且值就是当前BeanFactory
         * 1.小细节：一定只能写成(appConfig.getClass(), "$$beanFactory")而不能是(AppConfig.class, "$$beanFactory")哦，因为这个Field属于代理类而非目标类
         */
        Field $$beanFactoryField = ReflectionUtils.findField(appConfig.getClass(), "$$beanFactory");
        BeanFactory beanFactory = (BeanFactory) $$beanFactoryField.get(appConfig);


        /**
         * spring代理步骤：
         * 1.从BeanFactory拿出所有的bd信息，一个个判断
         * 2.如果是配置类并且是Full模式，就先存储起来，后面会对它做字节码提升。最终如果一个Full模式的配置类都木有，那直接return，此方法结束。否则继续
         * 3.对收集到的每一个 Full模式的配置类，使用ConfigurationClassEnhancer增强器进行字节码提升，生成一个CGLIB子类型
         * 1.小细节：此处显示标注了AOP自动代理为：始终代理目标类
         * 4.把CGLIB生成的子类型设置到元数据里去：beanDef.setBeanClass(enhancedClass)。这样Spring在最后实例化Bean时，实际生成的是该代理类型的实例，从而达到代理/增强的目的
         */
        System.out.println(beanFactory == context.getAutowireCapableBeanFactory());
        System.out.println(beanFactory == context);
        System.out.println(appConfig instanceof BeanFactoryAware);
        System.out.println(appConfig.getClass().getInterfaces()[0]);

        System.out.println(context.getBean(FullConfig.class).getClass());
        System.out.println(context.getBean(FullConfig.InnerConfig.class).getClass());

        String[] beanNames = context.getBeanNamesForType(User.class);
        for (String beanName : beanNames) {
            User user = context.getBean(beanName, User.class);
            System.out.println("beanName:" + beanName);
            System.out.println(user.getClass());
            System.out.println(user);
            System.out.println("------------------------");
        }
    }
}