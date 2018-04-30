package logic.manager;

import entity.GameObject;
import entity.Projectile;
import entity.Turret;
import logic.factory.ProjectileFactory;
import logic.factory.TurretFactory;

import java.awt.*;
import java.util.ArrayList;

public class TurretManager {

    private int j = 0;
    private ArrayList<Turret> turrets;
    private TurretFactory turretFactory;
    private ProjectileFactory projectileFactory;

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
    public ArrayList<Projectile> fireProjectiles() {
        ArrayList<Projectile> projectiles = new ArrayList<>();
        // TODO will be implemented after iteration I
        for(int i = 0; i < turrets.size() ; i++){
            if(j%turrets.get(i).getAttackSpeed() == 0) {
                //x and y will be decided later
                projectiles.add(projectileFactory.create(turrets.get(i).getType(),turrets.get(i).getX(),turrets.get(i).getY()));
                j = 0;
            }
        }

        return projectiles;
    }

    public void render(Graphics g) {
        for (Turret turret: turrets) {
            g.drawImage(turret.getImage(), turret.getX(), turret.getY(), null);
        }
    }

    public void setProjectileFactory(ProjectileFactory projectileFactory) {
        this.projectileFactory = projectileFactory;
    }
    public void increase(){
        j++;
    }
}
