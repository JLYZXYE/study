package expression;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpelExpressionContextTest {
    public static void main(String[] args) {
        //创建表达式解析器
        ExpressionParser expressionParser = new SpelExpressionParser();
//创建数据上下文
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
//设置变量
        evaluationContext.setVariable("a", 12);
        evaluationContext.setVariable("b", 34);
        evaluationContext.setVariable("c", 56);
        evaluationContext.setVariable("user", new User("lisi"));
//解析表达式
        System.out.println(expressionParser.parseExpression("#a+#b-#c").getValue(evaluationContext));
        System.out.println(expressionParser.parseExpression("#user.name").getValue(evaluationContext));
        System.out.println(expressionParser.parseExpression("#user.getName()").getValue(evaluationContext));
    }
}
