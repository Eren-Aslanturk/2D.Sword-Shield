package logic.manager;

import entity.GameObject;
import entity.Projectile;
import entity.Turret;
import logic.factory.TurretFactory;

import java.awt.*;
import java.util.ArrayList;

public class TurretManager {

    private ArrayList<Turret> turrets;
    private TurretFactory turretFactory;

    public TurretManager() {
        turrets = new ArrayList<>();
        turretFactory = new TurretFactory();

        // TODO the following lines will be deleted after the demo
        // these lines are added just for the demo
        add(0,305,155);
    }

    // adds a turret of the given type at the given location
    public void add(int type, int x, int y) {
        turrets.add(turretFactory.create(type,x,y));
    }

    // returns fired projectiles on this iteration
    public ArrayList<GameObject> fireProjectiles() {
        ArrayList<GameObject> projectiles = new ArrayList<>();
        // TODO will be implemented after iteration I
        return projectiles;
    }

    public void render(Graphics g) {
        for (Turret turret: turrets) {
            g.drawImage(turret.getImage(), turret.getX(), turret.getY(), null);
        }
    }

}
