import java.util.*;


public class Main {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Card> mazzo = Card.getStandardDeck();
        Card.printDeck(mazzo);
        Collections.shuffle(mazzo);
        Card.printDeck(mazzo);

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        //1 - pesca una carta che diventerà la briscola. Poi metti carta in fondo al mazzo
        // (deve sempre essere visibile ai giocatori... rappresentazione grafica?

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
        System.out.println("La Briscola è "+gioco.briscola);

        //3 - Giocatore 1 sceglie una carta casuale da giocare
        Card pl1Card = pickCard(player1.getCurrentHand(), player1);
        System.out.println("Player 1 chose the "+pl1Card+ " card.");

        //4- giocatore 2 sceglie carta in risposta (scanner user input)
        System.out.println("Scegli una carta (1°, 2°, 3°)");
        System.out.println(player2.getCurrentHand());
        int scelta = scanner.nextInt();
        Card pl2Card = pickCard(player2.getCurrentHand(), player2, scelta-1);
        System.out.println("Player 2 chose the "+pl2Card+ " card.");



        //5 - Viene valutata la carta vincitrice
        gioco.turnWinner(pl1Card, pl2Card);

        // Le carte vengono aggiunte al deck del giocatore vincente
        if ((gioco.turnWinner(pl1Card, pl2Card)) == 1) {
            player1.getPlayerDeck().add(pl1Card);
            player1.getPlayerDeck().add(pl2Card);
            player1.getCurrentHand().remove(pl1Card);
            player1.getCurrentHand().remove(pl2Card);
            player2.getCurrentHand().remove(pl2Card);
        }  else if ((gioco.turnWinner(pl1Card, pl2Card)) == 2) {
            player2.getPlayerDeck().add(pl1Card);
            player2.getPlayerDeck().add(pl2Card);
            player2.getCurrentHand().remove(pl1Card);
            player2.getCurrentHand().remove(pl2Card);
            player1.getCurrentHand().remove(pl1Card);

        }

        System.out.println("player1 deck = "+player1.getPlayerDeck());
        System.out.println("player2 deck = "+player2.getPlayerDeck());

        System.out.println("player1 hand = "+player1.getCurrentHand());
        System.out.println("player2 hand = "+player2.getCurrentHand());






        // il giocatore vicente diventa il primo che inizia il turno
        // vengono date una carta a testa -> implementare metodo

        //6 - si continua così fino a quano mazzo.size == 0.
        // Da implementare while loop 3-6
        // da implementare precedenza di turno (chi vince inizia) a partire dal secondo turno.




    }

    public static void primoTurno(List<Card> mazzo, Player player1, Player player2){
        player1.getCurrentHand().addAll(mazzo.subList(0,3));
        System.out.println("Player 1 cards = "+player1.getCurrentHand());
        player2.getCurrentHand().addAll(mazzo.subList(3,6));
        System.out.println("Player 2 cards = "+player2.getCurrentHand());
        mazzo.removeAll(mazzo.subList(0,6));
    }

    // TODO: 16/10/2023 IMPLEMENTARE!! 
    public static void giocaTurni(List<Card> mazzo, Player player1, Player player2){
        player1.getCurrentHand().addAll(mazzo.subList(0,3));
        System.out.println("Player 1 cards = "+player1.getCurrentHand());
        player2.getCurrentHand().addAll(mazzo.subList(3,6));
        System.out.println("Player 2 cards = "+player2.getCurrentHand());
        mazzo.removeAll(mazzo.subList(0,6));
    }

    public static Card pickCard(List<Card> currentHand, Player player1){
        Random random = new Random();
        int i = random.nextInt(0,2);
        return player1.getCurrentHand().get(i);
    }

    public static Card pickCard(List<Card> currentHand, Player player1, int position){
        return player1.getCurrentHand().get(position);
    }
}
