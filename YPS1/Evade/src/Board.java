import java.util.Collection;

/**
 * Created by miiila on 15/11/15.
 */
public class Board implements Cloneable {

    private Deck deck;
    private int[] history;

    public Deck getDeck() {
        return this.deck;
    }

//    public void setBoard(int[][] deck) {
//        this.deck = deck;
//    }

    public int[] getHistory() {
        return history;
    }

    public void setHistory(int[] history) {
        this.history = history;
    }

    public void setupNewBoard() {
        this.deck = new Deck();
    }

    public void makeTurn(Turn turn) {
        Position from = turn.getFrom();
        int i = this.deck.getPositionValue(from);
        this.deck.emptyPosition(from);
        Position to = turn.getTo();
        int positionToValue = this.deck.getPositionValue(to);
        this.deck.setPositionValue(to, positionToValue | i);
    }

//    public int[][] undoTurn() {
//
//        return this.deck;
//    }

    public Collection<Turn> getPossibleTurns() {
        return this.deck.getPossibleTurns();
    }

    public Board clone() {
        Board newBoard = new Board();
        newBoard.deck = this.deck.clone();

        return newBoard;
    }
}
