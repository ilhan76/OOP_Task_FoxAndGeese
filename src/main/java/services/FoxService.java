package services;


import figures.Figure;
import figures.Fox;
import figures.Goose;
import gameFild.Cell;
import gameFild.Direction;
import gameFild.LogicGameField;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class FoxService {

    public void move(LogicGameField gameField, Cell<Figure> nextCell, Fox fox, boolean isBeat) {
        boolean canContinue;
        System.out.println(gameField.getCellByFigure().get(fox).toString() + " -> " + nextCell.toString());
        for (Cell<Figure> cell :
                gameField.getCells()) {
            if (cell.equals(gameField.getCellByFigure().get(fox))) {
                cell.setFigure(null);
                nextCell.setFigure(fox);

                gameField.getCellByFigure().put(fox, nextCell);
                break;
            }
        }
        if (isBeat){
            gameField.getGeese().remove(fox.getPossibleBeat().get(nextCell));

            Cell<Figure> cell = gameField.getCellByFigure().get(fox.getPossibleBeat().get(nextCell));
            cell.setFigure(null);

            findPossibleMoves(fox, gameField);
            canContinue = fox.getPossibleBeat().size() != 0;
            if (canContinue){
                Cell<Figure> nCell = fox.getPossibleBeat().firstKey();
                move(gameField, nCell, fox, true);
            }
        }
    }

    public void findPossibleMoves(Fox fox, LogicGameField gameField) {
        ArrayList<Cell<Figure>> possibleMoves = new ArrayList<>();
        TreeMap<Cell<Figure>, Goose> possibleBeat = new TreeMap<>();

        for (Cell<Figure> curCell :
                gameField.getCellByFigure().get(fox).getAdjCell()) {
            findMoveByDirection(possibleMoves, possibleBeat, curCell, findDirection(curCell, gameField.getCellByFigure().get(fox)));
        }
        fox.setPossibleMoves(possibleMoves);
        fox.setPossibleBeat(possibleBeat);
        //System.out.println("Possible moves: " + fox.getPossibleMoves().toString());
        //System.out.println("Possible beat: " + fox.getPossibleBeat().keySet().toString());
        //System.out.println();
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
        findPossibleMoves(gameField.getFoxes().get((int) (Math.random() * gameField.getFoxes().size())), gameField);
        //if ((int)(Math.random() * 2) > 1)
        if(gameField.getFoxes().get((int) (Math.random() * gameField.getFoxes().size())).getPossibleBeat().size() > 0)
            beat(gameField);
        else
            move(gameField);
    }

    private void move(LogicGameField gameField){
        Fox fox;
        ArrayList<Cell<Figure>> moves;
        do {
            fox = gameField.getFoxes().get((int) (Math.random() * gameField.getFoxes().size()));
            moves = fox.getPossibleMoves();
        } while (moves.size() == 0);

        Cell<Figure> nextCell = moves.get((int)(Math.random() * moves.size()));
        move(gameField, nextCell, fox, false);
    }
    private void beat(LogicGameField gameField){
        Fox fox;
        TreeMap<Cell<Figure>, Goose> moves;
        do {
            fox = gameField.getFoxes().get((int) (Math.random() * gameField.getFoxes().size()));
            moves = fox.getPossibleBeat();
        } while (moves.size() == 0);

        ArrayList<Cell<Figure>> listMoves = new ArrayList<>(moves.keySet());
        Cell<Figure> nextCell = listMoves.get((int)(Math.random() * moves.size()));
        move(gameField, nextCell, fox, true);
    }

    public boolean isPassiveWin(LogicGameField gameField){
        GooseService gs = new GooseService();
        for (Goose g :
                gameField.getGeese()) {
            gs.findPossibleMoves(g, gameField);
            if (!(g.getPossibleMoves().size() == 0)){
                return false;
            }
        }
        return true;
    }

    public boolean isLoss(LogicGameField gameField){
        for (Fox f :
                gameField.getFoxes()) {
            findPossibleMoves(f, gameField);
            if (!(f.getPossibleBeat().size() == 0 && f.getPossibleMoves().size() == 0)) {
                return false;
            }
        }
        return true;
    }
}
