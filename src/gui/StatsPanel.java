package gui;

import util.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import util.User;

public class StatsPanel extends JPanel {
    public static int WIDTH = 400;
    public static int HEIGHT = 50;

    private BufferedImage sides;
    private JLabel attackerScore;
    private JLabel defenderScore;
    private JLabel attackerGold;
    private JLabel defenderGold;
    private JLabel timeLeft;

    private User attacker,defender;

    public StatsPanel() {
        setLayout(null);
        setOpaque(false);
        sides = FileManager.getInstance().getImage("/images/gamepanel/statspanel.png");
        sides = FileManager.getInstance().getResizedImage(sides, WIDTH, HEIGHT);

        attackerScore = new JLabel("" + 0);
        attackerScore.setForeground(Color.CYAN);
        attackerScore.setBounds(new Rectangle(30, 7, 40, 40));
        attackerScore.setFont(new Font("Helvetica", Font.PLAIN, 19));

        attackerGold = new JLabel("" + 800);
        attackerGold.setForeground(Color.CYAN);
        attackerGold.setBounds(new Rectangle(80, 7, 40, 40));
        attackerGold.setFont(new Font("Helvetica", Font.PLAIN, 19));


        timeLeft = new JLabel("00:00" );
        timeLeft.setForeground(Color.CYAN);
        timeLeft.setBounds(new Rectangle(175, 7, 60, 40));
        timeLeft.setFont(new Font("Helvetica", Font.PLAIN, 19));


        defenderGold = new JLabel("" + 800);
        defenderGold.setForeground(Color.CYAN);
        defenderGold.setBounds(new Rectangle(260, 7, 40, 40));
        defenderGold.setFont(new Font("Helvetica", Font.PLAIN, 19));

        defenderScore = new JLabel("" + 0);
        defenderScore.setForeground(Color.CYAN);
        defenderScore.setBounds(new Rectangle(345, 7, 40, 40));
        defenderScore.setFont(new Font("Helvetica", Font.PLAIN, 19));


        add(attackerScore);
        add(defenderScore);
        add(attackerGold);
        add(defenderGold);
        add(timeLeft);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    protected void paintComponent(Graphics g) {
        g.drawImage(sides, 0, 0, null);
    }
    public void setAttackerGold(int attackerGold) {
        this.attackerGold.setText("" + attackerGold);
    }

    //This works without a problem but whenever I call it from somewhere it gives some errors
    //Tried it with GameManager and Spaceship Manager
    public void setDefenderGold() {
        this.defenderGold.setText("" + String.valueOf(defender.getGold()));
    }

    public void setAttackerScore(int attackerScore) {
        this.attackerScore.setText("" + attackerScore);
    }

    public void setDefenderScore(int defenderScore) {
        this.defenderScore.setText("" + defenderScore);
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft.setText("" + timeLeft);
    }
    public void setUsers(User attacker, User defender){
        this.attacker = attacker;
        this.defender = defender;
    }


    //Point was updating through here but they can be discarded for now as I am looking for another way
    public void updateGold(){
        attackerGold.setText(String.valueOf(attacker.getGold()));
        defenderGold.setText(String.valueOf(defender.getGold()));
    }

    public void updateScore(){
        attackerScore.setText(String.valueOf(attacker.getScore()));
        defenderScore.setText(String.valueOf(defender.getScore()));
    }

}
