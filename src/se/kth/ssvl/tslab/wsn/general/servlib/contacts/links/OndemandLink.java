
package se.kth.ssvl.tslab.wsn.general.servlib.contacts.links;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundleDaemon;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.event.BundleEvent;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.event.ContactEvent.reason_t;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.event.LinkAvailableEvent;
import se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.ConvergenceLayer;

/**
 * "Abstraction for a ONDEMAND link.
 * 
 * ONDEMAND links have to be opened every time one wants to use it and close
 * after an idle period" [DTN2].
 * 
 * @author Maria Jose Peroza Marval (mjpm@kth.se)
 */

public class OndemandLink extends Link {

	/**
	 * Unique identifier according to Java Serializable specification
	 */
	private static final long serialVersionUID = 8419300085330455289L;

	/*
	 * Constructor
	 */
	public OndemandLink(String name, ConvergenceLayer cl, String nexthop) {

		super(name, Link.link_type_t.ONDEMAND, cl, nexthop);
		set_state(Link.state_t.AVAILABLE);

		// override the default for the idle close time
		params_.set_idle_close_time(30);
	}

	/**
	 * This function changes the state of the link
	 */
	@Override
	public void set_initial_state() {

		BundleEvent event = new LinkAvailableEvent(this, reason_t.NO_INFO);
		BundleDaemon BD = BundleDaemon.getInstance();
		BD.post(event);

	}

}
