package sample.figures;

import sample.gameFild.Cell;
import sample.gameFild.LogicGameField;

import java.util.HashSet;

public class Goose implements Figure {
    private LogicGameField LGameField;
    private Cell currentCell;
    private HashSet<Cell> possibleMoves = new HashSet<>();

    public Goose(LogicGameField gameField) {
        LGameField = gameField;
    }

    @Override
    public Cell getCurrentCell() {
        return currentCell;
    }

    @Override
    public void setCurrentCell(Cell cell) {
        currentCell = cell;
    }

    @Override
    public HashSet<Cell> getPossibleMoves() {
        return possibleMoves;
    }

    @Override
    public void setPossibleMoves(HashSet<Cell> newSet) {
        possibleMoves = newSet;
    }

    @Override
    public LogicGameField getField() {
        return LGameField;
    }

    @Override
    public void setField(LogicGameField logicGameField) {
        LGameField = logicGameField;
    }
}
