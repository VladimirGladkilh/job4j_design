package ru.job4j;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SomeClassTest {
    @Test
    public void testName() {
        assertThat(SomeClass.getClasName().equals("SomeClass"), is(true));
    }
}
