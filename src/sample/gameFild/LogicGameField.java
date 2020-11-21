package sample.gameFild;

import sample.figures.Figure;
import sample.figures.Fox;
import sample.figures.Goose;
import java.util.ArrayList;

public class LogicGameField extends Graph<Figure> {
    private final ArrayList<Goose> geese = new ArrayList<>();
    private final ArrayList<Fox> foxes = new ArrayList<>();

    public void addGoose(Goose goose){
        geese.add(goose);
    }
    public void addFox(Fox fox){
        foxes.add(fox);
    }
    public void removeGoose(Goose goose){
        geese.remove(goose);
    }

    public ArrayList<Goose> getGeese() {
        return geese;
    }

    public ArrayList<Fox> getFoxes() {
        return foxes;
    }
}
