import java.security.SecureRandom;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * The Dictionary class provides the functionality
 * to import words from a .txt file and choose a
 * random word.
 * 
 * @author Joshua Collins
 * @version 1.0
 * @since 2021-01-23
 *
 */
public class Dictionary {
	String[] wordList = new String[200];
	SecureRandom randomNumbers = new SecureRandom();
	
	/**
	 * This constructor calls the readFile() method on instantiation.
	 * 
	 * @param fileName The name of the file that contains the word list.
	 */
	public Dictionary(String fileName) {
		readFile(fileName);
	}
	
	/**
	 * This method iterates through the inputted file and stores each word in the wordList array.
	 * 
	 * @param fileName The name of the file that contains the word list.
	 */
	public void readFile(String fileName) {
		try {
			
		Scanner scan = new Scanner(new File(fileName));
		
		while (scan.hasNext()) {
			for (int i=0; i<200; i++) {
				wordList[i] = scan.next();
			}
		}
		
		}catch (IOException e) {
			
		}
	}
	
	/**
	 * This method selects a random word from the wordList array.
	 * 
	 * @return The randomly selected word.
	 */
	public String chooseWord() {
		int num = randomNumbers.nextInt(200);
		String word = this.wordList[num];
		return word;
	}
}
