package kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class MaxMinTest  {

    @Test
    public void whenStringMin() {
        List<String> strings = new LinkedList<>();
        strings.add("first");
        strings.add("abs");
        strings.add("many symbols");
        Comparator<String>  stringComparator = (o1, o2) -> o1.length() - o2.length();
        MaxMin maxMin = new MaxMin();
        assertThat(
                maxMin.min(strings, stringComparator), is("abs")
        );
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenZeroList() {
        List<String> strings = new LinkedList<>();
        Comparator<String>  stringComparator = (o1, o2) -> o1.length() - o2.length();
        MaxMin maxMin = new MaxMin();
        assertThat(
                maxMin.min(strings, stringComparator), is("abs")
        );
    }

    @Test
    public void whenStringMax() {
        List<String> strings = new LinkedList<>();
        strings.add("first");
        strings.add("abs");
        strings.add("many symbols");
        strings.add("some text");
        Comparator<String>  stringComparator = (o1, o2) -> o1.length() - o2.length();
        MaxMin maxMin = new MaxMin();
        assertThat(
                maxMin.max(strings, stringComparator), is("many symbols")
        );
    }

    @Test
    public void whenIntMax() {
        List<Integer> integers = new LinkedList<>();
        integers.add(-100);
        integers.add(100);
        integers.add(112);
        integers.add(31);
        integers.add(1787);
        Comparator<Integer>  stringComparator = (o1, o2) -> o1 - o2;
        MaxMin maxMin = new MaxMin();
        assertThat(
                maxMin.max(integers, stringComparator), is(1787)
        );
    }

    @Test
    public void whenIntMin() {
        List<Integer> integers = new LinkedList<>();
        integers.add(100);
        integers.add(112);
        integers.add(31);
        integers.add(-123);
        integers.add(1787);
        Comparator<Integer>  stringComparator = (o1, o2) -> o1 - o2;
        MaxMin maxMin = new MaxMin();
        assertThat(
                maxMin.min(integers, stringComparator), is(-123)
        );
    }

    @Test
    public void whenIntLenMin() {
        List<Integer> integers = new LinkedList<>();
        integers.add(100);
        integers.add(112);
        integers.add(31);
        integers.add(-123);
        integers.add(1787);
        Comparator<Integer>  stringComparator = (o1, o2) -> o1.toString().length() - o2.toString().length();
        MaxMin maxMin = new MaxMin();
        assertThat(
                maxMin.min(integers, stringComparator), is(31)
        );
    }

}