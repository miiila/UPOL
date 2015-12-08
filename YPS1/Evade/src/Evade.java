import java.util.Observable;
import java.util.Observer;

/**
 * Created by miiila on 15/11/15.
 */
public class Evade implements Observer {

    public static void main(String[] args)
    {
        System.out.println("********************");
        System.out.println("* Welcome to EVADE *");
        System.out.println("********************");

        Evade evade = new Evade();

        GameManager gameManager = new GameManager();
        gameManager.addObserver(evade);
        Player player1 = new Human(gameManager);
        player1.setSign(Board.WHITE);
        Player player2 = new Computer(gameManager);
        player2.setSign(Board.BLACK);

        Player[] players = {player1, player2};
        gameManager.setPlayers(players);


        Board board = new Board();
        board.setupNewBoard();

        gameManager.startGame(board);
    }

    @Override
    public void update(Observable observable, Object o) {
        Board board = (Board) o;
        this.printBoard(board);

    }

    public void printBoard(Board board) {
        int boardArray[][] = board.getBoard();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int val = boardArray[i][j];
                switch (val) {
                    case Board.WHITE:
                        val = 'x';
                        break;
                    case Board.BLACK:
                        val = 'y';
                        break;
                    case Board.WHITE_KING:
                        val = 'X';
                        break;
                    case Board.BLACK_KING:
                        val = 'Y';
                        break;
                    case Board.FROZEN_KING:
                        val = 'Z';
                        break;
                    case Board.FROZEN:
                        val = 'z';
                        break;
                    default:
                        val = '0';
                        break;
                }
                System.out.printf("%c",val);
            }
            System.out.printf("\n");
        }
    }
}
