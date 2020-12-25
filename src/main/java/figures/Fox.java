package figures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gameFild.Cell;

import java.util.*;

public class Fox implements Figure {
    @JsonIgnore
    private List<Cell> possibleMoves;
    @JsonIgnore
    private Map<Cell, Goose> possibleBeat;
    private Cell cell;

    public Fox(){
        possibleMoves = new ArrayList<>();
        possibleBeat = new TreeMap<>();
    }

    public Fox(Cell cell){
        this.cell = cell;
        possibleMoves = new ArrayList<>();
        possibleBeat = new TreeMap<>();
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

    public Map<Cell, Goose> getPossibleBeat() {
        return possibleBeat;
    }

    public void setPossibleBeat(Map<Cell, Goose> possibleBeat) {
        this.possibleBeat = possibleBeat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fox fox = (Fox) o;
        return Objects.equals(cell, fox.cell);
    }

    @Override
    public int hashCode() {
        return Objects.hash(possibleMoves, possibleBeat, cell);
    }

    @Override
    public String toString() {
        return "Fox " + cell;
    }
}
