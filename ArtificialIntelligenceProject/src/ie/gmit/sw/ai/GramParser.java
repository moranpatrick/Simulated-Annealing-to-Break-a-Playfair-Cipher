package ie.gmit.sw.ai;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GramParser {
	
	private Map<String, Double> map = new HashMap<String, Double>();
	private BufferedReader in;
	private int count = 0; 
	private String FILENAME = "4grams.txt";
	
	public GramParser(){
		parse4Gram();
	}
	
	public void parse4Gram(){
		
		try {
			in = new BufferedReader(new FileReader(FILENAME));
			String line = "";
			while ((line = in.readLine()) != null) {
				map.put(line.split(" ")[0], Double.parseDouble(line.split(" ")[1]));	
				count++; // count the total number of 4grams in file
			}
			//System.out.println(map.toString());
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}								
	}  
	
	public double scoreText(String text){
		double score = 0;
		int n = 0;
		String str = "";
		
		// If the length of the text input is less than 400 chars
		if(text.length() < 400){
			n = text.length() - 4;
		}
		else{
			// Otherwise set n to be 396
			n = 396;
		}
		
		for(int i = 0; i < n; i++){
			str = text.substring(i, i + 4);
			if(map.get(str) != null){
				score +=  Math.log10((double) map.get(str) / count);
			}
			else{
				score += 1 / count;
			}	
		}
		return score;		
	}
	
	

}
