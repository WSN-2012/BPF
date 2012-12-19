
package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;

/**
 * Event class for bundle expiration.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class BundleExpiredEvent extends BundleEvent {
	public BundleExpiredEvent(Bundle bundle) {

		super(event_type_t.BUNDLE_EXPIRED);
		bundle_ = bundle;

	}

	/**
	 * The expired bundle
	 */
	private Bundle bundle_;

	/**
	 * Accessor for the expired bundle
	 * 
	 * @return the bundle_
	 */
	public Bundle bundle() {
		return bundle_;
	}

	/**
	 * Setter for the expired bundle
	 * 
	 * @param bundle
	 *            the bundle_ to set
	 */
	public void set_bundle(Bundle bundle) {
		bundle_ = bundle;
	}
};
