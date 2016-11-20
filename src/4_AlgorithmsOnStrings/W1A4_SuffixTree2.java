import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class W1A4_SuffixTree2 {
    
	List<Integer[]> patternTrie;
	
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
	
	int letterToIndex (char letter)
	{
		switch (letter)
		{
			case 'A': return 0;
			case 'C': return 1;
			case 'G': return 2;
			case 'T': return 3;
			case '$': return 4;
			default: assert (false); return -1;
		}
	}
	
	char indexToLetter (int letter)
	{
		switch (letter)
		{
			case 0: return 'A';
			case 1: return 'C';
			case 2: return 'G';
			case 3: return 'T';
			case 4: return '$';
			default: assert (false); return 'X';
		}
	}
	
	int countNonNullValues(Integer[] array){
		int nonNullCounter = 0;
		for(Integer x : array){
			if(x != null){
				nonNullCounter++;
			}
		}
		
		return nonNullCounter;
	}
	
	

    public List<String> computeSuffixTreeEdges(String text) {
        patternTrie = buildSuffixTrie(text);
        return getSuffix(0,"");
    }
    
    public List<String> getSuffix(Integer nodeId, String prefix){
    	List<String> result = new ArrayList<>();
    	
    	Integer[] currentNode = patternTrie.get(nodeId);
    	String edge = prefix;
    	
    	
    	while(countNonNullValues(currentNode) == 1){
    		for(int i = 0; i < 5; i++){
    			if(currentNode[i] != null){
    				edge += indexToLetter(i);
    				currentNode = patternTrie.get(currentNode[i]);
    				break;
    			}
    		}
    	}
    	if(!edge.equals("")){
    		result.add(edge);
    	}
    	
    	if(countNonNullValues(currentNode) > 0){
    		for(int i = 0; i<5; i++){
    			if(currentNode[i] != null){
    				result.addAll(getSuffix(currentNode[i], ""+indexToLetter(i)));
    			}
    		}
    			
    	}
    	
    	return result;
    }
    
    


    static public void main(String[] args) throws IOException {
        new W1A4_SuffixTree2().run();
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
    
    List<Integer[]> buildSuffixTrie(String patterns) {
        List<Integer[]> trie = new ArrayList<Integer[]>();

        Integer nodeIndex;
        
        trie.add(new Integer[5]);
        
        for(int i = 0; i < patterns.length(); i++){
        	nodeIndex = 0;
        	for(int k = i; k < patterns.length(); k++){
	        	Integer nextNode = trie.get(nodeIndex)[letterToIndex(patterns.charAt(k))];
        		
	        	if(nextNode == null){
        			trie.get(nodeIndex)[letterToIndex(patterns.charAt(k))] = trie.size();
        			nextNode = trie.size();
        			trie.add(new Integer[5]);
        		}
        		
        		nodeIndex = nextNode;
	        	
        	}
        }

        return trie;
    }
    

    
}
