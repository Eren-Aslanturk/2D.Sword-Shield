package logic.factory;

import entity.Reactor;
import util.Boundary;
import util.FileManager;

import java.awt.image.BufferedImage;

public class ReactorFactory {

    // Reactor properties
    public static final String IMAGEPATH = "/images/reactor/reactor.png";
    public static final String NAME = "Reactor";
    public static final int COST = 100;

    public static final String DESCRIPTIONS =
            "<html><pre>" +
                    "<br/><header><h1>Reactor</h1></header>" +
                    "<br/>Reactor can be bought by the attacker to earn money throughout the stage.It is in the shape of a" +
                    "<br/>nuclear reactor for the game’s concept. It will earn the attacker a certain amount of coins throughout the"+
                    "<br/>game at a predetermined rate that can be upgraded." +
            "</pre></html>";


    private static final int INCOME_RATE = 5;
    private static final int INCOME = 5;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

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
    public static int getIncomeRate() {
        return INCOME_RATE;
    }
}
