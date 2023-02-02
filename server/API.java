package server;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import client.card.Player;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class API {
    //TODO pass in game object array so the listener can edit it.
    private final HttpServer httpServer;
    private final Server server;

    public API(Server server, int port) throws Exception {
        this.server = server;

        httpServer = HttpServer.create();
        httpServer.bind(new InetSocketAddress(port), 0);

        /*-- Im sure there is a nicer way to do this, TODO, clean up if possible --*/
        httpServer.createContext("/games", new unoHttpHandler(server));
        httpServer.createContext("/update", new unoHttpHandler(server));
        httpServer.createContext("/game", new unoHttpHandler(server));
        httpServer.createContext("/turn", new unoHttpHandler(server));
        httpServer.createContext("/join", new unoHttpHandler(server));
        httpServer.setExecutor(null);
    }

    public void start(){
        httpServer.start();
        System.out.println("Server Started!");
    }

    protected Server getServer(){
        return server;
    }

    private static class unoHttpHandler implements HttpHandler {
        private Server server;
        public unoHttpHandler(Server server){
            this.server = server;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            System.out.println("Connection! " + exchange.getRequestURI().toString());
            ObjectOutputStream objectOutputStream;
            ObjectInputStream objectInputStream;
            Game game;
            long gameID;
            long clientID;
            switch (exchange.getRequestURI().toString().replace("/", "")){
                case("games"):
                    exchange.sendResponseHeaders(200, sizeof(server.getGames()));
                    objectOutputStream = new ObjectOutputStream(exchange.getResponseBody());
                    objectOutputStream.writeObject(server.getGames());
                    exchange.close();
                    objectOutputStream.close();
                    break;

                case("update"):
                    clientID = Long.parseLong(exchange.getRequestHeaders().get("Client-ID").get(0));
                    gameID = Long.parseLong(exchange.getRequestHeaders().get("Game-ID").get(0));
                    game = server.getGame(gameID);

                    if(game == null){
                        exchange.sendResponseHeaders(404, 0);
                        break;
                    }
                    if(!server.getGame(gameID).hasPlayer(clientID)){
                        exchange.sendResponseHeaders(403,0);
                        break;
                    }

                    exchange.sendResponseHeaders(200,sizeof(game));
                    objectOutputStream = new ObjectOutputStream(exchange.getResponseBody());
                    objectOutputStream.writeObject(game);
                    exchange.close();
                    objectOutputStream.close();
                    break;

                case("game"):
                    objectInputStream = new ObjectInputStream(exchange.getRequestBody());
                    try {
                        game = (Game) objectInputStream.readObject();
                        gameID = server.addGame(game);

                        byte[] response  = String.valueOf(gameID).getBytes(StandardCharsets.UTF_8);
                        exchange.sendResponseHeaders(200, response.length);
                        exchange.getResponseBody().write(response);

                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case("turn"):
                    clientID = Long.parseLong(exchange.getRequestHeaders().get("Client-ID").get(0));
                    gameID = Long.parseLong(exchange.getRequestHeaders().get("Game-ID").get(0));
                    int cardIndex = Integer.parseInt(exchange.getRequestHeaders().get("Card-Index").get(0));
                    server.getGame(gameID).play(clientID, cardIndex);
                    break;

                case("join"):
                    gameID = Long.parseLong(exchange.getRequestHeaders().get("Game-ID").get(0));

                    objectInputStream = new ObjectInputStream(exchange.getRequestBody());
                    Player player;
                    try {
                        player = (Player) objectInputStream.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        break;
                    }
                    boolean addPlayerSuccess = server.getGame(gameID).addPlayer(player);
                    System.out.println(player.toString() + " is joining " + gameID);

                    if(addPlayerSuccess){
                        exchange.sendResponseHeaders(200,0);
                    }else{
                        exchange.sendResponseHeaders(409,0);
                    }
                    break;
            }
        }

        public static int sizeof(Object obj) throws IOException {

            ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);

            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            objectOutputStream.close();

            return byteOutputStream.toByteArray().length;
        }
    }
}
