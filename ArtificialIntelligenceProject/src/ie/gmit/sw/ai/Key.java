package ie.gmit.sw.ai;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates a Random 25 Character Key and Makes Small changes to a key provided.
 * @author Patrick Moran G00179039
 */
public class Key {
	
	private String keyToShuffle = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	
	/**
	 * Generates a randomly shuffled key using The Fisher Yates Shuffle
	 * @return String 25 Char Key
	 */
	public String generateKey(){		
		char[] key = keyToShuffle.toCharArray();
		int index;
		Random random = ThreadLocalRandom.current();
		for (int i = key.length - 1; i > 0; i--) {			
			index = random.nextInt(i + 1);
			if (index != i) {
				key[index] ^= key[i];
				key[i] ^= key[index];
				key[index] ^= key[i];
			}
		}
		return new String(key);
	}
	
	/**
	 * Randomly Makes Changes to The Key Provided
	 * 
	 * Swap single letters (90%)
	 * Swap random rows (2%)
	 * Swap columns (2%)
	 * Flip all rows (2%)
	 * Flip all columns (2%) 
	 * Reverse the whole key (2%)
	 * 
	 * @param key to randomly shuffle
	 * @return randomly shuffled key
	 */
	public String shuffleKey(String key){
		Random r = ThreadLocalRandom.current();
		int randomNum = r.nextInt(100) + 1;
		
		switch (randomNum) {
		case 1:
		case 2:	
			return SwapCols(key, r.nextInt(4), r.nextInt(4));
		case 3:
		case 4:
			return SwapRows(key, r.nextInt(4), r.nextInt(4));
		case 5:
		case 6:
			return FlipCols(key);
		case 7:
		case 8: 
			return FlipRows(key);
		case 9:
		case 10:
			return ReverseKey(key);
		default:	
			return SwapLetters(key, r.nextInt(24), r.nextInt(24));
		}	
	}
	/**
	 * Randomly picks two characters and swaps them
	 * @param k key to adjusted
	 * @param one first char to be swapped
	 * @param two second char to be swapped
	 * @return String with chars swapped
	 */
	public String SwapLetters(String k, int one, int two) {		
		char charArr[] = k.toCharArray();
        char temp = charArr[one];
        charArr[one] = charArr[two];
        charArr[two] = temp;
        return new String(charArr);	
	}

	/**
	 * Reverses The Key
	 * @param k key to adjusted
	 * @return Reversed String
	 */
	public String ReverseKey(String k) {
		return new StringBuilder(k).reverse().toString();
	}
	
	/**
	 * Flips The Rows in the Key
	 * @param k key to adjusted
	 * @return String with rows flipped
	 */
	public String FlipRows(String k) {
		char[] charArr = k.toCharArray();
		
		for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                charArr[i * 5 + j] = k.toCharArray()[(4 - i) * 5 + j];
            }
        }		
		return new String(charArr);	
	}
	
	/**
	 * Flips The Columns in the Key
	 * @param k key to adjusted
	 * @return String with columns flipped
	 */
	public String FlipCols(String k) {
		char[] charArr = k.toCharArray();
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				charArr[i * 5 + j] = k.toCharArray()[i * 5 + 4 - j];
			}
		}
		return new String(charArr);
	}
	
	/**
	 * Randomly Swaps two Rows in the Key
	 * @param k key to adjusted
	 * @param row1
	 * @param row2
	 * @return String with the rows swapped
	 */
	public String SwapRows(String k, int row1, int row2) {
		char[] charArr = k.toCharArray();
		
		for(int i = 0; i < 5; i++) {
			char temp = charArr[row1 * 5 + i];
			charArr[row1 * 5 + i] = charArr[row2 * 5 + i];
			charArr[row2 * 5 + i] = temp;
		}
		
		return new String(charArr);
	}

	/**
	 * Randomly Swaps two Colums in the Key
	 * @param k key to adjusted
	 * @param col1
	 * @param col2
	 * @return String with the Columns swapped
	 */
	public String SwapCols(String k, int col1, int col2) {
		char[] charArr = k.toCharArray();
		
		for(int i = 0; i < 5; i++){
			char temp = charArr[i * 5 + col1];
			charArr[i * 5 + col1] = charArr[i * 5 + col2];
			charArr[i * 5 + col2] = temp;
		}
		return new String(charArr);
	}	
}
