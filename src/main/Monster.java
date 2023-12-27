public class Monster extends Entity{

    double speed;
    // Vitesse, pas vraiment implementable sur le terminal
    public Monster(){
        super(20,5);
        speed = 1;
    }



    public void move(){

        this.setX(this.getX()-speed);
    }

}
