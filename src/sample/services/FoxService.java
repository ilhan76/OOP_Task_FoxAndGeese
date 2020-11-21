package sample.services;

import sample.figures.Figure;
import sample.figures.Fox;
import sample.figures.Goose;
import sample.gameFild.Cell;
import sample.gameFild.Direction;
import sample.gameFild.LogicGameField;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class FoxService {

    public void move(Cell<Figure> nextCell, Fox fox, boolean isBeat) {
        boolean canContinue;
        LogicGameField gameField = fox.getField();
        for (Cell<Figure> cell :
                gameField.getCells()) {
            if (cell.equals(fox.getCurrentCell())) {
                cell.setFigure(null);
                nextCell.setFigure(fox);
                fox.setCurrentCell(nextCell);
            }
        }
        if (isBeat){
            gameField.removeGoose(fox.getPossibleBeat().get(nextCell));
            fox.getPossibleBeat().get(nextCell).getCurrentCell().setFigure(null);

            findPossibleMoves(fox);
            canContinue = fox.getPossibleBeat().size() != 0;
            if (canContinue){
                TreeMap<Cell<Figure>, Goose> moves = fox.getPossibleBeat();
                ArrayList<Cell<Figure>> listMoves = new ArrayList<>(moves.keySet());
                Cell<Figure> nCell = listMoves.get((int)(Math.random() * moves.size()));
                move(nCell, fox, true);
            }
        }
    }

    public void findPossibleMoves(Fox fox) {
        ArrayList<Cell<Figure>> possibleMoves = new ArrayList<>();
        TreeMap<Cell<Figure>, Goose> possibleBeat = new TreeMap<>();

        for (Cell<Figure> curCell :
                fox.getCurrentCell().getAdjCell()) {
            findMoveByDirection(possibleMoves, possibleBeat, curCell, findDirection(curCell, fox.getCurrentCell()));
        }
        fox.setPossibleMoves(possibleMoves);
        fox.setPossibleBeat(possibleBeat);
    }

    private Cell<Figure> getSellWithDirection(TreeSet<Cell<Figure>> cells, Cell<Figure> to, Direction direction){
        for (Cell<Figure> cell :
                cells) {
            if (findDirection(cell, to) == direction) return cell;
        }
        return null;
    }

    private Direction findDirection(Cell<Figure> from, Cell<Figure> to){
        if (from.getY() - to.getY() == 0){
            if (from.getX() - to.getX() > 0){
                return Direction.LEFT;
            } else {
                return Direction.RIGHT;
            }
        } else {
            if (from.getY() - to.getY() > 0){
                return Direction.DOWN;
            } else {
                return Direction.UP;
            }
        }
    }

    private void findMoveByDirection(ArrayList<Cell<Figure>> possibleMoves, TreeMap<Cell<Figure>, Goose> possibleBeat, Cell<Figure> cell, Direction d){
        Cell<Figure> nextCell = getSellWithDirection(cell.getAdjCell(), cell, d);
        if (cell.getFigure() == null) {
            possibleMoves.add(cell);
            if (nextCell != null)
                findMoveByDirection(possibleMoves, possibleBeat, nextCell, d);
        } else if (nextCell != null && cell.getFigure().getClass() == Goose.class && nextCell.getFigure() == null){
            possibleBeat.put(nextCell, (Goose) cell.getFigure());
            findMoveByDirection(possibleMoves, possibleBeat, nextCell, d);
        }
    }

    public void randomMove(LogicGameField gameField){
        findPossibleMoves(gameField.getFoxes().get((int) (Math.random() * gameField.getFoxes().size())));
        if(gameField.getFoxes().get((int) (Math.random() * gameField.getFoxes().size())).getPossibleBeat().size() > 0){
            beat(gameField);
        } else
            move(gameField);
    }

    private void move(LogicGameField gameField){
        Fox fox;
        ArrayList<Cell<Figure>> moves;
        do {
            fox = gameField.getFoxes().get((int) (Math.random() * gameField.getFoxes().size()));
            findPossibleMoves(fox);
            moves = fox.getPossibleMoves();
        } while (moves.size() == 0);

        Cell<Figure> nextCell = moves.get((int)(Math.random() * moves.size()));
        move(nextCell, fox, false);
    }
    private void beat(LogicGameField gameField){
        Fox fox;
        TreeMap<Cell<Figure>, Goose> moves;
        do {
            fox = gameField.getFoxes().get((int) (Math.random() * gameField.getFoxes().size()));
            findPossibleMoves(fox);
            moves = fox.getPossibleBeat();
        } while (moves.size() == 0);

        ArrayList<Cell<Figure>> listMoves = new ArrayList<>(moves.keySet());
        Cell<Figure> nextCell = listMoves.get((int)(Math.random() * moves.size()));
        move(nextCell, fox, true);
    }

    public boolean isLoss(LogicGameField gameField){
        for (Fox f :
                gameField.getFoxes()) {
            if (f.getPossibleBeat().size() == 0 && f.getPossibleMoves().size() == 0) {
                return true;
            }
        }
        return false;
    }
}
