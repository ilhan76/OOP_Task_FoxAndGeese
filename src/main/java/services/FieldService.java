package services;

import process.GameProcess;
import figures.Figure;
import figures.Fox;
import figures.Goose;
import gameFild.Cell;
import gameFild.LogicGameField;

import java.util.ArrayList;

public class FieldService {

    public LogicGameField makeGameField(int[][] matrix) {
        LogicGameField gameField = new LogicGameField();
        final int fieldSize = 7;
        for (int i = 1; i < fieldSize; i++) {
            for (char j = 'B'; j < 'H'; j++) {
                Figure figure = chooseFigure(matrix[i][(j - 66)]);
                Cell<Figure> cell1 = new Cell<>(figure, (char) (j - 1), i);
                if (figure != null && !gameField.getCellByFigure().containsValue(cell1)){
                    gameField.getCellByFigure().put(figure, cell1);
                }
                figure = chooseFigure(matrix[i][(j - 65)]);
                Cell<Figure> cell2 = new Cell<>(figure, j, i);
                if (figure != null && !gameField.getCellByFigure().containsValue(cell2)){
                    gameField.getCellByFigure().put(figure, cell2);
                }
                figure = chooseFigure(matrix[i - 1][(j - 66)]);
                Cell<Figure> cell3 = new Cell<>(figure, (char) (j - 1), i - 1);
                if (figure != null && !gameField.getCellByFigure().containsValue(cell3)){
                    gameField.getCellByFigure().put(figure, cell3);
                }
                figure = chooseFigure(matrix[i - 1][(j - 65)]);
                Cell<Figure> cell4 = new Cell<>(figure, j, i - 1);
                if (figure != null && !gameField.getCellByFigure().containsValue(cell4)){
                    gameField.getCellByFigure().put(figure, cell4);
                }

                gameField.addEdge(cell1, cell2);
                gameField.addEdge(cell2, cell1);

                gameField.addEdge(cell1, cell3);
                gameField.addEdge(cell3, cell1);

                gameField.addEdge(cell2, cell4);
                gameField.addEdge(cell4, cell2);

                gameField.addEdge(cell3, cell4);
                gameField.addEdge(cell4, cell3);
            }
        }
        char[] delX = {'A', 'B', 'F', 'G'};
        int[] delY = {0,  5, 1, 6};
        for (int y : delY) {
            for (char x : delX) {
                gameField.remove(new Cell<Figure>(null, x, y));
            }
        }
        for (Cell<Figure> c :
                gameField.getCells()) {
            if (c.getFigure() != null) {
                if (c.getFigure().getClass() == Fox.class) gameField.getFoxes().add((Fox) c.getFigure());
                else gameField.getGeese().add((Goose) c.getFigure());
            }
        }
        return gameField;
    }

    private Figure chooseFigure(int i) {
        if (i == 0 || i == 1) return null;
        else if (i == 2) return new Goose();
        else return new Fox();
    }

    public void printField(LogicGameField gameField) {
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

    public void printWinner(GameProcess gameProcess){
        System.out.println();
        System.out.println();
        System.out.println("===================================");
        if (gameProcess.isFoxWin()) System.out.println("======== FOX IS THE WINNER ========");
        else System.out.println("======= GEESE ARE WINNER ==========");
        System.out.println("===================================");
    }

    private boolean canBeatCell(ArrayList<Fox> foxes, Cell<Figure> cell){
        for (Fox f :
                foxes) {
            if (f.getPossibleBeat().containsKey(cell)) return true;
        }
        return false;
    }
    private boolean canMoveCell(ArrayList<Fox> foxes, Cell<Figure> cell){
        for (Fox f :
                foxes) {
            if (f.getPossibleMoves().contains(cell)) return true;
        }
        return false;
    }
}
