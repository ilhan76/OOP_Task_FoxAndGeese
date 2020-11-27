import gameFild.LogicGameField;
import process.GameProcess;
import services.FieldService;
import services.GameService;

import java.io.IOException;


public class Main {


    public static void main(String[] args) throws InterruptedException, IOException {
        //int[][] matrix = {
        //        {0, 0, 1, 1, 1, 0, 0},
        //        {0, 0, 1, 1, 1, 0, 0},
        //        {2, 1, 3, 1, 3, 1, 2},
        //        {2, 1, 1, 1, 1, 1, 2},
        //        {2, 2, 2, 2, 2, 2, 2},
        //        {0, 0, 2, 2, 2, 0, 0},
        //        {0, 0, 2, 2, 2, 0, 0}
        //};
        int[][] matrix = {
                {0, 0, 1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 2, 1, 2, 1},
                {1, 1, 2, 1, 1, 1, 1},
                {0, 0, 1, 2, 2, 0, 0},
                {0, 0, 3, 2, 2, 0, 0}
        };

        FieldService fs = new FieldService();
        LogicGameField gameField = fs.makeGameField(matrix);
        GameService gameService = new GameService();
        gameService.startRandomGameProcess(new GameProcess(gameField));
    }
}
