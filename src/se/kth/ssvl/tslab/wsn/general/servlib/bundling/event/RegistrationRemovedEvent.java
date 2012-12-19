
package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.reg.Registration;

/**
 * Event class for registration removals.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */

public class RegistrationRemovedEvent extends BundleEvent {
	public RegistrationRemovedEvent(Registration reg) {
		super(event_type_t.REGISTRATION_REMOVED);
		registration_ = reg;
	}

	/**
	 * The to-be-removed registration
	 */
	private Registration registration_;

	/**
	 * Getter for the to-be-removed registration
	 * 
	 * @return the registration_
	 */
	public Registration registration() {
		return registration_;
	}

	/**
	 * Setter for the to-be-removed registration
	 * 
	 * @param registration
	 *            the registration_ to set
	 */
	public void set_registration(Registration registration) {
		registration_ = registration;
	}
};
