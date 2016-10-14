import java.util.List;
import java.util.ArrayList;

/**
 * Created by miiila on 15/11/15.
 */
public class Referee {

    public void setBoard(Board board) {
        this.board = board;
    }

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
        return from != null &&
                this.isOnBoard(from) &&
                this.isOnBoard(to) &&
                this.isTargetDifferent(from, to) &&
                this.isNotFrozen(from) &&
                this.isOwn(from, player) &&
                this.canFreeze(from, to, player);
    }

    public List<Turn> getValidTurnsForPlayer(Player player) {
        List<Turn> validTurns = new ArrayList();

        for(Turn turn : this.board.getPossibleTurns()) {
            if (this.validateTurn(turn, player)) {
                validTurns.add(turn);
            }
        }

        return validTurns;
    }

    public boolean checkLoose(Player player) {
        //If player cannot move, he loses
        if (this.getValidTurnsForPlayer(player).isEmpty()) {
            return true;
        }

        //If opponents king is on the winning side, current player loses
        //FIXME: Frozen king is handled as a winning position
        Deck deck = this.board.getDeck();
        for (Position position : deck.getPossiblePositions()){
            int piece = deck.getPositionValue(position);
            if(((piece & Deck.KING) > 0)) {
                if (((piece & Deck.WHITE) > 0 && position.getRow() == WHITE_WIN_LINE) || ((piece & Deck.BLACK) > 0 && position.getRow() == BLACK_WIN_LINE)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isOnBoard(Position position) {
        return position.getColumn() >= 0 && position.getColumn() < 6 &&
                position.getRow() >= 0 && position.getRow() < 6;
    }

    private boolean isTargetDifferent(Position from, Position to) {
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

        return true;
    }

    private boolean isNotFrozen(Position from) {
        Deck deck = board.getDeck();
        if((deck.getPositionValue(from) & Deck.FROZEN) == Deck.FROZEN) {
            return false;
        }

        return true;
    }

    private boolean isOwn(Position from, Player player) {
        Deck deck = board.getDeck();
        if((deck.getPositionValue(from) & player.getSign()) == 0) {
            return false;
        }

        return true;
    }

    private boolean canFreeze(Position from, Position to, Player player) {
        Deck deck = board.getDeck();

        if(deck.getPositionValue(to) != Deck.EMPTY &&
                (((deck.getPositionValue(to) & player.getSign()) > 0 ) ||
                ((deck.getPositionValue(from) & Deck.KING) > 0))) {
            return false;
        }

        return true;
    }
}
