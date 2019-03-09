
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class DynamicL0Sampler implements Sampler<Integer> {
    // you may need to add additional class attributes

    // size of universe
    private int n;

    private int sparsity;

    // struct of sparse rec. structures
    private SSparseRec [] recovery;

    // selected from k-wise independent family of hash functions with k>=s/2
    private Hash hash;

    public DynamicL0Sampler(int n) {
        this.n = n;
        // fill in
    }

    public void add(Integer index, int value) {
        // fill in
    }

    public Integer output() {
        // fill in
        return null;
    }

    // more difficult: return a vector index and its count-value
    public Pair<Integer,Integer> outputVector() {
        // you do not have to fill in

        return null;
    }

    // this may help
    @Override public String toString() {
        StringBuilder sb = new StringBuilder("L0_samp: s-sparse strucs: %n");
        for(int i=0; i<recovery.length; i++) {
            sb.append("struc: " + i + " " + recovery[i] + "%n");
        }
        return sb.toString();
    }

}
