package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        int honestPlayersAmount;
        int cheatingPlayersAmount;
        System.out.println("Input the amount of honest players involved in the game(integer in [1;20]): ");
        honestPlayersAmount = getCorrectIntInput(1, 20);

        System.out.println("Now input he amount of cheaters in the game(integer in [0;20]): ");
        cheatingPlayersAmount = getCorrectIntInput(0,20);

        List<Player> allPlayers = createPlayerList(honestPlayersAmount, cheatingPlayersAmount);


        for (Player player : allPlayers) {
            player.start();
        }
        System.out.println("The game has started");
        Thread.sleep(10000);

        for (Player player : allPlayers) {
            player.interrupt();
        }

        printAllbalances(allPlayers);
    }


    /**
     * Gets correct int input form the user that fits into the given interval.
     * @param minValue minimal allowed value.
     * @param maxValue maximum allowed value.
     * @return integer that user entered.
     */
    public static int getCorrectIntInput(int minValue, int maxValue) {
        int input = maxValue + 1;
        try {
            input = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ignored) {

        }
        String line;
        while (input > maxValue || input < minValue) {
            System.out.println("Incorrect input, enter a number in range [" + minValue + ";" + maxValue + "]");
            line = scanner.nextLine();
            try {
                input = Integer.parseInt(line);
            } catch (NumberFormatException ignored) {

            }
        }
        return input;
    }

    public static List<Player> createPlayerList(int honestAmount, int cheatersAmount) {
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < cheatersAmount; ++i) {
            players.add(new CheaterPlayer());
        }

        for (int i = 0; i < honestAmount; ++i) {
            HonestPlayer newPlayer = new HonestPlayer();

            players.add(newPlayer);
            HonestPlayer.allHonestPlayers.add(newPlayer);
        }
        return players;
    }

    public static void printAllbalances(List<Player> players) {

        int index = 1;
        for (Player player: players) {
            if (player.getClass() == HonestPlayer.class) {
                System.out.print("Honest player " + index + " balance: ");
                System.out.println(player.getBalance());
                ++index;
            }
        }

        index = 1;
        for (Player player: players) {
            if (player.getClass() == CheaterPlayer.class) {
                System.out.print("Cheater player " + index + " balance: ");
                System.out.println(player.getBalance());
                ++index;
            }
        }
    }


}
