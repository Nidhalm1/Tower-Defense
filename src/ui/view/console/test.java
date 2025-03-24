package ui.view.console;

import model.*;
import model.entity.*;

public class test{
    
    public static void main(String[] args) {

        Monster f = new Monster(MonsterType.GOBELIN);
        System.out.println(f.getSpeed());

        Map m = new Map();
        OnScreenCoord[][] cm = m.towerCoord(5, 10);
        for(OnScreenCoord[] l : cm){
            for(OnScreenCoord o : l){
                System.out.println(o);
            }
        }
        System.out.println(cm);
    }


}