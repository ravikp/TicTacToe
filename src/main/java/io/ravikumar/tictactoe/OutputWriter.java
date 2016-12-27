package io.ravikumar.tictactoe;

public interface OutputWriter {
    void print(GameState gameState);

    void println(String message);

    void println(GameResult gameResult);
}
