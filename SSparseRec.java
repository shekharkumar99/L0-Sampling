
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SSparseRec {

    private int r, s;

    // selected from pairwise independent family of hash functions
    private Hash[] hashFamily;
    private OneSparseRec[][] net;

    public SSparseRec(int r, int sparsity){
        this.r = r;
        this.s = sparsity;

        initialise();
    }
/*
 * initialize an array of hash family of si
 */
    private void initialise() {
    	hashFamily = new Hash[this.r];
        for(int i= 0; i < this.r;i++)
        {
        	this.hashFamily[i] = new Hash(this.s * 2);
        }
        this.net = new OneSparseRec[this.r][this.s *2];
        for(int i = 0 ; i < this.r; i++)
        	for(int j =0 ; j< this.s * 2 ; j++)
        		this.net[i][j] = new OneSparseRec();
        
    }
/*
 * @param: index and frequency of that value
 * takes random value from hash family
 * hash index then %this.r to keep in bounds simillarly for this.s*2
 * insert value of index and value on that position
 */
    public void add(int index, int value) {
    	int k = (int)StdRandom.uniform(this.r);
    	int pos1 = this.hashFamily[k].hash(index)%this.r;
    	int pos2 = this.hashFamily[k].hash(index)%(this.s * 2);
    	this.net[pos1][pos2].add(index, value);
    	
    	
   
    }
/*
 * To check if S-Sparse by checking from isOnesparse
 * if rerun more than increasse count
 */
    public boolean isSSparse() {
        int sparse_count= 0;
        for(int i = 0; i <this.r;i++) {
        	for(int j = 0 ; j <this.s * 2; j ++) {
        		if(net[i][j].isOneSparse())
        		{
        			sparse_count ++;
        		}
        	}
        }
        if(sparse_count == this.s)
        {
        	return true;
        }
        else
        {
        	return false;
        }
        
    }
/*
 * Function to recover the index value and frequency 
 */
    public String sparseRecTest() {
    	String result = " ";
    	int sparse_count= 0;
        for(int i = 0; i <this.r;i++) {
        	for(int j = 0 ; j <this.s * 2; j ++) {
        		if(net[i][j].isOneSparse())
        		{
        			sparse_count ++;
        		}
        	}
        }
        if(sparse_count == 0)
        {
        	return "zero /n";
        }
        else if(sparse_count > this.s)
        {
        	return "more";
        }
        else
        {
        	HashMap<Integer, Integer> hmap = recover();
            if (hmap != null) {
                Set set = hmap.entrySet();
                Iterator iterator = set.iterator();
                while (iterator.hasNext()) {
                    Map.Entry value = (Map.Entry) iterator.next();

                     result = result + value.getKey() + " " + value.getValue() + "\n";
                }

            }	
    }
        return result;
    }
/*
 * Function to map the index and frequency if one sparse
 */
    public HashMap<Integer,Integer> recover() {
    	HashMap<Integer,Integer> hash1 = new HashMap();
    	int counter = 0;
    	for(int i = 0; i <this.r;i++) {
        	for(int j = 0 ; j <this.s * 2; j ++)
        	{
        		if(net[i][j].isOneSparse()) {
        			int index = this.net[i][j].getIota()/this.net[i][j].getPhi();
        			if(hash1.containsKey(index)) {
        				hash1.put(index, hash1.get(index) + this.net[i][j].getPhi());
        			}
        			else {
        				hash1.put(index,this.net[i][j].getPhi());
        				
        			}
        			counter++;
        			
        		}
        		
        	}
    	}
    	if(counter != 0 && counter < s)
    	{
    		return hash1;
    		
    	}
    	else
    	{
    		return null;
    	}
       
    }

    // this might help
    @Override public String toString() {
        StringBuilder sb = new StringBuilder("SSparseRecoveryEstimator{array=[");
        int numCols = 2*s;

        for (int i=0; i<r; i++)
            for (int j=0; j<numCols; j++)
                sb.append(String.format("(%d,%d)=%s, ", i, j, net[i][j].toString()));
        sb.append("]}");

        return sb.toString();
    }




}
