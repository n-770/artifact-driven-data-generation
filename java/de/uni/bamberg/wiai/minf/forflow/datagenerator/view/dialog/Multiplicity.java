package de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.jdesktop.application.Action;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.ControllerFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.FacetSimplified;

/**
 * <b>Multiplicity</b> class inherits its behaviour from Dialog.
 * It's a dialog in which facets can be marked to have multiple values.
 * In fact, it's the pendant of {@link NullValues} dialog.
 * There are facets out there, which can have multiple entries of the
 * same type.
 * </p>
 * That is especially true as an example for the facet author.
 * Imagine a document like statement of scopes or specification sheets or
 * any other document. All of those can have more than one author involved
 * in the writing process.
 * </p>
 * To cope with that, the <i>tester</i> has to specify those facets in order
 * to generate multiple values.
 * </p>
 * The class has to main functions: to accept the choices of the tester and
 * to provide cancellation.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/05/09
 */
public class Multiplicity extends JDialog
{
	/**
	 * ID generated by Eclipse
	 */
	private static final long serialVersionUID = -126576944873932143L;
	
	/**
	 * a customized table model. It is much more sophisticated
	 * as the default one.
	 */
	private CustomizedTableModel tableModel_multiple = null;
	
	/**
	 * The constructor is handed down the main frame of the application framework
	 * and creates and sets up the corresponding components.
	 * 
	 * @param parent
	 * 		the main frame of the framework
	 */
	public Multiplicity(Frame parent)
	{
		super(parent);
		
		this.keyBinding();
		
		this.initCompBefore();
		
		// Netbeans method
		this.initComponents();
		
		this.initCompAfter();
	}
	
	/**
	 * a work-around for Netbeans {@link #initComponents()} method.
	 * It is called before.
	 */
	private void initCompBefore()
	{
		this.tableModel_multiple = new CustomizedTableModel();
	}
	
