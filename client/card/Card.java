package Client.card;
import java.util.*;

public class Card {
    public String[] colors = {"Red", "Blue", "Yellow", "Green"};
    public String color;
    public int value;
    public Random ran = new Random();

  
    public Card(int col, int value) {
        this.color = colors[col];
        this.value = value;

    }
    
    public Card() {
      this.color = colors[ran.nextInt(4)];
      this.value = ran.nextInt(9)+1;
    }

    public String toString() {
      return "(" + color + ", " + value + ")";
    }
}
