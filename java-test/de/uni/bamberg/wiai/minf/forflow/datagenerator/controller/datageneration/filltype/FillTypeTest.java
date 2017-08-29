/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

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
public class FillTypeTest {

    public FillTypeTest() {
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
     * Test of setFillBehaviour method, of class FillType.
     */
    @Test
    public void testSetFillBehaviour()
    {
        System.out.println("setFillBehaviour");
        FillBehaviour fb = null;
        FillType instance = new FillType();
        instance.setFillBehaviour(fb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFillBehaviour method, of class FillType.
     */
    @Test
    public void testGetFillBehaviour()
    {
        System.out.println("getFillBehaviour");
        FillType instance = new FillType();
        FillBehaviour expResult = null;
        FillBehaviour result = instance.getFillBehaviour();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueAt method, of class FillType.
     */
    @Test
    public void testGetValueAt()
    {
        System.out.println("getValueAt");
        int n = 0;
        FillType instance = new FillType();
        String expResult = "";
        String result = instance.getValueAt(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getN method, of class FillType.
     */
    @Test
    public void testGetN()
    {
        System.out.println("getN");
        FillType instance = new FillType();
        int expResult = 0;
        int result = instance.getN();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class FillType.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        FillType instance = new FillType();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}