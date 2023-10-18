import java.util.ArrayList;
import java.util.List;

public record Card(Suit suit, String face, int rank) {
    public enum Suit {
        CLUB, DIAMOND, HEART, SPADE;

        public char getImages() {
            return (new char[]{9827, 9830, 9829, 9824})[this.ordinal()];
        }
    }

    @Override
    public String toString() {
        int index = face.equals("10") ? 2 : 1;
        String faceString = face.substring(0, index);
        return "%s%c(%d)".formatted(faceString, suit.getImages(), rank);
    }

    public static Card getNumericCard(Suit suit, int cardNumber) {
        if (cardNumber == 3) {
            return new Card(suit, String.valueOf(cardNumber), 10);
        } else if (cardNumber > 1 && cardNumber < 8) {
            return new Card(suit, String.valueOf(cardNumber), 0);
        }
        System.out.println("Invalid Numeric card selected");
        return null;
    }

    public static Card getFaceCard(Suit suit, char abbrev) {


        int charIndex = "JQKA".indexOf(abbrev);
        if (charIndex > -1) {
            return new Card(suit, "" + abbrev,
                    switch (charIndex) {
                        case 0 -> 2;
                        case 1 -> 3;
                        case 2 -> 4;
                        case 3 -> 11;
                        default -> 0;
                    }
            );
        }
        System.out.println("Invalid Face card selected");
        return null;
    }

    public static List<Card> getStandardDeck() {

        List<Card> deck = new ArrayList<>(40);

        //for each suit im going to create a set of numeric and face cards
        for (Suit suit : Suit.values()) {
            for (int i = 2; i <= 7; i++) {
                deck.add(getNumericCard(suit, i));
            }
            for (char c : new char[]{'J', 'Q', 'K', 'A'}) {
                deck.add(getFaceCard(suit, c));
            }
        }

        return deck;
    }

    public static void printDeck(List<Card> deck) {
        printDeck(deck, "Current Deck", 2);
    }

    public static void printDeck(List<Card> deck, String description, int rows) {
        System.out.println("-".repeat(20));
        if (description != null) {
            System.out.println(description);
        }
        int cardsInRow = deck.size() / rows;
        for (int i = 0; i < rows; i++) {
            int startIndex = i * cardsInRow;
            int endIndex = startIndex + cardsInRow;
            deck.subList(startIndex, endIndex).forEach(c -> System.out.print(c + " "));
            System.out.println();

        }


    }



}