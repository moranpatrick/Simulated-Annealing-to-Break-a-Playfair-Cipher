package ie.gmit.sw.ai;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SimulatedAnnealing {
	
	private String parent;
	private Key k;
	private PlayfairCipher pc;
	private GramParser gp;
	private int transitions = 50000;
	private int temperature = 35;
	private String child;
	private File file;
	
	public SimulatedAnnealing() throws IOException{
		pc = new PlayfairCipher();
		gp = new GramParser();
		k = new Key();	
	}
	
	
	
	public void StartSimulatedAnnealing(String cipherTxt){
		Random r = ThreadLocalRandom.current();
		String bestDecryptedText;
		String decryptedText;
		String bestKey;
		
		
		parent = k.generateKey();	
		System.out.println("Parent Key: "  + parent);
		pc.createTable(parent);
		decryptedText = pc.decode(cipherTxt);
		bestDecryptedText = decryptedText;
		
		double parentScore = gp.scoreText(decryptedText); // or (cipherTxt)
		double bestScore = parentScore;
		bestKey = parent;
		
		System.out.println("Best Score Start: " + bestScore);
		for(int temp = temperature; temp > 0; temp--){
			
			for(int trans = transitions; trans > 0; trans--){
				child = k.shuffleKey(parent);
				pc.createTable(child);
				decryptedText = pc.decode(cipherTxt);
				
				double childScore = gp.scoreText(decryptedText);
				
				double delta = childScore - parentScore;
				
				if(delta > 0){
					parent = child;
					parentScore = childScore;
				}
				else{
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
			System.out.println("Temp: " + temp + "\tScore: " + bestScore + "\tBest Key: " + bestKey);
		}
		
		System.out.println("Best Score: " + bestScore + "\nBest Decrypted Text: " + bestDecryptedText + "\nBest Key: " + bestKey);
		
		System.out.println("Writing Decrypted Results to a text file called decrypted_results.txt...");
		file = new File();
		try {
			file.writeFile(bestDecryptedText);
			System.out.println("Write Complete!");
		} catch (IOException e) {
			System.out.println("Error writing decrypted results to a file");
		}
	}

}
