public class Tower extends Entity{

    private int cost; //PRIX DE LA TOURE

    private int attackDistance; // Jusqu'a ou la tour peut tirer

    public int getCost() {
        return cost;
    }

    public int getAttackDistance() {
        return attackDistance;
    }

    public Tower(int life, int power, int cost, int attackDistance){
        super(life,power);
        this.cost = cost;
        this.attackDistance = attackDistance;
    }

    public boolean inRange(Monster monster){
        return (monster.getX() - getX() )<= attackDistance && (monster.getX() - getX() ) > 0;
        // regarde si la distance entre le monstre et la tour est bien compris dans la distance d'attaque
        // si la distance est négative cela veut dire que le monstre est derrière la toure elle ne peut donc pas defendre dessus
    }

    
}
