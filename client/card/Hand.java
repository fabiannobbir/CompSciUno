package Client.card;
//import java.lang.Math;
import java.util.*;

public class Hand {
    ArrayList<Card> cards = new ArrayList<Card>();
    Random ran = new Random();


    public Hand(int count) {
        for(int i = 0; i < count; i++) {
            cards.add(new Card(ran.nextInt(4)+1, ran.nextInt(9)+1));
        }
    }


    public void addToDeck(int index, Deck deck) {
        deck.cards.add(cards.get(index));
        cards.remove(index);
    }
}
