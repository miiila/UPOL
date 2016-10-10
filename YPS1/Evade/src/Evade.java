import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;

public class Evade implements Observer {

    public static void main(String[] args)
    {
        System.out.println("********************");
        System.out.println("* Welcome to EVADE *");
        System.out.println("********************");

        Evade evade = new Evade();

        GameManager gameManager = new GameManager();
        gameManager.addObserver(evade);

        gameManager.setPlayers(Evade.setupPlayers(2, gameManager));

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
        Deck deck = board.getDeck();
        String [] deckArray = deck.toString().split("#");

        for (int i = 0; i < deckArray.length; i++) {
            for (int j = 0; j < 6; j++) {
                int val = Character.getNumericValue(deckArray[i].charAt(j));
                switch (val) {
                    case Deck.WHITE:
                        val = 'x';
                        break;
                    case Deck.BLACK:
                        val = 'y';
                        break;
                    case Deck.WHITE_KING:
                        val = 'X';
                        break;
                    case Deck.BLACK_KING:
                        val = 'Y';
                        break;
                    case Deck.FROZEN_KING:
                        val = 'Z';
                        break;
                    case Deck.FROZEN:
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

    private static Player[] setupPlayers(int count, GameManager gameManager) {
        Player[] players = new Player[count];

        for(int i=1;i<=count;i++) {
            System.out.printf("Select player no. %d ([C]omputer/[H]uman):\n", i);
            Player player = setupPlayer(gameManager);
            //TODO: Handle player signs better
            player.setSign(i);
            players[i-1] = player;
        }

        return players;
    }

    private static Player setupPlayer(GameManager gameManager) {
        String input = "";
        Player player = null;

        while(player == null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                input = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (input.charAt(0) == 'C') {
                player = new Computer(gameManager);
                Evade.askForLevel(player);
            }
            else if (input.charAt(0) == 'H') {
                player = new Human(gameManager);
                Evade.askForName(player);
            }
            else {
                System.out.println("Bad input, please write only 'C' or 'H'.");
            }
        }

        return player;
    }

    private static void askForName(Player player) {
        String input = "";
        System.out.println(String.format("What's your name?:"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            input = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.setName(input);
    }


    private static void askForLevel(Player player) {
        String input = "";
        System.out.println(String.format("Select difficulty: (E)asy - (M)edium - (H)ard"));
        boolean correct = false;
        while(!correct) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                input = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch(input.charAt(0)){
                case 'E':
                    player.setLevel(Computer.levels.EASY);
                    player.setName("Holly");
                    correct = true;
                    break;
                case 'M':
                    player.setLevel(Computer.levels.MEDIUM);
                    player.setName("Hex");
                    correct = true;
                    break;
                case 'H':
                    player.setLevel(Computer.levels.HARD);
                    player.setName("Hal 3000");
                    correct = true;
                    break;
                default:
                    System.out.println("Bad input, please write only 'E', 'M' or 'H'.");
                    break;
            }
        }
    }
}
