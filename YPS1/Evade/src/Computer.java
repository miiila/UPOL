/**
 * Created by miiila on 15/11/15.
 */
public class Computer extends Player {

    public Computer (GameManager gameManager) {
        super(gameManager);
    }

    public enum levels {
        EASY, MEDIUM, HARD
    }

    @Override
    public Turn getTurn(Board board) throws Exception {
        return AI.getBestTurn(board, this);
    }
}
