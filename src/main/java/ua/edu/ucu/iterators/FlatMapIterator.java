package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.IntStream;

import java.util.Iterator;

public class FlatMapIterator implements Iterator<Integer> {
    private StreamIntIterator iterator;
    private final Iterator<Integer> prevIterator;
    private final IntToIntStreamFunction operator;


    public FlatMapIterator(Iterator<Integer> prevIterator, IntToIntStreamFunction operator) {
        this.prevIterator = prevIterator;
        this.operator = operator;
        this.iterator = new StreamIntIterator();
    }

    @Override
    public boolean hasNext() {
        if (!iterator.hasNext()) {
            // there is no error when we get next intStream before next function
            // as we use generally hasNext function in cycle
            if (prevIterator.hasNext()) {
                IntStream intStream = operator.applyAsIntStream(prevIterator.next());
                iterator = new StreamIntIterator(intStream.toArray());
                return true;
            }
            else {
                // reinitialize iterator variable to start iterating
                // over it in future iterations
                iterator = new StreamIntIterator();
            }
        } else {
            return true;
        }

        return false;
    }

    @Override
    public Integer next() {
        return iterator.next();
    }
}
