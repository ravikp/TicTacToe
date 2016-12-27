package io.ravikumar.tictactoe;

public interface GameState {
    GameState update(int position, Token token);

    boolean isWinner();

    boolean isDraw();

    Token[][] getBoard();

    int getBoardSize();

    int getTotalCellsOnBoard();

    boolean isEmpty(int cellNumber);
}
