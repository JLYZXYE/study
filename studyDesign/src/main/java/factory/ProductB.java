package factory;

public class ProductB implements IProduct {
    @Override
    public void price() {
        System.out.println("ProductB price is 10");
    }
}
