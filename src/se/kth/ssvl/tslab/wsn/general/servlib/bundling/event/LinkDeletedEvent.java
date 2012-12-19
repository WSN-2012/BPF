package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.contacts.links.Link;

/**
 * Event generated after Link deletion
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class LinkDeletedEvent extends ContactEvent {

	public LinkDeletedEvent(final Link link, ContactEvent.reason_t reason) {
		super(event_type_t.LINK_DELETED, reason);

		link_ = link;
		reason_ = reason;

	}

	/**
	 * The link that was deleted
	 */
	private Link link_;

	/**
	 * Getter for the link that was deleted
	 * 
	 * @return the link_
	 */
	public Link link() {
		return link_;
	}

	/**
	 * Setter for the link that was deleted
	 * 
	 * @param link
	 *            the link_ to set
	 */
	public void set_link(Link link) {
		link_ = link;
	}
}
