package io.ravikumar.tictactoe;

/**
 * Created by ravikupin on 25/12/16.
 */
class Position {

    private final int row;
    private final int column;

    Position(int row, int column) {

        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if(row != position.row) return false;
        return column == position.column;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }

    @Override
    public String toString() {
        return "(" + this.row + "," + this.column + ")";
    }

    static Position getPositionFromIndex(int position, int boardSize) {
        int index = position - 1;
        int x = index / boardSize;
        int y = index % boardSize;
        return new Position(x, y);
    }
}
