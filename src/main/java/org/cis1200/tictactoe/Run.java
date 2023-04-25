package org.cis1200.tictactoe;

import javax.swing.*;
import java.awt.*;

public class Run {
    /**
     * Main method run to start and run the game. Initializes the runnable game
     * class of your choosing and runs it. IMPORTANT: Do NOT delete! You MUST
     * include a main method in your final submission.
     */
    public static void main(String[] args) {
        // Set the game you want to run here
        // Top-level frame in which game components live
        JFrame frame = new JFrame("Eric's CIS 1200 Game");

        final GameLogic game = new GameLogic();
        JPanel aBoard = game.createBoard();
        frame.add(aBoard, BorderLayout.CENTER);

        final JPanel dashboard = new JPanel();
        frame.add(dashboard, BorderLayout.SOUTH);


        // reset
        final JButton reset = new JButton("Reset Game");
        reset.setBackground(new Color(255,114,118));
        reset.addActionListener(e -> game.reset());
        reset.setPreferredSize(new Dimension(120, 65));
        dashboard.add(reset);

        // instrucitons
        final JButton instructions = new JButton("Instructions");
        instructions.setBackground(new Color(255,114,118));
        instructions.addActionListener(e -> JOptionPane.showMessageDialog(null,
                "Welcome!! To play just left click on a box."
                        + " It should reveal either a number"
                        + "which is the amount of" +
                        "neigbhouring bombs or a mine. Don't click on mines."
                        + "Reveal all of the non-mine tiles to win!"
                        + " Save and load by pressing the buttons. "
                        + "Also you can reload games!", "Eric's Instructions for Minesweeper",
                JOptionPane.PLAIN_MESSAGE));
        instructions.setPreferredSize(new Dimension(160, 65));
        dashboard.add(instructions);

        // save
        final JButton save = new JButton("Save");
        save.setBackground(new Color(255,114,118));
        save.addActionListener(e -> game.saveFile());
        save.setPreferredSize(new Dimension(90, 65));
        dashboard.add(save);

        // load
        final JButton load = new JButton("Load");
        load.setBackground(new Color(255,114,118));
        load.addActionListener(e -> game.load());
        load.setPreferredSize(new Dimension(90, 65));
        dashboard.add(load);


        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);



    }
}
