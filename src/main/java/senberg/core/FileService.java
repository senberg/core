package senberg.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.*;
import java.nio.channels.FileChannel;

/**
 * Optimized convenience methods for working with files.
 */
@Lazy
@Service
public class FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @PostConstruct
    public void init() {
        logger.info("Initialized.");
    }

    public void readIntArrayFromFile(String filename, int[] output) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            FileChannel fileChannel = fileInputStream.getChannel();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            IntBuffer intBuffer = mappedByteBuffer.asIntBuffer();
            intBuffer.get(output);
        }
    }

    public void readLongArrayFromFile(String filename, long[] output) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            FileChannel fileChannel = fileInputStream.getChannel();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            LongBuffer intBuffer = mappedByteBuffer.asLongBuffer();
            intBuffer.get(output);
        }
    }

    public void readFloatArrayFromFile(String filename, float[] output) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            FileChannel fileChannel = fileInputStream.getChannel();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            FloatBuffer floatBuffer = mappedByteBuffer.asFloatBuffer();
            floatBuffer.get(output);
        }
    }

    public void readDoubleArrayFromFile(String filename, double[] output) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            FileChannel fileChannel = fileInputStream.getChannel();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            DoubleBuffer doubleBuffer = mappedByteBuffer.asDoubleBuffer();
            doubleBuffer.get(output);
        }
    }

    public void readByteArrayFromFile(String filename, byte[] output) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            FileChannel fileChannel = fileInputStream.getChannel();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            mappedByteBuffer.get(output);
        }
    }
}
