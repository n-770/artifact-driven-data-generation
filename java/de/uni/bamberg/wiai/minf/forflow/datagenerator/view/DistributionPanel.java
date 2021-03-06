package de.uni.bamberg.wiai.minf.forflow.datagenerator.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.NormalDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.ProbabilityDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.UniformDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message.ErrorMessage;
import org.jdesktop.application.Action;

/**
 * This class gathers not only the probability distribution
 * available to the system but also the parameters of those
 * in a separate panel. If you select an available probability
 * distribution at the same time its corresponding parameter panel
 * changes, too. The parameter panel holds parameters like
 * the <i>Expectation</i> and <i>Variance</i> to change those
 * settings via the UI.
 * </p>
 * Note, the probability distributions notify about its creation
 * with an update. Make sure you pass it down here along with its
 * parameter panel. 
 * </p>
 * <font size=6><b>Example</b></font></br>
 * The following image shows an probability distribution and its
 * parameter panel.</br>
 * <img src="DistributionParameterPanel.jpg" alt="DistributionParameterPanel.jpg" />
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/07/09
 */
public class DistributionPanel extends JPanel
{
	/**
	 * ID generated by Eclispe
	 */
	private static final long serialVersionUID = 4319221727767614055L;
	
	/**
	 * holds all used parameter panels available.
	 */
	private Map<String, DistributionParameterPanel> distParameterPanels = null;
	
	/**
	 * reference to main view. We need that to update
	 * the table with the latest distribution
	 */
	private DataGeneratorView view = null;
	
	/**
	 * default constructor.
	 * Because there're problems with Netbeans, when
	 * overwriting the default constructor, we can't
	 * use a customized version.
	 * </p>
	 * <font size=6><b>Needed reference</b></font></br>
	 * Because it's impossible to pass the reference
	 * of {@link DataGeneratorView} with the constructor
	 * you've to make sure this is done manually by the
	 * provided method.
	 */
	public DistributionPanel()
	{
		super(new BorderLayout());
		
		this.initBefore();
		
		this.initComponents();
		
		this.initAfter();
	}
	
	/**
	 * constructor is passed the main view.
	 * There is the table model in which we have
	 * to set the latest distribution.
	 * </p>
	 * <font color=red>
	 * Do not use this method with Netbeans,
	 * because it won't work.
	 * </font>
	 * Instead use the default constructor and
	 * pass the reference manually.
	 */
	public DistributionPanel(DataGeneratorView view)
	{
		super(new BorderLayout());
		
		this.view = view;
		
		this.initBefore();
		
		this.initComponents();
		
		this.initAfter();
	}
	
	/**
	 * work-around of Netbeans {@link #initComponents()} method.
	 * This is called before that of Netbean's.
	 */
	private void initBefore()
	{
		this.distParameterPanels = new HashMap<String, DistributionParameterPanel>();
	}
	
	/**
	 * work-around of Netbeans {@link #initComponents()} method.
	 * It is called after that.
	 */
	private void initAfter()
	{
		this.initComboBox();
	}
	
