import java.util.Observable;

/**
 * Created by miiila on 15/11/15.
 */
public class GameManager extends Observable implements Runnable{

    private Board board;
    private Referee referee;
    private Player[] players;
    private int playerOnTurn;

    public void startGame(Board board) {
        this.board = board;
        this.referee = new Referee(this.board);
        this.run();
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
        this.playerOnTurn = 0;
    }

    @Override
    public void run() {
        while (true) {
            this.refreshBoard();
            Turn turn = players[this.playerOnTurn].getTurn();
            if(referee.validateTurn(turn, players[this.playerOnTurn])) {
                this.board.makeTurn(turn);
                // Bitwise XOR for setting on turn player: 0^1 = 1, 1^1 = 0
                this.playerOnTurn = this.playerOnTurn ^ 1;
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
