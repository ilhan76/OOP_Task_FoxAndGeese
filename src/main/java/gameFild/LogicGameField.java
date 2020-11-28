package gameFild;

import figures.Figure;
import figures.Fox;
import figures.Goose;

import java.util.ArrayList;
import java.util.HashMap;

public class LogicGameField extends Graph<Figure>{
    private final HashMap<Figure, Cell<Figure>> cellByFigure = new HashMap<>();
    private final ArrayList<Goose> geese = new ArrayList<>();
    private final ArrayList<Fox> foxes = new ArrayList<>();

    public ArrayList<Goose> getGeese() {
        return geese;
    }

    public ArrayList<Fox> getFoxes() {
        return foxes;
    }

    public HashMap<Figure, Cell<Figure>> getCellByFigure() {
        return cellByFigure;
    }
}
