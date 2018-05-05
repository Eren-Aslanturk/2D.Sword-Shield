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

    }
    public void add(int type, int x, int y) {
        factories.add(factoryFactory.create(type,x,y));
    }

    // TODO factory replacement row x column DONE
    public void insertFactory(int type, int row, int column, int playerNo){
        //factory types
        if(type>=0 && type<4){
            //attacker
            if(playerNo==0){
                //row and column condition: column=[1,5] && row={1,2,5,6}
                if( (1<=column && column<5) && (row == 1 || row == 2 || row == 5 || row == 6) ) {
                    //actual to pseudo dimension translation
                    int axis = ((column-1)*10+1)*5;
                    int ordinate = ((row-1)*10+1)*5 + 100;
                    //create factory
                    add(type, axis, ordinate);
                }
            }
            //defender
            else if(playerNo==1){
                //row and column condition: column=[1,5] && row={1,2,5,6}
                if( (1<=column && column<5) && (row == 1 || row == 2 || row == 5 || row == 6) ) {
                    //actual to pseudo dimension translation
                    int axis = ((column-1+6)*10+1)*5;
                    int ordinate = ((row-1)*10+1)*5 + 100;
                    //create factory
                    add(type, axis, ordinate);
                }
            }

        }

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

    public void setSpaceshipFactory(SpaceshipFactory spaceshipFactory){
        this.spaceshipFactory = spaceshipFactory;
    }
    public void increase(){
        j++;
    }
}
