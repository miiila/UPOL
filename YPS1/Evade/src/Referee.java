/**
 * Created by miiila on 15/11/15.
 */
public class Referee {

    private Board board;

    public Referee(Board board) {
        this.board = board;
    }

    public boolean validateTurn(Turn turn, Player player) {
        Position from = turn.getFrom();
        Position to = turn.getTo();

        if (from == null) {
            return false;
        }

        int columnDiff = from.getColumn() - to.getColumn();
        if (Math.abs(columnDiff) > 1 ) {
            return false;
        }

        int rowDiff = from.getRow() - to.getRow();
        if (Math.abs(rowDiff) > 1 ) {
            return false;
        }

        if (rowDiff == 0 && columnDiff == 0) {
            return false;
        }

        //TEMP!!!!
        if(board.getPositionValue(from) == 0) {
            return false;
        }

        if(board.getPositionValue(from) % player.getSign() != 0) {
            return false;
        }

        if(board.getPositionValue(to) % player.getSign() == 0) {
            return false;
        }


        return true;
    }
}
