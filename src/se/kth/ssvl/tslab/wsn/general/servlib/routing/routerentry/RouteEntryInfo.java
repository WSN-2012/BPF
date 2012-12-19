package se.kth.ssvl.tslab.wsn.general.servlib.routing.routerentry;

/**
 * Abstraction to put algorithm state in particular route entry
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public abstract class RouteEntryInfo {

	/**
	 * Dump information to StringBuffer. This is useful for debugging
	 * 
	 * @param buf
	 *            StrinBuffer
	 */
	public abstract void dump(StringBuffer buf);

	/**
	 * Dump statistics information to StringBuffer
	 * 
	 * @param buf
	 *            StrinBuffer
	 */
	public abstract void dump_stats(StringBuffer buf);
}
