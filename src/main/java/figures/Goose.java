package figures;

import gameFild.Cell;
import java.util.ArrayList;


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
    public String toString() {
        return "Goose " + hashCode();
    }
}
