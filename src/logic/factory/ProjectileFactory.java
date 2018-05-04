package logic.factory;

import entity.Projectile;
import entity.Spaceship;
import util.Boundary;
import util.FileManager;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ProjectileFactory {
    private ArrayList<Spaceship> spaceships;
    // Projectile properties
    public static final String[] IMAGEPATHS =  {"/images/projectile/projectile1.png",
                                                "/images/projectile/projectile2.png",
                                                "/images/projectile/projectile3.png",
                                                "/images/projectile/projectile4.png"};

    private static final double[] SPEEDS  = {5,7,9,11.5};
    private static final double[] ARMORPENS = {2.3, 4.5, 5.6, 6.7};
    private static final double[] DAMAGES = {34, 54, 126, 110};
    private static final double[] RANGES = {145, 144, 123.25, 145.5};
    public static final int[] WIDTHS = {40, 46, 35, 103};
    public static final int[] HEIGHTS = {40, 45, 35, 105};

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


        projectile.setImage(FileManager.rotate(image,rotationRequired));

        return projectile;
    }
    public void setSpaceships(ArrayList<Spaceship> spaceships){
        this.spaceships = spaceships;
    }

    private Spaceship closestSpaceship( int x ){

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
}
