package collection;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMapTest {

    @Test
    public void testInsert() {
        SimpleHashMap<String, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert("first","First");
        simpleHashMap.insert("second","Second");
        simpleHashMap.insert("third","Third");
        simpleHashMap.insert("first","2");
        System.out.println(simpleHashMap.get("first"));
    }
    @Test
    public void testGet() {
        SimpleHashMap<String, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert("first","First");
        simpleHashMap.insert("second","Second");
        simpleHashMap.insert("third","Third");
        simpleHashMap.insert("first","2");
        assertThat(simpleHashMap.get("first"), is("2"));
        assertThat(simpleHashMap.get("four"), is(nullValue()));

    }

    @Test
    public void testDelete() {
        SimpleHashMap<String, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert("first","First");
        simpleHashMap.insert("second","Second");
        simpleHashMap.insert("third","Third");
        simpleHashMap.insert("first","2");
        assertThat(simpleHashMap.delete("second"), is(true));
        assertThat(simpleHashMap.delete("four"), is(false));
    }
    @Test(expected = NoSuchElementException.class)
    public void testIterator() {
        SimpleHashMap<String, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert("first","First");
        simpleHashMap.insert("second","Second");
        simpleHashMap.insert("third","Third");
        simpleHashMap.insert("first","2");
        for (SimpleHashMap.Node s : simpleHashMap) {
            System.out.println(s.toString());
        }
        Iterator<SimpleHashMap.Node<String, String>> iterator = simpleHashMap.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        SimpleHashMap.Node<String, String> node = iterator.next();
        assertThat(node.getValue(), is("Second"));
    }
    @Test
    public void testIterator2() {
        SimpleHashMap<String, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert("first","First");
        simpleHashMap.insert("second","Second");
        simpleHashMap.insert("third","Third");
        simpleHashMap.insert("first","2");
        for (SimpleHashMap.Node s : simpleHashMap) {
            System.out.println(s.toString());
        }
        Iterator<SimpleHashMap.Node<String, String>> iterator = simpleHashMap.iterator();
        iterator.next();
        iterator.next();
        SimpleHashMap.Node<String, String> node = iterator.next();
        assertThat(node.getValue(), is("Second"));
    }
    @Test
    public void testMillionRecords() {
        SimpleHashMap<String, String> simpleHashMap = new SimpleHashMap<>();
        for (int i = 0; i < 1000; i++) {
            simpleHashMap.insert(String.valueOf(i), "Value" + i);
        }
        assertThat(simpleHashMap.get(String.valueOf(919)), is("Value919"));
    }

}