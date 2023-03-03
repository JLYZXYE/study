package spi;

import java.nio.charset.StandardCharsets;
import java.util.ServiceLoader;

public class TestSPI {
    public static void main(String[] args) {
        ServiceLoader<Compresser> serviceLoader = ServiceLoader.load(Compresser.class);
        for (Compresser service : serviceLoader) {
            System.out.println(service.getClass().getClassLoader());
            byte[] compress = service.compress("Hello".getBytes(StandardCharsets.UTF_8));
            System.out.println(new String(compress));
            byte[] decompress = service.decompress("Hello".getBytes(StandardCharsets.UTF_8));
            System.out.println(new String(decompress));
        }
    }

}
