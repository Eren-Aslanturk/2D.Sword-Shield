package logic.manager;

import entity.Factory;
import entity.GameObject;
import logic.GameManager;
import logic.factory.FactoryFactory;

import java.awt.*;
import java.util.ArrayList;

public class FactoryManager {

    private ArrayList<Factory> factories;
    private FactoryFactory factoryFactory;

    public FactoryManager() {
        factories = new ArrayList<>();
        factoryFactory = new FactoryFactory();

        // TODO the following lines will be deleted after the demo
        // these lines are added just for the demo
        add(1,55,105);
    }
    public void add(int type, int x, int y) {
        factories.add(factoryFactory.create(type,x,y));
    }

    public ArrayList<GameObject> produceSpaceships() {
        ArrayList<GameObject> spaceships = new ArrayList<>();
        // TODO will be implemented after iteration I
        return spaceships;
    }

    public void render(Graphics g) {
        for (Factory factory: factories)
            g.drawImage(factory.getImage(), factory.getX(), factory.getY(), null);

    }

}
