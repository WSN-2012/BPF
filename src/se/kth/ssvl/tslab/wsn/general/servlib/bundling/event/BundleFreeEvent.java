package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;

/**
 * Event class to remove Bundle from the system. This includes removing from the
 * storage.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class BundleFreeEvent extends BundleEvent {
	public BundleFreeEvent(Bundle bundle) {
		super(event_type_t.BUNDLE_FREE);
		// should be processed only by the daemon
		daemon_only_ = true;
		bundle_ = bundle;

	}

	/**
	 * The Bundle to be freed
	 */
	private Bundle bundle_;

	/**
	 * Accessor for the Bundle to be freed
	 * 
	 * @return the bundle_
	 */
	public Bundle bundle() {
		return bundle_;
	}

	/**
	 * Setter for the Bundle to be freed
	 * 
	 * @param bundle
	 *            the bundle_ to set
	 */
	public void set_bundle(Bundle bundle) {
		bundle_ = bundle;
	}
};
