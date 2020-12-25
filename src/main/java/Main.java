import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import figures.Figure;
import figures.Fox;
import figures.Goose;
import gameFild.LogicGameField;
import process.GameProcess;
import services.FieldService;
import services.GameService;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {
       /* try(FileReader reader = new FileReader("src/main/java/saveGame"))
        {
            Scanner scn = new Scanner(reader);
            StringBuilder json = new StringBuilder();
            while(scn.hasNext()){
                //System.out.print(scn.next());
                json.append(scn.next());
            }
            ObjectMapper objectMapper = new ObjectMapper();
            GameProcess gameProcess = objectMapper.readValue(String.valueOf(json), GameProcess.class);
            GameService gameService = new GameService();
            gameService.startRandomGameProcess(gameProcess);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }*/

        int[][] matrix = {
                {0, 0, 1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {1, 1, 1, 3, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 2},
                {0, 0, 2, 2, 2, 0, 0},
                {0, 0, 2, 2, 2, 0, 0}
        };
        FieldService fs = new FieldService();
        LogicGameField gameField = fs.makeGameField(matrix);
        GameService gameService = new GameService();
        gameService.startRandomGameProcess(new GameProcess(gameField));
    }
}
