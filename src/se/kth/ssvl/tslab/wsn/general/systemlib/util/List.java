package se.kth.ssvl.tslab.wsn.general.systemlib.util;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Generic List used in AnroidDTN. This list is made by extending Java ArrayList
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class List<T> extends ArrayList<T> implements Serializable {

	/**
	 * Unique identifier according to Java Serializable specification
	 */
	private static final long serialVersionUID = 1850296177208192183L;

	/**
	 * Get the first element of the list
	 * 
	 * @return the first element. And, null, if it's impossible to peak
	 */
	public final T front() {
		if (this.size() > 0)
			return get(0);
		else
			return null;
	}

	/**
	 * Get the last element of the list
	 * 
	 * @return the last element. And, null, if it's impossible to peak.
	 */
	public final T back() {
		if (this.size() > 0)
			return get(this.size() - 1);
		else
			return null;
	}

}
