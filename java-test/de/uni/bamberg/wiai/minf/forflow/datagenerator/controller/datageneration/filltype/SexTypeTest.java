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
public class SexTypeTest {

    public SexTypeTest() {
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
     * Test of values method, of class SexType.
     */
    @Test
    public void testValues()
    {
        System.out.println("values");
        SexType[] expResult = null;
        SexType[] result = SexType.values();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class SexType.
     */
    @Test
    public void testValueOf()
    {
        System.out.println("valueOf");
        String name = "";
        SexType expResult = null;
        SexType result = SexType.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSexType method, of class SexType.
     */
    @Test
    public void testGetSexType()
    {
        System.out.println("getSexType");
        SexType instance = null;
        String expResult = "";
        String result = instance.getSexType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}