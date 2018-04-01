package logic.manager;

import entity.Projectile;
import entity.Spaceship;
import logic.factory.ProjectileFactory;
import java.awt.*;
import java.util.ArrayList;

public class ProjectileManager {

    private ArrayList<Projectile> projectiles;
    private ProjectileFactory projectileFactory;

    public ProjectileManager() {
        projectiles = new ArrayList<>();
        projectileFactory = new ProjectileFactory();
    }

    // moves all projectiles towards their targets
    public void moveAll() {
        for(Projectile projectile : projectiles){
            int newX = projectile.getTargetX() + projectile.getX();
            int newY = projectile.getTargetY() + projectile.getY();
            projectile.setX(newX);
            projectile.setY(newY);
            projectile.setRange(projectile.getRange() - projectile.getTargetX());
        }
    }

    // to remove projectiles that are out of range or hit to their target
    public void cleanDeads() {
        for (int i = 0 ; i < projectiles.size() ; i++){
            if(projectiles.get(i).getRange() <= 0 ){
               projectiles.remove(i);
            }
            //after collision part completed will be implemented
        }

    }
    public void setSpaceships(ArrayList<Spaceship> spaceships){
        projectileFactory.setSpaceships(spaceships);
    }

    public void render(Graphics g) {
        for (Projectile projectile : projectiles)
            g.drawImage(projectile.getImage(), projectile.getX(), projectile.getY(), null);
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
