package figures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gameFild.Cell;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Goose implements Figure {
    @JsonIgnore
    private List<Cell> possibleMoves;
    private Cell cell;

    public Goose(){
        possibleMoves = new ArrayList<>();
    }

    public Goose(Cell cell){
        this.cell = cell;
        possibleMoves = new ArrayList<>();
    }

    @Override
    public List<Cell> getPossibleMoves() {
        return possibleMoves;
    }
    @Override
    public void setPossibleMoves(List<Cell> newSet) {
        possibleMoves = newSet;
    }

    @Override
    public Cell getCell() {
        return cell;
    }

    @Override
    public void setCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goose goose = (Goose) o;
        return Objects.equals(cell, goose.cell);
    }

    @Override
    public int hashCode() {
        return Objects.hash(possibleMoves, cell);
    }

    @Override
    public String toString() {
        return "Goose " + cell;
    }
}
