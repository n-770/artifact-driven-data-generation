/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Unit;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.Attributes;

/**
 *
 * @author Dark Angel
 */
public class XMLStartupHandlerTest {

    public XMLStartupHandlerTest() {
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
     * Test of startElement method, of class XMLStartupHandler.
     */
    @Test
    public void testStartElement() throws Exception
    {
        System.out.println("startElement");
        String uri = "";
        String localName = "";
        String name = "";
        Attributes attributes = null;
        XMLStartupHandler instance = new XMLStartupHandler();
        instance.startElement(uri, localName, name, attributes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endElement method, of class XMLStartupHandler.
     */
    @Test
    public void testEndElement() throws Exception
    {
        System.out.println("endElement");
        String uri = "";
        String localName = "";
        String name = "";
        XMLStartupHandler instance = new XMLStartupHandler();
        instance.endElement(uri, localName, name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of characters method, of class XMLStartupHandler.
     */
    @Test
    public void testCharacters() throws Exception
    {
        System.out.println("characters");
        char[] ch = null;
        int start = 0;
        int length = 0;
        XMLStartupHandler instance = new XMLStartupHandler();
        instance.characters(ch, start, length);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArtefactType method, of class XMLStartupHandler.
     */
    @Test
    public void testGetArtefactType()
    {
        System.out.println("getArtefactType");
        XMLStartupHandler instance = new XMLStartupHandler();
        List<ArtefactType> expResult = null;
        List<ArtefactType> result = instance.getArtefactType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUnits method, of class XMLStartupHandler.
     */
    @Test
    public void testGetUnits()
    {
        System.out.println("getUnits");
        XMLStartupHandler instance = new XMLStartupHandler();
        List<Unit> expResult = null;
        List<Unit> result = instance.getUnits();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLogging method, of class XMLStartupHandler.
     */
    @Test
    public void testGetLogging()
    {
        System.out.println("getLogging");
        XMLStartupHandler instance = new XMLStartupHandler();
        boolean expResult = false;
        boolean result = instance.getLogging();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateMin method, of class XMLStartupHandler.
     */
    @Test
    public void testGetDateMin()
    {
        System.out.println("getDateMin");
        XMLStartupHandler instance = new XMLStartupHandler();
        int expResult = 0;
        int result = instance.getDateMin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateMax method, of class XMLStartupHandler.
     */
    @Test
    public void testGetDateMax()
    {
        System.out.println("getDateMax");
        XMLStartupHandler instance = new XMLStartupHandler();
        int expResult = 0;
        int result = instance.getDateMax();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}