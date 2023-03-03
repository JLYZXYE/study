package lookUp;

import org.springframework.beans.factory.annotation.Qualifier;
import parse.Label;
import qualifier.Product;

public class User {

    public String a1;

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    private String name;

    private Integer age;

    public User() {
    }

    public User(String name, Integer age,@Qualifier("product222") Product product22) {
        this.name = name;
        this.age = age;
        this.product = product22;
    }

    public Label userLabel;

    public Label getUserLabel() {
        return userLabel;
    }

    public void setUserLabel(Label userLabel) {
        this.userLabel = userLabel;
    }

    public Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void ask() {
        System.out.println("User ask");
    }
}
