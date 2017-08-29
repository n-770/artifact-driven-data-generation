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
public class ExternalFileTest {

    public ExternalFileTest() {
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
     * Test of setPathToFile method, of class ExternalFile.
     */
    @Test
    public void testSetPathToFile()
    {
        System.out.println("setPathToFile");
        URI file = null;
        ExternalFile instance = new ExternalFile();
        instance.setPathToFile(file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPathToFile method, of class ExternalFile.
     */
    @Test
    public void testGetPathToFile()
    {
        System.out.println("getPathToFile");
        ExternalFile instance = new ExternalFile();
        URI expResult = null;
        URI result = instance.getPathToFile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateN method, of class ExternalFile.
     */
    @Test
    public void testCalculateN()
    {
        System.out.println("calculateN");
        URI file = null;
        ExternalFile instance = new ExternalFile();
        int expResult = 0;
        int result = instance.calculateN(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getN method, of class ExternalFile.
     */
    @Test
    public void testGetN()
    {
        System.out.println("getN");
        ExternalFile instance = new ExternalFile();
        int expResult = 0;
        int result = instance.getN();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueAt method, of class ExternalFile.
     */
    @Test
    public void testGetValueAt()
    {
        System.out.println("getValueAt");
        int n = 0;
        ExternalFile instance = new ExternalFile();
        String expResult = "";
        String result = instance.getValueAt(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class ExternalFile.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        ExternalFile instance = new ExternalFile();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerObserverFillBehaviour method, of class ExternalFile.
     */
    @Test
    public void testRegisterObserverFillBehaviour()
    {
        System.out.println("registerObserverFillBehaviour");
        ObserverFillBehaviour observer = null;
        ExternalFile instance = new ExternalFile();
        instance.registerObserverFillBehaviour(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeObserverFillBehaviour method, of class ExternalFile.
     */
    @Test
    public void testRemoveObserverFillBehaviour()
    {
        System.out.println("removeObserverFillBehaviour");
        ObserverFillBehaviour observer = null;
        ExternalFile instance = new ExternalFile();
        instance.removeObserverFillBehaviour(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyOberverFillBehaviour method, of class ExternalFile.
     */
    @Test
    public void testNotifyOberverFillBehaviour()
    {
        System.out.println("notifyOberverFillBehaviour");
        ExternalFile instance = new ExternalFile();
        instance.notifyOberverFillBehaviour();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}