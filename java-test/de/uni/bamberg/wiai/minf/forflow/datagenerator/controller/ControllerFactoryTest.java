/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller;

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
public class ControllerFactoryTest {

    public ControllerFactoryTest() {
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
     * Test of getController method, of class ControllerFactory.
     */
    @Test
    public void testGetController()
    {
        System.out.println("getController");
        Controller expResult = null;
        Controller result = ControllerFactory.getController();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getControllerBackgroundTask method, of class ControllerFactory.
     */
    @Test
    public void testGetControllerBackgroundTask()
    {
        System.out.println("getControllerBackgroundTask");
        ControllerBackgroundTask expResult = null;
        ControllerBackgroundTask result = ControllerFactory.getControllerBackgroundTask();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}