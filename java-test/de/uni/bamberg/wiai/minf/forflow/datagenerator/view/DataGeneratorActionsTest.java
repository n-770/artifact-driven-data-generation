/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.view;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Custom;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog.ChooseArtefacts;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog.Multiplicity;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog.NullValues;
import java.net.URI;
import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
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
public class DataGeneratorActionsTest {

    public DataGeneratorActionsTest() {
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
     * Test of openAboutDialog method, of class DataGeneratorActions.
     */
    @Test
    public void testOpenAboutDialog()
    {
        System.out.println("openAboutDialog");
        DataGeneratorActions instance = null;
        instance.openAboutDialog();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadModel method, of class DataGeneratorActions.
     */
    @Test
    public void testLoadModel()
    {
        System.out.println("loadModel");
        URI workingDir = null;
        DataGeneratorActions instance = null;
        instance.loadModel(workingDir);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of openNullValuesDialog method, of class DataGeneratorActions.
     */
    @Test
    public void testOpenNullValuesDialog()
    {
        System.out.println("openNullValuesDialog");
        DataGeneratorActions instance = null;
        instance.openNullValuesDialog();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of openMultiplicityValuesDialog method, of class DataGeneratorActions.
     */
    @Test
    public void testOpenMultiplicityValuesDialog()
    {
        System.out.println("openMultiplicityValuesDialog");
        DataGeneratorActions instance = null;
        instance.openMultiplicityValuesDialog();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of openChooseArtefactsDialog method, of class DataGeneratorActions.
     */
    @Test
    public void testOpenChooseArtefactsDialog()
    {
        System.out.println("openChooseArtefactsDialog");
        DataGeneratorActions instance = null;
        instance.openChooseArtefactsDialog();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadFacets method, of class DataGeneratorActions.
     */
    @Test
    public void testLoadFacets()
    {
        System.out.println("loadFacets");
        TreePath path = null;
        DefaultMutableTreeNode selectedNode = null;
        DataGeneratorActions instance = null;
        instance.loadFacets(path, selectedNode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addFacet method, of class DataGeneratorActions.
     */
    @Test
    public void testAddFacet()
    {
        System.out.println("addFacet");
        DataGeneratorActions instance = null;
        instance.addFacet();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteFacet method, of class DataGeneratorActions.
     */
    @Test
    public void testDeleteFacet()
    {
        System.out.println("deleteFacet");
        DataGeneratorActions instance = null;
        instance.deleteFacet();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createNewArtefact method, of class DataGeneratorActions.
     */
    @Test
    public void testCreateNewArtefact()
    {
        System.out.println("createNewArtefact");
        DataGeneratorActions instance = null;
        instance.createNewArtefact();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteArtefact method, of class DataGeneratorActions.
     */
    @Test
    public void testDeleteArtefact()
    {
        System.out.println("deleteArtefact");
        DefaultTreeModel model = null;
        DefaultMutableTreeNode nodeToDelete = null;
        boolean isAbstractTerm = false;
        DataGeneratorActions instance = null;
        instance.deleteArtefact(model, nodeToDelete, isAbstractTerm);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateData method, of class DataGeneratorActions.
     */
    @Test
    public void testGenerateData()
    {
        System.out.println("generateData");
        URI workingDir = null;
        DataGeneratorActions instance = null;
        instance.generateData(workingDir);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveProject method, of class DataGeneratorActions.
     */
    @Test
    public void testSaveProject()
    {
        System.out.println("saveProject");
        DataGeneratorActions instance = null;
        instance.saveProject();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateArtefact method, of class DataGeneratorActions.
     */
    @Test
    public void testUpdateArtefact()
    {
        System.out.println("updateArtefact");
        List<MetaClass> artefacts = null;
        DefaultTreeModel treeModel = null;
        JTree tree = null;
        ArtefactType type = null;
        DataGeneratorActions instance = null;
        instance.updateArtefact(artefacts, treeModel, tree, type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateQuantity method, of class DataGeneratorActions.
     */
    @Test
    public void testUpdateQuantity()
    {
        System.out.println("updateQuantity");
        int quantity = 0;
        TreePath path = null;
        DefaultMutableTreeNode node = null;
        DataGeneratorActions instance = null;
        instance.updateQuantity(quantity, path, node);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUnit method, of class DataGeneratorActions.
     */
    @Test
    public void testUpdateUnit()
    {
        System.out.println("updateUnit");
        List<String> units = null;
        String name = "";
        JTree tree = null;
        DefaultTreeModel model = null;
        DataGeneratorActions instance = null;
        instance.updateUnit(units, name, tree, model);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUnitCustom method, of class DataGeneratorActions.
     */
    @Test
    public void testUpdateUnitCustom()
    {
        System.out.println("updateUnitCustom");
        List<Custom> units = null;
        String name = "";
        JTree tree = null;
        DefaultTreeModel model = null;
        DataGeneratorActions instance = null;
        instance.updateUnitCustom(units, name, tree, model);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fillType_ExternalFile_SelectFile method, of class DataGeneratorActions.
     */
    @Test
    public void testFillType_ExternalFile_SelectFile()
    {
        System.out.println("fillType_ExternalFile_SelectFile");
        URI workingDir = null;
        DataGeneratorActions instance = null;
        URI expResult = null;
        URI result = instance.fillType_ExternalFile_SelectFile(workingDir);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fillType_Editor_Save method, of class DataGeneratorActions.
     */
    @Test
    public void testFillType_Editor_Save()
    {
        System.out.println("fillType_Editor_Save");
        URI workingDir = null;
        List<String> values = null;
        DataGeneratorActions instance = null;
        instance.fillType_Editor_Save(workingDir, values);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChooseArtefactsDialog method, of class DataGeneratorActions.
     */
    @Test
    public void testGetChooseArtefactsDialog()
    {
        System.out.println("getChooseArtefactsDialog");
        DataGeneratorActions instance = null;
        ChooseArtefacts expResult = null;
        ChooseArtefacts result = instance.getChooseArtefactsDialog();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNullValuesDialog method, of class DataGeneratorActions.
     */
    @Test
    public void testGetNullValuesDialog()
    {
        System.out.println("getNullValuesDialog");
        DataGeneratorActions instance = null;
        NullValues expResult = null;
        NullValues result = instance.getNullValuesDialog();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMultiplicityDialog method, of class DataGeneratorActions.
     */
    @Test
    public void testGetMultiplicityDialog()
    {
        System.out.println("getMultiplicityDialog");
        DataGeneratorActions instance = null;
        Multiplicity expResult = null;
        Multiplicity result = instance.getMultiplicityDialog();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getView method, of class DataGeneratorActions.
     */
    @Test
    public void testGetView()
    {
        System.out.println("getView");
        DataGeneratorActions instance = null;
        DataGeneratorView expResult = null;
        DataGeneratorView result = instance.getView();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}