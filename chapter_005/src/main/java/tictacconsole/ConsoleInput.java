package tictacconsole;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String askStr(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }


    @Override
    public int[] askXY(int max) {
        int x = askInt("Введите X:");
        if (x < 0 || x >= max) {
            throw new IllegalStateException(String.format("Out of about %s > [0, %s]", x, max - 1));
        }
        int y = askInt("Введите Y:");
        if (y < 0 || y >= max) {
            throw new IllegalStateException(String.format("Out of about %s > [0, %s]", y, max - 1));
        }
        return new int[]{x, y};
    }

    @Override
    public int askInt(String question) {
        return Integer.valueOf(askStr(question));
    }

    @Override
    public int askInt(String question, int max) {
        int select = askInt(question);
        Object s = new Object();
        if (select < 0 || select >= max) {
            throw new IllegalStateException(String.format("Out of about %s > [0, %s]", select, max));
        }
        return select;
    }
}
