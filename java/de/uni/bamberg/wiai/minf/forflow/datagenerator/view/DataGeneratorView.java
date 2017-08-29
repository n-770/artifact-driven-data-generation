package de.uni.bamberg.wiai.minf.forflow.datagenerator.view;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.DataGenerator;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.ControllerFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.ObserverDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.PRNG;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.ProbabilityDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Address;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Country;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.DateInterval;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Dates;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Editor;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Email;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.ExternalFile;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.FillBehaviour;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.FillBehaviourFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.FirstName;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Generator;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Interval;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.LanguageType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.LastName;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Name;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.ObserverGenerator;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Sex;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.SexType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Custom;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.ObserverUnit;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.ObserverUnitCustom;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Unit;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.Artefact;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.DataType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaAttribute;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ObserverArtefact;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog.Multiplicity;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog.NullValues;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message.ErrorMessage;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message.Message;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.jdesktop.application.Action;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.SingleFrameApplication;

/**
 * <b>DataGeneratorView</b> class is the main view.
 * Here are the components of the UI created and initialized.
 * Most of the actions are not directly handled her, but passed on.
 * </p>
 * To get updated with the latest news and informations about the model
 * layer, it implements some <i>Observer</i> interfaces. With this, the
 * UI is gonna be updated whenever a change in the subject occurs. The
 * subject is in terms of the observer pattern the observable.
 * </p>
 * http://java.sun.com/docs/books/tutorial/uiswing/
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Mar/29/09
 */
