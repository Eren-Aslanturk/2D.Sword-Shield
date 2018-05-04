package logic;

import entity.Projectile;
import entity.Spaceship;
import java.util.ArrayList;

public class CollisionManager {
    private ArrayList<Spaceship> spaceships;
    private ArrayList<Projectile> projectiles;

    /*
        public void destroySpaceship(){
            for (Spaceship spaceship : spaceships) {
                for(spaceship.getX() > 500 && spaceship.getHp()>0){

                }
            }
        }
    */
    public boolean collides(Spaceship spaceship, Projectile projectile) {
        //
        if ((spaceship.getX() - 5.75 <= projectile.getX() && spaceship.getX() + 5.75 >= projectile.getX())) {
            if (spaceship.getY() - 2 <= projectile.getY() && spaceship.getY() + 2 >= projectile.getY()) {
                return true;
            }
        }
        return false;
    }
}


