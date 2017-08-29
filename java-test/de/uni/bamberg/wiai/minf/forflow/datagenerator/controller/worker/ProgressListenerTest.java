/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker;

import java.beans.PropertyChangeEvent;
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
public class ProgressListenerTest {

    public ProgressListenerTest() {
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
     * Test of setProgress method, of class ProgressListener.
     */
    @Test
    public void testSetProgress()
    {
        System.out.println("setProgress");
        ProgressListener instance = null;
        instance.setProgress();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProgressValue method, of class ProgressListener.
     */
    @Test
    public void testSetProgressValue()
    {
        System.out.println("setProgressValue");
        int n = 0;
        ProgressListener instance = null;
        instance.setProgressValue(n);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class ProgressListener.
     */
    @Test
    public void testReset()
    {
        System.out.println("reset");
        ProgressListener instance = null;
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of propertyChange method, of class ProgressListener.
     */
    @Test
    public void testPropertyChange()
    {
        System.out.println("propertyChange");
        PropertyChangeEvent pce = null;
        ProgressListener instance = null;
        instance.propertyChange(pce);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}