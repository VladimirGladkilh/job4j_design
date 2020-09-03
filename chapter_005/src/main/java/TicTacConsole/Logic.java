package TicTacConsole;

import java.util.Arrays;
import java.util.function.Predicate;

public class Logic {
    private final Figure[][] table;

    public Logic(Figure[][] table) {
        this.table = table;
    }

    public boolean fillBy(Predicate<Figure> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = false;
        boolean diag = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure cell = this.table[startX][startY];
            if (predicate.test(cell)) {
                if (monoHorizontal(predicate, startX) || monoVertical(predicate, startY)) {
                    result = true;
                    break;
                }
            }else {
                diag = false;
            }
            startX += deltaX;
            startY += deltaY;
        }
        return result || diag;
    }
    public  boolean monoHorizontal(Predicate<Figure> predicate, int row) {
        boolean result = true;
        for (int i = 0; i < this.table.length; i++) {
            Figure cell = this.table[row][i];
            result = predicate.test(cell);
            if (!result) {
                break;
            }
        }
        return result;
    }

    public boolean monoVertical(Predicate<Figure> predicate, int column) {
        boolean result = true;
        for (int i = 0; i < this.table.length; i++) {
            Figure cell = this.table[i][column];
            result = predicate.test(cell);
            if (!result) {
                break;
            }
        }
        return result;
    }

    /**
     * Проверяем первую горизонталь, первую вертикаль и обе диагонали
     * проверка остальных вертикалей и диагоналей реализована методами monoHorizontal и monoVertical
     * @return
     */
    public boolean isWinnerX() {
        return this.fillBy(Figure::hasMarkX, 0, 0, 1, 0) ||
                this.fillBy(Figure::hasMarkX, 0, 0, 1, 1) ||
                this.fillBy(Figure::hasMarkX, 0, 0, 0, 1) ||
                this.fillBy(Figure::hasMarkX, this.table.length - 1 , 0, -1, 1);
    }
    /**
     * Проверяем первую горизонталь, первую вертикаль и обе диагонали
     * проверка остальных вертикалей и диагоналей реализована методами monoHorizontal и monoVertical
     * @return
     */
    public boolean isWinnerO() {
        return this.fillBy(Figure::hasMarkO, 0, 0, 1, 0) ||
                this.fillBy(Figure::hasMarkO, 0, 0, 1, 1) ||
                this.fillBy(Figure::hasMarkO, 0, 0, 0, 1) ||
                this.fillBy(Figure::hasMarkO, this.table.length - 1 , 0, -1, 1);

    }

    public boolean hasGap() {
        return Arrays.stream(this.table)
                .flatMap(figureS -> Arrays.stream(figureS))
                .anyMatch(figureT -> figureT.isClean());
    }
}
