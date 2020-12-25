package services;


import figures.Figure;
import figures.Fox;
import figures.Goose;
import gameFild.Cell;
import gameFild.Direction;
import gameFild.LogicGameField;
import graphics.ConsoleOutputType;

import java.util.*;

public class FoxService {

    public void move(LogicGameField gameField, Cell nextCell, Fox fox, boolean isBeat) {
        boolean canContinue;
        //System.out.println(gameField.getCellByFigure().get(fox).toString() + " -> " + nextCell.toString());
        for (Cell cell :
                gameField.getCells()) {
            /*if (cell.equals(gameField.getCellByFigure().get(fox))) {
                cell.setFigure(null);
                nextCell.setFigure(fox);

                gameField.getCellByFigure().put(fox, nextCell);
                break;
            }*/
            if (cell.equals(fox.getCell())){
                fox.setCell(nextCell);
            }
        }
        if (isBeat) {
            gameField.getGeese().remove(fox.getPossibleBeat().get(nextCell));

            /*Cell cell = gameField.getCellByFigure().get(fox.getPossibleBeat().get(nextCell));
            cell.setFigure(null);*/

            findPossibleMoves(fox, gameField);
            FieldService fieldService = new FieldService();
            fieldService.printField(gameField, ConsoleOutputType.FOX);
            canContinue = fox.getPossibleBeat().size() != 0;
            if (canContinue) {
                for (Cell nCell: fox.getPossibleBeat().keySet()) {
                    move(gameField, nCell, fox, true);
                    break;
                }
            }
        }
    }

    public void findPossibleMoves(Fox fox, LogicGameField gameField) {
        ArrayList<Cell> possibleMoves = new ArrayList<>();
        TreeMap<Cell, Goose> possibleBeat = new TreeMap<>();

        /*for (Cell curCell :
                gameField.getCellByFigure().get(fox).getAdjCell()) {
            findMoveByDirection(possibleMoves, possibleBeat, curCell, findDirection(curCell, gameField.getCellByFigure().get(fox)), false);
        }*/
        for (Cell curCell :
                fox.getCell().getAdjCell()) {
            findMoveByDirection(gameField, possibleMoves, possibleBeat, curCell, findDirection(curCell, fox.getCell()), false);
        }
        fox.setPossibleMoves(possibleMoves);
        fox.setPossibleBeat(possibleBeat);
        System.out.println("Possible moves: " + fox.getPossibleMoves().toString());
        System.out.println("Possible beat: " + fox.getPossibleBeat().keySet().toString());
        System.out.println();
    }

    private Cell getSellWithDirection(Set<Cell> cells, Cell to, Direction direction) {
        for (Cell cell :
                cells) {
            if (findDirection(cell, to) == direction) return cell;
        }
        return null;
    }

    private Direction findDirection(Cell from, Cell to) {
        if (from.getY() - to.getY() == 0) {
            if (from.getX() - to.getX() > 0) {
                return Direction.LEFT;
            } else {
                return Direction.RIGHT;
            }
        } else {
            if (from.getY() - to.getY() > 0) {
                return Direction.DOWN;
            } else {
                return Direction.UP;
            }
        }
    }

    private void findMoveByDirection(LogicGameField gf, List<Cell> pMoves, TreeMap<Cell, Goose> pBeat,
                                     Cell cell, Direction d, boolean alreadyBeaten) {
        Cell nextCell = getSellWithDirection(cell.getAdjCell(), cell, d);
        /*if (cell.getFigure() == null) {
            if (!alreadyBeaten) {
                pMoves.add(cell);
            } else {
                Goose g = pBeat.get(pBeat.lastKey());
                pBeat.put(cell, g);
                alreadyBeaten = true;
            }
            if (nextCell != null)
                findMoveByDirection(gf, pMoves, pBeat, nextCell, d, alreadyBeaten);
        } else if (nextCell != null && cell.getFigure().getClass() == Goose.class && nextCell.getFigure() == null) {
            pBeat.put(nextCell, (Goose) cell.getFigure());
            findMoveByDirection(gf, pMoves, pBeat, nextCell, d, true);
        }*/
        if (!isFigureByCell(gf, cell)){
            if (!alreadyBeaten) {
                pMoves.add(cell);
            } else {
                Goose g = pBeat.get(pBeat.lastKey());
                pBeat.put(cell, g);
                alreadyBeaten = true;
            }
            if (nextCell != null)
                findMoveByDirection(gf, pMoves, pBeat, nextCell, d, alreadyBeaten);
        } else if (nextCell != null && findFigure(gf, cell).getClass() == Goose.class && findFigure(gf, nextCell) == null) {
            pBeat.put(nextCell, (Goose) findFigure(gf, cell));
            findMoveByDirection(gf, pMoves, pBeat, nextCell, d, true);
        }
    }

    public void randomMove(LogicGameField gameField) {
        findPossibleMoves(gameField.getFoxes().get((int) (Math.random() * gameField.getFoxes().size())), gameField);
        FieldService fieldService = new FieldService();
        fieldService.printField(gameField, ConsoleOutputType.FOX);
        if (gameField.getFoxes().get((int) (Math.random() * gameField.getFoxes().size())).getPossibleBeat().size() > 0)
            beat(gameField);
        else
            move(gameField);
    }

    private void move(LogicGameField gameField) {
        Fox fox;
        List<Cell> moves;
        do {
            fox = gameField.getFoxes().get((int) (Math.random() * gameField.getFoxes().size()));
            moves = fox.getPossibleMoves();
        } while (moves.size() == 0);

        Cell nextCell = moves.get((int) (Math.random() * moves.size()));
        move(gameField, nextCell, fox, false);
    }

    private void beat(LogicGameField gameField) {
        Fox fox;
        Map<Cell, Goose> moves;
        do {
            fox = gameField.getFoxes().get((int) (Math.random() * gameField.getFoxes().size()));
            moves = fox.getPossibleBeat();
        } while (moves.size() == 0);

        ArrayList<Cell> listMoves = new ArrayList<>(moves.keySet());
        Cell nextCell = listMoves.get((int) (Math.random() * moves.size()));
        move(gameField, nextCell, fox, true);
    }

    private boolean isFigureByCell(LogicGameField gf, Cell cell){
        for (Figure f :
                gf.getGeese()) {
            if (f.getCell().equals(cell))
                return true;
        }
        for (Figure f :
                gf.getFoxes()) {
            if (f.getCell().equals(cell))
                return true;
        }
        return false;
    }
    private Figure findFigure(LogicGameField gf, Cell c){
        for (Figure f :
                gf.getGeese()) {
            if (f.getCell().equals(c)) return f;
        }
        for (Figure f :
                gf.getFoxes()) {
            if (f.getCell().equals(c)) return f;
        }
        return null;
    }

    public boolean isPassiveWin(LogicGameField gameField) {
        GooseService gs = new GooseService();
        for (Goose g :
                gameField.getGeese()) {
            gs.findPossibleMoves(g, gameField);
            if (!(g.getPossibleMoves().size() == 0)) {
                return false;
            }
        }
        return true;
    }

    public boolean isLoss(LogicGameField gameField) {
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
