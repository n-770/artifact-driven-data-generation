package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller;

/**
 * <b>UMLType</b> defines three types of possible class types.
 * Comparable to a tree in UML models exists an hierarchy.
 * </p>
 * The top-level classes do not have any base class itself, but
 * hand down it's behaviour to sub-classes. Here we say it's a root.
 * To stick at the tree image.
 * </p>
 * Classes which inherit from base class and have itself sub-classes
 * are said to be nodes, because they have the job of an intermediate.
 * </p>
 * The last case is a class, which has a base class, but has itself no
 * sub-class. We say that's a leave.
 * </p>
 * Classes which have the roll of a leave are the once we're interested in.
 * 'Cos they are concrete specifications to which test data should be generated
 * at the end.
 * </p>
 * Notice, this enumeration is stored in controller layer and is used only
 * by that. It doesn't reflect the model layer or anything else.
 * It's just used to controller the parsing of EMF Ecore files properly. 
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/09/09
 */
public enum UMLType
{
	/**
	 * In UML language a root is a base class. The class
	 * itself has no super class, but has one or more
	 * subclasses.
	 */
	ROOT,
	
	/**
	 * A node in UML language has a base class and one 
	 * or more subclasses.
	 */
	NODE,
	
	/**
	 * Has a node as its base class, but doesn't have any
	 * subclasses itself. We call it a leaf as a matter of fact.
	 */
	LEAF;
}