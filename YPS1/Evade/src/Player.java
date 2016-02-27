import java.util.List;

/**
 * Created by miiila on 15/11/15.
 */
public abstract class Player {

    private Computer.levels level;
    private int sign;
    private String name;
    //For properly obtaining of valid turns, gameManager reference is needed
    protected GameManager gameManager;

    public Player (GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public abstract Turn getTurn(Board board) throws Exception;

    public Computer.levels getLevel() {
        return level;
    }

    public void setLevel(Computer.levels level) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
