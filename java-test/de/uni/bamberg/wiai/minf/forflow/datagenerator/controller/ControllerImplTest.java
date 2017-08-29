/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.ObserverDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.ProbabilityDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Generator;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.ObserverGenerator;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.ObserverUnit;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.ObserverUnitCustom;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Unit;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.Artefact;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ObserverArtefact;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.DataGeneratorView;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.eclipse.emf.common.util.URI;
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
public class ControllerImplTest {

    public ControllerImplTest() {
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
     * Test of getLogging method, of class ControllerImpl.
     */
    @Test
    public void testGetLogging()
    {
        System.out.println("getLogging");
        ControllerImpl instance = new ControllerImpl();
        Boolean expResult = null;
        Boolean result = instance.getLogging();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateLowerBound method, of class ControllerImpl.
     */
    @Test
    public void testGetDateLowerBound()
    {
        System.out.println("getDateLowerBound");
        ControllerImpl instance = new ControllerImpl();
        Date expResult = null;
        Date result = instance.getDateLowerBound();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateUpperBound method, of class ControllerImpl.
     */
    @Test
    public void testGetDateUpperBound()
    {
        System.out.println("getDateUpperBound");
        ControllerImpl instance = new ControllerImpl();
        Date expResult = null;
        Date result = instance.getDateUpperBound();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadEcoreModel method, of class ControllerImpl.
     */
    @Test
    public void testLoadEcoreModel_oeecuURI()
    {
        System.out.println("loadEcoreModel");
        URI uri = null;
        ControllerImpl instance = new ControllerImpl();
        EPackage expResult = null;
        EPackage result = instance.loadEcoreModel(uri);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadEcoreModel method, of class ControllerImpl.
     */
    @Test
    public void testLoadEcoreModel_String()
    {
        System.out.println("loadEcoreModel");
        String uri = "";
        ControllerImpl instance = new ControllerImpl();
        EPackage expResult = null;
        EPackage result = instance.loadEcoreModel(uri);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEcoreModel method, of class ControllerImpl.
     */
    @Test
    public void testSetEcoreModel()
    {
        System.out.println("setEcoreModel");
        EPackage ePackage = null;
        ControllerImpl instance = new ControllerImpl();
        instance.setEcoreModel(ePackage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEcoreModel method, of class ControllerImpl.
     */
    @Test
    public void testGetEcoreModel()
    {
        System.out.println("getEcoreModel");
        ControllerImpl instance = new ControllerImpl();
        EPackage expResult = null;
        EPackage result = instance.getEcoreModel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printEcoreModel method, of class ControllerImpl.
     */
    @Test
    public void testPrintEcoreModel()
    {
        System.out.println("printEcoreModel");
        EPackage ePackage = null;
        ControllerImpl instance = new ControllerImpl();
        instance.printEcoreModel(ePackage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadStartupFile method, of class ControllerImpl.
     */
    @Test
    public void testLoadStartupFile()
    {
        System.out.println("loadStartupFile");
        java.net.URI xml = null;
        java.net.URI xsd = null;
        ControllerImpl instance = new ControllerImpl();
        instance.loadStartupFile(xml, xsd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArtefacts method, of class ControllerImpl.
     */
    @Test
    public void testGetArtefacts_0args()
    {
        System.out.println("getArtefacts");
        ControllerImpl instance = new ControllerImpl();
        List<Artefact> expResult = null;
        List<Artefact> result = instance.getArtefacts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArtefact method, of class ControllerImpl.
     */
    @Test
    public void testGetArtefact()
    {
        System.out.println("getArtefact");
        ArtefactType type = null;
        ControllerImpl instance = new ControllerImpl();
        Artefact expResult = null;
        Artefact result = instance.getArtefact(type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMetaClass method, of class ControllerImpl.
     */
    @Test
    public void testGetMetaClass()
    {
        System.out.println("getMetaClass");
        String name = "";
        ArtefactType type = null;
        ControllerImpl instance = new ControllerImpl();
        MetaClass expResult = null;
        MetaClass result = instance.getMetaClass(name, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iteratorArtefacts method, of class ControllerImpl.
     */
    @Test
    public void testIteratorArtefacts()
    {
        System.out.println("iteratorArtefacts");
        ControllerImpl instance = new ControllerImpl();
        Iterator<Artefact> expResult = null;
        Iterator<Artefact> result = instance.iteratorArtefacts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addArtefact method, of class ControllerImpl.
     */
    @Test
    public void testAddArtefact()
    {
        System.out.println("addArtefact");
        Artefact artefact = null;
        ControllerImpl instance = new ControllerImpl();
        instance.addArtefact(artefact);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addToArtefact method, of class ControllerImpl.
     */
    @Test
    public void testAddToArtefact()
    {
        System.out.println("addToArtefact");
        MetaClass mClass = null;
        ControllerImpl instance = new ControllerImpl();
        boolean expResult = false;
        boolean result = instance.addToArtefact(mClass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArtefacts method, of class ControllerImpl.
     */
    @Test
    public void testGetArtefacts_EPackage()
    {
        System.out.println("getArtefacts");
        EPackage ePackage = null;
        ControllerImpl instance = new ControllerImpl();
        List<MetaClass> expResult = null;
        List<MetaClass> result = instance.getArtefacts(ePackage);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createArtefacts method, of class ControllerImpl.
     */
    @Test
    public void testCreateArtefacts()
    {
        System.out.println("createArtefacts");
        List<MetaClass> selection = null;
        DataGeneratorView view = null;
        ControllerImpl instance = new ControllerImpl();
        instance.createArtefacts(selection, view);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerObserverArtefact method, of class ControllerImpl.
     */
    @Test
    public void testRegisterObserverArtefact()
    {
        System.out.println("registerObserverArtefact");
        ObserverArtefact observer = null;
        ArtefactType type = null;
        ControllerImpl instance = new ControllerImpl();
        instance.registerObserverArtefact(observer, type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeObserverArtefact method, of class ControllerImpl.
     */
    @Test
    public void testRemoveObserverArtefact()
    {
        System.out.println("removeObserverArtefact");
        ObserverArtefact observer = null;
        ArtefactType type = null;
        ControllerImpl instance = new ControllerImpl();
        instance.removeObserverArtefact(observer, type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyObserverArtefact method, of class ControllerImpl.
     */
    @Test
    public void testNotifyObserverArtefact()
    {
        System.out.println("notifyObserverArtefact");
        ControllerImpl instance = new ControllerImpl();
        instance.notifyObserverArtefact();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addArtefactType method, of class ControllerImpl.
     */
    @Test
    public void testAddArtefactType_ArtefactType()
    {
        System.out.println("addArtefactType");
        ArtefactType type = null;
        ControllerImpl instance = new ControllerImpl();
        instance.addArtefactType(type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addArtefactType method, of class ControllerImpl.
     */
    @Test
    public void testAddArtefactType_String()
    {
        System.out.println("addArtefactType");
        String name = "";
        boolean dummy = false;
        ControllerImpl instance = new ControllerImpl();
        instance.addArtefactType(name, dummy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArtefactTypes method, of class ControllerImpl.
     */
    @Test
    public void testGetArtefactTypes()
    {
        System.out.println("getArtefactTypes");
        ControllerImpl instance = new ControllerImpl();
        List<ArtefactType> expResult = null;
        List<ArtefactType> result = instance.getArtefactTypes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArtefactType method, of class ControllerImpl.
     */
    @Test
    public void testGetArtefactType()
    {
        System.out.println("getArtefactType");
        String name = "";
        ControllerImpl instance = new ControllerImpl();
        ArtefactType expResult = null;
        ArtefactType result = instance.getArtefactType(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeArtefactType method, of class ControllerImpl.
     */
    @Test
    public void testRemoveArtefactType_ArtefactType()
    {
        System.out.println("removeArtefactType");
        ArtefactType type = null;
        ControllerImpl instance = new ControllerImpl();
        boolean expResult = false;
        boolean result = instance.removeArtefactType(type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeArtefactType method, of class ControllerImpl.
     */
    @Test
    public void testRemoveArtefactType_String()
    {
        System.out.println("removeArtefactType");
        String name = "";
        ControllerImpl instance = new ControllerImpl();
        boolean expResult = false;
        boolean result = instance.removeArtefactType(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasArtefactType method, of class ControllerImpl.
     */
    @Test
    public void testHasArtefactType()
    {
        System.out.println("hasArtefactType");
        String name = "";
        ControllerImpl instance = new ControllerImpl();
        boolean expResult = false;
        boolean result = instance.hasArtefactType(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iteratorArtefactType method, of class ControllerImpl.
     */
    @Test
    public void testIteratorArtefactType()
    {
        System.out.println("iteratorArtefactType");
        ControllerImpl instance = new ControllerImpl();
        Iterator<ArtefactType> expResult = null;
        Iterator<ArtefactType> result = instance.iteratorArtefactType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNoOfAttributes method, of class ControllerImpl.
     */
    @Test
    public void testGetNoOfAttributes()
    {
        System.out.println("getNoOfAttributes");
        ControllerImpl instance = new ControllerImpl();
        int expResult = 0;
        int result = instance.getNoOfAttributes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNoOfClasses method, of class ControllerImpl.
     */
    @Test
    public void testGetNoOfClasses()
    {
        System.out.println("getNoOfClasses");
        ControllerImpl instance = new ControllerImpl();
        int expResult = 0;
        int result = instance.getNoOfClasses();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rawFormatArtefactTypes method, of class ControllerImpl.
     */
    @Test
    public void testRawFormatArtefactTypes()
    {
        System.out.println("rawFormatArtefactTypes");
        List<ArtefactType> artefactTypes = null;
        ControllerImpl instance = new ControllerImpl();
        instance.parsedArtefactTypes(artefactTypes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLogging method, of class ControllerImpl.
     */
    @Test
    public void testSetLogging()
    {
        System.out.println("setLogging");
        boolean logging = false;
        ControllerImpl instance = new ControllerImpl();
        instance.setLogging(logging);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isLogging method, of class ControllerImpl.
     */
    @Test
    public void testIsLogging()
    {
        System.out.println("isLogging");
        ControllerImpl instance = new ControllerImpl();
        boolean expResult = false;
        boolean result = instance.isLogging();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUnits method, of class ControllerImpl.
     */
    @Test
    public void testSetUnits()
    {
        System.out.println("setUnits");
        List<Unit> units = null;
        ControllerImpl instance = new ControllerImpl();
        instance.setUnits(units);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addUnit method, of class ControllerImpl.
     */
    @Test
    public void testAddUnit()
    {
        System.out.println("addUnit");
        Unit unit = null;
        ControllerImpl instance = new ControllerImpl();
        instance.addUnit(unit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUnits method, of class ControllerImpl.
     */
    @Test
    public void testGetUnits()
    {
        System.out.println("getUnits");
        ControllerImpl instance = new ControllerImpl();
        List<Unit> expResult = null;
        List<Unit> result = instance.getUnits();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iteratorUnit method, of class ControllerImpl.
     */
    @Test
    public void testIteratorUnit()
    {
        System.out.println("iteratorUnit");
        ControllerImpl instance = new ControllerImpl();
        Iterator<Unit> expResult = null;
        Iterator<Unit> result = instance.iteratorUnit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRawDate method, of class ControllerImpl.
     */
    @Test
    public void testSetRawDate()
    {
        System.out.println("setRawDate");
        int lower = 0;
        int upper = 0;
        ControllerImpl instance = new ControllerImpl();
        instance.setRawDate(lower, upper);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyObserverUnit method, of class ControllerImpl.
     */
    @Test
    public void testNotifyObserverUnit()
    {
        System.out.println("notifyObserverUnit");
        ControllerImpl instance = new ControllerImpl();
        instance.notifyObserverUnit();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerObserverUnit method, of class ControllerImpl.
     */
    @Test
    public void testRegisterObserverUnit()
    {
        System.out.println("registerObserverUnit");
        ObserverUnit observer = null;
        String name = "";
        ControllerImpl instance = new ControllerImpl();
        instance.registerObserverUnit(observer, name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeObserverUnit method, of class ControllerImpl.
     */
    @Test
    public void testRemoveObserverUnit()
    {
        System.out.println("removeObserverUnit");
        ObserverUnit observer = null;
        String name = "";
        ControllerImpl instance = new ControllerImpl();
        instance.removeObserverUnit(observer, name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyObserverUnitCustom method, of class ControllerImpl.
     */
    @Test
    public void testNotifyObserverUnitCustom()
    {
        System.out.println("notifyObserverUnitCustom");
        ControllerImpl instance = new ControllerImpl();
        instance.notifyObserverUnitCustom();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerObserverUnitCustom method, of class ControllerImpl.
     */
    @Test
    public void testRegisterObserverUnitCustom()
    {
        System.out.println("registerObserverUnitCustom");
        ObserverUnitCustom observer = null;
        String name = "";
        ControllerImpl instance = new ControllerImpl();
        instance.registerObserverUnitCustom(observer, name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeObserverUnitCustom method, of class ControllerImpl.
     */
    @Test
    public void testRemoveObserverUnitCustom()
    {
        System.out.println("removeObserverUnitCustom");
        ObserverUnitCustom observer = null;
        String name = "";
        ControllerImpl instance = new ControllerImpl();
        instance.removeObserverUnitCustom(observer, name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createAndRegisterGenerator method, of class ControllerImpl.
     */
    @Test
    public void testCreateAndRegisterGenerator()
    {
        System.out.println("createAndRegisterGenerator");
        ObserverGenerator observer = null;
        ControllerImpl instance = new ControllerImpl();
        instance.createAndRegisterGenerator(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unregisterGenerator method, of class ControllerImpl.
     */
    @Test
    public void testUnregisterGenerator()
    {
        System.out.println("unregisterGenerator");
        Generator generator = null;
        ObserverGenerator observer = null;
        ControllerImpl instance = new ControllerImpl();
        instance.unregisterGenerator(generator, observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createAndRegisterDistribution method, of class ControllerImpl.
     */
    @Test
    public void testCreateAndRegisterDistribution()
    {
        System.out.println("createAndRegisterDistribution");
        ObserverDistribution observer = null;
        ControllerImpl instance = new ControllerImpl();
        instance.createAndRegisterDistribution(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unregisterDistribution method, of class ControllerImpl.
     */
    @Test
    public void testUnregisterDistribution()
    {
        System.out.println("unregisterDistribution");
        ProbabilityDistribution distribution = null;
        ObserverDistribution observer = null;
        ControllerImpl instance = new ControllerImpl();
        instance.unregisterDistribution(distribution, observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}