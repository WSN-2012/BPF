package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;
import se.kth.ssvl.tslab.wsn.general.servlib.contacts.links.Link;

/**
 * Event generated after the Bundle was canceled successfully
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class BundleSendCancelledEvent extends BundleEvent {
	/**
	 * Constructor
	 * 
	 * @param bundle
	 * @param link
	 */
	public BundleSendCancelledEvent(Bundle bundle, final Link link) {
		super(event_type_t.BUNDLE_CANCELLED);
		bundle_ = bundle;
		link_ = link;
	}

	/**
	 * The canceled bundle
	 */
	private Bundle bundle_;

	/**
	 * The link the Bundle was queued on
	 */
	private Link link_;

	/**
	 * Accessor for the canceled bundle
	 * 
	 * @return the bundle_
	 */
	public Bundle bundle() {
		return bundle_;
	}

	/**
	 * Setter for the canceled bundle
	 * 
	 * @param bundle
	 *            the bundle_ to set
	 */
	public void set_bundle(Bundle bundle) {
		bundle_ = bundle;
	}

	/**
	 * Accessor for the link the Bundle was queued on
	 * 
	 * @return the link_
	 */
	public Link link() {
		return link_;
	}

	/**
	 * Setter for the link the Bundle was queued on
	 * 
	 * @param link
	 *            the link_ to set
	 */
	public void set_link(Link link) {
		link_ = link;
	}
};