	/**
	 * a work-around for Netbeans {@link #initComponents()} method.
	 * This method is called after it.
	 */
	private void initCompAfter()
	{
		// use right from the start good column sizes
		this.tableModel_multiple.initColumnSizes(this.table_multiplicityValues);
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
        button_ok = new javax.swing.JButton();
        button_cancel = new javax.swing.JButton();
        scrollPane_multiplicityValues = new javax.swing.JScrollPane();
        table_multiplicityValues = new javax.swing.JTable();
        label_description = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(de.uni.bamberg.wiai.minf.forflow.datagenerator.DataGenerator.class).getContext().getResourceMap(Multiplicity.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setModalityType(ModalityType.APPLICATION_MODAL);
        setName("Form"); // NOI18N
        setResizable(false);

        label_headline.setFont(resourceMap.getFont("label_headline.font")); // NOI18N
        label_headline.setText(resourceMap.getString("label_headline.text")); // NOI18N
        label_headline.setName("label_headline"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(de.uni.bamberg.wiai.minf.forflow.datagenerator.DataGenerator.class).getContext().getActionMap(Multiplicity.class, this);
        button_ok.setAction(actionMap.get("accept")); // NOI18N
        button_ok.setText(resourceMap.getString("button_ok.text")); // NOI18N
        button_ok.setName("button_ok"); // NOI18N

        button_cancel.setAction(actionMap.get("cancel")); // NOI18N
        button_cancel.setText(resourceMap.getString("button_cancel.text")); // NOI18N
        button_cancel.setName("button_cancel"); // NOI18N

        scrollPane_multiplicityValues.setName("scrollPane_multiplicityValues"); // NOI18N

        table_multiplicityValues.setModel(this.tableModel_multiple);
        table_multiplicityValues.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table_multiplicityValues.setColumnSelectionAllowed(true);
        table_multiplicityValues.setName("table_multiplicityValues"); // NOI18N
        scrollPane_multiplicityValues.setViewportView(table_multiplicityValues);
        table_multiplicityValues.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        label_description.setText(resourceMap.getString("label_description.text")); // NOI18N
        label_description.setName("label_description"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(label_headline))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label_description)))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollPane_multiplicityValues, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(133, Short.MAX_VALUE)
                .addComponent(button_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_cancel)
                .addGap(132, 132, 132))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(label_headline)
                .addGap(21, 21, 21)
                .addComponent(label_description)
                .addGap(18, 18, 18)
                .addComponent(scrollPane_multiplicityValues, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_ok)
                    .addComponent(button_cancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	/**
	 * This is where the made up choices take effect.
	 * In general, it means the data model is changed accordingly
	 * to the selection of the user. 
	 */
    @Action
    public void accept()
    {
    	ControllerFactory.getControllerBackgroundTask().saveMultiplicityValues(
    			this,
    			this.tableModel_multiple.data);
    }

    /**
     * To leave the dialog without changing anything, the cancel
     * method is provided.
     */
    @Action
    public void cancel()
    {
    	this.dispose();
    }
    
    /**
     * Sets the table data for this dialog.
     * The object passed should look like this:
     * <pre>
     * tableData = {{"attribute 1", "class", new Boolean(), "type"},
     *              ...};
     * </pre>
     * In the 1st column are the attribute names stored and tell which one
     * it is. To make clear where the attribute is hailed from the class name
     * comes in the 2nd column. Right after that a boolean flag indicates if
     * it is set to have multiple values or not.
     * And last to mention is the type in the 4th column. It's not displayed
     * and is only for maintaining used. 
     * 
     * @param tableData
     * 		lists all attributes of all classes
     */
    public void setTableData(List<FacetSimplified> tableData)
    {
    	this.tableModel_multiple.data = tableData;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_cancel;
    private javax.swing.JButton button_ok;
    private javax.swing.JLabel label_description;
    private javax.swing.JLabel label_headline;
    private javax.swing.JScrollPane scrollPane_multiplicityValues;
    private javax.swing.JTable table_multiplicityValues;
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
		private static final long serialVersionUID = 71359303950917158L;

		@Override
		public void actionPerformed(ActionEvent ae)
		{
			dispose();
		}
    }
    
    /**
     * Customizes the default table model. It allows to specify
     * how data in a table should be organized.
     * 
     * @author Michael Munz
     * @version 0.1
     * @since Apr/15/09
     */
    private class CustomizedTableModel extends AbstractTableModel
    {
		/**
		 * ID generated by Eclispe
		 */
		private static final long serialVersionUID = 8217944468823290788L;
		
		/**
		 * defines the names of columns in the table
		 */
		private String[] columnNames = {"Facet",
										"Where to find",
										"Are multiple values allowed?"};
		
		/**
		 * holds the table data
		 */
		private List<FacetSimplified> data = null;
		
		/**
		 * sole default constructor
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
			return this.columnNames[column];
		}
		
		@Override
		public Class<?> getColumnClass(int column)
		{
			switch(column)
			{
				// facet
				case 0:
					return String.class;
				
				// where to find
				case 1:
					return String.class;
				
				// multiple?
				case 2:
					return Boolean.class;
				
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
				FacetSimplified fs = this.data.get(row);
				
				switch(column)
				{
					// facet
					case 0:
						fs.setAttributeName((String) value);
						break;
					
					// where to find
					case 1:
						fs.setClassName((String) value);
						break;
					
					// multiple?
					case 2:
						fs.isMultipleValueAllowed((Boolean) value);
						break;
					
					// else
					default:
						break;
				}
				
				// replace it
				this.data.set(row, fs);
			}
			
			this.fireTableCellUpdated(row, column);
			this.updateColumnSize(table_multiplicityValues, row, column);
		}

		@Override
		public Object getValueAt(int row, int column)
		{
			if(this.data != null)
			{
				FacetSimplified fs = this.data.get(row);
				
				switch(column)
				{
					// facet
					case 0:
						return fs.getAttributeName();
					
					// where to find
					case 1:
						return fs.getClassName();
					
					// multiple?
					case 2:
						return fs.isMultipleValueAllowed();
					
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
			if(this.getColumnName(column).equals(this.columnNames[2]))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		@Override
		public void fireTableDataChanged()
		{
			super.fireTableDataChanged();
			
			this.initColumnSizes(table_multiplicityValues);
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
    }
}