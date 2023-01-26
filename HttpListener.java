import java.io.IOError;
import java.io.IOException;
import java.net.InetSocketAddress;

import javax.xml.ws.spi.http.HttpExchange;
import javax.xml.ws.spi.http.HttpHandler;

import com.sun.net.httpserver.HttpServer;

public class HttpListener {
    //TODO pass in game object array so the listener can edit it.
    private HttpServer httpServer;
    public HttpListener(int serverIP, int port) throws Exception{
        httpServer = null;
        httpServer = HttpServer.create();
        httpServer.bind(new InetSocketAddress(port), 0);
        httpServer.createContext("/games", new MyhttpHandler());
        httpServer.createContext("/update", new MyhttpHandler());
        httpServer.createContext("/game", new MyhttpHandler());
        httpServer.createContext("/turn", new MyhttpHandler());
        httpServer.createContext("/join", new MyhttpHandler());
    }

    private static class MyhttpHandler extends HttpHandler{
        @Override
        public void handle(){
            //TODO, Implement
        }
    }
}
