package io.ravikumar.tictactoe;

import java.util.function.Predicate;

public interface InputReader {
    int read(String prompt, Predicate<Integer> predicate, String errorMessage);

    String read(String prompt);
}
