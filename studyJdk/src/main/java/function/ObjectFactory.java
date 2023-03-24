package function;

@FunctionalInterface
public interface ObjectFactory<T> {
	T getObject();
}