package gui;

import util.FileManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.NotSerializableException;
import java.io.Serializable;

public class UtilityPanel extends JPanel {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 50;
    private FileManager fileManager;
    private MenuButton playButton;
    private MenuButton pauseButton;
    private MenuButton saveButton;

    public UtilityPanel() {
        setLayout(null);
        setOpaque(false);
        fileManager = FileManager.getInstance();
        playButton = new MenuButton(150, 10, fileManager.getImage("/images/gamepanel/continue.png"),
                fileManager.getImage("/images/gamepanel/continuehover.png"));
        pauseButton = new MenuButton(200, 10, fileManager.getImage("/images/gamepanel/pause.png"),
                fileManager.getImage("/images/gamepanel/pausehover.png/"));
        saveButton = new MenuButton( 250, 10 , fileManager.getImage("/images/gamepanel/save.png"),
                fileManager.getImage("/images/gamepanel/savehover.png"));


        //pause action
        pauseButton.addActionListener(e -> {
            ActionPanel.pauseTimer();
        });

        //save button action
        saveButton.addActionListener(e -> {
            fileManager.saveGame();
        });

        //speed button action
        playButton.addActionListener(e -> {
            ActionPanel.startTimer();


        });

        add(playButton);
        add(pauseButton);
        add(saveButton);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
}
