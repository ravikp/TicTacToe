package io.ravikumar.tictactoe;

public class PlayerFactory {
    public static Player createPlayer1(IOReaderWriter ioReaderWriter) {
        return createPlayer("Player 1", ioReaderWriter);
    }

    public static Player createPlayer2(IOReaderWriter ioReaderWriter) {
        return createPlayer("Player 2", ioReaderWriter);
    }

    private static Player createPlayer(String sudoName, IOReaderWriter ioReaderWriter) {
        String prompt = "Enter name for " + sudoName;
        String result = ioReaderWriter.read(prompt);
        return new InteractivePlayer(result, ioReaderWriter);
    }
}
