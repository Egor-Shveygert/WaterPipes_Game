package sk.stuba.fei.uim.oop.maze;

import javax.swing.*;
import java.awt.*;

public class Pipe extends JPanel {
    private int x;
    private int y;
    private int borderWidth;
    private int borderHeight;
    private double angel;
    private boolean isPath;
    private PipeForm pipeForm;

    public Pipe(int x, int y, int borderWidth, int borderHeight, double angel, boolean isPath, PipeForm pipeForm) {
        this.x = x;
        this.angel = angel;
        this.pipeForm = pipeForm;
        this.isPath = isPath;
        this.y = y;
        this.borderWidth = borderWidth;
        this.borderHeight = borderHeight;
    }

    public double getAngel() {
        return angel;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        var rad = getAngel() * Math.PI / 180;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(10));
        g2d.setColor(Color.BLUE);
        if (pipeForm == PipeForm.STRAIGHT && isPath) {
            double centerX = borderWidth / 2.0;
            double centerY = borderHeight / 2.0;
            double halfLength = borderWidth / 2.0;
            double startX = centerX - halfLength;
            double startY = centerY;
            double endX = centerX + halfLength;
            double endY = centerY;
            double startX2 = (startX - centerX) * Math.cos(rad) - (startY - centerY) * Math.sin(rad) + centerX;
            double startY2 = (startX - centerX) * Math.sin(rad) + (startY - centerY) * Math.cos(rad) + centerY;
            double endX2 = (endX - centerX) * Math.cos(rad) - (endY - centerY) * Math.sin(rad) + centerX;
            double endY2 = (endX - centerX) * Math.sin(rad) + (endY - centerY) * Math.cos(rad) + centerY;

            g2d.drawLine((int)Math.round(startX2), (int)Math.round(startY2), (int)Math.round(endX2), (int)Math.round(endY2));

        } else if (pipeForm == PipeForm.ROUNDED && isPath) {
            double centerX = borderWidth / 2;
            double centerY = borderHeight / 2;
            double halfLength = 30;
            double startX1 = centerX;
            double startY1 = centerY;
            double endX1 = centerX + halfLength * 2;
            double endY1 = centerY;
            double startX2 = (startX1 - centerX) * Math.cos(rad) - (startY1 - centerY) * Math.sin(rad) + centerX;
            double startY2 = (startX1 - centerX) * Math.sin(rad) + (startY1 - centerY) * Math.cos(rad) + centerY;
            double endX2 = (endX1 - centerX) * Math.cos(rad) - (endY1 - centerY) * Math.sin(rad) + centerX;
            double endY2 = (endX1 - centerX) * Math.sin(rad) + (endY1 - centerY) * Math.cos(rad) + centerY;

            g2d.drawLine((int) Math.round(startX2), (int) Math.round(startY2), (int) Math.round(endX2), (int) Math.round(endY2));

            double startX3 = centerX;
            double startY3 = centerY;
            double endX3 = centerX;
            double endY3 = centerY + halfLength * 2;
            double startX4 = (startX3 - centerX) * Math.cos(rad) - (startY3 - centerY) * Math.sin(rad) + centerX;
            double startY4 = (startX3 - centerX) * Math.sin(rad) + (startY3 - centerY) * Math.cos(rad) + centerY;
            double endX4 = (endX3 - centerX) * Math.cos(rad) - (endY3 - centerY) * Math.sin(rad) + centerX;
            double endY4 = (endX3 - centerX) * Math.sin(rad) + (endY3 - centerY) * Math.cos(rad) + centerY;

            g2d.drawLine((int) Math.round(startX4), (int) Math.round(startY4), (int) Math.round(endX4), (int) Math.round(endY4));
        }
    }
}
