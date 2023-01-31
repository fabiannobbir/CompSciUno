package client.card;
import java.util.*;

public class Deck {
    
  public ArrayList<Card> cards = new ArrayList<Card>();

  public Deck() {
    cards.add(new Card());
  }

  public Card topCard() {
    return cards.get(cards.size()-1);
  }


    
}
