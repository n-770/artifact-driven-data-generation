/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution;

import java.util.Map;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.uncommons.maths.number.NumberGenerator;

/**
 *
 * @author Dark Angel
 */
public class NormalDistributionTest {

    public NormalDistributionTest() {
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
     * Test of generatorInt method, of class NormalDistribution.
     */
    @Test
    public void testGeneratorInt()
    {
        System.out.println("generatorInt");
        Random random = null;
        NormalDistribution instance = new NormalDistribution();
        NumberGenerator<Integer> expResult = null;
        NumberGenerator<Integer> result = instance.generatorInt(random);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generatorDouble method, of class NormalDistribution.
     */
    @Test
    public void testGeneratorDouble()
    {
        System.out.println("generatorDouble");
        Random random = null;
        NormalDistribution instance = new NormalDistribution();
        NumberGenerator<Double> expResult = null;
        NumberGenerator<Double> result = instance.generatorDouble(random);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class NormalDistribution.
     */
    @Test
    public void testGetDescription()
    {
        System.out.println("getDescription");
        NormalDistribution instance = new NormalDistribution();
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setExpectation method, of class NormalDistribution.
     */
    @Test
    public void testSetExpectation()
    {
        System.out.println("setExpectation");
        int expectation = 0;
        NormalDistribution instance = new NormalDistribution();
        instance.setExpectation(expectation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getExpectation method, of class NormalDistribution.
     */
    @Test
    public void testGetExpectation()
    {
        System.out.println("getExpectation");
        NormalDistribution instance = new NormalDistribution();
        int expResult = 0;
        int result = instance.getExpectation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStandardDeviation method, of class NormalDistribution.
     */
    @Test
    public void testSetStandardDeviation()
    {
        System.out.println("setStandardDeviation");
        int standardDeviation = 0;
        NormalDistribution instance = new NormalDistribution();
        instance.setStandardDeviation(standardDeviation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStandardDeviation method, of class NormalDistribution.
     */
    @Test
    public void testGetStandardDeviation()
    {
        System.out.println("getStandardDeviation");
        NormalDistribution instance = new NormalDistribution();
        int expResult = 0;
        int result = instance.getStandardDeviation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVariance method, of class NormalDistribution.
     */
    @Test
    public void testGetVariance()
    {
        System.out.println("getVariance");
        NormalDistribution instance = new NormalDistribution();
        double expResult = 0.0;
        double result = instance.getVariance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDiscrete method, of class NormalDistribution.
     */
    @Test
    public void testIsDiscrete()
    {
        System.out.println("isDiscrete");
        NormalDistribution instance = new NormalDistribution();
        boolean expResult = false;
        boolean result = instance.isDiscrete();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateExpectedValues method, of class NormalDistribution.
     */
    @Test
    public void testGenerateExpectedValues()
    {
        System.out.println("generateExpectedValues");
        NormalDistribution instance = new NormalDistribution();
        Map<Double, Double> expResult = null;
        Map<Double, Double> result = instance.generateExpectedValues();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerObserverDistribution method, of class NormalDistribution.
     */
    @Test
    public void testRegisterObserverDistribution()
    {
        System.out.println("registerObserverDistribution");
        ObserverDistribution observer = null;
        NormalDistribution instance = new NormalDistribution();
        instance.registerObserverDistribution(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeObserverDistribution method, of class NormalDistribution.
     */
    @Test
    public void testRemoveObserverDistribution()
    {
        System.out.println("removeObserverDistribution");
        ObserverDistribution observer = null;
        NormalDistribution instance = new NormalDistribution();
        instance.removeObserverDistribution(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyObserverDistribution method, of class NormalDistribution.
     */
    @Test
    public void testNotifyObserverDistribution()
    {
        System.out.println("notifyObserverDistribution");
        boolean add = false;
        NormalDistribution instance = new NormalDistribution();
        instance.notifyObserverDistribution(add);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class NormalDistribution.
     */
    @Test
    public void testClone() throws Exception
    {
        System.out.println("clone");
        NormalDistribution instance = new NormalDistribution();
        Object expResult = null;
        Object result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}