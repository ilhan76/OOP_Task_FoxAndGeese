package figures;

import gameFild.Cell;
import java.util.ArrayList;
import java.util.List;


public interface Figure {
    List<Cell> getPossibleMoves();
    void setPossibleMoves(List<Cell> newSet);
    Cell getCell();
    void setCell(Cell cell);
    //TODO: добавить ссылку на клетку
    //TODO: возможно удалить список ходов
}
