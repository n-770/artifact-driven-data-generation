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
public class UnitTest {

    public UnitTest() {
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
     * Test of setWeightAndMeasurement method, of class Unit.
     */
    @Test
    public void testSetWeightAndMeasurement()
    {
        System.out.println("setWeightAndMeasurement");
        WeightAndMeasurement wam = null;
        Unit instance = new Unit();
        instance.setWeightAndMeasurement(wam);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWeightAndMeasurement method, of class Unit.
     */
    @Test
    public void testGetWeightAndMeasurement()
    {
        System.out.println("getWeightAndMeasurement");
        Unit instance = new Unit();
        WeightAndMeasurement expResult = null;
        WeightAndMeasurement result = instance.getWeightAndMeasurement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWeightAndMeasurementCustom method, of class Unit.
     */
    @Test
    public void testGetWeightAndMeasurementCustom()
    {
        System.out.println("getWeightAndMeasurementCustom");
        Unit instance = new Unit();
        WeightAndMeasurementCustomized expResult = null;
        WeightAndMeasurementCustomized result = instance.getWeightAndMeasurementCustom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Unit.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        Unit instance = new Unit();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCustomized method, of class Unit.
     */
    @Test
    public void testIsCustomized()
    {
        System.out.println("isCustomized");
        Unit instance = new Unit();
        boolean expResult = false;
        boolean result = instance.isCustomized();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}