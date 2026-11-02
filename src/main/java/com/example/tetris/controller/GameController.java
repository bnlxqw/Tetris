package com.example.tetris.controller;

import com.example.tetris.model.Board;
import com.example.tetris.model.Piece;
import com.example.tetris.model.ScoreSystem;
import com.example.tetris.view.GameCanvas;
import com.example.tetris.view.SidePanel;
import javafx.animation.AnimationTimer;

public class GameController {
    private final Board board;
    private final ScoreSystem scoreSystem;
    private final GameCanvas gameCanvas;
    private final SidePanel sidePanel;

    private Piece currentPiece;
    private Piece nextPiece; // Следующая фигурка
    private AnimationTimer gameLoop;

    private boolean isPlaying;
    private boolean isPaused;

    private long lastUpdate = 0;
    private static final long DROP_INTERVAL_NS = 500_000_000L;

    public GameController(Board board, ScoreSystem scoreSystem, GameCanvas gameCanvas, SidePanel sidePanel) {
        this.board = board;
        this.scoreSystem = scoreSystem;
        this.gameCanvas = gameCanvas;
        this.sidePanel = sidePanel;
        this.isPlaying = false;
        this.isPaused = false;
    }

    public void startGame() {
        board.clear();
        scoreSystem.reset();
        sidePanel.update(scoreSystem);

        currentPiece = Piece.getRandomPiece();
        nextPiece = Piece.getRandomPiece();
        sidePanel.drawNextPiece(nextPiece);

        isPlaying = true;
        isPaused = false;

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isPaused && isPlaying) {
                    if (now - lastUpdate >= DROP_INTERVAL_NS) {
                        moveDown();
                        lastUpdate = now;
                    }
                    gameCanvas.render(board, currentPiece);
                }
            }
        };
        gameLoop.start();
    }

    private void spawnPiece() {
        currentPiece = nextPiece;
        nextPiece = Piece.getRandomPiece();
        sidePanel.drawNextPiece(nextPiece);

        if (!board.isValidPosition(currentPiece, currentPiece.getX(), currentPiece.getY())) {
            gameOver();
        }
    }

    public void moveLeft() {
        if (!isPlaying || isPaused) return;
        if (board.isValidPosition(currentPiece, currentPiece.getX() - 1, currentPiece.getY())) {
            currentPiece.moveLeft();
        }
    }

    public void moveRight() {
        if (!isPlaying || isPaused) return;
        if (board.isValidPosition(currentPiece, currentPiece.getX() + 1, currentPiece.getY())) {
            currentPiece.moveRight();
        }
    }

    public void moveDown() {
        if (!isPlaying || isPaused) return;

        if (board.isValidPosition(currentPiece, currentPiece.getX(), currentPiece.getY() + 1)) {
            currentPiece.moveDown();
        } else {
            board.mergePiece(currentPiece);
            int clearedLines = board.clearLines();
            if (clearedLines > 0) {
                scoreSystem.addLines(clearedLines);
                sidePanel.update(scoreSystem);
            }
            spawnPiece();
        }
    }

    public void rotate() {
        if (!isPlaying || isPaused) return;
        currentPiece.rotate();
        if (!board.isValidPosition(currentPiece, currentPiece.getX(), currentPiece.getY())) {
            currentPiece.rotateBack();
        }
    }

    public void hardDrop() {
        if (!isPlaying || isPaused) return;
        while (board.isValidPosition(currentPiece, currentPiece.getX(), currentPiece.getY() + 1)) {
            currentPiece.moveDown();
        }
        moveDown();
    }

    public void togglePause() {
        if (!isPlaying) return;
        isPaused = !isPaused;
    }

    private void gameOver() {
        isPlaying = false;
        if (gameLoop != null) {
            gameLoop.stop();
        }
        System.out.println("Game Over! Final Score: " + scoreSystem.getScore());
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public boolean isPaused() {
        return isPaused;
    }
}