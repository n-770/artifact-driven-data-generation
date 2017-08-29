package de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jdesktop.application.Action;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.ControllerFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.DataGeneratorView;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message.Message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * <b>ChooseArtefacts</b> dialog allows to select only those
 * artefacts the tester is interested in.
 * To each artefact an so called artefact type must be attached
 * to make sure it is stored in the right data structure.
 * One has the possibility to remove or to add types.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/07/09
 */
public class ChooseArtefacts extends JDialog
{
	/**
	 * ID generated by Eclipse
	 */
	private static final long serialVersionUID = 8967034253023715075L;
	
    /**
     * default list model.
     * Here are the elements listed that are found in the EMF Ecore Model.
     */
    private DefaultListModel listModel_availableArtefacts = null;
    
    /**
     * default list model.
     * Here are the elements listed which are selected by the user.
     */
    private DefaultListModel listModel_selectedArtefacts = null;
    
    /**
     * reference to main view. It's actually not needed,
     * it's just passed on.
     */
    private DataGeneratorView view = null;
    
	/**
	 * constructor is passed the main frame of this application's framework.
	 * Furthermore, it creates the components for this dialog with {@link #initComponents()}.
	 * 
	 * @param parent
	 * 		main frame of this application.
	 * 		It comes from swing application framework.
	 * @param view
	 * 		reference of main view
	 */
	public ChooseArtefacts(Frame parent, DataGeneratorView view)
	{
		super(parent);
		
		this.view = view;
		
		this.init();
		this.keyBinding();
		
		// used by Netbeans
		this.initComponents();
		
		// add list selection listener
		this.list_artefactsSelected.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent lse)
			{
				int n = list_artefactsSelected.getSelectedIndex();
				
				if(n >= 0)
				{
					MetaClass mClass = (MetaClass) listModel_selectedArtefacts.get(n);
					
					if(mClass.getArtefactType() == null)
					{
						comboBox_artefactTypes.setSelectedIndex(-1);
					}
					else
					{
						for(int i=0; i<comboBox_artefactTypes.getItemCount(); i++)
						{
							ArtefactType type = (ArtefactType) comboBox_artefactTypes.getItemAt(i);
							
							if(type.getName().equals(mClass.getArtefactType().getName()))
							{
								comboBox_artefactTypes.setSelectedIndex(i);
							}
						}
					}
				}
			}
		});
		
		// add action listener
		this.comboBox_artefactTypes.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				JComboBox cb = (JComboBox) ae.getSource();
				
				ArtefactType type = (ArtefactType) cb.getSelectedItem();
				
				markAsArtefactType(type);
			}
		});
	}
	
	/**
	 * initializes some of the components which are used
	 * in {@link #initComponents()}
	 */
	private void init()
	{
		this.listModel_availableArtefacts = new DefaultListModel();
		this.listModel_selectedArtefacts = new DefaultListModel();
	}
	
	/**
	 * initializes the key binding on the focused window.
	 * When the <i>ESC</i> button has been pressed, the dialog
	 * is gonna be closed or disposed respectively.
	 */
	private void keyBinding()
	{
		this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"cancel");
		this.getRootPane().getActionMap().put("cancel", new EscAction());
	}
	
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label_headline = new javax.swing.JLabel();
        panel_artefactsAvailable = new javax.swing.JPanel();
        scrollPane_artefactsAvailable = new javax.swing.JScrollPane();
        list_artefactsAvailable = new javax.swing.JList();
        panel_artefactsSelected = new javax.swing.JPanel();
        scrollPane_artefactsSelected = new javax.swing.JScrollPane();
        list_artefactsSelected = new javax.swing.JList();
        button_add = new javax.swing.JButton();
        button_remove = new javax.swing.JButton();
        label_subheadline = new javax.swing.JLabel();
        button_ok = new javax.swing.JButton();
        button_cancel = new javax.swing.JButton();
        panel_artefactType = new javax.swing.JPanel();
        comboBox_artefactTypes = new javax.swing.JComboBox();
        button_addArtefactType = new javax.swing.JButton();
        button_delArtefactType = new javax.swing.JButton();

        setModalityType(ModalityType.DOCUMENT_MODAL);
        setResizable(false);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(de.uni.bamberg.wiai.minf.forflow.datagenerator.DataGenerator.class).getContext().getResourceMap(ChooseArtefacts.class);
        label_headline.setFont(resourceMap.getFont("label_headline.font")); // NOI18N
        label_headline.setText(resourceMap.getString("label_headline.text")); // NOI18N
        label_headline.setName("label_headline"); // NOI18N

        panel_artefactsAvailable.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("panel_artefactsAvailable.border.title"), javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP)); // NOI18N
        panel_artefactsAvailable.setName("panel_artefactsAvailable"); // NOI18N

        scrollPane_artefactsAvailable.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_artefactsAvailable.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_artefactsAvailable.setName("scrollPane_artefactsAvailable"); // NOI18N

        list_artefactsAvailable.setModel(this.listModel_availableArtefacts);
        list_artefactsAvailable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list_artefactsAvailable.setName("list_artefactsAvailable"); // NOI18N
        scrollPane_artefactsAvailable.setViewportView(list_artefactsAvailable);

        javax.swing.GroupLayout panel_artefactsAvailableLayout = new javax.swing.GroupLayout(panel_artefactsAvailable);
        panel_artefactsAvailable.setLayout(panel_artefactsAvailableLayout);
        panel_artefactsAvailableLayout.setHorizontalGroup(
            panel_artefactsAvailableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_artefactsAvailableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane_artefactsAvailable, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_artefactsAvailableLayout.setVerticalGroup(
            panel_artefactsAvailableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_artefactsAvailableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane_artefactsAvailable, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel_artefactsSelected.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("panel_artefactsSelected.border.title"), javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP)); // NOI18N
        panel_artefactsSelected.setName("panel_artefactsSelected"); // NOI18N

        scrollPane_artefactsSelected.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_artefactsSelected.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_artefactsSelected.setName("scrollPane_artefactsSelected"); // NOI18N

        list_artefactsSelected.setModel(this.listModel_selectedArtefacts);
        list_artefactsSelected.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list_artefactsSelected.setName("list_artefactsSelected"); // NOI18N
        scrollPane_artefactsSelected.setViewportView(list_artefactsSelected);

        javax.swing.GroupLayout panel_artefactsSelectedLayout = new javax.swing.GroupLayout(panel_artefactsSelected);
        panel_artefactsSelected.setLayout(panel_artefactsSelectedLayout);
        panel_artefactsSelectedLayout.setHorizontalGroup(
            panel_artefactsSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_artefactsSelectedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane_artefactsSelected, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_artefactsSelectedLayout.setVerticalGroup(
            panel_artefactsSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_artefactsSelectedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane_artefactsSelected, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(de.uni.bamberg.wiai.minf.forflow.datagenerator.DataGenerator.class).getContext().getActionMap(ChooseArtefacts.class, this);
        button_add.setAction(actionMap.get("addArtefact")); // NOI18N
        button_add.setText(resourceMap.getString("button_add.text")); // NOI18N
        button_add.setName("button_add"); // NOI18N

        button_remove.setAction(actionMap.get("removeArtefact")); // NOI18N
        button_remove.setText(resourceMap.getString("button_remove.text")); // NOI18N
        button_remove.setName("button_remove"); // NOI18N

        label_subheadline.setText(resourceMap.getString("label_subheadline.text")); // NOI18N
        label_subheadline.setName("label_subheadline"); // NOI18N

        button_ok.setAction(actionMap.get("accept")); // NOI18N
        button_ok.setText(resourceMap.getString("button_ok.text")); // NOI18N
        button_ok.setName("button_ok"); // NOI18N

        button_cancel.setAction(actionMap.get("cancel")); // NOI18N
        button_cancel.setText(resourceMap.getString("button_cancel.text")); // NOI18N
        button_cancel.setName("button_cancel"); // NOI18N

        panel_artefactType.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("panel_artefactType.border.title"), javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP)); // NOI18N
        panel_artefactType.setName("panel_artefactType"); // NOI18N

        comboBox_artefactTypes.setName("comboBox_artefactTypes"); // NOI18N

        button_addArtefactType.setAction(actionMap.get("addArtefactType")); // NOI18N
        button_addArtefactType.setText(resourceMap.getString("button_addArtefactType.text")); // NOI18N
        button_addArtefactType.setName("button_addArtefactType"); // NOI18N

        button_delArtefactType.setAction(actionMap.get("delArtefactType")); // NOI18N
        button_delArtefactType.setText(resourceMap.getString("button_delArtefactType.text")); // NOI18N
        button_delArtefactType.setName("button_delArtefactType"); // NOI18N

        javax.swing.GroupLayout panel_artefactTypeLayout = new javax.swing.GroupLayout(panel_artefactType);
        panel_artefactType.setLayout(panel_artefactTypeLayout);
        panel_artefactTypeLayout.setHorizontalGroup(
            panel_artefactTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_artefactTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_artefactTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_artefactTypeLayout.createSequentialGroup()
                        .addComponent(comboBox_artefactTypes, 0, 100, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_artefactTypeLayout.createSequentialGroup()
                        .addGroup(panel_artefactTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button_addArtefactType)
                            .addComponent(button_delArtefactType))
                        .addGap(24, 24, 24))))
        );

        panel_artefactTypeLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button_addArtefactType, button_delArtefactType});

        panel_artefactTypeLayout.setVerticalGroup(
            panel_artefactTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_artefactTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboBox_artefactTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(button_addArtefactType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_delArtefactType)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel_artefactsAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button_add)
                            .addComponent(button_remove))
                        .addGap(18, 18, 18)
                        .addComponent(panel_artefactsSelected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panel_artefactType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(label_headline))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(label_subheadline))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(button_ok)
                        .addGap(27, 27, 27)
                        .addComponent(button_cancel)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button_add, button_remove});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button_cancel, button_ok});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_headline)
                .addGap(7, 7, 7)
                .addComponent(label_subheadline)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(panel_artefactType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(70, 70, 70))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(70, 70, 70)
                            .addComponent(button_add)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(button_remove))
                        .addComponent(panel_artefactsAvailable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panel_artefactsSelected, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_cancel)
                    .addComponent(button_ok))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	/**
	 * adds the selected artefact(s) in the list <i>available artefacts</i>
	 * to the list of <i>selected artefacts</i>.
	 * Only for artefacts which are in the latter list are test datas generated. 
	 */
	@Action
	public void addArtefact()
	{
		int index = this.list_artefactsAvailable.getSelectedIndex();
		
		MetaClass artefact = (MetaClass) this.listModel_availableArtefacts.get(index);
		
		this.listModel_selectedArtefacts.addElement(artefact);
		this.listModel_availableArtefacts.remove(index);
	}

	/**
	 * removes selected artefact(s) from the list <i>selected artefacts</i>.
	 * It is not possible to generate test data for artefacts, which are not
	 * in the corresponding list.
	 */
	@Action
	public void removeArtefact()
	{
		int index = this.list_artefactsSelected.getSelectedIndex();
		
		MetaClass artefact = (MetaClass) this.listModel_selectedArtefacts.get(index);
		
		artefact.setArtefactType(null);
		
		this.listModel_availableArtefacts.addElement(artefact);
		this.listModel_selectedArtefacts.remove(index);
	}
	
	/**
	 * the selected artefacts are passed to the component
	 * showing them in the UI. Before this is done, the
	 * selection has to be valid, otherwise it's rejected.
	 * </p>
	 * A selection is said to be valid, if and only if
	 * all artefacts have an artefact type attached.
	 */
	@Action
	public void accept()
    {
		boolean isValid = true;
		
		for(int i=0; i<this.listModel_selectedArtefacts.size(); i++)
		{
			MetaClass artefact = (MetaClass) this.listModel_selectedArtefacts.get(i);
			
			if(artefact.getArtefactType() == null)
			{
				isValid = false;
			}
		}
		
		if(isValid)
		{
			List<MetaClass> artefacts = new ArrayList<MetaClass>();
			
			for(int i=0; i<this.listModel_selectedArtefacts.size(); i++)
			{
				MetaClass artefact = (MetaClass) this.listModel_selectedArtefacts.get(i);
				
				artefacts.add(artefact);
			}
			
			ControllerFactory.getController().createArtefacts(artefacts, this.view);
			
			this.dispose();
			
			this.resetLists();
		}
		else
		{
			Message.showMessageDialog("Please make sure you have defined all\n"
									 +"selected artefacts with a type!",
									  "Input Error",
									  JOptionPane.WARNING_MESSAGE);
		}
    }

	/**
	 * cancels the this process and closes the frame.
	 * No selection or other stuff is going to be saved.
	 */
    @Action
    public void cancel()
    {
    	this.dispose();
    	
    	this.resetLists();
    }
    
    /**
     * opens a dialog to add a new artefact type.
     */
    @Action
    public void addArtefactType()
    {
    	AddArtefactType iaat = new AddArtefactType(this);
    	
    	iaat.setVisible(true);
    }

    /**
     * deletes the current selected artefact type not only 
     * from the combo box, but from the model layer.
     * At the model the artefact type and the artefact collection
     * are deleted accordingly.
     */
    @Action
    public void delArtefactType()
    {
    	int n = this.comboBox_artefactTypes.getSelectedIndex();
    	
    	ArtefactType type = (ArtefactType) this.comboBox_artefactTypes.getSelectedItem();
    	
    	this.comboBox_artefactTypes.removeItemAt(n);
    	
    	ControllerFactory.getController().removeArtefactType(type);
    }
    
    /**
     * marks the current selected artefact with the selected
     * element of the combo box.
     * Notice, all artefacts have to be marked with such a type.
     * 
     * @param type
     * 		artefact type to distinguish collections.
     */
    @Action
    public void markAsArtefactType(ArtefactType type)
    {
    	int index = this.list_artefactsSelected.getSelectedIndex();
    	
    	if(index != -1)
    	{
	    	MetaClass artefact = (MetaClass) this.listModel_selectedArtefacts.get(index);
			
			artefact.setArtefactType(type);
			
			this.repaint();
    	}
    }
    
    /**
     * with this method the current artefact types are updated.
     * This is done before the dialog is displayed.
     * 
     * @param types
     * 		artefact types
     */
    public void setArtefactType(ArtefactType[] types)
	{	
    	this.comboBox_artefactTypes.removeAllItems();
    	
		for(int i=0; i<types.length; i++)
		{
			this.comboBox_artefactTypes.addItem(types[i]);
		}
	}
    
    /**
     * adds a new type of artefact to the list.
     * This is called by an internal input frame.
     * 
     * @param name
     * 		name of a new artefact type
     * @param createDummyFile
     * 		indicates whether to create dummy files for this kind
     * 		of artefact type or not.
     */
    protected void setArtefactType(String name, boolean createDummyFile)
    {
    	ControllerFactory.getController().addArtefactType(name, createDummyFile);
    	
    	this.update();
    }
    
	/**
	 * adds a artefact to the list of available artefacts.
	 * 
	 * @param artefact
	 * 		a meta class is an enriched object of EMF Ecore's EClass.
	 */
	public void addElementToAvailableArtefacts(MetaClass artefact)
	{
		this.listModel_availableArtefacts.addElement(artefact);
	}
	
	/**
	 * resets the lists in its initial state with no elements within.
	 */
	public void resetLists()
	{
		this.listModel_availableArtefacts.removeAllElements();
		this.listModel_selectedArtefacts.removeAllElements();
	}
	
	/**
	 * runs an update on the combo box with the latest
	 * elements on artefact types. This method is only called,
	 * when a new type has been added recently.
	 */
	private void update()
	{
		this.comboBox_artefactTypes.removeAllItems();
		
		for(int i=0; i<ControllerFactory.getController().getArtefactTypes().size(); i++)
		{
			ArtefactType type = ControllerFactory.getController().getArtefactTypes().get(i);
			
			this.comboBox_artefactTypes.addItem(type);
		}
	}
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_add;
    private javax.swing.JButton button_addArtefactType;
    private javax.swing.JButton button_cancel;
    private javax.swing.JButton button_delArtefactType;
    private javax.swing.JButton button_ok;
    private javax.swing.JButton button_remove;
    private javax.swing.JComboBox comboBox_artefactTypes;
    private javax.swing.JLabel label_headline;
    private javax.swing.JLabel label_subheadline;
    private javax.swing.JList list_artefactsAvailable;
    private javax.swing.JList list_artefactsSelected;
    private javax.swing.JPanel panel_artefactType;
    private javax.swing.JPanel panel_artefactsAvailable;
    private javax.swing.JPanel panel_artefactsSelected;
    private javax.swing.JScrollPane scrollPane_artefactsAvailable;
    private javax.swing.JScrollPane scrollPane_artefactsSelected;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Handles the action event, when button <i>ESC</i> has been pressed.
     * This is done by key binding to respond within the focused window.
     * </p>
     * When <i>ESC</i> is pressed, the dialog is gonna be disposed.
     * 
     * @author Michael Munz
     * @version 0.1
     * @since Apr/09/09
     */
    private class EscAction extends AbstractAction
    {
		/**
		 * ID generated by Eclipse
		 */
		private static final long serialVersionUID = 1716570860158519315L;

		@Override
		public void actionPerformed(ActionEvent ae)
		{
			dispose();
		}
    }
}