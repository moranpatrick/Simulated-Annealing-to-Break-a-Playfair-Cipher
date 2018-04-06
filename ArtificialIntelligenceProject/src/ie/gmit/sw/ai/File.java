package ie.gmit.sw.ai;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class File {

	private BufferedReader br;
	
	 // https://stackoverflow.com/q/326390
	public String readFile(String file) throws IOException{
		
		br = new BufferedReader(new FileReader (file));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		
		try {
			
		    while((line = br.readLine()) != null) {
		        stringBuilder.append(line);
		    }
		
		    return stringBuilder.toString();
		} finally {
		    br.close();
		}
	}
	
	// Adapted From: // https://stackoverflow.com/a/10390351
	public void writeFile(String text) throws IOException{
		BufferedWriter out = new BufferedWriter(new FileWriter("decrypted.txt"));

		try {
		    out.write(text);  		                                            
		}
		catch (IOException e)
		{
		    System.out.println("IOException " + e);
		}
		finally
		{
		    out.close();
		}
	}

}
