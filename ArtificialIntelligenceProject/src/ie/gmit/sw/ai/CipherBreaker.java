package ie.gmit.sw.ai;

/**
 * Main Class handling Menu input and Selection
 */
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

public class CipherBreaker {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int choice = 0;
		String gramsPath;
		String load;
		String fileName = "";
		String cipherText = "";
		SimulatedAnnealing sa = null;
		String key, encKey, encText;
		String result, urlFile, fileLocation;
		File file;
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
							file.writeFile(output);
							System.out.println("Results outputed to decrypt_result.txt");
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
					System.out.println("Load 4Grams file? (y/n)");
					load = sc.next();
					
					if(load.equalsIgnoreCase("y") || load.equalsIgnoreCase("yes")){
						System.out.println("Please enter path to 4grams file (eg C:\\4grams.txt): ");
						gramsPath = sc.next();											
						GramParser gp = new GramParser();
						try {
							gp.parse4Gram(gramsPath);
						} catch (FileNotFoundException e){
							System.out.println("Unable To Locate 4Grams File - Please Check Path");
							continue;
						} catch (IOException e) {
							System.out.println("Unable to load the nGrams.txt");
							continue;
						}
					}
					else{
						System.out.println("Sorry - That was invalid input.\n");
						continue;
					}
					System.out.println("\nPlease enter the path to your file (eg : C:\\fileName.txt): ");
					fileName = sc.next();

					try {
						cipherText = new File().readFile(fileName).toUpperCase().replaceAll("[^A-Za-z0-9 ]", "");
						sa = new SimulatedAnnealing();
						sa.StartSimulatedAnnealing(cipherText);
							
					} catch (FileNotFoundException e) {
						System.out.println("Unable To Locate The File - Please Check Path");
					} catch (IOException e) {
						System.out.println("Unable to load %s" + fileName);
					}						
					break;	
				case 3:
					/*Decrypt Cipher Text From Text File from URL */
					System.out.println("Enter URL to Text File (eg www.google.com/file.txt)");
					fileLocation = sc.next();
					
					try {
						URL url = new URL(fileLocation);
						InputStream in = url.openStream();
						
						urlFile = fileLocation.substring(fileLocation.lastIndexOf('/') + 1, fileLocation.length());
						pathFileUrl = Paths.get("./" + urlFile).toAbsolutePath().normalize();
						System.out.println("Downloading to " + pathFileUrl.toString());
						System.out.println(url.getFile().toString());
					    Files.copy(in, pathFileUrl , StandardCopyOption.REPLACE_EXISTING);
					    System.out.println("Download complete...");
					} catch (MalformedURLException mue) {
						System.out.println("Invalid Url - Please Check Again");
						continue;
					} catch (Exception e) {
						System.out.println("Error getting book.." + e);
						continue;
					}
					System.out.println("Text File Downloaded. Processing ..." + pathFileUrl.toString());
					
					System.out.println("Load 4Grams file? (y/n)");
					load = sc.next();
					
					if(load.equalsIgnoreCase("y") || load.equalsIgnoreCase("yes")){
						System.out.println("Please enter path to 4grams file (eg C:\\4grams.txt): ");
						gramsPath = sc.next();											
						GramParser gp = new GramParser();
						try {
							gp.parse4Gram(gramsPath);
						} catch (FileNotFoundException e){
							System.out.println("Unable To Locate nGrams File - Please Check Path");
							continue;
						} catch (IOException e) {
							System.out.println("Unable to load the nGrams.txt");
							continue;
						}
					}
					else{
						System.out.println("Sorry - That was invalid input.\n");
						continue;
					}
					
					try {
						cipherText = new File().readFile(pathFileUrl.toString()).toUpperCase().replaceAll("[^A-Za-z0-9 ]", "");
						sa = new SimulatedAnnealing();
						sa.StartSimulatedAnnealing(cipherText);
							
					} catch (FileNotFoundException e) {
						System.out.println("Unable To Locate The File - Please Check Path");
					} catch (IOException e) {
						System.out.println("Unable to load %s" + pathFileUrl.toString());
					}					
					break;
				case 4:
					System.out.println("Please Enter an encryption key (min length 6): ");
					encKey = sc.next();
				
					sc.nextLine();
					
					System.out.println("Please Enter your text to encrypt: ");
					encText = sc.nextLine();
					
					pf.createTable(encKey);
					encText = pf.prepareText(encText, true);
					System.out.println("Your Encrypted Text: " + pf.encode(encText));
					break;
				default:
					if(choice != 5){
						System.out.println("Sorry - Thats invalid Input Please Try Again default");
						break;
					}
				}			
			} catch (InputMismatchException e) {
				System.out.println("Sorry - Thats invalid Input Please Try Again Input");
				sc.next();
			}
			
		}while(choice != 5);				
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
        System.out.println("|  3) Decrypt text from URL |");     
        System.out.println("|  4) Encrypt Some Text     |");   
        System.out.println("|  5) Quit                  |");
        System.out.println("|---------------------------|");

        choice = sc.nextInt();
        return choice;    
    }


}
