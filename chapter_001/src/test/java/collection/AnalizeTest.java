package collection;


import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest  {
    @Test
    public void testDiffA2D2C2() {
        List<Analize.User> previous = new LinkedList<>(List.of(
                new Analize.User(1, "Vasya"),
                new Analize.User(2, "Petya"),
                new Analize.User(3, "Masha"),
                new Analize.User(4, "Kiril"),
                new Analize.User(5, "Ivan"),
                new Analize.User(6, "Kate")
        ));
        List<Analize.User> curent = new LinkedList<>(List.of(
                new Analize.User(1, "Vasya"),
                new Analize.User(2, "Petya"),
                new Analize.User(3, "Zina"),
                new Analize.User(4, "Jon"),
                new Analize.User(8, "Ivan"),
                new Analize.User(12, "Kate")
        ));
        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, curent);
        assertThat(info.getAdded(), is(2));
        assertThat(info.getChanged(), is(2));
        assertThat(info.getDeleted(), is(2));

    }
    @Test
    public void oneModify() {
        List<Analize.User> previous = new LinkedList<>(List.of(
                new Analize.User(1, "Vas"),
                new Analize.User(2, "Petya"),
                new Analize.User(3, "Zina"),
                new Analize.User(4, "Jon"),
                new Analize.User(8, "Ivan")
        ));
        List<Analize.User> curent = new LinkedList<>(List.of(
                new Analize.User(1, "Vasya")
        ));
        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, curent);
        assertThat(info.getChanged(), is(1));
    }

    @Test
    public void testDiffSomaSize() {
        List<Analize.User> previous = new LinkedList<>(List.of(
                new Analize.User(1, "Vasya"),
                new Analize.User(2, "Petya"),
                new Analize.User(3, "Masha"),
                new Analize.User(4, "Kiril"),
                new Analize.User(5, "Ivan"),
                new Analize.User(6, "Kate")
        ));
        List<Analize.User> curent = new LinkedList<>(List.of(
                new Analize.User(1, "Vasya"),
                new Analize.User(2, "Petya"),
                new Analize.User(3, "Zina")
        ));
        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, curent);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(1));
        assertThat(info.getDeleted(), is(3));

    }

    @Test
    public void testDiffSomaSize2() {
        List<Analize.User> previous = new LinkedList<>(List.of(
                new Analize.User(1, "Vasya"),
                new Analize.User(2, "Petya"),
                new Analize.User(3, "Zina")
        ));
        List<Analize.User> curent = new LinkedList<>(List.of(
                new Analize.User(1, "Vasya"),
                new Analize.User(2, "Petya"),
                new Analize.User(3, "Masha"),
                new Analize.User(4, "Kiril"),
                new Analize.User(5, "Ivan"),
                new Analize.User(6, "Kate")
        ));
        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, curent);
        assertThat(info.getAdded(), is(3));
        assertThat(info.getChanged(), is(1));
        assertThat(info.getDeleted(), is(0));

    }
}