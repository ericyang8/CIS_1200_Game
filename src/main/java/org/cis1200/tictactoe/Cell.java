package org.cis1200.tictactoe;


public class Cell {
    private boolean isVisible = false;
    private int cellValue;

    public Cell(int inputCellValue) {
        cellValue = inputCellValue;
    }
    public boolean isCellVisible() {
        return isVisible;
    }

    public void setVisible(boolean inputVisible) {
        isVisible = inputVisible;
    }

    public int getCellValue() {
        return cellValue;
    }





}