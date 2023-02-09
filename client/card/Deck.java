package client.card;
import java.io.Serializable;
import java.util.*;

public class Deck implements Serializable {
    
  public ArrayList<Card> cards = new ArrayList<Card>();
  private Hand hand = new Hand(0);
  public Deck() {
    cards.add(new Card());
  }

  public Card topCard() {
    return cards.get(cards.size()-1);
  }


  public String printTopCard() {
    Card card = cards.get(cards.size()-1);
    String print = "";
    print += hand.ascii(card.color, card.value)[0];
    print += hand.ascii(card.color, card.value)[1];
    print += hand.ascii(card.color, card.value)[2];
    print += hand.ascii(card.color, card.value)[3];
    return print;
  }


    
}
