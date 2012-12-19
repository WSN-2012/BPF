package se.kth.ssvl.tslab.wsn.general.systemlib.util;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Set data structure used in Android DTN service
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class Set<E> extends HashSet<E> implements Serializable {

	/**
	 * Serial UID for supporting Java Serializable
	 */
	private static final long serialVersionUID = -5936498800940043289L;

	/**
	 * Constructor
	 */
	public Set() {
		super();
	}

}
