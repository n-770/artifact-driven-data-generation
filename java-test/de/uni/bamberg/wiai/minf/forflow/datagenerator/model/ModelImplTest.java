/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.model;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.ProbabilityDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Generator;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Unit;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.Artefact;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;
import java.util.Iterator;
import java.util.List;
import org.eclipse.emf.ecore.EPackage;
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
public class ModelImplTest {

    public ModelImplTest() {
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
     * Test of getResource method, of class ModelImpl.
     */
    @Test
    public void testGetResource()
    {
        System.out.println("getResource");
        ModelImpl instance = new ModelImpl();
        EPackage expResult = null;
        EPackage result = instance.getResource();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setResource method, of class ModelImpl.
     */
    @Test
    public void testSetResource()
    {
        System.out.println("setResource");
        EPackage ePackage = null;
        ModelImpl instance = new ModelImpl();
        instance.setResource(ePackage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArtefacts method, of class ModelImpl.
     */
    @Test
    public void testGetArtefacts()
    {
        System.out.println("getArtefacts");
        ModelImpl instance = new ModelImpl();
        List<Artefact> expResult = null;
        List<Artefact> result = instance.getArtefacts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArtefact method, of class ModelImpl.
     */
    @Test
    public void testGetArtefact()
    {
        System.out.println("getArtefact");
        ArtefactType artefactType = null;
        ModelImpl instance = new ModelImpl();
        Artefact expResult = null;
        Artefact result = instance.getArtefact(artefactType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iteratorArtefact method, of class ModelImpl.
     */
    @Test
    public void testIteratorArtefact()
    {
        System.out.println("iteratorArtefact");
        ModelImpl instance = new ModelImpl();
        Iterator<Artefact> expResult = null;
        Iterator<Artefact> result = instance.iteratorArtefact();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addArtefact method, of class ModelImpl.
     */
    @Test
    public void testAddArtefact()
    {
        System.out.println("addArtefact");
        Artefact artefact = null;
        ModelImpl instance = new ModelImpl();
        instance.addArtefact(artefact);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addArtefactType method, of class ModelImpl.
     */
    @Test
    public void testAddArtefactType()
    {
        System.out.println("addArtefactType");
        ArtefactType artefactType = null;
        ModelImpl instance = new ModelImpl();
        instance.addArtefactType(artefactType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArtefactType method, of class ModelImpl.
     */
    @Test
    public void testGetArtefactType()
    {
        System.out.println("getArtefactType");
        String name = "";
        ModelImpl instance = new ModelImpl();
        ArtefactType expResult = null;
        ArtefactType result = instance.getArtefactType(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iteratorArtefactType method, of class ModelImpl.
     */
    @Test
    public void testIteratorArtefactType()
    {
        System.out.println("iteratorArtefactType");
        ModelImpl instance = new ModelImpl();
        Iterator<ArtefactType> expResult = null;
        Iterator<ArtefactType> result = instance.iteratorArtefactType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArtefactTypes method, of class ModelImpl.
     */
    @Test
    public void testGetArtefactTypes()
    {
        System.out.println("getArtefactTypes");
        ModelImpl instance = new ModelImpl();
        List<ArtefactType> expResult = null;
        List<ArtefactType> result = instance.getArtefactTypes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNoOfClassesInTotal method, of class ModelImpl.
     */
    @Test
    public void testGetNoOfClassesInTotal()
    {
        System.out.println("getNoOfClassesInTotal");
        ModelImpl instance = new ModelImpl();
        int expResult = 0;
        int result = instance.getNoOfClassesInTotal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNoOfAttributesInTotal method, of class ModelImpl.
     */
    @Test
    public void testGetNoOfAttributesInTotal()
    {
        System.out.println("getNoOfAttributesInTotal");
        ModelImpl instance = new ModelImpl();
        int expResult = 0;
        int result = instance.getNoOfAttributesInTotal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUnits method, of class ModelImpl.
     */
    @Test
    public void testSetUnits()
    {
        System.out.println("setUnits");
        List<Unit> units = null;
        ModelImpl instance = new ModelImpl();
        instance.setUnits(units);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addUnit method, of class ModelImpl.
     */
    @Test
    public void testAddUnit()
    {
        System.out.println("addUnit");
        Unit unit = null;
        ModelImpl instance = new ModelImpl();
        instance.addUnit(unit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUnits method, of class ModelImpl.
     */
    @Test
    public void testGetUnits()
    {
        System.out.println("getUnits");
        ModelImpl instance = new ModelImpl();
        List<Unit> expResult = null;
        List<Unit> result = instance.getUnits();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iteratorUnits method, of class ModelImpl.
     */
    @Test
    public void testIteratorUnits()
    {
        System.out.println("iteratorUnits");
        ModelImpl instance = new ModelImpl();
        Iterator<Unit> expResult = null;
        Iterator<Unit> result = instance.iteratorUnits();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addGenerator method, of class ModelImpl.
     */
    @Test
    public void testAddGenerator()
    {
        System.out.println("addGenerator");
        Generator generator = null;
        ModelImpl instance = new ModelImpl();
        instance.addGenerator(generator);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeGenerator method, of class ModelImpl.
     */
    @Test
    public void testRemoveGenerator()
    {
        System.out.println("removeGenerator");
        Generator generator = null;
        ModelImpl instance = new ModelImpl();
        instance.removeGenerator(generator);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addProbabilityDistribution method, of class ModelImpl.
     */
    @Test
    public void testAddProbabilityDistribution()
    {
        System.out.println("addProbabilityDistribution");
        ProbabilityDistribution distribution = null;
        ModelImpl instance = new ModelImpl();
        instance.addProbabilityDistribution(distribution);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeProbabilityDistribution method, of class ModelImpl.
     */
    @Test
    public void testRemoveProbabilityDistribution()
    {
        System.out.println("removeProbabilityDistribution");
        ProbabilityDistribution distribution = null;
        ModelImpl instance = new ModelImpl();
        instance.removeProbabilityDistribution(distribution);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of print method, of class ModelImpl.
     */
    @Test
    public void testPrint()
    {
        System.out.println("print");
        ModelImpl instance = new ModelImpl();
        instance.print();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}