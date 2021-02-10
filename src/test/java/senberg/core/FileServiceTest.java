package senberg.core;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class FileServiceTest extends AbstractTest {
    @Autowired
    FileService fileService;

    @Test
    public void testReadIntArrayFromFile() throws IOException {
        int[] array = new int[3];
        fileService.readIntArrayFromFile("src/test/resources/datafiles/ints.dat", array);
        assertNotNull(array);
        assertEquals(array[0], 0);
        assertEquals(array[1], 1);
        assertEquals(array[2], 2);
    }

    @Test
    public void testReadLongArrayFromFile() throws IOException {
        long[] array = new long[3];
        fileService.readLongArrayFromFile("src/test/resources/datafiles/longs.dat", array);
        assertNotNull(array);
        assertEquals(array[0], 0);
        assertEquals(array[1], 1);
        assertEquals(array[2], 2);
    }

    @Test
    public void testReadFloatsArrayFromFile() throws IOException {
        float[] array = new float[3];
        fileService.readFloatArrayFromFile("src/test/resources/datafiles/floats.dat", array);
        assertNotNull(array);
        assertEquals(array[0], 0.0f);
        assertEquals(array[1], 1.0f);
        assertEquals(array[2], 2.0f);
    }

    @Test
    public void testReadDoubleArrayFromFile() throws IOException {
        double[] array = new double[3];
        fileService.readDoubleArrayFromFile("src/test/resources/datafiles/doubles.dat", array);
        assertNotNull(array);
        assertEquals(array[0], 0.0d);
        assertEquals(array[1], 1.0d);
        assertEquals(array[2], 2.0d);
    }

    @Test
    public void testReadByteArrayFromFile() throws IOException {
        byte[] array = new byte[3];
        fileService.readByteArrayFromFile("src/test/resources/datafiles/bytes.dat", array);
        assertNotNull(array);
        assertEquals(array[0], 0);
        assertEquals(array[1], 1);
        assertEquals(array[2], 2);
    }
}
