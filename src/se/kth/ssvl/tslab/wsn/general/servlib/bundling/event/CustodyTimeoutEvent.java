package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;
import se.kth.ssvl.tslab.wsn.general.servlib.contacts.links.Link;

/**
 * Event class for custody transfer timeout events
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class CustodyTimeoutEvent extends BundleEvent {
	public CustodyTimeoutEvent(Bundle bundle, final Link link) {
		super(event_type_t.CUSTODY_TIMEOUT);
		bundle_ = bundle;
		link_ = link;
	}

	/**
	 * The bundle whose timer fired
	 */
	private Bundle bundle_;

	/**
	 * The link it was sent on
	 */
	private Link link_;

	/**
	 * Getter for the bundle whose timer fired
	 * 
	 * @return the bundle_
	 */
	public Bundle bundle() {
		return bundle_;
	}

	/**
	 * Setter for the bundle whose timer fired
	 * 
	 * @param bundle
	 *            the bundle_ to set
	 */
	public void set_bundle(Bundle bundle) {
		bundle_ = bundle;
	}

	/**
	 * Getter for the link it was sent on
	 * 
	 * @return the link_
	 */
	public Link link() {
		return link_;
	}

	/**
	 * Setter for the link it was sent on
	 * 
	 * @param link
	 *            the link_ to set
	 */
	public void set_link(Link link) {
		link_ = link;
	}
};
