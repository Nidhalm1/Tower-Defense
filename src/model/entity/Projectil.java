package model.entity;

import model.*;

public class Projectil{


    protected OnScreenCoord coord;
    TowerType type;

    public Projectil(double x,double y,TowerType type){
        this.coord = new OnScreenCoord(x,y);
        this.type = type;
    }

    public void move(){
        this.coord.setX(this.coord.getX()+4);
    }

    public OnScreenCoord getOnScreenCoord(){
        return this.coord;
    }

    public TowerType getType() {
        return type;
    }

}