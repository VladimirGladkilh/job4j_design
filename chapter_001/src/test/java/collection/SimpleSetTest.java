package collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleSetTest  {
    @Test
    public void testGet() {
        SimpleSet<String> strings = new SimpleSet<>();
        strings.add("first");
        strings.add("second");
        strings.add("third");
        assertThat(strings.get(1), is("second"));
    }
    @Test
    public void testAdd() {
        SimpleSet<String> strings = new SimpleSet<>();
        for (int i = 0; i < 15; i++) {
            strings.add("Value" + i);
        }
        String rsl = strings.get(13);
        assertThat(rsl, is("Value13"));
    }
    @Test
    public void testIterator() {
        SimpleSet<String> strings = new SimpleSet<>();
        for (int i = 0; i < 15; i++) {
            strings.add("Value" + i);
        }
        String rsl = strings.iterator().next();
        assertThat(rsl, is("Value0"));
    }
    @Test
    public void testCheckIndex() {
        SimpleSet<String> strings = new SimpleSet<>();
        strings.add("first");
        strings.add("second");
        strings.add("third");
        assertThat(strings.checkIndex(1), is(true));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleSet<String> strings = new SimpleSet<>();
        strings.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleSet<String> strings = new SimpleSet<>();
        strings.add("first");
        strings.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleSet<String> strings = new SimpleSet<>();

        System.out.println(strings.iterator().next());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleSet<String> strings = new SimpleSet<>();
        strings.add("first");
        Iterator<String> it = strings.iterator();
        strings.add("second");
        it.next();
    }

    @Test
    public void testCheckDouble() {
        SimpleSet<String> strings = new SimpleSet<>();
        strings.add("first");
        strings.add("second");
        strings.add("second");
        assertThat(strings.getSize(), is(2));
    }
}