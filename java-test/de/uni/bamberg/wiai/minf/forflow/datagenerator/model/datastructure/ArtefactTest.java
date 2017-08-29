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
public class ArtefactTest {

    public ArtefactTest() {
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
     * Test of setArtefacts method, of class Artefact.
     */
    @Test
    public void testSetArtefacts()
    {
        System.out.println("setArtefacts");
        List<MetaClass> artefacts = null;
        Artefact instance = new Artefact();
        instance.setArtefacts(artefacts);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addArtefact method, of class Artefact.
     */
    @Test
    public void testAddArtefact()
    {
        System.out.println("addArtefact");
        MetaClass artefact = null;
        Artefact instance = new Artefact();
        instance.addArtefact(artefact);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArtefact method, of class Artefact.
     */
    @Test
    public void testGetArtefact_String()
    {
        System.out.println("getArtefact");
        String name = "";
        Artefact instance = new Artefact();
        MetaClass expResult = null;
        MetaClass result = instance.getArtefact(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArtefact method, of class Artefact.
     */
    @Test
    public void testGetArtefact_int()
    {
        System.out.println("getArtefact");
        int index = 0;
        Artefact instance = new Artefact();
        MetaClass expResult = null;
        MetaClass result = instance.getArtefact(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasArtefact method, of class Artefact.
     */
    @Test
    public void testHasArtefact()
    {
        System.out.println("hasArtefact");
        MetaClass mClass = null;
        Artefact instance = new Artefact();
        boolean expResult = false;
        boolean result = instance.hasArtefact(mClass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArtefacts method, of class Artefact.
     */
    @Test
    public void testGetArtefacts()
    {
        System.out.println("getArtefacts");
        Artefact instance = new Artefact();
        List<MetaClass> expResult = null;
        List<MetaClass> result = instance.getArtefacts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteArtefact method, of class Artefact.
     */
    @Test
    public void testDeleteArtefact_MetaClass()
    {
        System.out.println("deleteArtefact");
        MetaClass mClass = null;
        Artefact instance = new Artefact();
        boolean expResult = false;
        boolean result = instance.deleteArtefact(mClass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteArtefact method, of class Artefact.
     */
    @Test
    public void testDeleteArtefact_String()
    {
        System.out.println("deleteArtefact");
        String name = "";
        Artefact instance = new Artefact();
        boolean expResult = false;
        boolean result = instance.deleteArtefact(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class Artefact.
     */
    @Test
    public void testIterator()
    {
        System.out.println("iterator");
        Artefact instance = new Artefact();
        Iterator<MetaClass> expResult = null;
        Iterator<MetaClass> result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArtefactType method, of class Artefact.
     */
    @Test
    public void testSetArtefactType()
    {
        System.out.println("setArtefactType");
        ArtefactType artefactType = null;
        Artefact instance = new Artefact();
        instance.setArtefactType(artefactType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArtefactType method, of class Artefact.
     */
    @Test
    public void testGetArtefactType()
    {
        System.out.println("getArtefactType");
        Artefact instance = new Artefact();
        ArtefactType expResult = null;
        ArtefactType result = instance.getArtefactType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of noOfClasses method, of class Artefact.
     */
    @Test
    public void testNoOfClasses()
    {
        System.out.println("noOfClasses");
        Artefact instance = new Artefact();
        int expResult = 0;
        int result = instance.noOfClasses();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of noOfAttributes method, of class Artefact.
     */
    @Test
    public void testNoOfAttributes()
    {
        System.out.println("noOfAttributes");
        Artefact instance = new Artefact();
        int expResult = 0;
        int result = instance.noOfAttributes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerObserverArtefact method, of class Artefact.
     */
    @Test
    public void testRegisterObserverArtefact()
    {
        System.out.println("registerObserverArtefact");
        ObserverArtefact observer = null;
        ArtefactType type = null;
        Artefact instance = new Artefact();
        instance.registerObserverArtefact(observer, type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeObserverArtefact method, of class Artefact.
     */
    @Test
    public void testRemoveObserverArtefact()
    {
        System.out.println("removeObserverArtefact");
        ObserverArtefact observer = null;
        ArtefactType type = null;
        Artefact instance = new Artefact();
        instance.removeObserverArtefact(observer, type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyObserverArtefact method, of class Artefact.
     */
    @Test
    public void testNotifyObserverArtefact()
    {
        System.out.println("notifyObserverArtefact");
        Artefact instance = new Artefact();
        instance.notifyObserverArtefact();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of print method, of class Artefact.
     */
    @Test
    public void testPrint()
    {
        System.out.println("print");
        Artefact instance = new Artefact();
        instance.print();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}