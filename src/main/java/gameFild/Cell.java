package gameFild;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import java.util.TreeSet;

public class Cell implements Comparable<Cell> {
    private  int y;
    private  char x;
    @JsonIgnore
    private Set<Cell> adjCell = new TreeSet<>();

    public Cell(char x, int y) {
        this.y = y;
        this.x = x;
    }

    public Cell(String json){
        //TODO: парсинг строки
        String[] strings = json.split(" ");
        this.y = Integer.parseInt(strings[0]);
        this.x = (strings[1]).charAt(0);
    }

    public Cell(){}

    void addAdjVertex(Cell cell, Set<Cell> vertices) {
        for (Cell v :
                vertices) {
            if (v.equals(cell)){
                adjCell.add(v);
                return;
            }
        }
        adjCell.add(cell);
    }

    public Set<Cell> getAdjCell() {
        return adjCell;
    }

    public void setAdjCell(Set<Cell> adjCell) {
        this.adjCell = adjCell;
    }

    public int getY() {
        return y;
    }

    public char getX() {
        return x;
    }

    /*public void print(){
        System.out.print(this.toString() + "- {");
        for (Cell<Figure> c :
                adjCell) {
            System.out.print(c.toString());
        }
        System.out.println("}");
    }*/

    @Override
    public int compareTo(Cell o) {
        return ("|" + y + "" + x + "|").compareTo("|" + o.y + "" + o.x + "|");
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        } else {
            Cell cell = (Cell) obj;
            return this.y == cell.y && this.x == cell.x;
        }
    }

    @Override
    public String toString() {
        return y + " " + x;
    }
}
