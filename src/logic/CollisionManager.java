package logic;

import entity.Projectile;
import entity.Spaceship;
import java.util.ArrayList;

public class CollisionManager {

    //Collision is checked through SpaceshipManager and ProjectileManager
    //This returns true so managers can do their job. Checks the boundaries.
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


