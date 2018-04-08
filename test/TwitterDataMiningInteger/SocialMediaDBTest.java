/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterDataMiningInteger;

import java.util.ArrayList;
import java.util.HashMap;
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
public class SocialMediaDBTest {

    public SocialMediaDBTest() {
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
     * Test of getMaxId method, of class SocialMediaDB.
     */
    @Test
    public void testGetMaxId() {
        System.out.println("getMaxId");
        String tableName = "set1";
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB instance = new SocialMediaDB(wr);
        int expResult = 449;
        int result = instance.getMaxId(tableName);
        assertEquals(expResult, result);
    }

}
