package com.example.tetris.controller;

import javafx.scene.input.KeyEvent;

public class InputHandler {
    private final GameController gameController;

    public InputHandler(GameController gameController) {
        this.gameController = gameController;
    }

    public void handlePressed(KeyEvent event) {
        if (gameController == null || !gameController.isPlaying()) {
            return;
        }

        switch (event.getCode()) {
            case LEFT -> gameController.moveLeft();
            case RIGHT -> gameController.moveRight();
            case DOWN -> gameController.moveDown();
            case UP, X -> gameController.rotate();
            case SPACE -> gameController.hardDrop();
            case ESCAPE -> gameController.togglePause();
            default -> {}
        }
    }
}