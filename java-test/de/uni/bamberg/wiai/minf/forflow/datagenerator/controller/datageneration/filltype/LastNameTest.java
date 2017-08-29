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
public class LastNameTest {

    public LastNameTest() {
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
     * Test of getValueAt method, of class LastName.
     */
    @Test
    public void testGetValueAt()
    {
        System.out.println("getValueAt");
        int n = 0;
        LastName instance = new LastName();
        String expResult = "";
        String result = instance.getValueAt(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerObserverGenerator method, of class LastName.
     */
    @Test
    public void testRegisterObserverGenerator()
    {
        System.out.println("registerObserverGenerator");
        ObserverGenerator observer = null;
        LastName instance = new LastName();
        instance.registerObserverGenerator(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeObserverGenerator method, of class LastName.
     */
    @Test
    public void testRemoveObserverGenerator()
    {
        System.out.println("removeObserverGenerator");
        ObserverGenerator observer = null;
        LastName instance = new LastName();
        instance.removeObserverGenerator(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyObserverGenerator method, of class LastName.
     */
    @Test
    public void testNotifyObserverGenerator()
    {
        System.out.println("notifyObserverGenerator");
        boolean add = false;
        LastName instance = new LastName();
        instance.notifyObserverGenerator(add);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class LastName.
     */
    @Test
    public void testClone() throws Exception
    {
        System.out.println("clone");
        LastName instance = new LastName();
        Object expResult = null;
        Object result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFile method, of class LastName.
     */
    @Test
    public void testGetFile()
    {
        System.out.println("getFile");
        LastName instance = new LastName();
        String expResult = "";
        String result = instance.getFile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}