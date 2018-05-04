package logic.factory;

import entity.Factory;
import util.Boundary;
import util.FileManager;

import java.awt.image.BufferedImage;

public class FactoryFactory {

    // Factory properties
    public static final String[] IMAGEPATHS =  {"/images/factory/factory1.png",
                                                "/images/factory/factory2.png",
                                                "/images/factory/factory3.png",
                                                "/images/factory/factory4.png"};

    public static final String[] NAMES = {"Factory Number I",
                                                "Factory Number II",
                                                 "Factory Number III",
                                                 "Factory Number IV"};
    public static final int[] COSTS = {100, 200, 300, 400};
    private static final int[] PRODUCTION_RATES = {100, 100, 300, 400};
    private static final int[] WIDTHS = {50, 40, 50, 25};
    private static final int[] HEIGHTS = {50, 40, 45, 50};

    // FileManager
    FileManager fileManager;

    public FactoryFactory() {
        fileManager = FileManager.getInstance();
    }

    public Factory create(int type, int x, int y) {
        Factory factory = new Factory( type );
        BufferedImage image = fileManager.getImage(IMAGEPATHS[type]);
        image = FileManager.getResizedImage(image,WIDTHS[type],HEIGHTS[type]);


        //initialize properties
        factory.setImage(image);
        factory.setBoundary(new Boundary(x,y,WIDTHS[type],HEIGHTS[type]));
        factory.setProductionRate(PRODUCTION_RATES[type]);

        return factory;
    }
}
