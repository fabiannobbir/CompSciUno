package Client;
import Client.card.*;
import java.util.Scanner;
import java.util.ArrayList;

class Main{
    public static ArrayList<Player> players;
    public static Scanner reader;
    public static void main(String[] args) {
        setup();
    }

    public static void setup() {
        players = new ArrayList<Player>();
        players.add(new Player("ryan dood", "password", 7));
        reader = new Scanner(System.in);
        System.out.println(players.get(0));
    }
}

