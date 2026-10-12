package com.example.tetris.model;

public class ScoreSystem {
    private int score;
    private int lines;
    private int level;

    public ScoreSystem() {
        reset();
    }

    public void reset() {
        score = 0;
        lines = 0;
        level = 1;
    }

    public void addLines(int clearedLines) {
        if (clearedLines <= 0) return;

        lines += clearedLines;

        int points = switch (clearedLines) {
            case 1 -> 100;
            case 2 -> 300;
            case 3 -> 500;
            case 4 -> 800;
            default -> clearedLines * 200;
        };

        score += points * level;

        level = (lines / 10) + 1;
    }

    public int getScore() {
        return score;
    }

    public int getLines() {
        return lines;
    }

    public int getLevel() {
        return level;
    }
}