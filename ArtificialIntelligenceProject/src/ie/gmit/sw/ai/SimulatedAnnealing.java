package ie.gmit.sw.ai;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Uses Simulated Annealing Algorithm to break a playfair cipher.
 * @author Patrick Moran G00179039
 */
public class SimulatedAnnealing {	
	private String parent;
	private Key k;
	private PlayfairCipher pc;
	private GramParser gp;
	private static final int TRANSITIONS = 50000;
	private static final int TEMPERATURE = 50;
	private String child;
	private File file;
	private String bestDecryptedText;
	private String decryptedText;
	private String bestKey;
	private double parentScore;
	private double bestScore;
	private String fullText;
	
	public SimulatedAnnealing(){
		pc = new PlayfairCipher();
		gp = new GramParser();
		k = new Key();	
	}
		
	/**
	 * Follows the Simulated Annealing Algorithm to break Playfair Cipher
	 * @param cipherTxt from user to use with the Simulated Annealing Algorithm
	 */
	public void StartSimulatedAnnealing(String cipherTxt){
		Random r = ThreadLocalRandom.current();		
		fullText = cipherTxt;
		/* Create A Table with the Key */
		pc.createTable(parent);
		/* Decrypt The Text */
		/* Only Decrypt 700 Charaters of the text */
		if(cipherTxt.length() > 700){
			cipherTxt = cipherTxt.substring(0, Math.min(cipherTxt.length(), 700));
		}

		decryptedText = pc.decode(cipherTxt);
		bestDecryptedText = decryptedText;
		
		/* Generate a random 25 letter key called Parent */
		parent = k.generateKey();
		/* Get Parent Score */
		parentScore = gp.scoreText(decryptedText); 
		bestScore = parentScore;
		bestKey = parent;
		
		System.out.println("Best Score Start: " + bestScore);
		for(int temp = TEMPERATURE; temp > 0; temp--){		
			for(int trans = TRANSITIONS; trans > 0; trans--){
				/* Make a small change to the key */
				child = k.shuffleKey(parent);
				pc.createTable(child);
				decryptedText = pc.decode(cipherTxt);
				
				double childScore = gp.scoreText(decryptedText);		
				double delta = childScore - parentScore;
				
				if(delta > 0){
					/* New Key Better */
					parent = child;
					parentScore = childScore;
				}
				else{
					/* New Key Worse */
					if((Math.exp((delta / temp)) > r.nextDouble())){
						parent = child;
						parentScore = childScore;
					}
				}
				
				if(parentScore > bestScore){
					bestScore = parentScore;
					bestDecryptedText = decryptedText;
					bestKey = parent;
				}
			}	
			System.out.println("Temp: " + temp + "\tBest Score: " + bestScore + "\tBest Key: " + bestKey);
		}		
		System.out.println("Best Score: " + bestScore + "\nBest Decrypted Text: " + bestDecryptedText + "\nBest Key: " + bestKey);
		
		pc.createTable(bestKey);
		bestDecryptedText = pc.decode(fullText);
		
		/* Write Results to a text file */
		System.out.println("Writing Decrypted Results to a text file called decrypted_results.txt...");
		file = new File();
		try {
			file.writeFile(bestDecryptedText, "decrypted_results.txt");
			System.out.println("Write Complete!");
		} catch (IOException e) {
			System.out.println("Error writing decrypted results to a file");
		}
	}

}
