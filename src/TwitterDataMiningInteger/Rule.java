package TwitterDataMiningInteger;

import java.util.Arrays;

/**
 *
 * @author Fraser
 */
public class Rule {

    public int conditionSize = 11;
    public int[] condition;
    public int output;

    public Rule() {
        condition = new int[conditionSize];
        output = 0;
    }

    @Override
    public String toString() {
        return "Rule{" + "conditionSize=" + conditionSize + ", condition=" + Arrays.toString(condition) + ", output=" + output + '}';
    }
    
    
}
