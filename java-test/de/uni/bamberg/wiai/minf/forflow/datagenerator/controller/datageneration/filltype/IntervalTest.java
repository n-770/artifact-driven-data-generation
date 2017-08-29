/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

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
public class IntervalTest {

    public IntervalTest() {
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
     * Test of setInterval method, of class Interval.
     */
    @Test
    public void testSetInterval()
    {
        System.out.println("setInterval");
        String interval = "";
        Interval instance = new Interval();
        boolean expResult = false;
        boolean result = instance.setInterval(interval);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEndpointsOfInterval method, of class Interval.
     */
    @Test
    public void testGetEndpointsOfInterval()
    {
        System.out.println("getEndpointsOfInterval");
        Interval instance = new Interval();
        int[] expResult = null;
        int[] result = instance.getEndpointsOfInterval();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLowerBound method, of class Interval.
     */
    @Test
    public void testGetLowerBound()
    {
        System.out.println("getLowerBound");
        Interval instance = new Interval();
        int expResult = 0;
        int result = instance.getLowerBound();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUpperBound method, of class Interval.
     */
    @Test
    public void testGetUpperBound()
    {
        System.out.println("getUpperBound");
        Interval instance = new Interval();
        int expResult = 0;
        int result = instance.getUpperBound();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateN method, of class Interval.
     */
    @Test
    public void testCalculateN()
    {
        System.out.println("calculateN");
        URI file = null;
        Interval instance = new Interval();
        int expResult = 0;
        int result = instance.calculateN(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getN method, of class Interval.
     */
    @Test
    public void testGetN()
    {
        System.out.println("getN");
        Interval instance = new Interval();
        int expResult = 0;
        int result = instance.getN();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueAt method, of class Interval.
     */
    @Test
    public void testGetValueAt()
    {
        System.out.println("getValueAt");
        int n = 0;
        Interval instance = new Interval();
        String expResult = "";
        String result = instance.getValueAt(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Interval.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        Interval instance = new Interval();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerObserverFillBehaviour method, of class Interval.
     */
    @Test
    public void testRegisterObserverFillBehaviour()
    {
        System.out.println("registerObserverFillBehaviour");
        ObserverFillBehaviour observer = null;
        Interval instance = new Interval();
        instance.registerObserverFillBehaviour(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeObserverFillBehaviour method, of class Interval.
     */
    @Test
    public void testRemoveObserverFillBehaviour()
    {
        System.out.println("removeObserverFillBehaviour");
        ObserverFillBehaviour observer = null;
        Interval instance = new Interval();
        instance.removeObserverFillBehaviour(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyOberverFillBehaviour method, of class Interval.
     */
    @Test
    public void testNotifyOberverFillBehaviour()
    {
        System.out.println("notifyOberverFillBehaviour");
        Interval instance = new Interval();
        instance.notifyOberverFillBehaviour();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Interval.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        Interval instance = new Interval();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}