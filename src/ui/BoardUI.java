package ui;

import javax.swing.*;

import domain.GameEnum;
import domain.GameSetup;
import domain.Territory;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.util.ArrayList;
import java.util.List;
//import java.util.Random;

public class BoardUI {
    private static final Color[] PLAYER_COLORS = {
            Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.CYAN
    };

    private static String[][] createTerritoryMatrix(Territory[] territories) {
        String[][] matrix = new String[7][12];
        matrix[0][1] = territories[0].getName();
        matrix[0][2] = territories[1].getName(); 
        matrix[0][3] = territories[2].getName();
        matrix[0][4] = territories[3].getName();
        matrix[0][5] = territories[4].getName();
        matrix[1][2] = territories[5].getName();
        matrix[1][3] = territories[6].getName();
        matrix[1][4] = territories[7].getName();
        matrix[1][5] = territories[8].getName();
        matrix[1][6] = territories[9].getName();
        matrix[2][3] = territories[10].getName();
        matrix[2][4] = territories[11].getName();
        matrix[2][5] = territories[12].getName();
        matrix[2][6] = territories[13].getName();
        matrix[2][7] = territories[14].getName();
        matrix[3][4] = territories[15].getName();
        matrix[3][5] = territories[16].getName();
        matrix[3][6] = territories[17].getName();
        matrix[3][7] = territories[18].getName();
        matrix[3][8] = territories[19].getName();
        matrix[4][5] = territories[20].getName();
        matrix[4][6] = territories[21].getName();
        matrix[4][7] = territories[22].getName();
        matrix[4][8] = territories[23].getName();
        matrix[4][9] = territories[24].getName();
        matrix[5][6] = territories[25].getName();
        matrix[5][7] = territories[26].getName();
        matrix[5][8] = territories[27].getName();
        matrix[5][9] = territories[28].getName();
        matrix[5][10] = territories[29].getName();
        return matrix;
    }

    private static MouseAdapter territoryClickListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel territoryPanel = (JPanel) e.getSource();
                territoryPanel.setBackground(Color.RED);
            }
        };
    }


    public static void showBoard(int numContinents, List<String> nicknameList, GameSetup gameSetup, Territory[] territories) {
        JFrame boardFrame = new JFrame("World Map");
        boardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        boardFrame.setSize(1000, 600);

        JPanel boardPanel = new JPanel(null); // Use a custom layout
        boardFrame.add(boardPanel, BorderLayout.CENTER);

        String[][] territoryMatrix = createTerritoryMatrix(territories);
        MouseAdapter territoryClickListener = territoryClickListener();
        int territoryWidth = 40;
        int territoryHeight = 40;

        for (int row = 0; row < territoryMatrix.length; row++) {
            for (int col = 0; col < territoryMatrix[row].length; col++) {
                String territoryName = territoryMatrix[row][col];

                if (territoryName != null) {
                    JPanel territoryPanel = new JPanel();
                    territoryPanel.setBackground(Color.GRAY);
                    territoryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    int territoryX = 40 + col * territoryWidth;
                    int territoryY = 50 + row * territoryHeight;

                    territoryPanel.setBounds(territoryX, territoryY, territoryWidth, territoryHeight);
                    territoryPanel.addMouseListener(territoryClickListener);
                    int armyCount = 1;
                    JLabel armyCountLabel = new JLabel(Integer.toString(armyCount));
                    territoryPanel.add(armyCountLabel, new GridBagConstraints());
                    boardPanel.add(territoryPanel);
                }
            }
        }

        JPanel userNamesPanel = new JPanel();
        userNamesPanel.setLayout(new BoxLayout(userNamesPanel, BoxLayout.X_AXIS));

        for (int i = 0; i < nicknameList.size(); i++) {
            JLabel userNameLabel = new JLabel(nicknameList.get(i));
            userNameLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            userNameLabel.setOpaque(true);
            userNameLabel.setBackground(PLAYER_COLORS[i % PLAYER_COLORS.length]);
            userNamesPanel.add(userNameLabel);
            userNamesPanel.add(Box.createHorizontalStrut(10)); // Add spacing between labels
        }

        boardFrame.add(userNamesPanel, BorderLayout.NORTH);

        JButton pauseResumeButton = new JButton("Pause");
        pauseResumeButton.addActionListener(e -> {
        if (gameSetup.getGame().getGameEnum() == GameEnum.RUNNING) {
            gameSetup.getGame().pauseGame();
            pauseResumeButton.setText("Resume");
            System.out.println("The Game is Paused");
        } else if (gameSetup.getGame().getGameEnum() == GameEnum.PAUSED) {
            gameSetup.getGame().resumeGame();
            pauseResumeButton.setText("Pause");
            System.out.println("The Game is Resumed");
        }
    });

        JButton exitButton = new JButton("Exit");
        if (exitButton.getModel().isPressed()){
            gameSetup.getGame().endGame();
        }
        exitButton.addActionListener(e -> System.exit(0));
        

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(pauseResumeButton, BorderLayout.WEST);
        bottomPanel.add(exitButton, BorderLayout.EAST);
        boardFrame.add(bottomPanel, BorderLayout.SOUTH);

        boardFrame.setVisible(true);
    }
}