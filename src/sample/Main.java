package sample;

import sample.services.FieldService;

public class Main {


    public static void main(String[] args) {
        int[][] matrix = {
                {0, 0, 1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {1, 1, 1, 3, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 2},
                {0, 0, 2, 2, 2, 0, 0},
                {0, 0, 2, 2, 2, 0, 0}
        };
        FieldService.print(matrix);
    }
}
