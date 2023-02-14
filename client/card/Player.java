package client.card;

import client.card.Hand;

import java.io.Serializable;
import java.util.Random;

public class Player implements Serializable {
    private String username;
    private final long id;
    private Hand hand;
    
    public Player(String username, int numCards) {
        this.username = username;
        this.id = genID();
        hand = new Hand(numCards);
    }

    private long genID(){
        Random random = new Random();
        return (long)((1E18) + (9E18 - 1E18) * random.nextDouble());
    }

    public String toString() {
        String print = "(" + username + " has " + hand.cards.size() + " cards)";
        return print;
    }

    public String getUsername() {
        return username;
    }

    public long getId() {
        return id;
    }

    public Hand getHand() {
        return hand;
    }
}

