package com.example.tetris.model;

import javafx.scene.paint.Color;

public class Board {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;

    private final Color[][] grid;

    public Board() {
        grid = new Color[HEIGHT][WIDTH];
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public Color getCell(int x, int y) {
        if (y >= 0 && y < HEIGHT && x >= 0 && x < WIDTH) {
            return grid[y][x];
        }
        return null;
    }

    public boolean isValidPosition(Piece piece) {
        int[][] shape = piece.getShape();
        int px = piece.getX();
        int py = piece.getY();

        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[r].length; c++) {
                if (shape[r][c] != 0) {
                    int boardX = px + c;
                    int boardY = py + r;

                    if (boardX < 0 || boardX >= WIDTH || boardY >= HEIGHT) {
                        return false;
                    }
                    if (boardY < 0) {
                        continue;
                    }
                    if (grid[boardY][boardX] != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void mergePiece(Piece piece) {
        int[][] shape = piece.getShape();
        int px = piece.getX();
        int py = piece.getY();
        Color color = piece.getType().getColor();

        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[r].length; c++) {
                if (shape[r][c] != 0) {
                    int boardX = px + c;
                    int boardY = py + r;
                    if (boardY >= 0 && boardY < HEIGHT && boardX >= 0 && boardX < WIDTH) {
                        grid[boardY][boardX] = color;
                    }
                }
            }
        }
    }

    public int clearLines() {
        int linesCleared = 0;

        for (int r = HEIGHT - 1; r >= 0; r--) {
            boolean isFull = true;
            for (int c = 0; c < WIDTH; c++) {
                if (grid[r][c] == null) {
                    isFull = false;
                    break;
                }
            }

            if (isFull) {
                linesCleared++;
                for (int moveRow = r; moveRow > 0; moveRow--) {
                    System.arraycopy(grid[moveRow - 1], 0, grid[moveRow], 0, WIDTH);
                }
                for (int c = 0; c < WIDTH; c++) {
                    grid[0][c] = null;
                }
                r++;
            }
        }
        return linesCleared;
    }

    public void clear() {
        for (int r = 0; r < HEIGHT; r++) {
            for (int c = 0; c < WIDTH; c++) {
                grid[r][c] = null;
            }
        }
    }
}