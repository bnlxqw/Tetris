package com.example.tetris.view;

import com.example.tetris.model.Piece;
import com.example.tetris.model.ScoreSystem;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SidePanel extends VBox {
    private final Label scoreLabel;
    private final Label highScoreLabel; // Метка рекорда
    private final Label linesLabel;
    private final Canvas nextPieceCanvas;
    private final GraphicsContext nextGc;

    private static final int BLOCK_SIZE = 20;

    public SidePanel(ScoreSystem scoreSystem) {
        setPrefWidth(150);
        setPrefHeight(600);
        setStyle("-fx-background-color: #121216; -fx-padding: 20px;");
        setSpacing(15);

        Label titleLabel = new Label("TETRIS");
        titleLabel.setFont(Font.font(18));
        titleLabel.setTextFill(Color.web("#ffffff"));

        scoreLabel = new Label("Score:\n0");
        scoreLabel.setFont(Font.font(14));
        scoreLabel.setTextFill(Color.web("#a0a0a5"));

        highScoreLabel = new Label("High:\n0");
        highScoreLabel.setFont(Font.font(14));
        highScoreLabel.setTextFill(Color.web("#e5c07b"));

        linesLabel = new Label("Lines:\n0");
        linesLabel.setFont(Font.font(14));
        linesLabel.setTextFill(Color.web("#a0a0a5"));

        Label nextLabel = new Label("Next:");
        nextLabel.setFont(Font.font(14));
        nextLabel.setTextFill(Color.web("#a0a0a5"));

        nextPieceCanvas = new Canvas(100, 100);
        nextGc = nextPieceCanvas.getGraphicsContext2D();

        getChildren().addAll(titleLabel, scoreLabel, highScoreLabel, linesLabel, nextLabel, nextPieceCanvas);
    }

    public void update(ScoreSystem scoreSystem) {
        scoreLabel.setText("Score:\n" + scoreSystem.getScore());
        highScoreLabel.setText("High:\n" + scoreSystem.getHighScore());
        linesLabel.setText("Lines:\n" + scoreSystem.getLines());
    }

    public void drawNextPiece(Piece nextPiece) {
        nextGc.setFill(Color.web("#1e1e24"));
        nextGc.fillRect(0, 0, nextPieceCanvas.getWidth(), nextPieceCanvas.getHeight());

        if (nextPiece == null) return;

        int[][] shape = nextPiece.getShape();
        Color color = nextPiece.getType().getColor();

        int shapeWidth = shape[0].length;
        int shapeHeight = shape.length;
        double offsetX = (nextPieceCanvas.getWidth() - shapeWidth * BLOCK_SIZE) / 2.0;
        double offsetY = (nextPieceCanvas.getHeight() - shapeHeight * BLOCK_SIZE) / 2.0;

        for (int r = 0; r < shapeHeight; r++) {
            for (int c = 0; c < shapeWidth; c++) {
                if (shape[r][c] != 0) {
                    double x = offsetX + c * BLOCK_SIZE;
                    double y = offsetY + r * BLOCK_SIZE;

                    nextGc.setFill(color);
                    nextGc.fillRect(x, y, BLOCK_SIZE - 1, BLOCK_SIZE - 1);
                }
            }
        }
    }
}