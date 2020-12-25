package services;

import figures.Fox;
import figures.Goose;
import gameFild.Cell;
import gameFild.LogicGameField;
import graphics.ConsoleOutputType;

import java.util.ArrayList;
import java.util.List;

public class GooseService {

    public void move(LogicGameField gameField, Cell nextCell, Goose goose) {
        //System.out.println(gameField.getCellByFigure().get(goose).toString() + " -> " + nextCell.toString());
        if(!goose.getPossibleMoves().contains(nextCell)) return;
        for (Cell cell :
                gameField.getCells()) {
            if (cell.equals(goose.getCell())){
                goose.setCell(nextCell);
            }
        }
    }

    public void findPossibleMoves(Goose goose, LogicGameField gameField) {
        ArrayList<Cell> possibleMoves = new ArrayList<>();
        boolean can;
        for (Cell c :
                goose.getCell().getAdjCell()) {
            can = true;
            for (Goose g : gameField.getGeese()) {
                if (g.getCell().equals(c)) {
                    can = false;
                    break;
                }
            }
            for (Fox f : gameField.getFoxes()) {
                if (f.getCell().equals(c)) can = false;
            }
            if (can) possibleMoves.add(c);
        }
        goose.setPossibleMoves(possibleMoves);
        //System.out.println(goose);
        //System.out.println("Possible moves (Geese): " + goose.getPossibleMoves().toString());
        //System.out.println();
    }

    public void randomMove(LogicGameField gameField){
        Goose goose;
        List<Cell> moves;
        if (gameField.getGeese().size() == 0) return;
        for (Goose g :
                gameField.getGeese()) {
            findPossibleMoves(g, gameField);
        }
        do {
            goose = gameField.getGeese().get((int) (Math.random() * gameField.getGeese().size()));
            moves = goose.getPossibleMoves();
        } while (moves.size() == 0);
        FieldService fieldService = new FieldService();
        fieldService.printField(gameField, ConsoleOutputType.GOOSE);
        System.out.println(goose + " => " + goose.getPossibleMoves());

        Cell nextCell = moves.get((int)(Math.random() * moves.size()));
        move(gameField, nextCell, goose);
    }

    public boolean isLoss(LogicGameField gameField){
        return gameField.getGeese().size() < 6;
    }
}
