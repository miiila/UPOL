import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class Deck implements Cloneable{

    private int[][] deck;
    // Because of cloning, singletons sounds better
    private static Collection<Turn> possibleTurns = new LinkedList<>();
    private static Collection<Position> possiblePositions = new LinkedList<>();

    public static final int WHITE = 1;
    public static final int BLACK = 2;
    public static final int FROZEN = 3;
    public static final int KING = 4;
    public static final int WHITE_KING = 5;
    public static final int BLACK_KING = 6;
    public static final int FROZEN_KING= 7;
    public static final int EMPTY = 0;

    public Deck() {
        this.createNewDeck();
    }

    public int getPositionValue(Position position) {
        //Classical problem - humans are indexing by [x,y], arrays are [y,x]
        return this.deck[position.getRow()][position.getColumn()];
    }

    public void setPositionValue(Position position, int value) {
        //Classical problem - humans are indexing by [x,y], arrays are [y,x]
        this.deck[position.getRow()][position.getColumn()] = value;
    }

    public void emptyPosition(Position position) {
        this.setPositionValue(position, Deck.EMPTY);
    }

    //TODO: Hopefully refactor in order to don't expose deck at all
    public int[][] getDeck() {
        return this.deck;
    }

    public Collection<Turn> getPossibleTurns() {
        if (Deck.possibleTurns.isEmpty()) {
            this.createCollectionOfTurns();
        }
        return Deck.possibleTurns;
    }

    @Override
    public Deck clone() {
        Deck deckClone = new Deck();

        for(int i=0;i<6;i++) {
            deckClone.deck[i] = this.deck[i].clone();
        }

        return deckClone;
    }

    public Collection<Position> getPossiblePositions() {
        if (Deck.possiblePositions.isEmpty()) {
            for (int i = 0; i < deck.length; i++) {
                for (int j = 0; j < deck[i].length; j++) {
                    Deck.possiblePositions.add(new Position(j, i));
                }
            }
        }

        return Deck.possiblePositions;
    }

    private void createCollectionOfTurns() {
        for (int i = 0; i < deck.length; i++) {
            for (int j = 0; j < deck[i].length; j++) {
                for(int k = i-1; k < i+2; k++) {
                    for (int l = j-1;l < j+2; l++ ) {
                        Turn turn = new Turn();
                        turn.setFrom(new Position(j,i));
                        turn.setTo(new Position(l,k));
                        Deck.possibleTurns.add(turn);
                    }
                }
            }
        }
    }

    private void createNewDeck(){
        this.deck = new int [6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int val;
                if (i == 0) {
                    val = this.WHITE;
                    if (j == 2 || j == 3) {
                        val = this.KING | this.WHITE ;
                    }
                }
                else if (i == 5) {
                    val = this.BLACK;
                    if (j == 2 || j == 3) {
                        val = this.KING | this.BLACK;
                    }
                }
                else {
                    val = this.EMPTY;
                }
                this.deck[i][j] = val;
            }
        }
    }
}
