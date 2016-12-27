package io.ravikumar.tictactoe;

import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

public class GameIOReaderWriter implements IOReaderWriter {

    private final Scanner scanner;

    public GameIOReaderWriter() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void print(GameState gameState) {
        int boardSize = gameState.getBoardSize();
        Token[][] tokens = gameState.getBoard();

        String rowLine = String.join("", Collections.nCopies(gameState.getBoardSize(), "----"));

        println("");

        for (int row = 0, cellid = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                cellid++;
                print(String.format(" %s ", getDisplayValue(tokens[row][col], cellid)));

                if(col + 1 < boardSize)
                    print("|");
            }
            if(row + 1 < boardSize)
                println("\n" + rowLine);
        }

        println("\n");
    }

    private String getDisplayValue(Token token, int cellNumber) {
        if(token == Token.EMPTY) return Integer.toString(cellNumber);
        return token.toString();
    }

    @Override
    public int read(String prompt, Predicate<Integer> predicate, String errorMessage) {
        int result = -1;
        Optional<Integer> maybeInt = Optional.empty();

        println(prompt);

        do {
            print(">> ");
            maybeInt = readInteger();
            if(maybeInt.isPresent() && predicate.test(maybeInt.get())) {
                return maybeInt.get();
            }
            println(errorMessage + " " + prompt);
        } while (!maybeInt.isPresent());

        return result;
    }

    private Optional<Integer> readInteger() {
        try {
            return Optional.of(Integer.parseInt(scanner.next()));
        } catch (NumberFormatException exception) {
            return Optional.empty();
        }
    }

    @Override
    public String read(String prompt) {
        println(prompt);
        print(">> ");
        return scanner.next();
    }

    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public void println(GameResult gameResult) {
        print(gameResult.getFinalGameStatus());
        print(gameResult.getWinner());
    }

    private void print(Optional<Player> winner) {
        String output = winner.
            map(player -> String.format("Congratulations %s!, You have won.", player.getName())).
            orElse("Good game, well it was a draw.");

        println(output);
    }

    private void print(String message) {
        System.out.print(message);
    }
}
