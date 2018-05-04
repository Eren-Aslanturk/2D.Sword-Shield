package gui;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;


public class GUIManager {
    public static int WIDTH = 800;
    public static int HEIGHT = 600;

    private static GUIManager instance;

    private JFrame frame;
    private MenuPanel menuPanel;
    private LoadPanel loadPanel;
    private CreditsPanel creditsPanel;
    private GamePanel gamePanel;
    private HelpPanel helpPanel;
    // TODO will be implemented after iteration I.
    private int loadGameIndex;

    private GUIManager() {
        menuPanel = new MenuPanel();
        loadPanel = new LoadPanel();
        creditsPanel = new CreditsPanel();
        helpPanel = new HelpPanel();

        // not sure if GamePanel should be singleton
        gamePanel = GamePanel.getInstance();

        frame = new JFrame("Sword&Shield: A Space Adventure");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        frame.setBounds(
                ((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - WIDTH/2),
                ((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - HEIGHT/2),
                WIDTH, HEIGHT);

        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static GUIManager getInstance() {
        if (instance == null)
            instance = new GUIManager();
        return instance;
    }

    public static GUIManager setInstance(GUIManager newInstance) {
        if (instance != null) {
            instance = newInstance;
        }
        return instance;
    }

    public void showMenuPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(menuPanel);
        frame.pack();
        frame.repaint();
    }

    public void showLoadPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(loadPanel);
        frame.pack();
        frame.repaint();
    }

    public void showCreditsPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(creditsPanel);
        frame.pack();
        frame.repaint();
    }

    public void showHelpPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(helpPanel);
        frame.pack();
        frame.repaint();
    }

    public void showGamePanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(gamePanel);
        gamePanel.startTimer();
        frame.pack();
        frame.repaint();
    }

}
