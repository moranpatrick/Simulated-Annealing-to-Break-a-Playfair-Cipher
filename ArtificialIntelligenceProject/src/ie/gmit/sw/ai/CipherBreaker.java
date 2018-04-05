package ie.gmit.sw.ai;

import java.io.IOException;
import java.util.Scanner;

public class CipherBreaker {

	public static void main(String[] args) throws IOException {
		int choice;
		String fileName = "";
		String cipherText = "";
		GramParser gp = new GramParser();
		PlayfairCipher pc = new PlayfairCipher();
		Scanner sc = new Scanner(System.in);
		Key k = new Key();
		SimulatedAnnealing sa = null;
		
		System.out.println(k.SwapLetters("THEQUICKBROWNFXMPDVLAZYGS", 4, 11));
		//System.out.println("Cols Flipped: " + k.FlipCols("THEQUICKBROWNFXMPDVLAZYGS"));
		//System.out.println(k.FlipRows("THEQUICKBROWNFXMPDVLAZYGS"));
	
		
		do{
			choice = menu();
			switch(choice){
			case 1:
				System.out.println("Please enter your filename: ");
				fileName = sc.next();
				
				cipherText = new File().readFile(fileName);
				System.out.println(cipherText);
						
				sa = new SimulatedAnnealing();
				//sa.StartSimulatedAnnealing("HEQEFIRCHITZMHUKOTXEDKWLHKHQVDSIEAKOZTXMTKOEEQSBXTDYHEUKUDBMKYZTFIRCEOMIYOZAEAMKIUZNQHTWDUOBVUDUPNOIEHEQKDLYWXNWILAZDYYOFTWAGADTVUDXXIEKITLKGKSIUYYOYETWDUOCHEFWHEKOABOKHUIMAREMWNFWFWIUNTTIOIOZAZTWFBRCHEXMTYDYHE");
				sa.StartSimulatedAnnealing(cipherText);
				break;
			case 2:

				break;
			default:
				if(choice != 2) System.out.println("Invalid choice - please try again!");
			}
		}while(choice != 2);		
;
		
		//double score = gp.scoreText("HFZQLYVEDWNITIQPQDUVHYLGXZHFNYBKPACAZQHFVQIQCUUVYCBXABQZQZURHQDZHBKDMVZQHXRGURLQHTXZQVDFYXZHRGGWHBYEGXNYYEGKYVHFLQDBWDVQIZEAUCAHHPQIBRRVBREZNYYQAHPUQDUVHYZXGNRDEOZWQFKCLZZHXVRDEOFEINQZZKZPKDYDCAMEEQUDBCLDBKPAEDUVYCHFZQQEUMSVPBUMURLQHTXZXZCUHTVTPHMDLDRGMDLDVBHCMGUVYCQVPVDMSZXQCPDIQZLQKDUBEMTCYDDBCQGDFEUKQZVPCYUHKDIABDFVFEETGKIDOZEFURLQUVYCKDPTACYQUCFUPVVBBREZZXDTZPWCMEDILYTHZHADMUDBGQHBKIFEMDEWIZRGVQHTKCNWIEGNHCPLLUDPCOFTQGDPNWBYHCHFQZITQVGKUVYCHFBDQVHVHCHFDIYXHFBRUMLZKDZDFQFHNYLGSAPLQCCAZQHCPCBODITCVBMUHFDIYXHFBRUMLZKDLULIDLIDDLQRKWZQACYQUZBHZBDUBHQZUKUZEDGWTVBXABQZQZBUFEUFFTQVEKZQINAHMEPTDFNYFBIZEXBRRVBREZTCILEVFBEDHUBRWDLYTHFHIZNYCPOVBDLIZQHFQPQDUVHYLGCUNYOKDMPCHTXZPCGCHFDYLQDBLTHPQEKCGKTIQIBRVQHBQNDBRXBZEFRFVUEDQYNYMZCPBDHYLKCUXF");
		//System.out.println(score);
	
		
		
//        String key = prompt("Enter an encryption key (min length 6): ", sc, 6);
//        String txt = prompt("Enter the message: ", sc, 1);
//        String jti = prompt("Replace J with I? y/n: ", sc, 1);
// 
//        boolean changeJtoI = jti.equalsIgnoreCase("y");
// 
//        PlayfairCipher.createTable(key, changeJtoI);
// 
//        String enc = txt; 
// 
//        System.out.printf("%nEncoded message: %n%s%n", enc);
//        System.out.printf("%nDecoded message: %n%s%n", pc.decode(enc));	
		
		sc.close();
	}
	
    private static String prompt(String promptText, Scanner sc, int minLen) {
        String s;
        do {
            System.out.print(promptText);
            s = sc.nextLine().trim();
        } while (s.length() < minLen);
        return s;
    }
    
    public static int menu() {

        int choice;
        Scanner input = new Scanner(System.in);

        System.out.println("|-------------------------|");
        System.out.println("|------- Main Menu -------|");
        System.out.println("|-------------------------|");
        System.out.println("1 - Decrypt a file");
        System.out.println("2 - Quit");

        choice = input.nextInt();
        return choice;    
    }


}
