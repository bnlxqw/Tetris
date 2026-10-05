package com.example.tetris.model;

public class Piece {
    private PieceType type;
    private int[][] shape;
    private int x;
    private int y;

    public Piece(PieceType type) {
        this.type = type;
        this.shape = cloneMatrix(type.getShape());
        this.x = 4 - shape[0].length / 2; //вычисляет начальную координату x так, чтобы фигурка появлялась ровно по центру верхней части поля.
        this.y = 0;
    }

    public void rotate() {
        int rows = shape.length;
        int cols = shape[0].length;
        int[][] rotated = new int[cols][rows];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rotated[c][rows - 1 - r] = shape[r][c];
            }
        }
        this.shape = rotated;
    }

    public void rotateBack() {
        for (int i = 0; i < 3; i++) {
            rotate();
        }
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public void moveDown() {
        y++;
    }

    public void moveUp() {
        y--;
    }

    private int[][] cloneMatrix(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }

    public PieceType getType() {
        return type;
    }

    public int[][] getShape() {
        return shape;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}