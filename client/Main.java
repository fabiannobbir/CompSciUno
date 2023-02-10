package client;

import client.card.*;
import java.util.Scanner;
import java.util.ArrayList;

//TODO: create reverse cards, +2 and +4, wild cards.
//TODO: create computer bot.

class Main{
    public static ArrayList<Player> players = new ArrayList<Player>();
    public static Scanner reader = new Scanner(System.in);
    public static Deck deck = new Deck();
    //public static Card topCard = new Card();
    public static boolean gameRun = true;
    public static String winner = "";
    public static int turn = 0;


  
    public static void main(String[] args) {
        setup(2);
        

        play();

        System.out.println(winner + " Wins");
    }


    

    public static void setup(int player_count) {
        String username;
        //String id;
        int numCards;

        for(int i = 0; i < player_count; i++) {
            System.out.println("Username: ");
            username = reader.nextLine();
            System.out.println("numCards: ");
            numCards = reader.nextInt();

            players.add(new Player(username, numCards));
            reader.nextLine(); //this is to clear the input buffer  https://www.freecodecamp.org/news/java-scanner-nextline-call-gets-skipped-solved/
        }
        
        
        //System.out.println(players.get(0));
    }


    public static void play() {
      //int index;
      while(gameRun) {
        
        prompt(turn%players.size());
        turn++;

        System.out.println("________________________________________________________");
          
        
      }
    }


    public static void prompt(int playerIndex) {
      Player player = players.get(playerIndex);
      System.out.println("These are the other players cards: " + otherPlayers(player));
      
      System.out.println("The top card in the deck is: " + deck.printTopCard());
      System.out.println("This is " + player.getUsername() + " turn.");
      System.out.println("These are your cards: " + player.getHand());
      
      
      if(anyMatching(player)) {
        int index;
        System.out.println("What card would you like to put down?");
        index = reader.nextInt();
        reader.nextLine(); //input buffer
        
        if(checkCard(player, index)) {
          player.getHand().addToDeck(index, deck);
        }else {
          System.out.println("This is not a valid card. Try again.");
          prompt(playerIndex);
        }
      } else {
        System.out.println("There are no cards able to be placed. Enter any key to add a card to your deck.");
        //System.out.println("do not skip this line");
        //BUG: skips this prompt?!?! SOLVED https://www.freecodecamp.org/news/java-scanner-nextline-call-gets-skipped-solved/ added an extra nextline in the setup function
        reader.nextLine();
        player.getHand().cards.add(new Card());
        prompt(playerIndex);
      }

      checkWin();
      
    }

    public static boolean anyMatching(Player player) {
      //if there are no possible cards to be placed down in comparision with the top of the deck, then this will return false.
      ArrayList<Card> playerCards = player.getHand().cards;
      for(int i = 0; i < playerCards.size(); i++) {
        if(playerCards.get(i).value == deck.topCard().value || playerCards.get(i).color.equals(deck.topCard().color)) {
            return true;
        }
      }
      
      return false;
    }
    
    public static boolean checkCard(Player player, int index) {
      //Checks if the player may actually add the card on top of the stack, and will disallow them if it does not have the same color or number.
      Card playerCard = player.getHand().cards.get(index);
      if(deck.topCard().value == playerCard.value || deck.topCard().color.equals(playerCard.color)) {
        return true;
      }
      return false;
    }


    public static void checkWin() {
      for(int i = 0; i < players.size(); i++) {
        if(players.get(i).getHand().cards.size() == 0) {
          gameRun = false;
          winner = players.get(i).getUsername();
        }
      }
    }

    public static String otherPlayers(Player player) {
      //ArrayList<Player> others = new ArrayList<Player>();
      String print = "";
      for(int i = 0; i < players.size(); i++) {
        if(players.get(i) != player) {
          print += "[" + players.get(i).getUsername() + ", " + players.get(i).getHand().cards.size()  + " cards] ";
        }
      }
      return print;
  }

}
