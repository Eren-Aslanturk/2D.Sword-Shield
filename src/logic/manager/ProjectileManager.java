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
        for (int i = 0; i < projectiles.size()-1; i++) {
            for(int j = 0; j < spaceships.size()-1; j++) {
                if(spaceships.get(j)!=null && projectiles.get(i)!=null) {
                    if (collision.collides(spaceships.get(j), projectiles.get(i))) {
                        double hp = spaceships.get(j).getHp();
                        double damage = projectiles.get(i).getDamage();
                        double newHP = hp - damage;
                        spaceships.get(j).setHp(newHP);
                        projectiles.remove(i);
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
        //TODO just to test out related functions
        for (int i = 0 ; i < projectiles.size() ; i++){
            //invalid range condition
            if(projectiles.get(i).getRange() <= 0 ){
               projectiles.remove(i);
            }

            //if collision happens and no hp left for target ship
/*
            int spaceshipIndex = findSpaceship(projectiles.get(i).getX(),projectiles.get(i).getY());
            if(spaceshipIndex>=0){
                Spaceship target = getSpaceships().get(spaceshipIndex);
                if(target!=null){
                    if(target.getHp()>0){
                        Projectile bullet = projectiles.get(i);
                        collide(target,bullet);
                    }
                }

            }
*/

            /*
            Spaceship target;
            target = getSpaceships().get(findSpaceship(projectiles.get(i).getX(),projectiles.get(i).getTargetY()));

            if(target!=null && target.getHp()<=0){
                //clean target and projectile
                getSpaceships().remove(target);
                projectiles.remove(i);
            }*/
        }
    }

    public void setSpaceships(ArrayList<Spaceship> spaceships) {
        projectileFactory.setSpaceships(spaceships);
        this.spaceships = spaceships;
    }


    public void render(Graphics g) {
        for (Projectile projectile : projectiles)
            //cleanDeads();
            g.drawImage(projectile.getImage(), projectile.getX(), projectile.getY()   , null);
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
