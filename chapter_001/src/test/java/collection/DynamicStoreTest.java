package collection;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicStoreTest {

    @Test
    public void testAdd() {
        DynamicStore<String> stringDynamicStore = new DynamicStore<>();
        stringDynamicStore.add("123");
        stringDynamicStore.add("234");
        stringDynamicStore.add("345");
    }

    @Test
    public void testGet() {
        DynamicStore<String> stringDynamicStore = new DynamicStore<>();
        stringDynamicStore.add("123");
        stringDynamicStore.add("234");
        stringDynamicStore.add("345");
        stringDynamicStore.add("456");
        stringDynamicStore.add("567");

        assertThat(stringDynamicStore.get(1), is("234"));
        assertThat(stringDynamicStore.get(4), is("567"));
    }

    @Test
    public void testCheckElement() {
        DynamicStore<String> stringDynamicStore = new DynamicStore<>();
        stringDynamicStore.add("123");
        stringDynamicStore.add("234");
        stringDynamicStore.add("345");
        assertThat(stringDynamicStore.checkElement(4), is(false));
        assertThat(stringDynamicStore.checkElement(1), is(true));
    }

    @Test
    public void testIterator() {
        DynamicStore<String> stringDynamicStore = new DynamicStore<>();
        stringDynamicStore.add("123");
        stringDynamicStore.add("234");
        stringDynamicStore.add("345");
        Iterator<String> it = stringDynamicStore.iterator();
        it.next();
        it.next();
        assertThat(it.next(), is("345"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        DynamicStore<String> stringDynamicStore = new DynamicStore<>();
        Iterator<String> it = stringDynamicStore.iterator();
        it.next();
    }
}