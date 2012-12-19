package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.contacts.links.Link;

/**
 * Event class for link creation events
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class LinkCreatedEvent extends ContactEvent {
	public LinkCreatedEvent(final Link link, ContactEvent.reason_t reason) {
		super(event_type_t.LINK_CREATED, reason);
		link_ = link;
		reason_ = reason;
	}

	/**
	 * The link that was created
	 */
	private Link link_;

	/**
	 * Getter for the link that was created
	 * 
	 * @return the link_
	 */
	public Link link() {
		return link_;
	}

	/**
	 * Setter for the link that was created
	 * 
	 * @param link
	 *            the link_ to set
	 */
	public void set_link(Link link) {
		link_ = link;
	}
};
