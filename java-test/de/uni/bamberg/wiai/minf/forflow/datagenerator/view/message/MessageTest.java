/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message;

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
public class MessageTest {

    public MessageTest() {
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
     * Test of showMessageDialog method, of class Message.
     */
    @Test
    public void testShowMessageDialog()
    {
        System.out.println("showMessageDialog");
        String text = "";
        String headline = "";
        int messageType = 0;
        Message.showMessageDialog(text, headline, messageType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showOptionDialog method, of class Message.
     */
    @Test
    public void testShowOptionDialog()
    {
        System.out.println("showOptionDialog");
        String message = "";
        String title = "";
        int optionType = 0;
        int messageType = 0;
        Object[] options = null;
        Object[] initialValue = null;
        int expResult = 0;
        int result = Message.showOptionDialog(message, title, optionType, messageType, options, initialValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showConfirmDialog method, of class Message.
     */
    @Test
    public void testShowConfirmDialog()
    {
        System.out.println("showConfirmDialog");
        String text = "";
        String headline = "";
        int messageType = 0;
        int expResult = 0;
        int result = Message.showConfirmDialog(text, headline, messageType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}