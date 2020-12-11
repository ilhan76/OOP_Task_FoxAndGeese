package figures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gameFild.Cell;
import java.util.ArrayList;


public class Goose implements Figure {
    //@JsonIgnore
    private ArrayList<Cell<Figure>> possibleMoves;

    public Goose(){
        possibleMoves = new ArrayList<>();
    }

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
