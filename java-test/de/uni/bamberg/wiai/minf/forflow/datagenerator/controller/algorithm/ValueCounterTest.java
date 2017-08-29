/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.algorithm;

import java.net.URI;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dark Angel
 */
public class ValueCounterTest {

    public ValueCounterTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of calculateN method, of class ValueCounter.
     */
    @Test
    public void testCalculateN()
    {
        System.out.println("calculateN");
        URI file = null;
        int expResult = 0;
        int result = ValueCounter.calculateN(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getN method, of class ValueCounter.
     */
    @Test
    public void testGetN()
    {
        System.out.println("getN");
        URI file = null;
        int[] expResult = null;
        int[] result = ValueCounter.getN(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}