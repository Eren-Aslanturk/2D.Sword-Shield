package gui;

/* Author: Mehmet Enes Keles
 * Date: 12.03.2018
 *
 * ActionPanel is the UI component that carries the GameManager. Main flow of events are shown through ActionPanel.
 * These events include movement of the game objects, change in score and gold, change in remaining time.
 */

import logic.GameManager;
import util.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ActionPanel extends JPanel {

    // TODO a custom cursor for the game might be added after iteration I
    // private static final int MOUSE_WIDTH = 40;
    // private static final int MOUSE_HEIGHT = 40;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    private static final int DELAY = 15;
    private int mouseX, mouseY;
    private GameManager gameManager;
    private Timer timer;

    public ActionPanel() {
        setLayout(null);
        setOpaque(false);

        gameManager = GameManager.getInstance();

        // set timer
        timer = new Timer(DELAY, iterateGameState -> {
            gameManager.update();
            repaint();
        });

        setPreferredSize(new Dimension(500, 600));
        // addMouseListener(new ActionPanelMouseListener());
    }

    public void startTimer() {
        timer.start();
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameManager.render(g);
    }
}
