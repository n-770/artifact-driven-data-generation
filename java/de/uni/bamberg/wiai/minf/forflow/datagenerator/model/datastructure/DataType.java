package de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure;

/**
 * An enumeration for supported data types to generated test data.
 * The literals have names according to the Java specification to
 * make it easier for users to use it properly.
 * </p>
 * If one of the data types below is used, the system knows how
 * to generated test data for this type.
 * At this stage the following types are supported:
 * </p>
 * <ul>
 * 		<li><b>byte</b></li>
 * 			<ul>
 * 				<li>
 *					The <i>byte</i> data type is an  8-bit signed two's complement integer.
 *					It has minimum value of -128 and a maximum value of 127 (inclusive).
 *					The <i>byte</i> data type can be useful for saving memory in large arrays,
 *					where the memory savings actually matters.
 * 				</li>
 * 			</ul>
 * 		<li><b>short</b></li>
 * 			<ul>
 * 				<li>
 * 					The <i>short</i> type is a 16-bit signed two's complement integer. It has
 *					a minimum value of -32,768 and a maximum value of 32,767 (inclusive).
 *					As with <i>byte</i> the same guidelines apply: you can use a short to save
 *					memory in large arrays, in situations where the memory savings actually matters.
 * 				</li>
 * 			</ul>
 * 		<li><b>int</b></li>
 * 			<ul>
 * 				<li>
 * 					The <i>int</i> data type is a 32-bit signed two's complement integer.
 *					It has minimum value of -2,147,483,648 and a maximum value of 2,147,483,647
 *					(inclusive). For integral values, this data type is generally the default choice
 *					unless there is a reason (like the above) to choose something else.
 *					This data type will most likely be large enough for the numbers a program will use,
 *					but if you need a wider range of values, use <i>long</i> instead.
 * 				</li>
 * 			</ul>
 * 		<li><b>long</b></li>
 * 			<ul>
 * 				<li>
 * 					The <i>long</i> data type is a 64-bit signed two's complement integer.
 *					It has a minimum value of -9,223,372,036,854,775,808 and a maximum value
 *					of 9,223,372,036,854,775,807 (inclusive). Use this data type when you need
 *					a range of values wider than those provided by <i>int</i>.
 * 				</li>
 * 			</ul>
 * 		<li><b>float</b></li>
 * 			<ul>
 * 				<li>
 * 					The <i>float</i> data type is a 32-bit floating point.
 *					As with the recommendations for <i>byte</i> and <i>short</i>, use
 *					a <i>float</i> (instead of <i>double</i>) if you need to save memory in large
 *					arrays of floating point numbers. This data type should never be used
 *					for precise values, such as currency.
 * 				</li>
 * 			</ul>
 * 		<li><b>double</b></li>
 * 			<ul>
 * 				<li>
 * 					The <i>double</i> data type is a double 64-bit floating point.
 *					For decimal values, this data type is generally the default choice.
 *					As mentioned above, this data type should never be used for precise values,
 *					such as currency.
 * 				</li>
 * 			</ul>
 * 		<li><b>boolean</b></li>
 * 			<ul>
 * 				<li>
 * 					The <i>boolean</i> data type has only two possible values:
 *					<i>true</i> and <i>false</i>. Use this type for simple flags that track
 *					true/false conditions.
 *					This data type represents one bit of information.
 * 				</li>
 * 			</ul>
 * 		<li><b>char</b></li>
 * 			<ul>
 * 				<li>
 * 					The <i>char</i> data type is a single 16-bit Unicode character.
 *					It has a minimum value of <i>'\u0000'</i> (or 0) and a maximum value
 *					of <i>'u\ffff'</i> (or 65,535 inclusive).
 * 				</li>
 * 			</ul>
 * 		<li><b>String</b></li>
 * 			<ul>
 * 				<li>
 * 					The <i>String</i> data type is a special support for character strings.
 * 				</li>
 * 			</ul>
 * 		<li><b>Date</b></li>
 * 			<ul>
 * 				<li>
 * 					The class Date represents a specific instant in time
 * 				</li>
 * 			</ul>
 * 		</li>
 * 		<li><b>Custom</b></li>
 * 			<ul>
 * 				<li>
 * 					Because we can't simple reject, what doesn't have
 *					one of the above types, we use this instead to model that.
 *					If a user defined data type occurs, we simply use its name.
 *					For that purpose only a set-method has been implemented.
 * 				</li>
 * 			</ul>
 * 		</li>
 * </ul>
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/22/09
 */
