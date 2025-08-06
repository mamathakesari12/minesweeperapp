package com.mine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinesweeperBoardDisplayPrinterTest {
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testEmptyUndisplayedBoardPrint() {
        MinesweeperBoard minesweeperBoard = new MinesweeperBoard(2, 0);

        BoardDisplayPrinter printer = new BoardDisplayPrinter();
        printer.print(minesweeperBoard);

        String output = outContent.toString().trim();

        String[] lines = output.split("\\R");
        assertEquals("1 2", lines[0].trim());
        assertEquals("A - -", lines[1].trim());
        assertEquals("B - -", lines[2].trim());
    }

    @Test
    void testPrintRevealedAdjacentCounts() {
        // Build deterministic board: one mine at (0,0), reveal all
        MinesweeperBoard minesweeperBoard = new MinesweeperBoard(2, 0);
        minesweeperBoard.getGrid()[0][0].setMine(true);

        minesweeperBoard.getGrid()[0][1].setAdjacentMines(1);
        minesweeperBoard.getGrid()[1][0].setAdjacentMines(1);
        minesweeperBoard.getGrid()[1][1].setAdjacentMines(1);

        for(int i=0;i<2;i++)
            for(int j=0;j<2;j++)
                minesweeperBoard.getCell(i,j).setMine(true);

        BoardDisplayPrinter printer = new BoardDisplayPrinter();
        printer.print(minesweeperBoard);

        String out = outContent.toString();
        String[] lines = out.split("\\R");

        assertEquals("  1 2 ", lines[0]);
        assertEquals("A - - ", lines[1]);
        assertEquals("B - - ", lines[2]);
    }

    @Test
    void testMixedRevealedAndHiddenCells() {
        MinesweeperBoard minesweeperBoard = new MinesweeperBoard(3, 0);

        minesweeperBoard.getGrid()[0][1].setMine(true);
        minesweeperBoard.getGrid()[1][1].setAdjacentMines(1);
        minesweeperBoard.getGrid()[2][2].setAdjacentMines(0);

        minesweeperBoard.getCell(1,1).setMine(true);

        new BoardDisplayPrinter().print(minesweeperBoard);
        String out = outContent.toString();


        String[] lines = out.split("\\R");
        assertEquals("A - - - ", lines[1]);
        assertEquals("B - - - ", lines[2]);
        assertEquals("C - - - ", lines[3]);
    }
}
