import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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
    
    String inverseBWT2(String bwtString) {
    	
    	// 0. Initializing vars
    	
    	char[] lastColumnChar = bwtString.toCharArray();
        char[] firstColumnChar = Arrays.copyOf(lastColumnChar,lastColumnChar.length);
        Arrays.sort(firstColumnChar);
        int bwtStringLength = bwtString.length();
        
        int[] firstColumnCharFirstAppearance = new int[256];
        
        
        // 1. building last column to first column index map
        
        
        char currentChar = '\0';
        
        int[] lastColumnIndex = new int[firstColumnChar.length];
        int[] lastColumnCharCount = new int[256];
        
        for(int i = 0; i < bwtStringLength; ++i) {
        	
        	lastColumnIndex[i] = lastColumnCharCount[lastColumnChar[i]]++;
        	
            if(currentChar != firstColumnChar[i]){
            	currentChar = firstColumnChar[i];
            	firstColumnCharFirstAppearance[currentChar] = i;
            }

        }
        
        
        StringBuilder rebuiltString = new StringBuilder();
        rebuiltString.insert(0, firstColumnChar[0]);
        int currentRow = 0;
        // 2. rebuilding string
        for(int i = 1; i < bwtStringLength; i++){
        	currentRow = firstColumnCharFirstAppearance[lastColumnChar[currentRow]]+lastColumnIndex[currentRow];
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
