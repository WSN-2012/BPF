
package se.kth.ssvl.tslab.wsn.general.servlib.contacts.links;

import java.util.HashSet;

import se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.ConvergenceLayer;

/**
 * "Abstraction for a SCHEDULED link. Scheduled links have a list of future
 * contacts" [DTN2].
 * 
 * @author Maria Jose Peroza Marval (mjpm@kth.se)
 */

public class ScheduledLink extends Link {

	/**
	 * Unique identifier according to Java Serializable specification
	 */
	private static final long serialVersionUID = -8410070509285427644L;

	/**
	 * Constructor
	 */
	public ScheduledLink(String name, ConvergenceLayer cl, String nexthop) {

		super(name, Link.link_type_t.SCHEDULED, cl, nexthop);
		fcts_ = new HashSet<FutureContact>();

	}

	/**
	 * Return the list of future contacts that exist on the link
	 */
	public HashSet<FutureContact> future_contacts() {
		return fcts_;
	}

	// Add a future contact
	public void add_fc(FutureContact fc) {
		fcts_.add(fc);
	}

	// Remove a future contact
	public void delete_fc(FutureContact fc) {
		fcts_.remove(fc);
	}

	// Return list of all future contacts
	public HashSet<FutureContact> future_contacts_list() {
		return fcts_;
	}

	protected HashSet<FutureContact> fcts_;
}

/**
 * "Abstract base class for FutureContact Relevant only for scheduled links"
 * [DTN2].
 */
class FutureContact {

	/**
	 * Constructor
	 */
	public FutureContact() {
		start_ = 0;
		duration_ = 0;
	}

	// / Time at which contact starts, 0 value means not defined
	long start_;

	// / Duration for this future contact, 0 value means not defined
	long duration_;

}
