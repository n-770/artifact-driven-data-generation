/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.view;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.ProbabilityDistribution;
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
public class DistributionPanelTest {

    public DistributionPanelTest() {
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
     * Test of accept method, of class DistributionPanel.
     */
    @Test
    public void testAccept()
    {
        System.out.println("accept");
        DistributionPanel instance = new DistributionPanel();
        instance.accept();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataGeneratorView method, of class DistributionPanel.
     */
    @Test
    public void testSetDataGeneratorView()
    {
        System.out.println("setDataGeneratorView");
        DataGeneratorView view = null;
        DistributionPanel instance = new DistributionPanel();
        instance.setDataGeneratorView(view);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addProbability method, of class DistributionPanel.
     */
    @Test
    public void testAddProbability()
    {
        System.out.println("addProbability");
        ProbabilityDistribution distribution = null;
        DistributionParameterPanel parameterPanel = null;
        DistributionPanel instance = new DistributionPanel();
        instance.addProbability(distribution, parameterPanel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeProbability method, of class DistributionPanel.
     */
    @Test
    public void testRemoveProbability()
    {
        System.out.println("removeProbability");
        ProbabilityDistribution distribution = null;
        DistributionPanel instance = new DistributionPanel();
        instance.removeProbability(distribution);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAndSetValues method, of class DistributionPanel.
     */
    @Test
    public void testUpdateAndSetValues()
    {
        System.out.println("updateAndSetValues");
        ProbabilityDistribution dist = null;
        boolean updateGraph = false;
        DistributionPanel instance = new DistributionPanel();
        instance.updateAndSetValues(dist, updateGraph);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateWithoutSettingValues method, of class DistributionPanel.
     */
    @Test
    public void testUpdateWithoutSettingValues()
    {
        System.out.println("updateWithoutSettingValues");
        ProbabilityDistribution dist = null;
        DistributionPanel instance = new DistributionPanel();
        instance.updateWithoutSettingValues(dist);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of useValuesAlreadySet method, of class DistributionPanel.
     */
    @Test
    public void testUseValuesAlreadySet()
    {
        System.out.println("useValuesAlreadySet");
        ProbabilityDistribution dist = null;
        DistributionPanel instance = new DistributionPanel();
        instance.useValuesAlreadySet(dist);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of useFirstInBox method, of class DistributionPanel.
     */
    @Test
    public void testUseFirstInBox()
    {
        System.out.println("useFirstInBox");
        DistributionPanel instance = new DistributionPanel();
        instance.useFirstInBox();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}