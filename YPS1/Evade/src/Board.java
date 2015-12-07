import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by miiila on 15/11/15.
 */
public class Board {

    private int[][] board;
    private int[] history;

    public static final int WHITE = 3;
    public static final int WHITE_KING = 30;
    public static final int BLACK = 7;
    public static final int BLACK_KING = 70;
    public static final int EMPTY = 0;

    private int iterableRows = 0;
    private int iterableCols = 0;

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

    public void setupNewBoard() {
        this.board = new int [6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int val = this.EMPTY;
                if (i == 0) {
                    val = this.WHITE;
                }
                else if (i == 5) {
                    val = this.BLACK;
                }
                if (j == 2 || j == 3) {
                    val *= 10;
                }

                this.board[i][j] = val;
            }
        }
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

    public int [][] getBoardCopy() {
        int [][] boardCopy = new int [6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                boardCopy[i][j] = this.board[i][j];
            }
        }

        return boardCopy;
    }
}
