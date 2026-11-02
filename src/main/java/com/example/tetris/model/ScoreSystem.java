package com.example.tetris.model;

public class ScoreSystem {
    private int score = 0;
    private int lines = 0;
    private int highScore = 0;

    public void addLines(int count) {
        lines += count;
        switch (count) {
            case 1 -> score += 100;
            case 2 -> score += 300;
            case 3 -> score += 500;
            case 4 -> score += 800;
        }
        if (score > highScore) {
            highScore = score;
        }
    }

    public void reset() {
        score = 0;
        lines = 0;
    }

    public int getScore() {
        return score;
    }

    public int getLines() {
        return lines;
    }

    public int getHighScore() {
        return highScore;
    }
}