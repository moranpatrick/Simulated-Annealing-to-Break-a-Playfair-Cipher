package ie.gmit.sw.ai;

public class SimulatedAnnealing {
	
	private String parent;
	private Key k;
	private PlayfairCipher pc;
	private GramParser gp;
	private int transitions = 50000;
	private int temperature = 10;
	
	public SimulatedAnnealing(){
		pc = new PlayfairCipher();
		gp = new GramParser();
		k = new Key();	
	}
	
	public void StartSimulatedAnnealing(String cipherTxt){
		
		
		// Step 1 Get the 25 letter key
		//parent = k.generateKey();	
		//System.out.println(parent);
		String parent = "THEQUICKBROWNFXMPDVLAZYGS";
		// Step 2
		//String encyyptedText = cipherTxt;
		pc.createTable(parent);
		System.out.println(pc.decode(cipherTxt));
		
		for(int temp = temperature; temp > 0; temp--){
			
			for(int trans = transitions; trans > 0; trans--){
				
				
			}
					
		}
			
	}

}
