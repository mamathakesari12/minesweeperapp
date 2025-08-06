package com.mine;

import java.util.Scanner;

public class MinesweeperGameService {
    private final MinesweeperBoard minesweeperBoard;
    private final DisplayPrinter displayPrinter;
    private int revealedCells = 0;
    private boolean gameOver = false;

    public MinesweeperGameService(MinesweeperBoard minesweeperBoard, DisplayPrinter displayPrinter) {
        this.minesweeperBoard = minesweeperBoard;
        this.displayPrinter = displayPrinter;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        boolean defaultSt = true;

        if(defaultSt) {
            System.out.println("Here is your minefield:");

        }

        while (!gameOver) {
            if(!defaultSt) {
                System.out.println("Here is updated your minefield:");
            }
            defaultSt = false;
            displayPrinter.print(minesweeperBoard);
            System.out.print("Select a square to reveal (e.g. A1):");
            String input = scanner.next().toUpperCase();

            if (input.length() < 2) {
                System.out.println("Invalid Input");
                continue;
            }

            char rowChar = input.charAt(0);
            int col;
            try {
                col = Integer.parseInt(input.substring(1)) - 1;
            } catch (NumberFormatException e) {
                System.out.println(e);
                continue;
            }

            int row = rowChar - 'A';
            if (row < 0 || row >= minesweeperBoard.getSize() || col < 0 || col >= minesweeperBoard.getSize()) {
                System.out.println("Invalid Coordinates");
                continue;
            }

            Cell cell = minesweeperBoard.getCell(row, col);

            if (cell.hasMine()) {
                System.out.println("Oh no, you detonated a mine! Game over");
                System.out.println("Press any key to play again...");
                revealAllMines();
                displayPrinter.print(minesweeperBoard);
                gameOver = true;
            } else {
                uncover(row, col);
                System.out.println("This square contains" + cell.getAdjacentMines() + "adjacent mines");
                if (revealedCells == minesweeperBoard.getSize() * minesweeperBoard.getMineCount()) {
                    System.out.println("Congratulations, you have won the game!");
                    revealAllMines();
                    displayPrinter.print(minesweeperBoard);
                    gameOver = true;
                }
            }
        }
        scanner.close();
    }

    private void uncover(int row, int col) {

        Cell cell = minesweeperBoard.getCell(row, col);
        if (cell.isRevealed())
            return;

        cell.reveal();
        revealedCells++;

        if (cell.getAdjacentMines() == 0) {
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    if (i >= 0 && i < minesweeperBoard.getSize() && j >= 0 && j < minesweeperBoard.getSize()) {
                        uncover(i, j);
                    }
                }
            }
        }
    }

    private void revealAllMines() {
        for (int i = 0; i < minesweeperBoard.getSize(); i++) {
            for (int j = 0; j < minesweeperBoard.getSize(); j++) {
                Cell cell = minesweeperBoard.getCell(i, j);
                if (cell.hasMine()) {
                    cell.reveal();
                }
            }
        }
    }
}