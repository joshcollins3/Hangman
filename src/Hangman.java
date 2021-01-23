import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Hangman class contains methods to read and write
 * from the file that contains the wins and losses and
 * a method which contains the core gameplay.
 * 
 * @author Joshua Collins
 * @version 1.0
 * @since 2021-01-23
 */
public class Hangman {
	int wins;
	int losses;
	int roundWins = 0;
	int roundLosses = 0;
	String currentWord;
	
	Dictionary dict = new Dictionary("src/words.txt");
	
	/**
	 * This is a default constructor.
	 */
	public Hangman() {
		
	}
	
	/**
	 * This is a method which reads the number of
	 * wins and losses from a file and stores the
	 * values in the class level "wins" and "losses"
	 * variables.
	 * 
	 * @return Nothing
	 */
	public void loadWL() {
		File f = new File("src/WL.txt");
		
		try {
			Scanner scan = new Scanner(f);
		
			while (scan.hasNext()) {
				this.wins = scan.nextInt();
				this.losses = scan.nextInt();
			}

		scan.close();
		
		}catch (IOException e) {
			
		}
	}
	
	/**
	 * This method writes the current number of
	 * wins and losses to the file.
	 * 
	 * @return Nothing.
	 */
	public void writeWL() {
		FileWriter fw;
		try {
			fw = new FileWriter("src/WL.txt");
			
			fw.write(this.wins + " " + this.losses);
			
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method contains the main gameplay of the application.
	 * 
	 * @return Nothing.
	 */
	public void playGame() {
		boolean playing = true;
		int guesses = 5;
		
		/*
		 * This block chooses a random word from the
		 * word list, converts it to a char array,
		 * stores it in a variable called "letters",
		 * and changes each character to uppercase
		 * for readability.
		 */
		this.currentWord = dict.chooseWord();
		char[] letters = this.currentWord.toCharArray();
		for (int i=0; i<letters.length; i++) {
			letters[i] = Character.toUpperCase(letters[i]);
		}
		
		/*
		 * Initializes lists to contain the guessed
		 * characters and the correctly guessed characters.
		 */
		List<Character> guessed = new ArrayList<Character>();
		List<Character> correct = new ArrayList<Character>();
		
		Scanner scan = new Scanner(System.in);
		
		//The loop for the main gameplay.
		while (playing) {
			
			//Informs user of remaining guesses and displays the blanks and guessed letters of the word.
			System.out.println("\nYou have " + guesses + " incorrect guesses left.\n");
			for (int i=0; i<letters.length; i++) {
				if (!guessed.contains(letters[i])) {
					System.out.print("_ ");
				}
				else {
					System.out.print(letters[i] + " ");
				}
			}
			
			//Prompts the user for their guess.
			System.out.println("\n\nWhat is your guess?");
			String guess = scan.next().toUpperCase();
			
			//Creates a list from the "letters" array to allow the use of the ".contains()" method.
			List<Character> letterList = new ArrayList<Character>();
			for (char c: letters) {
				letterList.add(c);
			}
			
			//Checks if inputted guess is a single character.
			if (guess.toCharArray().length == 1) {
				
				//Checks if user has already guessed the inputted character.
				if (!guessed.contains(guess.charAt(0))) {
					guessed.add(guess.charAt(0));
					
					//Adds guessed character to correct list if it is present in the word.
					if (letterList.contains(guess.charAt(0))) {
						for (char l: letterList) {
							if (l == guess.charAt(0)) {
								correct.add(guess.charAt(0));
							}
						}
					}
					//Decrements the remaining number of guesses.
					else {
						guesses--;
					}
					
					//Executes if the user has correctly guessed every letter.
					if (correct.size() == letters.length) {
						//Increments the record of wins.
						roundWins++;
						this.wins++;
						
						//Prints the completed word.
						System.out.println();
						for (char c: letters) {
							System.out.print(Character.toUpperCase(c));
						}
						
						System.out.println("\n\nYou won!");
						
						//Prompts the user to choose if they wish to continue.
						boolean responding = true;
						while (responding) {
							System.out.println("\n\nWould you like to play again? (Y/N)");
							String playAgain = scan.next();
							if (playAgain.toLowerCase().equals("yes") || playAgain.toLowerCase().equals("y")) {
								playGame();
								responding = false;
								playing = false;
							}
							
							else if (!playAgain.toLowerCase().equals("no") && !playAgain.toLowerCase().equals("n")) {
								System.out.println("Please enter 'Y' or 'N'.");
							}
							else {
								System.out.println("You had " + roundWins + " wins and " + roundLosses + " losses this round.\n\nYou have a total of " + this.wins + " wins and " + this.losses + " losses.");
								writeWL(); //Writes to record of wins and losses.
								scan.close();
								return;
							}
						}
					}

					//Executes if the user is out of guesses.
					else if (guesses == 0) {
						//Increments the record of losses.
						roundLosses++;
						this.losses++;
						
						System.out.println("You are out of guesses! You lost! The correct word was " + currentWord + ".");
						
						//Prompts the user to choose if they wish to continue.
						boolean responding = true;
						while (responding) {
							System.out.println("\nWould you like to play again? (Y/N)");
							String playAgain = scan.next();
							if (playAgain.toLowerCase().equals("yes") || playAgain.toLowerCase().equals("y")) {
								playGame();
								playing = false;
								responding = false;
							}
							else if (!playAgain.toLowerCase().equals("no") && !playAgain.toLowerCase().equals("n")) {
								System.out.println("Please enter 'Y' or 'N'.");
							}
							else {
								System.out.println("You had " + roundWins + " wins and " + roundLosses + " losses this round.\n\nYou have a total of " + this.wins + " wins and " + this.losses + " losses.");
								writeWL(); //Writes to record of wins and losses.
								scan.close();
								return;
							}
						}
					}
				}
				else {
					System.out.println("You have already guessed " + guess + ". Please try again.\n");
				}
			}
			else {
				System.out.println("Please enter a single letter.\n");
			}

		}
		scan.close();
	}
	
	
}
