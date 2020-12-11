package ua.edu.ucu.iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StreamIntIterator implements Iterator<Integer> {
    private final List<Integer> items;
    private int idx;
    private int size = 0;

    public StreamIntIterator() {
        items = new ArrayList<>();
        idx = 0;
    }

    public StreamIntIterator(int... values) {
        items = new ArrayList<>();
        idx = 0;
        load(values);
    }

    public void load(int... values) {
        for (Integer value: values) {
            items.add(value);
            size++;
        }
    }

    @Override
    public boolean hasNext() {
        if (idx < size) {
            return true;
        }
        idx = 0;
        return false;
    }

    @Override
    public Integer next() {
        if (this.hasNext()) {
            return items.get(idx++);
        }
        return null;
    }
}
