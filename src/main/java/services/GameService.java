package services;

import org.codehaus.jackson.map.ObjectMapper;
import process.GameProcess;

import java.io.File;
import java.io.IOException;


public class GameService {
    public void startRandomGameProcess(GameProcess gameProcess) throws InterruptedException, IOException {
        FieldService fieldService = new FieldService();
        FoxService foxService = new FoxService();
        GooseService gooseService = new GooseService();

        fieldService.printField(gameProcess.getGameField());
        while (!gameProcess.isFoxWin() && !gameProcess.isGeeseWin()){
            if (!gameProcess.isMoveFox()){
                gooseService.randomMove(gameProcess.getGameField());
                fieldService.printField(gameProcess.getGameField());
                if (foxService.isLoss(gameProcess.getGameField()))
                    gameProcess.setGeeseWin(true);
            } else {
                foxService.randomMove(gameProcess.getGameField());
                fieldService.printField(gameProcess.getGameField());
                if (gooseService.isLoss(gameProcess.getGameField()) || foxService.isPassiveWin(gameProcess.getGameField()))
                    gameProcess.setFoxWin(true);
            }
            Thread.sleep(2000);
            gameProcess.setMoveFox(!gameProcess.isMoveFox());
        }
        fieldService.printWinner(gameProcess);
        //ObjectMapper om = new ObjectMapper();
        //String json = om.writeValueAsString(gameProcess);
        //om.writeValue(new File("target/field.json"), gameProcess);
    }
}
