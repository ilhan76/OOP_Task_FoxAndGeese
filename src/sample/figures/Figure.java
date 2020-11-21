package sample.figures;

import sample.gameFild.Cell;
import sample.gameFild.LogicGameField;
import java.util.ArrayList;


public interface Figure {
    Cell<Figure> getCurrentCell();
    void setCurrentCell(Cell<Figure> cell);
    ArrayList<Cell<Figure>> getPossibleMoves();
    void setPossibleMoves(ArrayList<Cell<Figure>> newSet);
    LogicGameField getField();
    void setField(LogicGameField logicGameField);
}
