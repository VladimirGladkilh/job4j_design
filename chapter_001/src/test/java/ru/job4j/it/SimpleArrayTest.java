package ru.job4j.it;

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayTest {
    @Test
    public void testAddAndGet() {
        SimpleArray<String> stringSimpleArray = new SimpleArray<>(3);
        stringSimpleArray.add("test");
        String res = stringSimpleArray.get(0);
        assertThat(res, is("test"));
    }
    @Test
    public void testSet() {
        SimpleArray<String> stringSimpleArray = new SimpleArray<>(3);
        stringSimpleArray.add("testSet");
        stringSimpleArray.add("testSet2");
        stringSimpleArray.set(1, "testSet5");
        String res = stringSimpleArray.get(1);
        assertThat(res, is("testSet5"));
        assertThat(stringSimpleArray.get(3), isEmptyOrNullString());
    }
    @Test
    public void testRemove() {
        SimpleArray<String> stringSimpleArray = new SimpleArray<>(3);
        stringSimpleArray.add("test1");
        stringSimpleArray.add("test2");
        stringSimpleArray.add("test3");
        stringSimpleArray.remove(1);
        assertThat(stringSimpleArray.get(1), is("test3"));
    }

    @Test
    public void testCheckIndex() {
        SimpleArray<String> stringSimpleArray = new SimpleArray<>(3);
        stringSimpleArray.add("test1");
        stringSimpleArray.add("test2");
        stringSimpleArray.add("test3");
        assertThat(stringSimpleArray.get(5), isEmptyOrNullString());
    }
    @Test
    public void testIterator() {
        SimpleArray<String> stringSimpleArray = new SimpleArray<>(2);
        stringSimpleArray.add("test4");
        stringSimpleArray.add("test2");
        Iterator<String> it = stringSimpleArray.iterator();
        assertThat(it.hasNext(), is(true));
        it.next();
        it.next();
        assertThat(it.hasNext(), is(false));
    }
    @Test
    public void testIteratorNext() {
        SimpleArray<String> stringSimpleArray = new SimpleArray<>(5);
        stringSimpleArray.add("test4");
        stringSimpleArray.add("test2");
        stringSimpleArray.add("test3");
        stringSimpleArray.add("test1");
        stringSimpleArray.add("test");
        Iterator<String> it = stringSimpleArray.iterator();
        it.next();
        it.next();
        assertThat(it.next(), is("test3"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        SimpleArray<String> stringSimpleArray = new SimpleArray<>(0);
        Iterator<String> it = stringSimpleArray.iterator();
        it.next();
    }
}