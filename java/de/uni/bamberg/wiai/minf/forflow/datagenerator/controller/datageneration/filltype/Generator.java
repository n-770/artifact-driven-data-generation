package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.algorithm.ValueCounter;

/**
 * This class is an abstract generator class and implements
 * {@link FillBehaviour} and {@link ObservableGenerator} interfaces.
 * All generators must inherit form this class to get full
 * functionality. A generator, in general, generates data
 * to a specific kind of subject. So for example a generator
 * for fist names does generate only data to satisfy that.
 * </p>
 * A generator is a special kind of fill behaviour and is
 * substitutable with another behaviour. This is possible, since
 * the fill behaviour is based on <i>Strategy</i> pattern.
 * </p>
 * Each generator inherited from this abstract class also
 * is an observable. This makes it possible to list only
 * those generators, to which observers have had registered to.
 * For more informations about that functionality, have look
 * at the observer contract.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/30/09
 */
public abstract class Generator implements FillBehaviour, ObservableGenerator, Cloneable
{
	/**
	 * names the kind of generator.
	 * This is used for displaying concerns.
	 */
	private String name = null;
	
	/**
	 * here is the path to a plain text file
	 * from where we use data to generate.
	 * For example, if we wanna generate first names,
	 * the path links to a file containing first names. 
	 */
	private URI path = null;
	
	/**
	 * defines the type of language to generate
	 * data to.
	 */
	private LanguageType language = null;
	
	/**
	 * holds the lower bound of the filling behaviour.
	 */
	private int lowerBound  = 0;
	
	/**
	 * holds the upper bound of the filling behaviour.
	 */
	private int upperBound = 0;
	
	/**
	 * holds the total values for an filling behaviour.
	 * In this case its generators. A pre-defined
	 * generator is linked to an so-called value file.
	 * The value file is an plain text file with a single
	 * value holding in each line. The total amount
	 * of those values makes <i>N</i>.
	 * </p>
	 * <b>N = &Sigma;n_i</b>
	 */
	private int N = 0;
	
	/**
	 * holds the observers for fill behaviour.
	 */
	private List<ObserverFillBehaviour> observersFillBehaviour = null;
	
	/**
	 * constructor assigns a name.
	 * The name should give information about
	 * what it does generate. It is also used
	 * to display in the UI.
	 * </p>
	 * The default language to use is set here
	 * to {@link LanguageType#US}.
	 * You should overwrite it with the provided
	 * method to fit your needs.
	 * </p>
	 * Notice, it's important to call the method
	 * {@link #setPathToFileByLanguage()}. This
	 * has to be done manually, otherwise it would
	 * cause problems with the initializing of variables.
	 * Invoke the method in constructor of subclasses.
	 * 
	 * @param name
	 * 		what does this generator generate?
	 */
	public Generator(String name)
	{
		this.name = name;
		
		this.setDefaultLanguage();
	}
	
	/**
	 * <font size=6><b>Clone</b></font></br>
	 * This constructor is only visible to same package.
	 * It is used to make a copy of this object.
	 * 
	 * @param generator
	 * 		object to clone
	 */
	protected Generator(Generator generator)
	{
		this.name = generator.getName();
		this.path = generator.getPathToFile();
		this.language = generator.getLanguage();
		this.N = generator.getN();
	}
	
	/**
	 * gets the name of this generator.
	 * The name should give information about
	 * what data it generates, actually.
	 * The name is used for displaying purposes.
	 * 
	 * @return
	 * 		the name of the generator
	 */
	public final String getName()
	{
		return this.name;
	}
	
	/**
	 * sets the path to a flat text file. This
	 * file will be used to generate meaningful
	 * test data to an attribute (facet).
	 * We use this approach rather generating dull
	 * random test data. The advantage is, the 
	 * tester gets real life data.
	 * </p>
	 * Notice, this method is the <i>raw</i> type
	 * to set a path. It doesn't take into account
	 * the language type. By contract, this has to be
	 * done by the invoker, to get meaningful data.
	 * If you aren't quite sure, we recommend you use
	 * {@link #setPathToFileByLanguage(LanguageType)}
	 * method, instead.
	 * 
	 * @param path
	 * 		path to file to use for data generation.
	 */
	public void setPathToFile(URI path)
	{
		this.path = path;
		
		this.setN(this.calculateN(path));
		
		this.setLowerBound(0);
		this.setUpperBound(this.getN() - 1);
		
		this.notifyOberverFillBehaviour();
	}
	
