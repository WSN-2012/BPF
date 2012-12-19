package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.contacts.Contact;

/**
 * Event class for contact attribute change
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class ContactAttributeChangedEvent extends ContactEvent {
	/**
	 * main constructor
	 * 
	 * @param contact
	 * @param reason
	 */
	public ContactAttributeChangedEvent(final Contact contact, reason_t reason) {
		super(event_type_t.CONTACT_ATTRIB_CHANGED, reason);

		contact_ = contact;
		reason_ = reason;
	}

	/**
	 * The contact whose attributes changed
	 */
	private Contact contact_;

	/**
	 * Getter for contact whose attributes changed
	 * 
	 * @return the contact_
	 */
	public Contact contact() {
		return contact_;
	}

	/**
	 * Setter for the contact whose attributes change
	 * 
	 * @param contact
	 *            the contact_ to set
	 */
	public void set_contact(Contact contact) {
		contact_ = contact;
	}
};
