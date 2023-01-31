package client.card;

import client.card.Hand;

import java.util.Random;

public class Player {
    private String username;
    private final long id;
    private Hand hand;
    
    public Player(String username, int numCards) {
        this.username = username;
        this.id = 5;
        hand = new Hand(numCards);
    }

    // private long genID(){
    //     Random random = new Random();
    //     return random.nextLong((long) (1E18), (long) (9E18));
    // }

    public String toString() {
        String print = "Player " + username + " has cards: ";
        for(int i = 0; i < hand.cards.size(); i++) {
            print += "(" + hand.cards.get(i).color + ", " + hand.cards.get(i).value + ") ";
        }

        return print;
    }

    public String getUsername() {
        return username;
    }

    // public long getId() {
    //     return id;
    // }

    public Hand getHand() {
        return hand;
    }
}

