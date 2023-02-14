package client;

import client.card.*;
import client.client.ConnectionHandler;
import server.Game;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

//TODO: create reverse cards, +2 and +4, wild cards.
//TODO: create computer bot.

class Main{
    private static Scanner reader = new Scanner(System.in);
    private static int turn = 0;
    private static ConnectionHandler connectionHandler;
    /*
    * Ask the player for their name
    * Ask if they want to join or create a game
    * Create:
    *       ask use for the number of players
    *       create a game object and send it to the server
    * join:
    *    ask the server for the currently available games
    *    join the selected one, (print the games as:
    *       [1] Ryan's Game
    *
    * The game loop will work as the following
    *   1. Ask the server for the game object
    *   2. If the new object turn number > local turn number: update local object and re-render
    *   3. If it is the player's turn, they will select a card from their hand.
    *       The CLIENT will decide what cards are valid to place
    *   4. If the client cannot place a card, they will use turn(-1). The server will then randomly add a card to their deck and move on to the next player.
    *
    * 1-4 repeats until someone places down their last card
    **/

    public static void main(String[] args) {
        Uno uno = new Uno();
    }

}