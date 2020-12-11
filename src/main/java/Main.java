import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import figures.Figure;
import figures.Fox;
import figures.Goose;
import gameFild.LogicGameField;
import process.GameProcess;
import services.FieldService;
import services.GameService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {


    public static void main(String[] args) throws IOException {
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


        //Map<Integer, Figure> figureMap = new HashMap<>();
        //figureMap.put(1, new Fox());
        //figureMap.put(2, new Goose());
        //ObjectMapper objectMapper = new ObjectMapper();
        //String json = objectMapper.writeValueAsString(figureMap);
        //System.out.println(json);
    }
}