	/**
	 * sets the path to a flat text file.
	 * The file is gonna be used to generate meaningful
	 * and realistic test data values for an attribute,
	 * also called a facet.
	 * </p>
	 * Maybe you've noticed there're two different methods
	 * to set the path to a file to use for data generation.
	 * This is because, this takes the language into account
	 * and the other doesn't quite do this.
	 * We recommend you use this method by specifying the
	 * language and the system take the appropriate steps.
	 * 
	 * @param language
	 * 		rather specify the language and the system
	 * 		uses the right file itself.
	 */
	public void setPathToFileByLanguage(LanguageType language)
	{
		this.language = language;
		
		this.setPathToFileByLanguage();
	}
	
	/**
	 * this method does the job of choosing the
	 * right file depending on the language.
	 * </p>
	 * To get the right picture here an example:
	 * </br>
	 * Suppose you've used the default setting,
	 * that is <i>US</i>, then we get the following:
	 * </p>
	 * <code>Resources/us/file.txt</code>
	 * </p>
	 * If you do not want to use the default and 
	 * you've chosen another language, say <i>DE</i>
	 * you get the following:
	 * </p>
	 * <code>Resources/de/file.txt</code>
	 * </p>
	 * Whereas <i>file.txt</i> depends on the concrete
	 * generator, that is the subclass of this abstract
	 * class. The file.txt can be substituted by any
	 * file the subclass uses.
	 * </p>
	 * This method makes therefore use of several different
	 * sub-methods to build up the whole path.
	 * First, the resource folder is created. This is fix
	 * and we know it.
	 * </p>
	 * <code><b>Resources/</b></code>
	 * </p>
	 * Second, the language folder is set.
	 * If the language isn't set explicit, we guess and take
	 * the default.
	 * </p>
	 * <code>Resources/<b>us/</b></code> (implicit by default)</br> 
	 * <code>Resources/<b>de/</b></code> (if explicit set)</br> 
	 * </p>
	 * The last part, we don't know at this time
	 * and wait for a subclass to overwrite it. It's the actual
	 * file to use.
	 * </p>
	 * <code>Resources/de/<b>file.txt/</b></code>
	 */
	protected void setPathToFileByLanguage()
	{
		StringBuffer sb = new StringBuffer();
		
		// check
		this.setDefaultLanguage();
		
		sb.append(this.getResourceFolder());
		sb.append(this.getLanguageFolder());
		sb.append(this.getFile());
		
		this.setPathToFile(URI.create(sb.toString()));
	}
	
	/**
	 * returns the path to a plain text file
	 * to which the generator generates values.
	 * Because we do not generate dull random data,
	 * but real life valuable data.
	 * The file helps to achieve this, and the generator
	 * uses only values in that file which is linked
	 * here.
	 * </p>
	 * Here a word to the flat file:</br>
	 * The file is a plain text file, as mentioned above.
	 * Each line contains a single value and ends with
	 * a carriage return. 
	 * 
	 * @return
	 * 		the path to a file
	 */
	public final URI getPathToFile()
	{
		return this.path;
	}
	
	/**
	 * sets the language specific type.
	 * This isn't always used by each generator.
	 * That depends on the context and on the attribute
	 * to fill with data.
	 * </p>
	 * When this feature is needed to generate proper
	 * values, and no other language is set the default
	 * will be used instead.
	 * </p>
	 * The default language is set to {@link LanguageType#US}.
	 * 
	 * @param language
	 * 		language to use when attribute language specific
	 * 		values allows.
	 */
	public void setLanguage(LanguageType language)
	{
		if(language != null)
		{
			this.language = language;
			
			this.notifyOberverFillBehaviour();
		}
	}
	
