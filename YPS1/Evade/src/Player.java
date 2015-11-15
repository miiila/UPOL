/**
 * Created by miiila on 15/11/15.
 */
public abstract class Player {

    private int level;

    public abstract Turn getTurn();

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


}
