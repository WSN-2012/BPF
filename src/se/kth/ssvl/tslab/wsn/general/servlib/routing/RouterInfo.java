package se.kth.ssvl.tslab.wsn.general.servlib.routing;

/**
 * Interface for router specific state information
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public interface RouterInfo {
	/**
	 * Write the RouterInfo into StringBuffer
	 * 
	 * @param buf
	 *            the StringBuffer to write the result to
	 */
	public void dump(StringBuffer buf);

	/**
	 * Write the Stats into StringBuffer
	 * 
	 * @param buf
	 *            the StringBuffer to write the result to
	 */
	public void dump_stats(StringBuffer buf);
}
