import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class W1A3_TrieMatchingExtended implements Runnable {
	

	List <Integer> solve (String text, int n, List <String> patterns) {
		List <Integer> result = new ArrayList <Integer> ();

		List<Map<Character, TrieNode>> patternTrie = buildTrie(patterns.toArray(new String[patterns.size()]));
		//Integer currentTrieIndex = 0;
		
		TrieNode currentTrieNode;
		
//		for(int i = 0; i < patternTrie.size(); i++){
//			System.out.println(i + " : ");
//			for(Entry<Character, TrieNode> entry : patternTrie.get(i).entrySet()){
//				System.out.println(" > "+entry.getKey()+" - "+entry.getValue().index + (entry.getValue().isEnd?"|":""));
//			}
//		}
		
		
		for(int i = 0; i < text.length(); i++){
			currentTrieNode = new TrieNode(0,false);
			//currentTrieIndex = 0;
			for(int j = i; j < text.length(); j++){
				currentTrieNode = patternTrie.get(currentTrieNode.index).get(text.charAt(j));
				//currentTrieIndex = patternTrie.get(currentTrieIndex).get(text.charAt(j));
				
				if(currentTrieNode == null){
					break;
				}
				if(currentTrieNode.isEnd || patternTrie.get(currentTrieNode.index).size() == 0){
					result.add(i);
					break;
				}
			}
		}
		// write your code here

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
		new Thread (new W1A3_TrieMatchingExtended ()).start ();
	}
	
	List<Map<Character, TrieNode>> buildTrie(String[] patterns) {
        List<Map<Character, TrieNode>> trie = new ArrayList<Map<Character, TrieNode>>();

        Integer nodeIndex;
        
        trie.add(new HashMap<Character, TrieNode>());
        
        for(int i = 0; i < patterns.length; i++){
        	nodeIndex = 0;
        	for(int j = 0; j < patterns[i].length(); j++){
        		TrieNode nextNode = trie.get(nodeIndex).get(patterns[i].charAt(j));
        		if(nextNode == null){
        			nextNode = new TrieNode(trie.size(), false);
        			trie.get(nodeIndex).put(patterns[i].charAt(j), nextNode);
        			trie.add(new HashMap<Character, TrieNode>());
        		}
        		
        		nextNode.isEnd = nextNode.isEnd?true:j == patterns[i].length() - 1;
        		nodeIndex = nextNode.index;
        			
        	}
        }

        return trie;
    }
	
	public class TrieNode{
		public Integer index;
		public boolean isEnd;
		
		public TrieNode(Integer index, boolean isEnd){
			this.index = index;
			this.isEnd = isEnd;
		}
	}
}
