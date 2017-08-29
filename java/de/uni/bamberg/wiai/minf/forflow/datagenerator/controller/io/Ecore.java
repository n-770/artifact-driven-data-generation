package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message.ErrorMessage;

/**
 * <b>Load</b> class is designed to deserialize <i>*.ecore</i> files.
 * These files play an important role in obtaining metadata about EMF objects.
 * Ecore files are a dialect of XMI which in turn is a specialized form of XML.
 * To access and retrieve the structure of ecore files a specialized class
 * has to do this. It is actually done with the EMF persistence framework to
 * load an XMI serialization form.
 * </p>
 * The corresponding model is built in memory as a so-called dynamic model.
 *
 * @author Michael Munz
 * @version 0.1
 * @since 03/24/09
 */
public class Ecore
{
	/**
	 * default constructor
	 */
	public Ecore()
	{
	}

	/**
	 * Loads a serialized form of a model. That is an *.ecore file and is
	 * the metamodel of the model. It helps to initially create the model
	 * in memory.
	 *
	 * @param uri
	 * 		specifies the path to an ecore file
	 * @return
	 * 		metamodel of the loaded ecore file
	 */
	public EPackage load(URI uri)
	{
		// creating a resource set
		ResourceSet resourceSet = new ResourceSetImpl();

		// setting up ecore factory
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
				"ecore",
				new EcoreResourceFactoryImpl());

		Resource resource = resourceSet.getResource(uri, true);

		EPackage ePackage = (EPackage) resource.getContents().get(0);

		// register in local resource registry
		resourceSet.getPackageRegistry().put(ePackage.getNsURI(), ePackage);

		// load
		try
		{
			resource.load(null);
		}
		catch(IOException ioe)
		{
			ErrorMessage.getInstance().printMessage(ioe,
													this.getClass().getName(),
													"IOException");
		}

		return ePackage;
	}	
}