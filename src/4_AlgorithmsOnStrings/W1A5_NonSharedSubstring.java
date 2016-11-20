import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class W1A5_NonSharedSubstring implements Runnable {
	String solve (String p, String q) {
		String result = p;
		
//		 List<String> pSuffix = new ArrayList<String>();
//	        // Implement this function yourself
//	        for(int i = 0; i < p.length(); i++){
//	        	pSuffix.add(p.substring(i));
//	        }
	        
	   	 List<String> qSuffix = new ArrayList<String>();
	        // Implement this function yourself
	        for(int i = 0; i < q.length(); i++){
	        	qSuffix.add(q.substring(i));
	        }
		
		
		//List<Map<Character, Integer>> pTrie = buildTrie(pSuffix);
		List<Map<Character, Integer>> qTrie = buildTrie(qSuffix);
		
		
		
		for(int i = 0; i < p.length(); i++){
			Integer nodeIndex = 0;
			for(int j = 0; i+j < p.length(); j++){
				//System.out.print("i = "+i+"; j = "+j+"; search = "+p.substring(i, i+j+1)+"; ");
				if(!qTrie.get(nodeIndex).containsKey(p.charAt(i+j))){
					
					if(j < result.length()-1){
						result = p.substring(i, i+j+1);
						//System.out.println("found = KO; saving !");
					}
					else{
						//System.out.println("found = KO");
					}
					break;
				}
				else{
					nodeIndex = qTrie.get(nodeIndex).get(p.charAt(i+j));
					//System.out.println("found = OK");
				}
			}
		}
		
		
		return result;
	}

	public void run () {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			String p = in.readLine ();
			String q = in.readLine ();

			String ans = solve (p, q);

			System.out.println (ans);
		}
		catch (Throwable e) {
			e.printStackTrace ();
			System.exit (1);
		}
	}

	public static void main (String [] args) {
		new Thread (new W1A5_NonSharedSubstring ()).start ();
	}
	
	List<Map<Character, Integer>> buildTrie(List<String> patterns) {
        List<Map<Character, Integer>> trie = new ArrayList<Map<Character, Integer>>();

        Integer nodeIndex;
        Integer nextNode;
        trie.add(new HashMap<Character, Integer>());
        
        for(int i = 0; i < patterns.size(); i++){
        	nodeIndex = 0;
        	for(int j = 0; j < patterns.get(i).length(); j++){
        		nextNode = trie.get(nodeIndex).get(patterns.get(i).charAt(j));
        		if(nextNode == null){
        			trie.get(nodeIndex).put(patterns.get(i).charAt(j), trie.size());
        			nextNode=trie.size();
        			trie.add(new HashMap<Character, Integer>());
        		}
        		nodeIndex = nextNode;
        	}
        }

        return trie;
    }
}
