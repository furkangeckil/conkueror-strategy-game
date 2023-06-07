package ui;
import javax.swing.*;

import domain.controllers.Controller;
import domain.game.GameSetup;
import domain.game.GameState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GameSetupUI {
	protected static UserNameUI userNameUI = new UserNameUI();
	private static ArrayList<Integer> gameOptions = new ArrayList();
	private static ArrayList<String> nickNames = new ArrayList();
	private static JButton nextButton = new JButton("Next");
	private static JButton backButton = new JButton("Back");
	private static JFrame optionsFrame;
	
	
    public static void showGameOptions() {
    	
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

        nextButton = new JButton("Next");
      	
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numPlayers = (int) numPlayersComboBox.getSelectedItem();
                int numComputerPlayers = (int) numComputerPlayersComboBox.getSelectedItem();
                int numContinents = (int) numContinentsComboBox.getSelectedItem();
                
                gameOptions.add(numPlayers);
                gameOptions.add(numComputerPlayers);
                gameOptions.add(numContinents);
                           
                optionsFrame.dispose();
                userNameUI.showLogin(gameOptions);
            }
        });
      	
        optionsFrame.add(nextButton, BorderLayout.SOUTH);

        optionsFrame.setVisible(true);
        
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

	public static void addActionListener(ActionListener actionListener ) {
    	nextButton.addActionListener(actionListener);
    	backButton.addActionListener(actionListener);
    }
    
	
}
