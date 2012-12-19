package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.contacts.links.Link;

/**
 * Event class for requesting deletion of a link.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class LinkDeleteRequest extends BundleEvent {
	/**
	 * constructor
	 * 
	 * @param link
	 */
	public LinkDeleteRequest(Link link) {
		super(event_type_t.LINK_DELETE);

		link_ = link;

		// should be processed only by the daemon
		daemon_only_ = true;
	}

	/**
	 * The link to be deleted
	 */
	private Link link_;

	/**
	 * Getter for the link to be deleted
	 * 
	 * @return the link_
	 */
	public Link link() {
		return link_;
	}

	/**
	 * Setter for the link to be deleted
	 * 
	 * @param link
	 *            the link_ to set
	 */
	public void set_link(Link link) {
		link_ = link;
	}
};