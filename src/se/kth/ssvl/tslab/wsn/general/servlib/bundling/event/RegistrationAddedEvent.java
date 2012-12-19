package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.reg.Registration;

/**
 * Event class for new registration arrivals.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class RegistrationAddedEvent extends BundleEvent {
	public RegistrationAddedEvent(Registration reg, event_source_t source) {

		super(event_type_t.REGISTRATION_ADDED);

		registration_ = reg;
		source_ = source;

	}

	/**
	 * The newly added registration
	 */
	private Registration registration_;

	/**
	 * Where is the registration added
	 */
	private event_source_t source_;

	/**
	 * Getter for the newly added registration
	 * 
	 * @return the registration_
	 */
	public Registration registration() {
		return registration_;
	}

	/**
	 * Setter for the newly added registration
	 * 
	 * @param registration
	 *            the registration_ to set
	 */
	public void set_registration(Registration registration) {
		registration_ = registration;
	}

	/**
	 * Getter for where is the registration added
	 * 
	 * @return the source_
	 */
	public event_source_t source() {
		return source_;
	}

	/**
	 * Setter for where is the registration added
	 * 
	 * @param source
	 *            the source_ to set
	 */
	public void set_source(event_source_t source) {
		source_ = source;
	}
};
