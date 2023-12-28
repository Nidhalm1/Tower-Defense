import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;

public class Map {
    private Tower [][] map;// MATRICE OU LES TOURES SONT PLACÉES
    private ArrayList<ArrayList<Monster>> monstersLanes;;// LISTE DE LISTE OU CHAQUE LISTE EST UNE LANE

    public ArrayList<ArrayList<Monster>> getMonstersLanes() {
        return monstersLanes;
    }

    public Map(){
        map = new Tower[5][10];
        monstersLanes = new ArrayList<>(10);

        for (int i = 0; i < 5; i++) {
            monstersLanes.add(new ArrayList<Monster>());
        }
    }

    public Tower[][] getMap() {
        return map;
    }

    public Tower getTower(int x,int y){ //RETOURNE QUEL ENTITÉ CE TROUVE A CETTE POSITION
        return map[y][x];
    }

    public boolean placeTower(int x,int y,Tower tower){ //PLACE UNE TOURE
        if(getTower(x,y)==null){
            map[y][x] = tower;
            tower.setX(x);
            tower.setY(y);
            return true;
        }
        else System.out.println("On ne peut pas placer une toure ici !");return false;

    }

    public void addMonster(int x,int y, Monster monster){
        monstersLanes.get(y).add(monster);
        monster.setX(x);
        monster.setY(y);
    }

    public void afficherMap() {
        System.out.println("       1  2  3  4  5  6  7  8  9 10");
        System.out.print("-----------------------------------");

        for (int i = 0; i < map.length; i++) {
            System.out.println();
            System.out.print(" " + Character.toString((char) (i + 65)) + "  |  ");

            for (int j = 0; j < map[i].length; j++) {
                boolean towerPresent = !(map[i][j] ==null);
                boolean monsterPresent = false;
                if(!monstersLanes.get(i).isEmpty()) {
                    for (int x = 0;x<monstersLanes.get(i).size();x++){
                        if(monstersLanes.get(i).get(x).getX()==j){
                            monsterPresent = true;
                            break;
                        }
                    }
                }
                if (towerPresent) {
                    System.out.print("T  ");
                } else if (monsterPresent) {
                    System.out.print("M  ");
                } else {
                    System.out.print(".  ");
                }
            }
        }
        System.out.println();
    }

    public boolean canMove(Monster monster){//VARIABLE A CHANGER POUR MODE CONSOLE OU NON NORMALEMENT C'EST 0
        return ((map[(int)(monster.getY())][(int)(monster.getX() -1 )]) == null); //verifie si le monstre peux avancer
    }

    public void move(){
        for (ArrayList<Monster> lane : monstersLanes){
            for(Monster monster : lane){
                if(canMove(monster)){
                    monster.move();
                }
            }
        }
    }

    public void destroyTower(int x,int y){
        map[y][x] = null;
    }


    //FIRST LANE FROM TOWER
    // LE PLUS PETIT X PLUS GRAND QUE CELUI DE LA TOURE
    //MODIFIER !!!!
    public Monster firstInLane(ArrayList<Monster> lane) {//DONNE LE PREMIER MONSTRE D'UNE LANE NON VIDE!
        Monster monsterToshoot = lane.get(0);
        for (Monster monster : lane) {
            if (monster.getX() < monsterToshoot.getX()) {
                monsterToshoot = monster;
            }

        }
        return monsterToshoot;
    }


    // PERMET D'AVOIR LA LISTE DE TOUS LES MONSTRES SUR LESQUEL LA TOURE PEUT DEFENDRE
    //IN: un entier y donnant la lane, et une toure tower
    //OUT: Une liste de monstre etant dans la range de la  toure
    public ArrayList<Monster> inRangeLane(int y,Tower tower){
        ArrayList<Monster> inRange = new ArrayList<Monster>();// On creer une nouvelle liste de monstre vide
        for(Monster monster : monstersLanes.get(y)){// on Parcour les monstre de la lane y
            if(tower.inRange(monster)){// si le monstre est dans la range de la toure on l'ajoute
                inRange.add(monster);
            }
        }
        return inRange;
    }











}
