package entity;

import util.Boundary;
import util.User;

import java.awt.*;

public class Tile extends GameObject {

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