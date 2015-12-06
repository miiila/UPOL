/**
 * Created by miiila on 15/11/15.
 */
public class Board {

    private int[][] board;
    private int[] history;

    public int[][] getBoard() {
        return this.board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[] getHistory() {
        return history;
    }

    public void setHistory(int[] history) {
        this.history = history;
    }

    public void makeTurn(Turn turn) {
        Position from = turn.getFrom();
        int i = this.getPositionValue(from);
        this.setPositionValue(from, 0);
        Position to = turn.getTo();
        this.setPositionValue(to,i);
    }

    public int getPositionValue(Position position) {
        //Classical problem - humans are indexing by [x,y], arrays are [y,x]
        return this.board[position.getRow()][position.getColumn()];
    }

    public void setPositionValue(Position position, int value) {
        //Classical problem - humans are indexing by [x,y], arrays are [y,x]
        this.board[position.getRow()][position.getColumn()] = value;
    }

    public int[][] undoTurn() {

        return this.board;
    }
}
