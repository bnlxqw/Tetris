package com.example.tetris.view;

import com.example.tetris.model.Board;
import com.example.tetris.model.Piece;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameCanvas extends Canvas {
    private static final int BLOCK_SIZE = 30;
    private final GraphicsContext gc;

    public GameCanvas() {
        super(300, 600);
        this.gc = getGraphicsContext2D();
    }

    public void render(Board board, Piece currentPiece) {
        gc.setFillColor(Color.web("#1e1e24"));
        gc.fillRect(0, 0, getWidth(), getHeight());

        drawGrid();

        drawBoard(board);

        if (currentPiece != null) {
            drawPiece(currentPiece);
        }
    }

    private void drawGrid() {
        gc.setStroke(Color.web("#2e2e38"));
        gc.setLineWidth(1);

        for (int x = 0; x <= getWidth(); x += BLOCK_SIZE) {
            gc.strokeLine(x, 0, x, getHeight());
        }
        for (int y = 0; y <= getHeight(); y += BLOCK_SIZE) {
            gc.strokeLine(0, y, getWidth(), y);
        }
    }

    private void drawBoard(Board board) {
        for (int r = 0; r < board.getHeight(); r++) {
            for (int c = 0; c < board.getWidth(); c++) {
                Color color = board.getCell(c, r);
                if (color != null) {
                    drawBlock(c, r, color);
                }
            }
        }
    }

    private void drawPiece(Piece piece) {
        int[][] shape = piece.getShape();
        int px = piece.getX();
        int py = piece.getY();
        Color color = piece.getType().getColor();

        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[r].length; c++) {
                if (shape[r][c] != 0 && (py + r) >= 0) {
                    drawBlock(px + c, py + r, color);
                }
            }
        }
    }

    private void drawBlock(int x, int y, Color color) {
        int pixelX = x * BLOCK_SIZE;
        int pixelY = y * BLOCK_SIZE;

        gc.setFillColor(color);
        gc.fillRect(pixelX, pixelY, BLOCK_SIZE, BLOCK_SIZE);

        gc.setFillColor(color.brighter());
        gc.fillRect(pixelX, pixelY, BLOCK_SIZE, 3);
        gc.fillRect(pixelX, pixelY, 3, BLOCK_SIZE);

        gc.setFillColor(color.darker());
        gc.fillRect(pixelX, pixelY + BLOCK_SIZE - 3, BLOCK_SIZE, 3);
        gc.fillRect(pixelX + BLOCK_SIZE - 3, pixelY, 3, BLOCK_SIZE);
    }
}