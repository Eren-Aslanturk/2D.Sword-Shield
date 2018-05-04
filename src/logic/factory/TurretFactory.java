package logic.factory;

import entity.Turret;
import util.Boundary;
import util.FileManager;

import java.awt.image.BufferedImage;

public class TurretFactory {

    // Turret properties
    public static final String[] IMAGEPATHS =  {"/images/turret/turret1.png",
                                                "/images/turret/turret2.png",
                                                "/images/turret/turret3.png",
                                                "/images/turret/turret4.png"};

    public static final String[] NAMES = {"Turret Number I",
                                                 "Turret Number II",
                                                 "Turret Number III",
                                                 "Turret Number IV"};

    public static final int[] COSTS = {100, 200, 300, 400};
    private static final int[] ATTACK_SPEEDS = {10, 12, 20, 15};
    public static final int[] WIDTHS = {40, 40, 40, 80};
    public static final int[] HEIGHTS = {40, 40, 40, 80};

    // File Manager
    FileManager fileManager;

    public TurretFactory() {
        fileManager = FileManager.getInstance();
    }

    public Turret create(int type, int x, int y) {
        Turret turret = new Turret(type);
        BufferedImage image = fileManager.getImage(IMAGEPATHS[type]);
        image = FileManager.getResizedImage(image,WIDTHS[type],HEIGHTS[type]);

        //initialize properties
        turret.setImage(image);
        turret.setBoundary(new Boundary(x,y,WIDTHS[type],HEIGHTS[type]));
        turret.setAttackSpeed(ATTACK_SPEEDS[type]);

        return turret;
    }



}
