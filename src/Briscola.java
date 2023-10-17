import com.sun.jdi.Value;

import java.util.*;

public class Briscola implements Comparator<Card>{

    Card.Suit briscola;
    List<Card> mazzo;

    public Briscola(List<Card> mazzo) {
        this.briscola = getBriscola();
        this.mazzo = mazzo;
    }

    public Card.Suit getBriscola() {
        return briscola;
    }

    public List<Card> getMazzo() {
        return mazzo;
    }

/*
    public int turnWinner(Card player1, Card player2){
        //x semplicare : valutare se presenza briscola nelle 2 carte (boolean isBriscola)
        // TODO: 16/10/2023 Condizioni da valutare
        //  1- Tutte e due le carte hanno seme Briscola OK
        //  2- Tutte e due le Carte hanno stesso seme = seme prima carta giocata
        //  3- Chi ha la briscola vince
        //  4- Per ultimo comparare il rank (carichi) e il numero se rank == 0

        boolean cardHaveB = isBriscola(player1) && isBriscola(player2);
        if (cardHaveB){
            if ((player1.rank() == 0) && (player2.rank() == 0)){
                return Integer.parseInt(player1.face()) > Integer.parseInt(player1.face()) ? 1:2;
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
        if (isBriscola(player1)){
            System.out.println("Player 1 won");
            return 1;
        } else if (isBriscola(player2)) {
            System.out.println("Player 2 won");
            return 2;
        }

        //Card.Suit suit = player1.suit();
        if (player1.suit() == player2.suit()){
            System.out.println("Both Cards have the same suit. Procede to compare rank and number to determine the winner");
            if ((player1.rank() == 0) && (player2.rank() == 0)){
                return Integer.parseInt(player1.face()) > Integer.parseInt(player1.face()) ? 1:2;
            }
            if (player1.rank() > player2.rank()){
                return 1;
            }else {
                return 2;
            }
        }


        System.out.println("Player 1 won");
        return 1; //Caso residuo.
                        // Non ci sono Briscole quindi di conseguenza vince il player2
                        // a meno che valore numerico e rank siano maggiori
    }
*/

    public boolean isBriscola(Card card){
        if (card.suit() == this.briscola){
            return true;
        }
        return false;
    }


    @Override
    public int compare(Card o1, Card o2) {
        return o1.rank() - o2.rank();
    }


    //implementare un metodo per comparare rank e numeri



    //ELIMINARE DEAL CARD?

  /*  public static List<Card> dealCard(List<Card> mazzo, int inizio, int fine){
        return mazzo.subList(inizio, fine);
    }

    public static Card dealCard(List<Card> mazzo){

        return mazzo.get(0);
    }*/


}
