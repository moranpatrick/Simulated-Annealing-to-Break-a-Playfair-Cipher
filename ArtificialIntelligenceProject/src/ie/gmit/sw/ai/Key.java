package ie.gmit.sw.ai;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Key {
	
	private String keyToShuffle = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	
	
	public String generateKey(){		
		// convert keyToShuffle to char[] to use Fisher–Yates Shuffle
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
		
		// Return the char[] as a String
		return new String(key);
	}
	
	
	

	

}
