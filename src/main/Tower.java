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
}
