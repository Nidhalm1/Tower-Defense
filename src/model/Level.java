package model;
public class Level {

    private final int  numberOfMonsters;
    private int monstersKill;
    private int monstersToGenerate;

    public int getMonsterKill(){
        return this.monstersKill;
    }

    public int getNumberOfMonsters(){
        return this.numberOfMonsters;
    }

    public int getMonstersToGenerate(){
        return this.monstersToGenerate;
    }

    public Level(int monsters){
        this.numberOfMonsters = monsters;
        this.monstersToGenerate = monsters;
        this.monstersKill = 0;
    }

    public void levelIteration(){
        monstersToGenerate -- ;
    }

    public boolean canGenerate(){
        return monstersToGenerate > 0;
    }

    public void levelKill(){
        monstersKill ++;
    }

    public boolean levelOver(){
        return monstersKill >= numberOfMonsters;
    }


}