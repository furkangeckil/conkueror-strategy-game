package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;

import javax.swing.SwingUtilities;

public class TerritoryChangeAnimation {
    private Graphics2D g;
    private Shape shape;
    private Color previousColor;
    private Color newColor;
    private int alpha = 0;
    private int increment = 5;
    private int delay = 50;

    public TerritoryChangeAnimation(Graphics2D g, Shape shape, Color previousColor, Color newColor) {
        this.g = g;
        this.shape = shape;
        this.previousColor = previousColor;
        this.newColor = newColor;
    }

    public void startAnimation() {
        animate();
    }

    private void animate() {
        Thread animationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (alpha < 255) {
                    alpha += increment;
                    if (alpha > 255) {
                        alpha = 255;
                    }
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            paintComponent(g);
                        }
                    });
                }
            }
        });
        animationThread.start();
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color color = new Color(previousColor.getRed(), previousColor.getGreen(), previousColor.getBlue(), 255 - alpha);
        g2d.setColor(color);
        g2d.fill(shape);
        color = new Color(newColor.getRed(), newColor.getGreen(), newColor.getBlue(), alpha);
        g2d.setColor(color);
        g2d.fill(shape);
    }
}