package logic.factory;

import entity.Spaceship;
import util.Boundary;
import util.FileManager;
import java.awt.image.BufferedImage;


public class SpaceshipFactory {

    // Spaceship properties
    public static final String[] IMAGEPATHS =  {"/images/spaceship/spaceship1.png",
                                                "/images/spaceship/spaceship2.png",
                                                "/images/spaceship/spaceship3.png",
                                                "/images/spaceship/spaceship4.png"};

    private static final int[] SPEEDS = {5, 1, 3, 8};
    private static final double[] ARMORS = {3.3, 4.5, 2.5, 1.9};
    private static final double[] HPS = {200, 323, 234, 456};
    private static final int[] WIDTHS = {50, 50, 50, 50};
    private static final int[] HEIGHTS = {40, 40, 40, 40};
    private static final int[] REWARDS = {10,20,30,40};

    // FileManager
    FileManager fileManager;

    public SpaceshipFactory() {
        fileManager = FileManager.getInstance();
    }
    public Spaceship create(int type, int x, int y) {
        Spaceship spaceship = new Spaceship();
        // prepare the corresponding image
        BufferedImage image = fileManager.getImage(IMAGEPATHS[type]);
        image = fileManager.getResizedImage(image, WIDTHS[type], HEIGHTS[type]);

        // initialize properties
        spaceship.setImage(image);
        spaceship.setBoundary(new Boundary(x, y, WIDTHS[type], HEIGHTS[type]));
        spaceship.setArmor(ARMORS[type]);
        spaceship.setHp(HPS[type]);
        spaceship.setSpeed(SPEEDS[type]);
        spaceship.setReward(REWARDS[type]);
        return spaceship;
    }
}
