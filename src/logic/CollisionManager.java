package logic;

import entity.Projectile;
import entity.Spaceship;
import java.util.ArrayList;

public class CollisionManager {

    //Collision is checked through SpaceshipManager and ProjectileManager
    //This returns true so managers can do their job. Checks the boundaries.
    public boolean collides(Spaceship spaceship, Projectile projectile) {
        //
        if ((spaceship.getX() +2 <= projectile.getX() && spaceship.getX() + 30 >= projectile.getX())) {
            if (spaceship.getY() - 20 <= projectile.getY() && spaceship.getY() >= projectile.getY()) {
                return true;
            }
        }
        return false;
    }
}


