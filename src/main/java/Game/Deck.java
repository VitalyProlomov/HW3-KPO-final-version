package Game;

import java.util.Random;

public class Deck {
    static final int MAX_CARD = 10;
    static final int MIN_CARD = 1;

    private static final Random randCard = new Random();


    public static synchronized int getTopCard() {
        return 1 + (Math.abs(randCard.nextInt()) % 10);
    }

}
