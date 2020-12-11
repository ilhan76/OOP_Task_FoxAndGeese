package gameFild;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.TreeSet;

public class Cell<Figure> implements Comparable<Cell<Figure>> {
    private Figure figure;
    private final int y;
    private final char x;
    @JsonIgnore
    private TreeSet<Cell<Figure>> adjCell = new TreeSet<>();

    public Cell(Figure figure, char x, int y) {
        this.figure = figure;
        this.y = y;
        this.x = x;
    }

    /*public Cell(String json){

    }*/

    void addAdjVertex(Cell<Figure> cell, TreeSet<Cell<Figure>> vertices) {
        for (Cell<Figure> v :
                vertices) {
            if (v.equals(cell)){
                adjCell.add(v);
                return;
            }
        }
        adjCell.add(cell);
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public TreeSet<Cell<Figure>> getAdjCell() {
        return adjCell;
    }

    public void setAdjCell(TreeSet<Cell<Figure>> adjCell) {
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
        return ("|" + y + "" + x + "|" /*+ figureToString()*/).compareTo("|" + o.y + "" + o.x + "|" /*+ o.figureToString()*/);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        } else {
            Cell<Figure> cell = (Cell<Figure>) obj;
            return this.y == cell.y && this.x == cell.x;
        }
    }

    @Override
    public String toString() {
        return "Cell{" +
                "figure=" + figure +
                ", y=" + y +
                ", x=" + x +
                '}';
    }
}
