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
        String[] combine = {"", "", "", ""}; //by default, using new String[4] will contain a null. by creating the string object, it won't print out null :)
        String print = "\n";
        Card card;
        for(int i = 0; i < combine.length; i++) {
            for(int j = 0; j < cards.size(); j++) {
                card = cards.get(j);
                combine[i] += ascii(card.color, card.value)[i] + " ";
            }
            
        }

        for(int i = 0; i < combine.length; i++) {
            print += combine[i] + "\n";
        }

        return print;
    }

    public String[] ascii(String color, int value) {

        if(value == 10) {
            return special_Cards_Array(color, "Skip");
        } else if (value == 11) {
            return special_Cards_Array(color, "R");
        } else if (value == 12) {
            return special_Cards_Array(color, "+2");
        } else if (value == 13) {
            return special_Cards_Array(color, "+4");
        } else if (value == 14) {
            return special_Cards_Array(color, "Wild");
        } else{
            return regular_Cards_Array(color, value);
        }


        //return regular_Cards_Array(color, value);

    }

    public String[] regular_Cards_Array(String color, int value) {
        String[] print = {"", "", "", ""}; //by default, using new String[4] will contain a null. by creating the string object, it won't print out null :)
        if(color.equals("Red")) {
            print[0] += "[RED    " + value + "]";
            print[1] += "[        ]";
            print[2] += "[        ]";
            print[3] += "[" + value + "    RED]";
        }
        if(color.equals("Blue")) {
            print[0] += "[BLUE   " + value + "]";
            print[1] += "[        ]";
            print[2] += "[        ]";
            print[3] += "[" + value + "   BLUE]";
        }
        if(color.equals("Yellow")) {
            print[0] += "[Yellow " + value + "]";
            print[1] += "[        ]";
            print[2] += "[        ]";
            print[3] += "[" + value + " Yellow]";
        }
        if(color.equals("Green")) {
            print[0] += "[Green  " + value + "]";
            print[1] += "[        ]";
            print[2] += "[        ]";
            print[3] += "[" + value + "  Green]";
        }
        return print;
    }

    public String[] special_Cards_Array(String color, String value) {
        String[] print = {"", "", "", ""}; //by default, using new String[4] will contain a null. by creating the string object, it won't print out null :)
        String spaces = "        "; // spaces between "[        ]"
        int size = spaces.length();
        int between = size-color.length()-value.length();
        int make_space = 0;

        //this is to help automatically expand the card size if the color and value are too long to fit in one row. Usually this occurs with Yellow + Reverse but is now represented with Yellow + R, so this problem won't occur anymore.
        if(value.equals("Wild")) {
            print[0] += "[Wild    ]";
            print[1] += "[        ]";
            print[2] += "[        ]";
            print[3] += "[    Wild]";
        } else {

            if(between < 0) {
                make_space = size + between*-1-5;
            }


            print[0] += "[" + color;
            for(int i = 0; i < between+make_space; i++) {
                print[0] += " ";
            }
            print[0] += value + "]";

            print[1] += "[";
            for(int i = 0; i < size+make_space; i++) {
                print[1] += " ";
            }
            print[1] += "]";

            print[2] += "[";
            for(int i = 0; i < size+make_space; i++) {
                print[2] += " ";
            }
            print[2] += "]";

            print[3] += "[" + value;
            for(int i = 0; i < between+make_space; i++) {
                print[3] += " ";
            }
            print[3] += color + "]";
        }

        return print;
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
