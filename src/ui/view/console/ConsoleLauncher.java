package ui.view.console;
import java.util.Scanner;

import model.Game;
import model.entity.Monster;

public class ConsoleLauncher {


    public static void main(String[] args) {
        Game game = new Game(false,true);
        int moneyCompt = 0; //permet de gagner de l'argent après un certains nombre de tours
        int rounds = 0;// combien de tour son joué 
        // plus les tour augmente plus on génère de monstres
        int numberGeneration = 1;// nombre de monstre généré dans le tour

        boolean modeNonChoisi = true;
        Scanner myObj = new Scanner(System.in);

        boolean running = !game.gameLost();
        
        System.out.println("\n" +
                "\n" +
                "$$$$$$$$\\                                                $$$$$$$\\             $$$$$$\\                                               \n" +
                "\\__$$  __|                                               $$  __$$\\           $$  __$$\\                                              \n" +
                "   $$ | $$$$$$\\  $$\\  $$\\  $$\\  $$$$$$\\   $$$$$$\\        $$ |  $$ | $$$$$$\\  $$ /  \\__|$$$$$$\\  $$$$$$$\\   $$$$$$$\\  $$$$$$\\        \n" +
                "   $$ |$$  __$$\\ $$ | $$ | $$ |$$  __$$\\ $$  __$$\\       $$ |  $$ |$$  __$$\\ $$$$\\    $$  __$$\\ $$  __$$\\ $$  _____|$$  __$$\\       \n" +
                "   $$ |$$ /  $$ |$$ | $$ | $$ |$$$$$$$$ |$$ |  \\__|      $$ |  $$ |$$$$$$$$ |$$  _|   $$$$$$$$ |$$ |  $$ |\\$$$$$$\\  $$$$$$$$ |      \n" +
                "   $$ |$$ |  $$ |$$ | $$ | $$ |$$   ____|$$ |            $$ |  $$ |$$   ____|$$ |     $$   ____|$$ |  $$ | \\____$$\\ $$   ____|      \n" +
                "   $$ |\\$$$$$$  |\\$$$$$\\$$$$  |\\$$$$$$$\\ $$ |            $$$$$$$  |\\$$$$$$$\\ $$ |     \\$$$$$$$\\ $$ |  $$ |$$$$$$$  |\\$$$$$$$\\       \n" +
                "   \\__| \\______/  \\_____\\____/  \\_______|\\__|            \\_______/  \\_______|\\__|      \\_______|\\__|  \\__|\\_______/  \\_______|      \n" +
                "                                                                                                                                    \n" +
                "                                                                                                                                    \n" +
                "                                                                                                                                    \n" +
                "\n");

        while (modeNonChoisi) {
            System.out.println("Jouer au mode Marathon ? (OUI/NON):");
            String marathon = myObj.nextLine();
            if(marathon.equals("OUI")){
                game.setMarathon(true);
                modeNonChoisi = false;
            }
            else if(marathon.equals("EXIT")){
                running = false;
                break;
            }
            else if(marathon.equals("NON")){
                
                boolean levelNonChoisi = true;
                while (levelNonChoisi) {
                    System.out.println("Choisir un niveau entre 1 et 5");
                    System.out.println("1(8 monstres), 2(12 monstres), 3(16 monstres), 4(20 monstres), 5(30 monstres)");
                    String level = myObj.nextLine();
                    if(level.length()>1||(level.charAt(0)<=48)||(level.charAt(0)>=54)){
                        System.out.println("TAPEZ 1/2/3/4/5 ! ");
                    }
                    else{
                        game.setLevel(Integer.valueOf(level));
                        levelNonChoisi = false;
                        modeNonChoisi = false;
                    }                   
                }
            }
            else System.out.println("Écrire OUI ou NON pour choisir !");
        }

       

        


        while(running){

            
            if(!game.getIsMarathon()){
                if(game.getLevels()[game.getLevel()].levelOver()){
                    System.out.println();
                    System.out.println("$$\\     $$\\  $$$$$$\\  $$\\   $$\\       $$\\      $$\\ $$$$$$\\ $$\\   $$\\ \n" +
                "\\$$\\   $$  |$$  __$$\\ $$ |  $$ |      $$ | $\\  $$ |\\_$$  _|$$$\\  $$ |\n" +
                " \\$$\\ $$  / $$ /  $$ |$$ |  $$ |      $$ |$$$\\ $$ |  $$ |  $$$$\\ $$ |\n" +
                "  \\$$$$  /  $$ |  $$ |$$ |  $$ |      $$ $$ $$\\$$ |  $$ |  $$ $$\\$$ |\n" +
                "   \\$$  /   $$ |  $$ |$$ |  $$ |      $$$$  _$$$$ |  $$ |  $$ \\$$$$ |\n" +
                "    $$ |    $$ |  $$ |$$ |  $$ |      $$$  / \\$$$ |  $$ |  $$ |\\$$$ |\n" +
                "    $$ |     $$$$$$  |\\$$$$$$  |      $$  /   \\$$ |$$$$$$\\ $$ | \\$$ |\n" +
                "    \\__|     \\______/  \\______/       \\__/     \\__|\\______|\\__|  \\__|");
                    break;
                }
            }
            
            moneyCompt++;
            if(moneyCompt>=3){
                game.getPlayer().setMoney(game.getPlayer().getMoney()+10);
                moneyCompt = 0;
            }

            String spaceMoney = "          ";// AFFICHAGE
            for(int i=0;i<(String.valueOf(game.getPlayer().getMoney())).length();i++){
                spaceMoney = spaceMoney.substring(0, spaceMoney.length() - 1);
            }
            

            game.fight(true,true);
            game.getMap().nextMoveConsole();
            //game.verif();


            System.out.println("===================================");
            System.out.println("#                                 #");
            System.out.println("#     MONEY: "+game.getPlayer().getMoney()+"$"+spaceMoney+"          #");
            System.out.println("#                                 #");
            System.out.println("===================================");
            
            for(int i =0; i<numberGeneration;i++){
                game.genrerateMonster();
            }

            rounds++;
            switch (rounds) {
                case 4:
                    numberGeneration = 2;
                    break;
                case 8:
                    numberGeneration = 3;
                    break;
                case 12:
                    numberGeneration = 4;
                    break;
            }
            

            game.getMap().afficherMap();// ON AFFICHE LA MAP AU JOUEUR

            

            boolean faireChoix = false;

            if(game.gameLost()){
                System.out.println();
        System.out.println(" $$$$$$\\   $$$$$$\\  $$\\      $$\\ $$$$$$$$\\        $$$$$$\\  $$\\    $$\\ $$$$$$$$\\ $$$$$$$\\  \n" +
                "$$  __$$\\ $$  __$$\\ $$$\\    $$$ |$$  _____|      $$  __$$\\ $$ |   $$ |$$  _____|$$  __$$\\ \n" +
                "$$ /  \\__|$$ /  $$ |$$$$\\  $$$$ |$$ |            $$ /  $$ |$$ |   $$ |$$ |      $$ |  $$ |\n" +
                "$$ |$$$$\\ $$$$$$$$ |$$\\$$\\$$ $$ |$$$$$\\          $$ |  $$ |\\$$\\  $$  |$$$$$\\    $$$$$$$  |\n" +
                "$$ |\\_$$ |$$  __$$ |$$ \\$$$  $$ |$$  __|         $$ |  $$ | \\$$\\$$  / $$  __|   $$  __$$< \n" +
                "$$ |  $$ |$$ |  $$ |$$ |\\$  /$$ |$$ |            $$ |  $$ |  \\$$$  /  $$ |      $$ |  $$ |\n" +
                "\\$$$$$$  |$$ |  $$ |$$ | \\_/ $$ |$$$$$$$$\\        $$$$$$  |   \\$  /   $$$$$$$$\\ $$ |  $$ |\n" +
                " \\______/ \\__|  \\__|\\__|     \\__|\\________|       \\______/     \\_/    \\________|\\__|  \\__|\n" +
                "                                                                                          \n" +
                "                                                                                          \n" +
                "                                                                                          ");
                break;
            }


            // FAIT EN BOUCLE SI LE CHOIX EST PAS BON
            while(!faireChoix){
                System.out.println("Placer une toure (ex: B3 1)  1:Archer 2:Mur 3:Magicien");
                System.out.println("Prochain tour (NEXT) :");
                String response = myObj.nextLine();
                if(response.equals("EXIT")){
                running = false;
                break;
            }
                faireChoix = game.response(response);
            }

            



        }




    }
}