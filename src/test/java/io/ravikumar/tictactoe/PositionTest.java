package io.ravikumar.tictactoe;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PositionTest {

    @Test
    public void shoulReturnFirstCellInTheBoard() {
        Position position = Position.getPositionFromIndex(1, 3);
        Position expected = new Position(0, 0);
        assertThat(position, is(expected));
    }

    @Test
    public void shoulReturnMiddleCellInTheBoard() {
        Position position = Position.getPositionFromIndex(5, 3);
        Position expected = new Position(1, 1);
        assertThat(position, is(expected));
    }

    @Test
    public void shouldReturnBottomRightCellInTheBoard() {
        Position position = Position.getPositionFromIndex(9, 3);
        Position expected = new Position(2, 2);
        assertThat(position, is(expected));
    }
}
