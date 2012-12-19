
package se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles;


/**
 * Class to represent a temporary bundle -- i.e. one that exists only in memory
 * and never goes to the persistent store.
 * 
 * @author Sharjeel Ahmed (sharjeel@kth.se)
 */
public class TempBundle extends Bundle {

	/**
	 * SerialVersionID to Support Serializable.
	 */
	private static final long serialVersionUID = 3950490472303810726L;

	/**
	 * Constructor which forces the payload location to memory.
	 */
	public TempBundle() {
		super(BundlePayload.location_t.MEMORY);
	}

}
