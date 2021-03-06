package de.uni.bamberg.wiai.minf.forflow.datagenerator.view;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.ProbabilityDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.UniformDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message.ErrorMessage;

/**
 * This class represents the parameters of an Uniform probability
 * distribution. An Uniform probability distribution has two
 * parameters, <i>a</i> and <i>b</i>. Those form a so-called
 * range, where <i>a</i> is the minimum and <i>b</i> is the
 * maximum of the range or interval. 
 * </p>
 * In an Uniform probability function all values have the same
 * probability. In other words, they're all equal likely.
 * For more informations about this distribution, have a look
 * at {@link UniformDistribution}. 
 * </p>
 * <font size=6><b>Parameters</b></font></br>
 * a = minimum </br>
 * b = maximum </br>
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/06/09
 */
public class UniformDistributionPanel extends DistributionParameterPanel
{
	/**
	 * ID generated by Eclipse
	 */
	private static final long serialVersionUID = 5111252364759161919L;
	
	/**
	 * constructor is passed the probability distribution to which
	 * it belongs. This is because, we have to pass on the typed in
	 * values.
	 */
	public UniformDistributionPanel(ProbabilityDistribution distribution)
	{
		this.setDistribution(distribution);
		
		this.initBefore();
		
		this.initComponents();
		
		this.initAfter();
	}
	
	/**
	 * constructor is passed the probability distribution and the 
	 * parent panel. Remember, this is a panel for a specific
	 * probability distribution and is itself only a sub-panel.
	 * </p>
	 * <font size=6><b>Distribution</b></font></br>
	 * This is the probability distribution of this panel.
	 * The panel is responsible for updating typed in values
	 * and changes to that distribution. Because there're many
	 * different distributions available, one for all can't be
	 * realized. So there is one parameter panel for one distribution.
	 * </p>
	 * <font size=6><b>Distribution panel</b></font></br>
	 * This is the parent panel and holds all available probability
	 * distributions. When a particular distribution is selected by
	 * the tester, it gets the corresponding parameter panel.
	 * The parameter panel (this) is only responsible for the parameter
	 * settings like <i>a</i> and/or <i>b</i>.
	 * When an update occrus down here, it has to call its parent
	 * which in turn calls its parent, too. In other words, the
	 * <i>chain of responsibility</i> is followed.
	 * 
	 * @param distribution
	 * 		that's the distribution the panel is responsible for.
	 * @param panel
	 * 		the parent panel gets notified whenever an update occurs down here.
	 */
	public UniformDistributionPanel(ProbabilityDistribution distribution, DistributionPanel panel)
	{
		this.setDistribution(distribution);
		this.setDistributionPanel(panel);
		
		this.initBefore();
		
		this.initComponents();
		
		this.initAfter();
	}
	
	/**
	 * work-around of Netbeans {@link #initComponents()} method.
	 * This method is called before the mentioned method.
	 */
	private void initBefore()
	{
	}
	
	/**
	 * work-around of Netbeans {@link #initComponents()} method.
	 * This method is called after it.
	 */
	private void initAfter()
	{
		this.initSpinnerA();
		
		this.initSpinnerB();
	}
	
