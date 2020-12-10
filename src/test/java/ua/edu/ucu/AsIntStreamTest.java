package ua.edu.ucu;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;


public class AsIntStreamTest {
    private IntStream intStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
    }

    @Test
    public void testSum() {
        assert(intStream.sum() == 5);
    }

//    @Test
//    public void testAverage() {
//        assert(intStream.average() == 1);
//    }
}
