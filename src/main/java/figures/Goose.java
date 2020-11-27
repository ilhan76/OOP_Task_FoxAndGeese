package figures;

import gameFild.Cell;

import java.util.ArrayList;
import java.util.Objects;


public class Goose implements Figure {
    private ArrayList<Cell<Figure>> possibleMoves = new ArrayList<>();

    @Override
    public ArrayList<Cell<Figure>> getPossibleMoves() {
        return possibleMoves;
    }

    @Override
    public void setPossibleMoves(ArrayList<Cell<Figure>> newSet) {
        possibleMoves = newSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goose goose = (Goose) o;
        return Objects.equals(possibleMoves, goose.possibleMoves);
    }

    @Override
    public String toString() {
        return "Goose " + hashCode();
    }
}
