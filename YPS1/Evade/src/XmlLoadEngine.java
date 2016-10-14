import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import javax.xml.stream.*;

public class XmlLoadEngine {

    public int getPlayerOnTurn() {
        return playerOnTurn;
    }

    private enum states {
        PARSING_HISTORY,
        PARSING_HISTORY_TURN,
        PARSING_HISTORY_TURN_FROM,
        PARSING_HISTORY_TURN_COLUMN,
        PARSING_HISTORY_TURN_TO,
        PARSING_HISTORY_TURN_ROW,
        PARSING_PLAYERS,
        PARSING_PLAYERS_PLAYER,
        PARSING_PLAYERS_PLAYER_NAME, PARSING_PLAYERS_PLAYER_LEVEL, PARSING_PLAYERS_PLAYER_SIGN, PARSING_PLAYERS_PLAYER_ONTURN, NULL
    }

    private states state = states.NULL;

    public ArrayList<Player> getParsedPlayers() {
        return parsedPlayers;
    }

    private ArrayList<Player> parsedPlayers;

    public Queue<Turn> getParsedHistory() {
        return parsedHistory;
    }

    private Player parsedPlayer;

    private int playerOnTurn;

    private Turn parsedTurn;

    private Position parsedPosition;

    private Queue<Turn> parsedHistory = new ArrayDeque<>();

    public void loadGame(String fileName, GameManager gameManager) {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        try {
            XMLStreamReader streamReader = factory.createXMLStreamReader(new FileReader(fileName));
            while (streamReader.hasNext()) {
                switch (streamReader.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        switch (streamReader.getName().toString()) {
                            case "Evade":
                                this.state = states.NULL;
                                System.out.printf("LOADING %s", fileName);
                            break;
                            case "Row":
                                    this.state = states.PARSING_HISTORY_TURN_ROW;
                            break;
                            case "Column":
                                    this.state = states.PARSING_HISTORY_TURN_COLUMN;
                            break;
                            case "History":
                                this.state = states.PARSING_HISTORY;
                            break;
                            case "Turn":
                                this.state = states.PARSING_HISTORY_TURN;
                                this.parsedTurn = new Turn();
                            break;
                            case "From":
                                this.state = states.PARSING_HISTORY_TURN_FROM;
                                this.parsedPosition = new Position(-1, -1);
                            break;
                            case "To":
                                this.state = states.PARSING_HISTORY_TURN_TO;
                                this.parsedPosition = new Position(-1, -1);
                            break;
                            case "Players":
                                this.state = states.PARSING_PLAYERS;
                                this.parsedPlayers = new ArrayList<>();
                            break;
                            case "Player":
                                this.state = states.PARSING_PLAYERS_PLAYER;
                                String attribute = streamReader.getAttributeValue(0);
                                this.parsedPlayer = (attribute.equals("Computer")) ? new Computer(gameManager) : new Human(gameManager);
                            break;
                            case "Name":
                                this.state = states.PARSING_PLAYERS_PLAYER_NAME;
                            break;
                            case "Level":
                                this.state = states.PARSING_PLAYERS_PLAYER_LEVEL;
                            break;
                            case "Sign":
                                this.state = states.PARSING_PLAYERS_PLAYER_SIGN;
                            break;
                            case "OnTurn":
                                this.state = states.PARSING_PLAYERS_PLAYER_ONTURN;
                            break;
                        }
                    break;
                    case XMLStreamConstants.CHARACTERS:
                        String input = streamReader.getText();
                        switch(this.state) {
                            case PARSING_HISTORY_TURN_ROW:
                                this.parsedPosition.setRow(Character.getNumericValue(input.charAt(0)));
                            break;
                            case PARSING_HISTORY_TURN_COLUMN:
                                this.parsedPosition.setColumn(Character.getNumericValue(input.charAt(0)));
                            break;
                            case PARSING_PLAYERS_PLAYER_NAME:
                                this.parsedPlayer.setName(input);
                            break;
                            case PARSING_PLAYERS_PLAYER_ONTURN:
                                if (Boolean.parseBoolean(input)) {
                                    this.playerOnTurn = this.parsedPlayers.size();
                                }
                            break;
                            case PARSING_PLAYERS_PLAYER_SIGN:
                                this.parsedPlayer.setSign(Character.getNumericValue(input.charAt(0)));
                            break;
                            case PARSING_PLAYERS_PLAYER_LEVEL:
                                for (Computer.levels level : Computer.levels.values()) {
                                    if (input.equals(level.toString())) {
                                        this.parsedPlayer.setLevel(level);
                                        break;
                                    }
                                }
                            break;
                        }
                    break;
                    case XMLStreamConstants.END_ELEMENT:
                        switch (streamReader.getName().toString()) {
                            case "Evade":
                                System.out.printf("\nLOADING %s FINISHED\n", fileName);
                            break;
                            case "Deck":
                            case "Players":
                            case "History":
                                this.state = states.NULL;
                            break;
                            case "Turn":
                                this.state = states.PARSING_HISTORY;
                                this.parsedHistory.add(this.parsedTurn);
                                this.parsedTurn = null;
                            break;
                            case "From":
                                this.state = states.PARSING_HISTORY_TURN;
                                this.parsedTurn.setFrom(this.parsedPosition);
                                this.parsedPosition = null;
                            break;
                            case "To":
                                this.state = states.PARSING_HISTORY_TURN;
                                this.parsedTurn.setTo(this.parsedPosition);
                                this.parsedPosition = null;
                            break;
                            case "Player":
                                this.state = states.PARSING_PLAYERS;
                                this.parsedPlayers.add(this.parsedPlayer);
                            break;
                        }
                    break;
                }
                streamReader.next();
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
