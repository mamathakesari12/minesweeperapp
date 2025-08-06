package com.mine;

import java.util.Random;

public class MinesweeperBoard {
    private final int size;
    private final  int mineCount;
    private final Cell[][] grid;

    public MinesweeperBoard(int size, int mineCount) {
        this.size = size;
        this.mineCount = mineCount;
        this.grid = new Cell[size][size];
        initBoard();
        placeMines();
        calculateAdjacents();
    }
    private void initBoard() {
        for(int i=0; i<size; i++){
            for(int j=0; j <size; j++){
                grid[i][j] = new Cell();
            }
        }
    }

    void calculateAdjacents() {
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                if(!grid[i][j].hasMine()){
                    int count = counntAdjacentMines(i,j);
                    grid[i][j].setAdjacentMines(count);
                }
            }
        }
    }

    private int counntAdjacentMines(int row, int col) {
        int count = 0;
        for(int i=row-1; i<= row+1; i++){
            for(int j = col -1; j<= col + 1; j++){
                if(i >= 0 && i < size && j >= 0 && j<size && grid[i][j].hasMine()){
                    count++;
                }
            }
        }
        return count;
    }

    private void placeMines() {
        Random random = new Random();
        int placed = 0;
        while (placed < mineCount){
            int r = random.nextInt(size);
            int c = random.nextInt(size);

            if(!grid[r][c].hasMine()){
                grid[r][c].setMine(true);
                placed++;
            }
        }
    }



    public Cell getCell(int row, int columm){
        return grid[row][columm];
    }

    public int getSize() {
        return size;
    }

    public int getMineCount(){
        return mineCount;
    }

    public Cell[][] getGrid(){
        return grid;
    }
}
