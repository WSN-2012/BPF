package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundleProtocol;

/**
 * Bundle Event for deleting a Bundle
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class BundleDeleteRequest extends BundleEvent {
	/**
	 * Constructor
	 */
	public BundleDeleteRequest() {
		super(event_type_t.BUNDLE_DELETE);
		// should be processed only by the daemon
		daemon_only_ = true;

	}

	/**
	 * Constructor with DTNBundle specified
	 * 
	 * @param bundle
	 * @param reason
	 */
	public BundleDeleteRequest(Bundle bundle,
			BundleProtocol.status_report_reason_t reason)

	{
		super(event_type_t.BUNDLE_DELETE);
		bundle_ = bundle;
		reason_ = reason;
		daemon_only_ = true;
	}

	/**
	 * Bundle to be deleted
	 */
	private Bundle bundle_;

	/**
	 * The reason code
	 */
	private BundleProtocol.status_report_reason_t reason_;

	/**
	 * Accessor for the Bundle to be deleted
	 * 
	 * @return the bundle_
	 */
	public Bundle bundle() {
		return bundle_;
	}

	/**
	 * Setter for the Bundle to be deleted
	 * 
	 * @param bundle
	 *            the bundle_ to set
	 */
	public void set_bundle(Bundle bundle) {
		bundle_ = bundle;
	}

	/**
	 * Accessor for the reason code
	 * 
	 * @return the reason_
	 */
	public BundleProtocol.status_report_reason_t reason() {
		return reason_;
	}

	/**
	 * Setter for the reason code
	 * 
	 * @param reason
	 *            the reason_ to set
	 */
	public void set_reason(BundleProtocol.status_report_reason_t reason) {
		reason_ = reason;
	}
	
	public String toString() {
		return type_str() + " (@" + Integer.toHexString(super.hashCode())  +
				") (prio: " + super.priority() + ")" + " for bundle " + bundle_.bundleid();
	}
};
