/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator;

import java.util.EventObject;
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
public class DataGeneratorTest {

    public DataGeneratorTest() {
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
     * Test of initialize method, of class DataGenerator.
     */
    @Test
    public void testInitialize()
    {
        System.out.println("initialize");
        String[] arg0 = null;
        DataGenerator instance = new DataGenerator();
        instance.initialize(arg0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startup method, of class DataGenerator.
     */
    @Test
    public void testStartup()
    {
        System.out.println("startup");
        DataGenerator instance = new DataGenerator();
        instance.startup();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ready method, of class DataGenerator.
     */
    @Test
    public void testReady()
    {
        System.out.println("ready");
        DataGenerator instance = new DataGenerator();
        instance.ready();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exit method, of class DataGenerator.
     */
    @Test
    public void testExit()
    {
        System.out.println("exit");
        EventObject eo = null;
        DataGenerator instance = new DataGenerator();
        instance.exit(eo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shutdown method, of class DataGenerator.
     */
    @Test
    public void testShutdown()
    {
        System.out.println("shutdown");
        DataGenerator instance = new DataGenerator();
        instance.shutdown();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getApplication method, of class DataGenerator.
     */
    @Test
    public void testGetApplication()
    {
        System.out.println("getApplication");
        DataGenerator expResult = null;
        DataGenerator result = DataGenerator.getApplication();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class DataGenerator.
     */
    @Test
    public void testMain()
    {
        System.out.println("main");
        String[] args = null;
        DataGenerator.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}