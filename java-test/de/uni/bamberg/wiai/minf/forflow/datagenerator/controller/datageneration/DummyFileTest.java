/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration;

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
public class DummyFileTest {

    public DummyFileTest() {
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
     * Test of setPath method, of class DummyFile.
     */
    @Test
    public void testSetPath_URI()
    {
        System.out.println("setPath");
        URI path = null;
        DummyFile instance = new DummyFile();
        instance.setPath(path);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPath method, of class DummyFile.
     */
    @Test
    public void testSetPath_String()
    {
        System.out.println("setPath");
        String path = "";
        DummyFile instance = new DummyFile();
        instance.setPath(path);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPath method, of class DummyFile.
     */
    @Test
    public void testGetPath()
    {
        System.out.println("getPath");
        DummyFile instance = new DummyFile();
        String expResult = "";
        String result = instance.getPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFileName method, of class DummyFile.
     */
    @Test
    public void testSetFileName()
    {
        System.out.println("setFileName");
        String fileName = "";
        DummyFile instance = new DummyFile();
        instance.setFileName(fileName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFileName method, of class DummyFile.
     */
    @Test
    public void testGetFileName()
    {
        System.out.println("getFileName");
        DummyFile instance = new DummyFile();
        String expResult = "";
        String result = instance.getFileName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFileFormat method, of class DummyFile.
     */
    @Test
    public void testSetFileFormat()
    {
        System.out.println("setFileFormat");
        String fileFormat = "";
        DummyFile instance = new DummyFile();
        instance.setFileFormat(fileFormat);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFileFormat method, of class DummyFile.
     */
    @Test
    public void testGetFileFormat()
    {
        System.out.println("getFileFormat");
        DummyFile instance = new DummyFile();
        String expResult = "";
        String result = instance.getFileFormat();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPathWithFileName method, of class DummyFile.
     */
    @Test
    public void testGetPathWithFileName()
    {
        System.out.println("getPathWithFileName");
        DummyFile instance = new DummyFile();
        String expResult = "";
        String result = instance.getPathWithFileName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DummyFile.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        DummyFile instance = new DummyFile();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}