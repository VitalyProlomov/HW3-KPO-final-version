package Game;

import java.util.Random;

public abstract class Player extends Thread {
    protected int balance;

    public int getBalance() {
        return balance;
    }

    protected void reduceBalance(int stolen_amount) {
        if (stolen_amount > CheaterPlayer.MAX_STOLEN_AMOUNT) {
            throw new IllegalArgumentException("Something went wrong.. Too much points were stolen");
        }
        balance-= stolen_amount;
        if (balance < 0) {
            balance = 0;
        }
    }

    Random rnd = new Random();

    protected void getCard() {
        int pulledCard = Deck.getTopCard();
        balance += pulledCard;
    }
}
