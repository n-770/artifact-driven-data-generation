/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Dark Angel
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({de.uni.bamberg.wiai.minf.forflow.datagenerator.view.ViewSuite.class,de.uni.bamberg.wiai.minf.forflow.datagenerator.resources.ResourcesSuite.class,de.uni.bamberg.wiai.minf.forflow.datagenerator.DataGeneratorTest.class,de.uni.bamberg.wiai.minf.forflow.datagenerator.model.ModelSuite.class,de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.ControllerSuite.class})
public class DatageneratorSuite {

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

}