package sample.services;

import sample.figures.Figure;
import sample.figures.Goose;
import sample.gameFild.Cell;
import sample.gameFild.LogicGameField;
import java.util.ArrayList;

public class GooseService {

    public void move(Cell<Figure> nextCell, Goose goose) {
        findPossibleMoves(goose);
        if(!goose.getPossibleMoves().contains(nextCell)) return;
        LogicGameField gameField = goose.getField();
        //System.out.println(goose.getCurrentCell().toString() + "->" + nextCell.toString());
        for (Cell<Figure> cell :
                gameField.getCells()) {
            if (cell.equals(goose.getCurrentCell())) {
                cell.setFigure(null);
                nextCell.setFigure(goose);
                goose.setCurrentCell(nextCell);
            }
        }

    }

    public void findPossibleMoves(Goose goose) {
        ArrayList<Cell<Figure>> possibleMoves = new ArrayList<>();
        for (Cell<Figure> c :
                goose.getCurrentCell().getAdjCell()) {
            if (c.getFigure() == null && goose.getCurrentCell().getY() - c.getY() >= 0) possibleMoves.add(c);
        }
        goose.setPossibleMoves(possibleMoves);
    }

    public void randomMove(LogicGameField gameField){
        Goose goose;
        ArrayList<Cell<Figure>> moves;
        if (gameField.getGeese().size() == 0) return;
        do {
            goose = gameField.getGeese().get((int) (Math.random() * gameField.getGeese().size()));
            findPossibleMoves(goose);
            moves = goose.getPossibleMoves();
        } while (moves.size() == 0);

        Cell<Figure> nextCell = moves.get((int)(Math.random() * moves.size()));
        move(nextCell, goose);
    }

    public boolean isLoss(LogicGameField gameField){
        return gameField.getGeese().size() < 6;
    }
}
