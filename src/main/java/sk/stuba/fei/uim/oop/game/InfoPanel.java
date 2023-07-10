package sk.stuba.fei.uim.oop.game;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private JComboBox comboBox;
    private JButton restart;
    private JLabel jLabel;
    private JButton checkPath;
    private int score;


    public InfoPanel(){
        this.setLayout(new GridLayout(2, 2));
        String[] items = {"8", "9", "10", "11"};
        score = 0;
        comboBox = new JComboBox<>(items);
        restart = new JButton("Restart");
        jLabel = new JLabel();
        checkPath = new JButton("Check path");
        this.add(comboBox);
        this.add(restart);
        this.add(jLabel);
        this.add(checkPath);
        jLabel.setText("Your score: 0");
    }

    public JComboBox getComboBox() {
        return comboBox;
    }

    public JButton getRestart() {
        return restart;
    }

    public JLabel getjLabel() {
        return jLabel;
    }

    public JButton getCheckPath() {
        return checkPath;
    }

    public void addScore() {
        score ++;
        getjLabel().setText("Your score: " + score);
    }

    public void resetScore() {
        score = 0;
        getjLabel().setText("Your score: 0");
        getRestart().setFocusable(false);
    }
}
