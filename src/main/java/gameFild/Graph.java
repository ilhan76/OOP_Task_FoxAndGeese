package gameFild;

import java.util.TreeSet;

public class Graph<T> {

    protected TreeSet<Cell<T>> cells = new TreeSet<>();

    public void addVertex(Cell<T> cell) {
        cells.add(cell);
    }

    public void addEdge(Cell<T> cell1, Cell<T> cell2) {
        if (!cells.contains(cell1)) {
            cell1.addAdjVertex(cell2, cells);
            cells.add(cell1);
        } else {
            for (Cell<T> gfv :
                    cells) {
                if (gfv.equals(cell1)) {
                    gfv.addAdjVertex(cell2, cells);
                    break;
                }
            }
        }
    }

    public void remove(Cell<T> cell){
        for (Cell<T> vertex :
                cells) {
            if (vertex.equals(cell)) {
                for (Cell<T> c :
                        cells) {
                    c.adjCell.remove(vertex);
                }
                cells.remove(vertex);
                return;
            }
        }
    }

    public TreeSet<Cell<T>> getCells(){
        return cells;
    }
}
