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
public class MiscellaneousTest {

    public MiscellaneousTest() {
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
     * Test of getName method, of class Miscellaneous.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        Miscellaneous instance = new Miscellaneous();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSelected method, of class Miscellaneous.
     */
    @Test
    public void testSetSelected()
    {
        System.out.println("setSelected");
        int selected = 0;
        Miscellaneous instance = new Miscellaneous();
        instance.setSelected(selected);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSelected method, of class Miscellaneous.
     */
    @Test
    public void testGetSelected()
    {
        System.out.println("getSelected");
        Miscellaneous instance = new Miscellaneous();
        String expResult = "";
        String result = instance.getSelected();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setList method, of class Miscellaneous.
     */
    @Test
    public void testSetList()
    {
        System.out.println("setList");
        List<String> list = null;
        Miscellaneous instance = new Miscellaneous();
        instance.setList(list);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addToList method, of class Miscellaneous.
     */
    @Test
    public void testAddToList()
    {
        System.out.println("addToList");
        String type = "";
        Miscellaneous instance = new Miscellaneous();
        instance.addToList(type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class Miscellaneous.
     */
    @Test
    public void testRemove_String()
    {
        System.out.println("remove");
        String type = "";
        Miscellaneous instance = new Miscellaneous();
        boolean expResult = false;
        boolean result = instance.remove(type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getList method, of class Miscellaneous.
     */
    @Test
    public void testGetList()
    {
        System.out.println("getList");
        Miscellaneous instance = new Miscellaneous();
        List<String> expResult = null;
        List<String> result = instance.getList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class Miscellaneous.
     */
    @Test
    public void testIterator()
    {
        System.out.println("iterator");
        Miscellaneous instance = new Miscellaneous();
        Iterator<String> expResult = null;
        Iterator<String> result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSelectedCustom method, of class Miscellaneous.
     */
    @Test
    public void testGetSelectedCustom()
    {
        System.out.println("getSelectedCustom");
        int selected = 0;
        Miscellaneous instance = new Miscellaneous();
        Custom expResult = null;
        Custom result = instance.getSelectedCustom(selected);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setListCustom method, of class Miscellaneous.
     */
    @Test
    public void testSetListCustom()
    {
        System.out.println("setListCustom");
        List<Custom> list = null;
        Miscellaneous instance = new Miscellaneous();
        instance.setListCustom(list);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addToListCustom method, of class Miscellaneous.
     */
    @Test
    public void testAddToListCustom()
    {
        System.out.println("addToListCustom");
        Custom custom = null;
        Miscellaneous instance = new Miscellaneous();
        instance.addToListCustom(custom);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class Miscellaneous.
     */
    @Test
    public void testRemove_Custom()
    {
        System.out.println("remove");
        Custom custom = null;
        Miscellaneous instance = new Miscellaneous();
        boolean expResult = false;
        boolean result = instance.remove(custom);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListCustom method, of class Miscellaneous.
     */
    @Test
    public void testGetListCustom()
    {
        System.out.println("getListCustom");
        Miscellaneous instance = new Miscellaneous();
        List<Custom> expResult = null;
        List<Custom> result = instance.getListCustom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iteratorCustom method, of class Miscellaneous.
     */
    @Test
    public void testIteratorCustom()
    {
        System.out.println("iteratorCustom");
        Miscellaneous instance = new Miscellaneous();
        Iterator<Custom> expResult = null;
        Iterator<Custom> result = instance.iteratorCustom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyObserverUnit method, of class Miscellaneous.
     */
    @Test
    public void testNotifyObserverUnit()
    {
        System.out.println("notifyObserverUnit");
        Miscellaneous instance = new Miscellaneous();
        instance.notifyObserverUnit();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerObserverUnit method, of class Miscellaneous.
     */
    @Test
    public void testRegisterObserverUnit()
    {
        System.out.println("registerObserverUnit");
        ObserverUnit observer = null;
        String name = "";
        Miscellaneous instance = new Miscellaneous();
        instance.registerObserverUnit(observer, name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyObserverUnitCustom method, of class Miscellaneous.
     */
    @Test
    public void testNotifyObserverUnitCustom()
    {
        System.out.println("notifyObserverUnitCustom");
        Miscellaneous instance = new Miscellaneous();
        instance.notifyObserverUnitCustom();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeObserverUnit method, of class Miscellaneous.
     */
    @Test
    public void testRemoveObserverUnit()
    {
        System.out.println("removeObserverUnit");
        ObserverUnit observer = null;
        String name = "";
        Miscellaneous instance = new Miscellaneous();
        instance.removeObserverUnit(observer, name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerObserverUnitCustom method, of class Miscellaneous.
     */
    @Test
    public void testRegisterObserverUnitCustom()
    {
        System.out.println("registerObserverUnitCustom");
        ObserverUnitCustom observer = null;
        String name = "";
        Miscellaneous instance = new Miscellaneous();
        instance.registerObserverUnitCustom(observer, name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeObserverUnitCustom method, of class Miscellaneous.
     */
    @Test
    public void testRemoveObserverUnitCustom()
    {
        System.out.println("removeObserverUnitCustom");
        ObserverUnitCustom observer = null;
        String name = "";
        Miscellaneous instance = new Miscellaneous();
        instance.removeObserverUnitCustom(observer, name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}