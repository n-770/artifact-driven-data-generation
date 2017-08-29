/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure;

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
public class MetaClassTest {

    public MetaClassTest() {
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
     * Test of setName method, of class MetaClass.
     */
    @Test
    public void testSetName()
    {
        System.out.println("setName");
        String name = "";
        MetaClass instance = new MetaClass();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class MetaClass.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        MetaClass instance = new MetaClass();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAttributes method, of class MetaClass.
     */
    @Test
    public void testSetAttributes()
    {
        System.out.println("setAttributes");
        List<MetaAttribute> attributes = null;
        MetaClass instance = new MetaClass();
        instance.setAttributes(attributes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAttriubte method, of class MetaClass.
     */
    @Test
    public void testAddAttriubte()
    {
        System.out.println("addAttriubte");
        MetaAttribute attribute = null;
        MetaClass instance = new MetaClass();
        instance.addAttriubte(attribute);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAttribute method, of class MetaClass.
     */
    @Test
    public void testGetAttribute()
    {
        System.out.println("getAttribute");
        String name = "";
        MetaClass instance = new MetaClass();
        MetaAttribute expResult = null;
        MetaAttribute result = instance.getAttribute(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAttribute method, of class MetaClass.
     */
    @Test
    public void testDeleteAttribute_MetaAttribute()
    {
        System.out.println("deleteAttribute");
        MetaAttribute mAttribute = null;
        MetaClass instance = new MetaClass();
        boolean expResult = false;
        boolean result = instance.deleteAttribute(mAttribute);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAttribute method, of class MetaClass.
     */
    @Test
    public void testDeleteAttribute_String()
    {
        System.out.println("deleteAttribute");
        String name = "";
        MetaClass instance = new MetaClass();
        boolean expResult = false;
        boolean result = instance.deleteAttribute(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAttributes method, of class MetaClass.
     */
    @Test
    public void testGetAttributes()
    {
        System.out.println("getAttributes");
        MetaClass instance = new MetaClass();
        List<MetaAttribute> expResult = null;
        List<MetaAttribute> result = instance.getAttributes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArtefactType method, of class MetaClass.
     */
    @Test
    public void testSetArtefactType()
    {
        System.out.println("setArtefactType");
        ArtefactType artefactType = null;
        MetaClass instance = new MetaClass();
        instance.setArtefactType(artefactType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArtefactType method, of class MetaClass.
     */
    @Test
    public void testGetArtefactType()
    {
        System.out.println("getArtefactType");
        MetaClass instance = new MetaClass();
        ArtefactType expResult = null;
        ArtefactType result = instance.getArtefactType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setToConcreteSpec method, of class MetaClass.
     */
    @Test
    public void testSetToConcreteSpec()
    {
        System.out.println("setToConcreteSpec");
        MetaClass instance = new MetaClass();
        instance.setToConcreteSpec();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isConcreteSpec method, of class MetaClass.
     */
    @Test
    public void testIsConcreteSpec()
    {
        System.out.println("isConcreteSpec");
        MetaClass instance = new MetaClass();
        boolean expResult = false;
        boolean result = instance.isConcreteSpec();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasSuperClass method, of class MetaClass.
     */
    @Test
    public void testHasSuperClass()
    {
        System.out.println("hasSuperClass");
        MetaClass instance = new MetaClass();
        boolean expResult = false;
        boolean result = instance.hasSuperClass();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSuperClass method, of class MetaClass.
     */
    @Test
    public void testSetSuperClass()
    {
        System.out.println("setSuperClass");
        MetaClass superClass = null;
        MetaClass instance = new MetaClass();
        instance.setSuperClass(superClass);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSuperClass method, of class MetaClass.
     */
    @Test
    public void testGetSuperClass()
    {
        System.out.println("getSuperClass");
        MetaClass instance = new MetaClass();
        MetaClass expResult = null;
        MetaClass result = instance.getSuperClass();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQuantity method, of class MetaClass.
     */
    @Test
    public void testSetQuantity()
    {
        System.out.println("setQuantity");
        int quantitiy = 0;
        MetaClass instance = new MetaClass();
        instance.setQuantity(quantitiy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantity method, of class MetaClass.
     */
    @Test
    public void testGetQuantity()
    {
        System.out.println("getQuantity");
        MetaClass instance = new MetaClass();
        int expResult = 0;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of noOfAttributes method, of class MetaClass.
     */
    @Test
    public void testNoOfAttributes()
    {
        System.out.println("noOfAttributes");
        MetaClass instance = new MetaClass();
        int expResult = 0;
        int result = instance.noOfAttributes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class MetaClass.
     */
    @Test
    public void testIterator()
    {
        System.out.println("iterator");
        MetaClass instance = new MetaClass();
        Iterator<MetaAttribute> expResult = null;
        Iterator<MetaAttribute> result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class MetaClass.
     */
    @Test
    public void testEquals()
    {
        System.out.println("equals");
        Object ob = null;
        MetaClass instance = new MetaClass();
        boolean expResult = false;
        boolean result = instance.equals(ob);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class MetaClass.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        MetaClass instance = new MetaClass();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}