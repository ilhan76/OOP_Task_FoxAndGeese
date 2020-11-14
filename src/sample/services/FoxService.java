package sample.services;

import sample.figures.Figure;
import sample.figures.Fox;
import sample.gameFild.Cell;

import java.util.HashSet;

public class FoxService {

    public void move(Cell nextCell, Fox fox) {

    }

    public void findPossibleMoves(Fox fox) {
        HashSet<Cell> possibleMoves = new HashSet<>();
        // logic
        fox.setPossibleMoves(possibleMoves);
    }
}
