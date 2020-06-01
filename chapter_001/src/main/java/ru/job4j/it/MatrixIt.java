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

        return Arrays.stream(data).
                flatMapToInt(Arrays::stream).skip(row * column).toArray().length > 0;
     /*
     int i = row ;
        int j = column ;
     filter(ints -> ints.length > 0).skip(j*i).
        while (i < data.length) {
           if (data[i].length < j) {
               j = 0;
           }
           if (data[i].length > 0 && j < data[i].length ) {
               return true;
           }
           i++;
        }
        return false;*/
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

    public static void main(String[] args) {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        int[] i= Arrays.stream(in).
                flatMapToInt(Arrays::stream).toArray();
        System.out.println(i);
        System.out.println(it.hasNext());
    }
}