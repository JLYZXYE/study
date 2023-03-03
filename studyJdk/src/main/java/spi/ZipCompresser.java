package spi;

import java.nio.charset.StandardCharsets;

public class ZipCompresser implements Compresser {
    @Override
    public byte[] compress(byte[] bytes) {
        return "compress by Zip".getBytes(StandardCharsets.UTF_8);
    }
    @Override
    public byte[] decompress(byte[] bytes) {
        return "decompress by Zip".getBytes(StandardCharsets.UTF_8);
    }
}
