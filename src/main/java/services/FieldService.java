package services;

import graphics.ConsoleOutputType;
import process.GameProcess;
import figures.Figure;
import figures.Fox;
import figures.Goose;
import gameFild.Cell;
import gameFild.LogicGameField;

import java.util.ArrayList;
import java.util.List;

public class FieldService {

    public LogicGameField makeGameField(int[][] matrix) {
        LogicGameField gameField = new LogicGameField();
        final int fieldSize = 7;
        for (int i = 1; i < fieldSize; i++) {
            for (char j = 'B'; j < 'H'; j++) {
                Figure figure = chooseFigure(matrix[i][(j - 66)]);
                Cell cell1 = new Cell((char) (j - 1), i);
                if (figure != null) addFigure(gameField, figure, cell1);

                figure = chooseFigure(matrix[i][(j - 65)]);
                Cell cell2 = new Cell(j, i);
                if (figure != null) addFigure(gameField, figure, cell2);

                figure = chooseFigure(matrix[i - 1][(j - 66)]);
                Cell cell3 = new Cell((char) (j - 1), i - 1);
                if (figure != null) addFigure(gameField, figure, cell3);

                figure = chooseFigure(matrix[i - 1][(j - 65)]);
                Cell cell4 = new Cell(j, i - 1);
                if (figure != null) addFigure(gameField, figure, cell4);

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
        int[] delY = {0, 5, 1, 6};
        for (int y : delY) {
            for (char x : delX) {
                gameField.remove(new Cell(x, y));
            }
        }
        return gameField;
    }

    private void addFigure(LogicGameField gf, Figure figure, Cell cell){
        figure.setCell(cell);
        if (figure.getClass() == Fox.class && !gf.getFoxes().contains(figure)){
            System.out.println(figure + " " + figure.getCell());
            System.out.println(cell);
            gf.getFoxes().add((Fox) figure);
        }else if (figure.getClass() == Goose.class && !gf.getGeese().contains(figure)){
            System.out.println(figure + " " + figure.getCell());
            gf.getGeese().add((Goose) figure);
        }
    }
    private Figure findFigure(LogicGameField gf, Cell c){
        for (Figure f : gf.getGeese()) {
            if (c.getX() == f.getCell().getX() && c.getY() == f.getCell().getY()) return f;
        }
        for (Figure f : gf.getFoxes()) {
            if (c.getX() == f.getCell().getX() && c.getY() == f.getCell().getY()) return f;
        }
        return null;
    }

    private Figure chooseFigure(int i) {
        if (i == 0 || i == 1) return null;
        else if (i == 2) return new Goose();
        else return new Fox();
    }

    public void printField(LogicGameField gameField, ConsoleOutputType type) {
        int counter = 0;
        System.out.println("==============================");
        for (Cell c :
                gameField.getCells()) {
            if (c.getX() == 'C' && (c.getY() == 0 || c.getY() == 1 || c.getY() == 5 || c.getY() == 6)){
                System.out.print("||||||");
                counter+=2;
            }
            counter++;
            if (type == ConsoleOutputType.FOX){
                if (canBeatCell(gameField.getFoxes(), c))
                    System.out.print("\u001B[41m");
                else if (canMoveCell(gameField.getFoxes(), c))
                    System.out.print("\u001B[43m");
                else if ((c.getX() - 65 + c.getY()) % 2 == 0)
                    System.out.print("\u001B[40m");
                else
                    System.out.print("\u001B[47m");
            }
            else if (type == ConsoleOutputType.GOOSE){
                if (canGooseMoveCell(gameField.getGeese(), c))
                    System.out.print("\u001B[45m");
                else if ((c.getX() - 65 + c.getY()) % 2 == 0)
                    System.out.print("\u001B[40m");
                else
                    System.out.print("\u001B[47m");
            }
            else if ((c.getX() - 65 + c.getY()) % 2 == 0)
                System.out.print("\u001B[40m");
            else
                System.out.print("\u001B[47m");

            if (findFigure(gameField, c) == null) System.out.print("   ");
            else {
                if (findFigure(gameField, c).getClass() == Goose.class) System.out.print("\033[1;92m");
                else System.out.print("\033[1;91m");
                System.out.printf(" %s ", findFigure(gameField, c).getClass() == Fox.class ? 'F' : 'G');
            }
            System.out.print("\033[0;150m");

            if (c.getX() == 'E' && (c.getY() == 0 || c.getY() == 1 || c.getY() == 5 || c.getY() == 6)){
                System.out.print("||||||");
                counter+=2;
            }
            if (counter % 7 == 0) System.out.println();
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

    private boolean canBeatCell(List<Fox> foxes, Cell cell){
        for (Fox f :
                foxes) {
            if (f.getPossibleBeat().containsKey(cell)) return true;
        }
        return false;
    }
    private boolean canMoveCell(List<Fox> foxes, Cell cell){
        for (Fox f :
                foxes) {
            if (f.getPossibleMoves().contains(cell)) return true;
        }
        return false;
    }

    private boolean canGooseMoveCell(List<Goose> geese, Cell cell){
        for (Goose g :
                geese) {
            if (g.getPossibleMoves().contains(cell)) return true;
        }
        return false;
    }
}
