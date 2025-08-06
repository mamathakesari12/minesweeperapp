package com.mine;

public class BoardDisplayPrinter implements DisplayPrinter {

    @Override
    public void print(MinesweeperBoard minesweeperBoard) {
        int size = minesweeperBoard.getSize();
        System.out.print("  ");
        for(int j = 0; j<size; j++){
            System.out.print((j+1) + " ");
        }
        System.out.println();
        for(int i =0; i < size; i++){
            char rowLabel = (char) ('A' + i);
            System.out.print(rowLabel + " ");
            for (int j=0; j<size; j++){
                Cell cell = minesweeperBoard.getCell(i, j);
                if(cell.isRevealed()){
                    if(cell.hasMine()){
                        System.out.print("* ");
                    }else {
                        int adj = cell.getAdjacentMines();
                        System.out.print((adj == 0 ? " ": adj) + " ");
                    }
                }else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }
}
