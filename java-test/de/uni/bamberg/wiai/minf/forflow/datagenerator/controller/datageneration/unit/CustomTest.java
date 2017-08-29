/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit;

import java.util.Iterator;
import java.util.List;
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
public class CustomTest {

    public CustomTest() {
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
     * Test of setName method, of class Custom.
     */
    @Test
    public void testSetName()
    {
        System.out.println("setName");
        String name = "";
        Custom instance = new Custom();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Custom.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        Custom instance = new Custom();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setContent method, of class Custom.
     */
    @Test
    public void testSetContent()
    {
        System.out.println("setContent");
        List<String> list = null;
        Custom instance = new Custom();
        instance.setContent(list);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addToContent method, of class Custom.
     */
    @Test
    public void testAddToContent()
    {
        System.out.println("addToContent");
        String content = "";
        Custom instance = new Custom();
        instance.addToContent(content);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContent method, of class Custom.
     */
    @Test
    public void testGetContent()
    {
        System.out.println("getContent");
        Custom instance = new Custom();
        List<String> expResult = null;
        List<String> result = instance.getContent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class Custom.
     */
    @Test
    public void testIterator()
    {
        System.out.println("iterator");
        Custom instance = new Custom();
        Iterator<String> expResult = null;
        Iterator<String> result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}