package gui;

import util.FileManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LoadPanel extends JPanel {

    private JList list;
    private JButton selectButton;
    private JButton removeButton;
    private JLabel loadText;
    private DefaultListModel model;
    private JScrollPane pane;
    private ArrayList<String> savedGames;
    private MenuButton backButton;
    private BufferedImage backgroundImage;



    public LoadPanel() {
        setLayout(new BorderLayout());
        loadText = new JLabel("Load");
        model = new DefaultListModel();
        list = new JList(model);
        pane = new JScrollPane(list);
        savedGames = FileManager.getSavedGames();

        for (String savedGame : savedGames)
            model.addElement(savedGame);

        selectButton = new JButton("Select");
        selectButton.addActionListener( select -> {
            // TODO will be implemented after iteration I
            GUIManager.getInstance().showGamePanel();
        });

        removeButton = new JButton( "Remove");
        removeButton.addActionListener( remove -> {

        });

        add(pane, BorderLayout.NORTH);
       // add(pane, BorderLayout.CENTER);
        add(selectButton, BorderLayout.EAST);
        add(removeButton, BorderLayout.WEST);

        backgroundImage = FileManager.getInstance().getImage("/images/menu/menu_bg.gif");
        backButton = new MenuButton(370,500, FileManager.getInstance().getImage("/images/credits/back.png"),
                FileManager.getInstance().getImage("/images/credits/backhover.png"));
        backButton.addActionListener(back -> {
            GUIManager.getInstance().showMenuPanel();
        });
        add(backButton, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(800, 600));
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage,0,0, null);
    }

}
