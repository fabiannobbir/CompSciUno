package Client;
import Client.card.*;
import java.util.Scanner;
import java.util.ArrayList;

class Main{
    public static ArrayList<Player> players = new ArrayList<Player>();
    public static Scanner reader = new Scanner(System.in);
    public static Deck deck = new Deck();
    public static boolean gameRun = true;
    public static void main(String[] args) {
        setup(1);

        while(gameRun) {

        }
    }




    public static void setup(int player_count) {
        String username;
        String id;
        int numCards;

        for(int i = 0; i < player_count; i++) {
            System.out.println("Username: ");
            username = reader.nextLine();
            System.out.println("id: ");
            id = reader.nextLine();
            System.out.println("numCards: ");
            numCards = reader.nextInt();

            players.add(new Player(username, id, numCards));
        }
        
        
        //System.out.println(players.get(0));
    }


    public static void play() {
        
    }


}

