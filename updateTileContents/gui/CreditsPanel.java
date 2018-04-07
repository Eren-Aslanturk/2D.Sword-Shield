package gui;

import util.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CreditsPanel extends JPanel {
    private static int WIDTH = 800;
    private static int HEIGHT = 600;

    private BufferedImage backgroundImage;
    private BufferedImage name1, name2, name3, name4;
    private BufferedImage creditsTitle;
    private MenuButton backButton;

    public CreditsPanel() {
        backgroundImage = FileManager.getInstance().getImage("/images/menu/menu_bg.gif");
        backButton = new MenuButton(370,500, FileManager.getInstance().getImage("/images/credits/back.png"),
                FileManager.getInstance().getImage("/images/credits/backhover.png"));

        name1 = FileManager.getInstance().getImage("/images/credits/name1.png");
        name2 = FileManager.getInstance().getImage("/images/credits/name2.png");
        name3 = FileManager.getInstance().getImage("/images/credits/name3.png");
        name4 = FileManager.getInstance().getImage("/images/credits/name4.png");

        creditsTitle = FileManager.getInstance().getImage("/images/credits/title.png");
        backButton.addActionListener(back -> {
            GUIManager.getInstance().showMenuPanel();
        });


        add(backButton, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(800, 600));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage,0,0, null);
        g.drawImage(creditsTitle, 300, 100, null);
        g.drawImage(name1, 300, 200, null);
        g.drawImage(name2, 300, 250, null);
        g.drawImage(name3, 300, 300, null);
        g.drawImage(name4, 300, 350, null);
    }

}
