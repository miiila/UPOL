import java.util.ArrayList;

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

        if (!isOnBoard(from) || !isOnBoard(to)) {
            return false;
        }

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
//
//        if(board.getPositionValue(to) % player.getSign() == 0) {
//            return false;
//        }

        return true;
    }

    public ArrayList <Turn> getValidTurnsForPlayer(Player player) {
        ArrayList <Turn> validTurns = new ArrayList();
        int [][] board = this.board.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                for(int k = i-1; k < i+2; k++) {
                    for (int l = j-1;l < j+2; l++ ) {
                        Turn turn = new Turn();
                        turn.setFrom(new Position(j,i));
                        turn.setTo(new Position(l,k));
                        if (this.validateTurn(turn, player)) {
                            validTurns.add(turn);
                        }
                    }
                }

            }
        }

        return validTurns;
    }

    private boolean isOnBoard(Position position) {
        return position.getColumn() >= 0 && position.getColumn() < 6 &&
                position.getRow() >= 0 && position.getRow() < 6;
    }
}
