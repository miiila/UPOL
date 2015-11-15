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
        int [] from = turn.getFrom();
        int i = this.board[from[0]][from[1]];
        this.board[from[0]][from[1]] = 0;
        int [] to = turn.getTo();
        this.board[to[0]][to[1]] = i;
    }

    public int[][] undoTurn() {

        return this.board;
    }
}
