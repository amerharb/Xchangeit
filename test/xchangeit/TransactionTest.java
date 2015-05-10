/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xchangeit;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author AbuIlyas
 */
public class TransactionTest {
    
    public TransactionTest() {
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
     * Test of getTransDate method, of class Transaction.
     */
    @Test
    public void testGetTransDate() {
        System.out.println("getTransDate");
        Transaction instance = null;
        Date expResult = null;
        Date result = instance.getTransDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNote method, of class Transaction.
     */
    @Test
    public void testGetNote() {
        System.out.println("getNote");
        Transaction instance = null;
        String expResult = "";
        String result = instance.getNote();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPk method, of class Transaction.
     */
    @Test
    public void testGetPk() {
        System.out.println("getPk");
        Transaction instance = null;
        int expResult = 0;
        int result = instance.getPk();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class TransactionImpl extends Transaction {

        public TransactionImpl() {
            super(0, null, "");
        }
    }
    
}
