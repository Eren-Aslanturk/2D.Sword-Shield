package logic;

import entity.Spaceship;
import entity.Projectile;

public class CollisionManager {

    public boolean collide(Spaceship spaceship, Projectile projectile){
        if(spaceship.getX() +4.75 >= projectile.getX() && spaceship.getX() -4.75 <= projectile.getX()) {
            if (spaceship.getY()+4.75 >= projectile.getY() && spaceship.getY() -4.75 <= projectile.getY()) {
                System.out.println("Oldu gibi sanki mi acaba");
                return true;
            }
            System.out.println("Giriyor musun amin oglu");
        }
        System.out.println("Amcik agizlinin oglu");

        return false;
    }

}
