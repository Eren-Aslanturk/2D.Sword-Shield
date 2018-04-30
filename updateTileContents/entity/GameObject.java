package entity;

import util.Boundary;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {

    private Boundary boundary;
    private BufferedImage image;

    public int getX() {
        return boundary.getX();
    }

    public void setX(int x) {
        boundary.setX(x);
    }

    public int getY() {
        return boundary.getY();
    }
    public void setY(int y) {
        boundary.setY(y);
    }

    public void setBoundary(Boundary boundary) {
        this.boundary = boundary;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }
}
