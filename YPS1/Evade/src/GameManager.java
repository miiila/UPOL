import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Queue;

public class GameManager extends Observable implements Runnable{

    private Board board;
    private Referee referee;
    private ArrayList<Player> players;
    private int playerOnTurn;

    public void startGame(Board board) {
        this.board = board;
        this.referee = new Referee(this.board);
        this.run();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
        this.playerOnTurn = 0;
    }

    @Override
    public void run() {
        while (true) {
            this.refreshBoard();
            Turn turn = new Turn();
            boolean badTurn = false;
            if (this.referee.checkLoose(players.get(this.playerOnTurn))) {
                Player winner = this.players.get(this.playerOnTurn ^ 1);
                System.out.printf("Player %s won the game!\n", winner.getName());
                break;
            }
            System.out.printf("%s's turn: ", this.players.get(this.playerOnTurn).getName());
            try {
                 turn = players.get(this.playerOnTurn).getTurn(this.board);
            }
            catch (Exception e) {
                badTurn = true;
                System.out.println("Bad turn format!");
            }
            if(!badTurn && this.referee.validateTurn(turn, players.get(this.playerOnTurn))) {
                this.board.makeTurn(turn);
                System.out.printf("%d%d -> %d%d", turn.getFrom().getColumn(), turn.getFrom().getRow(), turn.getTo().getColumn(), turn.getTo().getRow());
                System.out.println("\n------------------");
                // Bitwise XOR for setting on turn player: 0^1 = 1, 1^1 = 0
                this.playerOnTurn = this.playerOnTurn ^ 1;
            }
            else {
                System.out.println("Invalid turn!");
            }

            //@TODO: handle saveGame properly from GUI and from other thread
//            this.saveGame();
//            this.loadGame();
        }
    }

    public List<Turn> getValidTurnsForCurrentPlayer() {
        return this.referee.getValidTurnsForPlayer(players.get(this.playerOnTurn));
    }

    public void saveGame() {
        XmlSaveEngine xmlSaveEngine = new XmlSaveEngine();
        try {
            xmlSaveEngine.saveGame("Evade.xml", this.board, players, this.playerOnTurn);
        }
        catch (Exception e) {
            System.out.printf("Exception raised during saving the game.");
        }
    }

    public void loadGame() {
        XmlLoadEngine xmlLoadEngine = new XmlLoadEngine();
        Board oldBoard = this.board.clone();
        try {
            // Creates Player instances with `this`
            xmlLoadEngine.loadGame("Evade.xml", this);
            this.board = new Board();
            this.board.setupNewBoard();
            this.referee.setBoard(this.board);
            Queue<Turn> loadedHistory = xmlLoadEngine.getParsedHistory();
            this.setPlayers(xmlLoadEngine.getParsedPlayers());
            this.playerOnTurn = xmlLoadEngine.getPlayerOnTurn();
            loadHistory(loadedHistory);
        }
        catch (Exception e) {
            System.out.printf("Exception raised during loading the game.");
            this.board = oldBoard;
            e.printStackTrace();
        }
    }

    private void refreshBoard() {
        this.setChanged();
        this.notifyObservers(this.board);
    }

    private void loadHistory(Queue<Turn> history) throws Exception {
        int player = 0;
        for(Turn turn : history) {
            if (this.referee.validateTurn(turn, this.players.get(player))) {
                this.board.makeTurn(turn);
                player = player ^ 1;
            }
            else {
                //@TODO: Create Exception hierarchy
                throw new Exception("Invalid Turn");
            }
        }
    }
}
