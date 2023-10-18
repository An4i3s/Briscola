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


    @Override
    public int compare(Card o1, Card o2) {
        return o1.rank() - o2.rank();
    }


}
