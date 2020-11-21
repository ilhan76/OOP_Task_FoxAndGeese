package sample.services;

import sample.GameProcess;
import sample.figures.Figure;
import sample.figures.Fox;
import sample.figures.Goose;
import sample.gameFild.Cell;
import sample.gameFild.LogicGameField;

public class FieldService {
    public static LogicGameField makeGameField(int[][] matrix) {
        LogicGameField gameField = new LogicGameField();
        for (int i = 1; i < 7; i++) {
            for (char j = 'B'; j < 'H'; j++) {
                Figure figure = chooseFigure(matrix[i][(j - 66)], gameField);
                Cell<Figure> cell1 = new Cell<>(figure, (char) (j - 1), i);
                if (figure != null) figure.setCurrentCell(cell1);
                figure = chooseFigure(matrix[i][(j - 65)], gameField);
                Cell<Figure> cell2 = new Cell<>(figure, j, i);
                if (figure != null) figure.setCurrentCell(cell2);
                figure = chooseFigure(matrix[i - 1][(j - 66)], gameField);
                Cell<Figure> cell3 = new Cell<>(figure, (char) (j - 1), i - 1);
                if (figure != null) figure.setCurrentCell(cell3);
                figure = chooseFigure(matrix[i - 1][(j - 65)], gameField);
                Cell<Figure> cell4 = new Cell<>(figure, j, i - 1);
                if (figure != null) figure.setCurrentCell(cell4);

                gameField.addEdge(cell1, cell2);
                gameField.addEdge(cell2, cell1);

                gameField.addEdge(cell1, cell3);
                gameField.addEdge(cell3, cell1);

                gameField.addEdge(cell2, cell4);
                gameField.addEdge(cell4, cell2);

                gameField.addEdge(cell3, cell4);
                gameField.addEdge(cell4, cell3);

                //gameField.addEdge(cell1, cell4);
                //gameField.addEdge(cell4, cell1);
                //
                //gameField.addEdge(cell3, cell2);
                //gameField.addEdge(cell2, cell3);
            }
        }
        char[] delX = {'A', 'B', 'F', 'G'};
        int[] delY = {0, 1, 5, 6};
        for (int y : delY) {
            for (char x : delX) {
                gameField.remove(new Cell<>(null, x, y));
            }
        }
        for (Cell<Figure> c :
                gameField.getCells()) {
            if (c.getFigure() != null) {
                if (c.getFigure().getClass() == Fox.class) gameField.addFox((Fox) c.getFigure());
                else gameField.addGoose((Goose) c.getFigure());
            }
        }
        return gameField;
    }

    private static Figure chooseFigure(int i, LogicGameField lgf) {
        if (i == 0 || i == 1) return null;
        else if (i == 2) return new Goose(lgf);
        else return new Fox(lgf);
    }

    public static void printMatrix(int[][] field) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (field[i][j] == 0) System.out.print("\033[0;100m");
                else if ((j + i) % 2 == 0) System.out.print("\u001B[40m");
                else System.out.print("\u001B[47m");

                if (field[i][j] % 2 == 0) System.out.print("\033[1;92m");
                else System.out.print("\033[1;91m");
                if (field[i][j] == 0 || field[i][j] == 1) {
                    System.out.print("   ");
                    continue;
                }
                System.out.printf(" %s ", field[i][j]);
            }
            System.out.print("\033[0m");
            System.out.println();
        }
    }

    public static void printField(LogicGameField gameField) {
        int counter = 0;
        System.out.println("==============================");
        for (Cell<Figure> c :
                gameField.getCells()) {
            if (c.getX() == 'C' && (c.getY() == 0 || c.getY() == 1 || c.getY() == 5 || c.getY() == 6)){
                System.out.print("||||||");
                counter+=2;
            }
            counter++;
            if ((c.getX() - 65 + c.getY()) % 2 == 0) System.out.print("\u001B[40m");
            else
                System.out.print("\u001B[47m");

            if (c.getFigure() != null) {
                if (c.getFigure().getClass() == Goose.class) System.out.print("\033[1;92m");
                else System.out.print("\033[1;91m");
            }

            if (c.getFigure() == null) System.out.print("   ");
            else System.out.printf(" %s ", c.getFigure().getClass() == Fox.class ? 'F' : 'G');
            if (c.getX() == 'E' && (c.getY() == 0 || c.getY() == 1 || c.getY() == 5 || c.getY() == 6)){
                System.out.print("\033[0;150m");
                System.out.print("||||||");
                counter+=2;
            }
            if (counter % 7 == 0) {
                System.out.print("\033[0m");
                System.out.println();
            }
        }
        System.out.println("==============================");
    }

    public static void printWinner(GameProcess gameProcess){
        System.out.println();
        System.out.println();
        System.out.println("===================================");
        if (gameProcess.isFoxWin()) System.out.println("========== FOX IS WINNER ==========");
        else System.out.println("========= GEESE IS WINNER =========");
        System.out.println("===================================");
    }
}
