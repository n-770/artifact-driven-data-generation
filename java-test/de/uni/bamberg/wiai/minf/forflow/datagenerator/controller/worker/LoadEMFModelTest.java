/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;
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
public class LoadEMFModelTest {

    public LoadEMFModelTest() {
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
     * Test of doInBackground method, of class LoadEMFModel.
     */
    @Test
    public void testDoInBackground() throws Exception
    {
        System.out.println("doInBackground");
        LoadEMFModel instance = null;
        List<MetaClass> expResult = null;
        List<MetaClass> result = instance.doInBackground();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of done method, of class LoadEMFModel.
     */
    @Test
    public void testDone()
    {
        System.out.println("done");
        LoadEMFModel instance = null;
        instance.done();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of finalize method, of class LoadEMFModel.
     */
    @Test
    public void testFinalize() throws Exception
    {
        System.out.println("finalize");
        LoadEMFModel instance = null;
        try
		{
			instance.finalize();
		}
		catch (Throwable e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}