import java.util.ArrayList;

/**
 * Created by miiila on 15/11/15.
 */
public class Computer extends Player {

    @Override
    public Turn getTurn(ArrayList <Turn> validTurns) {
        System.out.println(validTurns.size());
        return validTurns.get((int) (Math.random() * validTurns.size()));
    }
}
