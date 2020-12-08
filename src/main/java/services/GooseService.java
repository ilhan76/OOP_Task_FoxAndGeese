package services;

import figures.Figure;
import figures.Goose;
import gameFild.Cell;
import gameFild.LogicGameField;
import graphics.ConsoleOutputType;

import java.util.ArrayList;

public class GooseService {

    public void move(LogicGameField gameField, Cell<Figure> nextCell, Goose goose) {
        System.out.println(gameField.getCellByFigure().get(goose).toString() + " -> " + nextCell.toString());
        if(!goose.getPossibleMoves().contains(nextCell)) return;
        for (Cell<Figure> cell :
                gameField.getCells()) {
            if (cell.equals(gameField.getCellByFigure().get(goose))) {
                cell.setFigure(null);
                nextCell.setFigure(goose);
                gameField.getCellByFigure().put(goose, nextCell);
            }
        }
    }

    public void findPossibleMoves(Goose goose, LogicGameField gameField) {
        ArrayList<Cell<Figure>> possibleMoves = new ArrayList<>();
        for (Cell<Figure> c :
                gameField.getCellByFigure().get(goose).getAdjCell()) {
            if (c.getFigure() == null && gameField.getCellByFigure().get(goose).getY() - c.getY() >= 0) possibleMoves.add(c);
        }
        goose.setPossibleMoves(possibleMoves);
        //System.out.println("Possible moves (Geese): " + goose.getPossibleMoves().toString());
        //System.out.println();
    }

    public void randomMove(LogicGameField gameField){
        Goose goose;
        ArrayList<Cell<Figure>> moves;
        if (gameField.getGeese().size() == 0) return;
        for (Goose g :
                gameField.getGeese()) {
            findPossibleMoves(g, gameField);
        }
        do {
            goose = gameField.getGeese().get((int) (Math.random() * gameField.getGeese().size()));
            //if (!gameField.getCellByFigure().containsKey(goose)){
            //    System.out.println(gameField.getGeese());
            //}
            //findPossibleMoves(goose, gameField);
            moves = goose.getPossibleMoves();
        } while (moves.size() == 0);
        FieldService fieldService = new FieldService();
        fieldService.printField(gameField, ConsoleOutputType.GOOSE);

        Cell<Figure> nextCell = moves.get((int)(Math.random() * moves.size()));
        move(gameField, nextCell, goose);
    }

    public boolean isLoss(LogicGameField gameField){
        return gameField.getGeese().size() < 6;
    }
}
