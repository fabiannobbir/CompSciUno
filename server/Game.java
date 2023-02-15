package server;

import client.card.Card;
import client.card.Player;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/*
* BUG:
*   when playing, everything will work smoothly. But at some point, the players will get 2 turns, or they have to hit "enter" twice
* maybe the reverse card gets turns out of sync?
* */

public class Game implements Serializable {
    private Card topCard; //the card on the top of a pile
    private int turnNumber; //the number of turns that have been played
    private boolean gameStarted;
    private int playerTurnIndex; //the index of the player array who's turn it is
    private ArrayList<Player> players = new ArrayList<Player>();
    private final int numPlayers;

    //TODO Implement

    public Game(int numPlayers){
        turnNumber = -1;
        playerTurnIndex = 0;
        topCard = new Card();
        gameStarted = false;
        this.numPlayers = numPlayers;
    }


    //this function plays a card from a specific player
    public void play(long playerID, int cardIndex){
        if(players.get(playerTurnIndex).getId() != playerID) return;
        if(cardIndex < 0){
            getPlayer(playerID).getHand().cards.add(new Card());
            updateTurnIndex(1);
            turnNumber++;
            return;
        }

        Card placedCard = getPlayer(playerID).getHand().cards.remove(cardIndex);
        topCard = placedCard;
        if(topCard.value > 9){
            specialCardPlay();
        }else {
            updateTurnIndex(1);
            turnNumber++;
        }
    }

    //does special updates to the game object
    private void specialCardPlay(){
        switch (topCard.value){
            case 10: //skip
                updateTurnIndex(2);
                break;

            case 11:
                //reverses the player array
                ArrayList<Player> newPlayers = new ArrayList<>();
                players.forEach(player -> {
                            newPlayers.add(0, player);
                        }
                );
                players = newPlayers;

                playerTurnIndex = players.size() -1 -playerTurnIndex;
                updateTurnIndex(1);
                break;

            case 12: // +2
                updateTurnIndex(1);
                for(int i = 0; i < 2; i++){
                    players.get(playerTurnIndex).getHand().cards.add(new Card());
                }
                updateTurnIndex(1);
                break;

            case 13: // +4
                updateTurnIndex(1);
                for(int i = 0; i < 4; i++){
                    players.get(playerTurnIndex).getHand().cards.add(new Card());
                }
                updateTurnIndex(1);
                break;

            case 14: //wild card
                //wild cards need no special action
                updateTurnIndex(1);
                break;
        }
        turnNumber++;
    }

    //returns a player from their id
    public Player getPlayer(long ID){
        for(Player player: players){
            if(ID == player.getId()){
                return player;
            }
        }
        return null;
    }

    //determines if a player has joined
    public boolean hasPlayer(long playerID){
        for(Player player: players){
            if(player.getId() == playerID){
                return true;
            }
        }
        return false;
    }

    //checks if a player has won
    public boolean hasPlayerWon(){
        for(Player player: players){
            if(player.getHand().cards.size() == 0) return true;
        }
        return false;
    }

    //adds a player to the player array
    public boolean addPlayer(Player player){
        if(getPlayer(player.getId()) != null) return false;
        if(gameStarted) return false;
        players.add(player);
        if(players.size() >= numPlayers){
            gameStarted = true;
            turnNumber = 0;
        }
        return true;
    }

    //increments the turn index
    private void updateTurnIndex(int step){
        playerTurnIndex = (playerTurnIndex + step) % players.size();
    }

    //getters and setters
    @Override
    public String toString(){
        return players.get(0).getUsername() + "'s game, with (" + players.size() + "/" + numPlayers + ")";
    }

    public Card getTopCard() {
        return topCard;
    }

    public boolean hasGameStarted(){
        return gameStarted;
    }

    public long playersTurn(){
        return players.get(playerTurnIndex).getId();
    }

    public int getTurnNumber(){
        return turnNumber;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }
}
