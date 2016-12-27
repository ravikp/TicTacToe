package io.ravikumar.tictactoe;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by ravikupin on 24/12/16.
 */

public class GameStateTest {

    @Test
    public void shouldHaveSameGameStatesAfterSimilarMoves() {
        GameState gameState = GameStateFactory.initBoard(3);
        GameState updatedGameState1 = gameState.update(1, Token.X).update(2, Token.X);
        GameState updatedGameState2 = gameState.update(2, Token.X).update(1, Token.X);
        assertThat(updatedGameState1, is(updatedGameState2));
    }

    @Test
    public void shouldNotBeSameWhenGameStatesAreDifferent() {
        GameState gameState = GameStateFactory.initBoard(3);
        GameState updatedGameState1 = gameState.update(1, Token.X).update(2, Token.X).update(3, Token.X);
        GameState updatedGameState2 = gameState.update(2, Token.X).update(1, Token.X);
        assertThat(updatedGameState1, not(is(updatedGameState2)));
    }

    @Test
    public void shouldDeclareAsWinnerWhenThreeCellsInARowWereMarkedBySamePlayer() {
        GameState gameState = GameStateFactory.initBoard(3);
        GameState newGameState = gameState.update(1, Token.X).update(2, Token.X).update(3, Token.X);
        assertThat(newGameState.isWinner(), is(true));
    }

    @Test
    public void shouldDeclareAsWinnerWhenThreeCellsInAColumnWereMarkedBySamePlayer() {
        GameState gameState = GameStateFactory.initBoard(3);
        GameState newGameState = gameState.update(3, Token.X).update(6, Token.X).update(9, Token.X);
        assertThat(newGameState.isWinner(), is(true));
    }

    @Test
    public void shouldDeclareAsWinnerWhenThreeCellsInADiagonalFromLeftToRight() {
        GameState gameState = GameStateFactory.initBoard(3);
        GameState newGameState = gameState.update(1, Token.X).update(5, Token.X).update(9, Token.X);
        assertThat(newGameState.isWinner(), is(true));
    }

    @Test
    public void shouldDeclareAsWinnerWhenThreeCellsInADiagonalFromRightToLeft() {
        GameState gameState = GameStateFactory.initBoard(5);
        GameState newGameState = gameState.update(13, Token.X).update(17, Token.X).update(21, Token.X);
        assertThat(newGameState.isWinner(), is(true));
    }

    @Test
    public void shouldDeclareAsDrawWhenBothPlayersDidNotWin() {
        GameState gameState = GameStateFactory.initBoard(3);
        GameState newGameState = gameState.
            update(1, Token.X).update(2, Token.O).update(3, Token.X).
            update(4, Token.O).update(5, Token.X).update(6, Token.O).
            update(7, Token.O).update(8, Token.X).update(9, Token.O);

        assertThat(newGameState.isWinner(), is(false));
        assertThat(newGameState.isDraw(), is(true));
    }

    @Test
    public void shouldNotDeclareAsDrawWhenOpenPositionsExist() {
        GameState gameState = GameStateFactory.initBoard(3);
        GameState newGameState = gameState.
            update(1, Token.X).update(2, Token.O).update(3, Token.X).
            update(4, Token.O).update(5, Token.X).update(6, Token.O).
            update(7, Token.O).update(8, Token.X);

        assertThat(newGameState.isDraw(), is(false));
    }

    @Test
    public void shouldReturnCellStateAfterInitialization() {
        GameState gameState = GameStateFactory.initBoard(1);
        Token[][] actual = gameState.getBoard();
        Token[][] expected = {{Token.EMPTY}};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnModifiedCellStateMakingAMove() {
        GameState gameState = GameStateFactory.initBoard(1);
        gameState = gameState.update(1, Token.X);
        Token[][] actual = gameState.getBoard();
        Token[][] expected = {{Token.X}};
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldReturnACloneCopyOfBoard() {
        GameState gameState = GameStateFactory.initBoard(1);

        Token[][] actual1 = gameState.getBoard();
        Token[][] actual2 = gameState.getBoard();

        actual1[0][0] = Token.X;
        assertThat(actual1, not(is(equalTo(actual2))));
    }
}
