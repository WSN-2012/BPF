package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;
import se.kth.ssvl.tslab.wsn.general.servlib.reg.Registration;

/**
 * Bundle event for the Bundle delivered to a Registration
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class BundleDeliveredEvent extends BundleEvent {
	/**
	 * main Constructor taking bundle and registration as input
	 * 
	 * @param bundle
	 * @param registration
	 */
	public BundleDeliveredEvent(Bundle bundle, Registration registration) {
		super(event_type_t.BUNDLE_DELIVERED);
		bundle_ = bundle;
		registration_ = registration;
	}

	/**
	 * The delivered bundle
	 */
	private Bundle bundle_;

	/**
	 * The registration receiving the Bundle
	 */
	private Registration registration_;

	/**
	 * Accessor for the delivered bundle
	 * 
	 * @return the bundle_
	 */
	public Bundle bundle() {
		return bundle_;
	}

	/**
	 * Setter for the delivered bundle
	 * 
	 * @param bundle
	 *            the bundle_ to set
	 */
	public void set_bundle(Bundle bundle) {
		bundle_ = bundle;
	}

	/**
	 * Accessor for the registration receiving the Bundle
	 * 
	 * @return the registration_
	 */
	public Registration registration() {
		return registration_;
	}

	/**
	 * Setter for the registration receiving the Bundle
	 * 
	 * @param registration
	 *            the registration_ to set
	 */
	public void set_registration(Registration registration) {
		registration_ = registration;
	}
}
