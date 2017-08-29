/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.view;

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
public class NormalDistributionPanelTest {

    public NormalDistributionPanelTest() {
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
     * Test of changeSpinnerModel_Expectation method, of class NormalDistributionPanel.
     */
    @Test
    public void testChangeSpinnerModel_Expectation()
    {
        System.out.println("changeSpinnerModel_Expectation");
        int initValue = 0;
        int N = 0;
        int step = 0;
        NormalDistributionPanel instance = null;
        instance.changeSpinnerModel_Expectation(initValue, N, step);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeSpinnerModel_StandardDerivation method, of class NormalDistributionPanel.
     */
    @Test
    public void testChangeSpinnerModel_StandardDerivation()
    {
        System.out.println("changeSpinnerModel_StandardDerivation");
        int initValue = 0;
        int N = 0;
        int step = 0;
        NormalDistributionPanel instance = null;
        instance.changeSpinnerModel_StandardDerivation(initValue, N, step);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetInputFields method, of class NormalDistributionPanel.
     */
    @Test
    public void testResetInputFields()
    {
        System.out.println("resetInputFields");
        NormalDistributionPanel instance = null;
        instance.resetInputFields();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValues method, of class NormalDistributionPanel.
     */
    @Test
    public void testSetValues()
    {
        System.out.println("setValues");
        double[] values = null;
        NormalDistributionPanel instance = null;
        instance.setValues(values);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getExpectation method, of class NormalDistributionPanel.
     */
    @Test
    public void testGetExpectation()
    {
        System.out.println("getExpectation");
        NormalDistributionPanel instance = null;
        int expResult = 0;
        int result = instance.getExpectation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStandardDeviation method, of class NormalDistributionPanel.
     */
    @Test
    public void testGetStandardDeviation()
    {
        System.out.println("getStandardDeviation");
        NormalDistributionPanel instance = null;
        int expResult = 0;
        int result = instance.getStandardDeviation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}