import java.io.*;
import java.util.*;



public class W1A2_TrieMatching implements Runnable {

	List <Integer> solve (String text, int n, List <String> patterns) {
		List <Integer> result = new ArrayList <Integer> ();

		List<Map<Character, Integer>> patternTrie = buildTrie(patterns.toArray(new String[patterns.size()]));
		Integer currentTrieIndex = 0;
		
		for(int i = 0; i < text.length(); i++){
			currentTrieIndex = 0;
			for(int j = i; j < text.length(); j++){
				currentTrieIndex = patternTrie.get(currentTrieIndex).get(text.charAt(j));
				if(currentTrieIndex == null){
					break;
				}
				if(patternTrie.get(currentTrieIndex).size() == 0){
					result.add(i);
					break;
				}
			}
		}
		
		

		return result;
	}

	public void run () {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			String text = in.readLine ();
		 	int n = Integer.parseInt (in.readLine ());
		 	List <String> patterns = new ArrayList <String> ();
			for (int i = 0; i < n; i++) {
				patterns.add (in.readLine ());
			}

			List <Integer> ans = solve (text, n, patterns);

			for (int j = 0; j < ans.size (); j++) {
				System.out.print ("" + ans.get (j));
				System.out.print (j + 1 < ans.size () ? " " : "\n");
			}
		}
		catch (Throwable e) {
			e.printStackTrace ();
			System.exit (1);
		}
	}

	public static void main (String [] args) {
		new Thread (new W1A2_TrieMatching ()).start ();
	}
	
	List<Map<Character, Integer>> buildTrie(String[] patterns) {
        List<Map<Character, Integer>> trie = new ArrayList<Map<Character, Integer>>();

        Integer nodeIndex;
        Integer nextNode;
        trie.add(new HashMap<Character, Integer>());
        
        for(int i = 0; i < patterns.length; i++){
        	nodeIndex = 0;
        	for(int j = 0; j < patterns[i].length(); j++){
        		nextNode = trie.get(nodeIndex).get(patterns[i].charAt(j));
        		if(nextNode == null){
        			trie.get(nodeIndex).put(patterns[i].charAt(j), trie.size());
        			nextNode=trie.size();
        			trie.add(new HashMap<Character, Integer>());
        		}
        		nodeIndex = nextNode;
        	}
        }

        return trie;
    }
}
