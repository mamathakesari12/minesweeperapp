package com.mine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinesweeperBoardTest {
    static class TestMinesweeperBoard extends MinesweeperBoard {
        // allows setting mines manually for deterministic testing
        TestMinesweeperBoard(int size, int mineCount) {
            super(size, mineCount);
        }
        void setMineAt(int row, int col) {
            getGrid()[row][col].setMine(true);
        }
    }

    @Test
    void testInitialization_noMinesUntilPlacement() {
        TestMinesweeperBoard b = new TestMinesweeperBoard(5, 3);
        // after constructor, mines are placed automatically
        int actualMines = 0;
        for (Cell[] row : b.getGrid())
            for (Cell c : row)
                if (c.hasMine()) actualMines++;
        assertEquals(3, actualMines);
        assertEquals(5, b.getSize());
    }

    @Test
    void testAdjacencyCounts_manualSetup() {
        TestMinesweeperBoard b = new TestMinesweeperBoard(3, 0);
        // clear any mines then place manually
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                b.getGrid()[r][c].setMine(false);

        b.setMineAt(0, 0);
        b.setMineAt(1, 1);
        b.calculateAdjacents();
        assertEquals(2, b.getCell(0,1).getAdjacentMines());
        assertEquals(2, b.getCell(1,0).getAdjacentMines());
        assertEquals(1, b.getCell(0,2).getAdjacentMines());
        assertEquals(1, b.getCell(2,2).getAdjacentMines());
        assertTrue(b.getCell(0,0).hasMine());
        assertTrue(b.getCell(1,1).hasMine());
        assertEquals(1, b.getCell(2,2).getAdjacentMines());
    }

    @Test
    void testNoAdjacencyOnMineCells() {
        TestMinesweeperBoard b = new TestMinesweeperBoard(2, 1);
        // ensure one mine
        int mineRow = -1, mineCol = -1;
        for (int r = 0; r < 2; r++)
            for (int c = 0; c < 2; c++)
                if (b.getCell(r,c).hasMine()) {
                    mineRow = r; mineCol = c;
                }
        assertTrue(b.getCell(mineRow,mineCol).hasMine());
        assertEquals(0, b.getCell(mineRow,mineCol).getAdjacentMines());
    }

    @Test
    void testGetCell_bounds() {
        MinesweeperBoard b = new MinesweeperBoard(4, 0);
        assertThrows(IndexOutOfBoundsException.class, () -> b.getCell(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> b.getCell(0, 4));
    }

    @Test
    void testMineCountGetter() {
        MinesweeperBoard b = new MinesweeperBoard(6, 10);
        assertEquals(10, b.getMineCount());
    }
}