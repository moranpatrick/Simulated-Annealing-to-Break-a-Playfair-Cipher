package ie.gmit.sw.ai;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

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

}
