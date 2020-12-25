package gameFild;


import com.fasterxml.jackson.annotation.JsonAnySetter;
import figures.Figure;
import figures.Fox;
import figures.Goose;

import java.util.*;

public class LogicGameField extends Graph{
    private  List<Goose> geese = new ArrayList<>();
    private  List<Fox> foxes = new ArrayList<>();

    public LogicGameField(){}

    public List<Goose> getGeese() {
        return geese;
    }

    public List<Fox> getFoxes() {
        return foxes;
    }

    public void setGeese(List<Goose> geese) {
        this.geese = geese;
    }

    public void setFoxes(List<Fox> foxes) {
        this.foxes = foxes;
    }

    /*@JsonAnySetter
    public void putCellByFigure(String cell, Figure figure){
        *//*String[] strings = cell.split(" ");
        if (strings[0].equals("Fox")) cellByFigure.put(new Fox(), cell);
        else if (strings[0].equals("Goose")) cellByFigure.put(new Goose(), cell);*//*

        String[] str = cell.split(" ");
        for (String s : str) {
            Cell c = new Cell(s);
            //cellByFigure.put(figure, c);
        }
    }*/
    @JsonAnySetter
    public void desGraph(String s, Set<Cell> set){
        Cell cell = new Cell(s);
        cell.setAdjCell(set);
        cells.add(cell);
    }
}
