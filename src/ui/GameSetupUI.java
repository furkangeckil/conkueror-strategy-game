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
                LoginUI.showLogin(gameState);
                optionsFrame.dispose();
            }
        });
        optionsFrame.add(nextButton, BorderLayout.SOUTH);

        optionsFrame.setVisible(true);
    }
}
