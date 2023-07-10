package sk.stuba.fei.uim.oop.maze;

import sk.stuba.fei.uim.oop.contols.CellControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class CellPanel extends CellControl {
    private boolean isVisited;
    private boolean isConnected;
    private int row;
    private double angel;
    private int col;
    private boolean isTopBorder;
    private boolean isRightBorder;
    private boolean isBottomBorder;
    private boolean isLeftBorder;
    private int rotateCount;
    private int pressedCount;

    private Pipe pipe;
    private boolean isPath;
    private PipeForm pipeForm;
    private Color color;
    public CellPanel(int x, int y, Color color){
        this.isTopBorder = true;
        this.color = color;
        this.angel = 0;
        this.isRightBorder = true;
        this.isBottomBorder = true;
        this.rotateCount = 0;
        this.isLeftBorder = true;
        this.isVisited = false;
        this.isPath = false;
        this.isConnected = false;
        this.pipeForm = PipeForm.NONE;
        this.row = x;
        this.col = y;
        this.setPreferredSize(new Dimension( 50, 50));
        this.addMouseListener(this);
        this.pipe = new Pipe(getX(), getY(), getWidth(), getHeight(), angel, isPath, pipeForm);
        this.setBorder(BorderFactory.createMatteBorder(isTopBorder ? 1 : 0, isLeftBorder ? 1 : 0, isBottomBorder ? 1 : 0, isRightBorder ? 0 : 1, Color.BLACK));
    }

    public int getPressedCount() {
        return pressedCount;
    }

    public void setColor(Color color) {
        this.color = color;
        this.setBackground(color);
    }

    public Pipe getPipe() {
        return pipe;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public void setDirection(PipeForm pipeForm) {
        this.pipeForm = pipeForm;
    }

    public int getRotateCount() {
        return rotateCount;
    }

    public void setRotateCount(int rotateCount) {
        this.rotateCount = rotateCount;
    }

    public void setPath(boolean path) {
        isPath = path;
    }

    @Override
    public int getWidth() {
        return super.getWidth();
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!pipeForm.equals(PipeForm.NONE)) {

            this.pipe = new Pipe(getX(), getY(), getWidth(), getHeight(), angel, isPath, pipeForm);

            pipe.paintComponent(g);
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setTopBorder(boolean topBorder) {
        isTopBorder = topBorder;
        this.setBorder(BorderFactory.createMatteBorder(0, isLeftBorder ? 1 : 0, isBottomBorder ? 1 : 0, isRightBorder ? 1 : 0, Color.BLACK));

    }

    public void setRightBorder(boolean rightBorder) {
        isRightBorder = rightBorder;
        this.setBorder(BorderFactory.createMatteBorder(isTopBorder ? 1 : 0, 0, isBottomBorder ? 1 : 0, isRightBorder ? 1 : 0, Color.BLACK));
    }

    public void setBottomBorder(boolean bottomBorder) {
        isBottomBorder = bottomBorder;
        this.setBorder(BorderFactory.createMatteBorder(isTopBorder ? 1 : 0, isLeftBorder ? 1 : 0, 0, isRightBorder ? 1 : 0, Color.BLACK));
    }

    public void setLeftBorder(boolean leftBorder) {
        isLeftBorder = leftBorder;
        this.setBorder(BorderFactory.createMatteBorder(isTopBorder ? 1 : 0, 0, isBottomBorder ? 1 : 0, isRightBorder ? 1 : 0, Color.BLACK));
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public boolean isVisited() {
        return isVisited;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        angel = (angel + 90) % 360;
        repaint();
        pressedCount ++;

        if (rotateCount == 0) {
            setConnected(true);
        } else if (pressedCount == rotateCount) {
            setConnected(true);
        } else if (pressedCount > rotateCount && isConnected) {
            setConnected(false);
            pressedCount = 0;
            if (pipeForm.equals(PipeForm.ROUNDED)) {
                rotateCount = 3;
            }
            else {
                rotateCount = 1;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBackground(Color.lightGray);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(color);

    }

    @Override
    public String toString() {
        return "row: " + row + " col" + col + " isConnected: " + isConnected;
    }
}
