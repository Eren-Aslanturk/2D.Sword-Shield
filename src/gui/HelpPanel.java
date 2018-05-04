package gui;

import entity.*;
//import jdk.tools.jlink.internal.Jlink;
import logic.factory.FactoryFactory;
import logic.factory.ReactorFactory;
import logic.factory.SpaceshipFactory;
import logic.factory.TurretFactory;
import logic.manager.TurretManager;
import org.omg.CORBA.CODESET_INCOMPATIBLE;
import util.FileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Random;

public class HelpPanel extends JPanel{
    private static int WIDTH = 840;
    private static int HEIGHT = 550;
    private final int MAX_SIZE = 13;

    private boolean bordersActive = false;

    private ArrayList<JPanel> itemImageStatTextList = new ArrayList<JPanel>(MAX_SIZE);
    private ArrayList<JLabel> imagesList = new ArrayList<JLabel>(MAX_SIZE);
    private ArrayList<JLabel> textsList = new ArrayList<JLabel>(MAX_SIZE);
    private ArrayList<JLabel> statsList = new ArrayList<JLabel>(MAX_SIZE);
    private ArrayList<JPanel> itemList = new ArrayList<JPanel>(MAX_SIZE);

    private ArrayList<JTabbedPane> tabbedPane = new ArrayList<JTabbedPane>(3);

    private ArrayList<TurretFactory> turretsList = new ArrayList<TurretFactory>(4);
    private ArrayList<FactoryFactory> factoriesList = new ArrayList<FactoryFactory>(4);
    private ArrayList<SpaceshipFactory> spaceshipsList = new ArrayList<SpaceshipFactory>(4);

    private BufferedImage backgroundImage;

