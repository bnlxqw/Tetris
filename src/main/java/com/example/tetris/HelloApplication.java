package com.example.tetris;

import com.example.tetris.controller.GameController;
import com.example.tetris.controller.InputHandler;
import com.example.tetris.model.Board;
import com.example.tetris.model.ScoreSystem;
import com.example.tetris.view.GameCanvas;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        Board board = new Board();
        ScoreSystem scoreSystem = new ScoreSystem();
        GameCanvas gameCanvas = new GameCanvas();

        GameController gameController = new GameController(board, scoreSystem, gameCanvas);
        InputHandler inputHandler = new InputHandler(gameController);

        Pane root = new Pane(gameCanvas);
        Scene scene = new Scene(root, 300, 600);

        scene.setOnKeyPressed(inputHandler::handlePressed);

        stage.setTitle("Tetris - JavaFX Project");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        gameController.startGame();
    }

    public static void main(String[] args) {
        launch();
    }
}