package ru.job4j.it;

import java.util.*;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        return new Iterator<Integer>() {
            private Iterator<Integer> integerIterator = Collections.emptyIterator();
            @Override
            public boolean hasNext() {
                while (it.hasNext() && !integerIterator.hasNext()) {
                    integerIterator = it.next();
                }
                return integerIterator.hasNext();
            }

            @Override
            public Integer next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return integerIterator.next();
            }
        };
    }


}
