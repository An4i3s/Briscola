import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<Card> currentHand;
    private List<Card> playerDeck;

    public Player(String name) {
        this.name = name;
        this.currentHand = new ArrayList<>(3);
        this.playerDeck = new ArrayList<>();
    }

    public List<Card> getCurrentHand() {
        return currentHand;
    }

    public List<Card> getPlayerDeck() {
        return playerDeck;
    }
}
