package Entities;

import Entities.MonstersSpec;

public class Monster extends Entity implements MonstersSpec {
    private double speed;

    public double getSpeed(){
        return speed;
    }
}
