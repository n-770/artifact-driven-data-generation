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
public class LanguageTypeTest {

    public LanguageTypeTest() {
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
     * Test of values method, of class LanguageType.
     */
    @Test
    public void testValues()
    {
        System.out.println("values");
        LanguageType[] expResult = null;
        LanguageType[] result = LanguageType.values();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class LanguageType.
     */
    @Test
    public void testValueOf()
    {
        System.out.println("valueOf");
        String name = "";
        LanguageType expResult = null;
        LanguageType result = LanguageType.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLanguageName method, of class LanguageType.
     */
    @Test
    public void testGetLanguageName()
    {
        System.out.println("getLanguageName");
        LanguageType instance = null;
        String expResult = "";
        String result = instance.getLanguageName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}