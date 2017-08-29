package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker;

import java.util.List;

import javax.swing.SwingWorker;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.ControllerFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.FacetSimplified;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaAttribute;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog.NullValues;

/**
 * The job of this class is to save changed settings in facet's null values.
 * All facets having the option enabled to have null values, test data generation
 * could skip generating data for those.
 * Of course, the responsibility to generate meaningful data lies by the tester
 * itself.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/13/09
 */
public class SetNullValuesWorker extends SwingWorker<Void, Void>
{
	/**
	 * reference to dialog
	 */
	private NullValues nullValueDialog = null;
	
	/**
	 * that's the data of the {@link NullValues} dialog, when
	 * it has come to an selection.
	 */
	private List<FacetSimplified> tableDate = null;
	
	/**
	 * Constructor is passed the table data from null values
	 * dialog after a definition which facets should have
	 * the possibility to generate null values. 
	 * 
	 * @param dialog
	 * 		reference to dialog.
	 * 		When the job is done dispose() is called on it.
	 * @param tableData
	 * 		data from null values
	 */
	public SetNullValuesWorker(NullValues dialog, List<FacetSimplified> tableData)
	{
		this.nullValueDialog = dialog;
		this.tableDate = tableData;
	}
	
	@Override
	protected Void doInBackground() throws Exception
	{
		/*
		 * object[][] = {{"attribute", "class", boolean, "type"},
		 * 				 ...}
		 */
		for(int i=0; i<this.tableDate.size(); i++)
		{
			FacetSimplified simplified = this.tableDate.get(i);
			
			// find class
			MetaClass mClass = this.find(simplified.getClassName(), simplified.getArtefactType());
			
			// find attribute
			MetaAttribute mAttribute = mClass.getAttribute(simplified.getAttributeName());
			
			// set value we were looking for
			mAttribute.isNullValueAllowed(simplified.isNullAllowed());
		}
		
		return null;
	}
	
	@Override
	protected void done()
	{
		if(this.isDone())
		{
			this.nullValueDialog.dispose();
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