import java.util.ArrayList;

/**
 * Created by miiila on 15/11/15.
 */
public class Referee {

    private Board board;

    private static int BLACK_WIN_LINE = 0;
    private static int WHITE_WIN_LINE = 5;

    public Referee(Board board) {
        this.board = board;
    }

    public boolean validateTurn(Turn turn, Player player) {
        Position from = turn.getFrom();
        Position to = turn.getTo();

        // Make turns on board only
        if (!isOnBoard(from) || !isOnBoard(to)) {
            return false;
        }

        if (from == null) {
            return false;
        }

        // Don't go to the original position
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

        //Do not move frozen pieces
        Deck deck = board.getDeck();
        if((deck.getPositionValue(from) & Deck.FROZEN) == Deck.FROZEN) {
            return false;
        }

        //Move only own pieces
        if((deck.getPositionValue(from) & player.getSign()) == 0) {
            return false;
        }

        //Don't freeze your own piece
        if((deck.getPositionValue(to) & player.getSign()) > 0) {
            return false;
        }

        //Don't let king freeze anybody
        if (((deck.getPositionValue(from) & Deck.KING) > 0) && (deck.getPositionValue(to) != Deck.EMPTY)) {
            return false;
        }

        return true;
    }

    public ArrayList <Turn> getValidTurnsForPlayer(Player player) {
        ArrayList <Turn> validTurns = new ArrayList();
        int [][] board = this.board.getDeck().getDeck();
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

    public boolean checkLoose(Player player) {
        //If player cannot move, he lose
        if (getValidTurnsForPlayer(player).size() == 0) {
            return true;
        }

        //If opponents king is on the winning side, current player loses
        int [][] board = this.board.getDeck().getDeck();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int piece = board[i][j];
                if(((piece & Deck.KING) > 0)) {
                    if (((piece & Deck.WHITE) > 0 && i == WHITE_WIN_LINE) || ((piece & Deck.BLACK) > 0 && i == BLACK_WIN_LINE)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isOnBoard(Position position) {
        return position.getColumn() >= 0 && position.getColumn() < 6 &&
                position.getRow() >= 0 && position.getRow() < 6;
    }
}
