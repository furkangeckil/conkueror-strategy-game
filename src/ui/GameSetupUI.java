package ui;
import javax.swing.*;

import domain.Controller;
import domain.GameSetup;
import domain.GameState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GameSetupUI {
	private static ArrayList<Integer> gameOptions = new ArrayList();
	private static ArrayList<String> nickNames = new ArrayList();
	private static JButton nextButton = new JButton("Next");
	private static JButton backButton = new JButton("Back");
	private static JFrame optionsFrame;
	private static JFrame helpFrame;
	
    public static void showGameOptions() {
    	//GameSetup gameSetup = new GameSetup();
        optionsFrame = new JFrame("Game Options & Map Settings");
        optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        optionsFrame.setSize(800, 600);
        optionsFrame.setLayout(new BorderLayout());

        JPanel optionsPanel = new JPanel(new GridLayout(4, 2));

        optionsPanel.add(new JLabel("Number of players (2-6):"));
        JComboBox<Integer> numPlayersComboBox = new JComboBox<>(new Integer[]{2, 3, 4, 5, 6});
        optionsPanel.add(numPlayersComboBox);

        optionsPanel.add(new JLabel("Number of computer players (1-6):"));
        JComboBox<Integer> numComputerPlayersComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6});
        optionsPanel.add(numComputerPlayersComboBox);

        optionsPanel.add(new JLabel("Number of continents:"));
        JComboBox<Integer> numContinentsComboBox = new JComboBox<>(new Integer[]{2, 3, 4, 5, 6});
        optionsPanel.add(numContinentsComboBox);

        optionsFrame.add(optionsPanel, BorderLayout.CENTER);

        //nextButton = new JButton("Next");
        int numPlayers = (int) numPlayersComboBox.getSelectedItem();
        int numComputerPlayers = (int) numComputerPlayersComboBox.getSelectedItem();
        int numContinents = (int) numContinentsComboBox.getSelectedItem();
      
        gameOptions.add(numPlayers);
        gameOptions.add(numComputerPlayers);
      	gameOptions.add(numContinents);
      	
      	
      	
//        nextButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int numPlayers = (int) numPlayersComboBox.getSelectedItem();
//                int numComputerPlayers = (int) numComputerPlayersComboBox.getSelectedItem();
//                int numContinents = (int) numContinentsComboBox.getSelectedItem();
//                
//                gameOptions.add(numPlayers);
//                gameOptions.add(numComputerPlayers);
//                gameOptions.add(numContinents);
//                
//                //GameState gameState = gameSetup.setupNewGame(numPlayers, numComputerPlayers, numContinents, new ArrayList<>());
//                // Do something with the selected options, like creating a new game
//                //UserNameUI.showLogin(gameState, gameSetup);
//                Controller controller = new Controller(null, null);
//                optionsFrame.dispose();
//            }
//        });
      	
        optionsFrame.add(nextButton, BorderLayout.SOUTH);

        optionsFrame.setVisible(true);
        
    }

    public static void showHelpScreen() {
        helpFrame = new JFrame("Help Screen");
        helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        helpFrame.setSize(800, 600);
        helpFrame.setLayout(new BorderLayout());

        JTextArea instructionsArea = new JTextArea();
        instructionsArea.setText("Welcome to the game!\n\n" +
                "Instructions:\n" +
                "    Initial Sharing of Territories and Army Placement\n" +
                "      - Each player receives X amount of infantry (X depends on # of players)\n" +
                "      - All players roll a die, highest result places one infantry piece onto a territory to claim it\n" +
                        "      - All players roll a die, highest result places one infantry piece onto a territory to claim it\n" +
                "Press 'Start' to begin the game.");
        instructionsArea.setEditable(false);

        JPanel helpPanel = new JPanel(new GridLayout(4, 2));
        helpPanel.add(instructionsArea);

        helpFrame.add(helpPanel, BorderLayout.CENTER);

        //JButton backButton = new JButton("Back");
//        backButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                ui.MainMenu.showMainMenu();
//                helpFrame.dispose();
//            }
//        });
        helpFrame.add(backButton, BorderLayout.SOUTH);

        helpFrame.setVisible(true);
    }

	public static ArrayList<Integer> getGameOptions() {
		return gameOptions;
	}

	public static JButton getNextButton() {
		return nextButton;
	}
	
	public static JButton getBackButton() {
		return backButton;
	}

	public static JFrame getOptionsFrame() {
		return optionsFrame;
	}

	public static JFrame getHelpFrame() {
		return helpFrame;
	}

	public static void addActionListener(ActionListener actionListener ) {
    	nextButton.addActionListener(actionListener);
    	backButton.addActionListener(actionListener);
    }
    
	
}
