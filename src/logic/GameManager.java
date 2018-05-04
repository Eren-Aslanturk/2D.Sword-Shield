package logic;

import entity.Spaceship;
import logic.manager.*;
import util.User;

import java.awt.*;
import java.util.ArrayList;

public class GameManager {
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
        factoryManager.setSpaceshipFactory(spaceshipManager.getSpaceshipFactory());
        turretManager.setProjectileFactory(projectileManager.getProjectileFactory());
    }

    /*
    * TODO will be implemented after iteration I
    * right now all tiles on the left side belong to the attacker
    * whereas the tiles on the right side belong to the defender
    */
    public void buyTile(Point point) {

    }

    /*
    * TODO will be implemented after iteration I
    */
    public void buyItem(Point point, int type) {

    }

    // TODO will be implemented
    public void update() {

        spaceshipManager.addSpaceships(factoryManager.produceSpaceships());
        factoryManager.increase();
        turretManager.increase();
        spaceships = spaceshipManager.getSpaceships();
        if( spaceships.size() != 0) {
            projectileManager.setSpaceships(spaceships);
            turretManager.setSpaceships(spaceships);
            projectileManager.addProjectiles(turretManager.fireProjectiles());

            projectileManager.moveAll();
            spaceshipManager.takeDamage();
            spaceshipManager.destroySpaceship();
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
}
