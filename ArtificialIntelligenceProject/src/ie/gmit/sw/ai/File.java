package ie.gmit.sw.ai;
/**
 * File Class Handles Read and Write Operations on a File.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class File {

	private BufferedReader br;
	
	/**
	 * Uses a StringBuilder to append the contents of a text file to a String
	 * @param path to a file
	 * @return String of the file contents
	 * @throws IOException
	 */
	public String readFile(String path) throws IOException{
		
		br = new BufferedReader(new FileReader (path));
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
	
	/**
	 * Writes Result to a text file called decrypted_results.txt at current directory
	 * @param text results from Decryption
	 * @throws IOException
	 */
	public void writeFile(String text) throws IOException{
		BufferedWriter out = new BufferedWriter(new FileWriter("decrypted_results.txt"));
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
