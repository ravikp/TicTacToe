package io.ravikumar.tictactoe;

import java.util.function.Predicate;

public class GameStateFactory {
    private static final String BOARD_SIZE_PROMPT = "Enter the board size(positive odd number >= 3):";
    private static final String WRONG_BOARD_SIZE_PROMPT = "Wrong board size.";

    public static GameState initBoard(int boardSize) {
        return new GameStateImpl(boardSize);
    }

    public static GameState initialise(IOReaderWriter ioReaderWriter) {
        Predicate<Integer> isOddPositiveNumber = (
            x -> x % 2 == 1 && x >= 3
        );
        int boardSize = ioReaderWriter.read(BOARD_SIZE_PROMPT, isOddPositiveNumber, WRONG_BOARD_SIZE_PROMPT);
        return initBoard(boardSize);
    }
}

