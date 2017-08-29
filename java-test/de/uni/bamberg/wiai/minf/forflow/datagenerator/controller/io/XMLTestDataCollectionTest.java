/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.GeneratedArtefact;
import java.net.URI;
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
public class XMLTestDataCollectionTest {

    public XMLTestDataCollectionTest() {
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
     * Test of save method, of class XMLTestDataCollection.
     */
    @Test
    public void testSave()
    {
        System.out.println("save");
        URI xml = null;
        URI xsd = null;
        List<GeneratedArtefact> collection = null;
        XMLTestDataCollection instance = new XMLTestDataCollection();
        boolean expResult = false;
        boolean result = instance.save(xml, xsd, collection);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}