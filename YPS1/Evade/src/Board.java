import java.util.*;

public class Board implements Cloneable {

    private Deck deck;
    private Stack<Turn> history = new Stack<>();

    public Deck getDeck() {
        return this.deck;
    }

    public Stack<Turn> getHistory() {
        return this.history;
    }

    public void setupNewBoard() {
        this.deck = new Deck();
    }

    public void makeTurn(Turn turn) {
        this.history.push(turn);
        Position from = turn.getFrom();
        int i = this.deck.getPositionValue(from);
        this.deck.emptyPosition(from);
        Position to = turn.getTo();
        int positionToValue = this.deck.getPositionValue(to);
        this.deck.setPositionValue(to, positionToValue | i);
    }

    public void undoTurn() {
        Turn undo = this.history.pop();
        Position to = undo.getFrom();
        int i = this.deck.getPositionValue(to);
        this.deck.emptyPosition(to);
        Position from = undo.getTo();
        int positionToValue = this.deck.getPositionValue(from);
        this.deck.setPositionValue(from, positionToValue | i);

    }

    public Collection<Turn> getPossibleTurns() {
        return this.deck.getPossibleTurns();
    }

    @Override
    public Board clone() {
        Board newBoard = new Board();
        newBoard.deck = this.deck.clone();

        return newBoard;
    }
}
