package ds.hash;

public class HashTable{
	
	Item [] table;
	int c1, c2, c3;
	
	public HashTable(int n) {
		// fill in your code
        table = new Item[n];
	}

	public void Create(int c1, int c2, int c3){
		// fill in your code
		if (c1 == 0 && c2 == 0 && c3 == 0) return;
	    if (c1 < 0 || c2 < 0 || c3 < 0) return;
	    this.c1 = c1;
	    this.c2 = c2;
	    this.c3 = c3;
	}
	
	public void Insert(String key, int value){
		// fill in your code 
	    // This code cannot insert into "deleted" slots.
		int index = probeQuadratically(key, false);
	    if (table[index] == null) {
	        index = probeQuadratically(key, true);
	        table[index] = new Item(key, value);
        }
	    else table[index].insert(value);
		
		/** This is the sample output format. 
		 *  You'd better use this format to output your answer.
		 */
		System.out.println("Insert: (" + key + ", " + value + ") / " + "INDEX: " + index);
	}

	public void Delete(String key){
		// fill in your code 
		
		int index = probeQuadratically(key, false);
		if (table[index] == null)
            System.out.println("Failed to find " + key);
		else {
		    int value = table[index].popValue();
            System.out.println("Delete: (" + key + ", " + value + ") / " + "INDEX: " + index);
        }

		/** This is the sample output format. 
		 *  You'd better use this format to output your answer.
		 */
	}
	
	public void Search(String key){
		// fill in your code 
		
		int index = probeQuadratically(key, false);
		if (table[index] == null)
            System.out.println("Failed to find " + key);
		else {
		    int value = table[index].getValue();
            System.out.println("Search: (" + key + ", " + value + ") / " + "INDEX: " + index);
        }

		/** This is the sample output format. 
		 *  You'd better use this format to output your answer.
		 */
	}

	public int hash(String key) {
	    int hashCode = key.hashCode();
	    int M = table.length;
	    return hashCode % M;
    }

    public int getIdxQuadratically(int hashCode, int i) {
	    int jump = (c1 * i * i) + (c2 * i) + c3;
	    return hashCode + jump;
    }

    public int probeQuadratically(String key, boolean searchForDeleted) {
	    int M = table.length;
	    int hashCode = hash(key);
	    int idx = hashCode;
	    int i = 1;
	    while (notAtTerminal(searchForDeleted, idx) && !table[idx].getKey().equals(key)) {
	        idx = getIdxQuadratically(hashCode, i) % M;
	        i++;
        }
        return idx;
    }

    public boolean notAtTerminal(boolean searchForDeleted, int idx) {
	    if (searchForDeleted)
	        return table[idx] != null && !table[idx].getKey().equals("deleted");
	    else return table[idx] != null;
    }
}
