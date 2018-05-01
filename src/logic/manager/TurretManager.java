package logic.manager;

import entity.Projectile;
import entity.Spaceship;
import entity.Turret;
import logic.factory.ProjectileFactory;
import logic.factory.TurretFactory;
import util.FileManager;

import java.awt.*;
import java.awt.geom.AffineTransform;
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
        add(3,305,155);
    }

    // adds a turret of the given type at the given location
    public void add(int type, int x, int y) {
        turrets.add(turretFactory.create(type,x,y));
    }

    // returns fired projectiles on this iteration
    public ArrayList<Projectile> fireProjectiles() {
        ArrayList<Projectile> projectiles = new ArrayList<>();
        // TODO will be implemented after iteration I

        for(int i = 0; i < turrets.size() ; i++){
            //System.out.println(j%turrets.get(i).getAttackSpeed() + " forlop");
            if(j%turrets.get(i).getAttackSpeed() == 0) {
                //x and y will be decided later
                Spaceship spaceship = closestSpaceship(turrets.get(i).getX());
                float angle;

                if(turrets.get(i).getX() > spaceship.getX()) {
                    angle = (float) Math.toDegrees(Math.atan2(spaceship.getY() - turrets.get(i).getY(), spaceship.getX() - turrets.get(i).getX()));
                }
                else {
                    angle = (float) Math.toDegrees(Math.atan2(spaceship.getY() - turrets.get(i).getY(), -spaceship.getX() + turrets.get(i).getX()));
                }
                if(angle < 0){
                    angle += 360;
                }

               double rotationRequired =  Math.toRadians(angle);
                //double tmp = -turrets.get(i).getAngle()+rotationRequired;
               // turrets.get(i).setAngle(rotationRequired);



                int locationX = turrets.get(i).getImage().getWidth() / 2;
                int locationY = turrets.get(i).getImage().getHeight() / 2;
                projectiles.add(projectileFactory.create(turrets.get(i).getType(),turrets.get(i).getX(),turrets.get(i).getY()));
                AffineTransform tx = new AffineTransform();

                //AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
                //tx.rotate(tmp,locationX,locationY);
                //AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
                Turret turret = turrets.get(i);
                BufferedImage reloadedImage = FileManager.getInstance().getImage(TurretFactory.IMAGEPATHS[turret.getType()]);

                turret.setImage(FileManager.getResizedImage(reloadedImage, TurretFactory.WIDTHS[turret.getType()],TurretFactory.HEIGHTS[turret.getType()]));
                //turrets.get(i).setImage(op.filter(turrets.get(i).getImage(),null));

                //j = 0;

                turrets.get(i).setImage(FileManager.rotate(turrets.get(i).getImage(),rotationRequired));

            }
        }

        return projectiles;
    }

    public void render(Graphics g) {
        for (Turret turret: turrets) {
            g.drawImage(turret.getImage(), turret.getX(), turret.getY(), null);
        }
    }

    public void setProjectileFactory(ProjectileFactory projectileFactory) {
        this.projectileFactory = projectileFactory;
    }
    public void increase(){
        System.out.println(j);
        j++;
    }

    private Spaceship closestSpaceship(int x ){

        Spaceship spaceship = spaceships.get(0);
        Spaceship tmp;
        System.out.println(spaceships.size());
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

        System.out.println(spaceship.getX());
        return spaceship;
    }
    public void setSpaceships(ArrayList<Spaceship> spaceships){
        this.spaceships = spaceships;
    }
}
