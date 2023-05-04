package ui;
import javax.swing.*;

import domain.GameSetup;
import domain.GameState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameSetupUI {
    public static void showGameOptions(GameSetup gameSetup) {
            JFrame optionsFrame = new JFrame("Game Options & Map Settings");
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

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numPlayers = (int) numPlayersComboBox.getSelectedItem();
                int numComputerPlayers = (int) numComputerPlayersComboBox.getSelectedItem();
                int numContinents = (int) numContinentsComboBox.getSelectedItem();
                GameState gameState = gameSetup.setupNewGame(numPlayers, numComputerPlayers, numContinents, new ArrayList<>());
                // Do something with the selected options, like creating a new game
                UserNameUI.showLogin(gameState, gameSetup);
                optionsFrame.dispose();
            }
        });
        optionsFrame.add(nextButton, BorderLayout.SOUTH);

        optionsFrame.setVisible(true);
    }

    public static void showHelpScreen(GameSetup gameSetup) {
        JFrame helpFrame = new JFrame("Help Screen");
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

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ui.MainMenu.showMainMenu(gameSetup);
                helpFrame.dispose();
            }
        });
        helpFrame.add(backButton, BorderLayout.SOUTH);

        helpFrame.setVisible(true);
    }


}
