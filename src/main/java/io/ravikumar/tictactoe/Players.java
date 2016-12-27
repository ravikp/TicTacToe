package io.ravikumar.tictactoe;

public class Players {
    private Player player1;
    private Player player2;

    Players(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        this.player1.setToken(Token.X);
        this.player2.setToken(Token.O);
    }

    public void swapTurn() {
        Player temp = player1;
        player1 = player2;
        player2 = temp;
    }

    public Player getCurrentPlayer() {
        return player1;
    }

    public Player getOtherPlayer() {
        return player2;
    }
}
