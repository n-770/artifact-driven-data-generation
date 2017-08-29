/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.ProbabilityDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Custom;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io.XMLStartup;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.FacetSimplified;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.DataGeneratorActions;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.DataGeneratorView;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog.Multiplicity;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog.NullValues;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.graph.Graph;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ControllerBackgroundTaskTest {

    public ControllerBackgroundTaskTest() {
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
     * Test of loadEMFModel method, of class ControllerBackgroundTask.
     */
    @Test
    public void testLoadEMFModel()
    {
        System.out.println("loadEMFModel");
        DataGeneratorActions dga = null;
        String path = "";
        ControllerBackgroundTask instance = new ControllerBackgroundTask();
        instance.loadEMFModel(dga, path);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateObservers method, of class ControllerBackgroundTask.
     */
    @Test
    public void testUpdateObservers()
    {
        System.out.println("updateObservers");
        List<MetaClass> artefacts = null;
        DefaultTreeModel treeModel = null;
        JTree tree = null;
        ArtefactType type = null;
        ControllerBackgroundTask instance = new ControllerBackgroundTask();
        instance.updateObservers(artefacts, treeModel, tree, type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteArtefact method, of class ControllerBackgroundTask.
     */
    @Test
    public void testDeleteArtefact()
    {
        System.out.println("deleteArtefact");
        DefaultTreeModel model = null;
        DefaultMutableTreeNode nodeToDelete = null;
        boolean isAbstractTerm = false;
        ControllerBackgroundTask instance = new ControllerBackgroundTask();
        instance.deleteArtefact(model, nodeToDelete, isAbstractTerm);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of openNullValuesDialog method, of class ControllerBackgroundTask.
     */
    @Test
    public void testOpenNullValuesDialog()
    {
        System.out.println("openNullValuesDialog");
        NullValues nullValuesDialog = null;
        ControllerBackgroundTask instance = new ControllerBackgroundTask();
        instance.openNullValuesDialog(nullValuesDialog);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveNullValues method, of class ControllerBackgroundTask.
     */
    @Test
    public void testSaveNullValues()
    {
        System.out.println("saveNullValues");
        NullValues dialog = null;
        List<FacetSimplified> tableData = null;
        ControllerBackgroundTask instance = new ControllerBackgroundTask();
        instance.saveNullValues(dialog, tableData);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of openMultiplicityDialog method, of class ControllerBackgroundTask.
     */
    @Test
    public void testOpenMultiplicityDialog()
    {
        System.out.println("openMultiplicityDialog");
        Multiplicity dialog = null;
        ControllerBackgroundTask instance = new ControllerBackgroundTask();
        instance.openMultiplicityDialog(dialog);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveMultiplicityValues method, of class ControllerBackgroundTask.
     */
    @Test
    public void testSaveMultiplicityValues()
    {
        System.out.println("saveMultiplicityValues");
        Multiplicity dialog = null;
        List<FacetSimplified> tableData = null;
        ControllerBackgroundTask instance = new ControllerBackgroundTask();
        instance.saveMultiplicityValues(dialog, tableData);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateXMLFile method, of class ControllerBackgroundTask.
     */
    @Test
    public void testUpdateXMLFile()
    {
        System.out.println("updateXMLFile");
        URI xml = null;
        URI xsd = null;
        XMLStartup xmlHandler = null;
        boolean adding = false;
        String tagElement = "";
        Object content = null;
        ControllerBackgroundTask instance = new ControllerBackgroundTask();
        instance.updateXMLFile(xml, xsd, xmlHandler, adding, tagElement, content);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadFacets method, of class ControllerBackgroundTask.
     */
    @Test
    public void testLoadFacets()
    {
        System.out.println("loadFacets");
        TreePath path = null;
        DefaultMutableTreeNode selectedNode = null;
        DataGeneratorView view = null;
        ControllerBackgroundTask instance = new ControllerBackgroundTask();
        instance.loadFacets(path, selectedNode, view);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateQuantity method, of class ControllerBackgroundTask.
     */
    @Test
    public void testUpdateQuantity()
    {
        System.out.println("updateQuantity");
        int quantity = 0;
        TreePath path = null;
        DefaultMutableTreeNode selectedNode = null;
        ControllerBackgroundTask instance = new ControllerBackgroundTask();
        instance.updateQuantity(quantity, path, selectedNode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUnit method, of class ControllerBackgroundTask.
     */
    @Test
    public void testUpdateUnit()
    {
        System.out.println("updateUnit");
        List<String> data = null;
        String name = "";
        JTree tree = null;
        DefaultTreeModel model = null;
        ControllerBackgroundTask instance = new ControllerBackgroundTask();
        instance.updateUnit(data, name, tree, model);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUnitCustom method, of class ControllerBackgroundTask.
     */
    @Test
    public void testUpdateUnitCustom()
    {
        System.out.println("updateUnitCustom");
        List<Custom> data = null;
        String name = "";
        JTree tree = null;
        DefaultTreeModel model = null;
        ControllerBackgroundTask instance = new ControllerBackgroundTask();
        instance.updateUnitCustom(data, name, tree, model);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveTextFile method, of class ControllerBackgroundTask.
     */
    @Test
    public void testSaveTextFile()
    {
        System.out.println("saveTextFile");
        URI workingDir = null;
        List<String> values = null;
        String fileName = "";
        ControllerBackgroundTask instance = new ControllerBackgroundTask();
        instance.saveValueFile(workingDir, values, fileName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of log method, of class ControllerBackgroundTask.
     */
    @Test
    public void testLog()
    {
        System.out.println("log");
        Level level = null;
        String message = "";
        Throwable e = null;
        Logger logger = null;
        ControllerBackgroundTask instance = new ControllerBackgroundTask();
        instance.log(level, message, e, logger);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateProabilityDensityFunction method, of class ControllerBackgroundTask.
     */
    @Test
    public void testGenerateProabilityDensityFunction()
    {
        System.out.println("generateProabilityDensityFunction");
        ProbabilityDistribution distribution = null;
        Graph graph = null;
        ControllerBackgroundTask instance = new ControllerBackgroundTask();
        instance.generateProabilityDensityFunction(distribution, graph);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateTestData method, of class ControllerBackgroundTask.
     */
    @Test
    public void testGenerateTestData()
    {
        System.out.println("generateTestData");
        URI path = null;
        String fileName = "";
        String ext = "";
        ControllerBackgroundTask instance = new ControllerBackgroundTask();
        instance.generateTestData(path, fileName, ext);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}