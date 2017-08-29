/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.DataGeneratorView;
import java.awt.Frame;
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
public class DialogFactoryTest {

    public DialogFactoryTest() {
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
     * Test of createAboutDialog method, of class DialogFactory.
     */
    @Test
    public void testCreateAboutDialog()
    {
        System.out.println("createAboutDialog");
        Frame mainFrame = null;
        About expResult = null;
        About result = DialogFactory.createAboutDialog(mainFrame);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createNullValueDialog method, of class DialogFactory.
     */
    @Test
    public void testCreateNullValueDialog()
    {
        System.out.println("createNullValueDialog");
        Frame mainFrame = null;
        NullValues expResult = null;
        NullValues result = DialogFactory.createNullValueDialog(mainFrame);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMultiplicityDialog method, of class DialogFactory.
     */
    @Test
    public void testCreateMultiplicityDialog()
    {
        System.out.println("createMultiplicityDialog");
        Frame mainFrame = null;
        Multiplicity expResult = null;
        Multiplicity result = DialogFactory.createMultiplicityDialog(mainFrame);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createChooseArtefactsDialog method, of class DialogFactory.
     */
    @Test
    public void testCreateChooseArtefactsDialog()
    {
        System.out.println("createChooseArtefactsDialog");
        Frame mainFrame = null;
        DataGeneratorView view = null;
        ChooseArtefacts expResult = null;
        ChooseArtefacts result = DialogFactory.createChooseArtefactsDialog(mainFrame, view);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createGenerateDataDialog method, of class DialogFactory.
     */
    @Test
    public void testCreateGenerateDataDialog()
    {
        System.out.println("createGenerateDataDialog");
        Frame mainFrame = null;
        GenerateData expResult = null;
        GenerateData result = DialogFactory.createGenerateDataDialog(mainFrame);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}