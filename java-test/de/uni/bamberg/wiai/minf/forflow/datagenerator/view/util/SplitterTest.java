/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.view.util;

import java.io.File;
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
public class SplitterTest {

    public SplitterTest() {
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
     * Test of splitPathAndFileName method, of class Splitter.
     */
    @Test
    public void testSplitPathAndFileName()
    {
        System.out.println("splitPathAndFileName");
        URI uri = null;
        URI expResult = null;
        URI result = Splitter.splitPathAndFileName(uri);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of splitPathAndfileName method, of class Splitter.
     */
    @Test
    public void testSplitPathAndfileName()
    {
        System.out.println("splitPathAndfileName");
        URI uri = null;
        String expResult = "";
        String result = Splitter.splitPathAndfileName(uri);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFileExtension method, of class Splitter.
     */
    @Test
    public void testGetFileExtension_URI()
    {
        System.out.println("getFileExtension");
        URI file = null;
        String expResult = "";
        String result = Splitter.getFileExtension(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFileExtension method, of class Splitter.
     */
    @Test
    public void testGetFileExtension_File()
    {
        System.out.println("getFileExtension");
        File file = null;
        String expResult = "";
        String result = Splitter.getFileExtension(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFileExtension method, of class Splitter.
     */
    @Test
    public void testGetFileExtension_String()
    {
        System.out.println("getFileExtension");
        String file = "";
        String expResult = "";
        String result = Splitter.getFileExtension(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasFileExtension method, of class Splitter.
     */
    @Test
    public void testHasFileExtension_URI()
    {
        System.out.println("hasFileExtension");
        URI file = null;
        boolean expResult = false;
        boolean result = Splitter.hasFileExtension(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasFileExtension method, of class Splitter.
     */
    @Test
    public void testHasFileExtension_File()
    {
        System.out.println("hasFileExtension");
        File file = null;
        boolean expResult = false;
        boolean result = Splitter.hasFileExtension(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasFileExtension method, of class Splitter.
     */
    @Test
    public void testHasFileExtension_String()
    {
        System.out.println("hasFileExtension");
        String file = "";
        boolean expResult = false;
        boolean result = Splitter.hasFileExtension(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}