package model;


import java.util.ArrayList;
import java.util.Random;

import model.entity.Monster;
import model.entity.MonsterType;
import model.entity.Projectil;
import model.entity.Tower;
import model.entity.TowerType;

public class Game {

    private Player player;
    private Map map;
    private boolean isMarathon;
    private boolean isConsole;
    private ArrayList<Projectil> currentProjectils;
    private Level[] levels = 
    {new Level(8),new Level(12),new Level(16),new Level(20),new Level(30)};
    //5 niveau avec un nombre differents de monstre a tuer
    private int level; //choisis un level de 0 à 4 dans levels

    public ArrayList<Projectil> getCurrentProjectils() {
        return currentProjectils;
    }

    public void setLevel(int x){
        if(x>=1&&x<=5){
            level = x-1;
        }
    }

    public int getLevel() {
        return level;
    }

    public Level[] getLevels() {
        return levels;
    }

    public void setMarathon(boolean isMarathon){
        this.isMarathon = isMarathon; 
    }

    public boolean getIsMarathon(){
        return isMarathon;
    }

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public Game(boolean isMarathon,boolean isConsole){
        player = new Player();
        map = new Map();
        this.isMarathon = isMarathon;
        currentProjectils = new ArrayList<>();
        this.isConsole = isConsole;
    }

    public boolean gameWin(){
        return levels[level].levelOver();// Si tous les monstre du niveau sont tue on a gagné
    }

    public boolean gameLost() { // On verifie si des monstres ce trouvent sur la première colonne
        for (ArrayList<Monster> monstersLane : map.getMonstersLanes()) {
            for (Monster monster : monstersLane) {
                if(monster.getX() <= 0 && map.getMap()[(int)monster.getY()][(int)monster.getX()]==null) 
                    return true;
            }
        }
        return false;
    }

    public void moveProjectils(){
        for(Projectil proj : currentProjectils){
            proj.move();
        }
    }


    public boolean monsterTouched(Monster monster){
        
        for(ArrayList<Monster> lane : map.getMonstersLanes()){
            for(Monster m : lane){
                if(m == monster){
                    System.out.println(m.getOnScreenCoord().getX());
                    for(Projectil proj : currentProjectils){
                        if(proj.getOnScreenCoord().getX()>m.getOnScreenCoord().getX()){
                            System.out.println("touché");
                            currentProjectils.remove(proj);
                            return true;
                        }
                    }
                }
            }
        }


        return false;
    }

    public void defense(int y,boolean shoot) {// Defense des tours sur les monstres sur une ligne
        Tower[] towerLane = map.getMap()[y];
        ArrayList<Monster> monsterLane = map.getMonstersLanes().get(y);

        if (!monsterLane.isEmpty()) {
            for (int i = towerLane.length - 1; i >= 0; i--) {
                if (towerLane[i] != null) {
                    ArrayList<Monster> monsterRangeLane = map.inRangeLane(y, towerLane[i]);//liste des monstres pouvant etre attaqué par la toure
                    if(!monsterRangeLane.isEmpty()){
                        Monster monsterToShoot = map.firstInLane(monsterRangeLane);// Le monstre a attaquer
                        double nLife = monsterToShoot.getLife() - towerLane[i].getPower();
                        //GERER PROJECTIL SEULEMENT POUR LE MODE GRAPHIQUE
                        boolean isTouched = true;
                        if(!isConsole){
                            isTouched = false;
                            if(shoot&&(towerLane[i].getType()!=TowerType.WALL)){// mur ne tire pas logique!
                                Projectil proj = new Projectil(map.getMapPlacement()[y][i].getX(),map.getMapPlacement()[y][i].getY(),towerLane[i].getType());
                                currentProjectils.add(proj);
                            }

                            isTouched = monsterTouched(monsterToShoot);
                        }

                        if (nLife <= 0&&isTouched) {
                            map.getMonstersLanes().get(y).remove(monsterToShoot);// LE MONSTRE EST MORT on (remove depuis map)
                            if(!isMarathon){
                                levels[level].levelKill();
                            }
                            player.setMoney(player.getMoney()+20);// ajout d'argent au joueur quand monstre tué
                        } else if(isTouched) monsterToShoot.setLife(nLife);
                    
                    }
                }
            }
        }
    }


    //MODIFIER POUR QUE PLUSIEUR ATTAQUE EN MEME TEMPS
    public void consoleAttack(int y,boolean attack){
        if(attack){
            ArrayList<Monster> monsterLane = map.getMonstersLanes().get(y);
            for(Monster monster : monsterLane){
                if(map.getMap()[y][(int)monster.getX()]!=null){
                    double nLife = map.getMap()[y][(int)monster.getX()].getLife() - monster.getPower();
                    if(nLife<=0){
                        map.destroyTower((int)monster.getX(), y);
                    }else map.getMap()[y][(int)monster.getX()].setLife(nLife);
                }
            }
        }

    }

    public void fight(boolean shoot,boolean attack){
        for(int i=0;i<map.getMap().length;i++){
            consoleAttack(i,attack);
            defense(i,shoot);
        }
    }

    public boolean placeTower(int x,int y,Tower tower){
        if(player.getMoney()>=tower.getCost()){
            if(map.placeTower(x, y, tower)){
                player.setMoney(player.getMoney()-tower.getCost());
                return true;}
        }
        return false;
    }

    public boolean placeWithInput(String place){
        if(place.length()==4){
            if(place.charAt(0)>='A'&&place.charAt(0)<='E'&&((int)(place.charAt(1))-48)>0&&((int)(place.charAt(1))-48)<11
            &&place.charAt(2)==' '&&((int)(place.charAt(3)-48)>0)&&((int)(place.charAt(3)-48)<4)){
                TowerType type = TowerType.WALL;
                switch ((int)(place.charAt(3)-48)) {
                    case 1 -> type = TowerType.ARCHER;
                    case 2 -> type = TowerType.WALL;
                    case 3 -> type = TowerType.MAGICIAN;
                    }
                    Tower tower = new Tower(type);
                if(player.getMoney()>=tower.getCost()){
                    if(map.placeTower((int)(place.charAt(1))-49,(int)(place.charAt(0))-65,tower )){
                        player.setMoney(player.getMoney()-tower.getCost());
                    return true;}
                    else return false;
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

    public void genrerateMonster(){
            
            MonsterType[] typeList = MonsterType.values();

            if(!isMarathon){
                if(levels[level].canGenerate()){

                    Random rd = new Random();
                    int y = rd.nextInt(map.getMap().length);
                    MonsterType type = typeList[rd.nextInt(typeList.length)];
                    Monster monster = new Monster(type);
                    System.out.println(y);
                    map.addMonster(map.getMap()[0].length-1,y,monster);
                    levels[level].levelIteration();
                }
            }
            else{
                Random rd = new Random();
                int y = rd.nextInt(map.getMap().length);
                
                MonsterType type = typeList[rd.nextInt(typeList.length)];
                Monster monster = new Monster(type);
                System.out.println(y);
                System.out.println(map.getMap()[0].length-1);
                map.addMonster(map.getMap()[0].length-1,y,monster);
            }
 
            

        }

    //Fonction de test pour verification
    public void verif(){
        System.out.println();
        for(ArrayList<Monster> lane  : map.getMonstersLanes()){
            for(Monster m : lane){
                System.out.print(" X: "+m.getX()+" ");
            }
        }
        System.out.println();
    }





}





