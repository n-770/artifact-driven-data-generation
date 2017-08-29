/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker.GenerateTestData;
import java.awt.Color;
import java.awt.Font;
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
public class GenerateDataTest {

    public GenerateDataTest() {
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
     * Test of cancel method, of class GenerateData.
     */
    @Test
    public void testCancel()
    {
        System.out.println("cancel");
        GenerateData instance = null;
        instance.cancel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of done method, of class GenerateData.
     */
    @Test
    public void testDone()
    {
        System.out.println("done");
        GenerateData instance = null;
        instance.done();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProgressInfo method, of class GenerateData.
     */
    @Test
    public void testSetProgressInfo()
    {
        System.out.println("setProgressInfo");
        String message = "";
        GenerateData instance = null;
        instance.setProgressInfo(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addStatusInformation method, of class GenerateData.
     */
    @Test
    public void testAddStatusInformation_String()
    {
        System.out.println("addStatusInformation");
        String message = "";
        GenerateData instance = null;
        instance.addStatusInformation(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addStatusInformation method, of class GenerateData.
     */
    @Test
    public void testAddStatusInformation_String_Color()
    {
        System.out.println("addStatusInformation");
        String message = "";
        Color textColour = null;
        GenerateData instance = null;
        instance.addStatusInformation(message, textColour);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addStatusInformation method, of class GenerateData.
     */
    @Test
    public void testAddStatusInformation_String_Font()
    {
        System.out.println("addStatusInformation");
        String message = "";
        Font font = null;
        GenerateData instance = null;
        instance.addStatusInformation(message, font);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addStatusInformation method, of class GenerateData.
     */
    @Test
    public void testAddStatusInformation_3args()
    {
        System.out.println("addStatusInformation");
        String message = "";
        Color textColour = null;
        Font font = null;
        GenerateData instance = null;
        instance.addStatusInformation(message, textColour, font);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOKButtonEnabled method, of class GenerateData.
     */
    @Test
    public void testSetOKButtonEnabled()
    {
        System.out.println("setOKButtonEnabled");
        GenerateData instance = null;
        instance.setOKButtonEnabled();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValueProgressBar method, of class GenerateData.
     */
    @Test
    public void testSetValueProgressBar()
    {
        System.out.println("setValueProgressBar");
        int n = 0;
        GenerateData instance = null;
        instance.setValueProgressBar(n);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initProgressBar method, of class GenerateData.
     */
    @Test
    public void testInitProgressBar()
    {
        System.out.println("initProgressBar");
        int min = 0;
        int max = 0;
        boolean indeterminate = false;
        GenerateData instance = null;
        instance.initProgressBar(min, max, indeterminate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class GenerateData.
     */
    @Test
    public void testReset()
    {
        System.out.println("reset");
        GenerateData instance = null;
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWorkerReference method, of class GenerateData.
     */
    @Test
    public void testSetWorkerReference()
    {
        System.out.println("setWorkerReference");
        GenerateTestData worker = null;
        GenerateData instance = null;
        instance.setWorkerReference(worker);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}