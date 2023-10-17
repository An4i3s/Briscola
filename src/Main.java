import java.util.*;


public class Main {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Card> mazzo = Card.getStandardDeck();
       // Card.printDeck(mazzo);
        Collections.shuffle(mazzo);
        Card.printDeck(mazzo);

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");


        Briscola gioco = new Briscola(mazzo);
        Card primaCartaPescata = gioco.getMazzo().get(0);
        System.out.println("La carta pescata è "+primaCartaPescata);
        gioco.briscola = primaCartaPescata.suit();
        System.out.println("La Briscola è "+gioco.briscola);

        Collections.rotate(mazzo,-1);
        Card.printDeck(mazzo);


        //2 - Dai 3 carte al primo turno.
        // Forse creare metodo to pick a card che permette di prelevare una singola carta (o più) dal mazzo
        primoTurno(mazzo, player1,player2);
        Card.printDeck(mazzo);
        System.out.println("La Briscola2 è "+gioco.briscola);



        //5 - Viene valutata la carta vincitrice
        int sizeOfDeck = gioco.mazzo.size();
        int turni = 17;
        boolean flag = true;
        while (turni>0) {
            System.out.println();
            System.out.println("Turno n° "+(18-turni));
            System.out.println("*".repeat(20));
            int result = giocaTurni(mazzo, player1, player2, gioco.getBriscola(), flag);
            if (result == 1){
                flag = true;
            } else{
                flag = false;
            }
            turni--;
        }
        System.out.println();
        System.out.println("Ultima mano");
        int y = 3;
        while (y>0){
            int result2 = giocaUltimoTurno(player1,player2, gioco.getBriscola(), flag);
            if (result2 == 1){
                flag = true;
            } else{
                flag = false;
            }
            y--;
        }

        System.out.println("player1 deck = "+player1.getPlayerDeck());
        System.out.println("player2 deck = "+player2.getPlayerDeck());

        System.out.println("player1 hand = "+player1.getCurrentHand());
        System.out.println("player2 hand = "+player2.getCurrentHand());

        //Implemenatre "ultimo turno"

        //Vincitiore

        System.out.println("Punteggio totale Player 1 = "+player1.getTotalScore());
        System.out.println("Punteggio totale Player 2 = "+player2.getTotalScore());

