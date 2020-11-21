package sample.figures;

import sample.gameFild.Cell;
import sample.gameFild.LogicGameField;
import java.util.ArrayList;
import java.util.TreeMap;

public class Fox implements Figure {
    private LogicGameField gameField;
    private Cell<Figure> currentCell;
    private ArrayList<Cell<Figure>> possibleMoves = new ArrayList<>();
    private TreeMap<Cell<Figure>, Goose> possibleBeat = new TreeMap<>();

    public Fox(LogicGameField gameField) {
        this.gameField = gameField;
    }

    @Override
    public Cell<Figure> getCurrentCell() {
        return currentCell;
    }

    @Override
    public void setCurrentCell(Cell<Figure> cell) {
        currentCell = cell;
    }

    @Override
    public ArrayList<Cell<Figure>> getPossibleMoves() {
        return possibleMoves;
    }

    @Override
    public void setPossibleMoves(ArrayList<Cell<Figure>> newSet) {
        possibleMoves = newSet;
    }

    public TreeMap<Cell<Figure>, Goose> getPossibleBeat() {
        return possibleBeat;
    }

    public void setPossibleBeat(TreeMap<Cell<Figure>, Goose> possibleBeat) {
        this.possibleBeat = possibleBeat;
    }

    @Override
    public LogicGameField getField() {
        return gameField;
    }

    @Override
    public void setField(LogicGameField logicGameField) {
        gameField = logicGameField;
    }

}
