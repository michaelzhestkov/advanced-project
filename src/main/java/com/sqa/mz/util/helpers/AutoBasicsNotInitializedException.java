/**
 *   File Name: AutoBasicsNotInitializedException.java<br>
 *
 *   Zhestkov, Michael<br>
 *   Java Boot Camp Exercise<br>
 *   Instructor: Jean-francois Nepton<br>
 *   Created: May 2, 2016
 *
 */

package com.sqa.mz.util.helpers;

/**
 * AutoBasicsNotInitializedException //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 *
 * @author Zhestkov, Michael
 * @version 1.0.0
 * @since 1.0
 *
 */
public class AutoBasicsNotInitializedException extends Exception {

	@Override
	public String getMessage() {
		return "AutoBasics may have not been initialized, first initialize it before running a method which requires the driver to be initialized";

	}

}
