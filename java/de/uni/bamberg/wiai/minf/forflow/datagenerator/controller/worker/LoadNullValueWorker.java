package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.SwingWorker;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.DataGenerator;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.ControllerFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.Artefact;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.FacetSimplified;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaAttribute;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog.NullValues;

/**
 * Loading all facets of available artefact elements is done in background.
 * To do so this class inherits its behviour from {@link SwingWorker}.
 * After preparing the data it is passed to {@link NullValues} dialog.
 * When this happens, the dialog is displayed and ready for further manipulation.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/13/09
 */
public class LoadNullValueWorker extends SwingWorker<Void, Void>
{
	/**
	 * reference to dialog
	 */
	private NullValues nullValuesDialog = null;
	
	/**
	 * data to show in dialog
	 */
	private List<FacetSimplified> data = null;
	
	/**
	 * constructor is passed the dialog of null values.
	 * 
	 * @param nullValuesDialog
	 */
	public LoadNullValueWorker(NullValues nullValuesDialog)
	{
		this.nullValuesDialog = nullValuesDialog;
		
		this.data = new ArrayList<FacetSimplified>();
	}
	
	@Override
	protected Void doInBackground() throws Exception
	{
		// iterate through artefacts
		for(Iterator<Artefact> i=ControllerFactory.getController().iteratorArtefacts(); i.hasNext();)
		{
			Artefact artefact = i.next();
			
			for(Iterator<MetaClass> j=artefact.iterator(); j.hasNext();)
			{
				MetaClass mClass = j.next();
				
				// iterate through attributes
				for(Iterator<MetaAttribute> k=mClass.getAttributes().iterator(); k.hasNext();)
				{
					MetaAttribute mAttribute = k.next();
					
					FacetSimplified simplified = ArtefactFactory.createFacetSimplified();
					
					simplified.setAttributeName(mAttribute.getName());
					simplified.setClassName(mClass.getName());
					simplified.isNullAllowed(mAttribute.isNullValueAllowed());
					
					// invisible data (it's not displayed, but helpy when it comes to saving)
					simplified.setArtefactType(mClass.getArtefactType());
					
					this.data.add(simplified);
				}
			}
		}
		
		return null;
	}
	
	@Override
	protected void done()
	{
		if(this.isDone())
		{
			this.nullValuesDialog.setData(this.data);
			
			DataGenerator.getApplication().show(this.nullValuesDialog);
		}
	}
}