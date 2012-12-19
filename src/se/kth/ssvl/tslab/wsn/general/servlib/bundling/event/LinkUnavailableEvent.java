package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.contacts.links.Link;

/**
 * Event class for link unavailable events
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */

public class LinkUnavailableEvent extends ContactEvent {
	public LinkUnavailableEvent(final Link link, ContactEvent.reason_t reason) {

		super(event_type_t.LINK_UNAVAILABLE, reason);
		link_ = link;
		reason_ = reason;
	}

	/**
	 * The link that is unavailable
	 */
	private Link link_;

	/**
	 * Getter for the link that is unavailable
	 * 
	 * @return the link_
	 */
	public Link link() {
		return link_;
	}

	/**
	 * Setter for the that is unavailable
	 * 
	 * @param link
	 *            the link_ to set
	 */
	public void set_link(Link link) {
		link_ = link;
	}
};
