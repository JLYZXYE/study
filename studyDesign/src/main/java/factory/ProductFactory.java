package factory;

public class ProductFactory {
    public IProduct getProduct(int type) {
        if (type == 1) {
            return new ProductA();
        } else if (type == 2) {
            return new ProductB();
        }
        return null;
    }
}
