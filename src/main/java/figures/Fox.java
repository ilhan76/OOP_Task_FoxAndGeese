package figures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gameFild.Cell;
import java.util.ArrayList;
import java.util.TreeMap;

public class Fox implements Figure {
    //@JsonIgnore
    private ArrayList<Cell<Figure>> possibleMoves;
    //@JsonIgnore
    private TreeMap<Cell<Figure>, Goose> possibleBeat;

    public Fox(){
        possibleMoves = new ArrayList<>();
        possibleBeat = new TreeMap<>();
    }

    @Override
    public ArrayList<Cell<Figure>> getPossibleMoves() {
        return possibleMoves;
    }

    @Override
    public void setPossibleMoves(ArrayList<Cell<Figure>> newSet) {
        possibleMoves = newSet;
    }

    public TreeMap<Cell<Figure>, Goose> getPossibleBeat() {
        return possibleBeat;
    }

    public void setPossibleBeat(TreeMap<Cell<Figure>, Goose> possibleBeat) {
        this.possibleBeat = possibleBeat;
    }

    @Override
    public String toString() {
        return "Fox " + hashCode();
    }
}
