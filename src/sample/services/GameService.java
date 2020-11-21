package sample.services;

import sample.GameProcess;


public class GameService {
    public void startGame(GameProcess gameProcess) throws InterruptedException {
        int count = 0;
        while (!gameProcess.isFoxWin() || !gameProcess.isGeeseWin()){
            if (gameProcess.isMove()){
                gameProcess.getGooseService().randomMove(gameProcess.getGameField());
                FieldService.printField(gameProcess.getGameField());
                if (gameProcess.getFoxService().isLoss(gameProcess.getGameField()))
                    gameProcess.setGeeseWin(true);
            } else {
                gameProcess.getFoxService().randomMove(gameProcess.getGameField());
                FieldService.printField(gameProcess.getGameField());
                if (gameProcess.getGooseService().isLoss(gameProcess.getGameField()))
                    gameProcess.setFoxWin(true);
            }
            count++;
            Thread.sleep(1000);
            gameProcess.setMove(!gameProcess.isMove());
        }
        System.out.println(count);
        FieldService.printWinner(gameProcess);
    }

}
