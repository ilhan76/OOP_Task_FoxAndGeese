package sample.figures;

import sample.gameFild.Cell;
import sample.gameFild.LogicGameField;

import java.util.HashSet;

public interface Figure {
    Cell getCurrentCell();
    void setCurrentCell(Cell cell);
    HashSet<Cell> getPossibleMoves();
    void setPossibleMoves(HashSet<Cell> newSet);
    LogicGameField getField();
    void setField(LogicGameField logicGameField);
}
