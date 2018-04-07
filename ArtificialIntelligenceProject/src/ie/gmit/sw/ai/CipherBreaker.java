package ie.gmit.sw.ai;

import java.io.FileNotFoundException;
import java.io.IOException;
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
		String key;
		String result;
		File file;
		String output;		
		PlayfairCipher pf = new PlayfairCipher();		
		
		do{
			try {
				choice = menu();
				switch(choice){
				case 1:
					System.out.println("Please Enter Your Key:");
					key = sc.next();
					
					System.out.println("\nPlease enter the path to your file (eg : C:\\fileName.txt): ");
					fileName = sc.next();
					
					try {
						result = new File().readFile(fileName).toUpperCase().replaceAll("[^A-Za-z0-9 ]", "");
						pf.createTable(key);
						output = pf.decode(result);
						
						file = new File();
						try {
							file.writeFile(output);
							System.out.println("Write Complete!");
						} catch (IOException e) {
							System.out.println("Error writing decrypted results to a file");
						}
					} catch (FileNotFoundException e){
						System.out.println("Unable To Locate nGrams File - Please Check Path");
						continue;
					} catch (IOException e) {
						System.out.println("Unable to load File");
						continue;
					}
					break;
				case 2:
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
				default:
					if(choice != 3){
						System.out.println("Sorry - Thats invalid Input Please Try Again");
						break;
					}
				}			
			} catch (InputMismatchException e) {
				System.out.println("Sorry - Thats invalid Input Please Try Again");
				sc.next();
			}			
		}while(choice != 3);				
		sc.close();
	}
    
    public static int menu() {

        int choice;
        
        System.out.println("|-------------------------|");
        System.out.println("|------- Main Menu -------|");
        System.out.println("|-------------------------|");
        System.out.println("|  1) Decrypt With Key    |");
        System.out.println("|  2) Decrypt Without Key |");       
        System.out.println("|  3) Quit                |");     
        System.out.println("|-------------------------|");

        choice = sc.nextInt();
        return choice;    
    }


}
