/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit;

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
public class CurrencyTest {

    public CurrencyTest() {
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
     * Test of getName method, of class Currency.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        Currency instance = new Currency();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSelected method, of class Currency.
     */
    @Test
    public void testSetSelected()
    {
        System.out.println("setSelected");
        int selected = 0;
        Currency instance = new Currency();
        instance.setSelected(selected);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSelected method, of class Currency.
     */
    @Test
    public void testGetSelected()
    {
        System.out.println("getSelected");
        Currency instance = new Currency();
        String expResult = "";
        String result = instance.getSelected();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setList method, of class Currency.
     */
    @Test
    public void testSetList()
    {
        System.out.println("setList");
        List<String> list = null;
        Currency instance = new Currency();
        instance.setList(list);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addToList method, of class Currency.
     */
    @Test
    public void testAddToList()
    {
        System.out.println("addToList");
        String type = "";
        Currency instance = new Currency();
        instance.addToList(type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class Currency.
     */
    @Test
    public void testRemove()
    {
        System.out.println("remove");
        String type = "";
        Currency instance = new Currency();
        boolean expResult = false;
        boolean result = instance.remove(type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getList method, of class Currency.
     */
    @Test
    public void testGetList()
    {
        System.out.println("getList");
        Currency instance = new Currency();
        List<String> expResult = null;
        List<String> result = instance.getList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class Currency.
     */
    @Test
    public void testIterator()
    {
        System.out.println("iterator");
        Currency instance = new Currency();
        Iterator<String> expResult = null;
        Iterator<String> result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyObserverUnit method, of class Currency.
     */
    @Test
    public void testNotifyObserverUnit()
    {
        System.out.println("notifyObserverUnit");
        Currency instance = new Currency();
        instance.notifyObserverUnit();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerObserverUnit method, of class Currency.
     */
    @Test
    public void testRegisterObserverUnit()
    {
        System.out.println("registerObserverUnit");
        ObserverUnit observer = null;
        String name = "";
        Currency instance = new Currency();
        instance.registerObserverUnit(observer, name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeObserverUnit method, of class Currency.
     */
    @Test
    public void testRemoveObserverUnit()
    {
        System.out.println("removeObserverUnit");
        ObserverUnit observer = null;
        String name = "";
        Currency instance = new Currency();
        instance.removeObserverUnit(observer, name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Currency.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        Currency instance = new Currency();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}