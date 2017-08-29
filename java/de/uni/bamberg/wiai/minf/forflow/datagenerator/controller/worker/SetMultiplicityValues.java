package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker;

import java.util.List;

import javax.swing.SwingWorker;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.ControllerFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.FacetSimplified;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaAttribute;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog.Multiplicity;

/**
 * The job of this class is to save, for selected facets, the changed
 * multiplicity value. The value defines, how an attribute is handled
 * when it comes to test data generation.
 * Facets with multiplicity enabled get more values generated, whereas
 * the upper bound must be specified by the tester.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/15/09
 */
public class SetMultiplicityValues extends SwingWorker<Void, Void>
{
	/**
	 * reference to dialog
	 */
	private Multiplicity dialog = null;
	
	/**
	 * holds the settings, which attriubtes can have multiple values.
	 */
	private List<FacetSimplified> tableData = null;
	
	/**
	 * constructor is passed the dialog and the table data.
	 * 
	 * @param dialog
	 * 		used to handle the dialog
	 * @param data
	 * 		used to update the model layer.
	 */
	public SetMultiplicityValues(Multiplicity dialog, List<FacetSimplified> data)
	{
		this.dialog = dialog;
		this.tableData = data;
	}
	
	@Override
	protected Void doInBackground() throws Exception
	{
		/* 
		 * object = {{"attribute 1", "class", Boolean, "type"}
		 *           ...}
		 */
		for(int i=0; i<this.tableData.size(); i++)
		{
			FacetSimplified fs = this.tableData.get(i);
			
			MetaClass mClass = this.find(fs.getClassName(), fs.getArtefactType());
			
			MetaAttribute mAttribute = mClass.getAttribute(fs.getAttributeName());
			
			mAttribute.isMultiplicityAllowed(fs.isMultipleValueAllowed());
		}
		
		return null;
	}
	
	@Override
	protected void done()
	{
		if(this.isDone())
		{
			this.dialog.dispose();
		}
	}
	
	/**
	 * tries to find a meta class for a given name
	 * and a type.
	 * 
	 * @param name
	 * 		name of a meta class to look for
	 * @param type
	 * 		the artefact type of meta class
	 * @return
	 * 		meta class or null
	 */
	private MetaClass find(String name, ArtefactType type)
	{
		return ControllerFactory.getController().getMetaClass(name, type);
	}
}