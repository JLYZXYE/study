package expression;

import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.expression.BeanExpressionContextAccessor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class BeanExpressionContextTest {
    public static void main(String[] args) {
//创建表达式解析器
        ExpressionParser expressionParser = new SpelExpressionParser();
//创建数据上下文
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
//创建IOC容器上下文
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
////创建bean表达式上下文
        BeanExpressionContext beanExpressionContext = new BeanExpressionContext((ConfigurableBeanFactory) context.getAutowireCapableBeanFactory(), null);
        evaluationContext.setRootObject(beanExpressionContext);
////添加属性访问器 从IOC容器中获取bean
        evaluationContext.addPropertyAccessor(new BeanExpressionContextAccessor());
        System.out.println(expressionParser.parseExpression("#{user.name}", new TemplateParserContext()).getValue(evaluationContext));


    }
}