	/**
	 * gets the language to generate data.
	 * This feature isn't always used and depends
	 * on the data generator.
	 * Some attributes allow to generate language dependent
	 * data. This is especially true for names, addresses,
	 * zip, email addresses etc.
	 * And with this parameter the corresponding values
	 * are used to fullfil that need. For example,
	 * when <i>US</i> has been chosen, then US data
	 * will be generated.
	 * </p>
	 * When no language has been set, the default
	 * language will be returned to the invoker.
	 * </p>
	 * The default setting is: {@link LanguageType#US}.
	 * 
	 * @return
	 * 		type of language to generated data to.
	 */
	public final LanguageType getLanguage()
	{
		return this.language;
	}
	
	@Override
	public int calculateN(URI file)
	{
		return ValueCounter.calculateN(file);
	}
	
	/**
	 * sets the lower bound value
	 * 
	 * @param lowerBound
	 * 		lower endpoint
	 */
	protected void setLowerBound(int lowerBound)
	{
		this.lowerBound = lowerBound;
	}
	
	/**
	 * sets the upper bound value
	 * 
	 * @param upperBound
	 * 		upper endpoint
	 */
	protected void setUpperBound(int upperBound)
	{
		this.upperBound = upperBound;
	}
	
	/**
	 * sets the total amount of values for
	 * this kind of generator, fill behaviour
	 * respectively.
	 * 
	 * @param N
	 * 		N = &Sigma;n_i
	 */
	protected void setN(int N)
	{
		this.N = N;
	}
	
	/**
	 * gets the lower bound of the filling behaviour.
	 * 
	 * @return
	 * 		lower bound
	 */
	public final int getLowerBound()
	{
		return this.lowerBound;
	}
	
	/**
	 * gets the upper bound of this filling behaviour.
	 * 
	 * @return
	 * 		upper bound
	 */
	public final int getUpperBound()
	{
		return this.upperBound;
	}
	
	@Override
	public final int getN()
	{
		return this.N;
	}
	
	@Override
	public abstract String getValueAt(int n);
	
	@Override
	public void registerObserverFillBehaviour(ObserverFillBehaviour observer)
	{
		if(this.observersFillBehaviour == null)
		{
			this.observersFillBehaviour = new ArrayList<ObserverFillBehaviour>();
		}
		
		this.observersFillBehaviour.add(observer);
		
		this.notifyOberverFillBehaviour();
	}
	
	@Override
	public void removeObserverFillBehaviour(ObserverFillBehaviour observer)
	{
		if(this.observersFillBehaviour != null)
		{
			int n = this.observersFillBehaviour.indexOf(observer);
			
			if(n >= 0)
			{
				this.observersFillBehaviour.remove(n);
			}
		}
	}
	
	@Override
	public void notifyOberverFillBehaviour()
	{
		if(this.observersFillBehaviour != null)
		{
			for(Iterator<ObserverFillBehaviour> i=this.observersFillBehaviour.iterator(); i.hasNext();)
			{
				ObserverFillBehaviour ob = i.next();
				
				ob.updateFillBehaviour(this.getLowerBound(), this.getUpperBound(), this.getN());
			}
		}
	}
	
	@Override
	public final String toString()
	{
		return this.name;
	}
	
	@Override
	public abstract Object clone() throws CloneNotSupportedException;
	
	/**
	 * Gets the resource folder
	 * The resource folder is fix and we know it
	 * at all times.
	 */
	protected String getResourceFolder()
	{
		return Resources.FOLDER_RESOURCES;
	}
	
	/**
	 * gets the language folder.
	 * We don't know the language, but we can
	 * guess. If the language isn't set by user
	 * we take the default.
	 */
	protected String getLanguageFolder()
	{
		String ret = null;
		
		if(this.getLanguage().equals(LanguageType.US))
		{
			ret = Resources.FOLDER_US;
		}
		else if(this.getLanguage().equals(LanguageType.DE))
		{
			ret = Resources.FOLDER_DE;
		}
		
		return ret;
	}
	
	/**
	 * gets the file containing the values for an attribute.
	 * We don't know that and we don't guess about it.
	 * Each subclass must override this method and specify
	 * its own file.
	 */
	protected abstract String getFile();
	
	/**
	 * checks if any language type has been set 
	 * otherwise the default setting is gonna be restored.
	 * The default is set to <i>US</i>.
	 */
	private void setDefaultLanguage()
	{
		if(this.getLanguage() == null)
		{
			this.language = LanguageType.US;
		}
	}
}