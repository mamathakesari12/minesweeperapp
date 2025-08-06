package com.mine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    @Test
    void testDefaultState() {
        Cell cell = new Cell();
        assertFalse(cell.hasMine(), "By default, cell should not have a mine");
        assertFalse(cell.isRevealed(), "By default, cell should not be revealed");
        assertEquals(0, cell.getAdjacentMines(), "Default adjacent mines should be zero");
    }

    @Test
    void testSetAndHasMine() {
        Cell cell = new Cell();
        cell.setMine(true);
        assertTrue(cell.hasMine(), "hasMine() should return true after setting a mine");
        cell.setMine(false);
        assertFalse(cell.hasMine(), "hasMine() should return false after clearing mine");
    }

    @Test
    void testReveal() {
        Cell cell = new Cell();
        assertFalse(cell.isRevealed(), "Initially not revealed");
        cell.reveal();
        assertTrue(cell.isRevealed(), "isRevealed() should be true after reveal()");
    }

    @Test
    void testSetAndGetAdjacentMines() {
        Cell cell = new Cell();
        cell.setAdjacentMines(3);
        assertEquals(3, cell.getAdjacentMines(), "getAdjacentMines should reflect the value set");

        cell.setAdjacentMines(0);
        assertEquals(0, cell.getAdjacentMines(), "adjacentMines can be reset to zero");

        cell.setAdjacentMines(8);
        assertEquals(8, cell.getAdjacentMines(), "adjacentMines can be set to typical maximum 8");
    }

    @Test
    void testRevealAndMineInteraction() {
        Cell cell = new Cell();
        cell.setMine(true);
        cell.reveal();

        assertTrue(cell.hasMine(), "Cell still has mine after reveal");
        assertTrue(cell.isRevealed(), "Cell is revealed after reveal()");
        // adjacency count is irrelevant for a mined cell, but default is zero unless set
        assertEquals(0, cell.getAdjacentMines(), "Uninitialized adjacentMines remains zero");
    }

    @Test
    void testIndependentStateAcrossCells() {
        Cell cell1 = new Cell();
        Cell cell2 = new Cell();

        cell1.setMine(true);
        cell1.reveal();
        cell1.setAdjacentMines(2);

        assertTrue(cell1.hasMine());
        assertTrue(cell1.isRevealed());
        assertEquals(2, cell1.getAdjacentMines());

        // Ensure other cell remains unchanged
        assertFalse(cell2.hasMine());
        assertFalse(cell2.isRevealed());
        assertEquals(0, cell2.getAdjacentMines());
    }
}
