package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import figures.Fox;
import figures.Goose;
import graphics.ConsoleOutputType;
import process.GameProcess;
import java.io.IOException;


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
        /*try(FileWriter fw = new FileWriter("src/main/java/saveGame"))
        {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(gameProcess);
            //System.out.println(json);
            fw.write(json);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }*/

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(gameProcess);
        System.out.println(json);

        GameProcess gp = objectMapper.readValue(json, GameProcess.class);
        for (Goose g :
                gp.getGameField().getGeese()) {
            System.out.println(g + " " + g.getCell());
        }
        for (Fox f :
                gp.getGameField().getFoxes()) {
            System.out.println(f + " " + f.getCell());
        }
        System.out.println(gp.getGameField().getCells());
        //fieldService.printField(gp.getGameField(), ConsoleOutputType.NONE);
    }
}
