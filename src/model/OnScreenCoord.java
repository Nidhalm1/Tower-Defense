package model;

public class OnScreenCoord {

    private double x;
    private double y;

    
    public double getX(){
        return  x;
    }

    public double getY(){
        return y;
    }


    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public OnScreenCoord(double x,double y){
        this.x=x;this.y=y;
    }

    @Override
    public String toString() {
       
        return "OnScreen[ "+" x: "+x+" y: "+y+" ]";
    }
//cellule de 
    public boolean inCell(double x,double y){
        return (x > this.x-10 && x < this.x +50 && y > this.y-10 && y < this.y+80);
    }

    public boolean inCell(double x){
        return(x <= this.x + 43);
    }

}