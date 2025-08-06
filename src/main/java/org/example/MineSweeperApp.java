package org.example;

import java.util.Scanner;

public class MineSweeperApp {
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the grid (4 for a 4x4 grid :::: ");
        int size = 4; //scanner.nextInt();

        int maxMines = (int) (size * size * 0.35);
        System.out.println("Enter the number of mines to place on the grid :::(maxMines is  " + maxMines + " )");

        int mines = 2; //scanner.nextInt();
        if(mines > maxMines) mines = maxMines;
        //scanner.nextLine();
        MinesweeperGame game = new MinesweeperGame(size, mines);
        game.play();

    }
}
