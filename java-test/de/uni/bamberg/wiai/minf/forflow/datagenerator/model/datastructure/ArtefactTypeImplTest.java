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
public class ArtefactTypeImplTest {

    public ArtefactTypeImplTest() {
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
     * Test of setName method, of class ArtefactTypeImpl.
     */
    @Test
    public void testSetName()
    {
        System.out.println("setName");
        String artefactType = "";
        ArtefactTypeImpl instance = new ArtefactTypeImpl();
        instance.setName(artefactType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of name method, of class ArtefactTypeImpl.
     */
    @Test
    public void testName()
    {
        System.out.println("name");
        ArtefactTypeImpl instance = new ArtefactTypeImpl();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ArtefactTypeImpl.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        ArtefactTypeImpl instance = new ArtefactTypeImpl();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}