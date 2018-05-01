package gui;

import util.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class HelpPanel extends JPanel {

    private static HelpPanel instance;
    private BufferedImage backgroundImage;
    private MenuButton backButton;
    private HelpPanel() {
        setLayout(new BorderLayout());
        backgroundImage = FileManager.getInstance().getImage("/images/menu/menu_bg.gif");

        backButton = new MenuButton(370, 500, FileManager.getInstance().getImage("/images/credits/back.png"),
                FileManager.getInstance().getImage("/images/credits/backhover.png"));
        backButton.addActionListener(back -> {
            GUIManager.getInstance().showMenuPanel();
        });

        setLayout(new BorderLayout());
        add(backButton, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(800, 600));
    }

    public static HelpPanel getInstance() {
        if (instance == null)
            instance = new HelpPanel();
        return instance;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage,0,0, null);

    }
}
