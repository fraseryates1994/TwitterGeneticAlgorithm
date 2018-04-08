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
public class ResultsTest {
    
    public ResultsTest() {
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
     * Test of getRuleBase method, of class Results.
     */
    @Test
    public void testGetRuleBase() {
        System.out.println("getRuleBase");
        Results instance = new Results("aaa",0.1,1,"10/100");
        String expResult = "aaa";
        String result = instance.getRuleBase();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMutationRate method, of class Results.
     */
    @Test
    public void testGetMutationRate() {
        System.out.println("getMutationRate");
        Results instance = new Results("aaa",0.1,1,"10/100");
        double expResult = 0.1;
        double result = instance.getMutationRate();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getPopulationSize method, of class Results.
     */
    @Test
    public void testGetPopulationSize() {
        System.out.println("getPopulationSize");
        Results instance = new Results("aaa",0.1,1,"10/100");
        int expResult = 1;
        int result = instance.getPopulationSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFitness method, of class Results.
     */
    @Test
    public void testGetFitness() {
        System.out.println("getFitness");
        Results instance = new Results("aaa",0.1,1,"10/100");
        String expResult = "10/100";
        String result = instance.getFitness();
        assertEquals(expResult, result);
    }
    
}
