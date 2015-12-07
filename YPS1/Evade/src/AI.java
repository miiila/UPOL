import java.util.ArrayList;

/**
 * Created by miiila on 06/12/15.
 */
public class AI {

    private static int MAX = 99;
    private static Player player;

    public static Turn getBestTurn(Board board, Player currentPlayer) {
        player = currentPlayer;
        ArrayList<Turn> possibleTurns = currentPlayer.getNextTurns();
        int bestResult = -MAX;
        Turn bestTurn = null;

        for(Turn possibleTurn : possibleTurns) {
            Board newBoard = new Board();
            newBoard.setBoard(board.getBoardCopy());
            newBoard.makeTurn(possibleTurn);
            int result = Math.max(bestResult, -minimax(newBoard, 4));

            if (result > bestResult){
                bestResult = result;
                bestTurn = possibleTurn;
            }
        }

        return bestTurn;
    }

    public static int minimax(Board board, int depth) {

        int result;

        if (depth == 0) {
            //Heuristic evaluating function
            return (int) Math.random()*100;
        }
        else {
            ArrayList<Turn> possibleTurns = player.getNextTurns();
            result = -MAX;

            for(Turn possibleTurn : possibleTurns) {
                Board newBoard = new Board();
                newBoard.setBoard(board.getBoard());
                newBoard.makeTurn(possibleTurn);
                result = Math.max(result, -minimax(newBoard, depth-1));
            }
        }

        return result;
    }
}
