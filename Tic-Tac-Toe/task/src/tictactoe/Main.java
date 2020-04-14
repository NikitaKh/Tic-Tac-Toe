package tictactoe;

import java.util.Scanner;

class Field {

    private char[][] field;
    private int fieldSize;

    public Field(int fieldSize){
        field = new char[fieldSize][fieldSize];
        this.fieldSize = fieldSize;
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                field[i][j] = 'X';
            }
        }
    }

    public void print(String input) {
        int y = fieldSize + 2;
        int counter = 0;
        for (int i = 0; i < y; i++){
            if (i == 0 || i == y - 1){
                System.out.println("---------");
                continue;
            }
            for (int j = 0; j < fieldSize; j++){
                switch (j){
                    case 0:
                        System.out.print("| " + input.charAt(counter));
                        counter++;
                        break;
                    case 1:
                        System.out.print(" " + input.charAt(counter) + " ");
                        counter++;
                        break;
                    case 2:
                        System.out.println(input.charAt(counter) + " |");
                        counter++;
                        break;
                }
            }
        }
    }
}

class Game {
    private Field field;

    public Game(int size){
        field = new Field(size);
    }

    public void play(){
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        field.print(input);
        result(input);
    }


    public void result (String input){
        char[][] fileld2 = new char[3][3];
        int counter = 0;
        int x = 0;
        int o = 0;

        for(int i = 0; i < fileld2.length; i++){
            for (int j = 0; j < fileld2[i].length; j++){
                fileld2[i][j] = input.charAt(counter);
                if (fileld2[i][j] == 'X'){
                    x++;
                } else if (fileld2[i][j] == 'O'){
                    o++;
                }
                counter++;
            }
        }
        if (x - o > 1 || o - x > 1)
            System.out.println("Impossible");
        else
            lineChecker(fileld2);
    }

    public void lineChecker(char[][] field){
        int lineX = 0;
        int lineO = 0;

        int rowX = 0;
        int rowO = 0;

        int diogonalX = 0;
        int diogonalO = 0;

        int space = 0;

        int lineCounterX = 0;
        int lineCounterO = 0;

        int rowCounterX = 0;
        int rowCounterO = 0;

        int index = field.length;

        if ((field[1][1] == field[index - 1][index - 1] && field[1][1] == field[0][0]) ||
                (field[1][1] == field[0][index - 1] && field[1][1] == field[index - 1][0])){
            if (field[1][1] == 'X'){
                diogonalX++;
            }else if (field[1][1] == 'O'){
                diogonalO++;
            }
        }

        for(int i = 0; i < field.length; i++){
            for(int j = 0; j < field[i].length; j++){
                if (field[i][j] == 'O'){
                    lineO++;
                } else if (field[i][j] == 'X'){
                    lineX++;
                } else
                    space++;
            }
            if (lineO == 3){
                lineCounterO++;
            } else if (lineX == 3){
                lineCounterX++;
            }
            lineO = 0;
            lineX = 0;
        }

        for(int i = 0; i < field.length; i++) {
            if (rowO == 3) {
                rowCounterO++;
            } else if (rowX == 3) {
                rowCounterX++;
            }
            rowO = 0;
            rowX = 0;
            for (int j = 0; j < field[i].length; j++) {
                if (field[j][i] == 'O') {
                    rowO++;
                } else if (field[j][i] == 'X') {
                    rowX++;
                }
            }
            if (rowO == 3) {
                rowCounterO++;
            } else if (rowX == 3) {
                rowCounterX++;
            }
            rowO = 0;
            rowX = 0;
        }

        if (lineCounterO > 1 || lineCounterX > 1 || rowCounterO > 1 || rowCounterX > 1){
            System.out.println("Impossible");
            System.exit(0);
        } else if ((lineCounterO == 1 && lineCounterX == 1) || (lineCounterO == 1 && rowCounterO == 1) ||
                (lineCounterX == 1 && rowCounterX == 1) || (rowCounterO == 1 && rowCounterX == 1)) {
            System.out.println("Impossible");
            System.exit(0);
        } else if (lineCounterO == 1 || rowCounterO == 1 || diogonalO == 1){
            System.out.println("O wins");
            System.exit(0);
        } else if (lineCounterX == 1 || rowCounterX == 1 || diogonalX == 1){
            System.out.println("X wins");
            System.exit(0);
        } else if (space > 0){
            System.out.println("Game not finished");
            System.exit(0);
        } else {
            System.out.println("Draw");
            System.exit(0);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Game game1 = new Game(3);
        game1.play();
    }
}