    public HelpPanel() {

        add(options());

        tabSettings();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));



    }

    //paint function
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

    //image calling and transforming functions
    public BufferedImage getImage(String imagepath) {
        BufferedImage image;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagepath));
            return image;
        } catch (IOException exc) {
            System.err.println("getImage.IOException: " + imagepath);
            return null;
        } catch (IllegalArgumentException exc) {
            System.err.println("getImage.IllegalArgumentException: " + imagepath);
            return null;
        }
    }

    public BufferedImage getResizedImage(String imagepath, int width, int height) {
        BufferedImage image = getImage(imagepath);

        Image tempImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = resultImage.createGraphics();
        g2d.drawImage(tempImage, 0, 0, null);
        g2d.dispose();
        return resultImage;
    }

    private BufferedImage turretImage = getResizedImage("/images/help/TurretOption.jpg",WIDTH/3,HEIGHT);
    private BufferedImage factoryImage = getResizedImage("/images/help/FactoryOption.jpg",WIDTH/3,HEIGHT);
    private BufferedImage spaceshipImage = getResizedImage("/images/help/SpaceshipOption.jpg",WIDTH/3,HEIGHT);

    public JPanel options(){
        JPanel optionPanel = new JPanel();
        setBackground(Color.black);
        optionPanel.setBackground(null);
        optionPanel.setPreferredSize(new Dimension(WIDTH,HEIGHT));

        JButton turretLabel = new JButton();
        JButton factoryLabel = new JButton();
        JButton spaceshipLabel = new JButton();

        turretLabel.setPreferredSize(new Dimension(270, HEIGHT));
        factoryLabel.setPreferredSize(new Dimension(270, HEIGHT));
        spaceshipLabel.setPreferredSize(new Dimension(270, HEIGHT));

        turretLabel.setIcon(new ImageIcon(turretImage));
        factoryLabel.setIcon(new ImageIcon(factoryImage));
        spaceshipLabel.setIcon(new ImageIcon(spaceshipImage));

        optionListener(turretLabel,factoryLabel,spaceshipLabel);

        optionPanel.add(turretLabel);
        optionPanel.add(factoryLabel);
        optionPanel.add(spaceshipLabel);

        return optionPanel;


    }

    public void tabSettings(){
        //panels and labels

        for(int i = 0; i < 3; i++){
            JTabbedPane tab = new JTabbedPane();
            tab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

            tab.addMouseWheelListener(new MouseWheelListener() {
                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    JTabbedPane pane = (JTabbedPane) e.getSource();
                    int units = e.getWheelRotation();
                    int oldIndex = pane.getSelectedIndex();
                    int newIndex = oldIndex + units;

                    if (pane.getToolTipTextAt(0).equals("Factory")){
                        if (newIndex < 0)
                            pane.setSelectedIndex(4);
                        else if (newIndex >= pane.getTabCount()) {
                            pane.setSelectedIndex(pane.getTabCount() % 5);
                        }
                        else
                            pane.setSelectedIndex(newIndex);


                    }

                    else {
                        if (newIndex < 0)
                            pane.setSelectedIndex(3);
                        else if (newIndex >= pane.getTabCount()) {
                            pane.setSelectedIndex(pane.getTabCount() % 4);
                        }
                        else
                            pane.setSelectedIndex(newIndex);
                    }
                }
            });

            tabbedPane.add(i, tab);

        }

        for(int i = 0; i < MAX_SIZE; i++){
            JPanel itemPanel = new JPanel();
            itemList.add(i, itemPanel);
        }

        for(int i = 0; i < MAX_SIZE ; i++){
            JLabel imageLabel = new JLabel();
            imagesList.add(i, imageLabel);
        }

        for(int i = 0; i < MAX_SIZE ; i++){
            JLabel statsLabel = new JLabel();
            statsList.add(i, statsLabel);
        }

        for(int i = 0; i < MAX_SIZE ; i++){
            JLabel textLabel = new JLabel();
            textsList.add(i, textLabel);


        }

        //outer label for image panel and stats panel
        for(int i = 0; i < MAX_SIZE ; i++){
            JPanel itemImageStatLabel = new JPanel();
            itemImageStatTextList.add(i, itemImageStatLabel);
        }

        //dimensions
        for(JPanel items: itemList){
            items.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        }

        for(JLabel images: imagesList){
            images.setPreferredSize(new Dimension(300, 300));
        }

        for(JLabel stats: statsList){
            stats.setPreferredSize(new Dimension(300, 150));
        }

        for(JLabel texts: textsList){
            texts.setPreferredSize(new Dimension(480, HEIGHT-250));
        }

        for(JPanel itemImageStatText: itemImageStatTextList){
            itemImageStatText.setPreferredSize(new Dimension(300, HEIGHT-50));
        }

        //background colors / images
        //TODO set background images
        for(JLabel images: imagesList){
            images.setBackground(null);
        }

        for(JLabel stats: statsList){
            stats.setBackground(null);
        }

        for(JLabel texts: textsList){
            texts.setBackground(null);
        }

        for(JPanel itemImageStatText: itemImageStatTextList){
            itemImageStatText.setBackground(null);
        }

        for(int i=0 ; i< itemList.size(); i++) {
            Random rand = new Random();

            itemList.get(i).setBackground(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));

        }

        //borderlines
        if(bordersActive) {
            for (JLabel images : imagesList) {
                images.setBorder(BorderFactory.createLineBorder(Color.black));
            }

            for (JLabel stats : statsList) {
                stats.setBorder(BorderFactory.createLineBorder(Color.green));
            }

            for (JLabel texts : textsList) {
                texts.setBorder(BorderFactory.createLineBorder(Color.blue));
            }
        }

        //TODO label set texts for stats panel and text panel
        for(JLabel images: imagesList){
            images.setIcon(new ImageIcon("/images/spaceship/spaceship1.png"));
        }

        for(JLabel texts: textsList){
            texts.setText("information and how to use");
        }

        for(JLabel stats: statsList){
            stats.setText( "<html><pre>" +
                    "<br/>\tAttack:" +      // getAttack() +
                    "<br/>\tDefense:" +     // getDefense() +
                    "<br/>\tSpeed:" +       // getSpeed() +
                    "</pre></html>");
        }

        //merge image and stats panels
        for(int i=0 ; i < itemImageStatTextList.size() ; i++ ){
            itemImageStatTextList.get(i).add(imagesList.get(i));
            itemImageStatTextList.get(i).add(statsList.get(i));
        }

        //add panels to related tab
        //TODO add backpage button
        for(int i=0 ; i < itemList.size() ; i++ ){
            itemList.get(i).add(itemImageStatTextList.get(i));
            itemList.get(i).add(textsList.get(i));
        }

        tabbedPane.get(0).addTab("Turret1", null, itemList.get(0),"Turret");
        tabbedPane.get(0).addTab("Turret2", null, itemList.get(1),"Turret");
        tabbedPane.get(0).addTab("Turret3", null, itemList.get(2),"Turret");
        tabbedPane.get(0).addTab("Turret4", null, itemList.get(3),"Turret");

        setAttributes(1);

        tabbedPane.get(1).addTab("Factory1", null, itemList.get(4),"Factory");
        tabbedPane.get(1).addTab("Factory2", null, itemList.get(5),"Factory");
        tabbedPane.get(1).addTab("Factory3", null, itemList.get(6),"Factory");
        tabbedPane.get(1).addTab("Factory4", null, itemList.get(7),"Factory");
        tabbedPane.get(1).addTab("Reactor", null, itemList.get(8),"Factory");

        setAttributes(2);

        tabbedPane.get(2).addTab("Spaceship1", null, itemList.get(9),"Spaceship");
        tabbedPane.get(2).addTab("Spaceship2", null, itemList.get(10),"Spaceship");
        tabbedPane.get(2).addTab("Spaceship3", null, itemList.get(11),"Spaceship");
        tabbedPane.get(2).addTab("Spaceship4", null, itemList.get(12),"Spaceship");

        setAttributes(3);

        //tab features, add inner tabs
        tabbedPane.get(0).setTabPlacement(JTabbedPane.BOTTOM);
        tabbedPane.get(0).setPreferredSize(new Dimension(WIDTH,HEIGHT-10));

        tabbedPane.get(1).setTabPlacement(JTabbedPane.BOTTOM);
        tabbedPane.get(1).setPreferredSize(new Dimension(WIDTH,HEIGHT-10));

        tabbedPane.get(2).setTabPlacement(JTabbedPane.BOTTOM);
        tabbedPane.get(2).setPreferredSize(new Dimension(WIDTH,HEIGHT-10));

    }

    public void optionListener(JButton turret, JButton factory, JButton spaceship){

        if(turret!=null) {
            turret.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {
                    SwingUtilities.getWindowAncestor(turret).setVisible(false); //hide options window
                    JFrame turretframe = new JFrame();

                    turretframe.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

                    turretframe.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            SwingUtilities.getWindowAncestor(turret).setVisible(true); //hide options window

                            turretframe.setVisible(false);
                            turretframe.dispose();
                        }
                    });

                    turretframe.setBounds(300,150,WIDTH,HEIGHT);
                    turretframe.setVisible(true);



                    turretframe.add(tabbedPane.get(0));

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    turret.setIcon(new ImageIcon(getResizedImage("/images/help/TurretOptionHover.jpg",WIDTH/3,HEIGHT)));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    turret.setIcon(new ImageIcon(getResizedImage("/images/help/TurretOption.jpg",WIDTH/3,HEIGHT)));
                }
            });
        }

        if(factory!=null) {
            factory.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {

                    SwingUtilities.getWindowAncestor(factory).setVisible(false); //hide options window
                    JFrame factoryframe = new JFrame();
                    factoryframe.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

                    factoryframe.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            SwingUtilities.getWindowAncestor(factory).setVisible(true); //hide options window

                            factoryframe.setVisible(false);
                            factoryframe.dispose();
                        }
                    });

                    factoryframe.setBounds(300,150,WIDTH,HEIGHT);
                    factoryframe.setVisible(true);

                    factoryframe.add(tabbedPane.get(1));

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    factory.setIcon(new ImageIcon(getResizedImage("/images/help/FactoryOptionHover.jpg",WIDTH/3,HEIGHT)));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    factory.setIcon(new ImageIcon(getResizedImage("/images/help/FactoryOption.jpg",WIDTH/3,HEIGHT)));
                }
            });
        }

        if(spaceship!=null) {
            spaceship.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {

                    SwingUtilities.getWindowAncestor(spaceship).setVisible(false); //hide options window
                    JFrame spaceshipframe = new JFrame();
                    spaceshipframe.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

                    spaceshipframe.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            SwingUtilities.getWindowAncestor(spaceship).setVisible(true); //hide options window

                            spaceshipframe.setVisible(false);
                            spaceshipframe.dispose();
                        }
                    });

                    spaceshipframe.setBounds(300,150,WIDTH,HEIGHT);
                    spaceshipframe.setVisible(true);

                    spaceshipframe.add(tabbedPane.get(2));

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    spaceship.setIcon(new ImageIcon(getResizedImage("/images/help/SpaceshipOptionHover.jpg",WIDTH/3,HEIGHT)));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    spaceship.setIcon(new ImageIcon(getResizedImage("/images/help/SpaceshipOption.jpg",WIDTH/3,HEIGHT)));
                }
            });
        }


    }

    private void setAttributes(int type){ // TYPE: turret(1), factory(2),spaceship(3); itemNo (1 to 5)
        if(type == 1){
            for (int i = 0; i < 4; i++) {
                statsList.get(i).setText(
                        "<html><pre>" +
                                "<br/><header><h2>Stats</h2></header>" +
                                "<br/>\tName:" + TurretFactory.NAMES[i] +
                                "<br/>\tCost:" + TurretFactory.COSTS[i] +
                                "<br/>\tSpeed:" + TurretFactory.getSpeed(i) +
                                "</pre></html>");

                imagesList.get(i).setIcon(new ImageIcon(
                        getResizedImage(TurretFactory.IMAGEPATHS[i],
                                300,300)));

                textsList.get(i).setText(TurretFactory.DESCRIPTIONS[i]);


            }
        }

        else if(type == 2){
            for (int i = 0; i < 5; i++) {
                if(i!=4) {
                    statsList.get(i+4).setText("<html><pre>" +
                            "<br/><header><h2>Stats</h2></header>" +
                            "<br/>\tName:" + FactoryFactory.NAMES[i] +
                            "<br/>\tCost:" + FactoryFactory.COSTS[i] +
                            "<br/>\tProduction rate:" + FactoryFactory.getSpeed(i) +
                            "</pre></html>");

                    imagesList.get(i+4).setIcon(new ImageIcon(
                            getResizedImage(FactoryFactory.IMAGEPATHS[i],
                                    300,300)));

                    textsList.get(i+4).setText(FactoryFactory.DESCRIPTIONS[i]);
                }

                else {

                    statsList.get(i+4).setText("<html><pre>" +
                            "<br/><header><h2>Stats</h2></header>" +
                            "<br/>\tName:" + ReactorFactory.NAME +
                            "<br/>\tCost:" + ReactorFactory.COST +
                            "<br/>\tIncome rate:" + ReactorFactory.getIncomeRate() + " gold/s" +
                            "</pre></html>");

                    imagesList.get(i+4).setIcon(new ImageIcon(
                            getResizedImage(ReactorFactory.IMAGEPATH,
                                    300,300)));

                    textsList.get(i+4).setText(ReactorFactory.DESCRIPTIONS);
                }



            }
        }

        else if(type == 3){
            for (int i = 0; i < 4; i++) {

                statsList.get(i+9).setText(
                        "<html><pre>" +
                                "<br/><header><h2>Stats</h2></header>" +
                                "<br/>\tSpeed:" + SpaceshipFactory.speed(i) +
                                "<br/>\tArmor:" + SpaceshipFactory.armor(i) +
                                "<br/>\tHP:" + SpaceshipFactory.hp(i) +
                                "<br/>\tReward:" + SpaceshipFactory.reward(i) +
                                "</pre></html>");

                imagesList.get(i+9).setIcon(new ImageIcon(
                        getResizedImage(SpaceshipFactory.IMAGEPATHS[i],
                                300,300)));

                textsList.get(i+9).setText(SpaceshipFactory.DESCRIPTIONS[i]);

            }
        }
    }


}