public class DataGeneratorView extends FrameView implements ObserverArtefact,
															ObserverUnit,
															ObserverUnitCustom,
															ObserverGenerator,
															ObserverDistribution
{
	/**
	 * reference of the class where the actions caused by 
	 * this class are handled and passed on to the controller.
	 */
	private DataGeneratorActions dataGeneratorActions = null;
	
	/**
	 * By explicitly creating the tree's model, it is guaranteed that the
	 * tree model is an instance of the default model.
	 */
	private DefaultTreeModel treeModel_artefacts = null;
	
	/**
	 * defines the fix & unchangeable root node of the artefact's tree.
	 * This is necessary, 'cos a JTree can have only one
	 * root.
	 * </p>
	 * This node's name is <i>Artefact</i>
	 */
	private DefaultMutableTreeNode root_artefacts = null;
	
	/**
	 * we explicitly create a tree model for units. We this approach
	 * it's guaranteed that's adapted properly.
	 */
	private DefaultTreeModel treeModel_units = null;
	
	/**
	 * a fix and unchangeable root node of tree units.
	 * This component resides within a tabbed pane.
	 * </p>
	 * The root node is named <i>Units</i>
	 */
	private DefaultMutableTreeNode root_units = null;
	
	/**
	 * customized table model, holds the data of facets.
	 */
	private CustomizedTableModel tableModel_facets = null;
	
	/**
	 * tracks the current selected row.
	 * Used in dynamically changing panels.
	 */
	private int currentlySelectedRow = 0;
	
	/**
	 * tracks the current selected column.
	 * Used in dynamically changing panels.
	 */
	private int currentlySelectedColumn = 0;
	
	/**
	 * the UNIX command 'pwd'.
	 * Is set to protected because this package
	 * is allowed to modify it.
	 */
	protected URI workingDir = null;
	
	/**
	 * constructor is passed a <i>SingleFrameApplication</i> and a Controller
	 * object. It builds up the UI by calling the initComponents() method, which
	 * in turn is created by Netbeans IDE.
	 * 
	 * @param dataGenerator
	 * 		{@link SingleFrameApplication} object is mandatory, when
	 * 		inheriting from FrameView.
	 */
	public DataGeneratorView(SingleFrameApplication dataGenerator)
	{
		super(dataGenerator);
		
		this.create();
		
		this.setLookAndFeel();
		
		this.initBefore();
		
		// used by Netbeans
		this.initComponents();
		
		this.initAfter();
		
		this.registerObservers();
		
		this.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.getFrame().setPreferredSize(new Dimension(800, 500));
	}
	
	/**
	 * this method creates objects which are not created by
	 * controller layer. All objects created here are in the same
	 * layer, that is the view, and therefore controller isn't needed
	 * to do the job, nor is a factory useful.
	 */
	private void create()
	{
		// some actions are passed down & handled here
		this.dataGeneratorActions = ViewFactory.createDataGeneratorActions(this);
	}
	
	/**
	 * sets the look & feel for the application.
	 * At this time the method uses the system look, but it
	 * could be changed in future.
	 */
	private void setLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(DataGenerator.getApplication().getMainFrame());
		}
		catch(ClassNotFoundException cnfe)
		{
			ErrorMessage.getInstance().printMessage(cnfe, "ClassNotFoundException");
		}
		catch(IllegalAccessException iae)
		{
			ErrorMessage.getInstance().printMessage(iae, "IllegalAccessException");
		}
		catch(InstantiationException ie)
		{
			ErrorMessage.getInstance().printMessage(ie, "InstantiationException");
		}
		catch(UnsupportedLookAndFeelException ulafe)
		{
			ErrorMessage.getInstance().printMessage(ulafe, "UnsupportedLookAndFellException");
		}
	}
	
	/**
	 * work-around for Netbeans {@link #initComponents()} method.
	 * This method is called before.
	 */
	private void initBefore()
	{
		// this node is fix and not changeable
		this.root_artefacts = new DefaultMutableTreeNode("Artefacts");
		
		// tree model, holds artefacts
		this.treeModel_artefacts = new DefaultTreeModel(this.root_artefacts);
		
		this.root_units = new DefaultMutableTreeNode("Units");
		
		this.treeModel_units = new DefaultTreeModel(this.root_units);
		
		// table model, holds facets
		this.tableModel_facets = new CustomizedTableModel();
	}
	
	/**
	 * work-around for Netbeans {@link #initComponents()} method.
	 * It's called after and sets up key & mouse listener for some
	 * crucial UI components.
	 */
	private void initAfter()
	{
		this.panel_distribution.setDataGeneratorView(this);
		
		this.initTextField_Quantity();
		
		this.initTreeArtefact();
		
		this.initTable();
		
		this.initTreeUnits();
		
		this.groupRadioButtons();
		
		this.createGenerators();
		
		this.createProbabilityDistributions();
		
		this.initComboBoxGenerators();
		
		this.initComboBoxInterval();
		
		this.initTabbedPaneFunctions();
		
		this.initCheckBoxesMenuItem();
	}
	
	/**
	 * sets up an key listener to text field <i>quantity.</i>
	 * We listen to <i>ENTER</i> key as a short cut for 
	 * a OK button.
	 */
	private void initTextField_Quantity()
	{
		// listen to 'ENTER'
		KeyListener keyListener_quantity = new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent ke)
			{
				if(ke.getKeyCode() == KeyEvent.VK_ENTER)
				{
					updateQuantity();
				}
			}
		};
		
		this.formattedTextField_quantity.addKeyListener(keyListener_quantity);
		
		this.formattedTextField_quantity.setColumns(6);
		this.formattedTextField_quantity.setEditable(true);
	}
	
	/**
	 * sets up some customized settings on tree component.
	 * </p>
	 * First, tree selection mode is set to single tree selection.
	 * So we have at any time total control over what is selected.
	 * And swing worker has only one single task at a time.
	 * </p>
	 * Next, there is a customized tree model listener.
	 * </p>
	 * Furthermore, a mouse listener has been set up on that component.
	 * So the user has the ability, to use the mouse to navigate or browse
	 * through the tree. Each time a different node has been selected, its
	 * facets are loaded in background and displayed in the table.
	 * </p>
	 * To make life even simpler, a key listener listens to <i>DEL</i> key.
	 * With its help selected nodes are deleted. Which nodes exactly are removed,
	 * depends highly on the algorithm.
	 * </p>
	 * Last, the tree has been customized in its rendering behaviour.
	 * Neither folder nor document animation are used, 'cos we do not have those
	 * contexts.
	 */
	private void initTreeArtefact()
	{
		// set tree selection mode
		this.tree_artefacts.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		// add tree model listener
		this.treeModel_artefacts.addTreeModelListener(new CustomizedTreeModelListener());
		
		// add tree selection listener
		MouseListener mouseListener = new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent me)
			{
				loadFacets();
			}
		};
		
		// listen to key 'DEL'
		KeyListener keyListener_tree = new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent ke)
			{
				// 'DEL'
				if(ke.getKeyCode() == KeyEvent.VK_DELETE)
				{
					deleteArtefact();
					deleteFacet();
				}
			}
		};
		
		this.tree_artefacts.addMouseListener(mouseListener);
		this.tree_artefacts.addKeyListener(keyListener_tree);
		
		// customize cell rendering
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		Icon icon = null;
		renderer.setLeafIcon(icon);
		renderer.setClosedIcon(icon);
		renderer.setOpenIcon(icon);
		
		this.tree_artefacts.setCellRenderer(renderer);
	}
	
	/**
	 * initializes the table with some additional features.
	 * </p>
	 * First we use good column sizes. It takes either the
	 * size of column headers or the size of a cell. It depends 
	 * on the max. size.
	 * </p>
	 * Second, there are some listeners registered to the table.
	 * <ul>
	 * 		<li><b>list selection listener to column</b></li>
	 * 		<li><b>list selection listener to row</b></li>
	 * </ul>
	 * The table has had a default selection model. But we wanna
	 * notified when a different column has been selected. This
	 * is only possible customizing the default setting.
	 * </p>
	 * In this approach, both listeners to column and row has been
	 * modified, but we use only column to listen.
	 * This approach has been necessary, 'cos not implementing row
	 * would lead to undesired behaviour. We couldn't select any row
	 * anymore. And that's definitely not what one strives for.
	 */
	private void initTable()
	{
		// use good column sizes right from the beginning
		this.tableModel_facets.initColumnSizes(this.table_currentFacetts);
		
		TableColumn column = this.table_currentFacetts.getColumnModel().getColumn(1);
		
		this.setUpDataTypeColumn(column);
		
		// add list selection listener
		this.table_currentFacetts.setCellSelectionEnabled(true);
		this.table_currentFacetts.setColumnSelectionAllowed(true);
		
		// listen to column
		ListSelectionModel lsm_column = this.table_currentFacetts.getColumnModel().getSelectionModel();
		
		lsm_column.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent lse)
			{
				ListSelectionModel lsm = (ListSelectionModel) lse.getSource();
				
				if(!lsm.isSelectionEmpty())
				{
					currentlySelectedColumn = lsm.getMinSelectionIndex();
					
					loadDynamicPanel(currentlySelectedColumn);
				}
			}
		});
		
		// listen to row
		ListSelectionModel lsm_row = this.table_currentFacetts.getSelectionModel();
		
		lsm_row.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent lse)
			{
				ListSelectionModel lsm = (ListSelectionModel) lse.getSource();
				
				if(!lsm.isSelectionEmpty())
				{
					currentlySelectedRow = lsm.getMinSelectionIndex();
				}
			}
		});
		
		this.table_currentFacetts.setSelectionModel(lsm_column);
		this.table_currentFacetts.setSelectionModel(lsm_row);
	}
	
	/**
	 * fiddle with column's cell editors.
	 * This method builds a combo box and fills it with
	 * data type elements and sets it into the passed
	 * column object.
	 * 
	 * @param column
	 * 		the column to render as combo box
	 */
	private void setUpDataTypeColumn(TableColumn column)
	{
		JComboBox comboBox = new JComboBox();
		
		comboBox.addItem(DataType.BYTE);
		comboBox.addItem(DataType.SHORT);
		comboBox.addItem(DataType.INT);
		comboBox.addItem(DataType.LONG);
		comboBox.addItem(DataType.FLOAT);
		comboBox.addItem(DataType.DOUBLE);
		comboBox.addItem(DataType.BOOLEAN);
		comboBox.addItem(DataType.STRING);
		comboBox.addItem(DataType.DATE);
		
		column.setCellEditor(new DefaultCellEditor(comboBox));
	}
	
	/**
	 * sets up settings and customizes the tree used to display
	 * units. In the point of view of data generation units are
	 * standardized weights and measurements. There exist a few
	 * standards, like imperial system, US system, international system.
	 * There also units for natural subjects and physics.
	 * </p>
	 * All those unit elements can be displayed here.
	 * Because at this stage, there had been only a few initially created,
	 * it's up to the tester or user to adapt it to his or her needs.
	 * </p>
	 * To make life easier, we set up key listener to <i>DEL</i> key
	 * and a mouse listener to select a node (unit). And cell rendering
	 * is, of course, adapted like it has been done with other components,
	 * using tree structure.
	 */
	private void initTreeUnits()
	{
		// set tree selection mode
		this.tree_functionUnit.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		// add tree model listener
		this.treeModel_units.addTreeModelListener(new CustomizedTreeModelListener());
		
		// add tree selection listener
		MouseListener mouseListener = new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent me)
			{
				setSelectedUnitToTable();
			}
		};
		
		// listen to key 'DEL'
		KeyListener keyListener_treeUnit = new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent ke)
			{
				// 'DEL'
				if(ke.getKeyCode() == KeyEvent.VK_DELETE)
				{
					deleteUnit();
				}
			}
		};
		
		this.tree_functionUnit.addMouseListener(mouseListener);
		this.tree_functionUnit.addKeyListener(keyListener_treeUnit);
		
		// customize cell rendering
		DefaultTreeCellRenderer dtcr = new DefaultTreeCellRenderer();
		
		Icon icon = null;
		
		dtcr.setLeafIcon(icon);
		dtcr.setClosedIcon(icon);
		dtcr.setOpenIcon(icon);
		
		this.tree_functionUnit.setCellRenderer(dtcr);
	}
	
	/**
	 * this method initializes the combo box component
	 * used by the predefined generators.
	 * Depending on which generator has been selected by
	 * the user options are available or will be disabled.
	 * The combo box uses an action listener to get the latest
	 * selected item. In general, those item within that box
	 * are different kinds of {@link Generator}s which in turn have 
	 * the {@link FillBehaviour} signature implemented.
	 * </p>
	 * <font size=6><b>Generator objects</b></font></br>
	 * The combo box uses generator objects rather than a simple string
	 * for representation. This is necessary, because generators are
	 * observable. Each time an observer registers to or un-registers from
	 * any generator type this is notified to the UI and the combo list
	 * is gonna be updated.
	 * </p>
	 * <font size=6><b>Clone</b></font></br>
	 * But the objects used have also a disadvantage. We can't use the
	 * generators to fill with the settings an user performs, because
	 * of references. All generators of the same type will be updated each
	 * time. But that's not what's intended. To get a new object each time
	 * the generator will be cloned and settings are performed on the clone,
	 * rather on the original object.
	 */
	private void initComboBoxGenerators()
	{
		// add action listener
		this.comboBox_predefinedGenerator.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				getSelectedGenerator();
			}
		});
	}
	
	/**
	 * initializes the combo box used for the interval.
	 * This is the option where the user can define the 
	 * interval type. In other words: using float or integer values
	 */
	private void initComboBoxInterval()
	{
		// add action listener
		this.comboBox_interval.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				// TODO
			}
		});
	}
	
	/**
	 * initializes the check boxes on menu item run.
	 * Here are the possible random number generators hold.
	 * The user can change the RNG by selecting a different one.
	 * By default, however, it is MersenneTwister.
	 */
	private void initCheckBoxesMenuItem()
	{
		// AES
		this.checkBox_menuItem_AES.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent ie)
			{
				Object source = ie.getItemSelectable();
				
				if((source == checkBox_menuItem_AES) && (ie.getStateChange() == ItemEvent.SELECTED))
				{
					ControllerFactory.getController().setPRNG(PRNG.AESCounter); 
				}
			}
		});
		
		// Cellular
		this.checkBox_menuItem_Cellular.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent ie)
			{
				Object source = ie.getItemSelectable();
				
				if((source == checkBox_menuItem_Cellular) && (ie.getStateChange() == ItemEvent.SELECTED))
				{
					ControllerFactory.getController().setPRNG(PRNG.CelllarAutomaton);
				}
			}
		});
		
		// CMWC4096
		this.checkBox_menuItem_CMWC4096.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent ie)
			{
				Object source = ie.getItemSelectable();
				
				if((source == checkBox_menuItem_CMWC4096) && (ie.getStateChange() == ItemEvent.SELECTED))
				{
					ControllerFactory.getController().setPRNG(PRNG.CMWC4096);
				}
			}
		});
		
		// JavaRandom
		this.checkBox_menuItem_JavaRandom.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent ie)
			{
				Object source = ie.getItemSelectable();
				
				if((source == checkBox_menuItem_JavaRandom) && (ie.getStateChange() == ItemEvent.SELECTED))
				{
					ControllerFactory.getController().setPRNG(PRNG.JavaRNG);
				}
			}
		});
		
		// Mersenne
		this.checkBox_menuItem_Mersenne.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent ie)
			{
				Object source = ie.getItemSelectable();
				
				if((source == checkBox_menuItem_Mersenne) && (ie.getStateChange() == ItemEvent.SELECTED))
				{
					ControllerFactory.getController().setPRNG(PRNG.MersenneTwister);
				}
			}
		});
		
		// SecureRandom
		this.checkBox_menuItem_SecureRandom.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent ie)
			{
				Object source = ie.getItemSelectable();
				
				if((source == checkBox_menuItem_SecureRandom) && (ie.getStateChange() == ItemEvent.SELECTED))
				{
					ControllerFactory.getController().setPRNG(PRNG.SecureRandom);
				}
			}
		});
		
		// XORShift
		this.checkBox_menuItem_XORShift.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent ie)
			{
				Object source = ie.getItemSelectable();
				
				if((source == checkBox_menuItem_XORShift) && (ie.getStateChange() == ItemEvent.SELECTED))
				{
					ControllerFactory.getController().setPRNG(PRNG.XORShift);
				}
			}
		});
		
		// set default as selected 
		PRNG prng = ControllerFactory.getController().getPRNG();
		
		// AES
		if(prng.equals(PRNG.AESCounter))
		{
			this.checkBox_menuItem_AES.setSelected(true);
		}
		
		// Cellar
		else if(prng.equals(PRNG.CelllarAutomaton))
		{
			this.checkBox_menuItem_Cellular.setSelected(true);
		}
		
		// CMWC4096
		else if(prng.equals(PRNG.CMWC4096))
		{
			this.checkBox_menuItem_CMWC4096.setSelected(true);
		}
		
		// JavaRandom
		else if(prng.equals(PRNG.JavaRNG))
		{
			this.checkBox_menuItem_JavaRandom.setSelected(true);
		}
		
		// MersenneTwister
		else if(prng.equals(PRNG.MersenneTwister))
		{
			this.checkBox_menuItem_Mersenne.setSelected(true);
		}
		
		// SecureRandom
		else if(prng.equals(PRNG.SecureRandom))
		{
			this.checkBox_menuItem_SecureRandom.setSelected(true);
		}
		
		// XORShift
		else if(prng.equals(PRNG.XORShift))
		{
			this.checkBox_menuItem_XORShift.setSelected(true);
		}
	}
	
	/**
	 * this method adds a listener to the tabbed pane function.
	 * So whenever the tabbed pane changes, this method is responsible
	 * to to the change.
	 */
	private void initTabbedPaneFunctions()
	{
		// add listener
		this.tabbedPane_functions.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent ce)
			{
				JTabbedPane tp = (JTabbedPane) ce.getSource();
				
				// selected tab
				int n = tp.getSelectedIndex();
				
				switch(n)
				{
					// quantity
					case 0:
						break;
					
					// modification
					case 1:
						break;
					
					// dependency
					case 2:
						break;
					
					// fill type
					case 3:
						break;
					
					// distribution
					case 4:
//						ProbabilityDistribution dist = isProbabilityAlreadySet();
//						if(dist != null)
//						{
//							panel_distribution.useValuesAlreadySet(dist);
//						}
//						else
//						{
//							panel_distribution.useFirstInBox();
//						}
						break;
					
					// weight & measurement
					case 5:
						break;
				}
			}
		});
	}
	
	/**
	 * registers to all initial available units.
	 * If a change in the data structure happens, we get notified. 
	 */
	private void registerObservers()
	{
		for(Iterator<Unit> i=ControllerFactory.getController().iteratorUnit(); i.hasNext();)
		{
			Unit unit = i.next();
			
			if(!unit.isCustomized())
			{
				ControllerFactory.getController().registerObserverUnit(this, unit.getName());
			}
			else
			{
				ControllerFactory.getController().registerObserverUnitCustom(this, unit.getName());
			}
		}
	}
	
	/**
	 * makes a group of radio buttons of predefined generators.
	 * This is necessary, because only one at a time is supposed
	 * to be selected.
	 * </p>
	 * The 1st group is the language type. Only a language at a
	 * time should be selected.
	 * </p>
	 * The 2nd group gathers the gender. Only a gender at a time
	 * will be selectable.
	 * </p>
	 * The 3rd group is reserved only for dates. Likewise, only 
	 * one at a time is selectable.
	 */
	private void groupRadioButtons()
	{
		// language type
		this.buttonGroup_predefinedGenerator_languageType.add(this.radioButton_predefinedGenerator_us);
		this.buttonGroup_predefinedGenerator_languageType.add(this.radioButton_predefinedGenerator_de);
		
		// gender
		this.buttonGroup_predefinedGenerator_gender.add(this.radioButton_gender_female);
		this.buttonGroup_predefinedGenerator_gender.add(this.radioButton_gender_male);
		
		// date
		this.buttonGroup_predefinedGenerator_date.add(this.radioButton_date_year);
		this.buttonGroup_predefinedGenerator_date.add(this.radioButton_date_month);
		this.buttonGroup_predefinedGenerator_date.add(this.radioButton_date_day);
		
		// PRNGs
		this.buttonGroup_prngs.add(this.checkBox_menuItem_AES);
		this.buttonGroup_prngs.add(this.checkBox_menuItem_Cellular);
		this.buttonGroup_prngs.add(this.checkBox_menuItem_CMWC4096);
		this.buttonGroup_prngs.add(this.checkBox_menuItem_JavaRandom);
		this.buttonGroup_prngs.add(this.checkBox_menuItem_Mersenne);
		this.buttonGroup_prngs.add(this.checkBox_menuItem_SecureRandom);
		this.buttonGroup_prngs.add(this.checkBox_menuItem_XORShift);
	}
	
	/**
	 * creates the pre-defined generators.
	 * The view can't actually create them, so the call
	 * is passed to the constructor, which does the job.
	 */
	private void createGenerators()
	{
		ControllerFactory.getController().createAndRegisterGenerator(this);
	}
	
	/**
	 * passes a request to the controller layer to create
	 * probability distributions. The view gets a notification
	 * about them. 
	 */
	private void createProbabilityDistributions()
	{
		ControllerFactory.getController().createAndRegisterDistribution(this);
	}
	
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_main = new javax.swing.JPanel();
        toolBar = new javax.swing.JToolBar();
        button_loadEcoreModel = new javax.swing.JButton();
        button_loadProject = new javax.swing.JButton();
        button_saveProject = new javax.swing.JButton();
        separator_toolBar3 = new javax.swing.JToolBar.Separator();
        button_addArtefact = new javax.swing.JButton();
        button_delArtefact = new javax.swing.JButton();
        separator_toolBar1 = new javax.swing.JToolBar.Separator();
        button_nullValues = new javax.swing.JButton();
        button_multiplicityValues = new javax.swing.JButton();
        separator_toolBar2 = new javax.swing.JToolBar.Separator();
        button_generateData = new javax.swing.JButton();
        panel_progressBar = new javax.swing.JPanel();
        progressBar = new javax.swing.JProgressBar();
        splitPane_vertical = new javax.swing.JSplitPane();
        panel_artefacts = new javax.swing.JPanel();
        scrollPane_artefacts = new javax.swing.JScrollPane();
        tree_artefacts = new javax.swing.JTree();
        tabbedPane_facetsSelectedArtefact = new javax.swing.JTabbedPane();
        splitPane_function_facets = new javax.swing.JSplitPane();
        tabbedPane_functions = new javax.swing.JTabbedPane();
        panel_functionsQuantity = new javax.swing.JPanel();
        panel_quantity = new javax.swing.JPanel();
        formattedTextField_quantity = new javax.swing.JFormattedTextField();
        button_quantity = new javax.swing.JButton();
        label_quantity_desc = new javax.swing.JLabel();
        panel_functionsModification = new javax.swing.JPanel();
        panel_facetModification = new javax.swing.JPanel();
        button_addFacet = new javax.swing.JButton();
        button_delFacet = new javax.swing.JButton();
        label_modification_desc = new javax.swing.JLabel();
        panel_functionDependency = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panel_functionFillType = new javax.swing.JPanel();
        tabbedPane_subFunctionFillType = new javax.swing.JTabbedPane();
        panel_externalFile = new javax.swing.JPanel();
        button_externalFile_linkFile = new javax.swing.JButton();
        formattedTextField_externalFile_path = new javax.swing.JFormattedTextField();
        label_externalFile_path = new javax.swing.JLabel();
        label_externalFile_desc = new javax.swing.JLabel();
        button_externalFile_reset = new javax.swing.JButton();
        panel_predefinedGenerator = new javax.swing.JPanel();
        label_predefinedGenerator_desc = new javax.swing.JLabel();
        subPanel_predefinedGenerators = new javax.swing.JPanel();
        comboBox_predefinedGenerator = new javax.swing.JComboBox();
        label_predefinedGenerator_availableGenerators = new javax.swing.JLabel();
        panel_predefinedGenerator_type = new javax.swing.JPanel();
        radioButton_predefinedGenerator_us = new javax.swing.JRadioButton();
        radioButton_predefinedGenerator_de = new javax.swing.JRadioButton();
        panel_predefinedGenerator_gender = new javax.swing.JPanel();
        radioButton_gender_female = new javax.swing.JRadioButton();
        radioButton_gender_male = new javax.swing.JRadioButton();
        button_generator_ok = new javax.swing.JButton();
        panel_predefinedGenerator_date = new javax.swing.JPanel();
        radioButton_date_year = new javax.swing.JRadioButton();
        radioButton_date_month = new javax.swing.JRadioButton();
        radioButton_date_day = new javax.swing.JRadioButton();
        button_generator_reset = new javax.swing.JButton();
        panel_Editor = new javax.swing.JPanel();
        label_editor_desc = new javax.swing.JLabel();
        subPanel_editor = new javax.swing.JPanel();
        scrollPane_editor = new javax.swing.JScrollPane();
        textArea_editor = new javax.swing.JTextArea();
        button_editor_save = new javax.swing.JButton();
        button_editor_ok = new javax.swing.JButton();
        checkBox_editor_incrementValue = new javax.swing.JCheckBox();
        button_editor_reset = new javax.swing.JButton();
        button_editor_load = new javax.swing.JButton();
        panel_interval = new javax.swing.JPanel();
        panel_interval_notation = new javax.swing.JPanel();
        label_interval_notation4 = new javax.swing.JLabel();
        label_interval_notation3 = new javax.swing.JLabel();
        label_interval_notation2 = new javax.swing.JLabel();
        label_interval_notation1 = new javax.swing.JLabel();
        formattedTextField_interval = new javax.swing.JFormattedTextField();
        label_interval = new javax.swing.JLabel();
        button_interval_ok = new javax.swing.JButton();
        button_interval_reset = new javax.swing.JButton();
        label_interval_steps = new javax.swing.JLabel();
        formattedTextField_interval_steps = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        comboBox_interval = new javax.swing.JComboBox();
        panel_formula = new javax.swing.JPanel();
        label_formula_desc = new javax.swing.JLabel();
        panel_functionDistribution = new javax.swing.JPanel();
        panel_distribution = new de.uni.bamberg.wiai.minf.forflow.datagenerator.view.DistributionPanel();
        panel_graph = new de.uni.bamberg.wiai.minf.forflow.datagenerator.view.graph.Graph();
        panel_functionUnit = new javax.swing.JPanel();
        panel_functionUnit_availableUnits = new javax.swing.JPanel();
        scrollPane_functionUnit = new javax.swing.JScrollPane();
        tree_functionUnit = new javax.swing.JTree();
        label_availableUnits = new javax.swing.JLabel();
        panel_functionUnit_modify = new javax.swing.JPanel();
        button_addUnit = new javax.swing.JButton();
        button_deleteUnit = new javax.swing.JButton();
        panel_containsFacets = new javax.swing.JPanel();
        scrollPane_currentFacets = new javax.swing.JScrollPane();
        table_currentFacetts = new javax.swing.JTable();
        menuBar = new javax.swing.JMenuBar();
        menu_file = new javax.swing.JMenu();
        menuItem_loadModel = new javax.swing.JMenuItem();
        separator_menuFile2 = new javax.swing.JSeparator();
        menuItem_newProject = new javax.swing.JMenuItem();
        menuItem_loadProject = new javax.swing.JMenuItem();
        menuItem_saveProject = new javax.swing.JMenuItem();
        separator_menuFile1 = new javax.swing.JSeparator();
        menuItem_exit = new javax.swing.JMenuItem();
        menu_edit = new javax.swing.JMenu();
        menuItem_nullValues = new javax.swing.JMenuItem();
        menuItem_multiplicityValues = new javax.swing.JMenuItem();
        separator_menuEdit2 = new javax.swing.JSeparator();
        menuItem_addFacet = new javax.swing.JMenuItem();
        menuItem_deleteFacet = new javax.swing.JMenuItem();
        separator_menuEdit1 = new javax.swing.JSeparator();
        menuItem_createNewArtefact = new javax.swing.JMenuItem();
        menuItem_deleteSelectedArtefact = new javax.swing.JMenuItem();
        menu_view = new javax.swing.JMenu();
        menuItem_update = new javax.swing.JMenuItem();
        menu_debug = new javax.swing.JMenu();
        menuItem_checkBox_logging = new javax.swing.JCheckBoxMenuItem();
        menu_run = new javax.swing.JMenu();
        menuItem_generateData = new javax.swing.JMenuItem();
        separator_run = new javax.swing.JSeparator();
        menuItem_listOfPRNGs = new javax.swing.JMenu();
        checkBox_menuItem_Mersenne = new javax.swing.JCheckBoxMenuItem();
        checkBox_menuItem_AES = new javax.swing.JCheckBoxMenuItem();
        checkBox_menuItem_Cellular = new javax.swing.JCheckBoxMenuItem();
        checkBox_menuItem_SecureRandom = new javax.swing.JCheckBoxMenuItem();
        checkBox_menuItem_XORShift = new javax.swing.JCheckBoxMenuItem();
        checkBox_menuItem_CMWC4096 = new javax.swing.JCheckBoxMenuItem();
        checkBox_menuItem_JavaRandom = new javax.swing.JCheckBoxMenuItem();
        menu_help = new javax.swing.JMenu();
        menuItem_help = new javax.swing.JMenuItem();
        menuItem_about = new javax.swing.JMenuItem();
        buttonGroup_predefinedGenerator_languageType = new javax.swing.ButtonGroup();
        buttonGroup_predefinedGenerator_gender = new javax.swing.ButtonGroup();
        buttonGroup_predefinedGenerator_date = new javax.swing.ButtonGroup();
        buttonGroup_prngs = new javax.swing.ButtonGroup();

        panel_main.setMinimumSize(new java.awt.Dimension(400, 500));
        panel_main.setName("panel_main"); // NOI18N
        panel_main.setPreferredSize(new java.awt.Dimension(800, 500));

        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        toolBar.setName("toolBar"); // NOI18N
        toolBar.setPreferredSize(new java.awt.Dimension(800, 500));

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(de.uni.bamberg.wiai.minf.forflow.datagenerator.DataGenerator.class).getContext().getActionMap(DataGeneratorView.class, this);
        button_loadEcoreModel.setAction(actionMap.get("loadModel")); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(de.uni.bamberg.wiai.minf.forflow.datagenerator.DataGenerator.class).getContext().getResourceMap(DataGeneratorView.class);
        button_loadEcoreModel.setIcon(resourceMap.getIcon("button_loadEcoreModel.icon")); // NOI18N
        button_loadEcoreModel.setText(resourceMap.getString("button_loadEcoreModel.text")); // NOI18N
        button_loadEcoreModel.setToolTipText(resourceMap.getString("button_loadEcoreModel.toolTipText")); // NOI18N
        button_loadEcoreModel.setFocusable(false);
        button_loadEcoreModel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button_loadEcoreModel.setName("button_loadEcoreModel"); // NOI18N
        button_loadEcoreModel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(button_loadEcoreModel);

        button_loadProject.setAction(actionMap.get("loadProject")); // NOI18N
        button_loadProject.setIcon(resourceMap.getIcon("button_loadProject.icon")); // NOI18N
        button_loadProject.setText(resourceMap.getString("button_loadProject.text")); // NOI18N
        button_loadProject.setToolTipText(resourceMap.getString("button_loadProject.toolTipText")); // NOI18N
        button_loadProject.setFocusable(false);
        button_loadProject.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button_loadProject.setName("button_loadProject"); // NOI18N
        button_loadProject.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(button_loadProject);

        button_saveProject.setAction(actionMap.get("saveProject")); // NOI18N
        button_saveProject.setIcon(resourceMap.getIcon("button_saveProject.icon")); // NOI18N
        button_saveProject.setText(resourceMap.getString("button_saveProject.text")); // NOI18N
        button_saveProject.setToolTipText(resourceMap.getString("button_saveProject.toolTipText")); // NOI18N
        button_saveProject.setFocusable(false);
        button_saveProject.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button_saveProject.setName("button_saveProject"); // NOI18N
        button_saveProject.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(button_saveProject);

        separator_toolBar3.setName("separator_toolBar3"); // NOI18N
        toolBar.add(separator_toolBar3);

        button_addArtefact.setAction(actionMap.get("createNewArtefact")); // NOI18N
        button_addArtefact.setIcon(resourceMap.getIcon("button_addArtefact.icon")); // NOI18N
        button_addArtefact.setText(resourceMap.getString("button_addArtefact.text")); // NOI18N
        button_addArtefact.setToolTipText(resourceMap.getString("button_addArtefact.toolTipText")); // NOI18N
        button_addArtefact.setFocusable(false);
        button_addArtefact.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button_addArtefact.setName("button_addArtefact"); // NOI18N
        button_addArtefact.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(button_addArtefact);

        button_delArtefact.setAction(actionMap.get("deleteArtefact")); // NOI18N
        button_delArtefact.setIcon(resourceMap.getIcon("button_delArtefact.icon")); // NOI18N
        button_delArtefact.setText(resourceMap.getString("button_delArtefact.text")); // NOI18N
        button_delArtefact.setToolTipText(resourceMap.getString("button_delArtefact.toolTipText")); // NOI18N
        button_delArtefact.setFocusable(false);
        button_delArtefact.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button_delArtefact.setName("button_delArtefact"); // NOI18N
        button_delArtefact.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(button_delArtefact);

        separator_toolBar1.setName("separator_toolBar1"); // NOI18N
        toolBar.add(separator_toolBar1);

        button_nullValues.setAction(actionMap.get("openNullValueDialog")); // NOI18N
        button_nullValues.setIcon(resourceMap.getIcon("button_nullValues.icon")); // NOI18N
        button_nullValues.setText(resourceMap.getString("button_nullValues.text")); // NOI18N
        button_nullValues.setToolTipText(resourceMap.getString("button_nullValues.toolTipText")); // NOI18N
        button_nullValues.setFocusable(false);
        button_nullValues.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button_nullValues.setName("button_nullValues"); // NOI18N
        button_nullValues.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(button_nullValues);

        button_multiplicityValues.setAction(actionMap.get("openMultiplicityValuesDialog")); // NOI18N
        button_multiplicityValues.setIcon(resourceMap.getIcon("button_multiplicityValues.icon")); // NOI18N
        button_multiplicityValues.setText(resourceMap.getString("button_multiplicityValues.text")); // NOI18N
        button_multiplicityValues.setToolTipText(resourceMap.getString("button_multiplicityValues.toolTipText")); // NOI18N
        button_multiplicityValues.setFocusable(false);
        button_multiplicityValues.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button_multiplicityValues.setName("button_multiplicityValues"); // NOI18N
        button_multiplicityValues.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(button_multiplicityValues);

        separator_toolBar2.setName("separator_toolBar2"); // NOI18N
        toolBar.add(separator_toolBar2);

        button_generateData.setAction(actionMap.get("generateData")); // NOI18N
        button_generateData.setIcon(resourceMap.getIcon("button_generateData.icon")); // NOI18N
        button_generateData.setText(resourceMap.getString("button_generateData.text")); // NOI18N
        button_generateData.setToolTipText(resourceMap.getString("button_generateData.toolTipText")); // NOI18N
        button_generateData.setFocusable(false);
        button_generateData.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button_generateData.setName("button_generateData"); // NOI18N
        button_generateData.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(button_generateData);

        panel_progressBar.setName("panel_progressBar"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout panel_progressBarLayout = new javax.swing.GroupLayout(panel_progressBar);
        panel_progressBar.setLayout(panel_progressBarLayout);
        panel_progressBarLayout.setHorizontalGroup(
            panel_progressBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_progressBarLayout.createSequentialGroup()
                .addContainerGap(548, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel_progressBarLayout.setVerticalGroup(
            panel_progressBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_progressBarLayout.createSequentialGroup()
                .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );

        splitPane_vertical.setAutoscrolls(true);
        splitPane_vertical.setName("splitPane_vertical"); // NOI18N
        splitPane_vertical.setOneTouchExpandable(true);

        panel_artefacts.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("panel_artefacts.border.title"), javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP)); // NOI18N
        panel_artefacts.setMinimumSize(new java.awt.Dimension(140, 400));
        panel_artefacts.setName("panel_artefacts"); // NOI18N
        panel_artefacts.setPreferredSize(new java.awt.Dimension(140, 400));

        scrollPane_artefacts.setName("scrollPane_artefacts"); // NOI18N

        tree_artefacts.setModel(this.treeModel_artefacts);
        tree_artefacts.setLargeModel(true);
        tree_artefacts.setName("tree_artefacts"); // NOI18N
        scrollPane_artefacts.setViewportView(tree_artefacts);

        javax.swing.GroupLayout panel_artefactsLayout = new javax.swing.GroupLayout(panel_artefacts);
        panel_artefacts.setLayout(panel_artefactsLayout);
        panel_artefactsLayout.setHorizontalGroup(
            panel_artefactsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane_artefacts, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
        );
        panel_artefactsLayout.setVerticalGroup(
            panel_artefactsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane_artefacts, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
        );

        splitPane_vertical.setTopComponent(panel_artefacts);

        tabbedPane_facetsSelectedArtefact.setForeground(resourceMap.getColor("tabbedPane_facetsSelectedArtefact.foreground")); // NOI18N
        tabbedPane_facetsSelectedArtefact.setName("tabbedPane_facetsSelectedArtefact"); // NOI18N

        splitPane_function_facets.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        splitPane_function_facets.setResizeWeight(0.5);
        splitPane_function_facets.setName("splitPane_function_facets"); // NOI18N
        splitPane_function_facets.setOneTouchExpandable(true);

        tabbedPane_functions.setMinimumSize(new java.awt.Dimension(0, 0));
        tabbedPane_functions.setName("tabbedPane_functions"); // NOI18N

        panel_functionsQuantity.setName("panel_functionsQuantity"); // NOI18N

        panel_quantity.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("panel_quantity.border.title"), javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP)); // NOI18N
        panel_quantity.setName("panel_quantity"); // NOI18N

        formattedTextField_quantity.setText(resourceMap.getString("formattedTextField_quantity.text")); // NOI18N
        formattedTextField_quantity.setToolTipText(resourceMap.getString("formattedTextField_quantity.toolTipText")); // NOI18N
        formattedTextField_quantity.setName("formattedTextField_quantity"); // NOI18N

        button_quantity.setAction(actionMap.get("quantity_OkButton")); // NOI18N
        button_quantity.setText(resourceMap.getString("button_quantity.text")); // NOI18N
        button_quantity.setName("button_quantity"); // NOI18N

        javax.swing.GroupLayout panel_quantityLayout = new javax.swing.GroupLayout(panel_quantity);
        panel_quantity.setLayout(panel_quantityLayout);
        panel_quantityLayout.setHorizontalGroup(
            panel_quantityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_quantityLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_quantityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(formattedTextField_quantity, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .addComponent(button_quantity))
                .addContainerGap())
        );
        panel_quantityLayout.setVerticalGroup(
            panel_quantityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_quantityLayout.createSequentialGroup()
                .addComponent(formattedTextField_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_quantity)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        label_quantity_desc.setFont(resourceMap.getFont("label_quantity_desc.font")); // NOI18N
        label_quantity_desc.setForeground(resourceMap.getColor("label_quantity_desc.foreground")); // NOI18N
        label_quantity_desc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_quantity_desc.setText(resourceMap.getString("label_quantity_desc.text")); // NOI18N
        label_quantity_desc.setName("label_quantity_desc"); // NOI18N

        javax.swing.GroupLayout panel_functionsQuantityLayout = new javax.swing.GroupLayout(panel_functionsQuantity);
        panel_functionsQuantity.setLayout(panel_functionsQuantityLayout);
        panel_functionsQuantityLayout.setHorizontalGroup(
            panel_functionsQuantityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_functionsQuantityLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(label_quantity_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_functionsQuantityLayout.setVerticalGroup(
            panel_functionsQuantityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_functionsQuantityLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_functionsQuantityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_quantity_desc))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        tabbedPane_functions.addTab(resourceMap.getString("panel_functionsQuantity.TabConstraints.tabTitle"), panel_functionsQuantity); // NOI18N

        panel_functionsModification.setMinimumSize(new java.awt.Dimension(593, 123));
        panel_functionsModification.setName("panel_functionsModification"); // NOI18N

        panel_facetModification.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("panel_facetModification.border.title"), javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP)); // NOI18N
        panel_facetModification.setName("panel_facetModification"); // NOI18N

        button_addFacet.setAction(actionMap.get("addFacet")); // NOI18N
        button_addFacet.setText(resourceMap.getString("button_addFacet.text")); // NOI18N
        button_addFacet.setToolTipText(resourceMap.getString("button_addFacet.toolTipText")); // NOI18N
        button_addFacet.setName("button_addFacet"); // NOI18N

        button_delFacet.setAction(actionMap.get("deleteFacet")); // NOI18N
        button_delFacet.setText(resourceMap.getString("button_delFacet.text")); // NOI18N
        button_delFacet.setToolTipText(resourceMap.getString("button_delFacet.toolTipText")); // NOI18N
        button_delFacet.setName("button_delFacet"); // NOI18N

        javax.swing.GroupLayout panel_facetModificationLayout = new javax.swing.GroupLayout(panel_facetModification);
        panel_facetModification.setLayout(panel_facetModificationLayout);
        panel_facetModificationLayout.setHorizontalGroup(
            panel_facetModificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_facetModificationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_facetModificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_addFacet, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(button_delFacet))
                .addContainerGap())
        );

        panel_facetModificationLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button_addFacet, button_delFacet});

        panel_facetModificationLayout.setVerticalGroup(
            panel_facetModificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_facetModificationLayout.createSequentialGroup()
                .addComponent(button_addFacet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_delFacet)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        label_modification_desc.setFont(resourceMap.getFont("label_modification_desc.font")); // NOI18N
        label_modification_desc.setForeground(resourceMap.getColor("label_modification_desc.foreground")); // NOI18N
        label_modification_desc.setText(resourceMap.getString("label_modification_desc.text")); // NOI18N
        label_modification_desc.setName("label_modification_desc"); // NOI18N

        javax.swing.GroupLayout panel_functionsModificationLayout = new javax.swing.GroupLayout(panel_functionsModification);
        panel_functionsModification.setLayout(panel_functionsModificationLayout);
        panel_functionsModificationLayout.setHorizontalGroup(
            panel_functionsModificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_functionsModificationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_facetModification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
                .addComponent(label_modification_desc)
                .addContainerGap())
        );
        panel_functionsModificationLayout.setVerticalGroup(
            panel_functionsModificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_functionsModificationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_functionsModificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_modification_desc)
                    .addComponent(panel_facetModification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        tabbedPane_functions.addTab(resourceMap.getString("panel_functionsModification.TabConstraints.tabTitle"), panel_functionsModification); // NOI18N

        panel_functionDependency.setName("panel_functionDependency"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout panel_functionDependencyLayout = new javax.swing.GroupLayout(panel_functionDependency);
        panel_functionDependency.setLayout(panel_functionDependencyLayout);
        panel_functionDependencyLayout.setHorizontalGroup(
            panel_functionDependencyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_functionDependencyLayout.createSequentialGroup()
                .addContainerGap(547, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        panel_functionDependencyLayout.setVerticalGroup(
            panel_functionDependencyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_functionDependencyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(135, Short.MAX_VALUE))
        );

        tabbedPane_functions.addTab(resourceMap.getString("panel_functionDependency.TabConstraints.tabTitle"), panel_functionDependency); // NOI18N

        panel_functionFillType.setName("panel_functionFillType"); // NOI18N

        tabbedPane_subFunctionFillType.setName("tabbedPane_subFunctionFillType"); // NOI18N

        panel_externalFile.setName("panel_externalFile"); // NOI18N

        button_externalFile_linkFile.setAction(actionMap.get("fillTypeExternalFileSelectFile")); // NOI18N
        button_externalFile_linkFile.setText(resourceMap.getString("button_externalFile_linkFile.text")); // NOI18N
        button_externalFile_linkFile.setToolTipText(resourceMap.getString("button_externalFile_linkFile.toolTipText")); // NOI18N
        button_externalFile_linkFile.setName("button_externalFile_linkFile"); // NOI18N

        formattedTextField_externalFile_path.setEditable(false);
        formattedTextField_externalFile_path.setText(resourceMap.getString("formattedTextField_externalFile_path.text")); // NOI18N
        formattedTextField_externalFile_path.setToolTipText(resourceMap.getString("formattedTextField_externalFile_path.toolTipText")); // NOI18N
        formattedTextField_externalFile_path.setName("formattedTextField_externalFile_path"); // NOI18N

        label_externalFile_path.setText(resourceMap.getString("label_externalFile_path.text")); // NOI18N
        label_externalFile_path.setName("label_externalFile_path"); // NOI18N

        label_externalFile_desc.setFont(resourceMap.getFont("label_externalFile_desc.font")); // NOI18N
        label_externalFile_desc.setForeground(resourceMap.getColor("label_externalFile_desc.foreground")); // NOI18N
        label_externalFile_desc.setText(resourceMap.getString("label_externalFile_desc.text")); // NOI18N
        label_externalFile_desc.setName("label_externalFile_desc"); // NOI18N

        button_externalFile_reset.setAction(actionMap.get("fillTypeExternalFileReset")); // NOI18N
        button_externalFile_reset.setText(resourceMap.getString("button_externalFile_reset.text")); // NOI18N
        button_externalFile_reset.setName("button_externalFile_reset"); // NOI18N

        javax.swing.GroupLayout panel_externalFileLayout = new javax.swing.GroupLayout(panel_externalFile);
        panel_externalFile.setLayout(panel_externalFileLayout);
        panel_externalFileLayout.setHorizontalGroup(
            panel_externalFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_externalFileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_externalFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_externalFileLayout.createSequentialGroup()
                        .addGroup(panel_externalFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_externalFile_path)
                            .addComponent(button_externalFile_linkFile))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_externalFile_reset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE)
                        .addComponent(label_externalFile_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(formattedTextField_externalFile_path, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panel_externalFileLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button_externalFile_linkFile, button_externalFile_reset});

        panel_externalFileLayout.setVerticalGroup(
            panel_externalFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_externalFileLayout.createSequentialGroup()
                .addGroup(panel_externalFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_externalFileLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(label_externalFile_path)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(formattedTextField_externalFile_path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_externalFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button_externalFile_linkFile)
                            .addComponent(button_externalFile_reset)))
                    .addGroup(panel_externalFileLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label_externalFile_desc)))
                .addContainerGap(124, Short.MAX_VALUE))
        );

        tabbedPane_subFunctionFillType.addTab(resourceMap.getString("panel_externalFile.TabConstraints.tabTitle"), panel_externalFile); // NOI18N

        panel_predefinedGenerator.setName("panel_predefinedGenerator"); // NOI18N

        label_predefinedGenerator_desc.setFont(resourceMap.getFont("label_predefinedGenerator_desc.font")); // NOI18N
        label_predefinedGenerator_desc.setForeground(resourceMap.getColor("label_predefinedGenerator_desc.foreground")); // NOI18N
        label_predefinedGenerator_desc.setText(resourceMap.getString("label_predefinedGenerator_desc.text")); // NOI18N
        label_predefinedGenerator_desc.setName("label_predefinedGenerator_desc"); // NOI18N

        subPanel_predefinedGenerators.setName("subPanel_predefinedGenerators"); // NOI18N

        comboBox_predefinedGenerator.setToolTipText(resourceMap.getString("comboBox_predefinedGenerator.toolTipText")); // NOI18N
        comboBox_predefinedGenerator.setName("comboBox_predefinedGenerator"); // NOI18N

        label_predefinedGenerator_availableGenerators.setText(resourceMap.getString("label_predefinedGenerator_availableGenerators.text")); // NOI18N
        label_predefinedGenerator_availableGenerators.setName("label_predefinedGenerator_availableGenerators"); // NOI18N

        panel_predefinedGenerator_type.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("panel_predefinedGenerator_type.border.title"), javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP)); // NOI18N
        panel_predefinedGenerator_type.setName("panel_predefinedGenerator_type"); // NOI18N

        radioButton_predefinedGenerator_us.setText(resourceMap.getString("radioButton_predefinedGenerator_us.text")); // NOI18N
        radioButton_predefinedGenerator_us.setName("radioButton_predefinedGenerator_us"); // NOI18N

        radioButton_predefinedGenerator_de.setText(resourceMap.getString("radioButton_predefinedGenerator_de.text")); // NOI18N
        radioButton_predefinedGenerator_de.setName("radioButton_predefinedGenerator_de"); // NOI18N

        javax.swing.GroupLayout panel_predefinedGenerator_typeLayout = new javax.swing.GroupLayout(panel_predefinedGenerator_type);
        panel_predefinedGenerator_type.setLayout(panel_predefinedGenerator_typeLayout);
        panel_predefinedGenerator_typeLayout.setHorizontalGroup(
            panel_predefinedGenerator_typeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_predefinedGenerator_typeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_predefinedGenerator_typeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioButton_predefinedGenerator_us)
                    .addComponent(radioButton_predefinedGenerator_de))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        panel_predefinedGenerator_typeLayout.setVerticalGroup(
            panel_predefinedGenerator_typeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_predefinedGenerator_typeLayout.createSequentialGroup()
                .addComponent(radioButton_predefinedGenerator_us)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioButton_predefinedGenerator_de)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        panel_predefinedGenerator_gender.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("panel_predefinedGenerator_gender.border.title"), javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP)); // NOI18N
        panel_predefinedGenerator_gender.setMinimumSize(new java.awt.Dimension(0, 0));
        panel_predefinedGenerator_gender.setName("panel_predefinedGenerator_gender"); // NOI18N
        panel_predefinedGenerator_gender.setPreferredSize(new java.awt.Dimension(94, 80));

        radioButton_gender_female.setText(resourceMap.getString("radioButton_gender_female.text")); // NOI18N
        radioButton_gender_female.setName("radioButton_gender_female"); // NOI18N

        radioButton_gender_male.setText(resourceMap.getString("radioButton_gender_male.text")); // NOI18N
        radioButton_gender_male.setName("radioButton_gender_male"); // NOI18N

        javax.swing.GroupLayout panel_predefinedGenerator_genderLayout = new javax.swing.GroupLayout(panel_predefinedGenerator_gender);
        panel_predefinedGenerator_gender.setLayout(panel_predefinedGenerator_genderLayout);
        panel_predefinedGenerator_genderLayout.setHorizontalGroup(
            panel_predefinedGenerator_genderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_predefinedGenerator_genderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_predefinedGenerator_genderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioButton_gender_female)
                    .addComponent(radioButton_gender_male))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        panel_predefinedGenerator_genderLayout.setVerticalGroup(
            panel_predefinedGenerator_genderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_predefinedGenerator_genderLayout.createSequentialGroup()
                .addComponent(radioButton_gender_female)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioButton_gender_male)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        button_generator_ok.setAction(actionMap.get("fillTypeGeneratorOK")); // NOI18N
        button_generator_ok.setText(resourceMap.getString("button_generator_ok.text")); // NOI18N
        button_generator_ok.setName("button_generator_ok"); // NOI18N

        panel_predefinedGenerator_date.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("panel_predefinedGenerator_date.border.title"), javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP)); // NOI18N
        panel_predefinedGenerator_date.setName("panel_predefinedGenerator_date"); // NOI18N

        radioButton_date_year.setText(resourceMap.getString("radioButton_date_year.text")); // NOI18N
        radioButton_date_year.setName("radioButton_date_year"); // NOI18N

        radioButton_date_month.setText(resourceMap.getString("radioButton_date_month.text")); // NOI18N
        radioButton_date_month.setName("radioButton_date_month"); // NOI18N

        radioButton_date_day.setText(resourceMap.getString("radioButton_date_day.text")); // NOI18N
        radioButton_date_day.setName("radioButton_date_day"); // NOI18N

        javax.swing.GroupLayout panel_predefinedGenerator_dateLayout = new javax.swing.GroupLayout(panel_predefinedGenerator_date);
        panel_predefinedGenerator_date.setLayout(panel_predefinedGenerator_dateLayout);
        panel_predefinedGenerator_dateLayout.setHorizontalGroup(
            panel_predefinedGenerator_dateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_predefinedGenerator_dateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_predefinedGenerator_dateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioButton_date_year)
                    .addComponent(radioButton_date_month)
                    .addComponent(radioButton_date_day))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        panel_predefinedGenerator_dateLayout.setVerticalGroup(
            panel_predefinedGenerator_dateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_predefinedGenerator_dateLayout.createSequentialGroup()
                .addComponent(radioButton_date_year)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioButton_date_month)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(radioButton_date_day))
        );

        button_generator_reset.setAction(actionMap.get("fillTypeGeneratorReset")); // NOI18N
        button_generator_reset.setText(resourceMap.getString("button_generator_reset.text")); // NOI18N
        button_generator_reset.setName("button_generator_reset"); // NOI18N

        javax.swing.GroupLayout subPanel_predefinedGeneratorsLayout = new javax.swing.GroupLayout(subPanel_predefinedGenerators);
        subPanel_predefinedGenerators.setLayout(subPanel_predefinedGeneratorsLayout);
        subPanel_predefinedGeneratorsLayout.setHorizontalGroup(
            subPanel_predefinedGeneratorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subPanel_predefinedGeneratorsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(subPanel_predefinedGeneratorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_predefinedGenerator_availableGenerators)
                    .addComponent(comboBox_predefinedGenerator, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(subPanel_predefinedGeneratorsLayout.createSequentialGroup()
                        .addComponent(panel_predefinedGenerator_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panel_predefinedGenerator_gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(panel_predefinedGenerator_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(subPanel_predefinedGeneratorsLayout.createSequentialGroup()
                        .addComponent(button_generator_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_generator_reset)))
                .addContainerGap())
        );

        subPanel_predefinedGeneratorsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {panel_predefinedGenerator_gender, panel_predefinedGenerator_type});

        subPanel_predefinedGeneratorsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button_generator_ok, button_generator_reset});

        subPanel_predefinedGeneratorsLayout.setVerticalGroup(
            subPanel_predefinedGeneratorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subPanel_predefinedGeneratorsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_predefinedGenerator_availableGenerators)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBox_predefinedGenerator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(subPanel_predefinedGeneratorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panel_predefinedGenerator_date, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_predefinedGenerator_gender, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(panel_predefinedGenerator_type, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(subPanel_predefinedGeneratorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_generator_ok)
                    .addComponent(button_generator_reset))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_predefinedGeneratorLayout = new javax.swing.GroupLayout(panel_predefinedGenerator);
        panel_predefinedGenerator.setLayout(panel_predefinedGeneratorLayout);
        panel_predefinedGeneratorLayout.setHorizontalGroup(
            panel_predefinedGeneratorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_predefinedGeneratorLayout.createSequentialGroup()
                .addComponent(subPanel_predefinedGenerators, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addComponent(label_predefinedGenerator_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_predefinedGeneratorLayout.setVerticalGroup(
            panel_predefinedGeneratorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_predefinedGeneratorLayout.createSequentialGroup()
                .addGroup(panel_predefinedGeneratorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_predefinedGeneratorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label_predefinedGenerator_desc))
                    .addComponent(subPanel_predefinedGenerators, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane_subFunctionFillType.addTab(resourceMap.getString("panel_predefinedGenerator.TabConstraints.tabTitle"), panel_predefinedGenerator); // NOI18N

        panel_Editor.setName("panel_Editor"); // NOI18N

        label_editor_desc.setFont(resourceMap.getFont("label_editor_desc.font")); // NOI18N
        label_editor_desc.setForeground(resourceMap.getColor("label_editor_desc.foreground")); // NOI18N
        label_editor_desc.setText(resourceMap.getString("label_editor_desc.text")); // NOI18N
        label_editor_desc.setName("label_editor_desc"); // NOI18N

        subPanel_editor.setName("subPanel_editor"); // NOI18N

        scrollPane_editor.setName("scrollPane_editor"); // NOI18N

        textArea_editor.setColumns(20);
        textArea_editor.setRows(5);
        textArea_editor.setTabSize(4);
        textArea_editor.setToolTipText(resourceMap.getString("textArea_editor.toolTipText")); // NOI18N
        textArea_editor.setName("textArea_editor"); // NOI18N
        scrollPane_editor.setViewportView(textArea_editor);

        button_editor_save.setAction(actionMap.get("fillTypeEditorSave")); // NOI18N
        button_editor_save.setText(resourceMap.getString("button_editor_save.text")); // NOI18N
        button_editor_save.setToolTipText(resourceMap.getString("button_editor_save.toolTipText")); // NOI18N
        button_editor_save.setName("button_editor_save"); // NOI18N

        button_editor_ok.setAction(actionMap.get("fillTypeEditorOK")); // NOI18N
        button_editor_ok.setText(resourceMap.getString("button_editor_ok.text")); // NOI18N
        button_editor_ok.setToolTipText(resourceMap.getString("button_editor_ok.toolTipText")); // NOI18N
        button_editor_ok.setName("button_editor_ok"); // NOI18N

        checkBox_editor_incrementValue.setAction(actionMap.get("fillTypeEditorIncrement")); // NOI18N
        checkBox_editor_incrementValue.setText(resourceMap.getString("checkBox_editor_incrementValue.text")); // NOI18N
        checkBox_editor_incrementValue.setName("checkBox_editor_incrementValue"); // NOI18N

        button_editor_reset.setAction(actionMap.get("fillTypeEditorReset")); // NOI18N
        button_editor_reset.setText(resourceMap.getString("button_editor_reset.text")); // NOI18N
        button_editor_reset.setName("button_editor_reset"); // NOI18N

        button_editor_load.setAction(actionMap.get("fillTypeEditorLoad")); // NOI18N
        button_editor_load.setText(resourceMap.getString("button_editor_load.text")); // NOI18N
        button_editor_load.setName("button_editor_load"); // NOI18N

        javax.swing.GroupLayout subPanel_editorLayout = new javax.swing.GroupLayout(subPanel_editor);
        subPanel_editor.setLayout(subPanel_editorLayout);
        subPanel_editorLayout.setHorizontalGroup(
            subPanel_editorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subPanel_editorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(subPanel_editorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(subPanel_editorLayout.createSequentialGroup()
                        .addComponent(scrollPane_editor, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(subPanel_editorLayout.createSequentialGroup()
                        .addComponent(button_editor_ok)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(subPanel_editorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(subPanel_editorLayout.createSequentialGroup()
                                .addComponent(button_editor_load)
                                .addContainerGap())
                            .addGroup(subPanel_editorLayout.createSequentialGroup()
                                .addComponent(button_editor_save)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                                .addComponent(button_editor_reset)
                                .addGap(18, 18, 18)
                                .addComponent(checkBox_editor_incrementValue)
                                .addGap(10, 10, 10))))))
        );

        subPanel_editorLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button_editor_load, button_editor_ok, button_editor_save});

        subPanel_editorLayout.setVerticalGroup(
            subPanel_editorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subPanel_editorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane_editor, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(subPanel_editorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_editor_ok)
                    .addComponent(button_editor_save)
                    .addComponent(checkBox_editor_incrementValue)
                    .addComponent(button_editor_reset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_editor_load)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_EditorLayout = new javax.swing.GroupLayout(panel_Editor);
        panel_Editor.setLayout(panel_EditorLayout);
        panel_EditorLayout.setHorizontalGroup(
            panel_EditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_EditorLayout.createSequentialGroup()
                .addComponent(subPanel_editor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(label_editor_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_EditorLayout.setVerticalGroup(
            panel_EditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_EditorLayout.createSequentialGroup()
                .addGroup(panel_EditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_EditorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label_editor_desc))
                    .addComponent(subPanel_editor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane_subFunctionFillType.addTab(resourceMap.getString("panel_Editor.TabConstraints.tabTitle"), panel_Editor); // NOI18N

        panel_interval.setName("panel_interval"); // NOI18N

        panel_interval_notation.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("panel_interval_notation.border.title"), javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP)); // NOI18N
        panel_interval_notation.setName("panel_interval_notation"); // NOI18N

        label_interval_notation4.setText(resourceMap.getString("label_interval_notation4.text")); // NOI18N
        label_interval_notation4.setName("label_interval_notation4"); // NOI18N

        label_interval_notation3.setText(resourceMap.getString("label_interval_notation3.text")); // NOI18N
        label_interval_notation3.setName("label_interval_notation3"); // NOI18N

        label_interval_notation2.setText(resourceMap.getString("label_interval_notation2.text")); // NOI18N
        label_interval_notation2.setName("label_interval_notation2"); // NOI18N

        label_interval_notation1.setText(resourceMap.getString("label_interval_notation1.text")); // NOI18N
        label_interval_notation1.setName("label_interval_notation1"); // NOI18N

        javax.swing.GroupLayout panel_interval_notationLayout = new javax.swing.GroupLayout(panel_interval_notation);
        panel_interval_notation.setLayout(panel_interval_notationLayout);
        panel_interval_notationLayout.setHorizontalGroup(
            panel_interval_notationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_interval_notationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_interval_notationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_interval_notation1)
                    .addComponent(label_interval_notation2)
                    .addComponent(label_interval_notation3)
                    .addComponent(label_interval_notation4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_interval_notationLayout.setVerticalGroup(
            panel_interval_notationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_interval_notationLayout.createSequentialGroup()
                .addComponent(label_interval_notation1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_interval_notation2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_interval_notation3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_interval_notation4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        formattedTextField_interval.setText(resourceMap.getString("formattedTextField_interval.text")); // NOI18N
        formattedTextField_interval.setToolTipText(resourceMap.getString("formattedTextField_interval.toolTipText")); // NOI18N
        formattedTextField_interval.setName("formattedTextField_interval"); // NOI18N

        label_interval.setText(resourceMap.getString("label_interval.text")); // NOI18N
        label_interval.setName("label_interval"); // NOI18N

        button_interval_ok.setAction(actionMap.get("fillTypeIntervalAccept")); // NOI18N
        button_interval_ok.setText(resourceMap.getString("button_interval_ok.text")); // NOI18N
        button_interval_ok.setToolTipText(resourceMap.getString("button_interval_ok.toolTipText")); // NOI18N
        button_interval_ok.setName("button_interval_ok"); // NOI18N

        button_interval_reset.setAction(actionMap.get("fillTypeIntervalReset")); // NOI18N
        button_interval_reset.setText(resourceMap.getString("button_interval_reset.text")); // NOI18N
        button_interval_reset.setName("button_interval_reset"); // NOI18N

        label_interval_steps.setText(resourceMap.getString("label_interval_steps.text")); // NOI18N
        label_interval_steps.setName("label_interval_steps"); // NOI18N

        formattedTextField_interval_steps.setText(resourceMap.getString("formattedTextField_interval_steps.text")); // NOI18N
        formattedTextField_interval_steps.setName("formattedTextField_interval_steps"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        comboBox_interval.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Integer", "Double" }));
        comboBox_interval.setToolTipText(resourceMap.getString("comboBox_interval.toolTipText")); // NOI18N
        comboBox_interval.setName("comboBox_interval"); // NOI18N

        javax.swing.GroupLayout panel_intervalLayout = new javax.swing.GroupLayout(panel_interval);
        panel_interval.setLayout(panel_intervalLayout);
        panel_intervalLayout.setHorizontalGroup(
            panel_intervalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_intervalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_intervalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_intervalLayout.createSequentialGroup()
                        .addGroup(panel_intervalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_intervalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(label_interval)
                                .addComponent(formattedTextField_interval, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                                .addComponent(label_interval_steps)
                                .addComponent(formattedTextField_interval_steps))
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 196, Short.MAX_VALUE)
                        .addComponent(panel_interval_notation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comboBox_interval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_intervalLayout.createSequentialGroup()
                        .addComponent(button_interval_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_interval_reset)))
                .addContainerGap())
        );

        panel_intervalLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button_interval_ok, button_interval_reset});

        panel_intervalLayout.setVerticalGroup(
            panel_intervalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_intervalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_intervalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_intervalLayout.createSequentialGroup()
                        .addComponent(label_interval)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(formattedTextField_interval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label_interval_steps)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(formattedTextField_interval_steps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2))
                    .addComponent(panel_interval_notation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBox_interval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel_intervalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_interval_ok)
                    .addComponent(button_interval_reset))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        tabbedPane_subFunctionFillType.addTab(resourceMap.getString("panel_interval.TabConstraints.tabTitle"), panel_interval); // NOI18N

        panel_formula.setName("panel_formula"); // NOI18N

        label_formula_desc.setFont(resourceMap.getFont("label_formula_desc.font")); // NOI18N
        label_formula_desc.setForeground(resourceMap.getColor("label_formula_desc.foreground")); // NOI18N
        label_formula_desc.setText(resourceMap.getString("label_formula_desc.text")); // NOI18N
        label_formula_desc.setName("label_formula_desc"); // NOI18N

        javax.swing.GroupLayout panel_formulaLayout = new javax.swing.GroupLayout(panel_formula);
        panel_formula.setLayout(panel_formulaLayout);
        panel_formulaLayout.setHorizontalGroup(
            panel_formulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_formulaLayout.createSequentialGroup()
                .addContainerGap(542, Short.MAX_VALUE)
                .addComponent(label_formula_desc)
                .addContainerGap())
        );
        panel_formulaLayout.setVerticalGroup(
            panel_formulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_formulaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_formula_desc)
                .addContainerGap(191, Short.MAX_VALUE))
        );

        tabbedPane_subFunctionFillType.addTab(resourceMap.getString("panel_formula.TabConstraints.tabTitle"), panel_formula); // NOI18N

        javax.swing.GroupLayout panel_functionFillTypeLayout = new javax.swing.GroupLayout(panel_functionFillType);
        panel_functionFillType.setLayout(panel_functionFillTypeLayout);
        panel_functionFillTypeLayout.setHorizontalGroup(
            panel_functionFillTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane_subFunctionFillType, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
        );
        panel_functionFillTypeLayout.setVerticalGroup(
            panel_functionFillTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane_subFunctionFillType, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );

        tabbedPane_functions.addTab(resourceMap.getString("panel_functionFillType.TabConstraints.tabTitle"), panel_functionFillType); // NOI18N

        panel_functionDistribution.setName("panel_functionDistribution"); // NOI18N

        panel_distribution.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("panel_distribution.border.title"), javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP)); // NOI18N
        panel_distribution.setName("panel_distribution"); // NOI18N

        panel_graph.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("panel_graph.border.title"), javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP)); // NOI18N
        panel_graph.setName("panel_graph"); // NOI18N

        javax.swing.GroupLayout panel_functionDistributionLayout = new javax.swing.GroupLayout(panel_functionDistribution);
        panel_functionDistribution.setLayout(panel_functionDistributionLayout);
        panel_functionDistributionLayout.setHorizontalGroup(
            panel_functionDistributionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_functionDistributionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_distribution, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panel_graph, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_functionDistributionLayout.setVerticalGroup(
            panel_functionDistributionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_functionDistributionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_functionDistributionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel_graph, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(panel_distribution, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane_functions.addTab(resourceMap.getString("panel_functionDistribution.TabConstraints.tabTitle"), panel_functionDistribution); // NOI18N

        panel_functionUnit.setName("panel_functionUnit"); // NOI18N

        panel_functionUnit_availableUnits.setName("panel_functionUnit_availableUnits"); // NOI18N

        scrollPane_functionUnit.setName("scrollPane_functionUnit"); // NOI18N

        tree_functionUnit.setModel(this.treeModel_units);
        tree_functionUnit.setName("tree_functionUnit"); // NOI18N
        scrollPane_functionUnit.setViewportView(tree_functionUnit);

        label_availableUnits.setText(resourceMap.getString("label_availableUnits.text")); // NOI18N
        label_availableUnits.setName("label_availableUnits"); // NOI18N

        javax.swing.GroupLayout panel_functionUnit_availableUnitsLayout = new javax.swing.GroupLayout(panel_functionUnit_availableUnits);
        panel_functionUnit_availableUnits.setLayout(panel_functionUnit_availableUnitsLayout);
        panel_functionUnit_availableUnitsLayout.setHorizontalGroup(
            panel_functionUnit_availableUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_functionUnit_availableUnitsLayout.createSequentialGroup()
                .addComponent(label_availableUnits)
                .addContainerGap())
            .addComponent(scrollPane_functionUnit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
        );
        panel_functionUnit_availableUnitsLayout.setVerticalGroup(
            panel_functionUnit_availableUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_functionUnit_availableUnitsLayout.createSequentialGroup()
                .addComponent(label_availableUnits)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane_functionUnit, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
        );

        panel_functionUnit_modify.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("panel_functionUnit_modify.border.title"), javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP)); // NOI18N
        panel_functionUnit_modify.setName("panel_functionUnit_modify"); // NOI18N

        button_addUnit.setAction(actionMap.get("addUnit")); // NOI18N
        button_addUnit.setText(resourceMap.getString("button_addUnit.text")); // NOI18N
        button_addUnit.setName("button_addUnit"); // NOI18N

        button_deleteUnit.setAction(actionMap.get("deleteUnit")); // NOI18N
        button_deleteUnit.setText(resourceMap.getString("button_deleteUnit.text")); // NOI18N
        button_deleteUnit.setName("button_deleteUnit"); // NOI18N

        javax.swing.GroupLayout panel_functionUnit_modifyLayout = new javax.swing.GroupLayout(panel_functionUnit_modify);
        panel_functionUnit_modify.setLayout(panel_functionUnit_modifyLayout);
        panel_functionUnit_modifyLayout.setHorizontalGroup(
            panel_functionUnit_modifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_functionUnit_modifyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_functionUnit_modifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_addUnit)
                    .addComponent(button_deleteUnit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_functionUnit_modifyLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button_addUnit, button_deleteUnit});

        panel_functionUnit_modifyLayout.setVerticalGroup(
            panel_functionUnit_modifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_functionUnit_modifyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button_addUnit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_deleteUnit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_functionUnitLayout = new javax.swing.GroupLayout(panel_functionUnit);
        panel_functionUnit.setLayout(panel_functionUnitLayout);
        panel_functionUnitLayout.setHorizontalGroup(
            panel_functionUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_functionUnitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_functionUnit_availableUnits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
                .addComponent(panel_functionUnit_modify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_functionUnitLayout.setVerticalGroup(
            panel_functionUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_functionUnitLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_functionUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_functionUnit_modify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_functionUnit_availableUnits, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane_functions.addTab(resourceMap.getString("panel_functionUnit.TabConstraints.tabTitle"), panel_functionUnit); // NOI18N

        splitPane_function_facets.setLeftComponent(tabbedPane_functions);

        panel_containsFacets.setMinimumSize(new java.awt.Dimension(0, 0));
        panel_containsFacets.setName("panel_containsFacets"); // NOI18N

        scrollPane_currentFacets.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_currentFacets.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_currentFacets.setName("scrollPane_currentFacets"); // NOI18N

        table_currentFacetts.setModel(this.tableModel_facets);
        table_currentFacetts.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table_currentFacetts.setColumnSelectionAllowed(true);
        table_currentFacetts.setName("table_currentFacetts"); // NOI18N
        table_currentFacetts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table_currentFacetts.setShowHorizontalLines(false);
        table_currentFacetts.setShowVerticalLines(false);
        table_currentFacetts.getTableHeader().setReorderingAllowed(false);
        scrollPane_currentFacets.setViewportView(table_currentFacetts);
        table_currentFacetts.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout panel_containsFacetsLayout = new javax.swing.GroupLayout(panel_containsFacets);
        panel_containsFacets.setLayout(panel_containsFacetsLayout);
        panel_containsFacetsLayout.setHorizontalGroup(
            panel_containsFacetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane_currentFacets, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
        );
        panel_containsFacetsLayout.setVerticalGroup(
            panel_containsFacetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_containsFacetsLayout.createSequentialGroup()
                .addComponent(scrollPane_currentFacets, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );

        splitPane_function_facets.setRightComponent(panel_containsFacets);

        tabbedPane_facetsSelectedArtefact.addTab(resourceMap.getString("splitPane_function_facets.TabConstraints.tabTitle"), splitPane_function_facets); // NOI18N

        splitPane_vertical.setRightComponent(tabbedPane_facetsSelectedArtefact);

        javax.swing.GroupLayout panel_mainLayout = new javax.swing.GroupLayout(panel_main);
        panel_main.setLayout(panel_mainLayout);
        panel_mainLayout.setHorizontalGroup(
            panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
            .addGroup(panel_mainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(splitPane_vertical, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_mainLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(panel_progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_mainLayout.setVerticalGroup(
            panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_mainLayout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(splitPane_vertical, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                .addGap(11, 11, 11)
                .addComponent(panel_progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_main.getAccessibleContext().setAccessibleName(resourceMap.getString("panel_main.AccessibleContext.accessibleName")); // NOI18N

        menuBar.setName("menuBar"); // NOI18N

        menu_file.setAction(actionMap.get("quit")); // NOI18N
        menu_file.setText(resourceMap.getString("menu_file.text")); // NOI18N
        menu_file.setName("menu_file"); // NOI18N

        menuItem_loadModel.setAction(actionMap.get("loadModel")); // NOI18N
        menuItem_loadModel.setIcon(resourceMap.getIcon("menuItem_loadModel.icon")); // NOI18N
        menuItem_loadModel.setText(resourceMap.getString("menuItem_loadModel.text")); // NOI18N
        menuItem_loadModel.setToolTipText(resourceMap.getString("menuItem_loadModel.toolTipText")); // NOI18N
        menuItem_loadModel.setName("menuItem_loadModel"); // NOI18N
        menu_file.add(menuItem_loadModel);

        separator_menuFile2.setName("separator_menuFile2"); // NOI18N
        menu_file.add(separator_menuFile2);

        menuItem_newProject.setAction(actionMap.get("newProject")); // NOI18N
        menuItem_newProject.setIcon(resourceMap.getIcon("menuItem_newProject.icon")); // NOI18N
        menuItem_newProject.setText(resourceMap.getString("menuItem_newProject.text")); // NOI18N
        menuItem_newProject.setToolTipText(resourceMap.getString("menuItem_newProject.toolTipText")); // NOI18N
        menuItem_newProject.setName("menuItem_newProject"); // NOI18N
        menu_file.add(menuItem_newProject);

        menuItem_loadProject.setAction(actionMap.get("loadProject")); // NOI18N
        menuItem_loadProject.setIcon(resourceMap.getIcon("menuItem_loadProject.icon")); // NOI18N
        menuItem_loadProject.setText(resourceMap.getString("menuItem_loadProject.text")); // NOI18N
        menuItem_loadProject.setToolTipText(resourceMap.getString("menuItem_loadProject.toolTipText")); // NOI18N
        menuItem_loadProject.setName("menuItem_loadProject"); // NOI18N
        menu_file.add(menuItem_loadProject);

        menuItem_saveProject.setAction(actionMap.get("saveProject")); // NOI18N
        menuItem_saveProject.setIcon(resourceMap.getIcon("menuItem_saveProject.icon")); // NOI18N
        menuItem_saveProject.setText(resourceMap.getString("menuItem_saveProject.text")); // NOI18N
        menuItem_saveProject.setToolTipText(resourceMap.getString("menuItem_saveProject.toolTipText")); // NOI18N
        menuItem_saveProject.setName("menuItem_saveProject"); // NOI18N
        menu_file.add(menuItem_saveProject);

        separator_menuFile1.setName("separator_menuFile1"); // NOI18N
        menu_file.add(separator_menuFile1);

        menuItem_exit.setAction(actionMap.get("quit")); // NOI18N
        menuItem_exit.setIcon(resourceMap.getIcon("menuItem_exit.icon")); // NOI18N
        menuItem_exit.setText(resourceMap.getString("menuItem_exit.text")); // NOI18N
        menuItem_exit.setToolTipText(resourceMap.getString("menuItem_exit.toolTipText")); // NOI18N
        menuItem_exit.setName("menuItem_exit"); // NOI18N
        menu_file.add(menuItem_exit);

        menuBar.add(menu_file);

        menu_edit.setText(resourceMap.getString("menu_edit.text")); // NOI18N
        menu_edit.setName("menu_edit"); // NOI18N

        menuItem_nullValues.setAction(actionMap.get("openNullValueDialog")); // NOI18N
        menuItem_nullValues.setIcon(resourceMap.getIcon("menuItem_nullValues.icon")); // NOI18N
        menuItem_nullValues.setText(resourceMap.getString("menuItem_nullValues.text")); // NOI18N
        menuItem_nullValues.setToolTipText(resourceMap.getString("menuItem_nullValues.toolTipText")); // NOI18N
        menuItem_nullValues.setName("menuItem_nullValues"); // NOI18N
        menu_edit.add(menuItem_nullValues);

        menuItem_multiplicityValues.setAction(actionMap.get("openMultiplicityValuesDialog")); // NOI18N
        menuItem_multiplicityValues.setIcon(resourceMap.getIcon("menuItem_multiplicityValues.icon")); // NOI18N
        menuItem_multiplicityValues.setText(resourceMap.getString("menuItem_multiplicityValues.text")); // NOI18N
        menuItem_multiplicityValues.setToolTipText(resourceMap.getString("menuItem_multiplicityValues.toolTipText")); // NOI18N
        menuItem_multiplicityValues.setName("menuItem_multiplicityValues"); // NOI18N
        menu_edit.add(menuItem_multiplicityValues);

        separator_menuEdit2.setName("separator_menuEdit2"); // NOI18N
        menu_edit.add(separator_menuEdit2);

        menuItem_addFacet.setAction(actionMap.get("addFacet")); // NOI18N
        menuItem_addFacet.setText(resourceMap.getString("menuItem_addFacet.text")); // NOI18N
        menuItem_addFacet.setToolTipText(resourceMap.getString("menuItem_addFacet.toolTipText")); // NOI18N
        menuItem_addFacet.setName("menuItem_addFacet"); // NOI18N
        menu_edit.add(menuItem_addFacet);

        menuItem_deleteFacet.setAction(actionMap.get("deleteFacet")); // NOI18N
        menuItem_deleteFacet.setText(resourceMap.getString("menuItem_deleteFacet.text")); // NOI18N
        menuItem_deleteFacet.setToolTipText(resourceMap.getString("menuItem_deleteFacet.toolTipText")); // NOI18N
        menuItem_deleteFacet.setName("menuItem_deleteFacet"); // NOI18N
        menu_edit.add(menuItem_deleteFacet);

        separator_menuEdit1.setName("separator_menuEdit1"); // NOI18N
        menu_edit.add(separator_menuEdit1);

        menuItem_createNewArtefact.setAction(actionMap.get("createNewArtefact")); // NOI18N
        menuItem_createNewArtefact.setIcon(resourceMap.getIcon("menuItem_createNewArtefact.icon")); // NOI18N
        menuItem_createNewArtefact.setText(resourceMap.getString("menuItem_createNewArtefact.text")); // NOI18N
        menuItem_createNewArtefact.setToolTipText(resourceMap.getString("menuItem_createNewArtefact.toolTipText")); // NOI18N
        menuItem_createNewArtefact.setName("menuItem_createNewArtefact"); // NOI18N
        menu_edit.add(menuItem_createNewArtefact);

        menuItem_deleteSelectedArtefact.setAction(actionMap.get("deleteArtefact")); // NOI18N
        menuItem_deleteSelectedArtefact.setIcon(resourceMap.getIcon("menuItem_deleteSelectedArtefact.icon")); // NOI18N
        menuItem_deleteSelectedArtefact.setText(resourceMap.getString("menuItem_deleteSelectedArtefact.text")); // NOI18N
        menuItem_deleteSelectedArtefact.setToolTipText(resourceMap.getString("menuItem_deleteSelectedArtefact.toolTipText")); // NOI18N
        menuItem_deleteSelectedArtefact.setName("menuItem_deleteSelectedArtefact"); // NOI18N
        menu_edit.add(menuItem_deleteSelectedArtefact);

        menuBar.add(menu_edit);

        menu_view.setText(resourceMap.getString("menu_view.text")); // NOI18N
        menu_view.setName("menu_view"); // NOI18N

        menuItem_update.setAction(actionMap.get("update")); // NOI18N
        menuItem_update.setIcon(resourceMap.getIcon("menuItem_update.icon")); // NOI18N
        menuItem_update.setText(resourceMap.getString("menuItem_update.text")); // NOI18N
        menuItem_update.setToolTipText(resourceMap.getString("menuItem_update.toolTipText")); // NOI18N
        menuItem_update.setName("menuItem_update"); // NOI18N
        menu_view.add(menuItem_update);

        menuBar.add(menu_view);

        menu_debug.setText(resourceMap.getString("menu_debug.text")); // NOI18N
        menu_debug.setName("menu_debug"); // NOI18N

        menuItem_checkBox_logging.setAction(actionMap.get("logging")); // NOI18N
        menuItem_checkBox_logging.setSelected(ErrorMessage.getInstance().isLoggingEnabled());
        menuItem_checkBox_logging.setText(resourceMap.getString("menuItem_checkBox_logging.text")); // NOI18N
        menuItem_checkBox_logging.setToolTipText(resourceMap.getString("menuItem_checkBox_logging.toolTipText")); // NOI18N
        menuItem_checkBox_logging.setName("menuItem_checkBox_logging"); // NOI18N
        menu_debug.add(menuItem_checkBox_logging);

        menuBar.add(menu_debug);

        menu_run.setText(resourceMap.getString("menu_run.text")); // NOI18N
        menu_run.setName("menu_run"); // NOI18N

        menuItem_generateData.setAction(actionMap.get("generateData")); // NOI18N
        menuItem_generateData.setIcon(resourceMap.getIcon("menuItem_generateData.icon")); // NOI18N
        menuItem_generateData.setText(resourceMap.getString("menuItem_generateData.text")); // NOI18N
        menuItem_generateData.setToolTipText(resourceMap.getString("menuItem_generateData.toolTipText")); // NOI18N
        menuItem_generateData.setName("menuItem_generateData"); // NOI18N
        menu_run.add(menuItem_generateData);

        separator_run.setName("separator_run"); // NOI18N
        menu_run.add(separator_run);

        menuItem_listOfPRNGs.setText(resourceMap.getString("menuItem_listOfPRNGs.text")); // NOI18N
        menuItem_listOfPRNGs.setName("menuItem_listOfPRNGs"); // NOI18N

        checkBox_menuItem_Mersenne.setSelected(true);
        checkBox_menuItem_Mersenne.setText(resourceMap.getString("checkBox_menuItem_Mersenne.text")); // NOI18N
        checkBox_menuItem_Mersenne.setName("checkBox_menuItem_Mersenne"); // NOI18N
        menuItem_listOfPRNGs.add(checkBox_menuItem_Mersenne);

        checkBox_menuItem_AES.setSelected(true);
        checkBox_menuItem_AES.setText(resourceMap.getString("checkBox_menuItem_AES.text")); // NOI18N
        checkBox_menuItem_AES.setName("checkBox_menuItem_AES"); // NOI18N
        menuItem_listOfPRNGs.add(checkBox_menuItem_AES);

        checkBox_menuItem_Cellular.setSelected(true);
        checkBox_menuItem_Cellular.setText(resourceMap.getString("checkBox_menuItem_Cellular.text")); // NOI18N
        checkBox_menuItem_Cellular.setName("checkBox_menuItem_Cellular"); // NOI18N
        menuItem_listOfPRNGs.add(checkBox_menuItem_Cellular);

        checkBox_menuItem_SecureRandom.setSelected(true);
        checkBox_menuItem_SecureRandom.setText(resourceMap.getString("checkBox_menuItem_SecureRandom.text")); // NOI18N
        checkBox_menuItem_SecureRandom.setName("checkBox_menuItem_SecureRandom"); // NOI18N
        menuItem_listOfPRNGs.add(checkBox_menuItem_SecureRandom);

        checkBox_menuItem_XORShift.setSelected(true);
        checkBox_menuItem_XORShift.setText(resourceMap.getString("checkBox_menuItem_XORShift.text")); // NOI18N
        checkBox_menuItem_XORShift.setName("checkBox_menuItem_XORShift"); // NOI18N
        menuItem_listOfPRNGs.add(checkBox_menuItem_XORShift);

        checkBox_menuItem_CMWC4096.setSelected(true);
        checkBox_menuItem_CMWC4096.setText(resourceMap.getString("checkBox_menuItem_CMWC4096.text")); // NOI18N
        checkBox_menuItem_CMWC4096.setName("checkBox_menuItem_CMWC4096"); // NOI18N
        menuItem_listOfPRNGs.add(checkBox_menuItem_CMWC4096);

        checkBox_menuItem_JavaRandom.setSelected(true);
        checkBox_menuItem_JavaRandom.setText(resourceMap.getString("checkBox_menuItem_JavaRandom.text")); // NOI18N
        checkBox_menuItem_JavaRandom.setName("checkBox_menuItem_JavaRandom"); // NOI18N
        menuItem_listOfPRNGs.add(checkBox_menuItem_JavaRandom);

        menu_run.add(menuItem_listOfPRNGs);

        menuBar.add(menu_run);

        menu_help.setText(resourceMap.getString("menu_help.text")); // NOI18N
        menu_help.setName("menu_help"); // NOI18N

        menuItem_help.setAction(actionMap.get("openHelp")); // NOI18N
        menuItem_help.setIcon(resourceMap.getIcon("menuItem_help.icon")); // NOI18N
        menuItem_help.setText(resourceMap.getString("menuItem_help.text")); // NOI18N
        menuItem_help.setToolTipText(resourceMap.getString("menuItem_help.toolTipText")); // NOI18N
        menuItem_help.setName("menuItem_help"); // NOI18N
        menu_help.add(menuItem_help);

        menuItem_about.setAction(actionMap.get("openAboutDialog")); // NOI18N
        menuItem_about.setIcon(resourceMap.getIcon("menuItem_about.icon")); // NOI18N
        menuItem_about.setText(resourceMap.getString("menuItem_about.text")); // NOI18N
        menuItem_about.setToolTipText(resourceMap.getString("menuItem_about.toolTipText")); // NOI18N
        menuItem_about.setName("menuItem_about"); // NOI18N
        menu_help.add(menuItem_about);

        menuBar.add(menu_help);

        setComponent(panel_main);
        setMenuBar(menuBar);
    }// </editor-fold>//GEN-END:initComponents
	
	/**
	 * This method causes to show the <i>About</i> dialog where general
	 * stuff and information about the implementation can be seen.
	 */
	@Action
	public void openAboutDialog()
	{
		this.dataGeneratorActions.openAboutDialog();
	}

	/**
	 * loads the meta model describing the structure of classes and its
	 * attributes (facets).
	 * </p>
	 * The call is passed to {@link DataGeneratorActions#loadModel(URI)}
	 */
	@Action
	public void loadModel()
	{
		this.dataGeneratorActions.loadModel(this.workingDir);
	}

	/**
	 * Method to specify those facets which are allowed to have
	 * null values in generated test data sets.
	 * This <i>must</i> be done by the tester and can't be done by
	 * the application.
	 * </p>
	 * More informations about NULL values are in {@link NullValues}.
	 */
    @Action
    public void openNullValueDialog()
    {
    	this.dataGeneratorActions.openNullValuesDialog();
    }

    /**
     * Facets which can have more than one value are called
     * multiplicity many. To specify those, the tester <i>must</i>
     * mark them by hand and can't be done by the application.
     * </p>
     * More information about multiplicity values are placed at {@link Multiplicity}.
     */
    @Action
    public void openMultiplicityValuesDialog()
    {
    	this.dataGeneratorActions.openMultiplicityValuesDialog();
    }
    
    /**
     * loads the corresponding facets of an selected artefact.
     * The job is accomplished in the background.
     */
    public void loadFacets()
    {
    	if(this.tree_artefacts.getSelectionPath() != null)
    	{
    		TreePath path = this.tree_artefacts.getSelectionPath();
    		
    		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
    		
    		boolean existsArtefactType = ControllerFactory.getController().hasArtefactType(selectedNode.toString());
    		
    		// get latest data
    		if((!selectedNode.isRoot()) && (!existsArtefactType))
        	{
        		this.dataGeneratorActions.loadFacets(path, selectedNode);
        	}
    		
    		// do not update, but reset
    		else
    		{
    			this.setTableData(selectedNode.toString(), null);
    		}
    	}
    }
    
    /**
     * adds a new facet to an existing artefact
     */
    @Action
    public void addFacet()
    {
    	this.dataGeneratorActions.addFacet();
    }
    
    /**
     * deletes a facet of an existing artefact
     */
    @Action
    public void deleteFacet()
    {
    	this.dataGeneratorActions.deleteFacet();
    }
    
    /**
     * with this method the user has the opportunity to create
     * a new artefact which is not listed in the model layer until now.
     */
    @Action
    public void createNewArtefact()
    {
    	this.dataGeneratorActions.createNewArtefact();
    }
    
    /**
     * this method is comparable with {@link #deleteFacet()}, but
     * it deletes a whole artefact out of the model instead of a facet.
     * Has this method been executed, there is now way to restore it.
     * </p>
     * The option to delete an artefact is only possible, if a tree
     * component has the current focus. Otherwise it's ignored.
     * With this approach we make sure, that one has first to select
     * the node in the tree to kick and then explicitly calling
     * the delete function.
     * </p>
     * If the fix root element has been selected to delete, it is thrown
     * an error message to point out the mistake.
     */
    @Action
    public void deleteArtefact()
    {
    	if((this.tree_artefacts.isFocusOwner()) && (this.tree_artefacts.getSelectionPath() != null))
    	{
    		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) this.tree_artefacts.getSelectionPath().getLastPathComponent();
    		
    		boolean isArtefactType = ControllerFactory.getController().hasArtefactType(selectedNode.toString());
    		
    		if(!selectedNode.isRoot())
    		{
    			int n = Message.showOptionDialog(
    					"You really wanna delete the artefact?\n"
    					+"         \""
    					+selectedNode.toString()
    					+"\"\n\n"
    					+"Notice, if you go for it, the settings will be lost!\n", 
    					"Deleting Artefact?", 
    					JOptionPane.OK_CANCEL_OPTION,
    					JOptionPane.WARNING_MESSAGE, 
    					null, 
    					null);
	    		
	    		/* 
	    		 * yes = 0
	    		 * no = 1
	    		 * cancel = 2
	    		 */
	    		
	    		if(n == 0)
	    		{
	    			this.dataGeneratorActions.deleteArtefact(this.treeModel_artefacts,
									    					 selectedNode,
									    					 isArtefactType);
	    		}
    		}
    		
    		// the root is fix and not deletable
    		else
    		{
    			Message.showMessageDialog(
    					"The element you're trying to delete is not deletable!",
    					"Delete Exception",
    					JOptionPane.ERROR_MESSAGE);
    		}
    		
    		this.loadFacets();
    	}
    }
    
    /**
     * it is all about this method. It generated the test data sets,
     * based on the previously defined artefacts, facets, data types,
     * probability distributions and so on.
     */
    @Action
    public void generateData()
    {
    	this.dataGeneratorActions.generateData(this.workingDir);
    }
    
    /**
     * serializes the current project also called session, so
     * it is possible to restore it at a later time and work on
     * it further. 
     */
    @Action
    public void saveProject()
    {
    	this.dataGeneratorActions.saveProject();
    }
    
    /**
     * if a session has been serialized with method {@link #saveProject()},
     * it is possible to deserialize it and work further.
     * </p>
     * The advantage of this is clear: one doesn't have to get to the latest
     * point in defining test data manually, but automatically.
     * The whole process doesn't have to be redone and small changes in the
     * definition parameters can be adjusted. 
     */
    @Action
    public void loadProject()
    {
    	// TODO load project
    	Message.showMessageDialog(
    			"I'm sorry!"
    			+"\n"
    			+"Loading a project is not implemented, yet!"
    			+"\n"
    			+"This feature is postponed to a later version",
    			"TODO",
    			JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * tries to open the help content in your favorite
     * browser window. The help content is stored in the 
     * data structure shipped with this application and
     * links to that invoking this action.
     * </p>
     * There are a few things to check before the help
     * is actually displayed. If something goes wrong or
     * doesn't pass the check we throw an exception using
     * the UI.
     */
    @Action
    public void openHelp()
    {
    	if(Desktop.isDesktopSupported())
    	{
    		Desktop desktop = Desktop.getDesktop();
    		
    		// browsing
    		if(desktop.isSupported(Desktop.Action.BROWSE))
    		{
    			try
				{
					desktop.browse(new File(Help.HELP_INDEX).toURI());
				}
				catch(IOException ioe)
				{
					ErrorMessage.getInstance().printMessage(ioe, "IOException");
				}
    		}
    		else
    		{
    			Message.showMessageDialog(
    					"Your OS doesn't support the browse functionality!"
    					+"\n"
    					+"Please load the file below manually in your favorite brower:"
    					+"\n"
    					+new File(Help.HELP_INDEX).toURI(),
    					"BrowseActionError",
    					JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	else
    	{
    		Message.showMessageDialog(
    				"This function is not supported by your OS!"
    				+"\n"
    				+"Open your favorite browser manually and load"
    				+"\n"
    				+new File(Help.HELP_INDEX).toURI(),
    				"DesktopSupportedError",
    				JOptionPane.ERROR_MESSAGE);
    	}
    }

    @Action
    public void newProject()
    {
    	// TODO newProject
    	Message.showMessageDialog(
    			"I'm sorry!"
    			+"\n"
    			+"Creating a new project is not implemented, yet!"
    			+"\n"
    			+"This feature has been postponed due to other work",
    			"TODO",
    			JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * forces to get the latest update of the model layer data
     * structure. The data structure is build upon observer pattern,
     * and notifies the UI when the data has had changed.
     * Since every application has such a function, we follow that idiom.
     */
    @Action
    public void update()
    {
    	// updates the tree
    	if(ControllerFactory.getController().getArtefacts() != null)
    	{
	    	for(Iterator<Artefact> i=ControllerFactory.getController().iteratorArtefacts(); i.hasNext();)
	    	{
	    		Artefact artefact = i.next();
	    		
	    		this.updateArtefact(artefact.getArtefacts(), artefact.getArtefactType());
	    	}
    	}
    	
    	// update node selection
    	this.loadFacets();
    }
    
    /**
     * sets the logging settings. The default is <i>true</i>.
     * When an exception occurs logging allows to create
     * a so-called <i>log</i> file. If the flag is not set,
     * no log file will be created by exceptions or errors.
     */
    @Action
    public void logging()
    {
    	if(this.menuItem_checkBox_logging.isSelected())
    	{
    		ControllerFactory.getController().setLogging(true);
    	}
    	else
    	{
    		ControllerFactory.getController().setLogging(false);
    	}
    }
    
    @Action
    public void addUnit()
    {
    	// TODO add unit
    	Message.showMessageDialog(
    			"I'm sorry!"
    			+"\n"
    			+"Adding units is postponed to a later version.",
    			"TODO",
    			JOptionPane.INFORMATION_MESSAGE);
    }

    @Action
    public void deleteUnit()
    {
    	// TODO delete unit
    	Message.showMessageDialog(
    			"I'm sorry!"
    			+"\n"
    			+"deleting units is postponed to a later version, too.",
    			"TODO",
    			JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * This method shuts down the application.
     * The exit command is passed to the Application Framework
     * to handle it. The application itself checks, whether 
     * it can shut down or it has to do first some actions, e.g.
     * saving or cleaning up.
     * 
     * @see DataGenerator#exit()
     */
    @Action
    public void quit()
    {
    	DataGenerator.getApplication().exit();
    }
    
    
    /**
     * used by:
     * <font size=6>Quantity</font>
     * </p>
     * Sets the quantity explicit. It is also possible to use tab or
     * press enter. This approach uses the <i>OK</i> button.
     */
    @Action
    public void quantity_OkButton()
    {
    	this.updateQuantity();
    }
    
    /**
     * used by:
     * <font size=6>
     * FillType.External file
     * </font></p>
     * Seeding an attribute (facet) with data by linking to
     * an external file. The file lies outside the system.
     * With this method the tester has the opportunity to
     * browses to the desired file and registers the path.
     * With the given path the system knows where to find the file
     * and how to use it. 
     */
    @Action
    public void fillTypeExternalFileSelectFile()
    {
    	URI dirTmp = this.dataGeneratorActions.fillType_ExternalFile_SelectFile(this.workingDir);
    	 
    	if(dirTmp != null)
    	{
    		// save current dir
    		this.workingDir = URI.create(dirTmp.getPath());
    		
    		// display in UI path
    		this.formattedTextField_externalFile_path.setText(dirTmp.toString());
    		
    		// set into table
    		ExternalFile behaviour = FillBehaviourFactory.createExternalFile();
    		
    		behaviour.setPathToFile(dirTmp);
    		
    		this.tableModel_facets.setValueAt(
    				behaviour,
    				this.getSelectedRow(),
    				this.tableModel_facets.findColumn(this.tableModel_facets.FILL_TYPE));
    	}
    }
    
    /**
     * used by:
     * <font size=6>
     * FillType.Editor
     * </font>
     * </p>
     * Resets the selected fill behaviour.
     */
    @Action
    public void fillTypeExternalFileReset()
    {
    	this.formattedTextField_externalFile_path.setText("");
    	
    	this.tableModel_facets.setValueAt(
    			null,
    			this.getSelectedRow(),
    			this.tableModel_facets.findColumn(this.tableModel_facets.FILL_TYPE));
    }
    
    /**
     * used by:
     * <font size=6>
     * FillType.Editor
     * </font>
     * </p>
     * There may be cases in which no file with appropriate
     * values exits. In that case, one doesn't have to switch
     * to another program to create one, it can be done right
     * here. This is for convenience.
     * </p>
     * This method takes the values or text from a text area
     * component in the UI. The component is not passed as an parameter,
     * instead when this method is invoked, it will get the
     * input itself.
     */
    @Action
    public void fillTypeEditorOK()
    {
    	Scanner scanner = null;
    	
    	// something typed in?
    	if(!this.textArea_editor.getText().isEmpty())
    	{
    		if((this.tableModel_facets != null) && (this.tableModel_facets.data != null))
    		{
	    		scanner = new Scanner(this.textArea_editor.getText());
	    		
	    		MetaAttribute mAttribute = this.tableModel_facets.data.get(this.getSelectedRow());
	    		
	    		Editor editor = null;
	    		
	    		if(mAttribute != null)
	    		{
	    			if(mAttribute.getFillType() != null)
	    			{
	    				// Editor?
	    				if(mAttribute.getFillType().getFillBehaviour() instanceof Editor)
	    				{
	    					editor = (Editor) mAttribute.getFillType().getFillBehaviour();
	    				} 
	    				else
	    				{
	    					editor = FillBehaviourFactory.createEditor();
	    				}
	    			}
	    			else
	    			{
	    				editor = FillBehaviourFactory.createEditor();
	    			}
	    		}
	    		else
	    		{
	    			editor = FillBehaviourFactory.createEditor();
	    		}
	    		
	    		/*
	    		 * we use a value per line, it doesn't matter how it's written
	    		 * 
	    		 * Example:
	    		 *    valueOne
	    		 *    value Two
	    		 *    and another value three
	    		 */
	    		scanner.useDelimiter("\n");
	    		
	    		List<String> values = new ArrayList<String>();
	    		
	    		while(scanner.hasNext())
	    		{
	    			values.add(scanner.next());
	    		}
	    		
	    		editor.setValues(values);
	    		
	    		// store in table
	    		this.tableModel_facets.setValueAt(
	    				editor,
						this.getSelectedRow(),
						this.tableModel_facets.findColumn(this.tableModel_facets.FILL_TYPE));
    		}
    	}
    	else
    	{
    		Message.showMessageDialog(
    				"You didn't type in anything into the Editor!"
    				+"\n\n"
    				+"It's not used as the latest filling type!",
    				"Warning",
    				JOptionPane.WARNING_MESSAGE);
    	}
    }
    
    /**
     * used by:
     * <font size=6>
     * FillType.Editor
     * </font>
     * </p>
     * Resets the used fill behaviour.
     */
    @Action
    public void fillTypeEditorReset()
    {
    	this.textArea_editor.setText("");
    	
    	this.tableModel_facets.setValueAt(
    			null,
    			this.getSelectedRow(),
    			this.tableModel_facets.findColumn(this.tableModel_facets.FILL_TYPE));
    }
    
    /**
     * used by:
     * <font size=6>
     * FillType.Editor
     * </font>
     * </p>
     * With the editor the tester has the opportunity
     * to create a list with values used by the filling
     * algorithm. If the list is complex, for convenience,
     * a method to save the typed in text is appropriate.
     * So the list can be reused and is serialized.
     * </p>
     * Notice, the invocation is passed to {@link #dataGeneratorActions}.
     */
    @Action
    public void fillTypeEditorSave()
    {
    	boolean somethingNull = false;
    	
    	// working dir at least once used
    	if(this.workingDir != null)
    	{
    		// table & data exits
	    	if((this.tableModel_facets != null) && this.tableModel_facets.data != null)
	    	{
		    	MetaAttribute mAttribute = this.tableModel_facets.data.get(this.getSelectedRow());
		    	
		    	if(mAttribute != null)
		    	{
			    	Editor editor = (Editor) mAttribute.getFillType().getFillBehaviour();
			    	
			    	this.dataGeneratorActions.fillType_Editor_Save(this.workingDir, editor.getValues());
		    	}
		    	else
		    	{
		    		somethingNull = true;
		    	}
	    	}
	    	else
	    	{
	    		somethingNull = true;
	    	}
    	}
    	else
    	{
    		somethingNull = true;
    	}
    	
    	// do it manually
    	if(somethingNull)
    	{
    		Scanner scanner = null;
    		
    		if(!this.textArea_editor.getText().isEmpty())
    		{
    			scanner = new Scanner(this.textArea_editor.getText());
    			
    			List<String> values = new ArrayList<String>();
    			
    			scanner.useDelimiter("\n");
    			
    			while(scanner.hasNext())
    			{
    				values.add(scanner.next());
    			}
    			
    			this.dataGeneratorActions.fillType_Editor_Save(this.workingDir, values);
    		}
    	}
    }
    
    /**
     * used by:
     * <font size=6>
     * FillType.Editor
     * </font>
     * </p>
     * This method is thought to load an existing value file into
     * the editor to get modified.
     */
    @Action
    public void fillTypeEditorLoad()
    {
    	List<String> valueFile = null;
    	
    	StringBuffer sb = new StringBuffer();
    	
    	valueFile = this.dataGeneratorActions.fillType_Editor_Load(this.workingDir);
    	
    	if(valueFile != null)
    	{
	    	for(Iterator<String> i=valueFile.iterator(); i.hasNext();)
	    	{
	    		sb.append(i.next() +"\n");
	    	}
	    	
	    	this.textArea_editor.setText(sb.toString());
    	}
    }
    
    /**
     * used by:
     * <font size=6>FillType.Editor</font></p>
     * sometimes it is useful to increment one
	 * value rather than typing in a whole bunch
	 * of different ones.
	 * </br>
	 * <font size=6><b>When to use?</b></font></br>
	 * This is especially true, when file names are generated.
	 * In the file system are usually files containing the same file
	 * name but have different version numbers, as 
	 * an example. This is simulated with the increment option.
	 * </p>
	 * <font size=5><b>Example</b></font></br>
	 * Here is a quick example on how the values in the editor
	 * are used. Suppose you've typed in something like this:</br>
	 * <font color=green>Presentation_</font> </br>
	 * <font color=green>Order</font> </br>
	 * <font color=green>Contract with Certkiller.com </font></br>
	 * </p>
	 * Furthermore, we assume the increment function has been enabled.
	 * The generated values for the 1st increment and the last will be:</br>
	 * <font color=red>Presentation_</font><b>0</b></br>
	 * <font color=red>Order</font><b>0</b></br>
	 * <font color=red>Contract with Certkiller.com </font><b>0</b></br>
	 * ... </br>
	 * <font color=red>Presentation_</font><b>n</b></br>
	 * <font color=red>Order</font><b>n</b></br>
	 * <font color=red>Contract with Certkiller.com </font><b>n</b></br>
	 * </p>
	 * There is still one thing to mention. Only those values will be
	 * incremented which are actually taken for generation.
	 * 
	 * <font size=6><b>When available?</b></font></br>
	 * The option is only available when the editor 
	 * is used as the fill behaviour. If you save the
	 * typed in values as an <i>value file</i> and
	 * then link it with <i>external file</i> the
	 * option will not be available anymore.
     */
    @Action
    public void fillTypeEditorIncrement()
    {
    	if((this.tableModel_facets != null) && (this.tableModel_facets.data != null))
    	{
    		MetaAttribute mAttribute = this.tableModel_facets.data.get(this.getSelectedRow());
    		
    		boolean increment = this.checkBox_editor_incrementValue.isSelected();
    		
    		Editor editor = null;
    		
    		// a fill behaviour is already set
    		if(mAttribute != null)
    		{
    			if(mAttribute.getFillType() != null)
    			{
		    		// editor?
		    		if(mAttribute.getFillType().getFillBehaviour() instanceof Editor)
		    		{
		    			editor = (Editor) mAttribute.getFillType().getFillBehaviour();
		    		}
		    		
		    		// overwrite
		    		else
		    		{
		    			editor = FillBehaviourFactory.createEditor();
		    		}
    			}
    			else
    			{
    				editor = FillBehaviourFactory.createEditor();
    			}
    		}
    		
    		// no behaviour yet
    		else
    		{
    			editor = FillBehaviourFactory.createEditor();
    		}
    		
    		// parse text
			if(!this.textArea_editor.getText().isEmpty())
			{
				Scanner scanner = new Scanner(this.textArea_editor.getText());
				
				scanner.useDelimiter("\n");
				
				List<String> values = new ArrayList<String>();
				
				while(scanner.hasNext())
				{
					values.add(scanner.next());
				}
				
				// set values
				editor.setValues(values);
				
				// increment?
				editor.isValueIncremented(increment);
				
				this.tableModel_facets.setValueAt(
						editor,
						this.getSelectedRow(),
						this.tableModel_facets.findColumn(this.tableModel_facets.FILL_TYPE));
			}
			else
	    	{
	    		Message.showMessageDialog(
	    				"You didn't type in anything into the Editor!"
	    				+"\n\n"
	    				+"It's not used as the latest filling type!",
	    				"Warning",
	    				JOptionPane.WARNING_MESSAGE);
	    	}
    	}
    }
    
    /**
     * used by:
     * <font size=6>
     * fillType.Interval
     * </font></br>
     * Defining an interval is useful, when values of a
     * given attribute have known lower & upper bounds.
     * </p>
     * Example:
     * </p>
     * The thread length of screws has such a lower and upper bound.
     * The minimum length is '1' and the maximum is '14' cm.
     * That leads to the following situation:
     * </br>
     * <ul>
     * 	<li>
     * 		<code>
     * 			1, 2, ..., 14
     * 		</code>
     * 	</li>
     * </ul>
     * As a result of the above restricts, the interval could be described as:
     * <ol>
     * 		<li>]0, 15[</li>
     * 		<li>[1, 15[</li>
     * 		<li>]0, 14]</li>
     * 		<li>[1, 14]</li>
     * </ol>
     * The typed in interval will be parsed and validated.
     * In case the interval passes the validation test it is set
     * into the filling type of the currently selected row.
     * But if the interval fails the validation the typed in
     * text is gonna be erased by the system and the cursor
     * is set into the corresponding text field.
     */
    @Action
    public void fillTypeIntervalAccept()
    {
    	Interval interval = FillBehaviourFactory.createInterval();
    	
    	// TODO stepping, type
    	
    	if(interval.setInterval(this.formattedTextField_interval.getText()))
    	{
    		// table & data exit
    		if((this.tableModel_facets != null) && (this.tableModel_facets.data != null))
    		{
    			this.tableModel_facets.setValueAt(
    					interval,
    					this.getSelectedRow(),
    					this.tableModel_facets.findColumn(this.tableModel_facets.FILL_TYPE));
    		}
    	}
    	else
    	{
    		// reset
    		this.formattedTextField_interval.setText("");
    		
    		// set cursor into text field
    		this.formattedTextField_interval.requestFocusInWindow();
    	}
    }
    
    /**
     * used by:
     * <font size=6>
     * fillType.Generator
     * </font>
     * </p>
     * Resets the used fill behaviour. 
     */
    @Action
    public void fillTypeIntervalReset()
    {
    	this.formattedTextField_interval.setText("");
    	this.formattedTextField_interval_steps.setText("");
    	this.comboBox_interval.setSelectedIndex(0);
    	
    	this.tableModel_facets.setValueAt(
    			null,
    			this.getSelectedRow(),
    			this.tableModel_facets.findColumn(this.tableModel_facets.FILL_TYPE));
    }
    
    /**
     * used by:
     * <font size=6>
     * fillType.Generator
     * </font>
     * </p>
     * Causes an update of the current selected generator.
     * That means the current selection is taken and passed
     * down to the table.
     */
    @Action
    public void fillTypeGeneratorOK()
    {
    	this.getSelectedGenerator();
    }
    
    /**
     * used by:
     * <font size=6>
     * fillType.Generator
     * </font>
     * </p>
     * Resets the used generator.
     */
    @Action
    public void fillTypeGeneratorReset()
    {
    	this.tableModel_facets.setValueAt(
    			null,
    			this.getSelectedRow(),
    			this.tableModel_facets.findColumn(this.tableModel_facets.FILL_TYPE));
    }
    
    /**
     * used by:
     * <font size=6><b>FacetUpdater</b></font></br>
     * To determine how many test data set to generate
     * for a concrete term, it's possible to specify
     * that with this method.
     * </p>
     * This method is used by the background worker.
     * 
     * @param quantity
     * 		test data sets to generate for a concrete term.
     * @param isFieldEnabled
     * 		field is enabled, when called by a concrete term.
     */
    public void setQuantity(int quantity, boolean isFieldEnabled)
    {
    	// reset
    	this.formattedTextField_quantity.setText("");
    	
    	if(quantity > 0)
    	{
    		this.formattedTextField_quantity.setText(String.valueOf(quantity));
    	}
    	
    	this.formattedTextField_quantity.setEditable(isFieldEnabled);
    }
    
    /**
	 * sets the table data at once.
	 * Each attribute in the list has its own row in
	 * the table.
	 * </p>
	 * When null is passed it resets the table data
	 * to its initial state.
	 * 
	 * @param artefactName
	 * 		the name of the artefact loaded currently.
	 * @param data
	 * 		list of attributes. The data resides in the attributes.
	 */
	public void setTableData(String artefactName, List<MetaAttribute> data)
	{
		// set current name
		this.tabbedPane_facetsSelectedArtefact.setTitleAt(0, artefactName);
		
		this.tableModel_facets.data = data;
		
		this.tableModel_facets.fireTableDataChanged();
	}
	
	/**
	 * adds one attribute also called facet to the list of
	 * table data. Each attribute has its own table row.
	 * 
	 * @param mAttribute
	 * 		attribute (facet) to add to table
	 */
	public void addTableData(MetaAttribute mAttribute)
	{
		if(this.tableModel_facets.data == null)
		{
			this.tableModel_facets.data = new ArrayList<MetaAttribute>();
		}
		
		this.tableModel_facets.data.add(mAttribute);
		
		this.tableModel_facets.fireTableDataChanged();
	}
	
    /**
     * returns the progress bar of the view.
     * 
     * @return
     * 		progress bar
     */
    public static JProgressBar getProgressBar()
    {
    	return progressBar;
    }
    
    @Override
    public void updateArtefact(List<MetaClass> artefacts, ArtefactType type)
    {
    	// is there an abstract root in tree model?
    	if(!this.hasVariableRoot(this.root_artefacts, type))
    	{
    		this.root_artefacts.add(new DefaultMutableTreeNode(type.getName()));
    	}
    	
    	// update artefact tree
    	this.dataGeneratorActions.updateArtefact(artefacts, 
												 this.treeModel_artefacts,
												 this.tree_artefacts,
												 type);
    }
    
    @Override
    public void updateUnit(List<String> units, String name)
    {
    	// update unit tree
    	this.dataGeneratorActions.updateUnit(units,
							    			 name,
							    			 this.tree_functionUnit,
							    			 this.treeModel_units);
    }
    
    @Override
    public void updateUnitCustom(List<Custom> units, String name)
    {
    	// update unit tree with customs
    	this.dataGeneratorActions.updateUnitCustom(units,
								    			   name,
								    			   this.tree_functionUnit,
								    			   this.treeModel_units);
    }
    
    @Override
    public void updateGenerator(boolean add, Generator generator)
    {
    	// add
    	if(add)
    	{
    		this.comboBox_predefinedGenerator.addItem(generator);
    	}
    	
    	// remove
    	else
    	{
    		this.comboBox_predefinedGenerator.removeItem(generator);
    	}
    }
    
    @Override
    public void updateProbabilityDistribution(boolean add, ProbabilityDistribution distribution)
    {
    	if(add)
    	{
    		/*
    		 * adds the probability distribution + its parameter panel
    		 * distributions must register to fill type to work properly,
    		 * they need additional informations.
    		 * But this can't be done here! Here we only make them available
    		 * in UI.
    		 */
    		if(distribution.getName().equals("Normal Distribution"))
    		{
    			this.panel_distribution.addProbability(
    					distribution,
    					new NormalDistributionPanel(distribution, this.panel_distribution));
    		}
    		else if(distribution.getName().equals("Uniform Distribution"))
    		{
    			this.panel_distribution.addProbability(
    					distribution,
    					new UniformDistributionPanel(distribution, this.panel_distribution));
    		}
    	}
    	else
    	{
    		this.panel_distribution.removeProbability(distribution);
    	}
    }
    
    /**
     * used by:
     * <font size=6><b>DistributionPanel</b></br>
     * Because distributions are sourced out into a separate
     * panel (which is represented as a class) we've to provide
     * an access point to set it into the table.
     * </p>
     * The only thing passed down is the distribution, all other
     * things like the row and column knows this class.
     * 
     * @param dist
     * 		the latest probability distribution
     * @param updateGraph
     * 		flag, indicating whether to update graph or not
     */
    protected void setProbabilityIntoTable(ProbabilityDistribution dist, boolean updateGraph)
    {
    	this.tableModel_facets.setValueAt(
    			dist,
    			this.getSelectedRow(),
    			this.tableModel_facets.findColumn(this.tableModel_facets.DISTRIBUTION));
    	
    	// draw graph
    	if(updateGraph)
    	{
    		this.drawProbabilityDensityFunction(dist);
    	}
    }
    
    /**
     * used by: <font size=6><b>DistributionPanel</b></font></br>
     * this method gets the currently set probability distribution
     * in case it has already been set. Otherwise <i>null</i>
     * is returned. As mentioned, this method is used by distribution
     * panel, and gets the set distribution or a new one. Using
     * the old one has the positive effect to make changes on the
     * current values not on the initial ones.
     * 
     * @return
     * 		probability distribution or null
     */
    protected ProbabilityDistribution isProbabilityAlreadySet()
    {
    	if(this.tableModel_facets.data != null)
    	{
    		// attribute of current row
    		MetaAttribute mAttribute = this.tableModel_facets.data.get(this.getSelectedRow());
    		
    		return mAttribute.getDistribution();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    /**
     * method checks if there is an abstract root in the tree model.
     * Abstract root are needed to separate different types.
     * Each type has its own responsibility.
     * 
     * @param root
     * 		it's the fix root node. There's no way to modify or delete it.
     * @param type
     * 		defines the artefact type
     * @return
     * 		true, if is already created
     */
    @SuppressWarnings("unchecked")
	private boolean hasVariableRoot(DefaultMutableTreeNode root, ArtefactType type)
    {
    	boolean has = false;
    	
    	for(Enumeration<DefaultMutableTreeNode> i=root.children(); i.hasMoreElements();)
    	{
    		DefaultMutableTreeNode node = i.nextElement();
    		
    		if(node.toString().equals(type.getName()))
    		{
    			has = true;
    		}
    	}
    	
    	return has;
    }
    
    /**
     * When the user types in any number in the text field
     * assigned to quantity, the number is parsed and checked
     * against invalid input. Valid is only any number greater zero.
     */
    private void updateQuantity()
    {
    	int quantity = 0;
    	
    	if(!this.isTextFieldEmpty(this.formattedTextField_quantity))
    	{
    		try
    		{
    			quantity = Integer.parseInt(this.formattedTextField_quantity.getText());
    		}
    		catch(NumberFormatException nfe)
    		{
    			this.formattedTextField_quantity.setText("");
    			this.formattedTextField_quantity.requestFocusInWindow();
    		}
    	}
    	
    	TreePath path = this.tree_artefacts.getSelectionPath();
		
    	if((path != null) && (quantity >= 0))
    	{
    		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
    		
    		this.dataGeneratorActions.updateQuantity(quantity, path, selectedNode);
    	}
    }
    
    /**
     * checks whether a text field is empty or not.
     * If emtpy <i>true</i> is returned, othwise <i>false</i>.
     * 
     * @param textField
     * 		any text field to check
     * @return
     * 		true, if null
     */
    private boolean isTextFieldEmpty(JFormattedTextField textField)
    {
    	if(textField.getText().equals(""))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    /**
     * We wanna make life easy for tester and every time
     * a new column has been selected we update tabbed pane
     * dynamically.
     * </p>
     * An example might clarify the idea. Below is a clipping
     * of the system.
     * </p>
     * 	<img src="dynamicPanelFunction.jpg" alt="dynamicPanelFunction.jpg" width="75%" height="75%"/>
     * </p>
     * Imagine, you wanna modify the content of the selected
     * row in column <i>Unit</i>. Then the panel changes dynamically
     * and displays it's content. But if you change the row but you stay
     * at the same column it won't change at all.
     * </p>
     * In other words, it changes content when the column changes.  
     * 
     * @param column
     * 		selected column.
     * 		If a column has been selected, the system assumes,
     * 		user wants to make user specific settings.
     * 		For that reason, we display the corresponding panel
     * 		in a tabbed pane.
     */
    private void loadDynamicPanel(int column)
    {
    	switch(column)
    	{
    		// name
    		case 0:
//    			this.tabbedPane_functions.setSelectedComponent(this.panel_functionsQuantity);
    			break;
    		
    		// data type
    		case 1:
//    			this.tabbedPane_functions.setSelectedComponent(this.panel_functionsQuantity);
    			break;
    			
    		// allowed?
    		case 2:
//    			this.tabbedPane_functions.setSelectedComponent(this.panel_functionsQuantity);
    			break;
    			
    		// amount (%)
    		case 3:
//    			this.tabbedPane_functions.setSelectedComponent(this.panel_functionsQuantity);
    			break;
    			
    		// multiple?
    		case 4:
//    			this.tabbedPane_functions.setSelectedComponent(this.panel_functionsQuantity);
    			break;
    			
    		// upper bound
    		case 5:
//    			this.tabbedPane_functions.setSelectedComponent(this.panel_functionsQuantity);
    			break;
    			
    		// restricted?
    		case 6:
//    			this.tabbedPane_functions.setSelectedComponent(this.panel_functionsQuantity);
    			break;
    			
    		// exits dependency
    		case 7:
//    			this.tabbedPane_functions.setSelectedComponent(this.panel_functionsQuantity);
    			break;
    			
    		// dependencies
    		case 8:
    			this.tabbedPane_functions.setSelectedComponent(this.panel_functionDependency);
    			break;
    		
    		// fill type
    		case 9:
    			this.tabbedPane_functions.setSelectedComponent(this.panel_functionFillType);
    			break;
    		
    		// dist
    		case 10:
    			this.tabbedPane_functions.setSelectedComponent(this.panel_functionDistribution);
    			break;
    			
    		// preview
    		case 11:
//    			this.tabbedPane_functions.setSelectedComponent(this.panel_functionsQuantity);
    			break;
    		
    		// unit
    		case 12:
    			this.tabbedPane_functions.setSelectedComponent(this.panel_functionUnit);
    			break;
    		
    		// else
    		default:
    			break;
    	}
    }
    
    /**
     * when an unit item has been selected, this
     * method does a transfer to the table and sets
     * it into the unit column in the current row.
     * </p>
     * <font size=6 color="red">
     * There isn't an explicit function to reset the
     * previously selected unit. This is done implicit.
     * When a parent node has been selected, it resets
     * the unit to its default value.
     * </font>
     * </p>
     * Example:
     * </p>
     * Suppose, we've got the following situation.</br>
     * <pre>
     * <code>
     *     +Unit
     *        +Currency
     *           -USDollar
     *           -Euro
     * </code> 
     * </pre>
     * Clicking on a leaf like <i>USDollar</i> will set the value
     * into the table at its assigned destination.
     * When clicking after that to an parent node like <i>Currency</i>
     * or even <i>Unit</i> will undo the previous selection of <i>USDollar</i>.
     */
    private void setSelectedUnitToTable()
    {
    	if((this.tree_functionUnit.getSelectionPath() != null)
    			&&
    	   (this.tree_functionUnit.hasFocus()))
		{
    		TreePath path = this.tree_functionUnit.getSelectionPath();
    		
    		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
    		    		
    		/* 
    		 * set only concrete term into table, e.g.
    		 * +Currency (abstract, will be ignored)
    		 *   -$      (concrete)
    		 *   -€
    		 */
    		if((!selectedNode.isRoot()) && (selectedNode.isLeaf()))
    		{
    			this.tableModel_facets.setValueAt(
    					selectedNode.toString(),
						this.getSelectedRow(),
						this.tableModel_facets.findColumn(this.tableModel_facets.UNIT));
    		}
    		
    		// reset
    		else
    		{
    			this.tableModel_facets.setValueAt(
    					null,
    					this.getSelectedRow(),
    					this.tableModel_facets.findColumn(this.tableModel_facets.UNIT));
    		}
		}
    }
    
    /**
     * gets the current selected row of an attribute.
     * This is important to link values, options, or other
     * functional behaviour with the appropriate attribute.
     * </p>
     * When talking about rows, we mean the row of an attribute
     * in the table component.
     * 
     * @return
     * 		selected row
     */
    private int getSelectedRow()
    {
    	return this.currentlySelectedRow;
    }
    
    /**
     * used by:
     * <font size=6>
     * fillType.predefined generators
     * </font></br>
     * checks the radio buttons which one
     * has been selected and creates the
     * corresponding {@link LanguageType}.
     * 
     * @return
     * 		selected language type
     */
    private LanguageType getSelectedLanguage()
    {
    	LanguageType language = null;
    	
    	if(this.radioButton_predefinedGenerator_us.isSelected())
    	{
    		language = LanguageType.US;
    	}
    	else if(this.radioButton_predefinedGenerator_de.isSelected())
    	{
    		language = LanguageType.DE;
    	}
    	
    	return language;
    }
    
    /**
     * used by:
     * <font size=6>
     * fillType.predefined generators
     * </font></br>
     * method checks which radio button has been
     * selected on gender and creates the corresponding
     * sex type.
     * 
     * @return
     * 		selected gender
     */
    private SexType getSelectedGender()
    {
    	SexType sex = null;
    	
    	if(this.radioButton_gender_female.isSelected())
    	{
    		sex = SexType.FEMALE;
    	}
    	else if(this.radioButton_gender_male.isSelected())
    	{
    		sex = SexType.MALE;
    	}
    	
    	return sex;
    }
    
    /**
     * used by:
     * <font size=6>
     * fillType.predefined generators
     * </font></br>
     * checks each radio button attached to date and
     * finds the one selected. Based on that information
     * the corresponding interval is created and returned.
     * 
     * @return
     * 		Date interval
     */
    private DateInterval getLevelOfDetail()
    {
    	DateInterval date = null;
    	
    	if(this.radioButton_date_year.isSelected())
    	{
    		date = DateInterval.YEAR;
    	}
    	else if(this.radioButton_date_month.isSelected())
    	{
    		date = DateInterval.MONTH;
    	}
    	else if(this.radioButton_date_day.isSelected())
    	{
    		date = DateInterval.DAY;
    	}
    	
    	return date;
    }
    
    /**
     * depending on the selected item in a combo box,
     * button groups are enabled or disabled.
     * This helps to suppress the temptation to click
     * buttons which do not fit into context. 
     * 
     * @param buttonGroup
     * 		a button group to either enable or disable
     * @param enabled
     * 		should this button group be enabled?
     */
    private void isButtonGroupEnabled(ButtonGroup buttonGroup, boolean enabled)
    {
    	for(Enumeration<AbstractButton> i=buttonGroup.getElements(); i.hasMoreElements();)
    	{
    		JRadioButton rb = (JRadioButton) i.nextElement();
    		
    		rb.setEnabled(enabled);
    	}
    }
    
    /**
     * build the needed information upon the selected generator
     * and passes those into the table.
     */
    private void getSelectedGenerator()
    {
    	Generator generator = (Generator) comboBox_predefinedGenerator.getSelectedItem();
		
		Generator clone = null;
		
		try
		{
			// name
			if(generator instanceof Name)
			{
				Name name = (Name) generator;
				
				isButtonGroupEnabled(buttonGroup_predefinedGenerator_gender, true);
				isButtonGroupEnabled(buttonGroup_predefinedGenerator_date, false);
				
				Name cloned = (Name) name.clone();
				
				cloned.setLanguage(getSelectedLanguage());
				cloned.setSex(getSelectedGender());
				
				clone = cloned;
			}
			
			// prename
			else if(generator instanceof FirstName)
			{
				FirstName fistName = (FirstName) generator;
				
				isButtonGroupEnabled(buttonGroup_predefinedGenerator_gender, true);
				isButtonGroupEnabled(buttonGroup_predefinedGenerator_date, false);
				
				FirstName cloned = (FirstName) fistName.clone();
				
				cloned.setLanguage(getSelectedLanguage());
				cloned.setSex(getSelectedGender());
				
				clone = cloned;
			}
			
			// surname
			else if(generator instanceof LastName)
			{
				LastName lastName = (LastName) generator;
				
				isButtonGroupEnabled(buttonGroup_predefinedGenerator_gender, false);
				isButtonGroupEnabled(buttonGroup_predefinedGenerator_date, false);
				
				LastName cloned = (LastName) lastName.clone();
				
				cloned.setLanguage(getSelectedLanguage());
				
				clone = cloned;
			}
			
			// sex
			else if(generator instanceof Sex)
			{
				Sex sex = (Sex) generator;
				
				isButtonGroupEnabled(buttonGroup_predefinedGenerator_gender, true);
				isButtonGroupEnabled(buttonGroup_predefinedGenerator_date, false);
				
				Sex cloned = (Sex) sex.clone();
				
				cloned.setLanguage(getSelectedLanguage());
				cloned.setGender(getSelectedGender());
				
				clone = cloned;
			}
			
			// address
			else if(generator instanceof Address)
			{
				Address address = (Address) generator;
				
				isButtonGroupEnabled(buttonGroup_predefinedGenerator_gender, false);
				isButtonGroupEnabled(buttonGroup_predefinedGenerator_date, false);
				
				Address cloned = (Address) address.clone();
				
				cloned.setLanguage(getSelectedLanguage());
				
				clone = cloned;
			}
			
			// date
			else if(generator instanceof Dates)
			{
				Dates date = (Dates) generator;
				
				isButtonGroupEnabled(buttonGroup_predefinedGenerator_gender, false);
				isButtonGroupEnabled(buttonGroup_predefinedGenerator_date, true);
				
				Dates cloned = (Dates) date.clone();
				
				cloned.setLanguage(getSelectedLanguage());
				cloned.setLevelOfDetail(getLevelOfDetail());
				
				clone = cloned;
			}
			
			// country
			else if(generator instanceof Country)
			{
				Country country = (Country) generator;
				
				isButtonGroupEnabled(buttonGroup_predefinedGenerator_gender, false);
				isButtonGroupEnabled(buttonGroup_predefinedGenerator_date, false);
				
				Country cloned = (Country) country.clone();
				
				cloned.setLanguage(getSelectedLanguage());
				
				clone = cloned;
			}
			
			// email
			else if(generator instanceof Email)
			{
				Email email = (Email) generator;
				
				isButtonGroupEnabled(buttonGroup_predefinedGenerator_gender, false);
				isButtonGroupEnabled(buttonGroup_predefinedGenerator_date, false);
				
				Email cloned = (Email) email.clone();
				
				cloned.setLanguage(getSelectedLanguage());
				
				clone = cloned;
			}
		}
		catch(CloneNotSupportedException cnse)
		{
			ErrorMessage.getInstance().printMessage(cnse, "CloneNotSupportedException", Level.SEVERE);
		}
		
		// set clone into table
		tableModel_facets.setValueAt(
				clone,
				getSelectedRow(),
				tableModel_facets.findColumn(tableModel_facets.FILL_TYPE));
    }
    
    /**
     * this method passes the call on to the controller layer
     * where a background task is created and the probability
     * density function is generated.
     * 
     * @param dist
     * 		latest changes in probability distribution
     */
    protected void drawProbabilityDensityFunction(ProbabilityDistribution dist)
    {
    	ControllerFactory.getControllerBackgroundTask().generateProabilityDensityFunction(dist, this.panel_graph);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup_predefinedGenerator_date;
    private javax.swing.ButtonGroup buttonGroup_predefinedGenerator_gender;
    private javax.swing.ButtonGroup buttonGroup_predefinedGenerator_languageType;
    private javax.swing.ButtonGroup buttonGroup_prngs;
    private javax.swing.JButton button_addArtefact;
    private javax.swing.JButton button_addFacet;
    private javax.swing.JButton button_addUnit;
    private javax.swing.JButton button_delArtefact;
    private javax.swing.JButton button_delFacet;
    private javax.swing.JButton button_deleteUnit;
    private javax.swing.JButton button_editor_load;
    private javax.swing.JButton button_editor_ok;
    private javax.swing.JButton button_editor_reset;
    private javax.swing.JButton button_editor_save;
    private javax.swing.JButton button_externalFile_linkFile;
    private javax.swing.JButton button_externalFile_reset;
    private javax.swing.JButton button_generateData;
    private javax.swing.JButton button_generator_ok;
    private javax.swing.JButton button_generator_reset;
    private javax.swing.JButton button_interval_ok;
    private javax.swing.JButton button_interval_reset;
    private javax.swing.JButton button_loadEcoreModel;
    private javax.swing.JButton button_loadProject;
    private javax.swing.JButton button_multiplicityValues;
    private javax.swing.JButton button_nullValues;
    private javax.swing.JButton button_quantity;
    private javax.swing.JButton button_saveProject;
    private javax.swing.JCheckBox checkBox_editor_incrementValue;
    private javax.swing.JCheckBoxMenuItem checkBox_menuItem_AES;
    private javax.swing.JCheckBoxMenuItem checkBox_menuItem_CMWC4096;
    private javax.swing.JCheckBoxMenuItem checkBox_menuItem_Cellular;
    private javax.swing.JCheckBoxMenuItem checkBox_menuItem_JavaRandom;
    private javax.swing.JCheckBoxMenuItem checkBox_menuItem_Mersenne;
    private javax.swing.JCheckBoxMenuItem checkBox_menuItem_SecureRandom;
    private javax.swing.JCheckBoxMenuItem checkBox_menuItem_XORShift;
    private javax.swing.JComboBox comboBox_interval;
    private javax.swing.JComboBox comboBox_predefinedGenerator;
    private javax.swing.JFormattedTextField formattedTextField_externalFile_path;
    private javax.swing.JFormattedTextField formattedTextField_interval;
    private javax.swing.JFormattedTextField formattedTextField_interval_steps;
    private javax.swing.JFormattedTextField formattedTextField_quantity;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel label_availableUnits;
    private javax.swing.JLabel label_editor_desc;
    private javax.swing.JLabel label_externalFile_desc;
    private javax.swing.JLabel label_externalFile_path;
    private javax.swing.JLabel label_formula_desc;
    private javax.swing.JLabel label_interval;
    private javax.swing.JLabel label_interval_notation1;
    private javax.swing.JLabel label_interval_notation2;
    private javax.swing.JLabel label_interval_notation3;
    private javax.swing.JLabel label_interval_notation4;
    private javax.swing.JLabel label_interval_steps;
    private javax.swing.JLabel label_modification_desc;
    private javax.swing.JLabel label_predefinedGenerator_availableGenerators;
    private javax.swing.JLabel label_predefinedGenerator_desc;
    private javax.swing.JLabel label_quantity_desc;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuItem_about;
    private javax.swing.JMenuItem menuItem_addFacet;
    private javax.swing.JCheckBoxMenuItem menuItem_checkBox_logging;
    private javax.swing.JMenuItem menuItem_createNewArtefact;
    private javax.swing.JMenuItem menuItem_deleteFacet;
    private javax.swing.JMenuItem menuItem_deleteSelectedArtefact;
    private javax.swing.JMenuItem menuItem_exit;
    private javax.swing.JMenuItem menuItem_generateData;
    private javax.swing.JMenuItem menuItem_help;
    private javax.swing.JMenu menuItem_listOfPRNGs;
    private javax.swing.JMenuItem menuItem_loadModel;
    private javax.swing.JMenuItem menuItem_loadProject;
    private javax.swing.JMenuItem menuItem_multiplicityValues;
    private javax.swing.JMenuItem menuItem_newProject;
    private javax.swing.JMenuItem menuItem_nullValues;
    private javax.swing.JMenuItem menuItem_saveProject;
    private javax.swing.JMenuItem menuItem_update;
    private javax.swing.JMenu menu_debug;
    private javax.swing.JMenu menu_edit;
    private javax.swing.JMenu menu_file;
    private javax.swing.JMenu menu_help;
    private javax.swing.JMenu menu_run;
    private javax.swing.JMenu menu_view;
    private javax.swing.JPanel panel_Editor;
    private javax.swing.JPanel panel_artefacts;
    private javax.swing.JPanel panel_containsFacets;
    private de.uni.bamberg.wiai.minf.forflow.datagenerator.view.DistributionPanel panel_distribution;
    private javax.swing.JPanel panel_externalFile;
    private javax.swing.JPanel panel_facetModification;
    private javax.swing.JPanel panel_formula;
    private javax.swing.JPanel panel_functionDependency;
    private javax.swing.JPanel panel_functionDistribution;
    private javax.swing.JPanel panel_functionFillType;
    private javax.swing.JPanel panel_functionUnit;
    private javax.swing.JPanel panel_functionUnit_availableUnits;
    private javax.swing.JPanel panel_functionUnit_modify;
    private javax.swing.JPanel panel_functionsModification;
    private javax.swing.JPanel panel_functionsQuantity;
    private de.uni.bamberg.wiai.minf.forflow.datagenerator.view.graph.Graph panel_graph;
    private javax.swing.JPanel panel_interval;
    private javax.swing.JPanel panel_interval_notation;
    private javax.swing.JPanel panel_main;
    private javax.swing.JPanel panel_predefinedGenerator;
    private javax.swing.JPanel panel_predefinedGenerator_date;
    private javax.swing.JPanel panel_predefinedGenerator_gender;
    private javax.swing.JPanel panel_predefinedGenerator_type;
    private javax.swing.JPanel panel_progressBar;
    private javax.swing.JPanel panel_quantity;
    public static javax.swing.JProgressBar progressBar;
    private javax.swing.JRadioButton radioButton_date_day;
    private javax.swing.JRadioButton radioButton_date_month;
    private javax.swing.JRadioButton radioButton_date_year;
    private javax.swing.JRadioButton radioButton_gender_female;
    private javax.swing.JRadioButton radioButton_gender_male;
    private javax.swing.JRadioButton radioButton_predefinedGenerator_de;
    private javax.swing.JRadioButton radioButton_predefinedGenerator_us;
    private javax.swing.JScrollPane scrollPane_artefacts;
    private javax.swing.JScrollPane scrollPane_currentFacets;
    private javax.swing.JScrollPane scrollPane_editor;
    private javax.swing.JScrollPane scrollPane_functionUnit;
    private javax.swing.JSeparator separator_menuEdit1;
    private javax.swing.JSeparator separator_menuEdit2;
    private javax.swing.JSeparator separator_menuFile1;
    private javax.swing.JSeparator separator_menuFile2;
    private javax.swing.JSeparator separator_run;
    private javax.swing.JToolBar.Separator separator_toolBar1;
    private javax.swing.JToolBar.Separator separator_toolBar2;
    private javax.swing.JToolBar.Separator separator_toolBar3;
    private javax.swing.JSplitPane splitPane_function_facets;
    private javax.swing.JSplitPane splitPane_vertical;
    private javax.swing.JPanel subPanel_editor;
    private javax.swing.JPanel subPanel_predefinedGenerators;
    private javax.swing.JTabbedPane tabbedPane_facetsSelectedArtefact;
    private javax.swing.JTabbedPane tabbedPane_functions;
    private javax.swing.JTabbedPane tabbedPane_subFunctionFillType;
    private javax.swing.JTable table_currentFacetts;
    private javax.swing.JTextArea textArea_editor;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JTree tree_artefacts;
    private javax.swing.JTree tree_functionUnit;
    // End of variables declaration//GEN-END:variables
    
    /**
     * To be notified of node changes a tree model listener is implemented.
     * The listener detects when nodes - that are artefacts - are changed,
     * added, or removed.
     * 
     * @author Michael Munz
     * @version 0.1
     * @since Apr/11/09
     */
    private class CustomizedTreeModelListener implements TreeModelListener
    {
    	/**
    	 * default constructor
    	 */
    	protected CustomizedTreeModelListener()
		{
		}
    	
		@Override
		public void treeNodesChanged(TreeModelEvent tme)
		{
		}

		@Override
		public void treeNodesInserted(TreeModelEvent tme)
		{
		}

		@Override
		public void treeNodesRemoved(TreeModelEvent tme)
		{
		}

		@Override
		public void treeStructureChanged(TreeModelEvent tme)
		{
		}
    }
    
    /**
     * Customized table model to display data in a tabular fashion.
     * When the user selects a node in the tree its attributes (facets)
     * are loaded into the table. The loading itself is done via background
     * tasks. If the loading has finished it is displayed immediately.
     * At that point the data is available to get modified.
     * 
     * @author Michael Munz
     * @version 0.1
     * @since Apr/20/09
     */
    private class CustomizedTableModel extends AbstractTableModel
    {
    	/**
		 * ID generated by Eclipse
		 */
		private static final long serialVersionUID = -3263630458363963374L;
		
		/**
		 * specifies the column name <i>Name</i>
		 */
		private final String NAME = "Name";
		
		/**
		 * specifies the column name <i>Data Type</i>
		 */
		private final String DATA_TYPE = "Data Type";
		
		/**
		 * specifies the column name <i>Null Allowed?</i>
		 */
		private final String NULL_ALLOWED = "Null Allowed?";
		
		/**
		 * specifies the column name <i>Amount (%)</i>
		 */
		private final String AMOUNT = "Amount (%)";
		
		/**
		 * specifies the column name <i>Multiple Values?</i>
		 */
		private final String MULTIPLE_VALUES = "Multiple Values?";
		
		/**
		 * specifies the column name <i>Upper Bound</i>
		 */
		private final String UPPER_BOUND = "Upper Bound";
		
		/**
		 * specifies the column name <i>Values Restricted?</i>
		 */
		private final String VALUES_RESTRICTED = "Values Restricted?";
		
		/**
		 * specifies the column name <i>Exists Dependency?</i>
		 */
		private final String EXISTS_DEPENDENCY = "Exists Dependency?";
		
		/**
		 * specifies the column name <i>Dependenceies To:</i>
		 */
		private final String DEPENDENCIES_TO = "Dependencies To:";
		
		/**
		 * specifies the column name <i>Fill Type</i>
		 */
		private final String FILL_TYPE = "Fill Type";
		
		/**
		 * specifies the column name <i>Distribution</i>.
		 * Actually, this is a probability distribution.
		 */
		private final String DISTRIBUTION = "Distribution";
		
		/**
		 * specifies the column name <i>Facet Value(s) Preview</i>
		 */
		private final String FACET_VALUES_PREVIEW = "Facet Value(s) Preview";
		
		/**
		 * specifies the column name <i>Unit</i>
		 */
		private final String UNIT = "Unit";
	
		/**
		 * sets the column names for clarity and specifies
		 * the column no.
		 * Each column has a name and a number attached.
		 * The number specifies where the column is placed
		 * in the table.
		 * </p>
		 * Example:
		 * <pre>
		 * columnNames = { {"name", "no"}
		 *                 ...
		 *               }
		 * </pre>
		 */
		private final String[][] columnNames = {{this.NAME, "0"},
												{this.DATA_TYPE, "1"},
												{this.NULL_ALLOWED, "2"},
												{this.AMOUNT, "3"},
												{this.MULTIPLE_VALUES, "4"},
												{this.UPPER_BOUND, "5"},
												{this.VALUES_RESTRICTED, "6"},
												{this.EXISTS_DEPENDENCY, "7"},
												{this.DEPENDENCIES_TO, "8"},
												{this.FILL_TYPE, "9"},
												{this.DISTRIBUTION, "10"},
												{this.FACET_VALUES_PREVIEW, "11"},
												{this.UNIT, "12"}};
		
		/**
		 * holds the data used in table
		 */
		private List<MetaAttribute> data = null;
		
		/**
    	 * sole default constructor.
    	 */
    	public CustomizedTableModel()
		{
		}
    	
    	@Override
    	public int getColumnCount()
    	{
    		return this.columnNames.length;
    	}
    	
    	@Override
    	public String getColumnName(int column)
    	{
    		return this.columnNames[column][0];
    	}
    	
    	@Override
    	public Class<?> getColumnClass(int column)
    	{
    		switch(column)
    		{
    			// name
    			case 0:
    				return String.class;
    			
    			// data type
    			case 1:
    				return String.class;
    			
    			// null?
    			case 2:
    				return Boolean.class;
    			
    			// amount %
    			case 3:
    				return Integer.class;
    				
    			// multiple?
    			case 4:
    				return Boolean.class;

    			// upper bound
    			case 5:
    				return Integer.class;

    			// restricted?
    			case 6:
    				return Boolean.class;

    			// dependency?
    			case 7:
    				return Boolean.class;

    			// dependency
    			case 8:
    				return String.class;

    			// fill type
    			case 9:
    				return String.class;

    			// distribution
    			case 10:
    				return String.class;

    			// values
    			case 11:
    				return String.class;

    			// unit
    			case 12:
    				return String.class;

    			// else
    			default:
    				return null;
    		}
    	}
    	
    	@Override
    	public int getRowCount()
    	{
    		if(this.data != null)
    		{
    			return this.data.size();
    		}
    		else
    		{
    			return -1;
    		}
    	}
    	
    	@Override
    	public void setValueAt(Object value, int row, int column)
    	{
    		if(this.data != null)
    		{
    			MetaAttribute mAttribute = this.data.get(row);
    			
    			switch(column)
    			{
    				// name
        			case 0:
        				mAttribute.setName((String) value);
        				break;
        			
        			// data type
        			case 1:
        				mAttribute.setDataType(this.convert(value));
        				break;
        			
        			// null?
        			case 2:
        				mAttribute.isNullValueAllowed((Boolean) value);
        				
        				// when null has been enabled, enable amount cell
        				this.fireTableCellUpdated(row, this.findColumn(AMOUNT));
        				break;
        			
        			// amount %
        			case 3:
        				mAttribute.setPercentOfNullValues((Integer) value);
        				break;
        				
        			// multiple?
        			case 4:
        				mAttribute.isMultiplicityAllowed((Boolean) value);
        				
        				// when multiple has been enabled, enable upper bound cell
        				this.fireTableCellUpdated(row, this.findColumn(UPPER_BOUND));
        				break;

        			// upper bound
        			case 5:
        				mAttribute.setUpperBound((Integer) value);
        				break;

        			// restricted?
        			case 6:
        				mAttribute.isValueRestricted((Boolean) value);
        				break;

        			// dependency?
        			case 7:
        				mAttribute.existsDependency((Boolean) value);
        				break;

        			// dependencies
        			case 8:
        				// dependencies are more complicated
        				break;

        			// fill type
        			case 9:
        				// set fill type into attribute
        				mAttribute.setFillBehaviour((FillBehaviour) value);
        				
        				// when fill type has been set update preview
        				this.fireTableCellUpdated(row, this.findColumn(FACET_VALUES_PREVIEW));
        				break;

        			// distribution
        			case 10:
        				mAttribute.setDistribution((ProbabilityDistribution) value);
        				break;

        			// preview
        			case 11:
        				// can't be set by user
        				break;

        			// unit
        			case 12:
        				mAttribute.setUnit((String) value);
        				break;

        			// else
        			default:
        				break;
    			}
    			
    			// replace it
    			this.data.set(row, mAttribute);
    		}
    		
    		this.fireTableCellUpdated(row, column);
    		this.updateColumnSize(table_currentFacetts, row, column);
    	}
    	
    	@Override
    	public Object getValueAt(int row, int column)
    	{
    		if(this.data != null)
    		{
	    		MetaAttribute mAttribute = this.data.get(row);
	    		
	    		switch(column)
	    		{
	    			// name
	    			case 0:
	    				return mAttribute.getName();
	    			
	    			// data type
	    			case 1:
	    				return mAttribute.getDataType();
	    			
	    			// null allowed?
	    			case 2:
	    				return mAttribute.isNullValueAllowed();
	    			
	    			// amount %
	    			case 3:
	    				return mAttribute.getPercentOfNullVallues();
	    				
	    			// multiple values allowed?
	    			case 4:
	    				return mAttribute.isMultiplicityAllowed();
	    			
	   				// upper bound 
	    			case 5:
	    				return mAttribute.getUpperBound();
	    				
	    			// values restricted?
	    			case 6:
	    				return mAttribute.isValueRestricted();
	    			
	    			// exits dependency?
	    			case 7:
	    				return mAttribute.exitsDependency();
	    			
	    			// dependencies to
	    			case 8:
	    				return (mAttribute.getDependencies() != null) ? mAttribute.getDependencies() : null;
	    				
	    			// fill type
	    			case 9:
	    				return (mAttribute.getFillType() != null) ? mAttribute.getFillType().getName() : null;
	    			
	    			// distribution
	    			case 10:
	    				return (mAttribute.getDistribution() != null) ? mAttribute.getDistribution().getName() : null;
	    				
	    			// facet value(s) preview
	    			case 11:
	    				return (mAttribute.getPreviewValue() != null) ? mAttribute.getPreviewValue() : null;
	    			
	    			// unit
	    			case 12:
	    				return (mAttribute.getUnit() != null) ? mAttribute.getUnit() : null;
	    			
	    			// else
	    			default:
	    				return null;
	    		}
    		}
    		else
    		{
    			return null;
    		}
    	}
    	
    	@Override
    	public boolean isCellEditable(int row, int column)
    	{
    		switch(column)
    		{
    			// name
    			case 0:
    				return true;
    			
    			// data type
    			case 1:
    				return true;
    			
    			// null?
    			case 2:
    				return true;
    			
    			// amount %
    			case 3:
    				return this.data.get(row).isNullValueAllowed() ? true : false;
    				
    			// multiple?
    			case 4:
    				return true;
    			
    			// upper bound
    			case 5:
    				return this.data.get(row).isMultiplicityAllowed() ? true : false;
    			
    			// restricted?
    			case 6:
    				return true;
    			
    			// dependency?
    			case 7:
    				return true;
    			
    			// depends to
    			case 8:
    				return this.data.get(row).exitsDependency() ? true : false;
    			
    			// fill type
    			case 9:
    				return true;
    			
    			// dist
    			case 10:
    				return true;
    			
    			// preview value
    			case 11:
    				return false;
    			
    			// unit
    			case 12:
    				return true;
    			
    			// else
    			default:
    				return false;
    		}
    	}
    	
    	/**
    	 * tries to find the corresponding column
    	 * to a column name.
    	 * If a column has been found the column number
    	 * will be returned. Otherwise it's failed and
    	 * <i>-1</i> is returned.  
    	 * 
    	 * @return
    	 * 		if successful the column number.</br>
    	 * 		Otherwise <i>-1</i>
    	 */
    	@Override
    	public int findColumn(String columnName)
    	{
    		int column = -1;
    		
    		for(int i=0; i<this.columnNames.length; i++)
    		{
    			/*
    			 *                    0      1
    			 * columnNames = { {"name", "no"}
    			 *                 ...
    			 *               }
    			 */
    			if(this.columnNames[i][0].equals(columnName))
    			{
    				column = Integer.parseInt(this.columnNames[i][1]);
    			}
    		}
    		
    		return column;
    	}
    	
    	@Override
    	public void fireTableDataChanged()
    	{
    		super.fireTableDataChanged();
    		
    		this.initColumnSizes(table_currentFacetts);
    	}
    	
    	/**
         * This method picks good column sizes.
         * If column heads are wider than the column's cells'
         * contents, then this size is used. Otherwise
         * the cell's size is taken.
         * 
         * @param table
         * 		table to adjust the columns' sizes
         */
        private void initColumnSizes(JTable table)
    	{
    		TableColumn column = null;
    		Component comp = null;
    		
    		int headerWidth = 0;
    		int cellWidth = 0;
    		
    		TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
    		
    		for(int i=0; i<this.getColumnCount(); i++)
    		{
    			column = table.getColumnModel().getColumn(i);
    			
    			comp = headerRenderer.getTableCellRendererComponent(
    					table,
    					column.getHeaderValue(),
    					false,
    					false,
    					0, 
    					0);
    			
    			headerWidth = comp.getPreferredSize().width +10;
    			
    			Object maxOb = null;
    			int row = 0;
    			
    			// find the object with the max. length
    			if(this.data != null)
    			{
    				for(int j=0; j<this.data.size(); j++)
    				{
    					Object tmpOb = this.getValueAt(j, i);
    					
    					if(maxOb == null)
    					{
    						maxOb = tmpOb;
    						
    						row = j;
    					}
    					else
    					{
    						if(String.valueOf(maxOb).length() < String.valueOf(tmpOb).length())
    						{
    							maxOb = tmpOb;
    							
    							row = j;
    						}
    					}
    				}
    			}
    			
    			comp = table.getDefaultRenderer(this.getColumnClass(i)).getTableCellRendererComponent(
    					table,
    					maxOb,
    					false,
    					false,
    					row,
    					i);
    			
    			cellWidth = comp.getPreferredSize().width +10;
    			
    			column.setPreferredWidth(Math.max(headerWidth, cellWidth));
    		}
    	}
        
        /**
         * this method is like {@link #initColumnSizes(JTable)}.
         * Instead of initializing the whole table with a
         * good column size, we just update the column size at
         * a specific column.
         * </p>
         * This happens usually, when values change in the table
         * we update that column to have still a good column size.
         * 
         * @param table
         * 		that's the table to update. It has to be updated when
         * 		values change within. The update is necessary to get
         * 		good column sizes again. But we update only that column
         * 		where the changed had happened.
         * @param row
         * 		the row where the data changed.
         * @param column
         * 		the column to update in width to get a good display feeling.
         */
        private void updateColumnSize(JTable table, int row, int column)
        {
        	TableColumn col = table.getColumnModel().getColumn(column);
        	Component comp = null;
        	
        	int headerWidth = 0;
        	int cellWidth = 0;
        	
        	TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
        	
        	comp = headerRenderer.getTableCellRendererComponent(
        			table,
        			col.getHeaderValue(),
        			false,
        			false,
        			0,
        			0);
        	
        	headerWidth = comp.getPreferredSize().width +10;
        	
        	comp = table.getDefaultRenderer(this.getColumnClass(column)).getTableCellRendererComponent(
        			table,
        			this.getValueAt(row, column),
        			false,
        			false,
        			row,
        			column);
        	
        	cellWidth = comp.getPreferredSize().width +10;
        	
        	col.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
        
        /**
         * converts an well-known object to a data type object.
         * This is used when a data type has been selected in the table
         * model and restored back to its place.
         * 
         * @param ob
         * 		object to convert to a data type
         * @return
         * 		data type object
         */
        private DataType convert(Object ob)
        {
        	return ArtefactFactory.createDataType((String) ob);
        }
    }   
}