package logic.manager;

import entity.Spaceship;
import util.User;
import logic.factory.SpaceshipFactory;
import gui.StatsPanel;

import java.awt.*;
import java.util.ArrayList;

public class SpaceshipManager {
    private SpaceshipFactory spaceshipFactory;
    private ArrayList<Spaceship> spaceships;
    private User attacker,defender;
    private int MAX_SPAWN;
   // private StatsPanel statsPanel;

    public SpaceshipManager(){
        spaceshipFactory = new SpaceshipFactory();
        spaceships = new ArrayList<>();
       // statsPanel = new StatsPanel();

        // TODO SPACESHIP DEMO
        // these lines are added just for the demo
        for(int i = 0; i<spaceships.size(); i++) {
            System.out.println((spaceships.get(i).getImage()));
        }
    }

    //ship spawner
    public void add(int type, int x, int y) {
            spaceships.add(spaceshipFactory.create(type, x, y));
    }

    // moves all spaceships
    public void moveAll() {
        for (Spaceship spaceship : spaceships) {
            int newX = spaceship.getX() + spaceship.getSpeed();
            spaceship.setX(newX);
        }
    }

    public void render(Graphics g) {

        //spawn all ships of same kind
        for (Spaceship spaceship : spaceships)
            g.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(), null);


        //TODO remove spaceships -> remove default spawned ships (type1)
        //spawn for a specific amount

        /*
        int spawncount = 0;
        for (Spaceship spaceship : spaceships) {
            //specific amount of spawns
            if(spawncount++<=MAX_SPAWN) {
                //remove if any spaceship is out of game field
                if (spaceship.getX() >= 500 || spaceship.getY() >= 400) {

                    //spaceships.remove(spaceship);

                }
                g.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(), null);
            }
        }
        */
    }

    public void setUsers(User attacker, User defender){
        this.attacker = attacker;
        this.defender = defender;
    }

    //Scores and golds are updated through here by controlling the state of spaceships
    public void removeSpaceships(){
        for(int i = 0; i < spaceships.size() ; i++){
            if(spaceships.get(i).getX()>450) {
                attacker.setScore(attacker.getScore() + spaceships.get(i).getReward());
                spaceships.remove(i);
            }
            if(spaceships.get(i).getHp() <= 0) {
                defender.setGold(defender.getGold() + spaceships.get(i).getReward());
                System.out.println(defender.getGold());
             //   statsPanel.setDefenderGold();
                spaceships.remove(i);
            }
        }
    }
    public ArrayList<Spaceship> getSpaceships() {
        return spaceships;
    }

    public SpaceshipFactory getSpaceshipFactory() {
        return spaceshipFactory;
    }
    public void addSpaceships(ArrayList<Spaceship> spaceships){
        for(int i = 0; i < spaceships.size() ; i++){
            this.spaceships.add(spaceships.get(i));
        }
    }
}
