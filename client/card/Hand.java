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
        ArrayList<String> combine = new ArrayList<String>();
        String print = "\n";
        Card card;
        double limit;
        int counter;
        int remaining;
        limit = cards.size()/20; //amount of cards that can fit on screen
        counter = (int)(limit); //if 32 cards, limit will be 1.6 and return 1 to assign to counter.
        remaining = cards.size()%20;

        if(cards.size() <= 20) {

 
            for(int i = 0; i < 4; i++) {
                combine.add("");
                for(int j = 0; j < cards.size(); j++) {
                    card = cards.get(j);
                    combine.set(i, combine.get(i) + (ascii(card.color, card.value)[i] + " "));
                }
                
            }


        } else {

            int iterator = 0;  
            for(int i = 0; i < counter; i++) {
                for(int k = 0; k < 4; k++) {
                    combine.add("");
                
                    for(int j = iterator; j < iterator + 20; j++) {
                        card = cards.get(j+iterator/20);
                        combine.set(k+iterator/20, combine.get(k+iterator/20) + (ascii(card.color, card.value)[i] + " "));
                    }
                }
                iterator += 20;
            }

            for(int i = 0; i < 4; i++) {
                combine.add("");
                for(int j = cards.size()-cards.size()%20; j < cards.size(); j++) {
                    card = cards.get(j);
                    combine.set(j+iterator/20, combine.get(j+iterator/20) + (ascii(card.color, card.value)[i] + " "));
                }
            }
        
        }
        for(int i = 0; i < combine.size(); i++) {
            print += combine.get(i) + "\n";
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
