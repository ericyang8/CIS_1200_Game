package org.cis1200.tictactoe;

/**
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * This class is a model for TicTacToe.
 * 
 * This game adheres to a Model-View-Controller design framework.
 * This framework is very effective for turn-based games. We
 * STRONGLY recommend you review these lecture slides, starting at
 * slide 8, for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec36.pdf
 * 
 * This model is completely independent of the view and controller.
 * This is in keeping with the concept of modularity! We can play
 * the whole game from start to finish without ever drawing anything
 * on a screen or instantiating a Java Swing object.
 * 
 * Run this file to see the main method play a game of TicTacToe,
 * visualized with Strings printed to the console.
 */
public class GameLogic extends JPanel {

    private JButton[][] buttonBoard;
    private final int cleancellAmount = 80;
    private int remainingCell;
    String initialBoard = "1f1f1f2f2f2f1f1f0f0f9f4f4f9f9f2f9f1f0f0f9f9f9f9f4f3f1f1f0f0f9f6f5f4" +
            "f9f1f0f0f0f0f9f9f2f9f2f1f0f0f0f0f2f2f2f2f2f1f0f0f1f1f0f0f0f1f9" +
            "f3f2f2f3f9f0f1f1f2f2f9f9f2f9f9f0f1f9f1f1f2f2f2f2f2f0f1f1f1f0f0f0f0f0f0f";
    JPanel gameBoardPanel;
    private boolean inPlay;

    GameBoard theBoard;


    public GameLogic() {
        remainingCell = cleancellAmount;
        inPlay = true;
        gameBoardPanel = new JPanel(new GridLayout(10, 10));
        theBoard = new GameBoard(initialBoard);
        buttonBoard = new JButton[10][10];
    }


    public JButton createButton(int i, int ii) {
        JButton but = new JButton();
        but.setPreferredSize(new Dimension(65, 65));
        but.setBackground(new Color(173, 216, 230));
        addButtonAction(but, i, ii);
        return but;
    }

    public GameBoard getTheBoard() {
        return theBoard;
    }

    public void addButtonAction(JButton currButton, int i, int ii) {
        currButton.addActionListener(e -> {
            if (!theBoard.getCellVisibility(i, ii)) {
                revealCell(i,ii);
            }
        });
    }

    // create board of buttons
    public JPanel createBoard() {
        for (int i = 0; i < 10; i++) {
            for (int ii = 0; ii < 10; ii++) {
                buttonBoard[i][ii] = createButton(i, ii);
                gameBoardPanel.add(buttonBoard[i][ii]);
            }
        }
        return gameBoardPanel;
    }

    public void uncoverNonBombCell(int i, int ii) {
        theBoard.changeCellVisibility(i, ii, true);
        buttonBoard[i][ii].setText(Integer.toString(theBoard.getCellValue(i, ii)));
        remainingCell--;
    }

    // reveal cell on click (include recusion)
    public void revealCell(int i, int ii) {

        if (!theBoard.getCellVisibility(i, ii) && inPlay) {

            if (theBoard.getCellValue(i, ii) == 9) {
                theBoard.changeCellVisibility(i, ii, true);
                buttonBoard[i][ii].setText("B");
                lose();
            } else if (theBoard.getCellValue(i, ii) == 0) {
                revealZeroCellRecursive(i, ii);
            } else {
                uncoverNonBombCell(i, ii);
            }
        }

        if (remainingCell == 0) {
            win();
        }
    }


    // Recursion justification
    /* In accordance with Minesweeper's rules, when the player presses on a cell with a value of 0,
    the program has to uncover any surrounding non-bomb cells nearby.
    Therefore, the program uses recursion to uncover
    non-bomb cells up until it reaches a bomb cell.
    Recursion is adaptive, meaning it will call on itself untilitreaches
    a bomb no matter where the recursive file is called.
     Sometime like a for-loop would not be able to achieve this.*/
    public void revealZeroCellRecursive(int x, int y) {
        if (theBoard.getCellValue(x, y) == 0 && !theBoard.getCellVisibility(x, y)) {
            uncoverNonBombCell(x, y);

            if (x - 1 >= 0) {
                revealZeroCellRecursive(x - 1, y);
            }

            if (x + 1 < 10) {
                revealZeroCellRecursive(x + 1, y);
            }

            if (y - 1 >= 0) {
                revealZeroCellRecursive(x, y - 1);
            }

            if (y + 1 < 10) {
                revealZeroCellRecursive(x, y + 1);
            }
        } else if (theBoard.getCellValue(x, y) != 9 && !theBoard.getCellVisibility(x, y)) {
            uncoverNonBombCell(x, y);
        }
    }

    // lose game
    public void lose() {
        inPlay = false;
        JOptionPane.showMessageDialog(null, "You lose lol");
    }
    // win game
    public void win() {
        inPlay = false;
        JOptionPane.showMessageDialog(null, "You win nice!");
    }

    // load save
    public void load() {
        reloadGame(importFile(false));
        JOptionPane.showMessageDialog(null, "Load!");
    }

    public void reset() {
        reloadGame(importFile(true));
        JOptionPane.showMessageDialog(null, "Reset!");
    }

    // reset game (import default)
    public void reloadGame(String s) {
        inPlay = true;
        remainingCell = cleancellAmount;
        System.out.println(theBoard.toString());

        if (s.length() == 200) {
            theBoard = new GameBoard(s);

            for (int i = 0; i < 10; i++) {
                for (int ii = 0; ii < 10; ii++) {
                    buttonBoard[i][ii].setText("");
                    if (theBoard.getCellVisibility(i, ii)) {
                        uncoverNonBombCell(i, ii);
                    }
                }
            }
        }

    }

    public String importFile(boolean isReset) {
        String s = "";
        try {
            FileReader fr;
            if (isReset) {
                fr = new FileReader("gameFile/default.txt");
            } else {
                fr = new FileReader("gameFile/save.txt");
            }

            BufferedReader br = new BufferedReader(fr);
            s = br.readLine();
            br.close();

        } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(null, "File Not Found");
        } catch (IOException e2) {
            JOptionPane.showMessageDialog(null, "IO Error");
        } catch (Exception e3) {
            JOptionPane.showMessageDialog(null, "Help!");
        }

        return s;
    }

    // import game (import save)
    public void saveFile() {

        if (!inPlay) {
            JOptionPane.showMessageDialog(null, "Can't save since the game ended already!");
        } else {
            JOptionPane.showMessageDialog(null, "Just saved it!");
            try {
                FileWriter fr = new FileWriter("gameFile/save.txt", false);
                BufferedWriter br = new BufferedWriter(fr);
                System.out.println(theBoard.toString());
                br.write(theBoard.toString());
                br.close();
            } catch (FileNotFoundException e1) {
                JOptionPane.showMessageDialog(null, "File Not Found");
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Help!");
            }
        }
    }

    public boolean getInPlay() {
        return inPlay;
    }


}
