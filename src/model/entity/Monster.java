package model.entity;

import model.OnScreenCoord;

public class Monster extends Entity{

    protected double speed;
    // Vitesse, pas vraiment implementable sur le terminal
    protected MonsterType type;

    protected OnScreenCoord coord;

    public OnScreenCoord getOnScreenCoord(){
        return coord;
    }

    public Monster(MonsterType type){
        super(20,5);
        coord = new OnScreenCoord(970, 150);
        switch (type) {
            case SKELETON:
                speed = 0.3;
                this.type = type;
                setLife(50);
                setPower(5);
                break;
            
            case GOBELIN:
                speed = 0.8;
                this.type = type;
                setLife(25);
                setPower(3);
                break;
            case CRUSHER:
                speed = 0.5;
                this.type = type;
                setLife(40);
                setPower(7);
                break;
        }
    }

    public MonsterType getType(){
        return type;
    }

    public double getSpeed(){
        return speed;
    }

    public void move(){
        //this.setX(this.getX()-speed);
        this.getOnScreenCoord().setX(getOnScreenCoord().getX()-speed);
    }

    public void nextCell(){
        this.setX(this.getX()-1);
    }

    

}
