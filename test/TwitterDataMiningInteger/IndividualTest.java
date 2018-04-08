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
public class IndividualTest {
    
    public IndividualTest() {
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
     * Test of toString method, of class Individual.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Individual instance = new Individual();
        // Make fake individual
        for (int i = 0; i < instance.geneSize ; i++) {
            instance.genes[i] = 1;
        }
        String expResult = "[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,"
                + " 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,"
                + " 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,"
                + " 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,"
                + " 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,"
                + " 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,"
                + " 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,"
                + " 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,"
                + " 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,"
                + " 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,"
                + " 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]" + " fitness = " + 0;
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
