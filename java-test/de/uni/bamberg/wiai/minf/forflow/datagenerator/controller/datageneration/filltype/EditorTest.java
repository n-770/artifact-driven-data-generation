/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

import java.net.URI;
import java.util.Iterator;
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
public class EditorTest {

    public EditorTest() {
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
     * Test of setValues method, of class Editor.
     */
    @Test
    public void testSetValues()
    {
        System.out.println("setValues");
        List<String> values = null;
        Editor instance = new Editor();
        instance.setValues(values);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValueIncremented method, of class Editor.
     */
    @Test
    public void testIsValueIncremented_boolean()
    {
        System.out.println("isValueIncremented");
        boolean increment = false;
        Editor instance = new Editor();
        instance.isValueIncremented(increment);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValueIncremented method, of class Editor.
     */
    @Test
    public void testIsValueIncremented_0args()
    {
        System.out.println("isValueIncremented");
        Editor instance = new Editor();
        boolean expResult = false;
        boolean result = instance.isValueIncremented();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueAt method, of class Editor.
     */
    @Test
    public void testGetValueAt()
    {
        System.out.println("getValueAt");
        int n = 0;
        Editor instance = new Editor();
        String expResult = "";
        String result = instance.getValueAt(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValues method, of class Editor.
     */
    @Test
    public void testGetValues()
    {
        System.out.println("getValues");
        Editor instance = new Editor();
        List<String> expResult = null;
        List<String> result = instance.getValues();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateN method, of class Editor.
     */
    @Test
    public void testCalculateN()
    {
        System.out.println("calculateN");
        URI file = null;
        Editor instance = new Editor();
        int expResult = 0;
        int result = instance.calculateN(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getN method, of class Editor.
     */
    @Test
    public void testGetN()
    {
        System.out.println("getN");
        Editor instance = new Editor();
        int expResult = 0;
        int result = instance.getN();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLowerBound method, of class Editor.
     */
    @Test
    public void testGetLowerBound()
    {
        System.out.println("getLowerBound");
        Editor instance = new Editor();
        int expResult = 0;
        int result = instance.getLowerBound();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUpperBound method, of class Editor.
     */
    @Test
    public void testGetUpperBound()
    {
        System.out.println("getUpperBound");
        Editor instance = new Editor();
        int expResult = 0;
        int result = instance.getUpperBound();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class Editor.
     */
    @Test
    public void testIterator()
    {
        System.out.println("iterator");
        Editor instance = new Editor();
        Iterator<String> expResult = null;
        Iterator<String> result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Editor.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        Editor instance = new Editor();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerObserverFillBehaviour method, of class Editor.
     */
    @Test
    public void testRegisterObserverFillBehaviour()
    {
        System.out.println("registerObserverFillBehaviour");
        ObserverFillBehaviour observer = null;
        Editor instance = new Editor();
        instance.registerObserverFillBehaviour(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeObserverFillBehaviour method, of class Editor.
     */
    @Test
    public void testRemoveObserverFillBehaviour()
    {
        System.out.println("removeObserverFillBehaviour");
        ObserverFillBehaviour observer = null;
        Editor instance = new Editor();
        instance.removeObserverFillBehaviour(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyOberverFillBehaviour method, of class Editor.
     */
    @Test
    public void testNotifyOberverFillBehaviour()
    {
        System.out.println("notifyOberverFillBehaviour");
        Editor instance = new Editor();
        instance.notifyOberverFillBehaviour();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}