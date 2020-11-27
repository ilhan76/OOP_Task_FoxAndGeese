package process;

import gameFild.LogicGameField;

public class GameProcess {
    private LogicGameField gameField;
    private boolean moveFox;
    private boolean foxWin;
    private boolean geeseWin;

    public GameProcess(LogicGameField gameField){
        this.gameField = gameField;
        moveFox = false;
        foxWin = false;
        geeseWin = false;
    }

    public LogicGameField getGameField() {
        return gameField;
    }

    public void setGameField(LogicGameField gameField) {
        this.gameField = gameField;
    }

    public boolean isMoveFox() {
        return moveFox;
    }

    public void setMoveFox(boolean move) {
        this.moveFox = move;
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
