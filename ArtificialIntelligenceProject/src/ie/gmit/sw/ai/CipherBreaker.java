package ie.gmit.sw.ai;

import java.util.Scanner;

public class CipherBreaker {

	public static void main(String[] args) {
		
		PlayfairCipher pc = new PlayfairCipher();
		
		Scanner sc = new Scanner(System.in);
		  
        String key = prompt("Enter an encryption key (min length 6): ", sc, 6);
        String txt = prompt("Enter the message: ", sc, 1);
        String jti = prompt("Replace J with I? y/n: ", sc, 1);
 
        boolean changeJtoI = jti.equalsIgnoreCase("y");
 
        pc.createTable(key, changeJtoI);
 
        String enc = txt; // = pc.encode(pc.prepareText(txt, changeJtoI));
 
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
