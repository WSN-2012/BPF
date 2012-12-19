package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.reg.Registration;

/**
 * Registration Expiration Event
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 * 
 */
public class RegistrationExpiredEvent extends BundleEvent {
	public RegistrationExpiredEvent(Registration registration) {
		super(event_type_t.REGISTRATION_EXPIRED);
		registration_ = registration;

	}

	/**
	 * The expired Registration
	 */
	private Registration registration_;

	/**
	 * Getter for the expired Registration
	 * 
	 * @return the registration_
	 */
	public Registration registration() {
		return registration_;
	}

	/**
	 * Setter for the expired Registration
	 * 
	 * @param registration
	 *            the registration_ to set
	 */
	public void set_registration(Registration registration) {
		registration_ = registration;
	}
};
