import java.util.ArrayList;
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
            Turn turn = new Turn();
            boolean badTurn = false;
            if (this.referee.checkLoose(players[this.playerOnTurn])) {
                System.out.printf("Player %d won the game!\n", this.playerOnTurn ^ 1);
                break;
            }
            try {
                 turn = players[this.playerOnTurn].getTurn(this.board);
            }
            catch (Exception e) {
                badTurn = true;
                System.out.println("Bad turn format!");
            }
            if(!badTurn && this.referee.validateTurn(turn, players[this.playerOnTurn])) {
                this.board.makeTurn(turn);
                // Bitwise XOR for setting on turn player: 0^1 = 1, 1^1 = 0
                this.playerOnTurn = this.playerOnTurn ^ 1;
                if (this.players[this.playerOnTurn] instanceof Human ) {
                    System.out.printf("Doing turn: %d%d -> %d%d", turn.getFrom().getColumn(),turn.getFrom().getRow(),turn.getTo().getColumn(),turn.getTo().getRow());
                }
                System.out.println("\n------------------");
            }
            else {
                System.out.println("Invalid turn!");
            }
        }
    }

    public ArrayList<Turn> getValidTurnsForCurrentPlayer() {
        return this.referee.getValidTurnsForPlayer(players[this.playerOnTurn]);
    }

    private void refreshBoard() {
        this.setChanged();
        this.notifyObservers(this.board);
    }

}
