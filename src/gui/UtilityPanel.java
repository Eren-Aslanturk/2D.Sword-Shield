package gui;

import util.FileManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class UtilityPanel extends JPanel {
    private FileManager fileManager;
    private MenuButton continueButton;
    private MenuButton pauseButton;
    private MenuButton saveButton;

    public UtilityPanel() {
        setLayout(null);
        setOpaque(false);
        fileManager = FileManager.getInstance();
        continueButton = new MenuButton(200, 10, fileManager.getImage("/images/gamepanel/continue.png"),
                fileManager.getImage("/images/gamepanel/continuehover.png"));
        pauseButton = new MenuButton(250, 10, fileManager.getImage("/images/gamepanel/pause.png"),
                fileManager.getImage("/images/gamepanel/pausehover.png/"));
        saveButton = new MenuButton( 300, 10 , fileManager.getImage("/images/gamepanel/save.png"),
                fileManager.getImage("/images/gamepanel/savehover.png"));

        add(continueButton);
        add(pauseButton);
        add(saveButton);
    }
}
