package model.entity;

public class Tower extends Entity{

    private TowerType type;

    private int cost; //PRIX DE LA TOURE

    private int attackDistance; // Jusqu'a ou la tour peut tirer

    public int getCost() {
        return cost;
    }

    public TowerType getType() {
        return type;
    }

    public int getAttackDistance() {
        return attackDistance;
    }

    public Tower(TowerType type){
        //new Tower(20,4,20,10)
        super(20,4);
        switch (type) {
            case MAGICIAN:
                this.attackDistance = 6;
                this.cost = 30;
                setLife(25);
                setPower(6);
                this.type = TowerType.MAGICIAN;
                break;
            
            case WALL:
                this.attackDistance = 0;
                this.cost = 5;
                setLife(10);
                setPower(0);
                this.type = TowerType.WALL;
                break;
            case ARCHER:
                this.attackDistance = 10;
                this.cost = 20; 
                setLife(20);
                setPower(4);
                this.type = TowerType.ARCHER;              
                break;
        }
    }

    public boolean inRange(Monster monster){
        return (monster.getX() - getX() )<= attackDistance && (monster.getX() - getX() ) >= 0;
        // regarde si la distance entre le monstre et la tour est bien compris dans la distance d'attaque
        // si la distance est négative cela veut dire que le monstre est derrière la toure elle ne peut donc pas defendre dessus
    }

    
}
