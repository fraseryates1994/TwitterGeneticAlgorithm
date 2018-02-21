
package TwitterDataMiningInteger;


import java.util.Arrays;

/**
 *
 * @author Fraser
 */
public class Individual {

    public int geneSize = 55; // rule size * (condition size + output size)
    int genes[];
    int fitness;

    public Individual() {
        genes = new int[geneSize];
        fitness = 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(genes) + " fitness = " + fitness;
    }

}
