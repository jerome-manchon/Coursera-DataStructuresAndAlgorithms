import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class W1A4_SuffixTree {
    
	List<Map<Character, TrieNode>> patternTrie;
	
	class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public List<String> computeSuffixTreeEdges(String text) {
        patternTrie = buildSuffixTrie(text);
        return getSuffix(0,"");
    }
    
    public List<String> getSuffix(Integer nodeId, String prefix){
    	List<String> result = new ArrayList<>();
    	
    	Map<Character, TrieNode> currentNode = patternTrie.get(nodeId);
    	String edge = prefix;
    	
    	while(currentNode.size() == 1){
    		edge += currentNode.entrySet().iterator().next().getKey();
    		currentNode = patternTrie.get(currentNode.entrySet().iterator().next().getValue().index);
    	}
    	if(!edge.equals("")){
    		result.add(edge);
    	}
    	
    	if(currentNode.size() > 0){
    		for(Entry<Character, TrieNode> entry : currentNode.entrySet()){
        		result.addAll(getSuffix(entry.getValue().index, ""+entry.getKey()));
    		}
    	}
    	
    	return result;
    }
    
    


    static public void main(String[] args) throws IOException {
        new W1A4_SuffixTree().run();
    }

    public void print(List<String> x) {
        for (String a : x) {
            System.out.println(a);
        }
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        List<String> edges = computeSuffixTreeEdges(text);
        print(edges);
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
    
    List<Map<Character, TrieNode>> buildSuffixTrie(String patterns) {
        List<Map<Character, TrieNode>> trie = new ArrayList<Map<Character, TrieNode>>();

        Integer nodeIndex;
        
        trie.add(new HashMap<Character, TrieNode>());
        
        for(int i = 0; i < patterns.length(); i++){
        	nodeIndex = 0;
        	for(int k = i; k < patterns.length(); k++){
	        	
	        	
        		TrieNode nextNode = trie.get(nodeIndex).get(patterns.charAt(k));
        		if(nextNode == null){
        			nextNode = new TrieNode(trie.size(), false);
        			trie.get(nodeIndex).put(patterns.charAt(k), nextNode);
        			trie.add(new HashMap<Character, TrieNode>());
        		}
        		
        		nextNode.isEnd = nextNode.isEnd?true:k == patterns.length() - 1;
        		nodeIndex = nextNode.index;
	        	
        	}
        }

        return trie;
    }
    
//    List<Map<Character, TrieNode>> buildTrie(String[] patterns) {
//        List<Map<Character, TrieNode>> trie = new ArrayList<Map<Character, TrieNode>>();
//
//        Integer nodeIndex;
//        
//        trie.add(new HashMap<Character, TrieNode>());
//        for(int k = 0; k < patterns.length; k++){
//	        for(int i = 0; k+i < patterns.length(); i++){
//	        	nodeIndex = 0;
//	        	for(int j = 0; j < patterns[i].length(); j++){
//	        		TrieNode nextNode = trie.get(nodeIndex).get(patterns[i].charAt(j));
//	        		if(nextNode == null){
//	        			nextNode = new TrieNode(trie.size(), false);
//	        			trie.get(nodeIndex).put(patterns[i].charAt(j), nextNode);
//	        			trie.add(new HashMap<Character, TrieNode>());
//	        		}
//	        		
//	        		nextNode.isEnd = nextNode.isEnd?true:j == patterns[i].length() - 1;
//	        		nodeIndex = nextNode.index;
//	        			
//	        	}
//	        }
//        }
//
//        return trie;
//    }
//    
    
    public class TrieNode{
		public Integer index;
		public boolean isEnd;
		
		public TrieNode(Integer index, boolean isEnd){
			this.index = index;
			this.isEnd = isEnd;
		}
	}
    
}
