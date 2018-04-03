package ie.gmit.sw.ai;

import java.io.IOException;
import java.util.Scanner;

public class CipherBreaker {

	public static void main(String[] args) throws IOException {
		
		GramParser gp = new GramParser();
		PlayfairCipher pc = new PlayfairCipher();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Loading 4Gram.txt...");
		gp.parse4Gram();
		System.out.println("Loading Complete!");
		System.out.println("\n");
		
        String key = prompt("Enter an encryption key (min length 6): ", sc, 6);
        String txt = prompt("Enter the message: ", sc, 1);
        String jti = prompt("Replace J with I? y/n: ", sc, 1);
 
        boolean changeJtoI = jti.equalsIgnoreCase("y");
 
        PlayfairCipher.createTable(key, changeJtoI);
 
        String enc = txt; 
 
        System.out.printf("%nEncoded message: %n%s%n", enc);
        System.out.printf("%nDecoded message: %n%s%n", pc.decode(enc));	
	}
	
    private static String prompt(String promptText, Scanner sc, int minLen) {
        String s;
        do {
            System.out.print(promptText);
            s = sc.nextLine().trim();
        } while (s.length() < minLen);
        return s;
    }


}
