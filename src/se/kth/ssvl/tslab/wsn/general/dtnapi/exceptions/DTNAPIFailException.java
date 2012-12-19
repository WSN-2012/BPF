package se.kth.ssvl.tslab.wsn.general.dtnapi.exceptions;

/**
 * Class to represent DTNAPIFailException. 
 * This exception occurs when DTNAPI is unable to successfully execute the operation it is asked to
 * @author Rerngvit Yanggratoke (rerngvit@kth.se) 
 */
public class DTNAPIFailException extends Exception {

	public DTNAPIFailException(String e) {
		super(e);
	}
	
	/**
	 * The generated UID to support Java Serializable
	 */
	private static final long serialVersionUID = 1132845591560893507L;

}
