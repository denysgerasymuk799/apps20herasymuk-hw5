package ua.edu.ucu;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;


public class AsIntStreamTest {
    private IntStream intStream, intStreamNegative,
            intStreamOneEl, intStreamSimilar, intStreamLarge;

    @Before
    public void init() {
        intStream = AsIntStream.of(-1, 0, 1, 2, 3);
        intStreamNegative = AsIntStream.of(-3, -2, -1, 0, 1);
        intStreamOneEl = AsIntStream.of(0);
        intStreamSimilar = AsIntStream.of(-1, -1, -1, -1, -1);

        int[] intArr = new int[100];
        for (int i = 0; i < 100; i++) {
            intArr[i] = i + 1;
        }

        intStreamLarge = AsIntStream.of(intArr);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testOf() {
        AsIntStream.of();
    }

    /** ============================== Tests for Average ============================== */
    @Test
    public void testAverage() {
        double result = intStream.average();
        assertEquals(1.0, result, 0.00001);
    }

    @Test
    public void testAverageNegative() {
        double result = intStreamNegative.average();
        assertEquals(-1.0, result, 0.00001);
    }

    @Test
    public void testAverageOneEl() {
        double result = intStreamOneEl.average();
        assertEquals(0, result, 0.00001);
    }

    @Test
    public void testAverageSimilar() {
        double result = intStreamSimilar.average();
        assertEquals(-1.0, result, 0.00001);
    }

    @Test
    public void testAverageLarge() {
        double result = intStreamLarge.average();
        assertEquals(50.0, result, 0.00001);
    }


    /** ============================== Tests for Sum ============================== */
    @Test
    public void testSum() {
        int result = intStream.sum();
        assertEquals(5, result);
    }

    @Test
    public void testSumNegative() {
        int result = intStreamNegative.sum();
        assertEquals(-5, result);
    }

    @Test
    public void testSumOneEl() {
        int result = intStreamOneEl.sum();
        assertEquals(0, result);
    }

    @Test
    public void testSumSimilar() {
        int result = intStreamSimilar.sum();
        assertEquals(-5, result);
    }

    @Test
    public void testSumLarge() {
        int result = intStreamLarge.sum();
        assertEquals(5050, result);
    }

    /** ============================== Tests for Min ============================== */
    @Test
    public void testMin() {
        int result = intStream.min();
        assertEquals(-1, result);
    }

    @Test
    public void testMinNegative() {
        int result = intStreamNegative.min();
        assertEquals(-3, result);
    }

    @Test
    public void testMinOneEl() {
        int result = intStreamOneEl.min();
        assertEquals(0, result);
    }

    @Test
    public void testMinSimilar() {
        int result = intStreamSimilar.min();
        assertEquals(-1, result);
    }

    @Test
    public void testMinLarge() {
        int result = intStreamLarge.min();
        assertEquals(1, result);
    }

    /** ============================== Tests for Max ============================== */
    @Test
    public void testMax() {
        int result = intStream.max();
        assertEquals(3, result);
    }

    @Test
    public void testMaxNegative() {
        int result = intStreamNegative.max();
        assertEquals(1, result);
    }

    @Test
    public void testMaxOneEl() {
        int result = intStreamOneEl.max();
        assertEquals(0, result);
    }

    @Test
    public void testMaxSimilar() {
        int result = intStreamSimilar.max();
        assertEquals(-1, result);
    }

    @Test
    public void testMaxLarge() {
        int result = intStreamLarge.max();
        assertEquals(100, result);
    }

    /** ============================== Tests for Count ============================== */
    @Test
    public void testCount() {
        long result = intStream.count();
        assertEquals(5, result);
    }

    @Test
    public void testCountLarge() {
        long result = intStreamLarge.count();
        assertEquals(100, result);
    }

    @Test
    public void testCountOneEl() {
        long result = intStreamOneEl.count();
        assertEquals(1, result);
    }

    /** ============================== Tests for Filter ============================== */
    @Test
    public void testFilter() {
        int[] result = intStream.filter(x -> x < 0)
                .toArray();
        assertArrayEquals(new int[] {-1}, result);
    }

    @Test
    public void testFilterNegative() {
        int[] result = intStreamNegative.filter(x -> x < 0)
                .toArray();
        assertArrayEquals(new int[] {-3, -2, -1}, result);
    }

    @Test
    public void testFilterOneEl() {
        int[] result = intStreamOneEl.filter(x -> x < 0)
                .toArray();
        assertArrayEquals(new int[] {}, result);
    }

    @Test
    public void testFilterSimilar() {
        int[] result = intStreamSimilar.filter(x -> x < 0)
                .toArray();
        assertArrayEquals(new int[] {-1, -1, -1, -1, -1}, result);
    }

    @Test
    public void testFilterLarge() {
        int[] result = intStreamLarge.filter(x -> x < 0)
                .toArray();
        assertArrayEquals(new int[] {}, result);
    }

    /** ============================== Tests for ForEach ============================== */
    @Test
    public void testForEach() {
        StringBuilder str = new StringBuilder();
        intStream.forEach(str::append);
        assertEquals("-10123", str.toString());
    }

    @Test
    public void testForEachNegative() {
        StringBuilder str = new StringBuilder();
        intStreamNegative.forEach(str::append);
        assertEquals("-3-2-101", str.toString());
    }

    @Test
    public void testForEachOneEl() {
        StringBuilder str = new StringBuilder();
        intStreamOneEl.forEach(str::append);
        assertEquals("0", str.toString());
    }

    @Test
    public void testForEachSimilar() {
        StringBuilder expResult = new StringBuilder();
        StringBuilder str = new StringBuilder();
        intStreamSimilar.forEach(str::append);

        for (int i = 0; i < 5; i++) {
            expResult.append(-1);
        }
        assertEquals(expResult.toString(), str.toString());
    }

    @Test
    public void testForEachLarge() {
        StringBuilder expResult = new StringBuilder();
        StringBuilder str = new StringBuilder();
        intStreamLarge.forEach(str::append);

        for (int i = 0; i < 100; i++) {
            expResult.append(i + 1);
        }
        assertEquals(expResult.toString(), str.toString());
    }

    /** ============================== Tests for Map ============================== */
    @Test
    public void testMap() {
        int[] result = intStream.map(x -> x * x * x)
                .toArray();

        int[] expResult = new int[5];
        for (int i = 0; i < 5; i++) {
            expResult[i] = (int) Math.pow(i - 1, 3);
        }

        assertArrayEquals(expResult, result);
    }

    @Test
    public void testMapNegative() {
        int[] result = intStreamNegative.map(x -> x * x * x)
                .toArray();

        int[] expResult = new int[5];
        for (int i = 0; i < 5; i++) {
            expResult[i] = (int) Math.pow(i - 3, 3);
        }

        assertArrayEquals(expResult, result);
    }

    @Test
    public void testMapOneEl() {
        int[] result = intStreamOneEl.map(x -> x * x * x)
                .toArray();

        assertArrayEquals(new int[] {0}, result);
    }

    @Test
    public void testMapSimilar() {
        int[] result = intStreamSimilar.map(x -> x * x * x)
                .toArray();

        int[] expResult = new int[5];
        for (int i = 0; i < 5; i++) {
            expResult[i] = (int) Math.pow(-1, 3);
        }

        assertArrayEquals(expResult, result);
    }

    @Test
    public void testMapLarge() {
        int[] result = intStreamLarge.map(x -> x * x * x)
                .toArray();

        int[] expResult = new int[100];
        for (int i = 0; i < 100; i++) {
            expResult[i] = (int) Math.pow(i + 1, 3);
        }

        assertArrayEquals(expResult, result);
    }


    /** ============================== Tests for FlatMap ============================== */
    @Test
    public void testFlatMap() {
        int[] result = intStream.flatMap(x -> AsIntStream.of(x - 2, x, x + 2))
                .toArray();

        int[] expResult = new int[15];
        int idx = 0;
        for (int i = 0; i < 5; i++) {
            expResult[idx++] = i - 1 - 2;
            expResult[idx++] = i - 1;
            expResult[idx++] = i - 1 + 2;
        }
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testFlatMapNegative() {
        int[] result = intStreamNegative.flatMap(x -> AsIntStream.of(x - 2, x, x + 2))
                .toArray();

        int[] expResult = new int[15];
        int idx = 0;
        for (int i = 0; i < 5; i++) {
            expResult[idx++] = i - 3 - 2;
            expResult[idx++] = i - 3;
            expResult[idx++] = i - 3 + 2;
        }
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testFlatMapOneEl() {
        int[] result = intStreamOneEl.flatMap(x -> AsIntStream.of(x - 2, x, x + 2))
                .toArray();

        assertArrayEquals(new int[] {-2, 0, 2}, result);
    }

    @Test
    public void testFlatMapSimilar() {
        int[] result = intStreamSimilar.flatMap(x -> AsIntStream.of(x - 2, x, x + 2))
                .toArray();

        int[] expResult = new int[15];
        int idx = 0;
        for (int i = 0; i < 5; i++) {
            expResult[idx++] = -1 - 2;
            expResult[idx++] = -1;
            expResult[idx++] = -1 + 2;
        }

        assertArrayEquals(expResult, result);
    }

    @Test
    public void testFlatMapLarge() {
        int[] result = intStreamLarge.flatMap(x -> AsIntStream.of(x - 2, x, x + 2))
                .toArray();

        int[] expResult = new int[300];
        int idx = 0;
        for (int i = 0; i < 100; i++) {
            expResult[idx++] = i + 1 - 2;
            expResult[idx++] = i + 1;
            expResult[idx++] = i + 1 + 2;
        }

        assertArrayEquals(expResult, result);
    }

    /** ============================== Tests for ToArray ============================== */
    @Test
    public void testToArray() {
        int[] result = intStream.toArray();
        assertArrayEquals(new int[] {-1, 0, 1, 2, 3}, result);
    }

    @Test
    public void testToArrayLarge() {
        int[] result = intStreamLarge.toArray();

        int[] intArr = new int[100];
        for (int i = 0; i < 100; i++) {
            intArr[i] = i + 1;
        }

        assertArrayEquals(intArr, result);
    }

    @Test
    public void testToArrayOneEl() {
        int[] result = intStreamOneEl.toArray();
        assertArrayEquals(new int[] {0}, result);
    }
}
