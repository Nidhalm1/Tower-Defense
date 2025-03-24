package model;
import java.util.ArrayList;

import model.entity.Monster;
import model.entity.MonsterType;
import model.entity.Tower;

public class Map {
    private Tower [][] map;// MATRICE OU LES TOURES SONT PLACÉES
    private OnScreenCoord[][] mapPlacement;// MATRICE DES COORDONNÉES DES TOURES SUR L'ECRAN
    private ArrayList<ArrayList<Monster>> monstersLanes;;// LISTE DE LISTE OU CHAQUE LISTE EST UNE LANE


    //PERMET DE GENERER LA MATRICE DES COORDONNÉES
    public OnScreenCoord[][] towerCoord(int height, int width){
        OnScreenCoord[][] m = new OnScreenCoord[height][width];
        double firstX = 120; 
        double firstY = 130;
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                m[i][j] = new OnScreenCoord(firstX+(80*j), firstY+(100*i));
            }
        }
        return m;
    }

    public boolean canPlace(int x,int y){
        for(int i=0;i<mapPlacement.length;i++){
            for(int j=0;j<mapPlacement[i].length;j++){
                if(mapPlacement[i][j].inCell(x, y)){
                    return true;
                }
            }
        }
        return false;
    }

    public OnScreenCoord wherePlace(int x,int y){
        for(int i=0;i<mapPlacement.length;i++){
            for(int j=0;j<mapPlacement[i].length;j++){
                if(mapPlacement[i][j].inCell(x, y)){
                    return new OnScreenCoord(j,i);
                }
            }
        }
        return new OnScreenCoord(0, 0);
    }

    

    public OnScreenCoord[][] getMapPlacement() {
        return mapPlacement;
    }

    public ArrayList<ArrayList<Monster>> getMonstersLanes() {
        return monstersLanes;
    }

    public Map(){
        map = new Tower[5][10];
        monstersLanes = new ArrayList<>(10);

        for (int i = 0; i < 5; i++) {
            monstersLanes.add(new ArrayList<Monster>());
        }

        mapPlacement = towerCoord(5, 10);
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
        monster.getOnScreenCoord().setY((y+1)*100 + 50 );

    }

    public void afficherMap() {
        System.out.println("         1       2       3       4       5       6       7       8       9       10");
        
        for (int i = 0; i < map.length; i++) {
            System.out.println();
            System.out.println("     |-------+-------+-------+-------+-------+-------+-------+-------+-------+-------|");
            System.out.print(" " + Character.toString((char) (i + 65)) + "   |");
            
            
            for (int j = 0; j < map[i].length; j++) {
                Tower tower = map[i][j]; 
                int compteurM = 0;
                MonsterType type = null;
                
                if (!monstersLanes.get(i).isEmpty()) {
                    for (Monster monster : monstersLanes.get(i)) {
                        if (monster.getX() == j) {
                            compteurM++;
                            type = monster.getType();
                        }
                    }
                }
                if(tower !=null&&compteurM>=1){
                    String rep ="";
                    switch (tower.getType()) { 
                        case WALL:
                            rep="  W";
                            break;
                        case MAGICIAN:
                            rep="  X";
                            break;
                        default:
                            rep="  A"; 
                            break;
                    }   //"   A  C |"

                    if(compteurM==1 && type != null){
                        switch (type) {
                        case CRUSHER:
                            rep+="  C |";
                            break;
                        case SKELETON:
                            rep+="  S |";
                            break;
                        default:
                            rep+="  G |";
                            break;
                    }
                    } else if (compteurM > 1) {
                    rep+=(" M" + compteurM + " |");
                        
                    }
                    System.out.print(rep);
                }
                else if (tower != null) { 
                    switch (tower.getType()) { 
                        case WALL:
                            System.out.print("   W   |");
                            break;
                        case MAGICIAN:
                            System.out.print("   X  |");
                            break;
                        default:
                            System.out.print("   A   |"); 
                            break;
                    }
                } else if (compteurM > 1) {
                    System.out.print("   M" + compteurM + "  |");
                } else if (compteurM == 1 && type != null) {
                    switch (type) {
                        case CRUSHER:
                            System.out.print("   C   |");
                            break;
                        case SKELETON:
                            System.out.print("   S   |");
                            break;
                        default:
                            System.out.print("   G   |");
                            break;
                    }
                } else {
                    System.out.print("       |");
                }
            }
        }
        System.out.println();
    }
    
    
    


    public boolean canMove(Monster monster){//VARIABLE A CHANGER POUR MODE CONSOLE OU NON NORMALEMENT C'EST 0

        return ((map[(int)(monster.getY())][(int)(monster.getX() )]) == null); //verifie si le monstre peux avancer
    }


    //PERMET DE FAIRE AVANCER LES MONSTRES 
    public void move(){
        for (ArrayList<Monster> lane : monstersLanes){
            for(Monster monster : lane){
                //System.out.print(canMove(monster));
                if(canMove(monster)){
                    //System.out.print(monster.getType()+" "+monster.getSpeed());
                    monster.move();
                    if(monster.getX()!=0){
                    if(mapPlacement[(int)monster.getY()][(int)monster.getX()-1].// savoir si on manstre est entre dans la cellule suivante de la grille
                        inCell(monster.getOnScreenCoord().getX())){
                            System.out.println("NEXT CELL");
                            monster.nextCell();// mise a jour la cellulede monstre
                    }
                    }

                }
            }
        }
    }



    public void nextMoveConsole(){
        for(ArrayList<Monster> lane : monstersLanes){
            for(Monster monster : lane){
                if(canMove(monster)){
                    monster.nextCell();
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
