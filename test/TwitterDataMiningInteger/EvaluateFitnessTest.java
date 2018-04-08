/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterDataMiningInteger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fraser
 */
public class EvaluateFitnessTest {
    
    public EvaluateFitnessTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of matchesCondition method, of class EvaluateFitness.
     */
    @Test
    public void testMatchesCondition() {
        System.out.println("matchesCondition");
        Data inputData = new Data();
        // Make fake input data
        for (int i = 0; i < inputData.variableSize ; i++) {
            inputData.variables[i] = 1;
        }
        // Make fake rule
        Rule rule = new Rule();
        for (int i = 0; i < rule.conditionSize ; i ++) {
            rule.condition[i] = 1;
        }
        int loopSize = 11;
        boolean expResult = true;
        boolean result = EvaluateFitness.matchesCondition(inputData, rule, loopSize);
        assertEquals(expResult, result);
    }

    /**
     * Test of matchesOutput method, of class EvaluateFitness.
     */
    @Test
    public void testMatchesOutput() {
        System.out.println("matchesOutput");
        Data inputData = new Data();
        // Make fake input data
        for (int i = 0; i < inputData.variableSize ; i++) {
            inputData.variables[i] = 1;
        }
        // Make fake rule
        Rule rule = new Rule();
        for (int i = 0; i < rule.conditionSize ; i ++) {
            rule.condition[i] = 1;
        }
        
        boolean expResult = true;
        boolean result = EvaluateFitness.matchesOutput(inputData, rule);
        assertEquals(expResult, result);
    }
    
}
