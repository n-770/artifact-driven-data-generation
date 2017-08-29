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
public class UniformDistributionPanelTest {

    public UniformDistributionPanelTest() {
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
     * Test of changeSpinnerModel_A method, of class UniformDistributionPanel.
     */
    @Test
    public void testChangeSpinnerModel_A()
    {
        System.out.println("changeSpinnerModel_A");
        int currentValue = 0;
        int N = 0;
        int step = 0;
        UniformDistributionPanel instance = null;
        instance.changeSpinnerModel_A(currentValue, N, step);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changesSpinnerModel_B method, of class UniformDistributionPanel.
     */
    @Test
    public void testChangesSpinnerModel_B()
    {
        System.out.println("changesSpinnerModel_B");
        int currentValue = 0;
        int N = 0;
        int step = 0;
        UniformDistributionPanel instance = null;
        instance.changesSpinnerModel_B(currentValue, N, step);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetInputFields method, of class UniformDistributionPanel.
     */
    @Test
    public void testResetInputFields()
    {
        System.out.println("resetInputFields");
        UniformDistributionPanel instance = null;
        instance.resetInputFields();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValues method, of class UniformDistributionPanel.
     */
    @Test
    public void testSetValues()
    {
        System.out.println("setValues");
        double[] values = null;
        UniformDistributionPanel instance = null;
        instance.setValues(values);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getA method, of class UniformDistributionPanel.
     */
    @Test
    public void testGetA()
    {
        System.out.println("getA");
        UniformDistributionPanel instance = null;
        int expResult = 0;
        int result = instance.getA();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getB method, of class UniformDistributionPanel.
     */
    @Test
    public void testGetB()
    {
        System.out.println("getB");
        UniformDistributionPanel instance = null;
        int expResult = 0;
        int result = instance.getB();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}