	/**
	 * initializes the combo box which holds the probability
	 * distributions available. To get a change in the parameter
	 * panel an item listener causes an update on the appropriate
	 * panel. At any time, to a probability distribution the
	 * parameter panel is displayed.
	 * </p>
	 * <font size=6><b>Listener</b></font></br>
	 * As an appropriate listener an action listener has been
	 * chosen. The great advantage of this listener is that
	 * it's invoked each time an action happens. An action will
	 * occurs, although you select an item in the combo box
	 * but you're choosing the same item again as before.
	 * This isn't possible with the item changed listener.
	 * An event only occurs, when a item is selected which wasn't
	 * before. But that isn't appropriate to your needs. We want
	 * an event even it's the same action as before.
	 * </p>
	 * <font size=6><b>Clone object</b></font></br>
	 * Probability distributions reside in the combo boxes.
	 * But whenever an action occurs or any change in the values
	 * an clone is created to get the latest updates. This is
	 * necessary because typed in values or changes would occur
	 * anytime the distribution is selected. To avoid this and 
	 * to keep the original distribution in a virgin state,
	 * cloning is the way to go. 
	 * </p>
	 * <font size=6><b>Chain of responsibility</b></font></br>
	 * Cloned distributions hold the current values a tester has
	 * made. The clones then passed to the parent of this class.
	 * We use the parent rather setting it directly. We this approach
	 * the chain of responsiblity is not gonna be violated. 
	 */
	private void initComboBox()
	{
		// add item listener
		this.comboBox_distributions.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				findSelectedDistribution(true);
			}
		});
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboBox_distributions = new javax.swing.JComboBox();
        panel_parameter = new javax.swing.JPanel();
        button_accept = new javax.swing.JButton();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(de.uni.bamberg.wiai.minf.forflow.datagenerator.DataGenerator.class).getContext().getResourceMap(DistributionPanel.class);
        comboBox_distributions.setToolTipText(resourceMap.getString("comboBox_distributions.toolTipText")); // NOI18N
        comboBox_distributions.setName("comboBox_distributions"); // NOI18N

        panel_parameter.setName("panel_parameter"); // NOI18N
        panel_parameter.setLayout(new java.awt.CardLayout());

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(de.uni.bamberg.wiai.minf.forflow.datagenerator.DataGenerator.class).getContext().getActionMap(DistributionPanel.class, this);
        button_accept.setAction(actionMap.get("accept")); // NOI18N
        button_accept.setText(resourceMap.getString("button_accept.text")); // NOI18N
        button_accept.setToolTipText(resourceMap.getString("button_accept.toolTipText")); // NOI18N
        button_accept.setName("button_accept"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_parameter, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(comboBox_distributions, 0, 198, Short.MAX_VALUE)
                    .addComponent(button_accept, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboBox_distributions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panel_parameter, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_accept)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * this method accepts the current settings and passes it
     * on to the view, which itself passes it to the table to
     * store it in the selected facet.
     */
    @Action
    public void accept()
    {
    	this.findSelectedDistribution(false);
    }
    
    /**
     * sets the highly needed reference manually.
     * Make sure you call this method!
     * 
     * @param view
     * 		reference to main view
     */
    protected void setDataGeneratorView(DataGeneratorView view)
    {
    	this.view = view;
    }
    
    /**
     * Probability distributions which cause an update are handed
     * down here, if an <i>add</i> update is invoked. Remember,
     * the corresponding operation is indicated a flag and the
     * observer has to make sure it invokes the right kind of method
     * down here.
     * </p>
     * To each probability distribution there has to be a parameter
     * panel. That is a panel, in which users can make changes in
     * the values of <i>Expectation</i> and/or <i>Variance</i> etc.
     * 
     * @param distribution
     * 		that's the probability distribution to add to the list
     * 		of available distributions. Call this, when the update
     * 		is an <i>add</i> operation.
     * @param parameterPanel
     * 		a panel for parameter settings
     */
	protected void addProbability(ProbabilityDistribution distribution, DistributionParameterPanel parameterPanel)
	{
		// add to combo
		this.comboBox_distributions.addItem(distribution);
		
		// add to data structure
		this.distParameterPanels.put(distribution.getName(), parameterPanel);
		
		// add to panel
		this.panel_parameter.add(parameterPanel, distribution.getName());
		
		// hand down reference of dist to its parameter panel
		parameterPanel.setDistribution(distribution);
	}
	
	/**
	 * Probability distributions can also send an update about
	 * <i>removing</i> it from the UI list. Such an update is
	 * caused by the controller layer only. The view layer
	 * has to make sure that the update is handled correct.
	 * Otherwise not available probability distributions are
	 * in the view selectable. That would lead to an exception!
	 * 
	 * @param distribution
	 * 		a probability distribution which as send an <i>remove</i>
	 * 		update.
	 */
	protected void removeProbability(ProbabilityDistribution distribution)
	{
		this.comboBox_distributions.removeItem(distribution);
		
		this.distParameterPanels.remove(distribution.getName());
	}
	
	/**
	 * Whenever an change occurs on panels below of this
	 * we get an update and pass it on to the view.
	 * The reference is necessary because value changes
	 * have to passed to the table model and the distribution
	 * panel is one part of that chain.
	 * </p>
	 * <font size=6><b>Chain of responsibility</b></font></br>
	 * <img src="ChainOfResponsibility_distributionPanel.jpg" 
	 * alt="ChainOfResponsibility_distributionPanel.jpg"/>
	 * 
	 * @param dist
	 * 		probability distribution which has had a change
	 * 		in the data structure.
	 * @param updateGraph
	 * 		indicates whether to update graph or not
	 */
	protected void updateAndSetValues(ProbabilityDistribution dist, boolean updateGraph)
	{
		this.view.setProbabilityIntoTable(dist, updateGraph);
	}
	
	/**
	 * this method is usually called, when any distribution
	 * has been set earlier and the same row & column
	 * are selected again. So we get that settings and
	 * update the graph without setting the values again
	 * into the table.
	 * 
	 * @param dist
	 * 		probability distribution passed on to graph
	 */
	protected void updateWithoutSettingValues(ProbabilityDistribution dist)
	{
		this.view.drawProbabilityDensityFunction(dist);
	}
	
	/**
	 * this method uses the values of the last time 
	 * when the tester had made his/her choices.
	 * </p>
	 * First the probability distribution the last time
	 * usted is selected in the combo box. Then the
	 * values from the table are passed down here and
	 * displayed in the parameter of the corresponding
	 * panel.
	 * 
	 * @param dist
	 * 		distribution used the last time by the user.
	 */
	protected void useValuesAlreadySet(ProbabilityDistribution dist)
	{
		this.comboBox_distributions.setSelectedItem(dist);
		
		// set parameter values the user had last time
		DistributionParameterPanel parameterPanel = this.distParameterPanels.get(dist.getName());
		
		double[] values = null;
		
		// normal
		if(parameterPanel instanceof NormalDistributionPanel)
		{
			NormalDistribution normal = (NormalDistribution) dist;
			
			values = new double[2];
			
			values[0] = normal.getExpectation();
			values[1] = normal.getStandardDeviation();
		}
		
		// uniform
		else if(parameterPanel instanceof UniformDistributionPanel)
		{
			UniformDistribution uniform = (UniformDistribution) dist;
			
			values = new double[2];
			
			values[0] = uniform.getA();
			values[1] = uniform.getB();
		}
		
		parameterPanel.setValues(values);
		
		this.updateWithoutSettingValues(dist);
	}
	
	/**
	 * if no probability distribution has been chosen yet,
	 * we take the first in the combo box as the default
	 * selection strategy.
	 */
	protected void useFirstInBox()
	{
//		ProbabilityDistribution dist = (ProbabilityDistribution) this.comboBox_distributions.getItemAt(0);
//		
//		DistributionParameterPanel panel = this.distParameterPanels.get(dist.getName());
//		
//		panel.resetInputFields();
	}
	
	/**
	 * find the current selected distribution and makes a clone
	 * object of it. The clone gets then modified and passed on
	 * to the view.
	 * 
	 * @param updateGraph
	 * 		repaint graph?
	 */
	private void findSelectedDistribution(boolean updateGraph)
	{
		ProbabilityDistribution distribution = (ProbabilityDistribution) this.comboBox_distributions.getSelectedItem();
		
		ProbabilityDistribution clone = null;
		
		// change parameter panel to current selected dist
		CardLayout cl = (CardLayout) this.panel_parameter.getLayout();
		
		cl.show(this.panel_parameter, distribution.getName());
		
		DistributionParameterPanel panel = this.distParameterPanels.get(distribution.getName());
		
		try
		{
			// normal
			if(distribution instanceof NormalDistribution)
			{
				// dist
				NormalDistribution normal = (NormalDistribution) distribution;
				
				NormalDistribution cloned = (NormalDistribution) normal.clone();
				
				// panel
				NormalDistributionPanel panel_normal = (NormalDistributionPanel) panel;
				
				if(panel_normal != null)
				{
					cloned.setExpectation(panel_normal.getExpectation());
					cloned.setStandardDeviation(panel_normal.getStandardDeviation());
				}
				
				clone = cloned;
			}
			
			// uniform
			else if(distribution instanceof UniformDistribution)
			{
				// dist
				UniformDistribution uniform = (UniformDistribution) distribution;
				
				UniformDistribution cloned = (UniformDistribution) uniform.clone();
				
				// panel
				UniformDistributionPanel panel_uniform = (UniformDistributionPanel) panel;
				
				if(panel_uniform != null)
				{
					cloned.setA(panel_uniform.getA());
					cloned.setB(panel_uniform.getB());
				}
				
				clone = cloned;
			}
		}
		catch(CloneNotSupportedException cnse)
		{
			ErrorMessage.getInstance().printMessage(cnse, "CloneNotSupportedException");
		}
		
		// set clone into table
		this.updateAndSetValues(clone, updateGraph);
	}
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_accept;
    private javax.swing.JComboBox comboBox_distributions;
    private javax.swing.JPanel panel_parameter;
    // End of variables declaration//GEN-END:variables
}