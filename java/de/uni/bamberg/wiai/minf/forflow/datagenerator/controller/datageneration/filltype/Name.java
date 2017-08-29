package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.algorithm.ValueRetriever;

/**
 * This generator is responsible for creating names.
 * It is different from {@link FirstName} and {@link LastName}
 * in that way, that it combines both in once generator.
 * That means, for an attribute a prename and a surname are
 * generated.
 * It's possible to choose between language dependent 
 * names, like for US or German. This class inherits also
 * behaviour from {@link Generator}.
 * </p>
 * <font size=6><b>Observable</b></font></br>
 * Notice, the class implements {@link FillBehaviour} interface.
 * It makes this kind of strategy observable.
 * Observable means in this context, whenever an observer 
 * registers to or un-registers from this class, the subject
 * itself notifies the UI to register this class in a specific
 * list of generators.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/30/09
 */
public class Name extends Generator
{
	/**
	 * which sex has the owner of this name?
	 */
	private SexType sex = null;
	
	/**
	 * holds a second path to a file. This
	 * is because this generator generates a
	 * prename (first name) and a surname (last name).
	 */
	private URI path = null;
	
	/**
	 * This is an additional <i>N</i> for the 2nd path.
	 * Remember, we generate here both a last name plus
	 * a first name. So things are different around here!
	 * </p>
	 * Holds the total values for an filling behaviour.
	 * In this case its generators. A pre-defined
	 * generator is linked to an so-called value file.
	 * The value file is an plain text file with a single
	 * value holding in each line. The total amount
	 * of those values makes <i>N</i>.
	 * </p>
	 * <b>N = &Sigma;n_i</b>
	 */
	private int N2 = 0;
	
	/**
	 * holds observer of this kind of generator
	 */
	private List<ObserverGenerator> observer = null;
	
	/**
	 * default constructor assigns a meaningful name
	 * for this kind of generator.
	 */
	public Name()
	{
		super("Name");
		
		// default gender
		this.sex = SexType.FEMALE;
		
		this.setPathToFileByLanguage();
		
		// set additional path to last name
		this.setPath2(URI.create(this.getFile2()));
	}
	
	/**
	 * <font size=6><b>Clone</b></font></br>
	 * constructor is passed the object to clone.
	 * The constructor is only visible to package and
	 * accessable through that. Its only purpose is
	 * to make a copy of itself.
	 * 
	 * @param name
	 * 		object to clone
	 */
	protected Name(Name name)
	{
		super(name);
		
		this.sex = name.getSex();
		this.path = name.getPath2();
		this.N2 = name.getN2();
	}
	
