package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.contols.GeneralControl;
import sk.stuba.fei.uim.oop.maze.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GameLogic extends GeneralControl {
    private int boardSize;
    private List<List<CellPanel>> maze;
    private JPanel board;
    private Random rand;
    private List<CellPanel> correctPath;
    private CellPanel finishCell;

    public GameLogic(JPanel board, int gameSize) {
        this.board = board;
        this.boardSize = gameSize;
        this.rand = new Random();
        restartGame();
    }

    public void restartGame() {
        this.board.removeAll();
        this.board.setLayout(new GridLayout(boardSize, boardSize));
        maze = new ArrayList<>();
        initializeMaze();
        var initCells = getRandomCells();
        this.correctPath = genMaze(initCells[0], initCells[1]);
        this.finishCell = initCells[1];
        removeAllWalls();
    }

    public List<CellPanel> getCorrectPath() {
        return correctPath;
    }


    public CellPanel getFinishCell() {
        return finishCell;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }


    public CellPanel[] getRandomCells() {
        if(rand.nextInt(2) == 0) {
            return getRandomCell(0, rand.nextInt(boardSize), boardSize-1, rand.nextInt(boardSize));
        }
        else {
            return getRandomCell(rand.nextInt(boardSize-1), 0, rand.nextInt(boardSize), boardSize-1);
        }
    }

    public CellPanel[] getRandomCell(int row1, int col1, int row2, int col2){
        CellPanel newStartCell = maze.get(row1).get(col1);
        CellPanel finishCell = maze.get(row2).get(col2);
        newStartCell.setColor(Color.GREEN);
        finishCell.setColor(Color.RED);
        newStartCell.setConnected(true);
        newStartCell.setPath(true);
        finishCell.setConnected(true);
        finishCell.setPath(true);
        CellPanel[] result = new CellPanel[2];
        result[0] = newStartCell;
        result[1] = finishCell;
        return result;
    }

    public void removeAllWalls() {
        for (var row : maze){
            for (var cell :row) {
                cell.setBorder(null);
            }
        }
    }

    public List<CellPanel> genMaze(CellPanel startCell, CellPanel finishCell) {
        Stack<CellPanel> stack = new Stack<>();
        Map<CellPanel, CellPanel> prev = new HashMap<>();
        startCell.setVisited(true);
        stack.push(startCell);
        while (!stack.isEmpty()) {
            CellPanel currentCell = stack.pop();
            if (currentCell == finishCell) {
                return getPath(startCell, finishCell, prev);
            }
            List<CellPanel> neighbors = getUnvisitedCells(currentCell);
            List<CellPanel> unvisitedFinishNeighbors = new ArrayList<>();
            for (CellPanel neighbor : neighbors) {
                if (neighbor == finishCell) {
                    prev.put(finishCell, currentCell);
                    return getPath(startCell, finishCell, prev);
                }
                if (!neighbor.isVisited()) {
                    unvisitedFinishNeighbors.add(neighbor);
                }
            }
            if (unvisitedFinishNeighbors.size() > 0) {
                CellPanel nextCell = unvisitedFinishNeighbors.get(rand.nextInt(unvisitedFinishNeighbors.size()));
                removeWall(currentCell, nextCell);
                nextCell.setVisited(true);
                prev.put(nextCell, currentCell);
                stack.push(currentCell);
                stack.push(nextCell);
            }
        }
        return null;
    }

    private List<CellPanel> getPath(CellPanel startCell, CellPanel finishCell, Map<CellPanel, CellPanel> prev) {
        List<CellPanel> path = new ArrayList<>();
        CellPanel currentCell = finishCell;
        while (currentCell != startCell) {
            path.add(currentCell);
            currentCell.setPath(true);
            var nextCell = prev.get(currentCell);
            var nextAfterNextCell = prev.get(nextCell);

            int rowDiff = nextCell.getRow() - currentCell.getRow();
            int colDiff = nextCell.getCol() - currentCell.getCol();

            if (nextAfterNextCell != null && nextAfterNextCell.getCol() != currentCell.getCol() && nextAfterNextCell.getRow() != currentCell.getRow() ) {
                nextCell.setDirection(PipeForm.ROUNDED);
                checkRoundedCellRotation(currentCell, nextCell, nextAfterNextCell, 0, 1, -1, 0, 1, 0, 2, 1);
                checkRoundedCellRotation(currentCell, nextCell, nextAfterNextCell, 0, -1, 1, 0, -1, 0, 0, 3);
                checkRoundedCellRotation(currentCell, nextCell, nextAfterNextCell, 1, 0, 0, 1, 0, -1, 3, 2);
                checkRoundedCellRotation(currentCell, nextCell, nextAfterNextCell, -1, 0, 0, 1, 0, -1, 0, 1);
            }
            else if (nextAfterNextCell != null && nextCell != finishCell){
                nextCell.setDirection(PipeForm.STRAIGHT);
                checkStraightCellRotation(rowDiff, colDiff, nextCell, 0, 1, 0);
                checkStraightCellRotation(rowDiff, colDiff, nextCell, 0, -1, 0);
                checkStraightCellRotation(rowDiff, colDiff, nextCell, 1, 0,  1);
                checkStraightCellRotation(rowDiff, colDiff, nextCell, -1, 0, 1);
            }
            currentCell = nextCell;

            if (currentCell.getRotateCount() == 0) {
                currentCell.setConnected(true);
            } else if (currentCell.getPressedCount() == currentCell.getRotateCount()) {
                currentCell.setConnected(true);
            } else {
                currentCell.setConnected(false);
            }
        }
        path.add(startCell);
        Collections.reverse(path);
        return path;
    }

    public void checkStraightCellRotation(int rowDiff, int colDiff, CellPanel cell, int row, int col, int rotate) {
        if (rowDiff == row && colDiff == col) {
            cell.setRotateCount(rotate);
        }
    }

    public void checkRoundedCellRotation(CellPanel currentCell, CellPanel nextCell, CellPanel nextAfterNextCell, int row1, int col1, int row2, int col2, int row3, int col3, int rotate1, int rotate2) {
        int rowDiff = nextCell.getRow() - currentCell.getRow();
        int colDiff = nextCell.getCol() - currentCell.getCol();

        int nextRowDiff = nextAfterNextCell.getRow() - nextCell.getRow();
        int nextColDiff = nextAfterNextCell.getCol() - nextCell.getCol();

        if (rowDiff == row1 && colDiff == col1) {
            if (nextRowDiff == row2 && nextColDiff == col2) {
                nextCell.setRotateCount(rotate1);
            }
            else if (nextRowDiff == row3 && nextColDiff == col3) {
                nextCell.setRotateCount(rotate2);
            }
        }
    }

    private List<CellPanel> getUnvisitedCells(CellPanel cell) {
        int row = cell.getRow();
        int col = cell.getCol();
        List<CellPanel> neighbors = new ArrayList<>();

        if (row > 0 && !maze.get(row-1).get(col).isVisited()) {
            neighbors.add(maze.get(row-1).get(col));
        }
        if (row < boardSize-1 && !maze.get(row+1).get(col).isVisited()) {
            neighbors.add(maze.get(row+1).get(col));
        }
        if (col > 0 && !maze.get(row).get(col-1).isVisited()) {
            neighbors.add(maze.get(row).get(col-1));
        }
        if (col < boardSize-1 && !maze.get(row).get(col+1).isVisited()) {
            neighbors.add(maze.get(row).get(col+1));
        }
        return neighbors;
    }

    private void removeWall(CellPanel current, CellPanel neighbor) {
        int row1 = current.getRow();
        int col1 = current.getCol();
        int row2 = neighbor.getRow();
        int col2 = neighbor.getCol();

        if (row1 == row2) {
            if (col1 < col2) {
                current.setRightBorder(false);
                neighbor.setLeftBorder(false);

            } else {
                current.setLeftBorder(false);
                neighbor.setRightBorder(false);
            }
        } else {
            if (row1 < row2) {
                current.setBottomBorder(false);
                neighbor.setTopBorder(false);
            } else {
                current.setTopBorder(false);
                neighbor.setBottomBorder(false);
            }
        }
    }

    public void initializeMaze() {
        for(int i = 0; i < boardSize; i++) {
            var row = new ArrayList<CellPanel>();
            for(int j = 0; j < boardSize; j++) {
                CellPanel cell = new CellPanel(i, j, null);
                row.add(cell);

                board.add(cell);
            }
            maze.add(row);
        }
    }
}
