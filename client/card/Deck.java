package client.card;
import java.io.Serializable;
import java.util.*;

public class Deck implements Serializable {
    
  public ArrayList<Card> cards = new ArrayList<Card>();

  public Deck() {
    cards.add(new Card());
  }

  public Card topCard() {
    return cards.get(cards.size()-1);
  }


    
}
