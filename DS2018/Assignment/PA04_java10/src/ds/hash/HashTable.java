package ds.hash;

public class HashTable{
	
	Item [] table;
	int c1, c2, c3;
	
	public HashTable(int n) {
		// fill in your code 
	}

	public void Create(int c1, int c2, int c3){
		// fill in your code 
	}
	
	public void Insert(String key, int value){
		// fill in your code 
		
		int index = 0;
		
		/** This is the sample output format. 
		 *  You'd better use this format to output your answer.
		 */
		System.out.println("Insert: (" + key + ", " + value + ") / " + "INDEX: " + index);
	}

	public void Delete(String key){
		// fill in your code 
		
		int value = 0;
		int index = 0;
		
		/** This is the sample output format. 
		 *  You'd better use this format to output your answer.
		 */
		System.out.println("Delete: (" + key + ", " + value + ") / " + "INDEX: " + index);
		System.out.println("Failed to find " + key);
	}
	
	public void Search(String key){
		// fill in your code 
		
		int value = 0;
		int index = 0;
		
		/** This is the sample output format. 
		 *  You'd better use this format to output your answer.
		 */
		System.out.println("Search: (" + key + ", " + value + ") / " + "INDEX: " + index);
		System.out.println("Failed to find " + key);
	}
}
