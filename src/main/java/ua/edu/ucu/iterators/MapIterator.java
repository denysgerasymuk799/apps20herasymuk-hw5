package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntUnaryOperator;

import java.util.Iterator;

public class MapIterator implements Iterator<Integer> {
    private final IntUnaryOperator mapper;
    private final Iterator<Integer> prevIterator;

    public MapIterator(Iterator<Integer> prevIterator, IntUnaryOperator mapper) {
        this.mapper = mapper;
        this.prevIterator = prevIterator;
    }
    
    @Override
    public boolean hasNext() {
        return prevIterator.hasNext();
    }

    @Override
    public Integer next() {
        return mapper.apply(prevIterator.next());
    }
}