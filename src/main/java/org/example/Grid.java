package org.example;

import java.util.Arrays;
import java.util.Random;

public class Grid {
    private final char[][] board;
    private final boolean[][] revealed;

    private final boolean [][] mines;

    private final int size;
    private final int numMines;


    public Grid(int size, int numMines) {
        this.size = size;
        this.numMines = numMines;
        this.board = new char[size][size];
        this.revealed = new boolean[size][size];
        this.mines = new boolean[size][size];
        init();
    }

    private void init(){
        for(int i=0;i<size; i++){
            Arrays.fill(board[i],'_');
            placeMines();
            calculateHints();

        }
        }

    private void placeMines(){
        Random rand = new Random();
        int placed = 0;
        while(placed < numMines){
            int row = rand.nextInt(size);
            int col = rand.nextInt(size);
            if(!mines[row][col]){
                mines[row][col] = true;
                placed++;

            }
        }
    }

    private void calculateHints(){
        for(int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                if(!mines[i][j]){
                    int count = countAdjacentMines(i,j);
                    board[i][j] = (char) (count + '0');


                }
            }
        }

    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i =-1; i<=1; i++){
            for(int j = -1; j <= 1;j++)
            {
                int r = row +i, c = col +j;
                if(isValidCell(r,c) && mines[r][c])
                    count++;
            }
        }
        return count;
    }

    public void print(boolean showAll) {
        System.out.println(" ");
        for(int i=1; i <=size; i++) {
            System.out.println(i + " ");
        }
        System.out.println();
        for (int i=0;i<size; i++){
            System.out.println((char) ('A' + i) + " ");
            for(int j=0; j < size; j++){
                if(showAll || revealed[i][j]){
                    System.out.println(mines[i][j]? "*" : board[i][j] + " ");
                }else{
                    System.out.println("_ ");
                }
            }
            System.out.println();

        }


    }
    public void setRevealed(int row, int col, boolean value){
        revealed[row][col] = value;
    }

    public boolean isValidCell(int row, int col) {
        return row >= 0 && row < size && col >=0 && col <size;
    }

    public boolean isRevealed(int row , int col){
        return revealed[row][col];
    }
     public boolean isMine(int row, int col){
        return mines[row][col];
     }

     public char getHint(int row, int col){
        return board[row][col];
     }
     public int getSize(){
        return size;
     }


}
