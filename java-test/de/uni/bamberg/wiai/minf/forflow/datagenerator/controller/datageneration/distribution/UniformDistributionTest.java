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
public class UniformDistributionTest {

    public UniformDistributionTest() {
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
     * Test of generatorInt method, of class UniformDistribution.
     */
    @Test
    public void testGeneratorInt()
    {
        System.out.println("generatorInt");
        Random random = null;
        UniformDistribution instance = new UniformDistribution();
        NumberGenerator<Integer> expResult = null;
        NumberGenerator<Integer> result = instance.generatorInt(random);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generatorDouble method, of class UniformDistribution.
     */
    @Test
    public void testGeneratorDouble()
    {
        System.out.println("generatorDouble");
        Random random = null;
        UniformDistribution instance = new UniformDistribution();
        NumberGenerator<Double> expResult = null;
        NumberGenerator<Double> result = instance.generatorDouble(random);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class UniformDistribution.
     */
    @Test
    public void testGetDescription()
    {
        System.out.println("getDescription");
        UniformDistribution instance = new UniformDistribution();
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setA method, of class UniformDistribution.
     */
    @Test
    public void testSetA()
    {
        System.out.println("setA");
        int a = 0;
        UniformDistribution instance = new UniformDistribution();
        instance.setA(a);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getA method, of class UniformDistribution.
     */
    @Test
    public void testGetA()
    {
        System.out.println("getA");
        UniformDistribution instance = new UniformDistribution();
        int expResult = 0;
        int result = instance.getA();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setB method, of class UniformDistribution.
     */
    @Test
    public void testSetB()
    {
        System.out.println("setB");
        int b = 0;
        UniformDistribution instance = new UniformDistribution();
        instance.setB(b);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getB method, of class UniformDistribution.
     */
    @Test
    public void testGetB()
    {
        System.out.println("getB");
        UniformDistribution instance = new UniformDistribution();
        int expResult = 0;
        int result = instance.getB();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateExpectedValues method, of class UniformDistribution.
     */
    @Test
    public void testGenerateExpectedValues()
    {
        System.out.println("generateExpectedValues");
        UniformDistribution instance = new UniformDistribution();
        Map<Double, Double> expResult = null;
        Map<Double, Double> result = instance.generateExpectedValues();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getExpectation method, of class UniformDistribution.
     */
    @Test
    public void testGetExpectation()
    {
        System.out.println("getExpectation");
        UniformDistribution instance = new UniformDistribution();
        int expResult = 0;
        int result = instance.getExpectation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStandardDeviation method, of class UniformDistribution.
     */
    @Test
    public void testGetStandardDeviation()
    {
        System.out.println("getStandardDeviation");
        UniformDistribution instance = new UniformDistribution();
        int expResult = 0;
        int result = instance.getStandardDeviation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVariance method, of class UniformDistribution.
     */
    @Test
    public void testGetVariance()
    {
        System.out.println("getVariance");
        UniformDistribution instance = new UniformDistribution();
        double expResult = 0.0;
        double result = instance.getVariance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDiscrete method, of class UniformDistribution.
     */
    @Test
    public void testIsDiscrete()
    {
        System.out.println("isDiscrete");
        UniformDistribution instance = new UniformDistribution();
        boolean expResult = false;
        boolean result = instance.isDiscrete();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerObserverDistribution method, of class UniformDistribution.
     */
    @Test
    public void testRegisterObserverDistribution()
    {
        System.out.println("registerObserverDistribution");
        ObserverDistribution observer = null;
        UniformDistribution instance = new UniformDistribution();
        instance.registerObserverDistribution(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeObserverDistribution method, of class UniformDistribution.
     */
    @Test
    public void testRemoveObserverDistribution()
    {
        System.out.println("removeObserverDistribution");
        ObserverDistribution observer = null;
        UniformDistribution instance = new UniformDistribution();
        instance.removeObserverDistribution(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyObserverDistribution method, of class UniformDistribution.
     */
    @Test
    public void testNotifyObserverDistribution()
    {
        System.out.println("notifyObserverDistribution");
        boolean add = false;
        UniformDistribution instance = new UniformDistribution();
        instance.notifyObserverDistribution(add);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class UniformDistribution.
     */
    @Test
    public void testClone() throws Exception
    {
        System.out.println("clone");
        UniformDistribution instance = new UniformDistribution();
        Object expResult = null;
        Object result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}