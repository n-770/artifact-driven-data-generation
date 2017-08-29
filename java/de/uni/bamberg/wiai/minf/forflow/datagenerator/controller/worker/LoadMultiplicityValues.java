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
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog.Multiplicity;

/**
 * The system gives the user the opportunity to select those
 * values having multiple values in a dialog. Before the dialog
 * is showing up all facets of all artefacts are gathered.
 * Depending on the amount of artefacts and facets this process
 * can take some time. So this job is running in the background. 
 * </p>
 * The dialog will be displayed at the time the process has been
 * finished.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/15/09
 */
public class LoadMultiplicityValues extends SwingWorker<Void, Void>
{
	/**
	 * reference to dialog
	 */
	private Multiplicity dialog = null;
	
	/**
	 * first the data is created and then displayed.
	 */
	private List<FacetSimplified> tableData = null;
	
	/**
	 * constructor is passed the dialog for setting
	 * the multiplicity flag.
	 * 
	 * @param dialog
	 * 		Multiplicity dialog
	 */
	public LoadMultiplicityValues(Multiplicity dialog)
	{
		this.dialog = dialog;
		
		this.tableData = new ArrayList<FacetSimplified>();
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
				
				// iterate through facets
				for(Iterator<MetaAttribute> k=mClass.iterator(); k.hasNext();)
				{
					MetaAttribute mAttribute = k.next();
					
					FacetSimplified fs = ArtefactFactory.createFacetSimplified();
					
					fs.setAttributeName(mAttribute.getName());
					fs.setClassName(mClass.getName());
					fs.isMultipleValueAllowed(mAttribute.isMultiplicityAllowed());
					
					// it's not displayed, but helpy when it comes to saving 
					fs.setArtefactType(mClass.getArtefactType());
					
					this.tableData.add(fs);
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
			this.dialog.setTableData(this.tableData);
			
			DataGenerator.getApplication().show(this.dialog);
		}
	}
}