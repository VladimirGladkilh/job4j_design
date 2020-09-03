package TicTacConsole;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TicTacConsole {
    private int size = 3;
    private Figure[][] cells;// = new Figure[size][size];
    private Logic logic;// = new Logic(cells);
    private boolean userMarker = true;
    private boolean who = true;


    private void showAlert(String message) {
        System.out.println(message);
    }

    private boolean checkState() {
        boolean gap = this.logic.hasGap();
        if (!gap) {
            this.showAlert("Все поля запонены! Начните новую Игру!");
        }
        return gap;
    }

    private boolean checkWinner() {
        if (this.logic.isWinnerX()) {
            this.showAlert("Победили Крестики! Начните новую Игру!");
            return true;
        } else if (this.logic.isWinnerO()) {
            this.showAlert("Победили Нолики! Начните новую Игру!");
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
      new TicTacConsole().menu();
    }

    //отображаем сетку на экране
    public void writeGrid() {
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j <= size; j++) {
                if (j == 0 || i == 0) {
                    String print = (j + i - 1) < 0 ? "| " : "|" + (j + i - 1);
                    System.out.print(print);
                } else {
                    System.out.print(cells[i - 1][j - 1].isClean() ? "| " : cells[i - 1][j - 1].hasMarkX() ? "|X" : "|O");
                }
            }
            System.out.println("|");
        }
    }

    public void menu() {
        Input input = new ConsoleInput();
        String rez = input.askStr("Select 1 for New game else Exit:");
        if (rez.equals("1")) {
            init();
        } else {
            System.out.println("Bye!");
        }
    }

    public void init() {
        Input input = new ConsoleInput();
        int cellSize = input.askInt("Введите размер сетки");
        this.size = cellSize;
        cells = new Figure[cellSize][cellSize];
        //заполняем сетку заданного размера
        for (int i = 0; i < cellSize; i++) {
            for (int j = 0; j < cellSize; j++) {
                Figure figure = new Figure();
                cells[i][j] = figure;
            }
        }
        logic = new Logic(cells);

        String marker = input.askStr("Чем играем (X/O)?");
        this.userMarker = marker.toLowerCase().equals("x");
        who = this.userMarker;
        while (this.checkState() && !this.checkWinner()) {
            writeGrid();
            if (who) {
                userStep();
            } else {
                pcStep();
            }
        }
        menu();
    }

    private void userStep() {
        Input input = new ConsoleInput();
        int[] xy = input.askXY(size);
        if (cells[xy[0]][xy[1]].isClean()) {
            cells[xy[0]][xy[1]].take(this.userMarker);
            who = false;
        } else {
            System.out.println("Ячейка занята. Выберите другую");
            userStep();
        }
    }

    /** реализуем логику работы ПК
     * он не очень умный поэтому просто берем все свободные и тыкаем в первую попавшуюся
     */
    public void pcStep() {
        System.out.println("pcStep");
        List<Figure> figures = Arrays.stream(this.cells)
                .flatMap(figureS -> Arrays.stream(figureS))
                .filter(figureT -> figureT.isClean())
                .collect(Collectors.toList());
        Random rm = new Random();
        int index = rm.nextInt(figures.size());
        figures.get(index).take(!this.userMarker);
        who = true;
        /*System.out.println("pcStep");
        Input input = new ConsoleInput();
        int[] xy = input.askXY(size);
        if (cells[xy[0]][xy[1]].isClean()) {
            cells[xy[0]][xy[1]].take(!this.userMarker);
            who = true;
        } else {
            System.out.println("Ячейка занята. Выберите другую");
            pcStep();
        }*/
    }
}
