package io.ravikumar.tictactoe;

import java.util.Arrays;
import java.util.stream.Stream;

public class GameStateImpl implements GameState {
    private static final int NO_OF_CONSECUTIVE_ITEMS = 3;
    private final Token[][] board;
    private final int boardSize;
    private Token token;

    public GameStateImpl(int boardSize) {
        this(boardSize, initialisedBoard(boardSize), Token.EMPTY);
    }

    private GameStateImpl(int boardSize, Token[][] board, Token token) {
        this.boardSize = boardSize;
        this.board = board;
        this.token = token;
    }

    private static Token[][] initialisedBoard(int boardSize) {
        Token[][] board = new Token[boardSize][boardSize];
        Arrays.stream(board).forEach(row -> Arrays.fill(row, Token.EMPTY));
        return board;
    }

    @Override
    public GameState update(int position, Token token) {
        Position positionOnBoard = Position.getPositionFromIndex(position, boardSize);
        int x = positionOnBoard.getRow();
        int y = positionOnBoard.getColumn();

        Token[][] board = getCloneBoard();
        board[x][y] = token;
        return new GameStateImpl(boardSize, board, token);
    }

    private Token[][] getCloneBoard() {
        Token[][] newBoard = board.clone();
        for (int i = 0; i < boardSize; i++) {
            newBoard[i] = board[i].clone();
        }
        return newBoard;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        GameStateImpl gameState = (GameStateImpl) o;

        return Arrays.deepEquals(board, gameState.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }


    @Override
    public boolean isWinner() {
        return (
            isMatchFoundInRows(token) ||
                isMatchFoundInColumns(token) ||
                isMatchFoundInDiagonalLeftToRight(token) ||
                isMatchFoundInDiagonalRightToLeft(token) ||
                false
        );

    }

    private boolean isMatchFoundInDiagonalLeftToRight(Token token) {
        boolean didWon;
        for (int i = 0; i < boardSize - NO_OF_CONSECUTIVE_ITEMS + 1; i++) {
            didWon = (
                board[i][i] == token &&
                    board[i + 1][i + 1] == token &&
                    board[i + 2][i + 2] == token
            );
            if(didWon) return didWon;
        }
        return false;
    }

    private boolean isMatchFoundInDiagonalRightToLeft(Token token) {
        boolean didWon;
        for (int row = 0, col = boardSize - 1; row < boardSize - NO_OF_CONSECUTIVE_ITEMS + 1; row++, col--) {
            didWon = (
                board[row][col] == token &&
                    board[row + 1][col - 1] == token &&
                    board[row + 2][col - 2] == token
            );
            if(didWon) return didWon;
        }
        return false;
    }

    private boolean isMatchFoundInRows(Token token) {
        boolean didWon;
        //find horizontally
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize - NO_OF_CONSECUTIVE_ITEMS + 1; col++) {
                didWon = (
                    board[row][col] == token &&
                        board[row][col + 1] == token &&
                        board[row][col + 2] == token
                );
                if(didWon) return didWon;

            }
        }
        return false;
    }

    private boolean isMatchFoundInColumns(Token token) {
        boolean didWon;
        //find horizontally
        for (int col = 0; col < boardSize; col++) {
            for (int row = 0; row < boardSize - NO_OF_CONSECUTIVE_ITEMS + 1; row++) {
                didWon = (
                    board[row][col] == token &&
                        board[row + 1][col] == token &&
                        board[row + 2][col] == token
                );

                if(didWon) return didWon;

            }
        }
        return false;
    }

    public boolean isDraw() {
        //we can optimise this further instead of isWinner returning boolean we can return whether X won
        //or X lost. But for now we are keeping it simple.
        return (!isWinner()) && (hasNoMovesLeft());
    }

    @Override
    public Token[][] getBoard() {
        return getCloneBoard();
    }

    private boolean hasNoMovesLeft() {
        Stream<Token> tokenStream = Arrays.stream(board).flatMap(x -> Arrays.stream(x));
        return tokenStream.noneMatch(x -> x == Token.EMPTY);
    }

    @Override
    public int getBoardSize() {
        return boardSize;
    }

    @Override
    public int getTotalCellsOnBoard() {
        return boardSize * boardSize;
    }

    @Override
    public boolean isEmpty(int cellNumber) {
        Position position = Position.getPositionFromIndex(cellNumber, boardSize);
        return board[position.getRow()][position.getColumn()] == Token.EMPTY;
    }
}
