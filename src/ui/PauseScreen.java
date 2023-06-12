package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class PauseScreen extends JDialog {

    private JButton resumeButton;
    JFrame pauseScreen = new JFrame("Pause Screen");
    private JLabel animationLabel;
    String filePath = "pause.png";

    public PauseScreen() {
        // Check if the image file exists
        if (!new File(filePath).exists()) {
            throw new IllegalArgumentException("Image file not found: " + filePath);
        }

        ImageIcon pauseIcon = new ImageIcon(filePath);

        pauseScreen.setUndecorated(true);
        pauseScreen.setExtendedState(JFrame.MAXIMIZED_BOTH);
        pauseScreen.setLocationRelativeTo(null);
        pauseScreen.setLayout(null);  // Set to absolute layout

        JLabel label = new JLabel("Game is paused.");
        label.setSize(200, 50);  // Set size
        label.setLocation(pauseScreen.getWidth() / 2 - label.getWidth() / 2, 20);  // Set location
        label.setHorizontalAlignment(JLabel.CENTER);
        pauseScreen.add(label);

        animationLabel = new JLabel(pauseIcon);
        animationLabel.setSize(pauseIcon.getIconWidth(), pauseIcon.getIconHeight());  // Set size
        animationLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pauseScreen.dispose();
            }
        });
        pauseScreen.add(animationLabel);

        resumeButton = new JButton("Resume");
        resumeButton.setSize(100, 50);  // Set size
        resumeButton.setLocation(pauseScreen.getWidth() / 2 - resumeButton.getWidth() / 2, pauseScreen.getHeight() - 100);  // Set location
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseScreen.setVisible(false);
            }
        });
        pauseScreen.add(resumeButton);

        pauseScreen.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        pauseScreen.setOpacity(0.5f);

        // Start the animation timer
        Timer animationTimer = new Timer(50, new ActionListener() {
            private int x = 0;
            private int y = 0;
            private int dx = 5;
            private int dy = 5;

            @Override
            public void actionPerformed(ActionEvent e) {
                x += dx;
                y += dy;
                if (x < 0 || x + pauseIcon.getIconWidth() > pauseScreen.getWidth()) {
                    dx = -dx;
                    x = Math.max(0, Math.min(x, pauseScreen.getWidth() - pauseIcon.getIconWidth()));
                }
                if (y < 0 || y + pauseIcon.getIconHeight() > pauseScreen.getHeight()) {
                    dy = -dy;
                    y = Math.max(0, Math.min(y, pauseScreen.getHeight() - pauseIcon.getIconHeight()));
                }
                animationLabel.setLocation(x, y);
            }
        });
        animationTimer.start();
    }

    public void showDialog() {
        pauseScreen.setVisible(true);
    }
}