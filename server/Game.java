package server;

import client.card.Card;
import client.card.Player;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Game implements Serializable {
    private Card topCard;
    private int turnNumber;
    private boolean gameStarted;
    private int playerTurnIndex;
    private ArrayList<Player> players = new ArrayList<Player>();
    private int numPlayers;

    //TODO Implement

    public Game(int numPlayers){
        turnNumber = 0;
        playerTurnIndex = 0;
        topCard = new Card();
        gameStarted = false;
        this.numPlayers = numPlayers;
    }


    //this function plays
    public void play(long playerID, int cardIndex){
        if(cardIndex < 0){
            getPlayer(playerID).getHand().cards.add(new Card());
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
                playerTurnIndex += 2;
                if(playerTurnIndex >= players.size()){
                    playerTurnIndex -= players.size();
                }
                break;

            case 11: //reverse
                // [1,2,3,4]
                //ind: 1
                // 2 places a reverse
                //desired outcome: [4,3,2,1]
                //ind: 2

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
                updateTurnIndex(2);
                turnNumber++;
                for(int i = 0; i < 2; i++){
                    players.get(playerTurnIndex).getHand().cards.add(new Card());
                }
                break;

            case 13: // +4
                updateTurnIndex(2);
                turnNumber++;
                for(int i = 0; i < 4; i++){
                    players.get(playerTurnIndex).getHand().cards.add(new Card());
                }
                break;

            case 14: //wild card
                //wild cards need no special action
                break;
        }
        turnNumber++;
    }

    private Player getPlayer(long ID){
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

    public boolean addPlayer(Player player){
        if(getPlayer(player.getId()) != null) return false;
        if(gameStarted) return false;
        players.add(player);
        if(players.size() >= numPlayers){
            gameStarted = true;
        }
        return true;
    }

    private void updateTurnIndex(int step){
        playerTurnIndex += step;
        if(playerTurnIndex >= players.size()){
            playerTurnIndex -= players.size();
        }
    }

    public String toString(){
        return Arrays.deepToString(players.toArray());
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
}
