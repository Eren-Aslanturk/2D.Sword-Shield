package logic.manager;

import entity.Spaceship;
import logic.factory.SpaceshipFactory;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SpaceshipManager {
    private SpaceshipFactory spaceshipFactory;
    private ArrayList<Spaceship> spaceships;

    public SpaceshipManager(){
        spaceshipFactory = new SpaceshipFactory();
        spaceships = new ArrayList<>();

        // TODO the following lines will be deleted after the demo
        // these lines are added just for the demo

        add(1, 0, 230);
       // add(2, 0, 230);
    }

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
        for (Spaceship spaceship : spaceships)
            g.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(), null);
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
