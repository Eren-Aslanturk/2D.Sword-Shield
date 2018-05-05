package logic.manager;

import entity.Reactor;
import logic.factory.ReactorFactory;
import java.awt.*;
import java.util.ArrayList;

public class ReactorManager {
    private int j = 0;
    private ArrayList<Reactor> reactors;
    private ReactorFactory reactorFactory;

    public ReactorManager() {
        reactors = new ArrayList<>();
        reactorFactory = new ReactorFactory();
    }

    public void add(int type, int x, int y) {
        reactors.add(reactorFactory.create(x,y));
    }

    // returns the total gold collected from reactors at this iteration
    public int gatherGold() {
        int gold = 0;
        for(int i = 0; i < reactors.size() ; i++ ) {
            System.out.println(j);
            if(j%reactors.get(i).getIncomeRate()== 0) {
                gold = gold + reactors.get(i).getIncome();
            }
        }
        return gold;
    }

    public void render(Graphics g) {
        for (Reactor reactor: reactors)
            g.drawImage(reactor.getImage(), reactor.getX(), reactor.getY(), null);
    }
    public void increase(){
        j++;
    }
}
