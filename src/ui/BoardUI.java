package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoardUI {
    private static final Color[] CONTINENT_COLORS = {
            Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.CYAN
            // Actually we do not need to color continents, we need to assign colors to players so that they can place armies
            // and make territories their colors.
    };

    public static void showBoard(int numContinents, List<String> nicknameList) {
        JFrame boardFrame = new JFrame("World Map");
        boardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        boardFrame.setSize(1000, 600);

        JPanel boardPanel = new JPanel(null); // Use a custom layout
        boardFrame.add(boardPanel, BorderLayout.CENTER);

        Random random = new Random();
        List<List<JPanel>> territories = new ArrayList<>();

        int continentSpacing = 0;
        int continentOffsetX = 40;
        int continentWidth = 160;

        for (int continentIndex = 0; continentIndex < numContinents; continentIndex++) {
            int numTerritories = 4 + random.nextInt(9); // Random number between 4 and 12
            List<JPanel> continentTerritories = new ArrayList<>();

            int continentX = continentOffsetX + continentIndex * (continentSpacing + continentWidth);
            int continentY = 50;

            for (int territoryIndex = 0; territoryIndex < numTerritories; territoryIndex++) {
                JPanel territoryPanel = new JPanel();
                territoryPanel.setBackground(CONTINENT_COLORS[continentIndex]);
                territoryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                int territoryWidth = 40;
                int territoryHeight = 40;
                int territoryX = continentX + (territoryIndex % 4) * territoryWidth;
                int territoryY = continentY + (territoryIndex / 4) * territoryHeight;

                territoryPanel.setBounds(territoryX, territoryY, territoryWidth, territoryHeight);

                boardPanel.add(territoryPanel);

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
            //userNameLabel.setBackground(CONTINENT_COLORS[i]);
            userNamesPanel.add(userNameLabel);
            userNamesPanel.add(Box.createHorizontalStrut(10)); // Add spacing between labels
        }

        boardFrame.add(userNamesPanel, BorderLayout.NORTH);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(exitButton, BorderLayout.EAST);
        boardFrame.add(bottomPanel, BorderLayout.SOUTH);

        boardFrame.setVisible(true);
    }
}