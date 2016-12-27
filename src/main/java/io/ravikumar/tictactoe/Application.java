package io.ravikumar.tictactoe;

public class Application {
    public static void main(String[] args) {
        IOReaderWriter ioReaderWriter = new GameIOReaderWriter();
        GameState gameState = GameStateFactory.initialise(ioReaderWriter);

        Player player1 = PlayerFactory.createPlayer1(ioReaderWriter);
        Player player2 = PlayerFactory.createPlayer2(ioReaderWriter);

        Players players = new Players(player1, player2);

        GameController gameController = new GameController(players);
        GameResult result = gameController.startGame(gameState, ioReaderWriter);

        ioReaderWriter.println(result);
    }
}


