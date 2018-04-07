package gui;

import entity.Factory;
import entity.Reactor;
import entity.Spaceship;
import entity.Turret;
import logic.factory.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UnitsPanel extends JPanel {
    public static final int HEIGHT = 600;
    public static final int WIDTH = 150;

    private UnitCardPanel[] unitCards;
    private int selected;
    private boolean show;

    public UnitsPanel(String type) {
        setLayout(null);

        selected = -1;
        show = true;
        initUnitCards(type);

        for (int i = 0; i < unitCards.length; ++i)
                unitCards[i].setBounds(new Rectangle(0, i*UnitCardPanel.HEIGHT, UnitCardPanel.WIDTH, UnitCardPanel.HEIGHT));

        for (int i = 0; i < unitCards.length; ++i)
            add(unitCards[i]);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int mouseY = e.getY();
                int itemNo = mouseY / UnitCardPanel.HEIGHT;
                if (show && itemNo < unitCards.length) {
                    if (selected == itemNo) {
                        unitCards[itemNo].setSelected(false);
                        selected = -1;
                    }
                    else {
                        if (selected != -1) {
                            unitCards[selected].setSelected(false);
                            unitCards[selected].repaint();
                        }
                        unitCards[itemNo].setSelected(true);
                        selected = itemNo;
                    }
                    unitCards[itemNo].repaint();
                }
            }
        });

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    // TODO DESCRIPTION ====> NAMES
    private void initUnitCards(String type) {
        // Initialize UnitsPanel with attack units
        if (type == "Attack") {
            int numOfFactories = FactoryFactory.NAMES.length;
            // +1 for 'Reactor' unit type
            unitCards = new UnitCardPanel[numOfFactories + 1];

            for (int i = 0; i < numOfFactories; ++i) {
                String factoryName = FactoryFactory.NAMES[i];
                int cost = FactoryFactory.COSTS[i];
                String unitImagepath = FactoryFactory.IMAGEPATHS[i];
                String subUnitImagepath = SpaceshipFactory.IMAGEPATHS[i];
                unitCards[i] = new UnitCardPanel(factoryName, "dummynazor", cost, unitImagepath, subUnitImagepath);
            }
            String reactorName = ReactorFactory.NAME;
            int cost = ReactorFactory.COST;
            String unitImagepath = ReactorFactory.IMAGEPATH;
            unitCards[unitCards.length - 1] = new UnitCardPanel(reactorName, null, cost, unitImagepath, null);

        } else { // Initialize UnitsPanel with defense units
            int numOfTurrets = TurretFactory.NAMES.length;
            unitCards = new UnitCardPanel[numOfTurrets];

            for (int i = 0; i < unitCards.length; ++i) {
                String turretName = TurretFactory.NAMES[i];
                int cost = TurretFactory.COSTS[i];
                String unitImagepath = TurretFactory.IMAGEPATHS[i];
                String subUnitImagepath = ProjectileFactory.IMAGEPATHS[i];

                unitCards[i] = new UnitCardPanel(turretName, "dummynazor", cost, unitImagepath, subUnitImagepath);
            }
        }
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public int getSelected() {
        return selected;
    }
}
