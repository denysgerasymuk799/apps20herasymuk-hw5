package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;

public class FilterIterator implements Iterator<Integer> {
    private final IntPredicate predicate;
    private final Iterator<Integer> prevIterator;
    private int number;

    public FilterIterator(Iterator<Integer> prevIterator,
                          IntPredicate predicate) {
        this.predicate = predicate;
        this.prevIterator = prevIterator;
    }

    @Override
    public boolean hasNext() {
        while (prevIterator.hasNext()) {
            number = prevIterator.next();

            if (predicate.test(number)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return number;
    }
}