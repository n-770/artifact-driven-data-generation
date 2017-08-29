/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.dependency;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaAttribute;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;
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
public class DependencyImplTest {

    public DependencyImplTest() {
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
     * Test of setMClass method, of class DependencyImpl.
     */
    @Test
    public void testSetMClass()
    {
        System.out.println("setMClass");
        MetaClass mClass = null;
        DependencyImpl instance = new DependencyImpl();
        instance.setMClass(mClass);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMClass method, of class DependencyImpl.
     */
    @Test
    public void testGetMClass()
    {
        System.out.println("getMClass");
        DependencyImpl instance = new DependencyImpl();
        MetaClass expResult = null;
        MetaClass result = instance.getMClass();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMAttribute method, of class DependencyImpl.
     */
    @Test
    public void testSetMAttribute()
    {
        System.out.println("setMAttribute");
        MetaAttribute mAttribute = null;
        DependencyImpl instance = new DependencyImpl();
        instance.setMAttribute(mAttribute);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMAttribute method, of class DependencyImpl.
     */
    @Test
    public void testGetMAttribute()
    {
        System.out.println("getMAttribute");
        DependencyImpl instance = new DependencyImpl();
        MetaAttribute expResult = null;
        MetaAttribute result = instance.getMAttribute();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}