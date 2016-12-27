package io.ravikumar.tictactoe;

import java.util.Optional;

public class GameResult {
    private final GameState finalGameStatus;
    private final Optional<Player> player;

    public GameResult(Optional<Player> player, GameState finalGameStatus) {
        this.player = player;
        this.finalGameStatus = finalGameStatus;
    }

    public Optional<Player> getWinner() {
        return player;
    }

    public GameState getFinalGameStatus() {
        return finalGameStatus;
    }
}
