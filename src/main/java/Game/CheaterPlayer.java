package Game;

import java.util.Random;

public class CheaterPlayer extends Player {
    public static final int MAX_STOLEN_AMOUNT = 8;

    Random rnd = new Random();

    @Override
    public void run() {
        int timeAsleep;
        while (!isInterrupted()) {
            // Choosing to steal or to get a card.
            if (rnd.nextDouble() < 0.4) {
                int victimsAmount = HonestPlayer.allHonestPlayers.size();
                steal(HonestPlayer.allHonestPlayers.get(Math.abs(rnd.nextInt()) % victimsAmount));
                timeAsleep = 180 + (Math.abs(rnd.nextInt()) % 121);
            } else {
                getCard();
                timeAsleep = 100 + (Math.abs(rnd.nextInt()) % 101);
            }

            try {
                sleep(timeAsleep);
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }

    private void steal(HonestPlayer player) {
        // Synchronized on player to prevent stealing from stealing at the same time and,
        // as a consequence, not changing players balance properly.
        synchronized (player) {
            int amountToSteal = rnd.nextInt() % (MAX_STOLEN_AMOUNT + 1);
            if (player.getBalance() < amountToSteal) {
                this.balance += player.getBalance();
            } else {
                this.balance += amountToSteal;
            }
            player.reduceBalance(amountToSteal);
        }
    }

}
