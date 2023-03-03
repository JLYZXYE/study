package spi;


import java.nio.charset.StandardCharsets;

public class GzipCompresser implements Compresser {
    @Override
    public byte[] compress(byte[] bytes) {
        return "compress by Gzip".getBytes(StandardCharsets.UTF_8);
    }
    @Override
    public byte[] decompress(byte[] bytes) {
        return "decompress by Gzip".getBytes(StandardCharsets.UTF_8);
    }
}
