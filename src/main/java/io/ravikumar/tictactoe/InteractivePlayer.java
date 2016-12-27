package io.ravikumar.tictactoe;

import java.util.function.Predicate;

public class InteractivePlayer implements Player {
    private static final String INCORRECT_POSITION = "Incorrect position.";

    protected final IOReaderWriter ioReaderWriter;
    private final String name;
    private Token token;

    InteractivePlayer(String name, IOReaderWriter ioReaderWriter) {
        this.name = name;
        this.ioReaderWriter = ioReaderWriter;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public GameState play(GameState current) {
        Predicate<Integer> validInput = input ->
            (
                (input >= 1) && (input <= current.getTotalCellsOnBoard()) && current.isEmpty(input)
            );

        int cellNumber = readInput(validInput);
        return current.update(cellNumber, token);
    }

    @Override
    public void setToken(Token token) {
        this.token = token;
    }

    private int readInput(Predicate<Integer> predicate) {
        String prompt = String.format("%s, choose a box to place an '%s' into:", this.getName(), token);
        return ioReaderWriter.read(prompt, predicate, INCORRECT_POSITION);
    }

}
