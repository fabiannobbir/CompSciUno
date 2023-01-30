package Client.card;

public class Player {
    public String username = "";
    public String id = "";
    public Hand hand;
    
    public Player(String username, String id, int numCards) {
        this.username = username;
        this.id = id;
        hand = new Hand(numCards);
    }
    
    public String toString() {
        String print = "Player " + username + " has cards: ";
        for(int i = 0; i < hand.cards.size(); i++) {
            print += "(" + hand.cards.get(i).color + ", " + String.valueOf(hand.cards.get(i).value) + ") ";
        }

        return print;
    }

}
