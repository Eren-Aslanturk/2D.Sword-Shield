package logic.manager;

import entity.Projectile;
import entity.Spaceship;
import logic.factory.ProjectileFactory;
import java.awt.*;
import java.util.ArrayList;
import logic.CollisionManager;
import logic.GameManager;
import util.User;

public class ProjectileManager {
    private ArrayList<Spaceship> spaceships;
    private ArrayList<Projectile> projectiles;
    private ProjectileFactory projectileFactory;
    private CollisionManager collision;
    private User attacker,defender;

    public ProjectileManager() {
        projectiles = new ArrayList<>();
        projectileFactory = new ProjectileFactory();
        collision = new CollisionManager();

    }

    public void destroySpaceship() {
        for (int i = 0; i < projectiles.size(); i++) {
            for(int j = 0; j < spaceships.size(); j++) {
                if(spaceships.get(j)!=null && projectiles.get(i)!=null) {
                    if (collision.collides(spaceships.get(j), projectiles.get(i))) {
                        double hp = spaceships.get(j).getHp();
                        double damage = projectiles.get(i).getDamage();
                        double newHP = hp - damage;
                        spaceships.get(j).setHp(newHP);

                    }
                }
            }
        }
    }

    // moves all projectiles towards their targets
    public void moveAll() {
        for(Projectile projectile : projectiles){
            int newX = projectile.getTargetX() + projectile.getX();
            int newY = projectile.getTargetY() + projectile.getY();
            projectile.setX(newX);
            projectile.setY(newY);
            projectile.setRange(projectile.getRange() - projectile.getSpeed()/2);
        }
    }

    // to remove projectiles that are out of range or hit to their target
    public void cleanDeads() {
        for (int i = 0 ; i < projectiles.size() ; i++){
            if(projectiles.get(i).getRange() <= 0 ){
               projectiles.remove(i);
            }
        }
    }

    public void setSpaceships(ArrayList<Spaceship> spaceships) {
        projectileFactory.setSpaceships(spaceships);
        this.spaceships = spaceships;
    }


    public void render(Graphics g) {
        for (Projectile projectile : projectiles)
            g.drawImage(projectile.getImage(), projectile.getX() + projectile.getImage().getWidth()/2, projectile.getY()   , null);
    }

    public ProjectileFactory getProjectileFactory() {
        return projectileFactory;
    }

    public void addProjectiles(ArrayList<Projectile> projectiles){
        for(int i = 0 ; i < projectiles.size() ; i++){
            this.projectiles.add(projectiles.get(i));
        }
    }
}
