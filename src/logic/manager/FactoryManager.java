package logic.manager;

import entity.Factory;
import entity.Spaceship;
import logic.factory.FactoryFactory;
import logic.factory.SpaceshipFactory;

import java.awt.*;
import java.util.ArrayList;

public class FactoryManager {

    private SpaceshipFactory spaceshipFactory;
    private ArrayList<Factory> factories;
    private FactoryFactory factoryFactory;
    private int j = 0;
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

    public ArrayList<Spaceship> produceSpaceships() {
        //SpaceshipFactory spaceshipFactory = new SpaceshipFactory();
        ArrayList<Spaceship> spaceships = new ArrayList<>();
        for(int i = 0; i < factories.size() ; i++ ) {
            if(j%factories.get(i).getProductionRate()== 0) {
                spaceships.add(spaceshipFactory.create(factories.get(i).getType(), 0, 230));
            }
        }
        return spaceships;
    }

    public void render(Graphics g) {
        for (Factory factory: factories)
            g.drawImage(factory.getImage(), factory.getX(), factory.getY(), null);

    }

    public void setSpaceshipFactory(SpaceshipFactory spaceshipFactory){this.spaceshipFactory = spaceshipFactory;}
    public void increase(){
        j++;
    }
}
