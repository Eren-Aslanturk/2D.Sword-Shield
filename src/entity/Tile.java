package entity;

import util.Boundary;
import util.User;

import java.awt.*;

public class Tile extends GameObject{

    private int cost = 100;
    private User owner = null;

    public Tile() {
        super();
    }

    public int getCost() {
        return cost;
    }

    public User getOwner() {
        return owner;
    }

    public boolean isAvailableTo(User user) {
        return (owner != null) && (user == owner);
    }

    public void setAvailable(User user) {
        owner = user;
    }

    public Point tileLocation(int locX, int locY) {
        for (int i = 400; i <= 400; i = i / 20) {
            for (int j = 400; j <= 400; j = j / 20) {
                if (i == locX && j == locY) {
                    return new Point(locX, locY);
                }
            }
        }
        return null;
    }

    public int findTileLocation(Point loc) {
        for (int i = 400; i <= 400; i = i / 20) {
            for (int j = 400; j <= 400; j = j / 20) {
                if (i == loc.getX() && j == loc.getY()) {
                    return 1;
                }
            }
        }
        return 0;
    }


}

/**
    public void tileLocation(int loc){
        if(loc>400 || loc<=0)
            return;
        int line = loc/20;
        Point location = new Point((loc%20-1)*25+start.x, line*25+start.y);
    }

    public int findTileLocation(Point loc){
        int tileLoc = 0;
        int x,y;
        x = loc.x-start.x;
        x=x/25;
        x+=1;
        y=loc.y=start.y;
        int line = y/25;
        tileLoc = x+line*20;
        return tileLoc;
    }
 */
