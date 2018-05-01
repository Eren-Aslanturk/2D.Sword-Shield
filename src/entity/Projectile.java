package entity;

public class Projectile extends GameObject{

    private double angle;
    private double range;
    private double speed;
    private double armorpen;
    private double damage;
    private int  targetX;
    private int  targetY;

    public Projectile() {
        super();
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getArmorpen() {
        return armorpen;
    }

    public void setArmorpen(double armorpen) {
        this.armorpen = armorpen;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public int getTargetX() {
        return targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    public  void setAngle(double angle){
        this.angle = angle;
    }
    public double getAngle(){
        return angle;
    }
}
