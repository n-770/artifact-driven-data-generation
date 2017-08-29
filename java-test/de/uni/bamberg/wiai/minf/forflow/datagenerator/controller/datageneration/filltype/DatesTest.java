/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

import java.util.Date;
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
public class DatesTest {

    public DatesTest() {
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
     * Test of setLowerDate method, of class Dates.
     */
    @Test
    public void testSetLowerDate()
    {
        System.out.println("setLowerDate");
        Date lower = null;
        Dates instance = new Dates();
        instance.setLowerDate(lower);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLowerDate method, of class Dates.
     */
    @Test
    public void testGetLowerDate()
    {
        System.out.println("getLowerDate");
        Dates instance = new Dates();
        Date expResult = null;
        Date result = instance.getLowerDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUpperDate method, of class Dates.
     */
    @Test
    public void testSetUpperDate()
    {
        System.out.println("setUpperDate");
        Date upper = null;
        Dates instance = new Dates();
        instance.setUpperDate(upper);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUpperDate method, of class Dates.
     */
    @Test
    public void testGetUpperDate()
    {
        System.out.println("getUpperDate");
        Dates instance = new Dates();
        Date expResult = null;
        Date result = instance.getUpperDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLevelOfDetail method, of class Dates.
     */
    @Test
    public void testSetLevelOfDetail()
    {
        System.out.println("setLevelOfDetail");
        DateInterval level = null;
        Dates instance = new Dates();
        instance.setLevelOfDetail(level);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLevelOfDetail method, of class Dates.
     */
    @Test
    public void testGetLevelOfDetail()
    {
        System.out.println("getLevelOfDetail");
        Dates instance = new Dates();
        DateInterval expResult = null;
        DateInterval result = instance.getLevelOfDetail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueAt method, of class Dates.
     */
    @Test
    public void testGetValueAt()
    {
        System.out.println("getValueAt");
        int n = 0;
        Dates instance = new Dates();
        String expResult = "";
        String result = instance.getValueAt(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerObserverGenerator method, of class Dates.
     */
    @Test
    public void testRegisterObserverGenerator()
    {
        System.out.println("registerObserverGenerator");
        ObserverGenerator observer = null;
        Dates instance = new Dates();
        instance.registerObserverGenerator(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeObserverGenerator method, of class Dates.
     */
    @Test
    public void testRemoveObserverGenerator()
    {
        System.out.println("removeObserverGenerator");
        ObserverGenerator observer = null;
        Dates instance = new Dates();
        instance.removeObserverGenerator(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyObserverGenerator method, of class Dates.
     */
    @Test
    public void testNotifyObserverGenerator()
    {
        System.out.println("notifyObserverGenerator");
        boolean add = false;
        Dates instance = new Dates();
        instance.notifyObserverGenerator(add);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class Dates.
     */
    @Test
    public void testClone() throws Exception
    {
        System.out.println("clone");
        Dates instance = new Dates();
        Object expResult = null;
        Object result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFile method, of class Dates.
     */
    @Test
    public void testGetFile()
    {
        System.out.println("getFile");
        Dates instance = new Dates();
        String expResult = "";
        String result = instance.getFile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}