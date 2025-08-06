package com.mine;

import java.util.Scanner;

public class MinesweeperApp {
    public static void main(String[] ar){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid):");
        int size = scanner.nextInt();

        System.out.println("Enter the number of mines to place on the grid (maximum is 35% of the total squares):");
        int mines = scanner.nextInt();

        if(mines > size * size * 0.35) {
            System.out.println("Cannot Lah! Many mines ah... :)");
            return;
        }

        MinesweeperBoard minesweeperBoard = new MinesweeperBoard(size, mines);
        DisplayPrinter displayPrinter = new BoardDisplayPrinter();
        MinesweeperGameService minesweeperGameService = new MinesweeperGameService(minesweeperBoard, displayPrinter);
        minesweeperGameService.start();
    }
}
