package gui;

import util.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuPanel extends JPanel {

    private static MenuPanel instance;
    private BufferedImage backgroundImage;
    private BufferedImage gameLogo;
    private FileManager fileManager;
    private MenuButton playButton;
    private MenuButton loadButton;
    private MenuButton helpButton;
    private MenuButton creditsButton;
    private MenuButton soundButton;

    public MenuPanel() {
        fileManager = FileManager.getInstance();
        backgroundImage = fileManager.getImage("/images/menu/menu_bg.gif");
        gameLogo = fileManager.getImage("/images/menu/gamelogo.png");
        setLayout(null);

        playButton = new MenuButton(360,270,fileManager.getImage("/images/menu/play.png"),
                         fileManager.getImage("/images/menu/playhover.png"));
        playButton.addActionListener(play -> {
            GUIManager.getInstance().showGamePanel();
        });


        loadButton = new MenuButton(360,330,fileManager.getImage("/images/menu/load.png"),
                fileManager.getImage("/images/menu/loadhover.png"));
        loadButton.addActionListener(load -> {
            GUIManager.getInstance().showLoadPanel();
        });

        creditsButton = new MenuButton(348, 390,fileManager.getImage("/images/menu/credits.png"),
                fileManager.getImage("/images/menu/creditshover.png"));
        creditsButton.addActionListener(credits -> {
            GUIManager.getInstance().showCreditsPanel();
        });

        helpButton = new MenuButton(355, 450,fileManager.getImage("/images/menu/help.png"),
                fileManager.getImage("/images/menu/helphover.png"));
        helpButton.addActionListener(help -> {
            GUIManager.getInstance().showHelpPanel();
        });


        soundButton = new MenuButton(355, 510,fileManager.getImage("/images/menu/music.png"),
                fileManager.getImage("/images/menu/musichover.png"));
        soundButton.addActionListener(sound-> {
            // TODO
        });

        add(playButton);
        add(loadButton);
        add(creditsButton);
        add(helpButton);
        add(soundButton);

        setPreferredSize(new Dimension(800, 600));
    }

    public static MenuPanel getInstance() {
        if (instance == null)
            instance = new MenuPanel();
        return instance;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
        g.drawImage(gameLogo, 100, 100, null);
    }
}
