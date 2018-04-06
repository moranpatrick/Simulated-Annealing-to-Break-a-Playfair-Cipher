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
	private long count = 0; 
	private String FILENAME = "4grams.txt";
	

	
	public GramParser() throws IOException{
		parse4Gram();
	}
	
	public void parse4Gram() throws IOException{
		
		
		try {
			in = new BufferedReader(new FileReader(FILENAME));
			String line = "";
			while ((line = in.readLine()) != null) {
				map.put(line.split(" ")[0], Double.parseDouble(line.split(" ")[1]));	
			}
			
			System.out.println("NGrams Successfully Loaded");
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}	
		
		for(double val : map.values()){
			count += val;
		}
		System.out.println("COUNT" + count);
	}  
	
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
			//score += Math.log10((double)(((map.get(text.substring(i, i + 4)) != null) ? map.get(text.substring(i, i + 4)) : 1)) / this.count);
			
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
