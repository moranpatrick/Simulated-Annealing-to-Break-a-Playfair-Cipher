package ie.gmit.sw.ai;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CipherBreaker {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		int choice;
		String gramsPath;
		String load;
		String fileName = "";
		String cipherText = "";
		SimulatedAnnealing sa = null;
		
			
		do{
			choice = menu();
			switch(choice){
			case 1:
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
			case 2:

				break;
			default:
				if(choice != 2) System.out.println("Invalid choice - please try again!");
			}
		}while(choice != 2);		
		
		sc.close();
	}
    
    public static int menu() {

        int choice;
        
        System.out.println("|-------------------------|");
        System.out.println("|------- Main Menu -------|");
        System.out.println("|-------------------------|");
        System.out.println("|    1) Decrypt a file    |");
        System.out.println("|    2) Quit              |");
        System.out.println("|-------------------------|");

        choice = sc.nextInt();
        return choice;    
    }


}
