/**
 * Created by miiila on 15/11/15.
 */
public abstract class Player {

    private int level;
    private int sign;

    public abstract Turn getTurn();

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
}
