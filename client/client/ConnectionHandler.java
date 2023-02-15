package client.client;

import client.card.Player;
import server.Game;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

public class ConnectionHandler {
    private final Player player;
    private long gameID;
    private final String serverIP = "http://127.0.0.1:9090/";
    public ConnectionHandler(Player player) {
        this.player =  player;
        this.gameID = -1L;
    }

    // Retrieve the list of available games from the server
    public LinkedHashMap<Long, Game> getGames() throws IOException, ClassNotFoundException{
        URL url = new URL(serverIP + "games");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("GET");
        connection.setUseCaches(false);

        ObjectInputStream objectInputStream = new ObjectInputStream(connection.getInputStream());
        LinkedHashMap<Long, Game> games = (LinkedHashMap<Long, Game>) objectInputStream.readObject();
        objectInputStream.close();
        connection.disconnect();

        return games;
    }

    // Retrieve the current state of the game from the server
    public Game update() throws IOException, ClassNotFoundException {
        URL url = new URL(serverIP + "update");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("GET");
        connection.setUseCaches(false);
        connection.setRequestProperty("Client-ID", String.valueOf(player.getId()));
        connection.setRequestProperty("Game-ID", String.valueOf(gameID));

        ObjectInputStream objectInputStream = new ObjectInputStream(connection.getInputStream());
        Game game = (Game) objectInputStream.readObject();
        objectInputStream.close();
        connection.disconnect();
        return game;
    }

    // Create a new game on the server with the specified initial state
    public void makeGame(Game game) throws IOException, ClassNotFoundException {

        game.addPlayer(player);

        URL url = new URL(serverIP + "game");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(connection.getOutputStream());
        objectOutputStream.writeObject(game);

        InputStream inputStream = connection.getInputStream();
        byte[] gameIDBytes = new byte[connection.getContentLength()];
        inputStream.read(gameIDBytes);
        gameID = Long.parseLong
                (
                        new String(gameIDBytes, StandardCharsets.UTF_8)
                );
        connection.disconnect();
    }

    // Send the player's chosen card to the server to take their turn
    public void takeTurn(int cardIndex) throws IOException {
        URL url = new URL(serverIP + "turn");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setRequestProperty("Client-ID", String.valueOf(player.getId()));
        connection.setRequestProperty("Game-ID", String.valueOf(gameID));
        connection.setRequestProperty("Card-Index", String.valueOf(cardIndex));
        connection.getResponseCode(); //For some reason this is needed
        connection.disconnect();
    }

    // Join an existing game on the server with the specified ID
    public void joinGame(long id) throws IOException {
        URL url = new URL(serverIP + "join");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setRequestProperty("Game-ID", String.valueOf(id));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(connection.getOutputStream());
        objectOutputStream.writeObject(player);
        connection.getResponseCode(); //For some reason this is needed
        connection.disconnect();
        gameID = id;
    }

    // Calculate the size of an object in bytes using serialization
    public static int sizeof(Object obj) throws IOException {

        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);

        objectOutputStream.writeObject(obj);
        objectOutputStream.flush();
        objectOutputStream.close();

        return byteOutputStream.toByteArray().length;
    }
}
