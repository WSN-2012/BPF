package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.contacts.links.Link;

/**
 * Class to notify others components about Link's availability
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class LinkAvailableEvent extends ContactEvent {
	/**
	 * main constructor
	 * 
	 * @param link
	 * @param reason
	 */
	public LinkAvailableEvent(final Link link, ContactEvent.reason_t reason) {

		super(event_type_t.LINK_AVAILABLE, reason);
		link_ = link;
	}

	/**
	 * The link that is available
	 */
	private Link link_;

	/**
	 * Getter for the link that is available
	 * 
	 * @return the link_
	 */
	public Link link() {
		return link_;
	}

	/**
	 * Setter for the link that is available
	 * 
	 * @param link
	 *            the link_ to set
	 */
	public void set_link(Link link) {
		link_ = link;
	}
};
