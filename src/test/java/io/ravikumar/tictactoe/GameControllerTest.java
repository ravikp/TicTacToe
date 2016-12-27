package io.ravikumar.tictactoe;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ravikupin on 26/12/16.
 */
public class GameControllerTest {

    private Player player1;
    private Player player2;
    private OutputWriter outputWriter;
    private GameState initGame;

    @Before
    public void initialise() {
        player1 = mock(Player.class, "player");
        player2 = mock(Player.class, "player2");
        outputWriter = mock(OutputWriter.class);
        initGame = GameStateFactory.initBoard(3);
    }

    @Test
    public void shouldMakeFirstPlayerWin() {
        GameState input = initGame.
            update(1, Token.X).update(2, Token.O).update(3, Token.X).
            update(4, Token.O).update(5, Token.X).update(6, Token.O);

        GameState winningState = input.update(7, Token.X);

        when(player1.play(input)).thenReturn(winningState);

        GameResult result = new GameController(new Players(player1, player2)).startGame(input, outputWriter);

        assertThat(result.getWinner(), is(Optional.of(player1)));
        assertThat(result.getFinalGameStatus(), is(winningState));
    }

    @Test
    public void shouldMakeOtherPlayerWin() {
        GameState input = initGame.
            update(1, Token.X).update(3, Token.O).update(4, Token.X).
            update(7, Token.O);

        GameState player1Move = input.update(6, Token.X);
        when(player1.play(input)).thenReturn(player1Move);

        GameState player2Move = player1Move.update(5, Token.O);
        when(player2.play(player1Move)).thenReturn(player2Move);

        GameResult result = new GameController(new Players(player1, player2)).startGame(input, outputWriter);
        assertThat(result.getWinner(), is(Optional.of(player2)));
        assertThat(result.getFinalGameStatus(), is(player2Move));
    }

    @Test
    public void shouldLeadToDraw() {
        GameState input = initGame.
            update(1, Token.X).update(3, Token.O).update(5, Token.X).
            update(9, Token.O).update(6, Token.X).update(4, Token.O).
            update(8, Token.X).update(2, Token.O);

        GameState winningState = input.update(7, Token.X);
        when(player1.play(input)).thenReturn(winningState);

        GameResult result = new GameController(new Players(player1, player2)).startGame(input, outputWriter);

        assertThat(result.getWinner(), is(Optional.empty()));
        assertThat(result.getFinalGameStatus(), is(winningState));
    }

    @Test
    public void shouldTestLiveInteraction() {
        GameState player1Move1 = initGame.update(5, Token.X);
        when(player1.play(initGame)).thenReturn(player1Move1);

        GameState player2Move2 = player1Move1.update(3, Token.O);
        when(player2.play(player1Move1)).thenReturn(player2Move2);

        GameState player1Move3 = player2Move2.update(1, Token.X);
        when(player1.play(player2Move2)).thenReturn(player1Move3);

        GameState player2Move4 = player1Move3.update(6, Token.O);
        when(player2.play(player1Move3)).thenReturn(player2Move4);

        GameState player1Move5 = player2Move4.update(9, Token.X);
        when(player1.play(player2Move4)).thenReturn(player1Move5);

        GameResult result = new GameController(new Players(player1, player2)).startGame(initGame, outputWriter);

        assertThat(result.getWinner(), is(Optional.of(player1)));
        assertThat(result.getFinalGameStatus(), is(player1Move5));
    }
}
