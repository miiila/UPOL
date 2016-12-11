import javafx.beans.Observable;

import javax.swing.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Observer;

/**
 * Created by miiila on 11/12/2016.
 */
public class Evade_GUI implements Observer{
    private JPanel mainContainer;
    private JPanel setupGame;
    private JTextField playerOneName;
    private JRadioButton playerOneHumanRadioButton;
    private JTextField playerTwoName;
    private JRadioButton playerOneComputerRadioButton;
    private JRadioButton playerTwoHumanRadioButton;
    private JRadioButton playerTwoComputerRadioButton;
    private JPanel welcome;
    private JButton newGameButton;
    private JButton startGame;
    private JLabel difficulty;
    private JRadioButton playerOneEasyRadioButton;
    private JRadioButton playerOneMediumRadioButton;
    private JRadioButton playerOneHardRadioButton;
    private JRadioButton playerTwoEasyRadioButton;
    private JRadioButton playerTwoMediumRadioButton;
    private JRadioButton playerTwoHardRadioButton;
    private ArrayList<JRadioButton> playerOneDifficulty = new ArrayList<JRadioButton>();
    private ArrayList<JRadioButton> playerTwoDifficulty = new ArrayList<JRadioButton>();

    private GameManager gameManager = new GameManager();
    private Board board = new Board();

    public Evade_GUI() {

        this.gameManager = gameManager;
        this.board = board;

        this.playerOneDifficulty.add(playerOneEasyRadioButton);
        this.playerOneDifficulty.add(playerOneMediumRadioButton);
        this.playerOneDifficulty.add(playerOneHardRadioButton);

        this.playerTwoDifficulty.add(playerTwoEasyRadioButton);
        this.playerTwoDifficulty.add(playerTwoMediumRadioButton);
        this.playerTwoDifficulty.add(playerTwoHardRadioButton);

        this.verifyPlayerOneDifficulty();
        this.verifyPlayerTwoDifficulty();

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainContainer.getComponent(0).setVisible(false);
                mainContainer.getComponent(1).setVisible(true);
            }
        });

        playerOneComputerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifyPlayerOneDifficulty();
            }
        });

        playerOneHumanRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifyPlayerOneDifficulty();
            }
        });

        playerTwoComputerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifyPlayerTwoDifficulty();
            }
        });

        playerTwoHumanRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifyPlayerTwoDifficulty();
            }
        });

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player playerOne, playerTwo;

                ArrayList<Player> players = new ArrayList<>();


                if (playerOneHumanRadioButton.isSelected()) {
                    playerOne = new Human(gameManager);
                }
                else {
                    playerOne = new Computer(gameManager);
                    for (JRadioButton button : playerOneDifficulty) {
                        if (button.isSelected()) {
                            switch (button.getText()) {
                                case "Easy": playerOne.setLevel(Computer.levels.EASY);
                                case "Medium": playerOne.setLevel(Computer.levels.MEDIUM);
                                case "Hard": playerOne.setLevel(Computer.levels.HARD);
                            }
                        }
                    }
                }

                playerOne.setName(playerOneName.getText());
                playerOne.setSign(1);
                players.add(playerOne);

                if (playerTwoHumanRadioButton.isSelected()) {
                    playerTwo = new Human(gameManager);
                }
                else {
                    playerTwo = new Computer(gameManager);
                    for (JRadioButton button : playerTwoDifficulty) {
                        if (button.isSelected()) {
                            switch (button.getText()) {
                                case "Easy": playerTwo.setLevel(Computer.levels.EASY);
                                case "Medium": playerTwo.setLevel(Computer.levels.MEDIUM);
                                case "Hard": playerTwo.setLevel(Computer.levels.HARD);
                            }
                        }
                    }
                }

                playerTwo.setName(playerTwoName.getText());
                playerTwo.setSign(2);
                players.add(playerTwo);


                gameManager.setPlayers(players);
                board.setupNewBoard();
                // @TODO: Do it properly in new Thread
                gameManager.startGame(board);
            }
        });
    }

    private void verifyPlayerOneDifficulty() {
        boolean status = !playerOneHumanRadioButton.isSelected();
        for ( JRadioButton button : playerOneDifficulty) {
            button.setEnabled(status);
        }
    }

    private void verifyPlayerTwoDifficulty() {
        boolean status = !playerTwoHumanRadioButton.isSelected();
        for ( JRadioButton button : playerTwoDifficulty) {
            button.setEnabled(status);
        }
    }

    public static void main(String[] args) {
        Evade_GUI evade_gui = new Evade_GUI();

        JFrame frame = new JFrame();
        JPanel mainContainer = evade_gui.mainContainer;
        mainContainer.add(evade_gui.welcome);
        mainContainer.add(evade_gui.setupGame);

        frame.setContentPane(mainContainer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void update(java.util.Observable o, Object arg) {

    }
}
