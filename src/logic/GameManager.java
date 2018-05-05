package logic;

import entity.Factory;
import entity.Spaceship;
import entity.Tile;
import entity.Turret;
import gui.StatsPanel;
import logic.factory.FactoryFactory;
import logic.factory.ReactorFactory;
import logic.factory.TurretFactory;
import logic.manager.*;
import util.User;

import java.awt.*;
import java.util.ArrayList;
import java.io.Serializable;
import gui.UnitCardPanel;

import javax.swing.*;

public class GameManager extends UnitCardPanel{
    private static GameManager instance;

    private static final int MAX_TURNS = 3;
    private User attacker, defender;
    private boolean currentTurn, gameOver;
    private int turnCount;
    private int timeLeft;

    private FactoryManager factoryManager;
    private SpaceshipManager spaceshipManager;
    private TurretManager turretManager;
    private ProjectileManager projectileManager;
    private ReactorManager reactorManager;

    private StatsPanel statsPanel;

    private ArrayList<Spaceship> spaceships;
    private TileManager tileManager;

    private GameManager() {
        attacker = new User();
        defender = new User();
        currentTurn = false;
        gameOver = false;
        timeLeft = 0;
        turnCount = 0;

        initGameObjects();
    }

    public static GameManager getInstance() {
        if (instance == null)
            instance = new GameManager();
        return instance;
    }

    private void initGameObjects() {
        attacker = new User();
        defender = new User();

        factoryManager = new FactoryManager();
        spaceshipManager = new SpaceshipManager();
        turretManager = new TurretManager();
        projectileManager = new ProjectileManager();
        reactorManager = new ReactorManager();
        tileManager = new TileManager();
        statsPanel = new StatsPanel();

        factoryManager.setSpaceshipFactory(spaceshipManager.getSpaceshipFactory());
        turretManager.setProjectileFactory(projectileManager.getProjectileFactory());
        spaceshipManager.setUsers(attacker,defender);
    }

    /*
    * TODO will be implemented after iteration I
    * right now all tiles on the left side belong to the attacker
    * whereas the tiles on the right side belong to the defender
    */
    public Tile buyTile(Point point) {
        //tile to buy
        Tile boughtTile = null;

        //if attacker's turn
        if(turnCount%2==1) {

            //get the bought tile and update gold
            boughtTile = tileManager.clickedTile((int)point.getX(),(int)point.getY());

            //check if gold is enough
            if(attacker.getGold()<boughtTile.getCost()) {
                System.out.println("insufficient funds");
                return null;
            }

            //update gold
            attacker.setGold(attacker.getGold()-boughtTile.getCost());

        }

        //if defender's turn
        else if(turnCount%2==0) {

            boughtTile = tileManager.clickedTile((int)point.getX(),(int)point.getY());

            //check if gold is enough
            if(defender.getGold()<boughtTile.getCost()) {
                System.out.println("insufficient funds");
                return null;
            }

            //update gold
            defender.setGold(defender.getGold()-boughtTile.getCost());

        }

        //return the bought tile
        return boughtTile;
    }

    public void buyItem(Point point, int type) {
        if (type < 0) return ;

        System.out.println(point.x + " " + point.y + " " +  type);
        if (getCurrentTurn() == "Attacker") {
            int costOfItem;
            if (type != 4)
                costOfItem = FactoryFactory.COSTS[type];
            else
                costOfItem = ReactorFactory.COST;

            Tile clickedTile = tileManager.clickedTile((int)point.getX(), (int)point.getY());
            if (clickedTile != null && tileManager.getTileType(clickedTile).equals("Attacker")) {
                if (getAttackerGold() >= costOfItem) {
                    int itemX = clickedTile.getX();
                    int itemY = clickedTile.getY();
                    tileManager.removeTile(itemX, itemY);
                    attacker.setGold(attacker.getGold() - costOfItem);
                    if (type != 4)
                        factoryManager.add(type, itemX, itemY);
                    else
                        reactorManager.add(0, itemX, itemY);
                }
            }
        }

        if (getCurrentTurn() == "Defender") {
            int costOfItem = TurretFactory.COSTS[type];
            Tile clickedTile = tileManager.clickedTile((int)point.getX(), (int)point.getY());

            if (clickedTile != null && tileManager.getTileType(clickedTile).equals("Defender")) {
                if (getDefenderGold() >= costOfItem) {
                    int itemX = clickedTile.getX();
                    int itemY = clickedTile.getY();
                    tileManager.removeTile(itemX, itemY);
                    defender.setGold(defender.getGold() - costOfItem);
                    turretManager.add(type, itemX, itemY);
                }
            }
        }
    }

    public void setStatsPanel(StatsPanel panel){
        this.statsPanel = panel;
    }
    public void update() {
        statsPanel.setDefenderGold(defender.getGold());
        statsPanel.setAttackerGold(attacker.getGold());
        statsPanel.setDefenderScore(defender.getScore());
        statsPanel.setAttackerScore(attacker.getScore());

        spaceshipManager.addSpaceships(factoryManager.produceSpaceships());
        factoryManager.increase();
        reactorManager.increase();
        turretManager.increase();
        spaceships = spaceshipManager.getSpaceships();
        spaceshipManager.removeSpaceships();
        if( spaceships.size() != 0) {
            projectileManager.setSpaceships(spaceships);
            turretManager.setSpaceships(spaceships);
            projectileManager.addProjectiles(turretManager.fireProjectiles());

            projectileManager.moveAll();
            projectileManager.destroySpaceship();
            projectileManager.cleanDeads();
            attacker.setGold(reactorManager.gatherGold() + attacker.getGold());

            spaceshipManager.moveAll();
        }


    }

    public void render(Graphics g) {
        tileManager.render(g);
        factoryManager.render(g);
        projectileManager.render(g);
        reactorManager.render(g);
        spaceshipManager.render(g);
        turretManager.render(g);
    }

    public boolean checkGameOver() {
        return turnCount > MAX_TURNS;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public int getDefenderScore() {
        return defender.getScore();
    }

    public int getAttackerScore() {
        return attacker.getScore();
    }

    public int getAttackerGold() {
        return attacker.getGold();
    }

    public int getDefenderGold() {
        return defender.getGold();
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public String getCurrentTurn() {
        if (currentTurn)
            return "Defender";
        return "Attacker";
    }

    public int getCurrentTurnCount() {
        return turnCount;
    }
}
