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
public class DecodeIndividualTest {

    public DecodeIndividualTest() {
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
     * Test of deCodeIndividual method, of class DecodeIndividual.
     */
    @Test
    public void testDeCodeIndividual() {
        System.out.println("deCodeIndividual");
        Individual bestIndividual = new Individual();
        int ruleSize = 10;
        String expResult = "Rule 1\n"
                + "If Donald Trump says there's a witch hunt against him\n"
                + "If followers is in the range of 0 - 10\n"
                + "If reply is not favourited\n"
                + "If friends is in the range of 0 - 10\n"
                + "If location is in Africa\n"
                + "If user is not verified\n"
                + "If comment does not contains a swear word\n"
                + "If comment does not contain a positive word\n"
                + "If comment does not contain a negative word\n"
                + "If comment does not contain a positive emoji\n"
                + "If comment does not contain a negative emoji\n"
                + "THEN comment is negative\n"
                + "Rule 2\n"
                + "If Donald Trump says there's a witch hunt against him\n"
                + "If followers is in the range of 0 - 10\n"
                + "If reply is not favourited\n"
                + "If friends is in the range of 0 - 10\n"
                + "If location is in Africa\n"
                + "If user is not verified\n"
                + "If comment does not contains a swear word\n"
                + "If comment does not contain a positive word\n"
                + "If comment does not contain a negative word\n"
                + "If comment does not contain a positive emoji\n"
                + "If comment does not contain a negative emoji\n"
                + "THEN comment is negative\n"
                + "Rule 3\n"
                + "If Donald Trump says there's a witch hunt against him\n"
                + "If followers is in the range of 0 - 10\n"
                + "If reply is not favourited\n"
                + "If friends is in the range of 0 - 10\n"
                + "If location is in Africa\n"
                + "If user is not verified\n"
                + "If comment does not contains a swear word\n"
                + "If comment does not contain a positive word\n"
                + "If comment does not contain a negative word\n"
                + "If comment does not contain a positive emoji\n"
                + "If comment does not contain a negative emoji\n"
                + "THEN comment is negative\n"
                + "Rule 4\n"
                + "If Donald Trump says there's a witch hunt against him\n"
                + "If followers is in the range of 0 - 10\n"
                + "If reply is not favourited\n"
                + "If friends is in the range of 0 - 10\n"
                + "If location is in Africa\n"
                + "If user is not verified\n"
                + "If comment does not contains a swear word\n"
                + "If comment does not contain a positive word\n"
                + "If comment does not contain a negative word\n"
                + "If comment does not contain a positive emoji\n"
                + "If comment does not contain a negative emoji\n"
                + "THEN comment is negative\n"
                + "Rule 5\n"
                + "If Donald Trump says there's a witch hunt against him\n"
                + "If followers is in the range of 0 - 10\n"
                + "If reply is not favourited\n"
                + "If friends is in the range of 0 - 10\n"
                + "If location is in Africa\n"
                + "If user is not verified\n"
                + "If comment does not contains a swear word\n"
                + "If comment does not contain a positive word\n"
                + "If comment does not contain a negative word\n"
                + "If comment does not contain a positive emoji\n"
                + "If comment does not contain a negative emoji\n"
                + "THEN comment is negative\n"
                + "Rule 6\n"
                + "If Donald Trump says there's a witch hunt against him\n"
                + "If followers is in the range of 0 - 10\n"
                + "If reply is not favourited\n"
                + "If friends is in the range of 0 - 10\n"
                + "If location is in Africa\n"
                + "If user is not verified\n"
                + "If comment does not contains a swear word\n"
                + "If comment does not contain a positive word\n"
                + "If comment does not contain a negative word\n"
                + "If comment does not contain a positive emoji\n"
                + "If comment does not contain a negative emoji\n"
                + "THEN comment is negative\n"
                + "Rule 7\n"
                + "If Donald Trump says there's a witch hunt against him\n"
                + "If followers is in the range of 0 - 10\n"
                + "If reply is not favourited\n"
                + "If friends is in the range of 0 - 10\n"
                + "If location is in Africa\n"
                + "If user is not verified\n"
                + "If comment does not contains a swear word\n"
                + "If comment does not contain a positive word\n"
                + "If comment does not contain a negative word\n"
                + "If comment does not contain a positive emoji\n"
                + "If comment does not contain a negative emoji\n"
                + "THEN comment is negative\n"
                + "Rule 8\n"
                + "If Donald Trump says there's a witch hunt against him\n"
                + "If followers is in the range of 0 - 10\n"
                + "If reply is not favourited\n"
                + "If friends is in the range of 0 - 10\n"
                + "If location is in Africa\n"
                + "If user is not verified\n"
                + "If comment does not contains a swear word\n"
                + "If comment does not contain a positive word\n"
                + "If comment does not contain a negative word\n"
                + "If comment does not contain a positive emoji\n"
                + "If comment does not contain a negative emoji\n"
                + "THEN comment is negative\n"
                + "Rule 9\n"
                + "If Donald Trump says there's a witch hunt against him\n"
                + "If followers is in the range of 0 - 10\n"
                + "If reply is not favourited\n"
                + "If friends is in the range of 0 - 10\n"
                + "If location is in Africa\n"
                + "If user is not verified\n"
                + "If comment does not contains a swear word\n"
                + "If comment does not contain a positive word\n"
                + "If comment does not contain a negative word\n"
                + "If comment does not contain a positive emoji\n"
                + "If comment does not contain a negative emoji\n"
                + "THEN comment is negative\n"
                + "Rule 10\n"
                + "If Donald Trump says there's a witch hunt against him\n"
                + "If followers is in the range of 0 - 10\n"
                + "If reply is not favourited\n"
                + "If friends is in the range of 0 - 10\n"
                + "If location is in Africa\n"
                + "If user is not verified\n"
                + "If comment does not contains a swear word\n"
                + "If comment does not contain a positive word\n"
                + "If comment does not contain a negative word\n"
                + "If comment does not contain a positive emoji\n"
                + "If comment does not contain a negative emoji\n"
                + "THEN comment is negative\n";
        String result = DecodeIndividual.deCodeIndividual(bestIndividual, ruleSize);
        assertEquals(expResult, result);
    }

}
