package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.contacts.Contact;
import se.kth.ssvl.tslab.wsn.general.servlib.contacts.links.Link;

/**
 * Request class for changing the state of the link, for example, open -> close.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class LinkStateChangeRequest extends ContactEvent {
	/**
	 * Shared type code for state_t with Link
	 * 
	 * @param link
	 * @param state
	 * @param reason
	 */
	public LinkStateChangeRequest(final Link link, Link.state_t state,
			reason_t reason) {
		super(event_type_t.LINK_STATE_CHANGE_REQUEST, reason);
		link_ = link;
		old_state_ = link.state();
		state_ = state;
		daemon_only_ = true;

	}

	/**
	 * The link to be changed
	 */
	private Link link_;

	/**
	 * Requested state
	 */
	private Link.state_t state_;

	/**
	 * The active Contact when the request was made
	 */
	private Contact contact_;

	/**
	 * State when the request was made
	 */
	private Link.state_t old_state_;

	/**
	 * Getter for the link to be changed
	 * 
	 * @return the link_
	 */
	public Link link() {
		return link_;
	}

	/**
	 * Setter for the link to be changed
	 * 
	 * @param link
	 *            the link_ to set
	 */
	public void set_link(Link link) {
		link_ = link;
	}

	/**
	 * Getter for the Requested state
	 * 
	 * @return the state_
	 */
	public Link.state_t state() {
		return state_;
	}

	/**
	 * Setter for the Requested state
	 * 
	 * @param state
	 *            the state_ to set
	 */
	public void set_state(Link.state_t state) {
		state_ = state;
	}

	/**
	 * Getter for the active Contact when the request was made
	 * 
	 * @return the contact_
	 */
	public Contact contact() {
		return contact_;
	}

	/**
	 * Setter for the active Contact when the request was made
	 * 
	 * @param contact
	 *            the contact_ to set
	 */
	public void set_contact(Contact contact) {
		contact_ = contact;
	}

	/**
	 * Getter for the State when the request was made
	 * 
	 * @return the old_state_
	 */
	public Link.state_t old_state() {
		return old_state_;
	}

	/**
	 * Setter for the State when the request was made
	 * 
	 * @param oldState
	 *            the old_state_ to set
	 */
	public void set_old_state(Link.state_t oldState) {
		old_state_ = oldState;
	}
};
