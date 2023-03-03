package factory;

public class ProductA implements IProduct {
    @Override
    public void price() {
        System.out.println("ProductA price is 10");
    }
}
