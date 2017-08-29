/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.GeneratedArtefact;
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
public class PhysicalDummyFileTest {

    public PhysicalDummyFileTest() {
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
     * Test of createDummyFile method, of class PhysicalDummyFile.
     */
    @Test
    public void testCreateDummyFile_URI()
    {
        System.out.println("createDummyFile");
        URI file = null;
        PhysicalDummyFile instance = new PhysicalDummyFile();
        boolean expResult = false;
        boolean result = instance.createDummyFile(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDummyFile method, of class PhysicalDummyFile.
     */
    @Test
    public void testCreateDummyFile_String()
    {
        System.out.println("createDummyFile");
        String file = "";
        PhysicalDummyFile instance = new PhysicalDummyFile();
        boolean expResult = false;
        boolean result = instance.createDummyFile(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDummyFile method, of class PhysicalDummyFile.
     */
    @Test
    public void testCreateDummyFile_String_GeneratedArtefact()
    {
        System.out.println("createDummyFile");
        String file = "";
        GeneratedArtefact generatedArtefact = null;
        PhysicalDummyFile instance = new PhysicalDummyFile();
        boolean expResult = false;
        boolean result = instance.createDummyFile(file, generatedArtefact);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDummyFile method, of class PhysicalDummyFile.
     */
    @Test
    public void testCreateDummyFile_URI_GeneratedArtefact()
    {
        System.out.println("createDummyFile");
        URI file = null;
        GeneratedArtefact generatedArtefact = null;
        PhysicalDummyFile instance = new PhysicalDummyFile();
        boolean expResult = false;
        boolean result = instance.createDummyFile(file, generatedArtefact);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}