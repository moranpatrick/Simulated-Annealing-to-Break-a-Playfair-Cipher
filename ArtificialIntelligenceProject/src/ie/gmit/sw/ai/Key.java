package ie.gmit.sw.ai;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Key {
	
	private String key = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	private char[] randomKey = key.toCharArray();
	
	public char[] generateKey(){		
		randomKey = shuffle(randomKey);
		
		return randomKey;
	}
	
	public char[] shuffle(char[] key) {
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
		return key;
	}
	

}
