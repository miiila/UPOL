import java.util.Observable;

/**
 * Created by miiila on 15/11/15.
 */
public class GameManager extends Observable implements Runnable{

    private Board board;
    private Referee referee;
    private Player[] players;

    public void startGame(Board board) {
        this.board = board;
        this.referee = new Referee();
        this.run();
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    @Override
    public void run() {
        while (true) {
            this.refreshBoard();
            Turn turn = players[0].getTurn();
            if(referee.validateTurn(turn)) {
                this.board.makeTurn(turn);
            }
            else {
                System.out.println("Invalid turn!");
            }
        }
    }

    private void refreshBoard() {
        this.setChanged();
        this.notifyObservers(this.board);
    }
}
