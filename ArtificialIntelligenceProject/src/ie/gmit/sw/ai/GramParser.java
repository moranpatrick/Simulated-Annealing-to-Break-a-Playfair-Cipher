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
	
	public Map<String, Double> parse4Gram(){
		
		try {
			in = new BufferedReader(new FileReader("4grams.txt"));
			String line = "";
			while ((line = in.readLine()) != null) {
				map.put(line.split(" ")[0], Double.parseDouble(line.split(" ")[1]));			
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}

		System.out.println(map.toString());
					
		
		return map;
				
	}  

}
