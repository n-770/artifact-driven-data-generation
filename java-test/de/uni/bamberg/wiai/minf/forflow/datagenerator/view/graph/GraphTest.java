/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.view.graph;

import java.util.Map;
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
public class GraphTest {

    public GraphTest() {
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
     * Test of generateGraph method, of class Graph.
     */
    @Test
    public void testGenerateGraph()
    {
        System.out.println("generateGraph");
        String title = "";
        Map<Double, Double> expectedValues = null;
        double expectation = 0.0;
        double standardDeviation = 0.0;
        boolean isDiscrete = false;
        Graph instance = new Graph();
        instance.generateGraph(title, expectedValues, expectation, standardDeviation, isDiscrete);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}