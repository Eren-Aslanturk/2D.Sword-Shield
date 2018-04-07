package entity;

public class Turret extends GameObject {

    private int type;
    private int attackSpeed;

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
}
