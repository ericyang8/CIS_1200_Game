package org.cis1200.tictactoe;
public class GameBoard {


    private int columns = 10;
    private int rows = 10;
    private Cell[][] board;



    public GameBoard(String boardString) {
        String currCellString;
        Cell currCell;
        board = new Cell[rows][columns];

        for (int i = 0; i < 100; i++) {
            currCellString = boardString.substring(0, 2);
            currCell = new Cell(Integer.valueOf(currCellString.substring(0, 1)));

            if (currCellString.substring(1, 2).equals("t")) {
                currCell.setVisible(true);
            } else {
                currCell.setVisible(false);
            }

            board[i / 10][i % 10] = currCell;

            boardString = boardString.substring(2);
        }

    }


    public boolean getCellVisibility(int x, int y) {
        return board[x][y].isCellVisible();
    }

    public void changeCellVisibility(int x, int y, boolean isVisible) {
        board[x][y].setVisible(isVisible);
    }

    public int getCellValue(int x, int y) {
        return board[x][y].getCellValue();
    }


    public String toString() {
        String s = "";

        for (int i = 0; i < 10; i++) {
            for (int ii = 0; ii < 10; ii++) {
                s += board[i][ii].getCellValue();
                if (board[i][ii].isCellVisible()) {
                    s += "t";
                } else {
                    s += "f";
                }
            }
        }
        return s;
    }
}