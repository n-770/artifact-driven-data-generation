/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.dependency.Dependency;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.ProbabilityDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.FillBehaviour;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.FillType;
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
public class MetaAttributeTest {

    public MetaAttributeTest() {
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
     * Test of setName method, of class MetaAttribute.
     */
    @Test
    public void testSetName()
    {
        System.out.println("setName");
        String name = "";
        MetaAttribute instance = new MetaAttribute();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class MetaAttribute.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        MetaAttribute instance = new MetaAttribute();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataType method, of class MetaAttribute.
     */
    @Test
    public void testSetDataType()
    {
        System.out.println("setDataType");
        DataType datatype = null;
        MetaAttribute instance = new MetaAttribute();
        instance.setDataType(datatype);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataType method, of class MetaAttribute.
     */
    @Test
    public void testGetDataType()
    {
        System.out.println("getDataType");
        MetaAttribute instance = new MetaAttribute();
        DataType expResult = null;
        DataType result = instance.getDataType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValues method, of class MetaAttribute.
     */
    @Test
    public void testSetValues()
    {
        System.out.println("setValues");
        List<Object> values = null;
        MetaAttribute instance = new MetaAttribute();
        instance.setValues(values);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addValue method, of class MetaAttribute.
     */
    @Test
    public void testAddValue()
    {
        System.out.println("addValue");
        Object ob = null;
        MetaAttribute instance = new MetaAttribute();
        instance.addValue(ob);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValues method, of class MetaAttribute.
     */
    @Test
    public void testGetValues()
    {
        System.out.println("getValues");
        MetaAttribute instance = new MetaAttribute();
        List<Object> expResult = null;
        List<Object> result = instance.getValues();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPreviewValue method, of class MetaAttribute.
     */
    @Test
    public void testGetPreviewValue()
    {
        System.out.println("getPreviewValue");
        MetaAttribute instance = new MetaAttribute();
        String expResult = "";
        String result = instance.getPreviewValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLowerBound method, of class MetaAttribute.
     */
    @Test
    public void testSetLowerBound()
    {
        System.out.println("setLowerBound");
        int lowerBound = 0;
        MetaAttribute instance = new MetaAttribute();
        instance.setLowerBound(lowerBound);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLowerBound method, of class MetaAttribute.
     */
    @Test
    public void testGetLowerBound()
    {
        System.out.println("getLowerBound");
        MetaAttribute instance = new MetaAttribute();
        int expResult = 0;
        int result = instance.getLowerBound();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUpperBound method, of class MetaAttribute.
     */
    @Test
    public void testSetUpperBound()
    {
        System.out.println("setUpperBound");
        int upperBound = 0;
        MetaAttribute instance = new MetaAttribute();
        instance.setUpperBound(upperBound);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUpperBound method, of class MetaAttribute.
     */
    @Test
    public void testGetUpperBound()
    {
        System.out.println("getUpperBound");
        MetaAttribute instance = new MetaAttribute();
        int expResult = 0;
        int result = instance.getUpperBound();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMultiplicityAllowed method, of class MetaAttribute.
     */
    @Test
    public void testIsMultiplicityAllowed_boolean()
    {
        System.out.println("isMultiplicityAllowed");
        boolean allowed = false;
        MetaAttribute instance = new MetaAttribute();
        instance.isMultiplicityAllowed(allowed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMultiplicityAllowed method, of class MetaAttribute.
     */
    @Test
    public void testIsMultiplicityAllowed_0args()
    {
        System.out.println("isMultiplicityAllowed");
        MetaAttribute instance = new MetaAttribute();
        boolean expResult = false;
        boolean result = instance.isMultiplicityAllowed();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNullValueAllowed method, of class MetaAttribute.
     */
    @Test
    public void testIsNullValueAllowed_boolean()
    {
        System.out.println("isNullValueAllowed");
        boolean allowed = false;
        MetaAttribute instance = new MetaAttribute();
        instance.isNullValueAllowed(allowed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNullValueAllowed method, of class MetaAttribute.
     */
    @Test
    public void testIsNullValueAllowed_0args()
    {
        System.out.println("isNullValueAllowed");
        MetaAttribute instance = new MetaAttribute();
        boolean expResult = false;
        boolean result = instance.isNullValueAllowed();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPercentOfNullValues method, of class MetaAttribute.
     */
    @Test
    public void testSetPercentOfNullValues()
    {
        System.out.println("setPercentOfNullValues");
        int percent = 0;
        MetaAttribute instance = new MetaAttribute();
        instance.setPercentOfNullValues(percent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPercentOfNullVallues method, of class MetaAttribute.
     */
    @Test
    public void testGetPercentOfNullVallues()
    {
        System.out.println("getPercentOfNullVallues");
        MetaAttribute instance = new MetaAttribute();
        int expResult = 0;
        int result = instance.getPercentOfNullVallues();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValueRestricted method, of class MetaAttribute.
     */
    @Test
    public void testIsValueRestricted_boolean()
    {
        System.out.println("isValueRestricted");
        boolean restricted = false;
        MetaAttribute instance = new MetaAttribute();
        instance.isValueRestricted(restricted);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValueRestricted method, of class MetaAttribute.
     */
    @Test
    public void testIsValueRestricted_0args()
    {
        System.out.println("isValueRestricted");
        MetaAttribute instance = new MetaAttribute();
        boolean expResult = false;
        boolean result = instance.isValueRestricted();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of existsDependency method, of class MetaAttribute.
     */
    @Test
    public void testExistsDependency()
    {
        System.out.println("existsDependency");
        boolean dependency = false;
        MetaAttribute instance = new MetaAttribute();
        instance.existsDependency(dependency);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exitsDependency method, of class MetaAttribute.
     */
    @Test
    public void testExitsDependency()
    {
        System.out.println("exitsDependency");
        MetaAttribute instance = new MetaAttribute();
        boolean expResult = false;
        boolean result = instance.exitsDependency();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDependencies method, of class MetaAttribute.
     */
    @Test
    public void testSetDependencies()
    {
        System.out.println("setDependencies");
        List<Dependency> dependencies = null;
        MetaAttribute instance = new MetaAttribute();
        instance.setDependencies(dependencies);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDependency method, of class MetaAttribute.
     */
    @Test
    public void testAddDependency()
    {
        System.out.println("addDependency");
        Dependency dependency = null;
        MetaAttribute instance = new MetaAttribute();
        instance.addDependency(dependency);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDependencies method, of class MetaAttribute.
     */
    @Test
    public void testGetDependencies()
    {
        System.out.println("getDependencies");
        MetaAttribute instance = new MetaAttribute();
        List<Dependency> expResult = null;
        List<Dependency> result = instance.getDependencies();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFillType method, of class MetaAttribute.
     */
    @Test
    public void testSetFillType()
    {
        System.out.println("setFillType");
        FillType fillType = null;
        MetaAttribute instance = new MetaAttribute();
        instance.setFillType(fillType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFillType method, of class MetaAttribute.
     */
    @Test
    public void testGetFillType()
    {
        System.out.println("getFillType");
        MetaAttribute instance = new MetaAttribute();
        FillType expResult = null;
        FillType result = instance.getFillType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFillBehaviour method, of class MetaAttribute.
     */
    @Test
    public void testSetFillBehaviour()
    {
        System.out.println("setFillBehaviour");
        FillBehaviour behaviour = null;
        MetaAttribute instance = new MetaAttribute();
        instance.setFillBehaviour(behaviour);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUnit method, of class MetaAttribute.
     */
    @Test
    public void testSetUnit()
    {
        System.out.println("setUnit");
        String unit = "";
        MetaAttribute instance = new MetaAttribute();
        instance.setUnit(unit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUnit method, of class MetaAttribute.
     */
    @Test
    public void testGetUnit()
    {
        System.out.println("getUnit");
        MetaAttribute instance = new MetaAttribute();
        String expResult = "";
        String result = instance.getUnit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDistribution method, of class MetaAttribute.
     */
    @Test
    public void testSetDistribution()
    {
        System.out.println("setDistribution");
        ProbabilityDistribution pd = null;
        MetaAttribute instance = new MetaAttribute();
        instance.setDistribution(pd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDistribution method, of class MetaAttribute.
     */
    @Test
    public void testGetDistribution()
    {
        System.out.println("getDistribution");
        MetaAttribute instance = new MetaAttribute();
        ProbabilityDistribution expResult = null;
        ProbabilityDistribution result = instance.getDistribution();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}