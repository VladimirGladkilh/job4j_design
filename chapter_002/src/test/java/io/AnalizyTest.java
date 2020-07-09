package io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testUnavailable() throws IOException {
        String path = "server.log";
        String out = "unavailable.csv";
        File source = folder.newFile(path);
        File target = folder.newFile(out);
        try (PrintWriter outPrintWriter = new PrintWriter(source)) {

            outPrintWriter.println("200 10:56:01" + System.lineSeparator() +
                    "500 10:57:01" + System.lineSeparator() +
                    "400 10:58:01" + System.lineSeparator() +
                    "200 10:59:01" + System.lineSeparator() +
                    "200 11:00:01" + System.lineSeparator() +
                    "500 11:01:02" + System.lineSeparator() +
                    "200 11:02:02");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        String firtsStop = "10:57:01; 10:59:01";
        String find = "";
        try (BufferedReader in = new BufferedReader(new FileReader(target.getAbsoluteFile()))) {
            find = in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(firtsStop, is(find));
    }
    @Test
    public void testLast() throws IOException {
        String path = "server.log";
        String out = "unavailable.csv";
        File source = folder.newFile(path);
        File target = folder.newFile(out);
        try (PrintWriter outPrintWriter = new PrintWriter(source)) {
            outPrintWriter.println("200 10:56:01\n" +
                    "500 10:57:01\n" +
                    "400 10:58:01\n" +
                    "200 10:59:01\n" +
                    "200 11:00:01\n" +
                    "500 11:01:02\n" +
                    "200 11:02:02");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        String lastStop = "11:01:02; 11:02:02";
        String find = "";
        try (BufferedReader in = new BufferedReader(new FileReader(target.getAbsoluteFile()))) {
            find = in.lines().reduce((first, second) -> second).orElse("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(lastStop, is(find));
    }
}