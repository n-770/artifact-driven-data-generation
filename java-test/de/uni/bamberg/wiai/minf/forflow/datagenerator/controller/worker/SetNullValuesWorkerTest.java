/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker;

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
public class SetNullValuesWorkerTest {

    public SetNullValuesWorkerTest() {
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
     * Test of doInBackground method, of class SetNullValuesWorker.
     */
    @Test
    public void testDoInBackground() throws Exception
    {
        System.out.println("doInBackground");
        SetNullValuesWorker instance = null;
        Void expResult = null;
        Void result = instance.doInBackground();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of done method, of class SetNullValuesWorker.
     */
    @Test
    public void testDone()
    {
        System.out.println("done");
        SetNullValuesWorker instance = null;
        instance.done();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}