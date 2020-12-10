package ua.edu.ucu.stream;

import ua.edu.ucu.comparators.Comparator;
import ua.edu.ucu.comparators.MaxComparator;
import ua.edu.ucu.comparators.MinComparator;
import ua.edu.ucu.function.*;
import ua.edu.ucu.iterators.FilterIterator;
import ua.edu.ucu.iterators.StreamIntIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class AsIntStream implements IntStream {
    private Iterator<Integer> itemsIterator;

    private AsIntStream(Iterator<Integer> iterator) {
//        items = new ArrayList<>();
//        for (int val: values) {
//            items.add(val);
//        }

        itemsIterator = iterator;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(new StreamIntIterator(values));
    }

    @Override
    public Double average() {
//        ifStreamIsEmpty();

//        int sum = 0;
//        while (itemsIterator.hasNext()) {
//            sum += itemsIterator.next();
//        }

        return (double) (sum() / count());
    }

    public void ifStreamIsEmpty() {
        if (!itemsIterator.hasNext()) {
            throw new IllegalArgumentException("Array is empty");
        };
    }

    private Integer findSpecialValue(int startValue, Comparator comparator) {
        ifStreamIsEmpty();
        int specialVal = startValue;
        while (itemsIterator.hasNext()) {
            int nextVal = itemsIterator.next();
            if (comparator.compare(nextVal , specialVal)) {
                specialVal = nextVal;
            }
        }
        return specialVal;
    }

    @Override
    public Integer max() {
        return findSpecialValue(Integer.MIN_VALUE, new MaxComparator());
    }

    @Override
    public Integer min() {
        return findSpecialValue(Integer.MAX_VALUE, new MinComparator());
    }

    @Override
    public long count() {
        long size = 0;
        while (itemsIterator.hasNext()) {
            itemsIterator.next();
            size++;
        }

        return size;
    }

    @Override
    public Integer sum() {
        ifStreamIsEmpty();
        return reduce(0, (sum, el) -> sum += el);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIterator(this.itemsIterator, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        ifStreamIsEmpty();

        int result = identity;
        while (itemsIterator.hasNext()) {
            Integer nextVal = itemsIterator.next();
            result = op.apply(result, nextVal);
        }
        return result;
    }

    @Override
    public int[] toArray() {
        int[] result = new int[(int) count()];
        int idx = 0;
        Integer item;

        while (itemsIterator.hasNext()) {
            item = itemsIterator.next();
            result[idx++] = item;
        }
        return result;
    }

}
