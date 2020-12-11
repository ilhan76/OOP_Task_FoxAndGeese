package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import figures.Figure;
import figures.Fox;
import figures.Goose;
import gameFild.Cell;
import gameFild.LogicGameField;
import graphics.ConsoleOutputType;
import process.GameProcess;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeSet;


public class GameService {
    public void startRandomGameProcess(GameProcess gameProcess) throws IOException {
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

        for (Goose g :
                gameProcess.getGameField().getGeese()) {
            g.getPossibleMoves().clear();
        }
        for (Fox f :
                gameProcess.getGameField().getFoxes()) {
            f.getPossibleMoves().clear();
            f.getPossibleBeat().clear();
        }

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(gameProcess);
        System.out.println(json);

        GameProcess gp = objectMapper.readValue(json, GameProcess.class);
        //fieldService.printField(gp.getGameField(), ConsoleOutputType.NONE);
    }
}
