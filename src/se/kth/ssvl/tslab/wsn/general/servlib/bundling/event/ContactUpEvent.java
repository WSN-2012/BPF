
package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.contacts.Contact;

/**
 * Event class for contact up events. This means the DTN Contact Header is
 * exchanged between this node and the other side.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class ContactUpEvent extends ContactEvent {

	/**
	 * Constructor
	 * 
	 * @param contact
	 */
	public ContactUpEvent(Contact contact) {
		super(event_type_t.CONTACT_UP, ContactEvent.reason_t.NO_INFO);
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
