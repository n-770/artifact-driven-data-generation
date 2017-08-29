/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit;

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
public class UnitFactoryTest {

    public UnitFactoryTest() {
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
     * Test of createCurrency method, of class UnitFactory.
     */
    @Test
    public void testCreateCurrency()
    {
        System.out.println("createCurrency");
        Currency expResult = null;
        Currency result = UnitFactory.createCurrency();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCustom method, of class UnitFactory.
     */
    @Test
    public void testCreateCustom()
    {
        System.out.println("createCustom");
        Custom expResult = null;
        Custom result = UnitFactory.createCustom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createLength method, of class UnitFactory.
     */
    @Test
    public void testCreateLength()
    {
        System.out.println("createLength");
        Length expResult = null;
        Length result = UnitFactory.createLength();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMass method, of class UnitFactory.
     */
    @Test
    public void testCreateMass()
    {
        System.out.println("createMass");
        Mass expResult = null;
        Mass result = UnitFactory.createMass();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMath method, of class UnitFactory.
     */
    @Test
    public void testCreateMath()
    {
        System.out.println("createMath");
        Math expResult = null;
        Math result = UnitFactory.createMath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMisc method, of class UnitFactory.
     */
    @Test
    public void testCreateMisc()
    {
        System.out.println("createMisc");
        Miscellaneous expResult = null;
        Miscellaneous result = UnitFactory.createMisc();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTempeature method, of class UnitFactory.
     */
    @Test
    public void testCreateTempeature()
    {
        System.out.println("createTempeature");
        Temperature expResult = null;
        Temperature result = UnitFactory.createTempeature();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTime method, of class UnitFactory.
     */
    @Test
    public void testCreateTime()
    {
        System.out.println("createTime");
        Time expResult = null;
        Time result = UnitFactory.createTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createUnit method, of class UnitFactory.
     */
    @Test
    public void testCreateUnit()
    {
        System.out.println("createUnit");
        Unit expResult = null;
        Unit result = UnitFactory.createUnit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}