package Client.card;

public class Card {
    String[] colors = {"Red", "Blue", "Yellow", "Green"};
    String color;
    int value;
    public Card(int col, int value) {
        this.color = colors[col];
        this.value = value;

    }
    

}
