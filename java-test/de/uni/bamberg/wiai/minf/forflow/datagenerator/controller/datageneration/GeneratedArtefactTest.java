/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;
import java.net.URI;
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
public class GeneratedArtefactTest {

    public GeneratedArtefactTest() {
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
     * Test of setName method, of class GeneratedArtefact.
     */
    @Test
    public void testSetName()
    {
        System.out.println("setName");
        String name = "";
        GeneratedArtefact instance = new GeneratedArtefact();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class GeneratedArtefact.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        GeneratedArtefact instance = new GeneratedArtefact();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getID method, of class GeneratedArtefact.
     */
    @Test
    public void testGetID()
    {
        System.out.println("getID");
        GeneratedArtefact instance = new GeneratedArtefact();
        String expResult = "";
        String result = instance.getID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDummyFile method, of class GeneratedArtefact.
     */
    @Test
    public void testSetDummyFile_DummyFile()
    {
        System.out.println("setDummyFile");
        DummyFile dummyFile = null;
        GeneratedArtefact instance = new GeneratedArtefact();
        instance.setDummyFile(dummyFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDummyFile method, of class GeneratedArtefact.
     */
    @Test
    public void testSetDummyFile_URI_String()
    {
        System.out.println("setDummyFile");
        URI path = null;
        String fileName = "";
        GeneratedArtefact instance = new GeneratedArtefact();
        instance.setDummyFile(path, fileName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDummyFile method, of class GeneratedArtefact.
     */
    @Test
    public void testSetDummyFile_3args_1()
    {
        System.out.println("setDummyFile");
        URI path = null;
        String fileName = "";
        String fileFormat = "";
        GeneratedArtefact instance = new GeneratedArtefact();
        instance.setDummyFile(path, fileName, fileFormat);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDummyFile method, of class GeneratedArtefact.
     */
    @Test
    public void testSetDummyFile_String_String()
    {
        System.out.println("setDummyFile");
        String path = "";
        String fileName = "";
        GeneratedArtefact instance = new GeneratedArtefact();
        instance.setDummyFile(path, fileName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDummyFile method, of class GeneratedArtefact.
     */
    @Test
    public void testSetDummyFile_3args_2()
    {
        System.out.println("setDummyFile");
        String path = "";
        String fileName = "";
        String fileFormat = "";
        GeneratedArtefact instance = new GeneratedArtefact();
        instance.setDummyFile(path, fileName, fileFormat);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDummyFile method, of class GeneratedArtefact.
     */
    @Test
    public void testGetDummyFile()
    {
        System.out.println("getDummyFile");
        GeneratedArtefact instance = new GeneratedArtefact();
        DummyFile expResult = null;
        DummyFile result = instance.getDummyFile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArtefactType method, of class GeneratedArtefact.
     */
    @Test
    public void testSetArtefactType()
    {
        System.out.println("setArtefactType");
        ArtefactType type = null;
        GeneratedArtefact instance = new GeneratedArtefact();
        instance.setArtefactType(type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArtefactType method, of class GeneratedArtefact.
     */
    @Test
    public void testGetArtefactType()
    {
        System.out.println("getArtefactType");
        GeneratedArtefact instance = new GeneratedArtefact();
        ArtefactType expResult = null;
        ArtefactType result = instance.getArtefactType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFacets method, of class GeneratedArtefact.
     */
    @Test
    public void testSetFacets()
    {
        System.out.println("setFacets");
        List<Facet> facets = null;
        GeneratedArtefact instance = new GeneratedArtefact();
        instance.setFacets(facets);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addFacet method, of class GeneratedArtefact.
     */
    @Test
    public void testAddFacet()
    {
        System.out.println("addFacet");
        Facet facet = null;
        GeneratedArtefact instance = new GeneratedArtefact();
        instance.addFacet(facet);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFacets method, of class GeneratedArtefact.
     */
    @Test
    public void testGetFacets()
    {
        System.out.println("getFacets");
        GeneratedArtefact instance = new GeneratedArtefact();
        List<Facet> expResult = null;
        List<Facet> result = instance.getFacets();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class GeneratedArtefact.
     */
    @Test
    public void testIterator()
    {
        System.out.println("iterator");
        GeneratedArtefact instance = new GeneratedArtefact();
        Iterator<Facet> expResult = null;
        Iterator<Facet> result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class GeneratedArtefact.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        GeneratedArtefact instance = new GeneratedArtefact();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}