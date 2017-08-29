/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure;

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
public class ArtefactFactoryTest {

    public ArtefactFactoryTest() {
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
     * Test of createArtefact method, of class ArtefactFactory.
     */
    @Test
    public void testCreateArtefact()
    {
        System.out.println("createArtefact");
        Artefact expResult = null;
        Artefact result = ArtefactFactory.createArtefact();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMetaAttribute method, of class ArtefactFactory.
     */
    @Test
    public void testCreateMetaAttribute()
    {
        System.out.println("createMetaAttribute");
        MetaAttribute expResult = null;
        MetaAttribute result = ArtefactFactory.createMetaAttribute();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMetaClass method, of class ArtefactFactory.
     */
    @Test
    public void testCreateMetaClass_0args()
    {
        System.out.println("createMetaClass");
        MetaClass expResult = null;
        MetaClass result = ArtefactFactory.createMetaClass();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMetaClass method, of class ArtefactFactory.
     */
    @Test
    public void testCreateMetaClass_String()
    {
        System.out.println("createMetaClass");
        String name = "";
        MetaClass expResult = null;
        MetaClass result = ArtefactFactory.createMetaClass(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createArtefactType method, of class ArtefactFactory.
     */
    @Test
    public void testCreateArtefactType_0args()
    {
        System.out.println("createArtefactType");
        ArtefactType expResult = null;
        ArtefactType result = ArtefactFactory.createArtefactType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createArtefactType method, of class ArtefactFactory.
     */
    @Test
    public void testCreateArtefactType_String()
    {
        System.out.println("createArtefactType");
        String name = "";
        ArtefactType expResult = null;
        ArtefactType result = ArtefactFactory.createArtefactType(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFacetSimplified method, of class ArtefactFactory.
     */
    @Test
    public void testCreateFacetSimplified()
    {
        System.out.println("createFacetSimplified");
        FacetSimplified expResult = null;
        FacetSimplified result = ArtefactFactory.createFacetSimplified();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDataType method, of class ArtefactFactory.
     */
    @Test
    public void testCreateDataType()
    {
        System.out.println("createDataType");
        String dataType = "";
        DataType expResult = null;
        DataType result = ArtefactFactory.createDataType(dataType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}