package model.entity;
public class Entity {

    private double life; // Vie de l'entité
    private double power; // Puissance des attaques
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setLife(double life) {
        this.life = life;
    }

    public void setPower(double x){
        power = x;
    }

    public double getLife() {
        return life;
    }


    public double getPower() {
        return power;
    }



    public Entity(int life,int power){
        this.life = life;
        this.power = power;
    }
}
