import java.math.BigInteger;

public class OneSparseRec {

    private int phi;
    private int iota;
    private int V;
    private int theta;
    private int p = 1073741789;
    private double z = StdRandom.uniform(p-1)+1;

    public OneSparseRec() {
    	this.phi = 0;
    	this.iota = 0;
    	this.V = 0;
    	this.theta =0;
    	
    	
    }

    public void add(int index, int value) {
    	this.iota = this.iota + (index*value);
    	this.phi = this.phi + value;
    	this.V = (int) (this.V + (value * Math.pow(index,2)));	
    	this.theta =  this.theta +(int) ((value * Math.pow(z,index) %p));
    	
    }
/*
 * The fingerprint test to check one sparse
 */
    public boolean isOneSparse() {
    	
    	if(this.phi == 0)
    	{
    		return false;
    	}
    	
    	else if(this.theta == (int)(this.phi *  (Math.pow(z, this.iota/this.phi)%p)))
        {
        	return true;
        }
        else
        {
        return false;
        }
        
        
    }
    
/*
 * one sparse test by using figerprint test
 */
    public String oneSparseTest() {
        // fill in
        if(this.phi == 0)
        {
        	return "zero";
        }
        else if(this.theta == this.phi * Math.pow(z, this.iota/this.phi)%p)
        {
        	String x = "Index: " + (this.iota/this.phi) + " Value: " + this.phi + "\n";
        	return x;
        }
        else
        	return "more";
    }

    // getters
    public int getPhi() {
        return this.phi;
    }

    public int getIota() {
        return this.iota;
    }

    // this might help
    @Override public boolean equals(Object otherObj) {
        if (!(otherObj instanceof OneSparseRec)) return false;
        else {
            OneSparseRec oner = (OneSparseRec) otherObj;
            return this.getIota() == oner.getIota()
                    && this.getPhi() == oner.getPhi();
        }
    }

    @Override public String toString() {
        if(this.isOneSparse()) {
            return "index = " + iota/phi + ",value = " + phi;
        } else if(phi == 0) {
            return "empty " + phi + " " + iota ;
        } else return "multiple " + "index = " + iota/phi +
                ",value = " + phi + this.isOneSparse();
    }


}
