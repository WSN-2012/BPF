
package se.kth.ssvl.tslab.wsn.general.servlib.contacts.links;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundleDaemon;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.event.BundleEvent;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.event.ContactEvent.reason_t;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.event.LinkStateChangeRequest;
import se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.ConvergenceLayer;

/**
 * "ALWAYSON links are immediately opened upon creation and remain open for
 * their duration" [DTN2].
 * 
 * @author Maria Jose Peroza Marval (mjpm@kth.se)
 */

public class AlwaysOnLink extends Link {

	/**
	 * Unique identifier according to Java Serializable specification
	 */
	private static final long serialVersionUID = -8238280579978005251L;

	/**
	 * Constructor
	 */
	public AlwaysOnLink(String name, ConvergenceLayer cl, String nexthop) {

		super(name, Link.link_type_t.ALWAYSON, cl, nexthop);
		set_state(Link.state_t.UNAVAILABLE);
	}

	/**
	 * This function changes the state of the link to OPEN
	 */

	@Override
	public void set_initial_state() {

		BundleEvent event = new LinkStateChangeRequest(this, state_t.OPEN,
				reason_t.USER);
		BundleDaemon BD = BundleDaemon.getInstance();
		BD.post_at_head(event);

	}
}
