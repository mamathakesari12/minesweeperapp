package com.mine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinesweeperAppTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    void main_whenMinesTooMany_showsErrorAndExits() {
        String userInput = "4\n6\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        MinesweeperApp.main(new String[0]);
        String output = outContent.toString();

        assertTrue(output.contains("Enter the size of the grid"), "Prompt for size printed");
        assertTrue(output.contains("Cannot Lah! Many mines ah..."), "Too many mines message printed");
    }

    @Test
    void main_whenTooManyMines_showsErrorAndExits() {
        // Input: size=4, mines=10 (too many)
        String simulatedInput = "4\n10\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        MinesweeperApp.main(new String[0]);

        String output = outContent.toString();
        assertTrue(output.contains("Cannot Lah! Many mines"), "Should reject too many mines");
        assertFalse(output.contains("Select a square to reveal"));
    }
    @Test
    void serviceStart_withMineOnFirstMove_triggersGameOver() {
        // 1x1 board with 1 mine → A1 will end game in one move
        String simulatedInput = "A1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        MinesweeperBoard board = new MinesweeperBoard(1, 1);
        DisplayPrinter printer = new BoardDisplayPrinter();
        MinesweeperGameService service = new MinesweeperGameService(board, printer);

        service.start(); // should exit after one move

        String output = outContent.toString();
        assertTrue(output.contains("Select a square to reveal"));
        assertFalse(output.contains("Boom! You hit a mine"));
    }
    @Test
    void serviceStart_withMineOnFirstMove_triggersGameOver1() {

        String simulatedInput = "A1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        MinesweeperBoard board = new MinesweeperBoard(1, 1);
        DisplayPrinter printer = new BoardDisplayPrinter();
        MinesweeperGameService service = new MinesweeperGameService(board, printer);

        service.start();

        String output = outContent.toString();
        assertTrue(output.contains("Select a square to reveal"));
        assertFalse(output.contains("Boom! You hit a mine"));
    }
}
