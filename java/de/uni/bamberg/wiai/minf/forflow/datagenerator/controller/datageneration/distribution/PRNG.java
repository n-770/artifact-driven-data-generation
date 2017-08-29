package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution;

/**
 * This enumeration specifies all supported pseudo random number generators (PRNG).
 * Here are all random number generators listed the <b>uncommon.math</b> package
 * supports. The main intention of this class is to get a quick overview of the
 * different RNG's. So they could get substituted by each other easily by passing
 * just the enum type to the probability distribution in question.
 * You can find the package at <a href="https://uncommons-maths.dev.java.net">uncommon math</a>.
 * </p>
 * <font size=6><b>Pseudo random number generators</b></font></br>
 * <ul>
 * 		<li><b>MersenneTwisterRNG</b></li>
 * 			<ul>
 * 				<li>
 * 					A Java port of the fast & reliable Mersenne Twister originally developed by
 * 					Makota Mastumoto and Takuji Nishimura. It is faster than <i>java.uti.random</i>
 * 					and doesn't have the same statistical flaws and also has a long period
 * 					(2^19937).
 * 				</li>
 * 				<li>Mersenne Twister is an excellent general purpose RNG</li>
 * 			</ul>
 * 		<li><b>AESCounterRNG</b></li>
 * 			<ul>
 * 				<li>
 * 					This is a cryptographically-strong non linear RNG that is around 10 times
 * 					faster than <i>java.security.SecureRandom</i>. Reverse engineering the
 * 					generator state form observations of its output would involve cracking
 * 					the AES block cipher.
 * 				</li>
 * 			</ul>
 * 		<li><b>CellularAutomatonRNG</b></li>
 * 			<ul>
 * 				<li>
 * 					A Java port of the fast Cellular Automation RNG of Tony Pasqualoni.
 * 					It uses a 256 cell automaton to generate random values.
 * 				</li>
 * 			</ul>
 * 		<li><b>SecureRandom</b></li>
 * 			<ul>
 * 				<li>
 * 					Provides a cryptographically strong random number generator.
 * 					It is shipped with the JDK. More information about it can be found
 * 					in the Java API.
 * 				</li>
 * 			</ul>
 * 		<li><b>XORShiftRNG</b></li>
 * 			<ul>
 * 				<li>
 * 					A java implementation of the very fast RNG described by George Marsaglia.
 * 					It has a period of about 2^160, although much shorter than the Mersenne
 * 					Twister's, is still significantly longer than that of <i>java.util.Random</i> 
 * 				</li>
 * 				<li>
 * 					This is the RNG to use when performance is the primary concern.
 * 					It can be up to twice as fast as the Mersenne Twister.
 * 				</li>
 * 			</ul>
 * 		<li><b>CMWC4096RNG</b></li>
 * 			<ul>
 * 				<li>
 * 					A Java implementation of a Complementary-Multiply-With-Carry (CMWC) RNG
 * 					described by George Marsaglia. It ahs an extremely long period of 2^131104
 * 					and performance comparable to the Mersenne Twister.
 * 				</li>
 * 				<li>
 * 					The Mersenne Twister has the advantage of only requiring 16 bytes of seed
 * 					data rather than the 16 kbyte required by CMWC.
 * 				</li>
 * 			</ul>
 * 		<li><b>JavaRNG</b></li>
 * 			<ul>
 * 				<li>The default RNG shipped with the JDK.</li>
 * 			</ul>
 * </ul>
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/15/09
 */
public enum PRNG
{
	/**
	 * A Java port of the fast & reliable Mersenne Twister originally by
	 * Makota Mastumoto and Takuji Nishimura. It is faster than <i>java.uti.random</i>
	 * and doesn't have the same statistical flaws and also has a long period
	 * (2^19937).
	 * </p>
	 * Mersenne Twister is an excellent general purpose RNG
	 */
	MersenneTwister,
	
	/**
	 * This is a cryptographically-strong non linear RNG that is around 10 times
	 * faster than <i>java.security.SecureRandom</i>. Reverse engineering the
	 * generator state form observations of its output would involve cracking
	 * the AES block cipher.
	 */
	AESCounter,
	
	/**
	 * A Java port of the fast Cellular Automation RNG of Tony Pasqualoni.
	 * It uses a 256 cell automaton to generate random values.
	 */
	CelllarAutomaton,
	
	/**
	 * A Java implementation of a Complementary-Multiply-With-Carry (CMWC) RNG
	 * described by George Marsaglia. It ahs an extremely long period of 2^131104
	 * and performance comparable to the Mersenne Twister.
	 * </p>
	 * The Mersenne Twister has the advantage of only requiring 16 bytes of seed
	 * data rather than the 16 kbyte required by CMWC.
	 */
	CMWC4096,
	
	/**
	 * Provides a cryptographically strong random number generator.
	 * It is shipped with the JDK. More information about it can be found
	 * in the Java API.
	 */
	SecureRandom,
	
	/**
	 * A java implementation of the very fast RNG described by George Marsaglia.
	 * It has a period of about 2^160, although much shorter than the Mersenne
	 * Twister's, is still significantly longer than that of <i>java.util.Random</i>
	 * </p>
	 * This is the RNG to use when performance is the primary concern.
	 * It can be up to twice as fast as the Mersenne Twister.
	 */
	XORShift,
	
	/**
	 * The default RNG shipped with the JDK. It is not recommended to
	 * use this random number generator.
	 */
	JavaRNG;
}