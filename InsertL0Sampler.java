
import java.util.HashMap;
//import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class InsertL0Sampler implements Sampler<Integer> {
    // you will need to add additional class attributes

    // size of universe
    private int n;

    // selected from k-wise independent family of hash functions with k>=s/2
    private Hash hash;
    private  int min_val;
    private int val = 0;
    private int item_index = 0;
    private int r ;
/*
 * Make hash of size n
 */
    public InsertL0Sampler(int n) {
        this.n = n;
        this.hash = new Hash((int) Math.pow(this.n, 3));
        this.min_val = (int) Math.floor(Math.pow(this.n, 3));
        this.item_index = this.n;
        this.r = (int) Math.log(this.n);
    }
/*
 * add:
 * @param: takes index and value
 * runs the for loop from 0 to log(n) times
 * maintains a smallest value as n,
 * if hashed value of index is less than (n ^ 3)/(2 ^ -j) and less than min value then min value is changed.
 */
    public void add(Integer index, int value) {
        
    	for(int j =0; j< this.r;j++)
    	{
    	int check = (int)Math.floor(Math.pow(this.n, 3) /Math.pow( 2 ,j+1));
    	val = hash.hash(index);
    	if(check >= val )
    	{
    		//assigning the minimum hash value
    	if(val < min_val)
    	{
    		min_val = val;
    		this.item_index = index;
    		
    	}
    	}
    }
    }
/*
 * returns min_val
 */
    public Integer output() {
        

        return this.item_index;
    }
    /*public void setN(int n) {
        this.n = n;
        hash = new Hash((int) Math.floor(Math.pow(this.n, 3)));
        this.r = (int) (Math.log10(this.n) / Math.log10(2));
        this.min_val = (int) Math.floor(Math.pow(this.n, 3));
        this.item_index = this.n;
    }*/
}
