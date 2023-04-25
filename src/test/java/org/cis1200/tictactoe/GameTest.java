package org.cis1200.tictactoe;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void testCellFunction() {
        Cell c = new Cell(8);
        c.setVisible(true);

        assertTrue(c.isCellVisible());
        assertEquals(8, c.getCellValue());
    }


    @Test
    public void testClickBombLose() {
        GameLogic g = new GameLogic();
        g.createBoard();
        g.revealCell(1, 0);
        System.out.println(g.getTheBoard().toString());
        assertEquals("1f1f1f2f2f2f1f1f0f0f" +
                "9t4f4f9f9f2f9f1f0f0f" +
                "9f9f9f9f4f3f1f1f0f0f" +
                "9f6f5f4f9f1f0f0f0f0f" +
                "9f9f2f9f2f1f0f0f0f0f" +
                "2f2f2f2f2f1f0f0f1f1f" +
                "0f0f0f1f9f3f2f2f3f9f" +
                "0f1f1f2f2f9f9f2f9f9f" +
                "0f1f9f1f1f2f2f2f2f2f" +
                "0f1f1f1f0f0f0f0f0f0f", g.getTheBoard().toString());
        assertFalse(g.getInPlay());
    }

    @Test
    public void testClickCell() {
        GameLogic g = new GameLogic();
        g.createBoard();
        g.revealCell(0, 0);
        assertEquals("1t1f1f2f2f2f1f1f0f0f" +
                "9f4f4f9f9f2f9f1f0f0f" +
                "9f9f9f9f4f3f1f1f0f0f" +
                "9f6f5f4f9f1f0f0f0f0f" +
                "9f9f2f9f2f1f0f0f0f0f" +
                "2f2f2f2f2f1f0f0f1f1f" +
                "0f0f0f1f9f3f2f2f3f9f" +
                "0f1f1f2f2f9f9f2f9f9f" +
                "0f1f9f1f1f2f2f2f2f2f" +
                "0f1f1f1f0f0f0f0f0f0f", g.getTheBoard().toString());
    }

    @Test
    public void testClickMultipleCell() {
        GameLogic g = new GameLogic();
        g.createBoard();
        g.revealCell(0, 9);
        assertEquals("1f1f1f2f2f2f1f1t0t0t" +
                "9f4f4f9f9f2f9f1t0t0t" +
                "9f9f9f9f4f3f1t1t0t0t" +
                "9f6f5f4f9f1t0t0t0t0t" +
                "9f9f2f9f2f1t0t0t0t0t" +
                "2f2f2f2f2f1t0t0t1t1t" +
                "0f0f0f1f9f3f2t2t3f9f" +
                "0f1f1f2f2f9f9f2f9f9f" +
                "0f1f9f1f1f2f2f2f2f2f" +
                "0f1f1f1f0f0f0f0f0f0f", g.getTheBoard().toString());
    }

    @Test
    public void testImport() {
        GameLogic g = new GameLogic();
        g.createBoard();
        g.reset();

        assertEquals("1f1f1f2f2f2f1f1f0f0f" +
                "9f4f4f9f9f2f9f1f0f0f" +
                "9f9f9f9f4f3f1f1f0f0f" +
                "9f6f5f4f9f1f0f0f0f0f" +
                "9f9f2f9f2f1f0f0f0f0f" +
                "2f2f2f2f2f1f0f0f1f1f" +
                "0f0f0f1f9f3f2f2f3f9f" +
                "0f1f1f2f2f9f9f2f9f9f" +
                "0f1f9f1f1f2f2f2f2f2f" +
                "0f1f1f1f0f0f0f0f0f0f", g.getTheBoard().toString());
    }

    @Test
    public void testExport() throws IOException {
        GameLogic g = new GameLogic();
        g.createBoard();
        g.reset();

        g.revealCell(0, 0);
        g.saveFile();
        FileReader fr = new FileReader("gameFile/save.txt");
        BufferedReader br = new BufferedReader(fr);
        assertEquals("1t1f1f2f2f2f1f1f0f0f" +
                "9f4f4f9f9f2f9f1f0f0f" +
                "9f9f9f9f4f3f1f1f0f0f" +
                "9f6f5f4f9f1f0f0f0f0f" +
                "9f9f2f9f2f1f0f0f0f0f" +
                "2f2f2f2f2f1f0f0f1f1f" +
                "0f0f0f1f9f3f2f2f3f9f" +
                "0f1f1f2f2f9f9f2f9f9f" +
                "0f1f9f1f1f2f2f2f2f2f" +
                "0f1f1f1f0f0f0f0f0f0f", br.readLine());
    }

}


