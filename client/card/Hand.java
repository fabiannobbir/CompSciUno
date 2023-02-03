package client.card;
//import java.lang.Math;
import java.io.Serializable;
import java.util.*;

public class Hand implements Serializable {
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
        Card card;
        for(int i = 0; i < cards.size(); i++) {
            card = cards.get(i);
            if(card.value == 10) {
                print += "(" + card.color + ", " + "Skip" + ") ";
            } else if (card.value == 11) {
                print += "(" + card.color + ", " + "Reverse" + ") ";
            } else if (card.value == 12) {
                print += "(" + card.color + ", " + "+2" + ") ";
            } else if (card.value == 13) {
                print += "(" + card.color + ", " + "+4" + ") ";
            } else if (card.value == 14) {
                print += "(" + "Wild" + ") ";
            } else{
                print += "(" + card.color + ", " + String.valueOf(card.value) + ") ";
            }
            


            
        }

        return print;
    }

}
