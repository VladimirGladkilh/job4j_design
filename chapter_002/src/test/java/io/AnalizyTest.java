package io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
    @Test
    public void testUnavailable() {
        String path = "./data/server.log";
        String out = "./data/unavailable.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(path, out);
        String firtsStop = "10:57:01; 10:59:01";
        String find = "";
        try (BufferedReader in = new BufferedReader(new FileReader(out))) {
            find = in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(firtsStop, is(find));
    }
    @Test
    public void testLast() {
        String path = "./data/server.log";
        String out = "./data/unavailable.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(path, out);
        String lastStop = "11:01:02; 11:02:02";
        String find = "";
        try (BufferedReader in = new BufferedReader(new FileReader(out))) {
            find = in.lines().reduce((first, second) -> second).orElse("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(lastStop, is(find));
    }
}