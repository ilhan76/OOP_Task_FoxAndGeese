package gameFild;

import com.fasterxml.jackson.annotation.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Graph {

    Graph(){}

    /*@JsonCreator
    Graph(String str, Set<Cell> map){
        Cell cell = new Cell(str);
        cell.setAdjCell(map);
        cells.add(cell);
    }*/
    @JsonIgnore
    protected final Set<Cell> cells = new TreeSet<>();

    public void addVertex(Cell cell) {
        cells.add(cell);
    }

    public void addEdge(Cell cell1, Cell cell2) {
        if (!cells.contains(cell1)) {
            cell1.addAdjVertex(cell2, cells);
            cells.add(cell1);
        } else {
            for (Cell gfv :
                    cells) {
                if (gfv.equals(cell1)) {
                    gfv.addAdjVertex(cell2, cells);
                    break;
                }
            }
        }
    }

    public void remove(Cell cell){
        for (Cell vertex :
                cells) {
            if (vertex.equals(cell)) {
                for (Cell c :
                        cells) {
                    c.getAdjCell().remove(vertex);
                }
                cells.remove(vertex);
                return;
            }
        }
    }

    public Set<Cell> getCells(){
        return cells;
    }

    @JsonGetter
    public Map<Cell, Set<Cell>> getMap(){
        Map<Cell, Set<Cell>> mapGraph = new TreeMap<>();
        for (Cell curCell : cells) {
            mapGraph.put(curCell, curCell.getAdjCell());
        }
        return mapGraph;
    }

    /*@JsonSetter
    public void mapToGraph(String cellJson, TreeSet<Cell> set){

        Cell cell = new Cell(cellJson);
        cell.setAdjCell(set);
        cells.add(cell);
    }*/

    /*@JsonSetter
    public void mapToGraph(Map<String, Set<Cell>> map){
        System.out.println("-=-=-=-");
        for (String s: map.keySet()) {
            Cell cell = new Cell();
            cell.setAdjCell(map.get(s));
            cells.add(cell);
        }
    }*/
}
