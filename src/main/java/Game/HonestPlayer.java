package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HonestPlayer extends Player{
    Random rnd = new Random();

    public HonestPlayer() {
        allHonestPlayers.add(this);
    }

    protected static List<HonestPlayer> allHonestPlayers = new ArrayList<>();

    public void addHonestPlayer(HonestPlayer player) {
        allHonestPlayers.add(player);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            getCard();
            int timeAsleep = 100 + (Math.abs(rnd.nextInt()) % 101);

            try {
                sleep(timeAsleep);
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}
