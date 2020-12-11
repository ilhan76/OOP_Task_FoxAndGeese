package gameFild;

import figures.Figure;
import figures.Fox;
import figures.Goose;

import java.util.ArrayList;
import java.util.HashMap;

public class LogicGameField extends Graph<Figure>{
    private  HashMap<Figure, Cell<Figure>> cellByFigure = new HashMap<>();
    private  ArrayList<Goose> geese = new ArrayList<>();
    private  ArrayList<Fox> foxes = new ArrayList<>();

    public LogicGameField(){}

    public ArrayList<Goose> getGeese() {
        return geese;
    }

    public ArrayList<Fox> getFoxes() {
        return foxes;
    }

    public HashMap<Figure, Cell<Figure>> getCellByFigure() {
        return cellByFigure;
    }

    public void setCellByFigure(HashMap<Figure, Cell<Figure>> cellByFigure) {
        this.cellByFigure = cellByFigure;
    }

    public void setGeese(ArrayList<Goose> geese) {
        this.geese = geese;
    }

    public void setFoxes(ArrayList<Fox> foxes) {
        this.foxes = foxes;
    }
}
