import java.util.Scanner;

/**
 * This Hangman application is a fully functioning Hangman game
 * which implements the functionality to read a custom word list
 * and record wins and losses.
 * 
 * @author Joshua Collins
 * @version 1.0
 * @since 2021-01-23
 */
public class Application {
	/**
	 * This is the main method which calls the playGame() method
	 * of the Hangman class.
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	public static void main(String[] args) {
		Hangman hm = new Hangman();
		Scanner scan = new Scanner(System.in);
		
		
		/**
		 * The loop containing the prompt for the user to choose to play.
		 */
		boolean responding = true;
		while (responding) {
			System.out.println("Would you like to play? (Y/N)");
			String response = scan.next();
			
			if (response.toLowerCase().equals("yes") || response.toLowerCase().equals("y")) {
				hm.playGame();
				responding = false;
			}
			else if (!response.toLowerCase().equals("no") && !response.toLowerCase().equals("n")) {
				System.out.println("Please enter 'Y' or 'N'.");
			}
			else {
				responding = false;
			}
		}
		scan.close();
	}
}
