import java.util.ArrayList;

/**
 * Created by miiila on 15/11/15.
 */
public class Computer extends Player {

    public Computer (GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public Turn getTurn(Board board) {
        return AI.getBestTurn(board, this);
    }
}
