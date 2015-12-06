/**
 * Created by miiila on 15/11/15.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Human extends Player {

    @Override
    public Turn getTurn() {
        String input = "";

        try {
            System.out.println("Enter your turn:");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            input = bufferedReader.readLine();

        }
        catch (IOException e) {
            System.out.println("Error reading from user");
        }

//@TODO: Exception!!!!
        return this.parseTurn(input);

    }


    private Turn parseTurn (String turnString) {

        if (turnString.length() == 4
                && turnString.charAt(0) >= 'a' && turnString.charAt(0) <= 'f'
                && turnString.charAt(2) >= 'a' && turnString.charAt(2) <= 'f'
                && turnString.charAt(1) >= '1' && turnString.charAt(1) <= '6'
                && turnString.charAt(3) >= '1' && turnString.charAt(3) <= '6') {
            Turn turn = new Turn();
            Position positionFrom = new Position();
            positionFrom.setColumn(turnString.charAt(0) - 'a');
            positionFrom.setRow(turnString.charAt(1) - '1');
            Position positionTo = new Position();
            positionTo.setColumn(turnString.charAt(2) - 'a');
            positionTo.setRow(turnString.charAt(3) - '1');
            turn.setFrom(positionFrom);
            turn.setTo(positionTo);
            return turn;
        }
        else {
            System.out.println("Bad turn format!");
            return new Turn();
        }

    }
}
