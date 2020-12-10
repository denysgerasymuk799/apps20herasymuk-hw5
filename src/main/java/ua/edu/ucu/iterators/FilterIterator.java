package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;

public class FilterIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private IntPredicate predicate;
    private int value;

    public FilterIterator(Iterator<Integer> mainIterator, IntPredicate predicate) {
        this.iterator = mainIterator;
        this.predicate = predicate;
    }
    @Override
    public boolean hasNext() {
        while (iterator.hasNext()) {
            value = iterator.next();
            if (predicate.test(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return value;
    }
}