package collection;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

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
    @Test
    public void testIterator() {
        SimpleHashMap<String, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert("first","First");
        simpleHashMap.insert("second","Second");
        simpleHashMap.insert("third","Third");
        simpleHashMap.insert("first","2");
        for (String s : simpleHashMap) {
            System.out.println(s);
        }
        simpleHashMap.iterator().next();
        simpleHashMap.iterator().next();
        simpleHashMap.iterator().next();
        simpleHashMap.iterator().next();
    }
}