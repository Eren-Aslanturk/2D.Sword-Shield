package logic.manager;

import entity.Spaceship;
import logic.factory.SpaceshipFactory;
import entity.Projectile;
import logic.factory.ProjectileFactory;
import logic.CollisionManager;
import java.awt.*;
import java.util.ArrayList;

public class SpaceshipManager {
    private SpaceshipFactory spaceshipFactory;
    private ArrayList<Spaceship> spaceships;
    private ArrayList<Projectile> projectiles;
    private CollisionManager colman;

    public SpaceshipManager(){
        spaceshipFactory = new SpaceshipFactory();
        spaceships = new ArrayList<>();
        projectiles = new ArrayList<>();
        colman = new CollisionManager();

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

    public void destroySpaceship(){
        for(int i = 0; i < spaceships.size() ; i++){
            if(spaceships.get(i).getHp() <= 0)
                spaceships.remove(i);
            if(spaceships.get(i).getX() >= 450)
                spaceships.remove(i);
        }
    }
    public void takeDamage(){
        for(int i = 0; i < spaceships.size() ; i++){
           for(int j = 0; j < projectiles.size(); j++){

               if(colman.collide(spaceships.get(i),projectiles.get(j))) {
                   System.out.println("1");
                   spaceships.get(i).setHp(spaceships.get(i).getHp() - projectiles.get(j).getDamage());
                   System.out.println(spaceships.get(i).getHp());
               }


           }
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
