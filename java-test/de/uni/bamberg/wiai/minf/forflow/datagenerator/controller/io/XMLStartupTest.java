/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io;

import java.net.URI;
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
public class XMLStartupTest {

    public XMLStartupTest() {
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
     * Test of loadStartUp method, of class XMLStartup.
     */
    @Test
    public void testLoadStartUp() throws Exception
    {
        System.out.println("loadStartUp");
        URI xml = null;
        URI xsd = null;
        XMLStartup instance = new XMLStartup();
        instance.loadStartUp(xml, xsd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class XMLStartup.
     */
    @Test
    public void testUpdate()
    {
        System.out.println("update");
        URI xml = null;
        URI xsd = null;
        boolean adding = false;
        String tagElement = "";
        Object content = null;
        XMLStartup instance = new XMLStartup();
        instance.update(xml, xsd, adding, tagElement, content);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}