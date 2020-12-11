package gameFild;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import figures.Figure;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Graph<T> {

    Graph(){}

    @JsonIgnore
    private final TreeSet<Cell<T>> cells = new TreeSet<>();

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
                    c.getAdjCell().remove(vertex);
                }
                cells.remove(vertex);
                return;
            }
        }
    }

    public TreeSet<Cell<T>> getCells(){
        return cells;
    }

    @JsonAnyGetter
    public Map<Cell<T>, TreeSet<Cell<T>>> toMap(){
        Map<Cell<T>, TreeSet<Cell<T>>> mapGraph = new TreeMap<>();
        for (Cell<T> curCell : cells) {
            mapGraph.put(curCell, curCell.getAdjCell());
        }
        return mapGraph;
    }

    /*@JsonAnySetter
    public void mapToGraph(Map<Cell<T>, TreeSet<Cell<T>>> map){
        for (Cell<T> curCell :
                map.keySet()) {
            curCell.setAdjCell(map.get(curCell));
        }
    }*/

    @JsonAnySetter
    public void mapToGraph(String cellJson, TreeSet<Cell<Figure>> set){
        //Cell<Figure> cell = new Cell<>(cellJson);
        //cell.setAdjCell(set);
    }
}
