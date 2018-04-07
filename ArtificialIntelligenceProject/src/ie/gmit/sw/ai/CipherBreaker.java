package ie.gmit.sw.ai;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main Class handling Menu input and Selection
 * @author Patrick Moran G00179039
 *
 */
public class CipherBreaker {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int choice = 0;
		String load;
		String fileName = "";
		String cipherText = "";
		SimulatedAnnealing sa = null;
		String key, encKey, encText;
		String result, urlFile, fileLocation;
		File file = new File();
		String output;		
		PlayfairCipher pf = new PlayfairCipher();	
		Path pathFileUrl = null;
		
		do{
			try {
				choice = menu();
				switch(choice){
				case 1:
					/*Decrypt Cipher Text From Users Key */
					System.out.println("Please Enter Your Key:");
					key = sc.next();
					
					sc.nextLine();
					
					System.out.println("\nPlease enter the path to your file (eg : C:\\fileName.txt): ");
					fileName = sc.next();
					
					try {
						result = new File().readFile(fileName).toUpperCase().replaceAll("[^A-Za-z0-9 ]", "");
						pf.createTable(key);
						output = pf.decode(result);
						System.out.println(output);
						file = new File();
						try {
							file.writeFile(output, "decrypted_result.txt");
							System.out.println("Results outputed to decrypted_result.txt");
						} catch (IOException e) {
							System.out.println("Error writing decrypted results to a file");
						}
					} catch (FileNotFoundException e){
						System.out.println("Unable To Locate File - Please Check Path");
						continue;
					} catch (IOException e) {
						System.out.println("Unable to load File");
						continue;
					}
					break;
				case 2:
					/*Decrypt Cipher Text Using SA */
					System.out.println("Do you need to Load 4Grams file? (y/n)(no if allready loaded)");
					load = sc.next();

					if(load.equalsIgnoreCase("y") || load.equalsIgnoreCase("yes")){
						loadFourGramsFile(load);
					}
					else{
						
					}
					System.out.println("\nEnter Path To a Text File OR URL to Text A File (eg www.google.com/file.txt)");
					fileName = sc.next();
					
					try {
						URL url = new URL(fileName);
						InputStream in = url.openStream();
						
						urlFile = fileName.substring(fileName.lastIndexOf('/') + 1, fileName.length());
						pathFileUrl = Paths.get("./" + urlFile).toAbsolutePath().normalize();
						System.out.println("Downloading to " + pathFileUrl.toString());
						System.out.println(url.getFile().toString());
					    Files.copy(in, pathFileUrl , StandardCopyOption.REPLACE_EXISTING);
					    System.out.println("Download complete...\n");
					} catch (MalformedURLException mue) {
						/* Not A Valid URL so it must be a file locally */
						pathFileUrl = Paths.get(fileName);
					} catch (Exception e) {
						System.out.println("Error getting book.." + e);
						continue;
					}

					try {
						cipherText = new File().readFile(pathFileUrl.toString()).toUpperCase().replaceAll("[^A-Za-z0-9 ]", "");
						System.out.println("Starting Simulated Annealing Algorithm. This may take some time...");
						sa = new SimulatedAnnealing();
						sa.StartSimulatedAnnealing(cipherText);
							
					} catch (FileNotFoundException e) {
						System.out.println("Unable To Locate The File - Please Check Path");
					} catch (IOException e) {
						System.out.println("Unable to load %s" + fileName);
					}						
					break;	
				case 3:
					System.out.println("Please Enter an encryption key (min length 6): ");
					encKey = sc.next();
				
					sc.nextLine();
					
					System.out.println("Please Enter your text to encrypt: ");
					encText = sc.nextLine();
					
					pf.createTable(encKey);
					encText = pf.prepareText(encText, true);
					encText = pf.encode(encText);
					System.out.println("Your Decypted Text:\n" + encText);
					
					try {
						file.writeFile(encText, "yourFile.txt");
						System.out.println("Create a file called yourFile.txt with your decrypted text");
					} catch (IOException e) {
						System.out.println("Error creating a text file with your decrypted output.");
					}
					break;
				default:
					if(choice != 4){
						System.out.println("Sorry - Thats invalid Input Please Try Again default");
						break;
					}
				}			
			} catch (InputMismatchException e) {
				System.out.println("Sorry - Thats invalid Input Please Try Again Input");
				sc.next();
			}
			
		}while(choice != 4);				
		sc.close();
	}
    
	/**
	 * Displays a menu to the user and accepts input
	 * @return int as users choice
	 */
    public static int menu() {
        int choice;   
        System.out.println("|---------------------------|");
        System.out.println("|-------- Main Menu --------|");
        System.out.println("|---------------------------|");
        System.out.println("|  1) Decrypt With Key      |");
        System.out.println("|  2) Decrypt Without Key   |");       
        System.out.println("|  3) Encrypt Some Text     |");     
        System.out.println("|  4) Quit                  |");   
        System.out.println("|---------------------------|");

        choice = sc.nextInt();
        return choice;    
    }
    
    /**
     * Loads the 4grams.txt using the GramParser
     * @param load 
     */
    public static void loadFourGramsFile(String load){
    	String gramsPath;
    	
		System.out.println("Please enter path to 4grams file (eg C:\\4grams.txt): ");
		gramsPath = sc.next();
		
		GramParser gp = new GramParser();
		try {
			gp.parse4Gram(gramsPath);
			
		} catch (FileNotFoundException e){
			System.out.println("Unable To Locate 4Grams File - Please Check Path");
		} catch (IOException e) {
			System.out.println("Unable to load the 4Grams.txt");
		}
		
		System.out.println("Successfully Loaded 4grams File.");
    }


}
