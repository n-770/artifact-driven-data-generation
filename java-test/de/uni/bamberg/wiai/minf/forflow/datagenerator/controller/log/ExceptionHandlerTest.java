/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.log;

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
public class ExceptionHandlerTest {

    public ExceptionHandlerTest() {
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
     * Test of uncaughtException method, of class ExceptionHandler.
     */
    @Test
    public void testUncaughtException()
    {
        System.out.println("uncaughtException");
        Thread t = null;
        Throwable e = null;
        ExceptionHandler instance = new ExceptionHandler();
        instance.uncaughtException(t, e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}