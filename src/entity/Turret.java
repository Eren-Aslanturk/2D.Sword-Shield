package entity;

public class Turret extends GameObject {

    private double angle = Math.toRadians(180);
    private int type;
    private int attackSpeed;
    private String description;

    public Turret(int type) {
        super();
        this.type = type;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getType(){return type;}

    public double getAngle(){return angle;}
    public void setAngle(double angle){this.angle = angle;}

    public String getDescription() {return description;}
    public void setDescription(String description) {
        this.description = description;
    }
}
