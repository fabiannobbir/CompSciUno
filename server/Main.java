package server;

public class Main {
    public static void main(String[] args) {
        try {
            Server server = new Server();
            API api = new API(server, 9090);
            api.start();
        }catch (Exception ignored){}
    }
}
