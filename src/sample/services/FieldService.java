package sample.services;

import sample.figures.Figure;
import sample.figures.Fox;
import sample.figures.Goose;
import sample.gameFild.Cell;
import sample.gameFild.LogicGameField;

public class FieldService {

    public Cell[][] createField(){
        Cell[][] field = new Cell[7][7];


        return field;
    }

    private Figure chooseFigure(int i, LogicGameField lgf){
        if (i == 0 || i == 1) return null;
        else if (i == 2) return new Goose(lgf);
        else return new Fox(lgf);
    }

    public static void print(int[][] field) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (field[i][j] == 0) System.out.print("\033[0;100m");
                else if ((j + i) % 2 == 0) System.out.print("\u001B[40m");
                else System.out.print("\u001B[47m");

                if (field[i][j] % 2 == 0) System.out.print("\033[1;92m");
                else System.out.print("\033[1;91m");
                if (field[i][j] == 0 || field[i][j] == 1) {
                    System.out.print("   ");
                    continue;
                }
                System.out.printf(" %s ", field[i][j]);
            }
            System.out.print("\033[0m");
            System.out.println();
        }
    }
}
