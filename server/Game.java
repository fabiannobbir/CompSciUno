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
    private Card topCard;
    private int turnNumber;
    private boolean gameStarted;
    private int playerTurnIndex;
    private ArrayList<Player> players = new ArrayList<Player>();
    private int numPlayers;

    //TODO Implement

    public Game(int numPlayers){
        turnNumber = -1;
        playerTurnIndex = 0;
        topCard = new Card();
        gameStarted = false;
        this.numPlayers = numPlayers;
    }


    //this function plays
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

    public Player getPlayer(long ID){
        for(Player player: players){
            if(ID == player.getId()){
                return player;
            }
        }
        return null;
    }

    public boolean hasPlayer(long playerID){
        for(Player player: players){
            if(player.getId() == playerID){
                return true;
            }
        }
        return false;
    }

    public boolean hasPlayerWon(){
        for(Player player: players){
            if(player.getHand().cards.size() == 0) return true;
        }
        return false;
    }

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

    private void updateTurnIndex(int step){
        playerTurnIndex = (playerTurnIndex + step) % players.size();
    }

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
