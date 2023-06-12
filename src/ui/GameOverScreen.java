package ui;

import domain.controllers.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameOverScreen {

    private class Confetti {
        int x;
        int y;
        double speed;
        ImageIcon confettiIcon;

        public Confetti(int x, int y, double speed, ImageIcon confettiIcon) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.confettiIcon = confettiIcon;
        }
    }

    private final List<Confetti> confettiList = new ArrayList<>();
    private final Random random = new Random();

    public void showScreen() {

        ImageIcon[] confettiIcons = new ImageIcon[]{
            new ImageIcon("confetti1.png"),
            new ImageIcon("confetti3.png"),
            new ImageIcon("confetti1.png")
        };

        // Check if the image file exists
        for(ImageIcon icon : confettiIcons) {
            if (!new File(icon.getDescription()).exists()) {
                throw new IllegalArgumentException("Image file not found: " + icon.getDescription());
            }
        }

        JFrame frame = new JFrame("Game Over");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        

        JLabel label = new JLabel("Player " + Controller.getInstance().getCurrentPlayer().getName() + " has won the game!");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setForeground(Color.RED);
        label.setHorizontalAlignment(JLabel.CENTER);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        panel.add(exitButton, BorderLayout.SOUTH);
        frame.add(panel, BorderLayout.CENTER);

        // Start the animation timer
        Timer animationTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add a new confetti every second
                if (random.nextInt(20) == 0) {
                    ImageIcon randomIcon = confettiIcons[random.nextInt(confettiIcons.length)];
                    confettiList.add(new Confetti(
                        random.nextInt(frame.getWidth() - randomIcon.getIconWidth()),
                        frame.getHeight(),
                        1 + random.nextDouble() * 2, // Speed
                        randomIcon
                    ));
                }

                // Move all confetti upwards
                for (Confetti c : confettiList) {
                    c.y -= c.speed;
                    c.speed *= 1.01; // Increase speed
                }

                // Remove confetti that have moved off screen
                confettiList.removeIf(c -> c.y + c.confettiIcon.getIconHeight() < 0);

                // Redraw frame
                frame.repaint();
            }
        });

        animationTimer.start();

        // Set glass pane to draw confetti
        JComponent glassPane = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Confetti c : confettiList) {
                    g.drawImage(c.confettiIcon.getImage(), c.x, c.y, null);
                }
            }
        };
        glassPane.setOpaque(false);
        frame.setGlassPane(glassPane);
        glassPane.setVisible(true);

        frame.setVisible(true);
    }
}