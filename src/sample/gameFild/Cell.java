package sample.gameFild;

import sample.figures.Figure;

public class Cell implements Comparable<Cell>{
    Figure figure;
    int y;
    char x;

    public Cell(Figure figure, char x, int y) {
        this.figure = figure;
        this.y = y;
        this.x = x;
    }


    @Override
    public int compareTo(Cell o) {
        return 0;
    }  //допилить
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        } else {
            Cell cell = (Cell) obj;
            return this.y == cell.y && this.x == cell.x;
        }
    }
}
