package services;

import graphics.ConsoleOutputType;
import process.GameProcess;


public class GameService {
    public void startRandomGameProcess(GameProcess gameProcess) {
        FieldService fieldService = new FieldService();
        FoxService foxService = new FoxService();
        GooseService gooseService = new GooseService();

        fieldService.printField(gameProcess.getGameField(), ConsoleOutputType.NONE);
        while (!gameProcess.isFoxWin() && !gameProcess.isGeeseWin()){
            if (!gameProcess.isMoveFox()){
                gooseService.randomMove(gameProcess.getGameField());
                fieldService.printField(gameProcess.getGameField(), ConsoleOutputType.NONE);
                if (foxService.isLoss(gameProcess.getGameField()))
                    gameProcess.setGeeseWin(true);
            } else {
                foxService.randomMove(gameProcess.getGameField());
                fieldService.printField(gameProcess.getGameField(), ConsoleOutputType.NONE);
                if (gooseService.isLoss(gameProcess.getGameField()) || foxService.isPassiveWin(gameProcess.getGameField()))
                    gameProcess.setFoxWin(true);
            }

            //Thread.sleep(2000);
            gameProcess.setMoveFox(!gameProcess.isMoveFox());
        }
        fieldService.printWinner(gameProcess);
    }
}
