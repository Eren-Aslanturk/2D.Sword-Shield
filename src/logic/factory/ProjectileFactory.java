package logic.factory;

import entity.Projectile;
import entity.Spaceship;
import util.Boundary;
import util.FileManager;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ProjectileFactory {
    private ArrayList<Spaceship> spaceships;
    // Projectile properties
    public static final String[] IMAGEPATHS =  {"/images/projectile/projectile1.png",
                                                "/images/projectile/projectile2.png",
                                                "/images/projectile/projectile3.png",
                                                "/images/projectile/projectile4.png"};

    private static final double[] SPEEDS  = {10,14,18,23};
    private static final double[] ARMORPENS = {2.3, 4.5, 5.6, 6.7};
    private static final double[] DAMAGES = {3.4, 5.4, 3.3, 2.2};
    private static final double[] RANGES = {45, 44, 23.25, 45.5};
    private static final int[] WIDTHS = {40, 46, 35, 103};
    private static final int[] HEIGHTS = {40, 45, 35, 105};

    // FileManager
    FileManager fileManager;

    public ProjectileFactory() {
        fileManager = FileManager.getInstance();
    }

    public Projectile create(int type, int x, int y) {
        Projectile projectile = new Projectile();
        // prepare the corresponding image
        BufferedImage image = fileManager.getImage(IMAGEPATHS[type]);
        image = fileManager.getResizedImage(image, WIDTHS[type], HEIGHTS[type]);
        // initialize properties
        //projectile.setImage(image);
        projectile.setDamage(DAMAGES[type]);
        projectile.setArmorpen(ARMORPENS[type]);
        projectile.setSpeed(SPEEDS[type]);
        projectile.setRange(RANGES[type]);
        projectile.setBoundary(new Boundary(x,y,WIDTHS[type],HEIGHTS[type]));
        Spaceship spaceship = closestSpaceship(x);
        int a = x - spaceship.getX() ;
        int b = spaceship.getY() - y;

        int c = (int) (Math.sqrt(a*a + b*b) / SPEEDS[type]);
        if(spaceship.getX() < x )
            a = -a;

        float angle;

        if(x > spaceship.getX()) {
            angle = (float) Math.toDegrees(Math.atan2(spaceship.getY() - y, spaceship.getX() - x));
        }
        else {
            angle = (float) Math.toDegrees(Math.atan2(spaceship.getY() - y, -spaceship.getX() + x));
        }
        if(angle < 0){
            angle += 360;
        }


        projectile.setTargetX(a/c + 2);
        projectile.setTargetY(b/c);

        double rotationRequired =  Math.toRadians(angle);

        int locationX = image.getWidth() / 2;
        int locationY = image.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        projectile.setImage(op.filter(image,null));

        return projectile;
    }
    public void setSpaceships(ArrayList<Spaceship> spaceships){
        this.spaceships = spaceships;
    }

    private Spaceship closestSpaceship( int x ){

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
}