public interface DataType
{
	/**
	 * represents the <i>byte</i> data type.
	 * The <i>byte</i> data type is an  8-bit signed two's complement integer.
	 * It has minimum value of -128 and a maximum value of 127 (inclusive).
	 * The <i>byte</i> data type can be useful for saving memory in large arrays,
	 * where the memory savings actually matters.
	 */
	public static final String BYTE = "Byte";
	
	/**
	 * represents the <i>short</i> data type.
	 * The <i>short</i> type is a 16-bit signed two's complement integer. It has
	 * a minimum value of -32,768 and a maximum value of 32,767 (inclusive).
	 * As with <i>byte</i> the same guidelines apply: you can use a short to save
	 * memory in large arrays, in situations where the memory savings actually matters.
	 */
	public static final String SHORT = "Short";
	
	/**
	 * represents the <i>int</i> data type.
	 * The <i>int</i> data type is a 32-bit signed two's complement integer.
	 * It has minimum value of -2,147,483,648 and a maximum value of 2,147,483,647
	 * (inclusive). For integral values, this data type is generally the default choice
	 * unless there is a reason (like the above) to choose something else.
	 * This data type will most likely be large enough for the numbers a program will use,
	 * but if you need a wider range of values, use <i>long</i> instead.
	 */
	public static final String INT = "Integer";
	
	/**
	 * represents <i>long</i> data type.
	 * The <i>long</i> data type is a 64-bit signed two's complement integer.
	 * It has a minimum value of -9,223,372,036,854,775,808 and a maximum value
	 * of 9,223,372,036,854,775,807 (inclusive). Use this data type when you need
	 * a range of values wider than those provided by <i>int</i>.
	 */
	public static final String LONG = "Long";
	
	/**
	 * The <i>float</i> data type is a 32-bit floating point.
	 * As with the recommendations for <i>byte</i> and <i>short</i>, use
	 * a <i>float</i> (instead of <i>double</i>) if you need to save memory in large
	 * arrays of floating point numbers. This data type should never be used
	 * for precise values, such as currency. 
	 */
	public static final String FLOAT = "Float";
	
	/**
	 * The <i>double</i> data type is a double 64-bit floating point.
	 * For decimal values, this data type is generally the default choice.
	 * As mentioned above, this data type should never be used for precise values,
	 * such as currency.
	 */
	public static final String DOUBLE = "Double";
	
	/**
	 * The <i>boolean</i> data type has only two possible values:
	 * <i>true</i> and <i>false</i>. Use this type for simple flags that track
	 * true/false conditions.
	 * This data type represents one bit of information.
	 */
	public static final String BOOLEAN = "Boolean";
	
	/**
	 * The <i>char</i> data type is a single 16-bit Unicode character.
	 * It has a minimum value of <i>'\u0000'</i> (or 0) and a maximum value
	 * of <i>'u\ffff'</i> (or 65,535 inclusive).
	 */
	public static final String CHAR = "Char";
	
	/**
	 * The <i>String</i> data type is a special support for character strings.
	 */
	public static final String STRING = "String";
	
	/**
	 * The <i>Date</i> represents a specific instant in time.
	 */
	public static final String DATE = "Date";
	
	/**
	 * gets the pretty printed name of a literal
	 * of this enumeration.
	 * 
	 * @return
	 * 		name
	 */
	public String getName();
}
