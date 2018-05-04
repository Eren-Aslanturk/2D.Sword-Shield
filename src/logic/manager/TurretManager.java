package logic.manager;

import entity.Projectile;
import entity.Spaceship;
import entity.Turret;
import logic.factory.ProjectileFactory;
import logic.factory.TurretFactory;
import util.FileManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TurretManager {
    private ArrayList<Spaceship> spaceships;
    private int j = 0;
    private ArrayList<Turret> turrets;
    private TurretFactory turretFactory;
    private ProjectileFactory projectileFactory;
    FileManager fileManager;
    public TurretManager() {
        turrets = new ArrayList<>();
        turretFactory = new TurretFactory();

        // TODO the following lines will be deleted after the demo
        // these lines are added just for the demo
        add(1,310,155);
        add(2,350,155);
        add(0,410,155);
        add(3,435,135);
    }

    // adds a turret of the given type at the given location
    public void add(int type, int x, int y) {
        turrets.add(turretFactory.create(type,x+TurretFactory.WIDTHS[type]/2,y+TurretFactory.HEIGHTS[type]/2));
    }

    // returns fired projectiles on this iteration
    public ArrayList<Projectile> fireProjectiles() {
        ArrayList<Projectile> projectiles = new ArrayList<>();
        // TODO will be implemented after iteration I

        for(int i = 0; i < turrets.size() ; i++){
            if(j%turrets.get(i).getAttackSpeed() == 0) {
                //x and y will be decided later
                Spaceship spaceship = closestSpaceship(turrets.get(i).getX());
                double angle;


                    angle =  Math.toDegrees(Math.atan2(spaceship.getY() - turrets.get(i).getY()+ TurretFactory.HEIGHTS[turrets.get(i).getType()]/2,
                            spaceship.getX() - turrets.get(i).getX() + TurretFactory.WIDTHS[turrets.get(i).getType()]/2));


                    //angle = (float) Math.toDegrees(Math.atan2(spaceship.getY() - turrets.get(i).getY(), -spaceship.getX() + turrets.get(i).getX()));

                if(angle < 0){
                    angle += 360;
                }
                double rotationRequired =  Math.toRadians(angle);
                Projectile projectile = projectileFactory.create(turrets.get(i).getType(),turrets.get(i).getX()- ProjectileFactory.WIDTHS[turrets.get(i).getType()/2],
                        turrets.get(i).getY()- ProjectileFactory.HEIGHTS[turrets.get(i).getType()]/2);
                projectiles.add(projectile);

                Turret turret = turrets.get(i);
                BufferedImage reloadedImage = FileManager.getInstance().getImage(TurretFactory.IMAGEPATHS[turret.getType()]);
                turret.setImage(FileManager.getResizedImage(reloadedImage, TurretFactory.WIDTHS[turret.getType()],TurretFactory.HEIGHTS[turret.getType()]));


                //j = 0;

                turrets.get(i).setImage(FileManager.rotate(turrets.get(i).getImage(),Math.toRadians(projectile.getAngle())));

            }
        }

        return projectiles;
    }

    public void render(Graphics g) {
        for (Turret turret: turrets) {
            g.drawImage(turret.getImage(), turret.getX()-(turret.getImage().getWidth()/2), turret.getY()-(turret.getImage().getHeight()/2), null);
        }
    }

    public void setProjectileFactory(ProjectileFactory projectileFactory) {
        this.projectileFactory = projectileFactory;
    }
    public void increase(){
        j++;
    }

    private Spaceship closestSpaceship(int x ){

        Spaceship spaceship = spaceships.get(0);
        Spaceship tmp;

        for(int i = 0; i < spaceships.size() - 1 ; i++){
            //spaceship = spaceships.get(i);
            tmp = spaceships.get(i + 1);
            if(spaceship.getX() < x && tmp.getX() < x ){
                if(spaceship.getX() < tmp.getX()){ spaceship = tmp;}
            }
            else if (spaceship.getX() > x && tmp.getX() < x ){
                if(x - tmp.getX() < spaceship.getX() - x){
                    spaceship = tmp;
                }
            }
            else if(spaceship.getX() < x && tmp.getX() > x){
                if(x - spaceship.getX() > tmp.getX() - x){
                    spaceship = tmp;
                }
            }
            else if(spaceship.getX() > x && tmp.getX() > x ){
                if(spaceship.getX() > tmp.getX()){ spaceship = tmp;}
            }
        }


        return spaceship;
    }
    public void setSpaceships(ArrayList<Spaceship> spaceships){
        this.spaceships = spaceships;
    }
}
