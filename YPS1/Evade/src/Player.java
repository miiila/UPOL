import java.util.List;

/**
 * Created by miiila on 15/11/15.
 */
public abstract class Player {

    private int level;
    private int sign;
    //For properly obtaining of valid turns, gameManager reference is needed
    protected GameManager gameManager;

    public Player (GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public abstract Turn getTurn(Board board) throws Exception;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public int getSign() {
        return sign;
    }

    public List<Turn> getNextTurns() {
        return this.gameManager.getValidTurnsForCurrentPlayer();
    }
}
