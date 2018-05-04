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

    public static final String[] DESCRIPTIONS = {
            "<html><pre>" +
                    "<br/><header><h1>Swedish Spaceship Co</h1></header>" +
                    "<br/>If you want very fast things which will divide in three when first one is destroyed Swedish is the way " +
                    "<br/>to go. This units won’t have any armor and they will have low health, but as expected they will be very fast." +
                    "<br/>When the first one is destroyed it will spawn three more. Production rate is low." +
                    "</pre></html>",

            "<html><pre>" +
                    "<br/><header><h1>Muscular Spaceship Co</h1></header>" +
                    "<br/>As the name suggests this factory will produce heavy and powerful units. Therefore, it has a low" +
                    "<br/>production rate." +
                    "<br/>Units will be moderate in speed but will have heavy armor and high health." +
                    "</pre></html>",

            "<html><pre>" +
                    "<br/><header><h1>Athletic Spaceship Co.</h1></header>" +
                    "<br/>This factory will produce units which will be fast, have light armor and average health at average" +
                    "<br/>production rate." +
                    "</pre></html>",

            "<html><pre>" +
                    "<br/><header><h1>Tough Tough Co</h1>" +
                    "<br/>This is the most expensive factory, it will take 2 tiles and will have a low production rate. It will" +
                    "<br/>produce units with heavy armor and heavy health which can go at average speed." +
                    "</pre></html>"
    };

    public static final int[] COSTS = {100, 200, 300, 400};
    private static final int[] PRODUCTION_RATES = {45, 40, 50, 60};
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

    public static int getSpeed(int type) {
        return PRODUCTION_RATES[type];
    }
}
