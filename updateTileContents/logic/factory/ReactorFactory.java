package logic.factory;

import entity.Reactor;
import util.Boundary;
import util.FileManager;

import java.awt.image.BufferedImage;

public class ReactorFactory {

    // Reactor properties
    public static final String IMAGEPATH = "/images/reactor/reactor.png";
    public static final String NAME = "Reactor";
    public static final int COST = 5;

    private static final int INCOME_RATE = 5;
    private static final int INCOME = 5;
    private static final int WIDTH = 55;
    private static final int HEIGHT = 100;

    // FileManager
    FileManager fileManager;

    public ReactorFactory() {
        fileManager = FileManager.getInstance();
    }

    public Reactor create(int x, int y) {
        Reactor reactor = new Reactor();

        BufferedImage image = fileManager.getImage(IMAGEPATH);
        image = FileManager.getResizedImage(image,WIDTH,HEIGHT);

        reactor.setBoundary(new Boundary(x,y,WIDTH,HEIGHT));
        reactor.setBoundary(new Boundary(x,y,WIDTH,HEIGHT));
        reactor.setX(x);
        reactor.setY(y);
        reactor.setIncome(INCOME);
        reactor.setIncomeRate(INCOME_RATE);
        return reactor;
    }

    public int getCost() {
        return COST;
    }
}
