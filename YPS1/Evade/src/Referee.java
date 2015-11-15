/**
 * Created by miiila on 15/11/15.
 */
public class Referee {

    public boolean validateTurn(Turn turn) {
        if (turn.getFrom() == null) {
            return false;
        }

        return true;
    }
}
