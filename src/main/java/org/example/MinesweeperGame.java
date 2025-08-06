package org.example;

import java.util.Scanner;

public class MinesweeperGame {
    private final Grid grid;
    private final GameLogic gameLogic;
    public MinesweeperGame(int size, int mines) {
        this.grid = new Grid(size, mines);
        this.gameLogic = new GameLogic(grid);

    }
    public void play(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Minesweeper");

        while(!gameLogic.isGameOver() && !gameLogic.isGameWon()){
            System.out.println("**********");
            grid.print(false);
            System.out.println("Select a square to reveal ::");
            String input = scan.nextLine().trim().toUpperCase();
            if(input.length() < 2) continue;;

            int row = input.charAt(0)-'A';
            int col = Integer.parseInt(input.substring(1)) -1;

            if(grid.isValidCell(row, col)){
                gameLogic.reveal(row, col);
                if(gameLogic.isGameOver()){
                    System.out.println("you lose the game, Game over");
                    grid.print(true);
                }else if (gameLogic.isGameWon()){
                    grid.print(true);
                    System.out.println("Congratulations, you have won the game !");
                }
            }


        }
    }
}
