package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.contols.GeneralControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameStructure extends GeneralControl {
    private GameLogic gameLogic;
    private int gameSize;
    private JFrame frame;
    private JDialog jDialog;
    private JPanel board;
    private JButton levelUpButton;
    private InfoPanel infoPanel;;
    public GameStructure() {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setFocusable(true);
        this.jDialog = new JDialog(frame, "Congrats");
        this.levelUpButton = new JButton("Level up");
        levelUpButton.addActionListener(this);

        frame.requestFocusInWindow();
        this.gameSize = 8;
        frame.addKeyListener(this);

        frame.setLayout(new BorderLayout());
        board = new JPanel();

        this.infoPanel = new InfoPanel();
        infoPanel.getComboBox().addActionListener(this);
        infoPanel.getRestart().addActionListener(this);
        infoPanel.getCheckPath().addActionListener(this);

        frame.add(board, BorderLayout.CENTER);
        frame.add(infoPanel, BorderLayout.SOUTH);
        this.gameLogic = new GameLogic(board, gameSize);

        frame.pack();
        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Restart")) {
            gameLogic.restartGame();
            infoPanel.resetScore();
            frame.pack();
        }
        else if (e.getActionCommand().equals("comboBoxChanged")) {
            JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
            String selectedValue = (String) comboBox.getSelectedItem();
            gameSize = Integer.parseInt(selectedValue);
            gameLogic.setBoardSize(gameSize);
            gameLogic.restartGame();
            infoPanel.resetScore();
            frame.pack();
            comboBox.setFocusable(false);

        }
        else if (e.getActionCommand().equals("Check path")) {
            checkPAthButton();

        }
        if (e.getActionCommand().equals("Level up")) {
            gameLogic.restartGame();
            jDialog.dispose();
            levelUpButton.setFocusable(false);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 82) {
            gameLogic.restartGame();
            infoPanel.resetScore();
        }
        else if(e.getKeyCode() == 10) {
            checkPAthButton();
        }
        else if(e.getKeyCode() == 27) {
            frame.dispose();
        }

    }

    public void checkPAthButton() {
        infoPanel.getCheckPath().setFocusable(false);
        for (var cell : gameLogic.getCorrectPath()) {
            if (!cell.isConnected()) {
                cell.setBackground(Color.RED);
                JOptionPane.showMessageDialog(frame, "Try again", "Notification", JOptionPane.INFORMATION_MESSAGE);
                gameLogic.getFinishCell().setBackground(Color.RED);
                return;
            }
            cell.setBackground(Color.GREEN);

        }
        gameLogic.getFinishCell().setBackground(Color.GREEN);
        infoPanel.addScore();
        jDialog.add(levelUpButton);
        jDialog.setSize(400, 100);
        jDialog.setVisible(true);
    }
}
