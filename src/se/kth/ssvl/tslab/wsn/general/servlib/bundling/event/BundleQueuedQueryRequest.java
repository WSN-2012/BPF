package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;
import se.kth.ssvl.tslab.wsn.general.servlib.contacts.links.Link;

/**
 * Event class to query the Bundle Queue status
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class BundleQueuedQueryRequest extends CLAQueryReport {
	/**
	 * Main Constructor
	 * 
	 * @param query_id
	 * @param bundle
	 * @param link
	 */
	public BundleQueuedQueryRequest(String query_id, Bundle bundle, Link link) {
		super(event_type_t.CLA_BUNDLE_QUEUED_QUERY, query_id, true);
		bundle_ = bundle;
		link_ = link;

	}

	/**
	 * Bundle to be checked for queued status.
	 */
	private Bundle bundle_;

	/**
	 * Link on which to check if the given bundle is queued.
	 */
	private Link link_;

	/**
	 * Accessor for the Bundle to be checked for queued status.
	 * 
	 * @return the bundle_
	 */
	public Bundle bundle() {
		return bundle_;
	}

	/**
	 * Setter for the Bundle to be checked for queued status.
	 * 
	 * @param bundle
	 *            the bundle_ to set
	 */
	public void set_bundle(Bundle bundle) {
		bundle_ = bundle;
	}

	/**
	 * Accessor for the Link on which to check if the given bundle is queued.
	 * 
	 * @return the link_
	 */
	public Link link() {
		return link_;
	}

	/**
	 * Setter for the Link on which to check if the given bundle is queued.
	 * 
	 * @param link
	 *            the link_ to set
	 */
	public void set_link(Link link) {
		link_ = link;
	}
};