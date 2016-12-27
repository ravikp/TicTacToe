package io.ravikumar.tictactoe;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 * Created by ravikupin on 25/12/16.
 */
public class CurrentPlayerTest {

    @Test
    public void shouldInitialiseCurrentPlayer() {
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);
        Players players = new Players(player1, player2);
        assertThat(players.getCurrentPlayer(), is(player1));
        assertThat(players.getOtherPlayer(), is(player2));
    }

    @Test
    public void shouldGetOtherPlayerWhenSwapped() {
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);
        Players players = new Players(player1, player2);
        players.swapTurn();
        assertThat(players.getCurrentPlayer(), is(player2));
        assertThat(players.getOtherPlayer(), is(player1));
    }

    @Test
    public void shouldAssignTokensToPlayers() {
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);

        Players players = new Players(player1, player2);

        verify(players.getCurrentPlayer()).setToken(Token.X);
        verify(players.getOtherPlayer()).setToken(Token.O);
    }
}
