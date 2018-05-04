package entity;

import gui.GamePanel;

public class Factory extends GameObject {

    private int type;
    private int productionRate;
    private String description;

    public Factory( int type ) {
        super();
        this.type = type;
    }

    public int getProductionRate() {
        return productionRate;
    }

    public void setProductionRate(int productionRate) {
        this.productionRate = productionRate;
    }

    public int getType() {
        return type;
    }

    public String getDescription() {return description;}

    public void setDescription(String description) {
        this.description = description;
    }
}
