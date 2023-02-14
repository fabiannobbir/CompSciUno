package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

public class Server {
    private volatile LinkedHashMap<Long, Game> games;
    private boolean isRunning;

    public Server(){
        games = new LinkedHashMap<>();
    }

    public long addGame(Game game){
        long gameID = genID();
        games.put(gameID, game);
        System.out.println("Game \"" + game.getPlayers().get(0).getUsername() + "\" with ID " + gameID + " added.");
        return gameID;
    }

    public LinkedHashMap<Long, Game> getGames(){
        return games;
    }

    public Game getGame(long gameID){
        return games.get(gameID);
    }

    public void takeTurn(long gameID, long playerID, int cardIndex){
        games.get(gameID).play(playerID, cardIndex);
    }

    private long genID(){
        Random random = new Random();
        return (long)((1E18) + (9E18 - 1E18) * random.nextDouble());
    }
}
