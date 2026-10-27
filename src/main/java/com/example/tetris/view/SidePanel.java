package com.example.tetris.view;

import com.example.tetris.model.ScoreSystem;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SidePanel extends VBox {
    private final Label scoreLabel;
    private final Label linesLabel;

    public SidePanel(ScoreSystem scoreSystem) {
        setPrefWidth(150);
        setPrefHeight(600);
        setStyle("-fx-background-color: #121216; -fx-padding: 20px;");
        setSpacing(20);

        Label titleLabel = new Label("TETRIS");
        titleLabel.setFont(Font.font(18));
        titleLabel.setTextFill(Color.web("#ffffff"));

        scoreLabel = new Label("Score:\n0");
        scoreLabel.setFont(Font.font(14));
        scoreLabel.setTextFill(Color.web("#a0a0a5"));

        linesLabel = new Label("Lines:\n0");
        linesLabel.setFont(Font.font(14));
        linesLabel.setTextFill(Color.web("#a0a0a5"));

        getChildren().addAll(titleLabel, scoreLabel, linesLabel);
    }

    public void update(ScoreSystem scoreSystem) {
        scoreLabel.setText("Score:\n" + scoreSystem.getScore());
        linesLabel.setText("Lines:\n" + scoreSystem.getLines());
    }
}