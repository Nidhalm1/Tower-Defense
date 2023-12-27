import java.util.ArrayList;
import java.util.Random;

public class Game {

    private Player player;
    private Map map;

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public Game(){
        player = new Player();
        map = new Map();
    }

    public boolean gameLost() { // On verifie si des monstres ce trouvent sur la première colonne
        for (ArrayList<Monster> monstersLane : map.getMonstersLanes()) {
            for (Monster monster : monstersLane) {
                return monster.getX() == 0;
            }
        }
        return false;
    }


    public void defense(int y) {// Defense des tours sur les monstres sur une ligne
        Tower[] towerLane = map.getMap()[y];
        ArrayList<Monster> monsterLane = map.getMonstersLanes().get(y);

        if (!monsterLane.isEmpty()) {
            Monster monsterToshoot = map.firstInLane(monsterLane);

            for (int i = towerLane.length - 1; i >= 0; i--) {
                if (towerLane[i] != null) {
                    double nLife = monsterToshoot.getLife() - towerLane[i].getPower();
                    if (nLife <= 0) {
                        monsterLane.remove(monsterToshoot);// LE MONSTRE EST MORT
                        player.setMoney(player.getMoney()+20);

                        if (!monsterLane.isEmpty()) { // SI IL RESTE DES MONSTRE ON CONTINUE SUR LE SUIVANT
                            monsterToshoot = map.firstInLane(monsterLane);
                        } else break;
                    } else monsterToshoot.setLife(nLife);

                }
            }
        }
    }


    //MODIFIER POUR QUE PLUSIEUR ATTAQUE EN MEME TEMPS
    public void consoleAttack(int y){
        ArrayList<Monster> monsterLane = map.getMonstersLanes().get(y);
        if (!monsterLane.isEmpty()) {
            ArrayList<Monster> attackingMonsters = new ArrayList<Monster>();//on cherche tous les monstre en premiere position
            Monster min = map.firstInLane(monsterLane);
            attackingMonsters.add(min);
            for(Monster monster : monsterLane){
                if(monster!=min&&monster.getX()==min.getX())
                    attackingMonsters.add(monster);
            }

            for(Monster m : attackingMonsters){
                if(map.getMap()[y][(int)m.getX()-1]!=null){
                    double nLife = map.getMap()[y][(int)m.getX()-1].getLife() - m.getPower();
                    if(nLife<=0){
                        map.destroyTower((int)m.getX()-1,y);
                        // DANS DEFENSE PAS ATTACK
                    }else map.getMap()[y][(int)m.getX()-1].setLife(nLife);
                }
            }
        }

    }

    public void fight(){
        for(int i=0;i<map.getMap().length;i++){
            consoleAttack(i);
            defense(i);
        }
    }

    public boolean placeWithInput(String place){
        if(place.length()==2){
            if(place.charAt(0)>='A'&&place.charAt(0)<='E'&&((int)(place.charAt(1))-48)>0&&((int)(place.charAt(1))-48)<11){
                if(player.getMoney()>=20){
                    if(map.placeTower((int)(place.charAt(1))-49,(int)(place.charAt(0))-65,new Tower(20,4,20,2) ))
                        player.setMoney(player.getMoney()-20);
                    return true;
                }
                else System.out.println("Pas assez d'argent !"); return false;
            }
            else System.out.println("Coordonées pas sur la map !");return false;
        }
        else System.out.println("Veuiller rentrer des coordonées sous forme LettreChiffre ex:(B3)");return false;
    }

    public boolean response(String input){
        if(!input.equals("NEXT")){
            return placeWithInput(input);
        }
        else return true;
    }

    public void genrerateMonster(Monster monster){
            Random rd = new Random();
            int y = rd.nextInt(map.getMap().length);
            map.addMonster(map.getMap()[0].length,y,monster);
        }






}





