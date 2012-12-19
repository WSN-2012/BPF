
package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.contacts.Contact;

/**
 * Event class for contact down events.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class ContactDownEvent extends ContactEvent {

	/**
	 * main constructor
	 * 
	 * @param contact
	 * @param reason
	 */
	public ContactDownEvent(final Contact contact, reason_t reason) {

		super(event_type_t.CONTACT_DOWN, reason);
		contact_ = contact;
	}

	/**
	 * The contact that is up
	 */
	private Contact contact_;

	/**
	 * Getter for the contact that is up
	 * 
	 * @return
	 */
	public Contact contact() {
		return contact_;
	}

	/**
	 * Setter for the contact that is up
	 * 
	 * @param contact
	 */
	public void set_contact(Contact contact) {
		contact_ = contact;
	}
};
