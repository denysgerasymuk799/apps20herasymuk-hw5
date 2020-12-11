package ua.edu.ucu.stream;

import ua.edu.ucu.comparators.Comparator;
import ua.edu.ucu.comparators.MaxComparator;
import ua.edu.ucu.comparators.MinComparator;
import ua.edu.ucu.function.*;
import ua.edu.ucu.iterators.FilterIterator;
import ua.edu.ucu.iterators.FlatMapIterator;
import ua.edu.ucu.iterators.MapIterator;
import ua.edu.ucu.iterators.StreamIntIterator;

import java.util.Iterator;


public class AsIntStream implements IntStream {
    private Iterator<Integer> itemsIterator;

    private AsIntStream(Iterator<Integer> iterator) {
        itemsIterator = iterator;
    }

    public static IntStream of(int... values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("you can not create stream of no elements");
        }

        return new AsIntStream(new StreamIntIterator(values));
    }

    @Override
    public Double average() {
        return (double) (sum() / count());
    }

    private void ifStreamIsEmpty() {
        if (!itemsIterator.hasNext()) {
            throw new IllegalArgumentException("Array is empty");
        }
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
        return new AsIntStream(new FilterIterator(itemsIterator, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        while (itemsIterator.hasNext()) {
            action.accept(itemsIterator.next());
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIterator(itemsIterator, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIterator(itemsIterator, func));
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

        while (itemsIterator.hasNext()) {
            result[idx++] = itemsIterator.next();
        }
        return result;
    }

}
