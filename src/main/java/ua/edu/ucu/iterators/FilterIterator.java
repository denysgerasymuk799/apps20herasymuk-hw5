package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilterIterator implements Iterator<Integer> {
    private final IntPredicate predicate;
    private final Iterator<Integer> prevIterator;
    private int number;
    private boolean noNextItem;

    public FilterIterator(Iterator<Integer> prevIterator,
                          IntPredicate predicate) {
        this.predicate = predicate;
        this.prevIterator = prevIterator;
        noNextItem = false;
    }

    @Override
    public boolean hasNext() {
        while (prevIterator.hasNext()) {
            noNextItem = false;
            number = prevIterator.next();

            if (predicate.test(number)) {
                return true;
            }
        }
        noNextItem = true;
        return false;
    }

    @Override
    public Integer next() {
        if (noNextItem) {
            throw new NoSuchElementException(
                    "FilterIterator has not next element");
        }

        return number;
    }
}