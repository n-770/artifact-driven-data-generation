/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message;

import java.util.logging.Level;
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
public class ErrorMessageTest {

    public ErrorMessageTest() {
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
     * Test of getInstance method, of class ErrorMessage.
     */
    @Test
    public void testGetInstance_0args()
    {
        System.out.println("getInstance");
        ErrorMessage expResult = null;
        ErrorMessage result = ErrorMessage.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInstance method, of class ErrorMessage.
     */
    @Test
    public void testGetInstance_boolean()
    {
        System.out.println("getInstance");
        boolean log = false;
        ErrorMessage expResult = null;
        ErrorMessage result = ErrorMessage.getInstance(log);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printMessage method, of class ErrorMessage.
     */
    @Test
    public void testPrintMessage_Throwable_String()
    {
        System.out.println("printMessage");
        Throwable e = null;
        String title = "";
        ErrorMessage instance = null;
        instance.printMessage(e, title);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printMessage method, of class ErrorMessage.
     */
    @Test
    public void testPrintMessage_3args_1()
    {
        System.out.println("printMessage");
        Throwable e = null;
        String title = "";
        Level level = null;
        ErrorMessage instance = null;
        instance.printMessage(e, title, level);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printMessage method, of class ErrorMessage.
     */
    @Test
    public void testPrintMessage_3args_2()
    {
        System.out.println("printMessage");
        Throwable e = null;
        String message = "";
        String title = "";
        ErrorMessage instance = null;
        instance.printMessage(e, message, title);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printMessage method, of class ErrorMessage.
     */
    @Test
    public void testPrintMessage_4args_1()
    {
        System.out.println("printMessage");
        Throwable e = null;
        String message = "";
        String title = "";
        Level level = null;
        ErrorMessage instance = null;
        instance.printMessage(e, message, title, level);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printMessage method, of class ErrorMessage.
     */
    @Test
    public void testPrintMessage_3args_3()
    {
        System.out.println("printMessage");
        Thread t = null;
        Throwable e = null;
        String title = "";
        ErrorMessage instance = null;
        instance.printMessage(t, e, title);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printMessage method, of class ErrorMessage.
     */
    @Test
    public void testPrintMessage_4args_2()
    {
        System.out.println("printMessage");
        Thread t = null;
        Throwable e = null;
        String title = "";
        Level level = null;
        ErrorMessage instance = null;
        instance.printMessage(t, e, title, level);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printMessage method, of class ErrorMessage.
     */
    @Test
    public void testPrintMessage_5args()
    {
        System.out.println("printMessage");
        Thread t = null;
        Throwable e = null;
        String message = "";
        String title = "";
        Level level = null;
        ErrorMessage instance = null;
        instance.printMessage(t, e, message, title, level);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ok method, of class ErrorMessage.
     */
    @Test
    public void testOk()
    {
        System.out.println("ok");
        ErrorMessage instance = null;
        instance.ok();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeDialog method, of class ErrorMessage.
     */
    @Test
    public void testCloseDialog()
    {
        System.out.println("closeDialog");
        ErrorMessage instance = null;
        instance.closeDialog();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isLoggingEnabled method, of class ErrorMessage.
     */
    @Test
    public void testIsLoggingEnabled()
    {
        System.out.println("isLoggingEnabled");
        ErrorMessage instance = null;
        boolean expResult = false;
        boolean result = instance.isLoggingEnabled();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}