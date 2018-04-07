package ie.gmit.sw.ai;

/**
 * Loads the 4grams.txt into a HashMap summing nGrams and scores text using nGram statistics
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GramParser {
	
	private static Map<String, Double> map = new HashMap<String, Double>();
	private BufferedReader in;
	private static long count = 0; 
	
	/**
	 * Parses 4Grams.txt creating a hashmap of Strings and doubles
	 * @param filePath to the 4Grams text file
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void parse4Gram(String filePath) throws NumberFormatException, IOException{
				
		in = new BufferedReader(new FileReader(filePath));
		String line = "";
		while ((line = in.readLine()) != null) {
			map.put(line.split(" ")[0], Double.parseDouble(line.split(" ")[1]));	
		}		
		System.out.println("Successfully Loaded in 4Grams.txt");
		in.close();
	
		/* Sum Total nGram values */
		for(double val : map.values()){
			count += val;
		}
	}  
	
	/**
	 * Scores maximum 696 characters from the cipher text against 4grams
	 * @param text to be scored
	 * @return score as double
	 */
	public double scoreText(String text){	
		double score = 0;
		int n = 0;		
		// If the length of the text input is less than 700 chars
		if(text.length() < 700){
			n = text.length() - 4;
		}
		else{
			// Otherwise set n to be 696
			n = 700 - 4;
		}		
		for(int i = 0; i < n; i++) {
						
			if(map.get(text.substring(i, i + 4)) != null){
				score += Math.log10((double)(map.get(text.substring(i, i + 4))) / count);
			}
			else{
				score += Math.log10((double)(1) / count);
			}
		}
		return score;
	}
}
