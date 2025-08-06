package com.mine;

public class Cell {
    private boolean mine;
    private boolean revealed;
    private int adjacentMines;

    public boolean hasMine(){
        return mine;
    }
    public void setMine(boolean mine){
        this.mine = mine;
    }
    public boolean isRevealed(){
        return revealed;
    }

    public void reveal(){
        this.revealed = true;
    }
    public int getAdjacentMines(){
        return adjacentMines;
    }

    public void setAdjacentMines(int adjacentMines){
        this.adjacentMines = adjacentMines;
    }
}
