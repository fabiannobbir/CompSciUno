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
            print += ascii(card.color, card.value);

            
        }

        return print;
    }

    public String ascii(String color, int value) {
        String spaces = "                            ";
        int size = spaces.length();
        if(value == 10) {
            return "(" + color + ", " + "Skip" + ") ";
        } else if (value == 11) {
            return "(" + color + ", " + "Reverse" + ") ";
        } else if (value == 12) {
            return "(" + color + ", " + "+2" + ") ";
        } else if (value == 13) {
            return "(" + color + ", " + "+4" + ") ";
        } else if (value == 14) {
            return "(" + "Wild" + ") ";
        } else{
            return regular_cards(color, value) + "\n";
        }

    }


    public String regular_cards(String color, int value) {
        String print = "";
        if(color.equals("Red")) {
            print += "\n[RED    " + value + "]";
            print += "\n[        ]";
            print += "\n[        ]";
            print += "\n[" + value + "    RED]";
        }
        if(color.equals("Blue")) {
            print += "\n[BLUE   " + value + "]";
            print += "\n[        ]";
            print += "\n[        ]";
            print += "\n[        ]";
            print += "\n[" + value + "   BLUE]";
        }
        if(color.equals("Yellow")) {
            print += "\n[Yellow " + value + "]";
            print += "\n[        ]";
            print += "\n[        ]";
            print += "\n[        ]";
            print += "\n[" + value + " Yellow]";
        }
        if(color.equals("Green")) {
            print += "\n[Green  " + value + "]";
            print += "\n[        ]";
            print += "\n[        ]";
            print += "\n[        ]";
            print += "\n[" + value + "  Green]";
        }
        return print;
    }


}
