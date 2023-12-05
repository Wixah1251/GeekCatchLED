import java.util.Random;
import java.util.Scanner;


public class TwitchAPI {

    public static void main(String[] args) {
        // Replace "your-channel-name" with the actual Twitch channel name
        // Replace these with your Twitch API credentials
        String clientId = "your-client-id";
        String clientSecret = "your-client-secret";
        String accessToken = "your-access-token";
        String channelName = "your-channel-name";


        // Display a message in the terminal & prompt the user to start the program
        System.out.println("Welcome, hit space to start the program...");


        // Wait for the user to hit space
        waitForSpace();

        // Display a message in the terminal
        System.out.println("Welcome to the " + channelName + " channel! Waiting for donation...");

        // Fake donation processing logic
        Random random = new Random();
        int donationCount = 0;

        while (true) {
            // Simulate receiving a donation every few seconds
            try {
                Thread.sleep(3000); // Sleep for 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Generate a random donation amount
            int donationAmount = random.nextInt(100) + 1;

            // Display donation message in the terminal
            donationCount++;
            System.out.println("New donation #" + donationCount + ": $" + donationAmount);
        }
    }

    private static void waitForSpace() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextLine() && scanner.nextLine().equals(" ")) {
                break;
            }
        }
    }
}