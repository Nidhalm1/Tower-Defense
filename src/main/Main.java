import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Game game = new Game();
        int moneyCompt = 0; //permet de gagner après un certains nombre de tours

        while(!game.gameLost()){

            moneyCompt++;
            if(moneyCompt>=2){
                game.getPlayer().setMoney(game.getPlayer().getMoney()+10);
                moneyCompt = 0;
            }

            String spaceMoney = "          ";// AFFICHAGE
            for(int i=0;i<(String.valueOf(game.getPlayer().getMoney())).length();i++){
                spaceMoney = spaceMoney.substring(0, spaceMoney.length() - 1);
            }

            game.fight();
            game.getMap().move();



            System.out.println("===================================");
            System.out.println("#                                 #");
            System.out.println("#     MONEY: "+game.getPlayer().getMoney()+"$"+spaceMoney+"          #");
            System.out.println("#                                 #");
            System.out.println("===================================");

            game.genrerateMonster(new Monster());

            game.getMap().afficherMap();// ON AFFICHE LA MAP AU JOUEUR

            Scanner myObj = new Scanner(System.in);

            boolean faireChoix = false;


            // FAIT EN BOUCLE SI LE CHOIX EST PAS BON
            while(!faireChoix){
                System.out.println("Placer une toure (ex: B3) ou prochain tour (NEXT) : ");
                String response = myObj.nextLine();
                faireChoix = game.response(response);
            }





        }
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



    }
}