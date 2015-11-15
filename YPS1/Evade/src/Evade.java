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
        Player player1 = new Human();
        Player player2 = new Human();

        Player[] players = {player1, player2};
        gameManager.setPlayers(players);
        int[][] boardArray = new int [6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int val = 0;
                if (i == 0) {
                    val = 10;
                }
                else if (i == 5) {
                    val = 20;
                }
                if (j == 2 || j == 3) {
                    val *= 10;
                }

                boardArray[i][j] = val;
            }
        }

        Board board = new Board();
        board.setBoard(boardArray);

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
                    case 10:
                        val = 'x';
                        break;
                    case 20:
                        val = 'y';
                        break;
                    case 100:
                        val = 'X';
                        break;
                    case 200:
                        val = 'Y';
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
