/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration;

import java.util.List;
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
public class FacetTest {

    public FacetTest() {
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
     * Test of setName method, of class Facet.
     */
    @Test
    public void testSetName()
    {
        System.out.println("setName");
        String name = "";
        Facet instance = new Facet();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Facet.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        Facet instance = new Facet();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValues method, of class Facet.
     */
    @Test
    public void testSetValues()
    {
        System.out.println("setValues");
        List<String> values = null;
        Facet instance = new Facet();
        instance.setValues(values);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addValue method, of class Facet.
     */
    @Test
    public void testAddValue()
    {
        System.out.println("addValue");
        String value = "";
        Facet instance = new Facet();
        instance.addValue(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class Facet.
     */
    @Test
    public void testGetValue()
    {
        System.out.println("getValue");
        Facet instance = new Facet();
        String expResult = "";
        String result = instance.getValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUnit method, of class Facet.
     */
    @Test
    public void testSetUnit()
    {
        System.out.println("setUnit");
        String unit = "";
        Facet instance = new Facet();
        instance.setUnit(unit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUnit method, of class Facet.
     */
    @Test
    public void testGetUnit()
    {
        System.out.println("getUnit");
        Facet instance = new Facet();
        String expResult = "";
        String result = instance.getUnit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueAndUnit method, of class Facet.
     */
    @Test
    public void testGetValueAndUnit()
    {
        System.out.println("getValueAndUnit");
        Facet instance = new Facet();
        String expResult = "";
        String result = instance.getValueAndUnit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Facet.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        Facet instance = new Facet();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}