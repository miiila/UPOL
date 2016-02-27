/**
 * Created by miiila on 27/02/16.
 */
public class Deck implements Cloneable{

    private int[][] deck;

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
        this.setPositionValue(position, this.EMPTY);
    }

    public Deck clone() {
        Deck deckClone = new Deck();

        for(int i=0;i<6;i++) {
            deckClone.deck[i] = this.deck[i].clone();
        }

        return deckClone;
    }

    //TODO: Hopefully refactor in order to don't expose deck at all
    public int[][] getDeck() {
        return this.deck;
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