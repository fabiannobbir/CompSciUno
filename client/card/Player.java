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
        this.id = 123412341324L;//genID();
        hand = new Hand(numCards);
    }

    private long genID(){
        Random random = new Random();
        return (long)((1E18) + (9E18 - 1E18) * random.nextDouble());
    }

    public String toString() {
        String print = "Player " + username + " has cards: ";
        print += hand.toString();

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

