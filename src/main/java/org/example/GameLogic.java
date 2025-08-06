package org.example;

public class GameLogic {
    private final Grid grid;
    private boolean gameOver = false;
    private boolean gameWon = false;

    private int cellsToReveal;

    public GameLogic(Grid grid) {
        this.grid = grid;
        int totalCells = grid.getSize() * grid.getSize();
        this.cellsToReveal = totalCells -countMines();
    }
    private int countMines(){
        int count =0;
        int size = grid.getSize();
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(grid.isMine(i, j)) count++;
            }
        }
        return count;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameWon(){
        return  cellsToReveal == 0;
    }

    public void reveal(int row, int col) {
        if(!grid.isValidCell(row, col) || grid.isRevealed(row, col)) return;

        grid.setRevealed(row, col, true);
        if(grid.isMine(row, col)){
            gameOver = true;
            return;
        }
        cellsToReveal--;
        if (grid.getHint(row, col) == '0') {

            for(int i=-1; i<1; i++){
                for(int j =-1;j<=1;j++){
                    if(i != 0 || j !=0) reveal(row+i, col+j);
                }

            }
        }
    }
}
