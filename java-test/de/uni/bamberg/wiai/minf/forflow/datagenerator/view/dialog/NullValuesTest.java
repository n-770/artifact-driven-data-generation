/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.FacetSimplified;
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
public class NullValuesTest {

    public NullValuesTest() {
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
     * Test of accept method, of class NullValues.
     */
    @Test
    public void testAccept()
    {
        System.out.println("accept");
        NullValues instance = null;
        instance.accept();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cancel method, of class NullValues.
     */
    @Test
    public void testCancel()
    {
        System.out.println("cancel");
        NullValues instance = null;
        instance.cancel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setData method, of class NullValues.
     */
    @Test
    public void testSetData()
    {
        System.out.println("setData");
        List<FacetSimplified> tableData = null;
        NullValues instance = null;
        instance.setData(tableData);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}