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

    public static final String[] DESCRIPTIONS = {
            "<html><pre>" +
                    "<br/><header><h1>Miner</header></h1>" +
                    "<br/>Miner will produce mines which will be placed in front of the miner. It can produce at most 3 mines " +
                    "<br/>until at least one of them get destroyed. They will be placed at first exactly in front, then one to the left tile of" +
                    "<br/>the first, then the last one will be on the right tile of the first one. Mines will have area damage effect. To" +
                    "<br/>balance it, it will be produced in a low rate. It does not have armor penetration. Moreover it gives less" +
                    "<br/>damage to armored units." +
                    "</pre></html>",

            "<html><pre>" +
                    "<br/><header><h1>Fryinator</header></h1>" +
                    "<br/>Fryinator is the most powerful weapon for defender. It will have the most damage and most range" +
                    "<br/>But as expected its rate of fire is low, it takes up two tiles and it is the most expensive one of the turrets." +
                    "<br/>It has armor penetration" +
                    "</pre></html>",

            "<html><pre>" +
                    "<br/><header><h1>Lazerion</header></h1>" +
                    "<br/>Lazerion will be the fast shooter turret." +
                    "<br/>Therefore, it will have the least range but second powerful\n" +
                    "projectiles for balancing. It does not have armor penetration" +
                    "</pre></html>",

            "<html><pre>" +
                    "<br/><header><h1>Good Ol' Gun</header></h1>" +
                    "<br/>Good Ol’ Gun will be a machine gun. As technology is kinda old with this one, it will have the least" +
                    "<br/>damage, but an average range and a good rate of firing. No armor penetration with this gun." +
                    "</pre></html>"
    };

    public static final int[] COSTS = {100, 200, 300, 400};
    private static final int[] ATTACK_SPEEDS = {25, 12, 20, 15};
    public static final int[] WIDTHS = {20, 20, 20, 20};
    public static final int[] HEIGHTS = {20, 20, 20, 20};

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

    public static int getSpeed(int type){
        return ATTACK_SPEEDS[type];
    }



}
