package figures;

import gameFild.Cell;
import java.util.ArrayList;
import java.util.TreeMap;

public class Fox implements Figure {
    private ArrayList<Cell<Figure>> possibleMoves = new ArrayList<>();
    private TreeMap<Cell<Figure>, Goose> possibleBeat = new TreeMap<>();

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
