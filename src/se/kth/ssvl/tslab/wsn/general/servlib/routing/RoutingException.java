
package se.kth.ssvl.tslab.wsn.general.servlib.routing;

/**
 * Base Exception in Routing package
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class RoutingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7657675067087522528L;

}

/**
 * Exception represents Router type unknown
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
class UnknownRouterTypeException extends RoutingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3422906230682133917L;

}