        System.out.println("Vince" + (player1.getTotalScore()> player2.getTotalScore()?"Player 1":"Player 2"));

    }

    public static void primoTurno(List<Card> mazzo, Player player1, Player player2){
        player1.getCurrentHand().addAll(mazzo.subList(0,3));
        System.out.println("Player 1 cards = "+player1.getCurrentHand());
        player2.getCurrentHand().addAll(mazzo.subList(3,6));
        System.out.println("Player 2 cards = "+player2.getCurrentHand());
        mazzo.removeAll(mazzo.subList(0,6));
    }

    //deal card dovrebbe distribuire carte nell'ordine winner-loser
    public static void deal1Card(List<Card> mazzo, Player player1, Player player2){
        player1.getCurrentHand().add(mazzo.get(0));
        System.out.println("Player 1 cards = "+player1.getCurrentHand());
        player2.getCurrentHand().add(mazzo.get(1));
        System.out.println("Player 2 cards = "+player2.getCurrentHand());

    mazzo.removeAll(mazzo.subList(0,2));
    }

    public static int giocaUltimoTurno(Player player1, Player player2, Card.Suit briscola, boolean flag){
        Scanner scanner1 = new Scanner(System.in);
        //ultimo turno range pick a card minore
        Card pl1Card = pickCard(player1.getCurrentHand(), player1);
        Card pl2Card = null;

        if (!flag){
            System.out.println("Scegli una carta (1°, 2°, 3°)");
            System.out.println(player2.getCurrentHand());
            int scelta = scanner1.nextInt();
            while (scelta > player2.getCurrentHand().size()) {
                System.out.println("Scelta non valida. Scegliere un numero tra 1 e 3");
                scelta = scanner1.nextInt();
            }
            pl2Card = pickCard(player2.getCurrentHand(), player2, scelta-1);
            
            System.out.println("Player 2 chose the "+pl2Card+ " card.");
            System.out.println("Player 1 chose the "+pl1Card+ " card.");
        } else if (flag){
            System.out.println("Player 1 chose the "+pl1Card+ " card.");
            System.out.println("Scegli una carta (1°, 2°, 3°)");
            System.out.println(player2.getCurrentHand());

            int scelta = scanner1.nextInt();
            while (scelta > player2.getCurrentHand().size()){
                System.out.println("Scelta non valida. Scegliere un numero tra 1 e 3");
                scelta = scanner1.nextInt();
            }
            pl2Card = pickCard(player2.getCurrentHand(), player2, scelta-1);

            System.out.println("Player 2 chose the "+pl2Card+ " card.");
        }

        //attenzione a ordine di turn winner per fare iniziare il Player 2 quando vince una mano
        int result = turnWinner(pl1Card,pl2Card,briscola, (flag)?pl1Card.suit():pl2Card.suit());
        System.out.println("Result of turn winner is "+result);
        if (result == 1) {
            handleCards(player1,player2,pl1Card,pl2Card);
            //deal1Card(mazzo, player1,player2);
            return 1;
            //giocaP1 = true;
        }else {
            handleCards(player2,player1,pl2Card,pl1Card);
            //deal1Card(mazzo, player1,player2);
            return 2;
            //giocaPlayer1 = false;
        }

        //make gioca turni return an int?

    }


    public static int giocaTurni(List<Card> mazzo, Player player1, Player player2, Card.Suit briscola, boolean flag){
        Scanner scanner1 = new Scanner(System.in);
        Card pl1Card = pickCard(player1.getCurrentHand(), player1);
        Card pl2Card = null;

        if (!flag){
            System.out.println("Scegli una carta (1°, 2°, 3°)");
            System.out.println(player2.getCurrentHand());
            int scelta = scanner1.nextInt();
            while(scelta > player2.getCurrentHand().size()){
                System.out.println("Scelta non valida. Scegliere un numero tra 1 e 3");
                scelta = scanner1.nextInt();
            }
            pl2Card = pickCard(player2.getCurrentHand(), player2, scelta-1);
            System.out.println("Player 2 chose the "+pl2Card+ " card.");
            System.out.println("Player 1 chose the "+pl1Card+ " card.");
        } else if (flag){
            System.out.println("Player 1 chose the "+pl1Card+ " card.");
            System.out.println("Scegli una carta (1°, 2°, 3°)");
            System.out.println(player2.getCurrentHand());

            int scelta = scanner1.nextInt();
            while(scelta > player2.getCurrentHand().size()){
                System.out.println("Scelta non valida. Scegliere un numero tra 1 e 3");
                scelta = scanner1.nextInt();
            }

            pl2Card = pickCard(player2.getCurrentHand(), player2, scelta-1);

            System.out.println("Player 2 chose the "+pl2Card+ " card.");
            System.out.println("Player 1 chose the "+pl1Card+ " card.");
        }

        //attenzione a ordine di turn winner per fare iniziare il Player 2 quando vince una mano
        int result = turnWinner(pl1Card,pl2Card,briscola, (flag)?pl1Card.suit():pl2Card.suit());
        System.out.println("Result of turn winner is "+result);
        if (result == 1) {
            handleCards(player1,player2,pl1Card,pl2Card);
            deal1Card(mazzo, player1,player2);
            return 1;
            //giocaP1 = true;
        }else {
            handleCards(player2,player1,pl2Card,pl1Card);
            deal1Card(mazzo, player1,player2);
            return 2;
            //giocaPlayer1 = false;
        }

        //make gioca turni return an int?

    }

    public static Card pickCard(List<Card> currentHand, Player player1){
        if(player1.getCurrentHand().size() == 1){
           return player1.getCurrentHand().get(0);
        }
        Random random = new Random();
        int i = random.nextInt(0,currentHand.size()-1); //bound is exlusive lenght = 3
        return player1.getCurrentHand().get(i);
    }

    // TODO: 17/10/2023 pickCard dovrebbe implementare controlli su modalità scelta delle carte 
    public static Card pickCard(List<Card> currentHand, Player player2, int position){
        if (player2.getCurrentHand().size() == 1){
            return player2.getCurrentHand().get(0);
        }
        return player2.getCurrentHand().get(position);
    }

    public static int turnWinner(Card player1, Card player2, Card.Suit briscola, Card.Suit semeVincitore){

//devo implementare seme vincente, seme piu forte dopo la briscola.

        boolean cardHaveB =  isBriscola2(player1,briscola) && isBriscola2(player2, briscola);
        if (cardHaveB){
            if ((player1.rank() == 0) && (player2.rank() == 0)){
                return Integer.parseInt(player1.face()) > Integer.parseInt(player2.face()) ? 1:2;
            }
            if (player1.rank() > player2.rank()){
                System.out.println("Player 1 won");
                return 1;
            }else {
                System.out.println("Player 2 won");
                return 2;
            }
        }

        //now describe the case where only one Card has Briscola
        if (isBriscola2(player1,briscola)){
            System.out.println("Player 1 won");
            return 1;
        } else if (isBriscola2(player2,briscola)) {
            System.out.println("Player 2 won");
            return 2;
        }

        //Card.Suit suit = player1.suit();
        if (player1.suit() == player2.suit()){
            System.out.println("Both Cards have the same suit. Procede to compare rank and number to determine the winner");
            if ((player1.rank() == 0) && (player2.rank() == 0)){
                return Integer.parseInt(player1.face()) > Integer.parseInt(player2.face()) ? 1:2;
            }
            if (player1.rank() > player2.rank()){
                return 1;
            }else {
                return 2;
            }
        }

        if (player1.suit()== semeVincitore){
            return 1;
        } else if (player2.suit() == semeVincitore) {
            return 2;
        }


        System.out.println("Player 1 won - default");
        return 1;
    }

    public static boolean isBriscola2(Card card, Card.Suit semeBriscola){
        if (card.suit() == semeBriscola){
            return true;
        }
        return false;
    }

    public static void handleCards(Player winner, Player loser, Card winnerCard, Card loserCard){

        winner.getPlayerDeck().add(winnerCard);
        winner.getPlayerDeck().add(loserCard);
        winner.getCurrentHand().remove(winnerCard);
        loser.getCurrentHand().remove(loserCard);
    }

    public static boolean p1Won(int i){
        if (i ==1){
            return true;
        } else if (i == 2) {
            return false;
        }else {
            return false;
        }
    }

}
