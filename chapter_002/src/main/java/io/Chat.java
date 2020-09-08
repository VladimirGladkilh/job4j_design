package io;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Chat {
    private static boolean sleep = true;
    private static boolean shut = false;
    static final String COMMAND_STOP = "стоп";
    static final String COMMAND_START = "продолжить";
    static final String COMMAND_END = "закончить";

    public static void main(String[] args) {
        sayWithMe();
    }

    private static void sayWithMe() {
        Scanner scanner = new Scanner(System.in);
        Slovar slovar = new Slovar();
        while (sleep) {
            analize(scanner.nextLine());
            say(slovar);
        }
        System.out.println("Пока, пока");
    }


    private static void say(Slovar slovar) {
        if (!shut) {
            String say = slovar.getAnswer();
            System.out.println(say);
            writeToLog("<< " + say);
        }
    }

    private static void writeToLog(String text) {
        try (FileWriter writer = new FileWriter("chatLog.txt", true);) {
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(text + System.lineSeparator());
            bufferWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void analize(String command) {
        writeToLog(">> " + command);
        switch (command.toLowerCase()) {
            case COMMAND_STOP:
                shut = true;
                break;
            case COMMAND_START:
                shut = false;
                break;
            case COMMAND_END:
                shut = true;
                sleep = false;
                break;
            default: shut = false;
                break;
        }
    }

    private static class Slovar {
        private String[] slova;
        public Slovar() {
            try (BufferedReader in = new BufferedReader(new FileReader("slovar.txt"))) {
                this.slova = in.lines().collect(Collectors.joining(System.lineSeparator())).split(System.lineSeparator());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public String getAnswer() {
            int index = new Random().nextInt(this.slova.length - 1);
            return this.slova[index];
        }
    }
}

