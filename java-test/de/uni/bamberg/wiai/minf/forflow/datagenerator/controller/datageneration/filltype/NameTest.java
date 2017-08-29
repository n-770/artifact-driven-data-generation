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
public class NameTest {

    public NameTest() {
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
     * Test of getValueAt method, of class Name.
     */
    @Test
    public void testGetValueAt_int()
    {
        System.out.println("getValueAt");
        int n = 0;
        Name instance = new Name();
        String expResult = "";
        String result = instance.getValueAt(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueAt2 method, of class Name.
     */
    @Test
    public void testGetValueAt2()
    {
        System.out.println("getValueAt2");
        int n = 0;
        Name instance = new Name();
        String expResult = "";
        String result = instance.getValueAt2(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueAt method, of class Name.
     */
    @Test
    public void testGetValueAt_int_int()
    {
        System.out.println("getValueAt");
        int n = 0;
        int m = 0;
        Name instance = new Name();
        String expResult = "";
        String result = instance.getValueAt(n, m);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSex method, of class Name.
     */
    @Test
    public void testSetSex()
    {
        System.out.println("setSex");
        SexType sex = null;
        Name instance = new Name();
        instance.setSex(sex);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSex method, of class Name.
     */
    @Test
    public void testGetSex()
    {
        System.out.println("getSex");
        Name instance = new Name();
        SexType expResult = null;
        SexType result = instance.getSex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPath2 method, of class Name.
     */
    @Test
    public void testSetPath2()
    {
        System.out.println("setPath2");
        URI file = null;
        Name instance = new Name();
        instance.setPath2(file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPath2 method, of class Name.
     */
    @Test
    public void testGetPath2()
    {
        System.out.println("getPath2");
        Name instance = new Name();
        URI expResult = null;
        URI result = instance.getPath2();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getN2 method, of class Name.
     */
    @Test
    public void testGetN2()
    {
        System.out.println("getN2");
        Name instance = new Name();
        int expResult = 0;
        int result = instance.getN2();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerObserverGenerator method, of class Name.
     */
    @Test
    public void testRegisterObserverGenerator()
    {
        System.out.println("registerObserverGenerator");
        ObserverGenerator observer = null;
        Name instance = new Name();
        instance.registerObserverGenerator(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeObserverGenerator method, of class Name.
     */
    @Test
    public void testRemoveObserverGenerator()
    {
        System.out.println("removeObserverGenerator");
        ObserverGenerator observer = null;
        Name instance = new Name();
        instance.removeObserverGenerator(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyObserverGenerator method, of class Name.
     */
    @Test
    public void testNotifyObserverGenerator()
    {
        System.out.println("notifyObserverGenerator");
        boolean add = false;
        Name instance = new Name();
        instance.notifyObserverGenerator(add);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class Name.
     */
    @Test
    public void testClone() throws Exception
    {
        System.out.println("clone");
        Name instance = new Name();
        Object expResult = null;
        Object result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFile method, of class Name.
     */
    @Test
    public void testGetFile()
    {
        System.out.println("getFile");
        Name instance = new Name();
        String expResult = "";
        String result = instance.getFile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}