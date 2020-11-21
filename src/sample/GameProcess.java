package sample;

import sample.gameFild.LogicGameField;
import sample.services.FoxService;
import sample.services.GameService;
import sample.services.GooseService;

public class GameProcess {
    private GameService gameService;
    private FoxService foxService;
    private GooseService gooseService;
    private LogicGameField gameField;
    private boolean move;
    private boolean foxWin;
    private boolean geeseWin;

    GameProcess(LogicGameField gameField){
        gameService = new GameService();
        foxService = new FoxService();
        gooseService = new GooseService();
        this.gameField = gameField;
        move = true;
        foxWin = false;
        geeseWin = false;
    }

    public FoxService getFoxService() {
        return foxService;
    }

    public void setFoxService(FoxService foxService) {
        this.foxService = foxService;
    }

    public GooseService getGooseService() {
        return gooseService;
    }

    public void setGooseService(GooseService gooseService) {
        this.gooseService = gooseService;
    }

    public LogicGameField getGameField() {
        return gameField;
    }

    public void setGameField(LogicGameField gameField) {
        this.gameField = gameField;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public boolean isFoxWin() {
        return foxWin;
    }

    public void setFoxWin(boolean foxWin) {
        this.foxWin = foxWin;
    }

    public boolean isGeeseWin() {
        return geeseWin;
    }

    public void setGeeseWin(boolean geeseWin) {
        this.geeseWin = geeseWin;
    }
}
