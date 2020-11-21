package sample;

import sample.gameFild.LogicGameField;
import sample.services.FieldService;
import sample.services.GameService;


public class Main {


    public static void main(String[] args) throws InterruptedException {
        int[][] matrix = {
                {0, 0, 1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {1, 1, 1, 3, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 2},
                {0, 0, 2, 2, 2, 0, 0},
                {0, 0, 2, 2, 2, 0, 0}
        };
        LogicGameField gameField = FieldService.makeGameField(matrix);
        GameService gameService = new GameService();
        gameService.startGame(new GameProcess(gameField));
    }
}
