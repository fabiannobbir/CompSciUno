package client.card;
//import java.lang.Math;
import java.util.*;

public class Hand {
    public ArrayList<Card> cards = new ArrayList<Card>();
    Random ran = new Random();


    public Hand(int count) {
        for(int i = 0; i < count; i++) {
            cards.add(new Card());
        }
    }


    public void addToDeck(int index, Deck deck) {
        deck.cards.add(cards.get(index));
        cards.remove(index);
    }

    public String toString() {
        String print = "";
        for(int i = 0; i < cards.size(); i++) {
            print += "(" + cards.get(i).color + ", " + String.valueOf(cards.get(i).value) + ") ";
        }

        return print;
    }

}
