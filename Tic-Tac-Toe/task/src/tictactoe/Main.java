package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

class Field {

    private char[][] field;


    public Field(int fieldSize) {
        field = new char[fieldSize + 2][fieldSize + 6];
        for (int row = 0; row < field.length; row++) {
            for (int pos = 0; pos < field[row].length; pos++) {
                if (row == 0 || row == field.length - 1) {
                    field[row][pos] = '-';
                } else {
                    if (pos == 0 || pos == field[row].length - 1) {
                        field[row][pos] = '|';
                    } else {
                        field[row][pos] = ' ';
                    }
                }
            }
        }
    }

    public char[][] getField() {
        return field;
    }

    public void print (char[][] field){
        for (int row = 0; row < field.length; row++) {
            for (int pos = 0; pos < field[row].length; pos++) {
                System.out.print(field[row][pos]);
            }
            System.out.print("\n");
        }
    }

}

class Moves {

    private int x;
    private int y;
    private int movesCounter = 1;
    private char[][] field;

    public Moves(char[][] field){
        this.field = field;
    }

    public void movesScanner(){
        System.out.print("Enter the coordinates: ");
        try{
            Scanner sc = new Scanner(System.in);
            x = sc.nextInt();
            y = sc.nextInt();
        } catch (InputMismatchException e){
            System.out.println("You should enter numbers!");
            movesScanner();
        }
        if ((x > 0 && x < 4) && (y > 0 && y < 4)){
            x += x;
            if (y == 1){
                y += 2;
            } else if (y == 3){
                y -= 2;
            }
        } else {
            System.out.println("Coordinates should be from 1 to 3!");
            movesScanner();
        }
        placeMove();
        lineChecker(field);
        printWithMove();
        movesScanner();
    }

    private void placeMove(){
        if (field[y][x] == 'X' || field[y][x] == 'O'){
            System.out.println("This cell is occupied! Choose another one!");
            movesScanner();
        } else {
            if (movesCounter % 2 == 1) {
                field[y][x] = 'X';
                movesCounter++;
            } else if (movesCounter % 2 == 0) {
                field[y][x] = 'O';
                movesCounter++;
            }
        }
    }
    private void printWithMove(){
        for(int i = 0; i < field.length; i++){
            for (int j = 0; j < field[i].length; j++){
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
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

        if ((field[2][4] == field[1][2] && field[2][4] == field[3][6]) ||
                (field[2][4] == field[3][2] && field[2][4] == field[1][6])){
            if (field[2][4] == 'X'){
                diogonalX++;
            }else if (field[2][4] == 'O'){
                diogonalO++;
            }
        }

        for(int i = 1; i < field.length - 1; i++){
            for(int j = 2; j < field[i].length - 2; j += 2){
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

        for(int i = 2; i < field.length + 2; i +=2) {
            for (int j = 1; j < field.length - 1; j++) {
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
            printWithMove();
            System.out.println("O wins");
            System.exit(0);
        } else if (lineCounterX == 1 || rowCounterX == 1 || diogonalX == 1){
            printWithMove();
            System.out.println("X wins");
            System.exit(0);
        } else if (space > 0){
            printWithMove();
            movesScanner();
        } else {
            System.out.println("Draw");
            System.exit(0);
        }
    }
}

class Game {
    private Field field;

    public Game(int size){
        field = new Field(size);
    }

    public void play(){
        field.print(field.getField());
        Moves scanning = new Moves(field.getField());
        scanning.movesScanner();
    }
}

public class Main {
    public static void main(String[] args) {
        Game game = new Game(3);
        game.play();
    }
}
