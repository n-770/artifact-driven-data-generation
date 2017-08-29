/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

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
public class FillBehaviourFactoryTest {

    public FillBehaviourFactoryTest() {
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
     * Test of createAddress method, of class FillBehaviourFactory.
     */
    @Test
    public void testCreateAddress()
    {
        System.out.println("createAddress");
        Address expResult = null;
        Address result = FillBehaviourFactory.createAddress();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCountry method, of class FillBehaviourFactory.
     */
    @Test
    public void testCreateCountry()
    {
        System.out.println("createCountry");
        Country expResult = null;
        Country result = FillBehaviourFactory.createCountry();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDate method, of class FillBehaviourFactory.
     */
    @Test
    public void testCreateDate()
    {
        System.out.println("createDate");
        Dates expResult = null;
        Dates result = FillBehaviourFactory.createDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEditor method, of class FillBehaviourFactory.
     */
    @Test
    public void testCreateEditor()
    {
        System.out.println("createEditor");
        Editor expResult = null;
        Editor result = FillBehaviourFactory.createEditor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEmail method, of class FillBehaviourFactory.
     */
    @Test
    public void testCreateEmail()
    {
        System.out.println("createEmail");
        Email expResult = null;
        Email result = FillBehaviourFactory.createEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createExternalFile method, of class FillBehaviourFactory.
     */
    @Test
    public void testCreateExternalFile()
    {
        System.out.println("createExternalFile");
        ExternalFile expResult = null;
        ExternalFile result = FillBehaviourFactory.createExternalFile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFillType method, of class FillBehaviourFactory.
     */
    @Test
    public void testCreateFillType()
    {
        System.out.println("createFillType");
        FillType expResult = null;
        FillType result = FillBehaviourFactory.createFillType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFirstName method, of class FillBehaviourFactory.
     */
    @Test
    public void testCreateFirstName()
    {
        System.out.println("createFirstName");
        FirstName expResult = null;
        FirstName result = FillBehaviourFactory.createFirstName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createInterval method, of class FillBehaviourFactory.
     */
    @Test
    public void testCreateInterval()
    {
        System.out.println("createInterval");
        Interval expResult = null;
        Interval result = FillBehaviourFactory.createInterval();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createLastName method, of class FillBehaviourFactory.
     */
    @Test
    public void testCreateLastName()
    {
        System.out.println("createLastName");
        LastName expResult = null;
        LastName result = FillBehaviourFactory.createLastName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createName method, of class FillBehaviourFactory.
     */
    @Test
    public void testCreateName()
    {
        System.out.println("createName");
        Name expResult = null;
        Name result = FillBehaviourFactory.createName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createSex method, of class FillBehaviourFactory.
     */
    @Test
    public void testCreateSex()
    {
        System.out.println("createSex");
        Sex expResult = null;
        Sex result = FillBehaviourFactory.createSex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}