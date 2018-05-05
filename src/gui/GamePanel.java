package gui;

import logic.GameManager;
import util.FileManager;

import javax.rmi.CORBA.Util;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {

    private static GamePanel instance;
    private BufferedImage backgroundImage;
    private ActionPanel actionPanel;
    private UnitsPanel attackUnitsPanel;
    private UnitsPanel defenseUnitsPanel;
    private StatsPanel statsPanel;
    private UtilityPanel utilityPanel;
    private GameManager gameManager;

    private GamePanel() {
        setLayout(null);
        backgroundImage = FileManager.getInstance().getImage("/images/gamepanel/bg.png");

       // gameManager = GameManager.getInstance();
        actionPanel = new ActionPanel();
        actionPanel.setBounds(new Rectangle(150, 50, ActionPanel.WIDTH, ActionPanel.HEIGHT));
        attackUnitsPanel = new UnitsPanel("Attack");
        attackUnitsPanel.setBounds(new Rectangle(0, 0, UnitsPanel.WIDTH, UnitsPanel.HEIGHT));
        defenseUnitsPanel = new UnitsPanel("Defense");
        defenseUnitsPanel.setBounds(new Rectangle(650, 0, 150, 600));
        statsPanel = new StatsPanel();
        statsPanel.setBounds(new Rectangle(200, 0, StatsPanel.WIDTH, StatsPanel.HEIGHT));
        utilityPanel = new UtilityPanel();
        utilityPanel.setBounds(200, 550, UtilityPanel.WIDTH, UtilityPanel.HEIGHT);


        addMouseListener(new GamePanelMouseHandler());
        add(attackUnitsPanel);
        add(defenseUnitsPanel);
        add(actionPanel);
        add(utilityPanel);
        add(statsPanel);
        actionPanel.setStatsPanel(statsPanel);
        setPreferredSize(new Dimension(800, 600));
    }

    public void startTimer() {
        actionPanel.startTimer();
    }


    public static GamePanel getInstance() {
        if (instance == null)
            instance = new GamePanel();
        return instance;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage, 150, 0, null);
    }


    // TODO will be implemented after iteration I
    private class GamePanelMouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
             int mouseX = e.getX() - attackUnitsPanel.WIDTH;
             int mouseY = e.getY() - statsPanel.HEIGHT;
             Point clickedPoint = new Point(mouseX, mouseY);

             if (GameManager.getInstance().getCurrentTurn().equals("Attacker"))
                GameManager.getInstance().buyItem(clickedPoint, attackUnitsPanel.getSelected());
            else
                GameManager.getInstance().buyItem(clickedPoint, defenseUnitsPanel.getSelected());

        }
    }

}
