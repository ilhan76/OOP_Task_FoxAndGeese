package gameFild;

import figures.Fox;


import java.util.TreeSet;

public class Cell<Figure> implements Comparable<Cell<Figure>> {
    private Figure figure;
    private final int y;
    private final char x;
    TreeSet<Cell<Figure>> adjCell = new TreeSet<>();

    public Cell(Figure figure, char x, int y) {
        this.figure = figure;
        this.y = y;
        this.x = x;
    }

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

    public int getY() {
        return y;
    }

    public char getX() {
        return x;
    }

    public void print(){
        System.out.print(this.toString() + "- {");
        for (Cell<Figure> c :
                adjCell) {
            System.out.print(c.toString());
        }
        System.out.println("}");
    }

    @Override
    public int compareTo(Cell o) {
        return this.toString().compareTo(o.toString());
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
    public String toString(){
        return "|" + y + "" + x + "|" + figureToString();
    }
    private String figureToString(){
        if (figure == null) return "null";
        else if (figure.getClass() == Fox.class) return "Fox";
        else return "Goose";
    }
}
