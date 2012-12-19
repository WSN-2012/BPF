package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.reg.Registration;

/**
 * Registration Delete Event
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class RegistrationDeleteRequest extends BundleEvent {
	/**
	 * main constructor
	 * 
	 * @param registration
	 */
	public RegistrationDeleteRequest(Registration registration) {
		super(event_type_t.REGISTRATION_DELETE);
		daemon_only_ = true;
		registration_ = registration;

	}

	/**
	 * The registration to be deleted
	 */
	private Registration registration_;

	/**
	 * Getter for the registration to be deleted
	 * 
	 * @return the registration_
	 */
	public Registration registration() {
		return registration_;
	}

	/**
	 * Setter for the registration to be deleted
	 * 
	 * @param registration
	 *            the registration_ to set
	 */
	public void set_registration(Registration registration) {
		registration_ = registration;
	}
};