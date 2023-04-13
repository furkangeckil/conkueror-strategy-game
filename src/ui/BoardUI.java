package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoardUI {
    private static final Color[] CONTINENT_COLORS = {
            Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.CYAN
    };

    public static void showBoard(int numContinents, List<String> nicknameList) {
        JFrame boardFrame = new JFrame("World Map");
        boardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        boardFrame.setSize(800, 600);

        JPanel boardPanel = new JPanel(new GridBagLayout());
        boardFrame.add(boardPanel, BorderLayout.CENTER);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;

        Random random = new Random();
        List<List<JPanel>> territories = new ArrayList<>();

        for (int continentIndex = 0; continentIndex < numContinents; continentIndex++) {
            int numTerritories = 3 + random.nextInt(5); // Random number between 3 and 7
            List<JPanel> continentTerritories = new ArrayList<>();

            for (int territoryIndex = 0; territoryIndex < numTerritories; territoryIndex++) {
                JPanel territoryPanel = new JPanel();
                territoryPanel.setBackground(CONTINENT_COLORS[continentIndex]);
                territoryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                constraints.gridx = territoryIndex;
                constraints.gridy = continentIndex;
                boardPanel.add(territoryPanel, constraints);

                continentTerritories.add(territoryPanel);
            }

            territories.add(continentTerritories);
        }

        JPanel userNamesPanel = new JPanel();
        userNamesPanel.setLayout(new BoxLayout(userNamesPanel, BoxLayout.X_AXIS));

        for (int i = 0; i < nicknameList.size(); i++) {
            JLabel userNameLabel = new JLabel(nicknameList.get(i));
            userNameLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            userNameLabel.setOpaque(true);
            userNameLabel.setBackground(CONTINENT_COLORS[i]);
            userNamesPanel.add(userNameLabel);
            userNamesPanel.add(Box.createHorizontalStrut(10)); // Add spacing between labels
        }

        boardFrame.add(userNamesPanel, BorderLayout.NORTH);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(exitButton, BorderLayout.EAST);
        boardFrame.add(bottomPanel, BorderLayout.SOUTH);

        boardFrame.setVisible(true);
    }
}