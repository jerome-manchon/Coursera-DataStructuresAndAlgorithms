import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class W2A2_InverseBWT {
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

    String inverseBWT(String bwtString) {
    	
    	// 0. Initializing vars
    	
    	char[] lastColumnChar = bwtString.toCharArray();
        char[] firstColumnChar = Arrays.copyOf(lastColumnChar,lastColumnChar.length);
        Arrays.sort(firstColumnChar);
        
        Map<Character, List<Integer>> lastColumnIndexMap = new HashMap<Character, List<Integer>>();
        String rebuiltString = "";

        int[] firstColumnCharIndex = new int[firstColumnChar.length];
        
        //List<List<Integer>> listArray = new ArrayList<List<Integer>>();

        // 1. building last column to first column index map
        int firstColumnCharIndexLastValue = 1;
        
        for(int i = 0; i < firstColumnCharIndex.length; ++i) {
        	firstColumnCharIndexLastValue = i > 0 && firstColumnChar[i] == firstColumnChar[i-1] ? firstColumnCharIndexLastValue + 1 : 1;
            firstColumnCharIndex[i] = firstColumnCharIndexLastValue;
            
            if(!lastColumnIndexMap.containsKey(lastColumnChar[i])){
            	lastColumnIndexMap.put(lastColumnChar[i], new ArrayList<Integer>());
            }
            lastColumnIndexMap.get(lastColumnChar[i]).add(i);
        }
        
        
        //rebuiltString = ""+lastColumnChar[0];
        // 2. rebuilding string
        char currentChar = '$';
        int currentIndex = 1;
        while(rebuiltString.length() < bwtString.length()) {
        	Integer row = lastColumnIndexMap.get(currentChar).get(currentIndex-1);
        	 currentChar = firstColumnChar[row];
             currentIndex = firstColumnCharIndex[row];
             rebuiltString += currentChar;
        }
        
        return rebuiltString;
    }
    
    String inverseBWT2(String bwtString) {
    	
    	// 0. Initializing vars
    	
    	char[] lastColumnChar = bwtString.toCharArray();
        char[] firstColumnChar = Arrays.copyOf(lastColumnChar,lastColumnChar.length);
        Arrays.sort(firstColumnChar);
        
        int[] firstColumnCharFirstAppearance = new int[256];
        
        int[] firstColumnCharIndex = new int[firstColumnChar.length];
        
        // 1. building last column to first column index map
        int firstColumnCharIndexLastValue = 1;
        
        char currentChar = '\0';
        
        int[] lastColumnIndex = new int[firstColumnChar.length];
        int[] lastColumnCharCount = new int[256];
        
        
        
        
        for(int i = 0; i < bwtString.length(); ++i) {
        	
        	lastColumnIndex[i] = lastColumnCharCount[lastColumnChar[i]]++;
        	
        	firstColumnCharIndexLastValue = i > 0 && firstColumnChar[i] == firstColumnChar[i-1] ? firstColumnCharIndexLastValue + 1 : 1;
            firstColumnCharIndex[i] = firstColumnCharIndexLastValue;
        
            if(currentChar != firstColumnChar[i]){
            	currentChar = firstColumnChar[i];
            	firstColumnCharFirstAppearance[currentChar] = i;
            }
            
            
//            if(!lastColumnIndexMap.containsKey(lastColumnChar[i])){
//            	lastColumnIndexMap.put(lastColumnChar[i], new ArrayList<Integer>());
//            }
//            lastColumnIndexMap.get(lastColumnChar[i]).add(i);
        }
        
        
        StringBuilder rebuiltString = new StringBuilder(firstColumnChar[0]);
        int currentRow = 0;
        // 2. rebuilding string
        //currentChar = '$';
        //int currentIndex = 1;
        while(rebuiltString.length() < bwtString.length()) {
    		
        	//getting index of char in last column
        	currentRow = firstColumnCharFirstAppearance[lastColumnChar[currentRow]]+lastColumnIndex[currentRow];
        	
        	//Integer row = lastColumnIndexMap.get(currentChar).get(currentIndex-1);
        	//currentChar = firstColumnChar[row];
            //currentIndex = firstColumnCharIndex[row];
            rebuiltString.insert(0, firstColumnChar[currentRow]);;
        }
        
        return rebuiltString.toString();
    }
    

    static public void main(String[] args) throws IOException {
        new W2A2_InverseBWT().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(inverseBWT2(bwt));
    }
    
    
    
}
