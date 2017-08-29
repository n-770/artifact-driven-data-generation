/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker;

import javax.swing.JTree;
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
public class UpdateUnitTest {

    public UpdateUnitTest() {
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
     * Test of doInBackground method, of class UpdateUnit.
     */
    @Test
    public void testDoInBackground() throws Exception
    {
        System.out.println("doInBackground");
        UpdateUnit instance = null;
        JTree expResult = null;
        JTree result = instance.doInBackground();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of done method, of class UpdateUnit.
     */
    @Test
    public void testDone()
    {
        System.out.println("done");
        UpdateUnit instance = null;
        instance.done();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}