/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.view;

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
public class ViewFactoryTest {

    public ViewFactoryTest() {
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
     * Test of createDataGeneratorActions method, of class ViewFactory.
     */
    @Test
    public void testCreateDataGeneratorActions()
    {
        System.out.println("createDataGeneratorActions");
        DataGeneratorView view = null;
        DataGeneratorActions expResult = null;
        DataGeneratorActions result = ViewFactory.createDataGeneratorActions(view);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}