package figures;

import gameFild.Cell;
import java.util.ArrayList;


public interface Figure {
    //Cell<Figure> getCurrentCell();
    //void setCurrentCell(Cell<Figure> cell);
    ArrayList<Cell<Figure>> getPossibleMoves();
    void setPossibleMoves(ArrayList<Cell<Figure>> newSet);
}
