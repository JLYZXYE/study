package qualifier;

import org.springframework.beans.factory.BeanNameAware;

public class Product implements BeanNameAware{

    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println(name);
    }
}
