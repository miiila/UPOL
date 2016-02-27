/**
 * Created by miiila on 15/11/15.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Human extends Player {

    public Human (GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public Turn getTurn(Board board) throws Exception {
        String input = "";

        try {
            System.out.println("Enter your turn:");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            input = bufferedReader.readLine();

        }
        catch (IOException e) {
            System.out.println("Error reading from user");
        }

        return this.parseTurn(input);

    }


    private Turn parseTurn (String turnString) throws Exception{

        //@TODO: Just parse it here, don't verify it
        if (turnString.length() == 4
                && turnString.charAt(0) >= 'a' && turnString.charAt(0) <= 'f'
                && turnString.charAt(2) >= 'a' && turnString.charAt(2) <= 'f'
                && turnString.charAt(1) >= '1' && turnString.charAt(1) <= '6'
                && turnString.charAt(3) >= '1' && turnString.charAt(3) <= '6') {
            Turn turn = new Turn();
            Position positionFrom = new Position(turnString.charAt(0) - 'a',turnString.charAt(1) - '1');
            Position positionTo = new Position( turnString.charAt(2) - 'a', turnString.charAt(3) - '1');
            turn.setFrom(positionFrom);
            turn.setTo(positionTo);
            return turn;
        }
        else {
            throw new Exception();
        }

    }
}
