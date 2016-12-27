package io.ravikumar.tictactoe;

public interface Player {
    String getName();

    GameState play(GameState current);

    void setToken(Token token);
}
