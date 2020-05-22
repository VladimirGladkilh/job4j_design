package ru.job4j.it;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {

        int i = row;
        int j = column;
        while (i < data.length) {
           if (data[i].length < j) {
               j = 0;
           }
           if (data[i].length > 0 && j < data[i].length ) {
               return true;
           }
           i++;
        }
        return false;//data.length > 0 && data[data.length - 1].length > 0 && (row <= data.length - 1 || column < data[row].length);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (data[row].length > 0) {
            int ret = data[row][column];
            column++;
            if (column == data[row].length) {
                column = 0;
                row++;
            }
            return ret;
        } else {
            column = 0;
            row++;
            return next();
        }
    }
}