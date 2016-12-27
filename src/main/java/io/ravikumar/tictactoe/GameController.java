package io.ravikumar.tictactoe;

import java.util.Optional;

public class GameController {

    private final Players players;

    public GameController(Players players) {
        this.players = players;
    }

    public GameResult startGame(GameState gameState, OutputWriter outputWriter) {
        GameState newGameState = gameState;
        while (true) {
            outputWriter.print(newGameState);
            Player currentPlayer = players.getCurrentPlayer();
            newGameState = currentPlayer.play(newGameState);

            if(newGameState.isWinner()) return new GameResult(Optional.of(currentPlayer), newGameState);
            if(newGameState.isDraw()) return new GameResult(Optional.empty(), newGameState);

            players.swapTurn();
        }
    }
}