	/**
	 * initializes the spinner for parameter <i>a</i> = min.
	 * </p>
	 * <font size=6><b>SpinnerModel</b></font></br>
	 * First an default model is used at this point,
	 * because we don't know the values, yet.
	 * The default model is gonna be substituted by
	 * a custom model when the values are available.
	 * </p>
	 * <font size=6><b>ChangeListener</b></font></br>
	 * To get notified about the latest values, a change 
	 * listener is registered. So if the value changes for
	 * parameter <i>a</i>, the uniform probability distribution
	 * gets the latest value.
	 * </p>
	 * <font size=6><b>Clone</b></font></br>
	 * Whenever a change here happens a clone object
	 * of the distribution this panel is responsible for
	 * is gonna be created. That is, 'cos we've to set
	 * the values many times. And if we use each time
	 * the same instance, we use also the same values.
	 * This is avoided by this approach. The cloned object
	 * gets the latest values and is send to the parent
	 * panel by an update call. We call the parent, rather
	 * to set the distribution by ourself, because
	 * otherwise we would violate the <i>chain of responsibility</i>.
	 */
	private void initSpinnerA()
	{
		// model
		SpinnerModel model = new SpinnerNumberModel();
		
		this.spinner_a.setModel(model);
		
		// add change listener
		this.spinner_a.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent ce)
			{
				UniformDistribution dist = (UniformDistribution) getDistribution();
				
				UniformDistribution clone = null;
				
				try
				{
					clone = (UniformDistribution) dist.clone();
				}
				catch(CloneNotSupportedException cnse)
				{
					ErrorMessage.getInstance().printMessage(cnse, "CloneNotSupportedException");
				}
				
				// set latest values
				clone.setA(getValue(spinner_a));
				clone.setB(getValue(spinner_b));
				
				// send out an update
				getDistributionPanel().updateAndSetValues(clone, true);
			}
		});
	}
	
	/**
	 * initializes the spinner for parameter <i>b</i> = max.
	 * </p>
	 * <font size=6><b>SpinnerModel</b></font></br>
	 * First an default model is used at this point,
	 * because we don't know the values, yet.
	 * The default model is gonna be substituted by
	 * a custom model when the values are available.
	 * </p>
	 * <font size=6><b>ChangeListener</b></font></br>
	 * A change listener is registered to this spinner to get
	 * notified about changes in the value. It then updates
	 * the uniform probability distribution.
	 * </p>
	 * <font size=6><b>Clone</b></font></br>
	 * Whenever a change here happens a clone object
	 * of the distribution this panel is responsible for
	 * is gonna be created. That is, 'cos we've to set
	 * the values many times. And if we use each time
	 * the same instance, we use also the same values.
	 * This is avoided by this approach. The cloned object
	 * gets the latest values and is send to the parent
	 * panel by an update call. We call the parent, rather
	 * to set the distribution by ourself, because
	 * otherwise we would violate the <i>chain of responsibility</i>.
	 */
	private void initSpinnerB()
	{
		// model
		SpinnerModel model = new SpinnerNumberModel();
		
		this.spinner_b.setModel(model);
		
		// add change listener
		this.spinner_b.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent ce)
			{
				UniformDistribution dist = (UniformDistribution) getDistribution();
				
				UniformDistribution clone = (UniformDistribution) dist;
				
				try
				{
					clone = (UniformDistribution) dist.clone();
				}
				catch(CloneNotSupportedException cnse)
				{
					ErrorMessage.getInstance().printMessage(cnse, "CloneNotSupportedException");
				}
				
				// set latest values
				clone.setA(getValue(spinner_a));
				clone.setB(getValue(spinner_b));
				
				// send out an update
				getDistributionPanel().updateAndSetValues(clone, true);
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

        label_min = new javax.swing.JLabel();
        label_max = new javax.swing.JLabel();
        spinner_a = new javax.swing.JSpinner();
        spinner_b = new javax.swing.JSpinner();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(de.uni.bamberg.wiai.minf.forflow.datagenerator.DataGenerator.class).getContext().getResourceMap(UniformDistributionPanel.class);
        label_min.setText(resourceMap.getString("label_min.text")); // NOI18N
        label_min.setName("label_min"); // NOI18N

        label_max.setText(resourceMap.getString("label_max.text")); // NOI18N
        label_max.setName("label_max"); // NOI18N

        spinner_a.setName("spinner_min"); // NOI18N

        spinner_b.setName("spinner_max"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_min)
                    .addComponent(spinner_a, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_max)
                    .addComponent(spinner_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {spinner_b, spinner_a});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label_min)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinner_a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_max)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinner_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * gets the typed in value of any spinner text field
     * used in this panel.
     * 
     * @param spinner
     * 		text field to get typed in value
     * @return
     * 		intValue()
     */
    private int getValue(JSpinner spinner)
    {
    	SpinnerNumberModel model = (SpinnerNumberModel) spinner.getModel();
    	
    	return model.getNumber().intValue();
    }
    
    /**
     * initializes the spinner model with the current value to
     * display, the min and max values and the stepping.
     * This is necessary when a change in the fill behaviour happens.
     * 
     * @param currentValue
     * 		the initial value to display
     * @param N
     * 		the total amount of values.
	 * 		Because this is in terms of a probability distribution,
	 * 		we refer to it as <i>X</i>.
     * @param step
     * 		the stepping between min & max
     */
    protected void changeSpinnerModel_A(int currentValue, int N, int step)
    {
    	int[] min_max = this.getMinMax(N);
    	
    	SpinnerModel model = new SpinnerNumberModel(currentValue, min_max[0], min_max[1], step);
    	
    	this.spinner_a.setModel(model);
    }
    
    /**
     * initializes the spinner model for parameter <i>b</i>
     * with the inital value, the min and max value and the
     * stepping. This is necessary, when a change in the fill
     * behaviour happens.
     * 
     * @param currentValue
     * 		the initial value to display
     * @param N
     * 		the total amount of values.
	 * 		Because this is in terms of a probability distribution,
	 * 		we refer to it as <i>X</i>.
     * @param step
     * 		the stepping between min & max
     */
    protected void changesSpinnerModel_B(int currentValue, int N, int step)
    {
    	int[] min_max = this.getMinMax(N);
    	
    	SpinnerModel model = new SpinnerNumberModel(currentValue, min_max[0], min_max[1], step);
    	
    	this.spinner_b.setModel(model);
    }
    
    @Override
    protected void resetInputFields()
    {
    	UniformDistribution uniform = (UniformDistribution) this.getDistribution();
    	
    	// a = min
    	this.spinner_a.setValue(uniform.getA());
    	
    	// b = max
    	this.spinner_b.setValue(uniform.getB());
    }
    
    /**
     * sets the min and max values into text fields.
     * </p>
     * values[0] = a </br>
     * values[1] = b 
     */
    @Override
    protected void setValues(double[] values)
    {
    	this.spinner_a.setValue((int) values[0]);
    	this.spinner_b.setValue((int) values[1]);
    }
    
    /**
     * gets the minimum
     * 
     * @return
     * 		a = min
     */
    protected int getA()
    {
    	return this.getValue(this.spinner_a);
    }
    
    /**
     * gets the maximum
     * 
     * @return
     * 		b = max
     */
    protected int getB()
    {
    	return this.getValue(this.spinner_b);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel label_max;
    private javax.swing.JLabel label_min;
    private javax.swing.JSpinner spinner_b;
    private javax.swing.JSpinner spinner_a;
    // End of variables declaration//GEN-END:variables
}