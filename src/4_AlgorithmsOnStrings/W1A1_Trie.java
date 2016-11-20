import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class W1A1_Trie {
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

    static public void main(String[] args) throws IOException {
        new W1A1_Trie().run();
    }

    public void print(List<Map<Character, Integer>> trie) {
        for (int i = 0; i < trie.size(); ++i) {
            Map<Character, Integer> node = trie.get(i);
            for (Map.Entry<Character, Integer> entry : node.entrySet()) {
                System.out.println(i + "->" + entry.getValue() + ":" + entry.getKey());
            }
        }
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        int patternsCount = scanner.nextInt();
        String[] patterns = new String[patternsCount];
        for (int i = 0; i < patternsCount; ++i) {
            patterns[i] = scanner.next();
        }
        List<Map<Character, Integer>> trie = buildTrie(patterns);
        print(trie);
    }
    
  
}