	@Override
	public final String getValueAt(int n)
	{
		this.setPathToFileByLanguage();
		
		// is in interval?
		if((n >= 0) && (n < this.getN()))
		{
			return ValueRetriever.getValueAt(n, getPathToFile());
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * gets the value at position n of the additional
	 * file. Remember, this class generates names consisting
	 * of first name & last name.
	 * 
	 * @param n
	 * 		value at position n
	 * @return
	 * 		value at position n as a string
	 */
	public final String getValueAt2(int n)
	{
		this.setPath2(URI.create(this.getFile2()));
		
		// is in interval?
		if((n >= 0) && (n < this.getN2()))
		{
			return ValueRetriever.getValueAt(n, getPath2());
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * combines the two getValueAt methods to one method.
	 * Remember this is the generator for names and names
	 * consist of a first name and a last name. Therefore,
	 * we need two indexes for values.
	 * 
	 * @param n
	 * 		the index for the 1st value
	 * @param m
	 * 		the index for the 2nd value
	 * @return
	 * 		string combined of value 1 + 2
	 */
	public final String getValueAt(int n, int m)
	{
		StringBuffer sb = new StringBuffer();
		
		String v1 = this.getValueAt(n);
		String v2 = this.getValueAt2(m);
		
		if(v1 != null)
		{
			sb.append(this.getValueAt(n) +" ");
		}
		
		if(v2 != null)
		{
			sb.append(this.getValueAt2(m));
		}
		
		return sb.toString();
	}
	
	/**
	 * to generate accurate prenames, the
	 * generator needs to know which sex
	 * type it is.
	 * It doesn't make sense to generate a prename
	 * for girls if the sex type is <i>male</i>
	 * and vice versa.
	 * </p>
	 * The default gender is set to <i>female</i>.
	 * 
	 * @param sex
	 * 		which sex type is it? <i>male</i> or <i>female</i>
	 */
	public void setSex(SexType sex)
	{
		if(sex != null)
		{
			this.sex = sex;
			
			this.notifyOberverFillBehaviour();
		}
	}
	
	/**
	 * gets the sex type for this generator.
	 * It's important to distinguish between 
	 * <i>female</i> and <i>male</i> types,
	 * because it doesn't make sense to generate
	 * a prename for girls when it's a <i>male</i>.
	 * </p>
	 * The default setting is <i>female</i>.
	 * 
	 * @return
	 * 		sex: <i>female</i> or <i>male</i>
	 */
	public final SexType getSex()
	{
		return this.sex;
	}
	
	/**
	 * sets an additional path to a file
	 * it uses to generate names.
	 * Names consist of both, a prename and
	 * a surname. And the additional path
	 * takes these into account.
	 * 
	 * @param file
	 * 		an additional path to a flat text file.
	 */
	public void setPath2(URI file)
	{
		this.path = file;
		
		this.N2 = this.calculateN(file);
		
		this.notifyOberverFillBehaviour();
	}
	
	/**
	 * gets the additional path to a plain file
	 * to generate a name. A name consists of
	 * a prename and a surname.
	 * 
	 * @return
	 * 		path to additional file.
	 */
	public final URI getPath2()
	{
		return this.path;
	}
	
	/**
	 * gets the additional <i>N</i> also called
	 * <i>N2</i>. Remember, this is the name object
	 * and each name consists of a first name and 
	 * a last name. If you wanna generate only one
	 * of them use {@link FirstName} or {@link LastName}
	 * instead.
	 * </p>
	 * The total amount returned here is valid for
	 * the part 'last name'. If you wanna get N1 use
	 * {@link #getN()} instead. It gives you <i>N</i>
	 * for prenames.
	 * 
	 * @return
	 * 		N2 = &Sigma;n_i
	 */
	public final int getN2()
	{
		return this.N2;
	}
	
	@Override
	public void registerObserverGenerator(ObserverGenerator observer)
	{
		if(this.observer == null)
		{
			this.observer = new ArrayList<ObserverGenerator>();
		}
		
		this.observer.add(observer);
		
		this.notifyObserverGenerator(true);
	}
	
	@Override
	public void removeObserverGenerator(ObserverGenerator observer)
	{
		if(this.observer != null)
		{
			int n = this.observer.indexOf(observer);
			
			if(n >= 0)
			{
				this.observer.remove(n);
				
				this.notifyObserverGenerator(false);
			}
		}
	}
	
	@Override
	public void notifyObserverGenerator(boolean add)
	{
		for(Iterator<ObserverGenerator> i=this.observer.iterator(); i.hasNext();)
		{
			ObserverGenerator ob = i.next();
			
			ob.updateGenerator(add, this);
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return new Name(this);
	}

	@Override
	protected String getFile()
	{
		String ret = null;
		
		// prename
		if(this.getSex().equals(SexType.FEMALE))
		{
			ret = Resources.PRENAME_GIRLS;
		}
		else if(this.getSex().equals(SexType.MALE))
		{
			ret = Resources.PRENAME_BOYS;
		}
		
		return ret;
	}
	
	/**
	 * creates the additional path to surname file.
	 * A last name consists of both, a prename and a
	 * surname. Because we can only set one of them
	 * in the usual way, we do this manual.
	 * 
	 * @return
	 * 		path to surname
	 */
	private String getFile2()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append(this.getResourceFolder());
		sb.append(this.getLanguageFolder());
		sb.append(Resources.NAMES);
		
		return sb.toString();
	}
}