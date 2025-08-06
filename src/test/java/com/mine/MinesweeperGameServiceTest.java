package com.mine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinesweeperGameServiceTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayInputStream inContent = new ByteArrayInputStream("A1\n".getBytes());

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(System.out);
        System.setIn(System.in);
    }

    @Test
    void testGameStartWithValidInput() {
        // Setup a 2x2 board with 1 mine at (0, 0)
        MinesweeperBoard minesweeperBoard = new MinesweeperBoard(2, 1);
        minesweeperBoard.getCell(0, 0).setMine(true);
        minesweeperBoard.getCell(0, 1).setAdjacentMines(1);
        minesweeperBoard.getCell(1, 0).setAdjacentMines(1);
        minesweeperBoard.getCell(1, 1).setAdjacentMines(1);

        DisplayPrinter displayPrinter = new BoardDisplayPrinter();
        MinesweeperGameService minesweeperGameService = new MinesweeperGameService(minesweeperBoard, displayPrinter);

        // Start the game
        minesweeperGameService.start();

        // Capture and assert the output
        String output = outContent.toString();
        assertTrue(output.contains("Select a square to reveal"));
        assertFalse(output.contains("Congratulations!, GameOve it!"));
    }
}
