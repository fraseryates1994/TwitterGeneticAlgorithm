
package TwitterDataMiningInteger;


import static TwitterDataMiningInteger.main.ruleSize;
import java.util.Arrays;

/**
 *
 * @author Fraser
 */
public class Individual {

    public int geneSize = ruleSize * (11 + 1); // rule size * (condition size + output size)
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
